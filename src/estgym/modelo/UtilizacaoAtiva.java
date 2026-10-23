package estgym.modelo;

import java.time.LocalDateTime;

import estgym.equipamentos.Equipamento;

public class UtilizacaoAtiva {
    
    private final Servico servico;
    private final Equipamento equipamento;
    private final LocalDateTime dataInicio;
    private final LocalDateTime dataFimPrevista;

    public UtilizacaoAtiva(Servico servico,Equipamento equipamento,LocalDateTime dataInicio, LocalDateTime dataFimPrevista) {
        this.servico = servico;
        this.equipamento = equipamento;
        this.dataInicio = dataInicio;
        this.dataFimPrevista = dataFimPrevista;
    }

    public boolean estaEmCurso(LocalDateTime agora) {
        return agora.isBefore(dataFimPrevista);
    }

    public Servico getServico() {
        return servico;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

}
