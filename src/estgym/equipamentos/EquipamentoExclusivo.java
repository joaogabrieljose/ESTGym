package estgym.equipamentos;

import java.time.Duration;
import java.time.LocalDateTime;

import estgym.modelo.Servico;

public class EquipamentoExclusivo extends Equipamento {

    public EquipamentoExclusivo(int id,String nome,Servico servico,boolean exigeEntrada,Duration duracaoFixa) {
        super(id,nome,servico,exigeEntrada,duracaoFixa);
    }

    @Override
    public boolean estaDisponivel(LocalDateTime agora) {
        return contarUtilizacoesAtivas(agora) == 0;
    }
    
}
