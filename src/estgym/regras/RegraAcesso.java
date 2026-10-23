package estgym.regras;

import java.time.LocalDate;
import java.time.LocalDateTime;

import estgym.modelo.Membro;
import estgym.modelo.Servico;


public interface RegraAcesso {
    boolean permite(
        Membro membro, 
        Servico  service, 
        LocalDateTime agora
    ); 

    String getDescricao(); 

}
