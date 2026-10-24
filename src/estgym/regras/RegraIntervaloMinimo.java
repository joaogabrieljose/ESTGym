package estgym.regras;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Objects;

import estgym.modelo.Membro;
import estgym.modelo.RegistoUtilizacao;
import estgym.modelo.Servico;

public class RegraIntervaloMinimo implements RegraAcesso {
    
    private final Duration intervaloMinimo;

    public RegraIntervaloMinimo(Duration intervaloMinimo) {

        Objects.requireNonNull(intervaloMinimo,"O intervalo mínimo é obrigatório.");

        if (intervaloMinimo.isNegative()|| intervaloMinimo.isZero()) {
            throw new IllegalArgumentException( "O intervalo mínimo deve ser positivo.");
        }
        this.intervaloMinimo = intervaloMinimo;
    }

    @Override
    public boolean permite(Membro membro, Servico servico, LocalDateTime agora) {

        Objects.requireNonNull(membro);
        Objects.requireNonNull(servico);
        Objects.requireNonNull(agora);

        return membro.getRegistos().stream()
            .filter(registo ->
                registo.getServico().getId() == servico.getId()
            )
            .map(RegistoUtilizacao::getDataHoraInicio)
            .filter(inicio -> !inicio.isAfter(agora))
            .max(Comparator.naturalOrder())
            .map(ultimoInicio ->
                !agora.isBefore(
                    ultimoInicio.plus(intervaloMinimo)
                )
            )
            .orElse(true);
    }

    @Override
    public String getDescricao() {
        return "Intervalo mínimo de " + intervaloMinimo.toMinutes() + " minutos entre utilizações.";
    }
}
