package estgym.regras;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

import estgym.modelo.Membro;
import estgym.modelo.Servico;

public class RegraDuracaoDiaria implements RegraAcesso {

        private final Duration duracaoMaxima;

    public RegraDuracaoDiaria(Duration duracaoMaxima) {

        Objects.requireNonNull(duracaoMaxima);

        if (duracaoMaxima.isZero() || duracaoMaxima.isNegative()) {
            throw new IllegalArgumentException("A duração máxima deve ser positiva.");
        }

        this.duracaoMaxima = duracaoMaxima;
    }

    public Duration obterTempoRestante( Membro membro,Servico servico,LocalDateTime agora) {

        Objects.requireNonNull(membro);
        Objects.requireNonNull(servico);
        Objects.requireNonNull(agora);

        LocalDateTime inicioDia = agora.toLocalDate().atStartOfDay();
        Duration tempoUtilizado = Duration.ZERO;

        for (var registo : membro.getRegistos()) {

            if (registo.getServico().getId() != servico.getId()) {
                continue;
            }

            LocalDateTime inicio = registo.getDataHoraInicio();
            LocalDateTime fim = registo.getDataHoraFim();

            if (inicio == null || fim == null || !fim.isAfter(inicio)) {
                continue;
            }

            LocalDateTime inicioContabilizado =inicio.isBefore(inicioDia) ? inicioDia : inicio;
            LocalDateTime fimContabilizado =fim.isAfter(agora) ? agora : fim;

            if (fimContabilizado.isAfter(inicioContabilizado)) {
                tempoUtilizado = tempoUtilizado.plus(
                    Duration.between(inicioContabilizado, fimContabilizado ));
            }
        }

        Duration restante = duracaoMaxima.minus(tempoUtilizado);
        return restante.isNegative()? Duration.ZERO : restante;
    }

    @Override
    public boolean permite(
            Membro membro,
            Servico servico,
            LocalDateTime agora) {

        return !obterTempoRestante(
            membro, servico, agora
        ).isZero();
    }

    @Override
    public String getDescricao() {
        return "Duração máxima diária de "
            + duracaoMaxima.toMinutes() + " minutos.";
    }
}
