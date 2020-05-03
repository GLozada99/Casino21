package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;

import javax.swing.ImageIcon;

import javax.swing.Icon;
import javax.swing.border.SoftBevelBorder;

import logic.Card;
import logic.Game;
import logic.Player;

import javax.swing.border.BevelBorder;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.Timer;
import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

public class GameWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public ArrayList<JPanel> playerPanels = new ArrayList<JPanel>();
	public ArrayList<JPanel> PCPanels = new ArrayList<JPanel>();


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
	 * @throws InterruptedException 
	 */
	public GameWindow() throws IOException, InterruptedException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1900, 1040);
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanelBackground();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		((JPanelBackground) contentPane).setBackground("Images/Green2.jpg");
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel P1 = new JPanelBackground();
		P1.setBorder(null);
		P1.setBounds(714, 782, 95, 145);
		contentPane.add(P1);
		P1.setLayout(new BorderLayout(0, 0));

		JPanel P2 = new JPanelBackground();
		P2.setBorder(null);
		P2.setBounds(835, 782, 95, 145);
		contentPane.add(P2);
		P2.setLayout(new BorderLayout(0, 0));

		JPanel P3 = new JPanelBackground();
		P3.setBorder(null);
		P3.setBounds(953, 782, 95, 145);
		contentPane.add(P3);
		P3.setLayout(new BorderLayout(0, 0));

		JPanel P4 = new JPanelBackground();
		P4.setBorder(null);
		P4.setBounds(1077, 782, 95, 145);
		contentPane.add(P4);
		P4.setLayout(new BorderLayout(0, 0));



		JPanel PC1 = new JPanelBackground();

		PC1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		PC1.setBounds(720, 52, 99, 132);
		contentPane.add(PC1);
		PC1.setLayout(new BorderLayout(0, 0));

		JPanel PC2 = new JPanelBackground();
		PC2.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		PC2.setBounds(837, 52, 99, 132);
		contentPane.add(PC2);
		PC2.setLayout(new BorderLayout(0, 0));

		JPanel PC3 = new JPanelBackground();
		PC3.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		PC3.setBounds(954, 52, 99, 132);
		contentPane.add(PC3);
		PC3.setLayout(new BorderLayout(0, 0));

		JPanel PC4 = new JPanelBackground();
		PC4.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		PC4.setBounds(1071, 52, 99, 132);
		contentPane.add(PC4);
		PC4.setLayout(new BorderLayout(0, 0));

		setResizable(false);

		playerPanels.add(P1);
		playerPanels.add(P2);
		playerPanels.add(P3);
		playerPanels.add(P4);
		PCPanels.add(PC1);
		PCPanels.add(PC2);
		PCPanels.add(PC3);
		PCPanels.add(PC4);

		JButton btnDeal = new JButton("Deal");
		btnDeal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					dealCards(800);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});


		btnDeal.setBounds(1785, 13, 97, 25);
		contentPane.add(btnDeal);
		offPanelCards();
		Game.getInstance().createDeck();
		Game.getInstance().shuffleDeck();
		Game.getInstance().deal();

	}
	
	public void setCardInPanel(boolean choice, int i) {
		//if choice is true, it means that we will show card for player, else for PC
		if (choice) {
			((JPanelBackground)playerPanels.get(i)).setBackground("Images/"+ Game.getInstance().getPlayer().getHand().get(i).getAddressName());
		}else {
			((JPanelBackground)PCPanels.get(i)).setBackground("Images/CardBack4.jpg");	
			//((JPanelBackground)PCPanels.get(i)).setBackground("Images/"+ Game.getInstance().getPC().getHand().get(i).getAddressName());
			
		}
		
	}
	
	public void dealCards(int step) throws IOException, InterruptedException {
		TimerStop timerPlayer = new TimerStop();;
		TimerStop timerPC = new TimerStop();;
		
		ActionListener dealPlayer = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCardInPanel(true, timerPlayer.getRepetitions());
				playerPanels.get(timerPlayer.getRepetitions()).setVisible(true);
				timerPlayer.incrementRepetitions();
				if(timerPlayer.getRepetitions()==4) {
					timerPlayer.stopTimer();
				}
			}
		};
		ActionListener dealPC = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCardInPanel(false, timerPC.getRepetitions());
				PCPanels.get(timerPC.getRepetitions()).setVisible(true);
				timerPC.incrementRepetitions();
				if(timerPC.getRepetitions()==4) {
					timerPC.stopTimer();
				}
			}
		};
		timerPlayer.setTimer(new Timer(step, dealPlayer));
		timerPC.setTimer(new Timer(step, dealPC));
		timerPlayer.startTimer();
		Thread.sleep(step/2);
		timerPC.startTimer();
	}

	public void offPanelCards() {
		for (int i = 0; i < 4; i++) {
			playerPanels.get(i).setVisible(false);
			PCPanels.get(i).setVisible(false);
		}
	}



	public Icon iconFromAddress(String address) throws IOException {
		return new ImageIcon(ImageIO.read(new File("Images/"+address)));
	}
}
