package estgym.regras;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Objects;

import estgym.modelo.Membro;
import estgym.modelo.Servico;

public class RegraLimiteSemanal implements RegraAcesso {
    
    private final int limiteMaximo;

    public RegraLimiteSemanal(int limiteMaximo) {

        if (limiteMaximo <= 0) {
            throw new IllegalArgumentException("O limite semanal deve ser superior a zero.");
        }
        this.limiteMaximo = limiteMaximo;
    }

    @Override
    public boolean permite(Membro membro,Servico servico, LocalDateTime agora) {

        Objects.requireNonNull( membro, "O membro é obrigatório.");
        Objects.requireNonNull(servico, "O serviço é obrigatório.");
        Objects.requireNonNull(agora, "A data é obrigatória.");

        LocalDate dataAtual = agora.toLocalDate();

        // Determinar a segunda-feira da semana atual
        LocalDate inicioSemana = dataAtual.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

        // Determinar o domingo da mesma semana
        LocalDate fimSemana = inicioSemana.plusDays(6);

        // Contar as utilizações do serviço na semana
        long totalUtilizacoes = membro.getRegistos()
            .stream().filter(registo ->registo.getServico().getId() == servico.getId()
            )
            .filter(registo -> {

                LocalDate dataUtilizacao =
                    registo.getDataHoraInicio().toLocalDate();

                return !dataUtilizacao.isBefore(inicioSemana) && !dataUtilizacao.isAfter(fimSemana) && !registo.getDataHoraInicio().isAfter(agora);
            })
            .count();

        return totalUtilizacoes < limiteMaximo;
    }

    @Override
    public String getDescricao() {
        return "Máximo de " + limiteMaximo + " utilização(ões) por semana.";
    }

    public int getLimiteMaximo() {
        return limiteMaximo;
    }
}
