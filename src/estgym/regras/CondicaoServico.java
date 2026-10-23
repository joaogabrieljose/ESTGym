package estgym.regras;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import estgym.modelo.Servico;

public class CondicaoServico {
    
    private final Servico servico;
    private final String descricao;
    private final Duration duracaoPermitida;

    private final List<RegraAcesso> regras;

    public CondicaoServico(Servico servico,String descricao,Duration duracaoPermitida) {

        this.servico = Objects.requireNonNull(
            servico,
            "O serviço é obrigatório."
        );

        this.descricao = descricao == null
            ? ""
            : descricao.trim();

        if (duracaoPermitida != null
                && (duracaoPermitida.isNegative()
                    || duracaoPermitida.isZero())) {
            throw new IllegalArgumentException(
                "A duração deve ser positiva."
            );
        }

        this.duracaoPermitida = duracaoPermitida;
        this.regras = new ArrayList<>();
    }

    public Servico getServico() {
        return servico;
    }

    public String getDescricao() {
        return descricao;
    }

    public Duration getDuracaoPermitida() {
        return duracaoPermitida;
    }

    public List<RegraAcesso> getRegras() {
        return List.copyOf(regras);
    }

    public void adicionarRegra(RegraAcesso regra) {
        regras.add(Objects.requireNonNull(regra));
    }
}
