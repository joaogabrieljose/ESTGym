package estgym.modelo;

public class Servico {

    private final int id;
    private final String nome;
    private final String descricao; 
    private final TipoService tipo;

    public Servico(int id, String nome, String descricao, TipoService tipo) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.tipo = tipo;
    }
  
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public TipoService getTipo() {
        return tipo;
    }

}
