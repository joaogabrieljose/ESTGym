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
import estgym.modelo.Membro;
import estgym.modelo.Servico;
import estgym.modelo.TipoService;
import estgym.regras.CondicaoServico;
import estgym.regras.ContratoServico;
import estgym.regras.Plano;
import estgym.regras.RegraIntervaloMinimo;
import estgym.regras.RegraLimiteDiario;
import estgym.regras.RegraLimiteSemanal;
import estgym.regras.RegraPeriodicidade;
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
		Equipamento aulaIoga = new EquipamentoAula(117, "Aula Ioga",aulaNivel2, Duration.ofMinutes(60), 15);

				
		List<Equipamento> equipamentos = List.of(
			portaEntrada,dispensador,aulaStep,portaSaida,duche1,duche2,duche3,ptManel,
			ptRita,nutricionistaCarlos,nutricionistaMarta,avaliacaoFisica,cadeira1, cadeira2,
			portaPiscina,aulaCycling,aulaBodyCombat,aulaIoga
		);

		for (Equipamento equipamento : equipamentos) {
			ginasio.adicionarEquipamento(equipamento);
		}
	
		// TODO FEITO  criar oe membros

		Membro vitor = new Membro(1, "Vítor Virtual");
		Membro iris = new Membro(2, "Iris Irreal");
		Membro ines = new Membro(3, "Inês Inexistente");
		Membro filipe = new Membro(4, "Filipe Fictício");
		Membro diogo = new Membro(5, "Diogo Digital");
		Membro beatriz = new Membro(6, "Beatriz Bytes");

		for (Membro membro : List.of(
				vitor, iris, ines, filipe, diogo, beatriz)) {

			ginasio.adicionarMembro(membro);
		}

		// TODO FEITO criar os planos para cada membro

		Plano planoVitor = new Plano(1001, "Plano Vítor", "Condições do Vítor Virtual");
		adicionarCondicao(planoVitor, entrada,"1 hora por dia, 3 dias por semana",Duration.ofHours(1), null, null);
		adicionarCondicao(planoVitor, saida,"Sempre", null, null, null);
		adicionarCondicao(planoVitor, aulaNivel1,"1 aula por semana", null, null, 1);
		adicionarCondicao(planoVitor, duche,"1 duche por dia, 3 + 2 minutos",Duration.ofMinutes(3), 1, null);
		adicionarCondicao(planoVitor, bebida,"1 abastecimento por dia", null, 1, null);
		adicionarCondicao(planoVitor, nutricao,"1 consulta de 6 em 6 meses",null, null, null);
		adicionarCondicao(planoVitor, avaliacao,"1 consulta de 3 em 3 meses",null, null, null);

		Plano planoIris = new Plano(1002, "Plano Iris", "Condições da Iris Irreal");
		adicionarCondicao(planoIris, entrada,"3 vezes por semana", null, null, 3);
		adicionarCondicao(planoIris, saida,"Sempre", null, null, null);
		adicionarCondicao(planoIris, piscina,"45 minutos por dia, 2 dias por semana",Duration.ofMinutes(45), null, null);
		adicionarCondicao(planoIris, duche,"1 duche por dia, 5 + 2 minutos",Duration.ofMinutes(5), 1, null);
		adicionarCondicao(planoIris, bebida,"2 abastecimentos por dia, intervalo de 30 minutos",null, 2, null);
		adicionarCondicao(planoIris, personalTrainer,"45 minutos por dia, 3 dias por semana",Duration.ofMinutes(45), null, null);
		adicionarCondicao(planoIris, nutricao,"1 consulta de 6 em 6 meses",null, null, null);
		adicionarCondicao(planoIris, avaliacao,"1 consulta de 3 em 3 meses",null, null, null);

		Plano planoInes = new Plano(1003, "Plano Inês", "Condições da Inês Inexistente");
		adicionarCondicao(planoInes, entrada,"Sempre", null, null, null);
		adicionarCondicao(planoInes, saida,"Sempre", null, null, null);
		adicionarCondicao(planoInes, aulaNivel1,"1 aula por semana", null, null, 1);
		adicionarCondicao(planoInes, duche,"1 duche por dia, 5 + 2 minutos",Duration.ofMinutes(5), 1, null);
		adicionarCondicao(planoInes, bebida,"2 abastecimentos por dia, intervalo de 30 minutos",null, 2, null);
		adicionarCondicao(planoInes, nutricao,"1 consulta por mês", null, null, null);
		adicionarCondicao(planoInes, avaliacao,"1 consulta de 3 em 3 meses",null, null, null);

		Plano planoFilipe = new Plano(1004, "Plano Filipe", "Condições do Filipe Fictício");
		adicionarCondicao(planoFilipe, entrada,"3 vezes por semana", null, null, 3);
		adicionarCondicao(planoFilipe, saida,"Sempre", null, null, null);
		adicionarCondicao(planoFilipe, aulaNivel1,"2 aulas por semana", null, null, 2);
		adicionarCondicao(planoFilipe, aulaNivel2,"1 aula por semana", null, null, 1);
		adicionarCondicao(planoFilipe, piscina,"45 minutos por dia, 2 dias por semana",Duration.ofMinutes(45), null, null);
		adicionarCondicao(planoFilipe, duche,"1 duche por dia, 5 + 2 minutos",Duration.ofMinutes(5), 1, null);
		adicionarCondicao(planoFilipe, bebida,	"2 abastecimentos por dia, intervalo de 30 minutos",null, 2, null);
		adicionarCondicao(planoFilipe, personalTrainer,"45 minutos por dia, 3 dias por semana",Duration.ofMinutes(45), null, null);
		adicionarCondicao(planoFilipe, nutricao,"1 consulta de 6 em 6 meses",null, null, null);
		adicionarCondicao(planoFilipe, avaliacao,"1 consulta de 3 em 3 meses",null, null, null);
		adicionarCondicao(planoFilipe, massagem,"1 sessão por dia, 3 por semana",null, 1, 3);

		Plano planoDiogo = new Plano(1005, "Plano Diogo", "Condições do Diogo Digital");
		adicionarCondicao(planoDiogo, entrada,"Sempre", null, null, null);
		adicionarCondicao(planoDiogo, saida,"Sempre", null, null, null);
		adicionarCondicao(planoDiogo, aulaNivel1,"Ilimitado", null, null, null);
		adicionarCondicao(planoDiogo, aulaNivel2,"3 aulas por semana", null, null, 3);
		adicionarCondicao(planoDiogo, piscina,"1 hora por dia, 5 dias por semana",Duration.ofHours(1), null, null);
		adicionarCondicao(planoDiogo, duche,"2 duches por dia, 6 + 3 minutos",Duration.ofMinutes(6), 2, null);
		adicionarCondicao(planoDiogo, bebida,"4 abastecimentos por dia, intervalo de 20 minutos",null, 4, null);
		adicionarCondicao(planoDiogo, nutricao,"1 consulta de 3 em 3 meses",null, null, null);
		adicionarCondicao(planoDiogo, avaliacao,"1 consulta de 3 em 3 meses",null, null, null);
		adicionarCondicao(planoDiogo, massagem,"1 sessão por dia, 7 por semana",null, 1, 7);

		Plano planoBeatriz = new Plano(1006, "Plano Beatriz", "Condições da Beatriz Bytes");
		adicionarCondicao(planoBeatriz, entrada,"Sempre", null, null, null);
		adicionarCondicao(planoBeatriz, saida,"Sempre", null, null, null);
		adicionarCondicao(planoBeatriz, aulaNivel1,"Ilimitado", null, null, null);
		adicionarCondicao(planoBeatriz, aulaNivel2,"Ilimitado", null, null, null);
		adicionarCondicao(planoBeatriz, duche,"2 duches por dia, 6 + 3 minutos",Duration.ofMinutes(6), 2, null);
		adicionarCondicao(planoBeatriz, bebida,"4 abastecimentos por dia, intervalo de 20 minutos",null, 4, null);
		adicionarCondicao(planoBeatriz, personalTrainer,"1 hora por dia, 5 dias por semana",Duration.ofHours(1), null, null);
		adicionarCondicao(planoBeatriz, nutricao,"1 consulta por mês", null, null, null);
		adicionarCondicao(planoBeatriz, avaliacao,"1 consulta por mês", null, null, null);
		adicionarCondicao(planoBeatriz, massagem,"1 sessão por dia, 3 por semana",null, 1, 3);

		//* REGRAS DE INTERVALO MÍNIMO ENTRE BEBIDAS VITAMINADAS

		planoIris.procurarCondicao(bebida.getId()).adicionarRegra(new RegraIntervaloMinimo(Duration.ofMinutes(30)));
		planoInes.procurarCondicao(bebida.getId()).adicionarRegra(new RegraIntervaloMinimo(Duration.ofMinutes(30)));
		planoFilipe.procurarCondicao(bebida.getId()).adicionarRegra(new RegraIntervaloMinimo(Duration.ofMinutes(30)));
		planoDiogo.procurarCondicao(bebida.getId()).adicionarRegra(new RegraIntervaloMinimo(Duration.ofMinutes(20)));
		planoBeatriz.procurarCondicao(bebida.getId()).adicionarRegra(new RegraIntervaloMinimo(Duration.ofMinutes(20)));	

		//* REGRAS DE PERIODICIDADE, 1 mes, 3 meses e  6 meses 
		planoVitor.procurarCondicao(nutricao.getId()).adicionarRegra(new RegraPeriodicidade(6));
		planoVitor.procurarCondicao(avaliacao.getId()).adicionarRegra(new RegraPeriodicidade(3));
		planoIris.procurarCondicao(nutricao.getId()).adicionarRegra(new RegraPeriodicidade(6));
		planoIris.procurarCondicao(avaliacao.getId()).adicionarRegra(new RegraPeriodicidade(3));
		planoInes.procurarCondicao(nutricao.getId()).adicionarRegra(new RegraPeriodicidade(1));
		planoInes.procurarCondicao(avaliacao.getId()).adicionarRegra(new RegraPeriodicidade(3));
		planoFilipe.procurarCondicao(nutricao.getId()).adicionarRegra(new RegraPeriodicidade(6));
		planoFilipe.procurarCondicao(avaliacao.getId()).adicionarRegra(new RegraPeriodicidade(3));
		planoDiogo.procurarCondicao(nutricao.getId()).adicionarRegra(new RegraPeriodicidade(3));
		planoDiogo.procurarCondicao(avaliacao.getId()).adicionarRegra(new RegraPeriodicidade(3));
		planoBeatriz.procurarCondicao(nutricao.getId()).adicionarRegra(new RegraPeriodicidade(1));
		planoBeatriz.procurarCondicao(avaliacao.getId()).adicionarRegra(new RegraPeriodicidade(1));	


		// 3. ASSOCIAR OS PLANOS AOS MEMBROS 

		vitor.associarPlano(planoVitor);
		iris.associarPlano(planoIris);
		ines.associarPlano(planoInes);
		filipe.associarPlano(planoFilipe);
		diogo.associarPlano(planoDiogo);
		beatriz.associarPlano(planoBeatriz);

		// contratos associado aos membros 
		for (Membro membro : ginasio.listarMembros()) {
			criarContratosDoPlano(membro);
		}

		
		

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



	private static void adicionarCondicao( Plano plano,Servico servico,String descricao,Duration duracao,Integer limiteDiario,Integer limiteSemanal) {

		CondicaoServico condicao = new CondicaoServico(servico, descricao, duracao);

		if (limiteDiario != null) {
			condicao.adicionarRegra(new RegraLimiteDiario(limiteDiario));
		}

		if (limiteSemanal != null) {
			condicao.adicionarRegra(new RegraLimiteSemanal(limiteSemanal));
		}
		plano.adicionarCondicao(condicao);
	}



	// este metodo percorre todos as condições do plano e cria contrato por cada serviço

	private static void criarContratosDoPlano(Membro membro) {

		Plano plano = membro.getPlano();

		if (plano == null) {throw new IllegalStateException("O membro não possui um plano associado.");
		}

		for (CondicaoServico condicao : plano.getCondicoes()) {

			Duration tempoExtra = Duration.ZERO;

			// Tempo extra dos duches
			if (condicao.getServico().getTipo()== TipoService.DUCHE) {

				if (membro.getId() == 1) {
					tempoExtra = Duration.ofMinutes(2);
				} else if (membro.getId() <= 4) {
					tempoExtra = Duration.ofMinutes(2);
				} else {
					tempoExtra = Duration.ofMinutes(3);
				}
			}

			ContratoServico contrato = new ContratoServico(
				condicao.getServico(),condicao.getDescricao(),condicao.getRegras(),condicao.getDuracaoPermitida(),tempoExtra);
			membro.adicionarContrato(contrato);
		}
	}

}





