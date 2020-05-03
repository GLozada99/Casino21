package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.border.SoftBevelBorder;

import logic.Card;
import logic.Game;
import logic.Player;

import javax.swing.border.BevelBorder;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;

public class GameWindow extends JFrame {

	private JPanel contentPane;
	private Dimension panelSize = new Dimension(90, 137);
	private JLabel lblCard1;
	private JLabel lblCard2;
	private JLabel lblCard3;
	private JLabel lblCard4;
	public ArrayList<JLabel> playerLabels = new ArrayList<JLabel>();
	public ArrayList<JLabel> PCLabels = new ArrayList<JLabel>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameWindow frame = new GameWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public GameWindow() throws IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1900, 1040);
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanelBackground();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		((JPanelBackground) contentPane).setBackground("Images/Green2.jpg");
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel P1 = new JPanel();
		P1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		P1.setBounds(714, 782, 95, 145);
		contentPane.add(P1);
		P1.setLayout(new BorderLayout(0, 0));

		lblCard1 = new JLabel();
		P1.add(lblCard1, BorderLayout.NORTH);

		JPanel P2 = new JPanel();
		P2.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		P2.setBounds(835, 782, 95, 145);
		contentPane.add(P2);
		P2.setLayout(new BorderLayout(0, 0));

		lblCard2 = new JLabel();
		P2.add(lblCard2, BorderLayout.NORTH);

		JPanel P3 = new JPanel();
		P3.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		P3.setBounds(953, 782, 95, 145);
		contentPane.add(P3);
		P3.setLayout(new BorderLayout(0, 0));

		lblCard3 = new JLabel();
		P3.add(lblCard3, BorderLayout.NORTH);

		JPanel P4 = new JPanel();
		P4.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		P4.setBounds(1077, 782, 95, 145);
		contentPane.add(P4);
		P4.setLayout(new BorderLayout(0, 0));

		lblCard4 = new JLabel();
		P4.add(lblCard4, BorderLayout.NORTH);

		JPanel PC1 = new JPanel();
		PC1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		PC1.setBounds(720, 52, 99, 132);
		contentPane.add(PC1);
		PC1.setLayout(new BorderLayout(0, 0));

		JLabel lblPC1 = new JLabel("");
		PC1.add(lblPC1, BorderLayout.NORTH);

		JPanel PC2 = new JPanel();
		PC2.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		PC2.setBounds(837, 52, 99, 132);
		contentPane.add(PC2);
		PC2.setLayout(new BorderLayout(0, 0));

		JLabel lblPC2 = new JLabel("");
		PC2.add(lblPC2, BorderLayout.NORTH);

		JPanel PC3 = new JPanel();
		PC3.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		PC3.setBounds(954, 52, 99, 132);
		contentPane.add(PC3);
		PC3.setLayout(new BorderLayout(0, 0));

		JLabel lblPC3 = new JLabel("");
		PC3.add(lblPC3, BorderLayout.NORTH);

		JPanel PC4 = new JPanel();
		PC4.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		PC4.setBounds(1071, 52, 99, 132);
		contentPane.add(PC4);
		PC4.setLayout(new BorderLayout(0, 0));

		JLabel lblPC4 = new JLabel("");
		PC4.add(lblPC4, BorderLayout.NORTH);
		setResizable(false);

		playerLabels.add(lblCard1);
		playerLabels.add(lblCard2);
		playerLabels.add(lblCard3);
		playerLabels.add(lblCard4);
		PCLabels.add(lblPC1);
		PCLabels.add(lblPC2);
		PCLabels.add(lblPC3);
		PCLabels.add(lblPC4);
		Game.getInstance().createDeck();
		Game.getInstance().shuffleDeck();
		Game.getInstance().deal();
		showPlayerDealtCards();
		showPCBackCards();
	}

	public void showPlayerDealtCards() throws IOException {
		Player auxPl = Game.getInstance().getPlayer();
		int i = 0;
		for (Card card : auxPl.getHand()) {
			playerLabels.get(i).setIcon(iconFromAddress(card.getAddressName()));
			i++;
		}
	}

	public void showPCBackCards() throws IOException {
		Player auxPC = Game.getInstance().getPC();
		for (int i = 0; i < auxPC.getHand().size(); i++) {
			PCLabels.get(i).setIcon(iconFromAddress("CardBack3.jpg"));
		}
	}




	public Icon iconFromAddress(String address) throws IOException {
		return new ImageIcon(ImageIO.read(new File("Images/"+address)));
	}
}
