
package estgym.modelo;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

import estgym.equipamentos.Equipamento;

public class UtilizacaoAtiva {

    private final Membro membro;
    private final Servico servico;
    private final Equipamento equipamento;
    private final LocalDateTime dataInicio;
    private final LocalDateTime dataFimPrevista;

    public UtilizacaoAtiva( Membro membro,Servico servico,Equipamento equipamento,LocalDateTime dataInicio,LocalDateTime dataFimPrevista) {

        this.membro = Objects.requireNonNull(membro, "O membro é obrigatório.");
        this.servico = Objects.requireNonNull( servico, "O serviço é obrigatório.");
        this.equipamento = Objects.requireNonNull( equipamento, "O equipamento é obrigatório.");
        this.dataInicio = Objects.requireNonNull(dataInicio, "A data de início é obrigatória.");
        this.dataFimPrevista = Objects.requireNonNull(dataFimPrevista, "A data de fim prevista é obrigatória.");

        if (!dataFimPrevista.isAfter(dataInicio)) {
            throw new IllegalArgumentException( "A data de fim deve ser posterior à data de início.");
        }

        if (equipamento.getServico().getId() != servico.getId()) {
            throw new IllegalArgumentException("O serviço não corresponde ao equipamento.");
        }
    }

    public Membro getMembro() {
        return membro;
    }

    public Servico getServico() {
        return servico;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public LocalDateTime getDataFimPrevista() {
        return dataFimPrevista;
    }

    public boolean estaEmCurso(LocalDateTime agora) {
        Objects.requireNonNull(agora, "A data atual é obrigatória.");

        return !agora.isBefore(dataInicio) && agora.isBefore(dataFimPrevista);
    }

    public boolean terminou(LocalDateTime agora) {
        Objects.requireNonNull(agora, "A data atual é obrigatória.");
        return !agora.isBefore(dataFimPrevista);
    }

    public Duration getDuracaoPrevista() {
        return Duration.between(dataInicio, dataFimPrevista);
    }

    public Duration getTempoRestante(LocalDateTime agora) {
        Objects.requireNonNull( agora, "A data atual é obrigatória.");

        if (terminou(agora)) {
            return Duration.ZERO;
        }

        return Duration.between(
            agora.isBefore(dataInicio) ? dataInicio : agora,
            dataFimPrevista
        );
    }

}
