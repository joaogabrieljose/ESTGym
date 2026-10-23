package estgym.regras;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Plano {

    private final int id;
    private final String nome;
    private final String descricao;

    private final List<CondicaoServico> condicoes;

    public Plano(int id, String nome, String descricao) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                "O ID do plano deve ser positivo."
            );
        }

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException(
                "O nome do plano é obrigatório."
            );
        }

        this.id = id;
        this.nome = nome.trim();
        this.descricao = descricao == null
            ? ""
            : descricao.trim();

        this.condicoes = new ArrayList<>();
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

    public List<CondicaoServico> getCondicoes() {
        return List.copyOf(condicoes);
    }

    public void adicionarCondicao(CondicaoServico condicao) {
        Objects.requireNonNull(
            condicao,
            "A condição não pode ser nula."
        );

        boolean existe = condicoes.stream()
            .anyMatch(c ->
                c.getServico().getId()
                    == condicao.getServico().getId()
            );

        if (existe) {
            throw new IllegalArgumentException(
                "O plano já contém uma condição para este serviço."
            );
        }

        condicoes.add(condicao);
    }

    public CondicaoServico procurarCondicao(int servicoId) {
        return condicoes.stream()
            .filter(c -> c.getServico().getId() == servicoId)
            .findFirst()
            .orElse(null);
    }

    public boolean incluiServico(int servicoId) {
        return procurarCondicao(servicoId) != null;
    }



}
