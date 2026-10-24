package estgym.regras;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Objects;

import estgym.modelo.Membro;
import estgym.modelo.Servico;

public class RegraDiasPorSemana implements RegraAcesso {

    private final int maximoDias;

    public RegraDiasPorSemana(int maximoDias) {

        if (maximoDias < 1 || maximoDias > 7) {
            throw new IllegalArgumentException( "O número de dias deve estar entre 1 e 7.");
        }

        this.maximoDias = maximoDias;
    }

    @Override
    public boolean permite( Membro membro, Servico servico, LocalDateTime agora) {

        Objects.requireNonNull(membro);
        Objects.requireNonNull(servico);
        Objects.requireNonNull(agora);
        LocalDate hoje = agora.toLocalDate();
        LocalDate inicioSemana = hoje.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

        var diasUtilizados = membro.getRegistos().stream()
            .filter(registo ->
                registo.getServico().getId() == servico.getId()
            )
            .map(registo -> registo.getDataHoraInicio())
            .filter(inicio -> !inicio.isAfter(agora))
            .map(LocalDateTime::toLocalDate)
            .filter(data -> !data.isBefore(inicioSemana))
            .distinct()
            .toList();

        // Se já utilizou o serviço hoje,
        // não está a consumir um novo dia semanal.
        if (diasUtilizados.contains(hoje)) {
            return true;
        }

        return diasUtilizados.size() < maximoDias;
    }

    @Override
    public String getDescricao() {
        return "Máximo de " + maximoDias
            + " dias distintos por semana.";
    }
    
}
