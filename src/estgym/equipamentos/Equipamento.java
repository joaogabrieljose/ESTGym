package estgym.equipamentos;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import estgym.modelo.Servico;
import estgym.modelo.UtilizacaoAtiva;

public abstract class Equipamento {

    private final int id;
    private final String nome;
    private final Servico servico;
    private final boolean exigeEntrada;

    private final Duration duracaoFixa; 

    private final List<UtilizacaoAtiva> utilizacoesAtivas;
    
   protected Equipamento(int id,String nome,Servico servico,boolean exigeEntrada,Duration duracaoFixa) {
        if (id <= 0) {
            throw new IllegalArgumentException("O ID do equipamento deve ser positivo.");
        }

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome do equipamento é obrigatório.");
        }

        if (duracaoFixa != null&& (duracaoFixa.isNegative()|| duracaoFixa.isZero())) {
            throw new IllegalArgumentException( "A duração fixa deve ser positiva.");
        }

        this.id = id;
        this.nome = nome.trim();
        this.servico = Objects.requireNonNull(servico, "O serviço é obrigatório.");
        this.exigeEntrada = exigeEntrada;
        this.duracaoFixa = duracaoFixa;
        this.utilizacoesAtivas = new ArrayList<>();
    }


    public int getId() {
        return id;
    }


    public String getNome() {
        return nome;
    }


    public Servico getServico() {
        return servico;
    }


    public boolean isExigeEntrada() {
        return exigeEntrada;
    } 

  
    public Duration getDuracaoFixa() {
        return duracaoFixa;
    }

  public List<UtilizacaoAtiva> getUtilizacoesAtivas(LocalDateTime agora) {
        atualizarUtilizacoes(agora);
        return List.copyOf(utilizacoesAtivas);
    }

    public void atualizarUtilizacoes(LocalDateTime agora) {
        Objects.requireNonNull(agora, "A data é obrigatória.");
        utilizacoesAtivas.removeIf(utilizacao -> !utilizacao.estaEmCurso(agora));
    }

    protected int contarUtilizacoesAtivas(LocalDateTime agora) {
        atualizarUtilizacoes(agora);
        return utilizacoesAtivas.size();
    }

    public void iniciarUtilizacao( UtilizacaoAtiva utilizacao,LocalDateTime agora) {

        Objects.requireNonNull(utilizacao, "A utilização é obrigatória." );
        Objects.requireNonNull(agora, "A data é obrigatória.");

        if (utilizacao.getEquipamento() != this) {
            throw new IllegalArgumentException("A utilização pertence a outro equipamento.");
        }

        if (!utilizacao.estaEmCurso(agora)) {
            throw new IllegalArgumentException( "A utilização não está ativa na data indicada.");
        }

        if (!estaDisponivel(agora)) {
            throw new IllegalStateException( "O equipamento não está disponível.");
        }

        utilizacoesAtivas.add(utilizacao);
    }

    public abstract boolean estaDisponivel(LocalDateTime agora);

}
