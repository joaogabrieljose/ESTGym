
package estgym.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import estgym.regras.ContratoServico;
import estgym.regras.Plano;

public class Membro {

    private final int id;
    private final String nome;

    private boolean dentroGinasio;
    private Plano plano;

    private final List<ContratoServico> contratos;
    private final List<RegistoUtilizacao> registos;

    private UtilizacaoAtiva utilizacaoAtiva;

    public Membro(int id, String nome) {
        this(id, nome, null);
    }
    public Membro(int id, String nome, Plano plano) {
        if (id <= 0) {throw new IllegalArgumentException("O ID do membro deve ser positivo."); }
        if (nome == null || nome.isBlank()) {throw new IllegalArgumentException("O nome do membro é obrigatório.");
        }

        this.id = id;
        this.nome = nome.trim();
        this.plano = plano;

        this.dentroGinasio = false;
        this.contratos = new ArrayList<>();
        this.registos = new ArrayList<>();
        this.utilizacaoAtiva = null;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public boolean isDentroGinasio() {
        return dentroGinasio;
    }

    public Plano getPlano() {
        return plano;
    }

    public List<ContratoServico> getContratos() {
        return List.copyOf(contratos);
    }

    public List<RegistoUtilizacao> getRegistos() {
        return List.copyOf(registos);
    }

    public UtilizacaoAtiva getUtilizacaoAtiva() {
        return utilizacaoAtiva;
    }

    public void associarPlano(Plano plano) {
        Objects.requireNonNull(plano,"O plano não pode ser nulo.");

        if (this.plano != null) {
            throw new IllegalStateException("O membro já possui um plano associado.");
        }

        this.plano = plano;
    }

    public void adicionarContrato(ContratoServico contrato) {

        Objects.requireNonNull(contrato,"O contrato não pode ser nulo.");
        boolean existe = contratos.stream().anyMatch(c -> c.getServico().getId()== contrato.getServico().getId());

        if (existe) {
            throw new IllegalArgumentException( "O membro já possui um contrato para este serviço.");
        }
        contratos.add(contrato);
    }

    public void adicionarRegisto(RegistoUtilizacao registo) {

        Objects.requireNonNull(registo,"O registo não pode ser nulo.");
        registos.add(registo);
    }

    public void entrarGinasio() {

        if (dentroGinasio) {
            throw new IllegalStateException( "O membro já está dentro do ginásio.");
        }
        dentroGinasio = true;
    }

    public void sairGinasio() {

        if (!dentroGinasio) {
            throw new IllegalStateException( "O membro já está fora do ginásio.");
        }
        dentroGinasio = false;
    }

    public boolean estaOcupado(LocalDateTime agora) {

        Objects.requireNonNull(agora);

        return utilizacaoAtiva != null && utilizacaoAtiva.estaEmCurso(agora);
    }

    public void iniciarUtilizacao(UtilizacaoAtiva utilizacao, LocalDateTime agora) {

        Objects.requireNonNull(utilizacao);
        Objects.requireNonNull(agora);

        if (!utilizacao.estaEmCurso(agora)) {
            throw new IllegalArgumentException( "A utilização não está em curso." );
        }

        if (estaOcupado(agora)) {
            throw new IllegalStateException("O membro já está a utilizar um serviço." );
        }
        this.utilizacaoAtiva = utilizacao;
    }

    public void terminarUtilizacao(LocalDateTime agora) {

        Objects.requireNonNull(agora);

        if (utilizacaoAtiva != null && utilizacaoAtiva.terminou(agora)) {
            utilizacaoAtiva = null;
        }
    }
}
