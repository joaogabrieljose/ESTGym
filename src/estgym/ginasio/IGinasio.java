package estgym.ginasio;

import java.util.Collection;

import estgym.equipamentos.Equipamento;
import estgym.modelo.Membro;
import estgym.modelo.Servico;

public interface IGinasio {

     String getNome();

    void adicionarMembro(Membro membro);

    Membro procurarMembro(int id);

    Collection<Membro> listarMembros();

    void adicionarEquipamento(Equipamento equipamento);

    Equipamento procurarEquipamento(int id);

    Collection<Equipamento> listarEquipamentos();

    void adicionarServico(Servico servico);

    Servico procurarServico(int id);

    Collection<Servico> listarServicos();
    
}
