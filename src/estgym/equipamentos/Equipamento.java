package estgym.equipamentos;

import java.security.Provider.Service;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import estgym.modelo.UtilizacaoAtiva;

public abstract class Equipamento {

    private final int id;
    private final String nome;
    private final Service servico;
    private final boolean exigeEntrada;

    private final Duration duracaoFixa; 

    private final List<UtilizacaoAtiva> utilizacoesAtivas;
    
    public Equipamento(int id, String nome, Service servico, boolean exigeEntrada, Duration duracaoFixa) {
        this.id = id;
        this.nome = nome;
        this.servico = servico;
        this.exigeEntrada = exigeEntrada;
        this.duracaoFixa = duracaoFixa;
        
        this.utilizacoesAtivas = new ArrayList<>();
    }


    public int getId() {
        return id;
    }


    public String getNome() {
        return nome;
    }


    public Service getServico() {
        return servico;
    }


    public boolean isExigeEntrada() {
        return exigeEntrada;
    } 

  
    public Duration getDuracaoFixa() {
        return duracaoFixa;
    }

    public abstract boolean estaDisponivel();

}
