package estgym.ginasio;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;



import estgym.equipamentos.Equipamento;
import estgym.equipamentos.EquipamentoAula;
import estgym.modelo.Membro;
import estgym.modelo.RegistoUtilizacao;
import estgym.modelo.TipoService;
import estgym.modelo.UtilizacaoAtiva;
import estgym.regras.ContratoServico;

public class GestorUtilizacoes {

    private final List<UtilizacaoAtiva> utilizacoes =new ArrayList<>();

     public UtilizacaoAtiva iniciarUtilizacao(Membro membro,Equipamento equipamento,LocalDateTime agora) {
    
        Objects.requireNonNull(membro, "O membro é obrigatório.");
        Objects.requireNonNull(equipamento, "O equipamento é obrigatório.");
        Objects.requireNonNull(agora, "A data é obrigatória.");

        // Nesta fase, as entradas e saídas serão tratadas
        // separadamente.
        TipoService tipo = equipamento.getServico().getTipo();

        if (tipo == TipoService.ENTRADA|| tipo == TipoService.SAIDA) {
            throw new IllegalArgumentException( "As entradas e saídas exigem tratamento específico.");
        }

        if (equipamento instanceof EquipamentoAula) {
            throw new IllegalArgumentException("As aulas de grupo serão integradas posteriormente.");
        }

        if (equipamento.isExigeEntrada() && !membro.isDentroGinasio()) {
            throw new IllegalStateException("O membro não está dentro do ginásio.");
        }

        if (membro.getUtilizacaoAtiva() != null) {
            throw new IllegalStateException("O membro ainda possui uma utilização por finalizar.");
        }

        ContratoServico contrato = membro.getContratos()
            .stream()
            .filter(c -> c.getServico().getId()
                == equipamento.getServico().getId())
            .findFirst()
            .orElseThrow(() -> new IllegalStateException(
                "O membro não possui contrato para este serviço."
            ));

        if (!contrato.permiteUtilizacao(membro, agora)) {
            throw new IllegalStateException("As regras do contrato não permitem a utilização.");
        }

        if (!equipamento.estaDisponivel(agora)) {
            throw new IllegalStateException("O equipamento está ocupado.");
        }

        Duration duracao = equipamento.getDuracaoFixa();

        if (duracao == null) {
            duracao = contrato.getDuracaoPermitida();
        }

        if (duracao == null|| duracao.isZero()|| duracao.isNegative()) {
            throw new IllegalStateException("Não foi possível determinar a duração da utilização.");
        }

        UtilizacaoAtiva utilizacao = new UtilizacaoAtiva(membro,equipamento.getServico(),equipamento,agora,agora.plus(duracao));
        membro.iniciarUtilizacao(utilizacao, agora);
        equipamento.iniciarUtilizacao(utilizacao, agora);
        utilizacoes.add(utilizacao);

        return utilizacao;
    }

    public void finalizarUtilizacao(UtilizacaoAtiva utilizacao,LocalDateTime agora) {

        Objects.requireNonNull(utilizacao, "A utilização é obrigatória.");
        Objects.requireNonNull(agora, "A data é obrigatória.");

        if (!utilizacao.terminou(agora)) {
            throw new IllegalStateException("A utilização ainda não terminou.");
        }

        Membro membro = utilizacao.getMembro();
        Equipamento equipamento = utilizacao.getEquipamento();

        if (membro.getUtilizacaoAtiva() != utilizacao) {
            throw new IllegalStateException( "Esta utilização já foi finalizada ou não pertence ao membro.");
        }

        RegistoUtilizacao registo = new RegistoUtilizacao( utilizacao.getServico(), equipamento, utilizacao.getDataInicio(), utilizacao.getDataFimPrevista());

        membro.adicionarRegisto(registo);
        equipamento.removerUtilizacao(utilizacao);
        membro.terminarUtilizacao(agora);
        utilizacoes.remove(utilizacao);
    }


    public void atualizarUtilizacoes(LocalDateTime agora) {

        Objects.requireNonNull(agora, "A data atual é obrigatória.");

        // Criar uma cópia para evitar modificar
        // a lista enquanto a percorremos.
        List<UtilizacaoAtiva> copia =new ArrayList<>(utilizacoes);
        for (UtilizacaoAtiva utilizacao : copia) {

            if (utilizacao.terminou(agora)) {
                finalizarUtilizacao(utilizacao, agora);
            }
        }
    }

    
}
