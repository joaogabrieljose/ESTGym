package estgym.regras;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Objects;

import estgym.modelo.Membro;
import estgym.modelo.RegistoUtilizacao;
import estgym.modelo.Servico;

public class RegraPeriodicidade implements RegraAcesso{
    
     private final int meses;

    public RegraPeriodicidade(int meses) {

        if (meses <= 0) {throw new IllegalArgumentException("A periodicidade deve ser positiva." );
        }
        this.meses = meses;
    }

    @Override
    public boolean permite(Membro membro,Servico servico,LocalDateTime agora) {

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
            .map(ultimaUtilizacao ->
                !agora.isBefore(
                    ultimaUtilizacao.plusMonths(meses)
                )
            )
            .orElse(true);
    }

    @Override
    public String getDescricao() {
        return "Uma utilização a cada " + meses + " mês(es).";
    }
}
