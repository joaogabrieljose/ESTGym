package menu;

import java.awt.Point;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

import estgym.equipamentos.Equipamento;
import estgym.equipamentos.EquipamentoAula;
import estgym.equipamentos.EquipamentoExclusivo;
import estgym.ginasio.GinasioDefault;
import estgym.ginasio.IGinasio;
import estgym.modelo.Servico;
import estgym.modelo.TipoService;
import estgym.modelo.UtilizacaoAtiva;
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



		// TODO FEITO  criar os equipamentos

		Equipamento portaEntrada = new EquipamentoExclusivo(100,"Porta de entrada",entrada,false,Duration.ofSeconds(3));
		Equipamento dispensador = new EquipamentoExclusivo(101,"Dispensador de bebidas",bebida,true,Duration.ofSeconds(20));
		Equipamento aulaStep = new EquipamentoAula(102,"Aula de Step",aulaNivel1,Duration.ofMinutes(30),30);
		Equipamento portaSaida = new EquipamentoExclusivo(103, "Porta de saída",saida, false, Duration.ofSeconds(3));
		Equipamento duche1 = new EquipamentoExclusivo(104, "Duche 1",duche, true, null);
		Equipamento duche2 = new EquipamentoExclusivo(105, "Duche 2",duche, true, null);
		Equipamento duche3 = new EquipamentoExclusivo(106, "Duche 3",duche, true, null);
		Equipamento ptManel = new EquipamentoExclusivo(107, "Personal Trainer - Manel Mandão",personalTrainer, true, null);
		Equipamento ptRita = new EquipamentoExclusivo(108, "Personal Trainer - Rita Rígida",personalTrainer, true, null);
		Equipamento nutricionistaCarlos = new EquipamentoExclusivo(109, "Nutricionista - Carlos Comilão",nutricao, 
		true, Duration.ofMinutes(30));
		Equipamento nutricionistaMarta = new EquipamentoExclusivo(110, "Nutricionista - Marta Magra",nutricao, true, Duration.ofMinutes(30));
		Equipamento avaliacaoFisica = new EquipamentoExclusivo(111, "Sala de avaliação física",avaliacao, true, Duration.ofMinutes(20));
		Equipamento cadeira1 = new EquipamentoExclusivo(112, "Cadeira de massagem 1",massagem, true, Duration.ofMinutes(10));
		Equipamento cadeira2 = new EquipamentoExclusivo(113, "Cadeira de massagem 2",massagem, true, Duration.ofMinutes(10));
		Equipamento portaPiscina = new EquipamentoExclusivo(114, "Porta da piscina",piscina, true, Duration.ofSeconds(3));
		Equipamento aulaCycling = new EquipamentoAula(115, "Aula Cycling",aulaNivel1, Duration.ofMinutes(50), 25);
		Equipamento aulaBodyCombat = new EquipamentoAula(116, "Aula Body Combat",aulaNivel2, Duration.ofMinutes(30), 20);
		Equipamento aulaIoga = new EquipamentoAula(117, "Aula Ioga",aulaNivel1, Duration.ofMinutes(60), 15);

				
		List<Equipamento> equipamentos = List.of(
			portaEntrada,dispensador,aulaStep,portaSaida,duche1,duche2,duche3,ptManel,
			ptRita,nutricionistaCarlos,nutricionistaMarta,avaliacaoFisica,cadeira1, cadeira2,
			portaPiscina,aulaCycling,aulaBodyCombat,aulaIoga
		);

		for (Equipamento equipamento : equipamentos) {
			ginasio.adicionarEquipamento(equipamento);
		}


				
		// System.out.println(
		// 	"===== TESTE DOS EQUIPAMENTOS ====="
		// );

		// LocalDateTime agora = RelogioSimulado.getTempoAtual();

		// for (Equipamento equipamento : ginasio.listarEquipamentos()) {

		// 	System.out.println(
		// 		"ID: " + equipamento.getId()
		// 		+ " | Nome: " + equipamento.getNome()
		// 		+ " | Serviço: "
		// 			+ equipamento.getServico().getNome()
		// 		+ " | Disponível: "
		// 			+ equipamento.estaDisponivel(agora)
		// 	);
		// }

		// System.out.println(
		// 	"Total de equipamentos: "
		// 	+ ginasio.listarEquipamentos().size()
		// );

		

	

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
