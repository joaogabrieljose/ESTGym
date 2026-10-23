package estgym.regras;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import estgym.modelo.Membro;
import estgym.modelo.Servico;


public class ContratoServico {

   private final Servico servico;
    private final String descricao;

    private final List<RegraAcesso> regras;
    private final Duration duracaoPermitida;
    private final Duration tempoExtra;

    public ContratoServico(Servico servico, String descricao, List<RegraAcesso> regras,Duration duracaoPermitida,Duration tempoExtra) {

        this.servico = Objects.requireNonNull( servico,"O serviço é obrigatório.");
        this.descricao = descricao == null? "": descricao.trim();

        if (duracaoPermitida != null && (duracaoPermitida.isNegative() || duracaoPermitida.isZero())) {
            throw new IllegalArgumentException( "A duração permitida deve ser positiva.");
        }

        if (tempoExtra != null && tempoExtra.isNegative()) {
            throw new IllegalArgumentException("O tempo extra não pode ser negativo.");
        }

        this.regras = new ArrayList<>(Objects.requireNonNull( regras,"A lista de regras é obrigatória."));

        if (this.regras.stream().anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("A lista não pode conter regras nulas.");
        }

        this.duracaoPermitida = duracaoPermitida;
        this.tempoExtra = tempoExtra == null ? Duration.ZERO : tempoExtra;
    }

    public Servico getServico() {
        return servico;
    }

    public String getDescricao() {
        return descricao;
    }

    public List<RegraAcesso> getRegras() {
        return List.copyOf(regras);
    }

    public Duration getDuracaoPermitida() {
        return duracaoPermitida;
    }

    public Duration getTempoExtra() {
        return tempoExtra;
    }

    public boolean permiteUtilizacao( Membro membro,LocalDateTime agora) {
        Objects.requireNonNull( membro,"O membro é obrigatório.");
        Objects.requireNonNull(agora, "A data é obrigatória.");

        return regras.stream().allMatch(regra -> regra.permite(membro, servico, agora));
    }
    
}
