package estgym.modelo;

import java.time.LocalDateTime;

import estgym.equipamentos.Equipamento;

public class RegistoUtilizacao {

    private final Servico servico;
    private final Equipamento equipamento;
    private final LocalDateTime dataHoraInicio;
    private final LocalDateTime dataHoraFim;

    
    public RegistoUtilizacao(Servico servico, Equipamento equipamento, LocalDateTime dataHoraInicio,
            LocalDateTime dataHoraFim) {
        this.servico = servico;
        this.equipamento = equipamento;
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFim = dataHoraFim;
    }


    public Servico getServico() {
        return servico;
    }


    public Equipamento getEquipamento() {
        return equipamento;
    }


    public LocalDateTime getDataHoraInicio() {
        return dataHoraInicio;
    }


    public LocalDateTime getDataHoraFim() {
        return dataHoraFim;
    } 

    
}
