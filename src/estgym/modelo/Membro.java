package estgym.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Membro {

    private final int id;
    private String nome;
    private boolean dentroGinasio;

    private final List<ContratoServico> contratos;
    private final List<RegistoUtilizacao> registos;

    private UtilizacaoAtiva utilizacaoAtiva;

   
    public Membro(int id, String nome) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                "O ID do membro deve ser positivo."
            );
        }

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException(
                "O nome do membro é obrigatório."
            );
        }

        this.id = id;
        this.nome = nome.trim();
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

    public List<ContratoServico> getContratos() {
        return List.copyOf(contratos);
    }

    public List<RegistoUtilizacao> getRegistos() {
        return List.copyOf(registos);
    }

    public UtilizacaoAtiva getUtilizacaoAtiva() {
        return utilizacaoAtiva;
    }

      public void adicionarContrato(ContratoServico contrato) {
        Objects.requireNonNull(
            contrato,
            "O contrato não pode ser nulo."
        );

        if (contratos.contains(contrato)) {
            throw new IllegalArgumentException(
                "O contrato já está associado ao membro."
            );
        }

        contratos.add(contrato);
    }

    public void adicionarRegisto(RegistoUtilizacao registo) {
        Objects.requireNonNull(
            registo,
            "O registo não pode ser nulo."
        );

        registos.add(registo);
    }
   public void entrarGinasio() {
        if (dentroGinasio) {
            throw new IllegalStateException(
                "O membro já está dentro do ginásio."
            );
        }

        dentroGinasio = true;
    }

    public void sairGinasio() {
        if (!dentroGinasio) {
            throw new IllegalStateException(
                "O membro já está fora do ginásio."
            );
        }

        dentroGinasio = false;
    }

    public boolean estaOcupado(LocalDateTime agora) {
        Objects.requireNonNull(agora);

        return utilizacaoAtiva != null
            && utilizacaoAtiva.estaEmCurso(agora);
    }

    public void iniciarUtilizacao(
            UtilizacaoAtiva utilizacao,
            LocalDateTime agora) {

        Objects.requireNonNull(utilizacao);
        Objects.requireNonNull(agora);

        if (estaOcupado(agora)) {
            throw new IllegalStateException(
                "O membro já está a utilizar um serviço."
            );
        }

        this.utilizacaoAtiva = utilizacao;
    }

    public void terminarUtilizacao(LocalDateTime agora) {
        Objects.requireNonNull(agora);

        if (utilizacaoAtiva != null
                && !utilizacaoAtiva.estaEmCurso(agora)) {

            utilizacaoAtiva = null;
        }
    }


}
