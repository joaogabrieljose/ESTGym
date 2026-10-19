package menu;

import java.awt.Point;
import java.time.LocalDateTime;

import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

import pds.util.RelogioSimulado;

public class Arranque {

	public static void main(String[] args) {
		// Criar o relógio simulado e "acertá-lo" para a hora atual
		RelogioSimulado relogio = RelogioSimulado.getRelogioSimulado();
		relogio.setTempoAtual(LocalDateTime.now());

		// TODO criar os serviços

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
