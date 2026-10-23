package estgym.modelo;

import java.time.Duration;
import java.util.List;

import estgym.regras.*;


public class ContratoServico {

    private final Servico servico;
    private final String descricao; 

    private final list<RegraAcesso> regras; 
    private final Duration duracaoPermitida; 
    private final Duration tempoExtra;

    public ContratoServico(Servico servico, String descricao, list<RegraAcesso> regras, Duration duracaoPermitida,
            Duration tempoExtra) {
        this.servico = servico;
        this.descricao = descricao;
        this.regras = regras;
        this.duracaoPermitida = duracaoPermitida;
        this.tempoExtra = tempoExtra;
    } 

    
}
