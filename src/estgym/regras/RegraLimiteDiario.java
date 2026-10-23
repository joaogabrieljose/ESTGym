package estgym.regras;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import estgym.modelo.Membro;
import estgym.modelo.Servico;

public class RegraLimiteDiario implements RegraAcesso {

    private final int limiteMaximo;

    public RegraLimiteDiario(int limiteMaximo) {
        if (limiteMaximo <= 0) {
            throw new IllegalArgumentException(
                "O limite diário deve ser superior a zero."
            );
        }

        this.limiteMaximo = limiteMaximo;
    }

    @Override
    public boolean permite(Membro membro,Servico servico,LocalDateTime agora) {

        Objects.requireNonNull(membro, "O membro é obrigatório.");
        Objects.requireNonNull(servico, "O serviço é obrigatório.");
        Objects.requireNonNull(agora, "A data é obrigatória.");

        LocalDate dataAtual = agora.toLocalDate();

        long totalUtilizacoes = membro.getRegistos().stream().filter(registo ->
                registo.getServico().getId() == servico.getId()
            )
            .filter(registo ->registo.getDataHoraInicio()
                    .toLocalDate().equals(dataAtual)).count();

        return totalUtilizacoes < limiteMaximo;
    }

    @Override
    public String getDescricao() {
        return "Máximo de " + limiteMaximo + " utilizaçães por dia.";
    }

    public int getLimiteMaximo() {
        return limiteMaximo;
    }
    
}
