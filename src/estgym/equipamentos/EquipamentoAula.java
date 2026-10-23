package estgym.equipamentos;

import java.time.Duration;
import java.time.LocalDateTime;

import estgym.modelo.Servico;
import estgym.modelo.UtilizacaoAtiva;

public class EquipamentoAula extends Equipamento{

    private final int capacidadeMaxima;

    public EquipamentoAula(int id,String nome,Servico servico, Duration duracao,int capacidadeMaxima) {

        super(id, nome, servico, true, duracao);

        if (duracao == null) {
            throw new IllegalArgumentException( "A duração da aula é obrigatória.");
        }

        if (capacidadeMaxima <= 0) {
            throw new IllegalArgumentException( "A capacidade deve ser positiva.");
        }

        this.capacidadeMaxima = capacidadeMaxima;
    }

    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public int getVagasDisponiveis(LocalDateTime agora) {
        return capacidadeMaxima - contarUtilizacoesAtivas(agora);
    }

    @Override
    public boolean estaDisponivel(LocalDateTime agora) {
        return getVagasDisponiveis(agora) > 0;
    }

    public LocalDateTime obterFimSessao(LocalDateTime agora) {

        for (UtilizacaoAtiva utilizacao: getUtilizacoesAtivas(agora)) {
            return utilizacao.getDataFimPrevista();
        }
        
        return agora.plus(getDuracaoFixa());
    }
}
