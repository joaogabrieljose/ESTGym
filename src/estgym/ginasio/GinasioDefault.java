package estgym.ginasio;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import estgym.equipamentos.Equipamento;
import estgym.modelo.Membro;
import estgym.modelo.Servico;

public class GinasioDefault implements IGinasio {
    
    private String nome; 
    private final Map<Integer, Membro> membros;
    private final Map<Integer, Equipamento> equipamentos;
    private final Map<Integer, Servico> servicos;



    public GinasioDefault(String nome) {

        this.nome = Objects.requireNonNull(nome);
        this.membros = new LinkedHashMap<>();
        this.equipamentos = new LinkedHashMap<>();
        this.servicos = new LinkedHashMap<>();
    
    }

    @Override
    public void adicionarMembro(Membro membro) {

        Objects.requireNonNull(membro);
        if (membros.containsKey(membro.getId())) {
            throw new IllegalArgumentException(" já existe membro com esse ID. ");
        }
        membros.put(membro.getId(), membro);
    }


    @Override
    public Membro procurarMembro(int id) {
        return membros.get(id);
    }


    @Override
    public Collection<Membro> listarMembros() {
        return Collections.unmodifiableCollection(
            membros.values());
    }



    @Override
    public void adicionarEquipamento(Equipamento equipamento) {

       Objects.requireNonNull(equipamento);

       if (equipamentos.containsKey(equipamento.getId())) {
            throw new IllegalArgumentException(" já existe equipamento com esse ID. ");
       }

       equipamentos.put(equipamento.getId(), equipamento);
    }

     @Override
    public Equipamento procurarEquipamento(int id) {
        return equipamentos.getId(id);
    }

     @Override
    public Collection<Equipamento> listarEquipamentos() {
        return Collections.synchronizedCollection(
            equipamentos.values());
    }



    @Override
    public void adicionarServico(Servico servico) {
        Objects.requireNonNull(servico);

        if (servicos.containsKey(servico.getId())) {
            throw new IllegalArgumentException("já existe serviço com esse ID.");
        }
        servicos.put(servico.getId(), servico);
    }

      @Override
    public Servico procurarServico(int id) {
        return servicos.get(id);
    }

    @Override
    public Collection<Servico> listarServicos() {

        return Collections.unmodifiableCollection(
            servicos.values());
    }

    @Override
    public String getNome() {
        return nome;
    }

}
