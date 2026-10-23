package menu;

import java.awt.Point;
import java.time.LocalDateTime;

import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

import estgym.ginasio.GinasioDefault;
import estgym.ginasio.IGinasio;
import estgym.modelo.Servico;
import estgym.modelo.TipoService;
import pds.util.RelogioSimulado;

public class Arranque {

	public static void main(String[] args) {
		// Criar o relógio simulado e "acertá-lo" para a hora atual
		RelogioSimulado relogio = RelogioSimulado.getRelogioSimulado();
		relogio.setTempoAtual(LocalDateTime.now());

		IGinasio ginasio = new GinasioDefault("ESTGym");

		// TODO  FEITO criar os serviços
		Servico entrada = new Servico(1, "Entrada", "Acesso ao ginasio", TipoService.ENTRADA);
		Servico saida = new Servico(2,"Saída","Saída do ginásio",TipoService.SAIDA);
		Servico duche = new Servico(3,"Duche","Utilização dos duches",TipoService.DUCHE);
		Servico bebida = new Servico(4,"Bebida vitaminada","Dispensador de bebidas vitaminadas", TipoService.BEBIDA_VITAMINADA);
		Servico piscina = new Servico(5,"Piscina","Acesso à piscina",TipoService.PISCINA);
		Servico personalTrainer = new Servico(6,"Personal Trainer","Sessão de treino personalizado",TipoService.PERSONAL_TRAINER);
		Servico nutricao = new Servico(7,"Consulta de nutrição","Acompanhamento nutricional",TipoService.CONSULTA_NUTRICAO);
		Servico avaliacao = new Servico(8,"Avaliação física","Avaliação da condição física", TipoService.AVALIACAO_FISICA);
		Servico massagem = new Servico(9,"Cadeira de massagem","Utilização da cadeira de massagem",TipoService.CADEIRA_MASSAGEM);
		Servico aulaNivel1 = new Servico(10,"Aula de nível 1","Aulas de grupo de nível 1", TipoService.AULA_NIVEL_1);
		Servico aulaNivel2 = new Servico(11, "Aula de nível 2","Aulas de grupo de nível 2",TipoService.AULA_NIVEL_2);


		ginasio.adicionarServico(entrada);
		ginasio.adicionarServico(saida);
		ginasio.adicionarServico(duche);
		ginasio.adicionarServico(bebida);
		ginasio.adicionarServico(piscina);
		ginasio.adicionarServico(personalTrainer);
		ginasio.adicionarServico(nutricao);
		ginasio.adicionarServico(avaliacao);
		ginasio.adicionarServico(massagem);
		ginasio.adicionarServico(aulaNivel1);
		ginasio.adicionarServico(aulaNivel2);


		// System.out.println("===== TESTE DOS SERVIÇOS =====");

		// System.out.println(
		// 	"Ginásio: " + ginasio.getNome()
		// );

		// System.out.println(
		// 	"Total de serviços: "
		// 		+ ginasio.listarServicos().size()
		// );

		// for (Servico servico : ginasio.listarServicos()) {

		// 	System.out.println(
		// 		"ID: " + servico.getId()
		// 		+ " | Nome: " + servico.getNome()
		// 		+ " | Tipo: " + servico.getTipo()
		// 	);
		// }



		// TODO criar os equipamentos

		// TODO criar oe membros

		// TODO criar os planos para cada membro

		SwingUtilities.invokeLater(() -> {
			JanelaGinasio main = new JanelaGinasio();

			// TODO adicionar os equipamentos ao simulador (ids apenas exemplificativos)
			main.addEquipamento(10, getIcons("porta"));
			main.addEquipamento(11, getIcons("bebida"));
			main.addEquipamento(12, getIcons("duche"));
			main.addEquipamento(13, getIcons("pt"));
			main.addEquipamento(14, getIcons("nutricionista"));
			main.addEquipamento(15, getIcons("poltrona"));
			main.addEquipamento(16, getIcons("aula"));
			main.addEquipamento(17, getIcons("fisica"));
			main.addEquipamento(18, getIcons("porta_piscina"));
			
			// System.out.println("testa se funciona a nova version ");

			main.setVisible(true);
			RelogioDialog relog = new RelogioDialog(main, new Point(200, 20));
			relog.setVisible(true);

		});
	}

	/**
	 * Lê os ficheiros com os icons para um dado equipamento
	 * 
	 * @param nome nome da imagem representativa do equipamento
	 * @return os icones com a imagem livre e ocupado
	 */
	private static ImageIcon[] getIcons(String nome) {
		String pasta = "art/";
		ImageIcon icons[] = { new ImageIcon(pasta + nome + "_livre.png"),
				new ImageIcon(pasta + nome + "_ocupado.png") };
		return icons;
	}
}
