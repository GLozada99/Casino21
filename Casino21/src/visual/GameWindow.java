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
import visual.Bounds;


import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.Timer;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;

public class GameWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ArrayList<JPanel> playerPanels = new ArrayList<JPanel>();
	private ArrayList<JPanel> PCPanels = new ArrayList<JPanel>();
	private ArrayList<JPanel> tablePanels = new ArrayList<JPanel>();
	private ArrayList<Bounds> playerPanelsBounds = new ArrayList<Bounds>();
	private ArrayList<Bounds> PCPanelsBounds = new ArrayList<Bounds>();
	private ArrayList<Bounds> tablePanelsBounds = new ArrayList<Bounds>();
	private int dealCount = 0;
	private JLabel lblCount;
	private ArrayList<Card> setToCreate = new ArrayList<Card>();
	private Player currentPlayer;




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
		currentPlayer = Game.getInstance().getPlayer();
		//fillPlayersPanelsBounds(xStart, commonYPC, width, height, xSpacing, ySpacing);
		fillTablePanelsBounds(517, 333, 95, 145, 153, 30, 6);
		fillPlayersPanelsBounds(714, 75, 100, 140, 120, 550, 4);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1900, 1040);
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanelBackground();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		((JPanelBackground) contentPane).setBackground("Images/Green2.jpg");
		setContentPane(contentPane);
		contentPane.setLayout(null);

		setResizable(false);
		generatePanels();

		JButton btnDeal = new JButton("Deal");
		btnDeal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					Game.getInstance().getPlayer().getHand().clear();
					Game.getInstance().deal();
					dealCards(800);
					dealCount++;
					if(dealCount==5) {
						btnDeal.setEnabled(false);
					}
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

		JButton btnTable = new JButton("Table");
		btnTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					dealCardsTable(800);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnTable.setBounds(1785, 70, 97, 25);
		contentPane.add(btnTable);

		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(1785, 127, 97, 60);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		lblCount = new JLabel("0");
		lblCount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				boolean found = false;
				ArrayList<Card> aux = currentPlayer.getHand();
				Card auxCard = null;
				int setValue = Integer.valueOf(lblCount.getText());
				for (int i = 0; i < 4 && !found; i++) {
					if(aux.get(i).getNumber()==setValue) {
						found = true;
						auxCard = aux.get(i);
					}
				}
				if(found) {
					Game.getInstance().createGroup(currentPlayer, (ArrayList<Card>) setToCreate.clone(), setValue);
					JOptionPane.showMessageDialog(null, "El grupo fue creado con éxito");
					lblCount.setText("0");
					setToCreate.clear();
				}else {
					JOptionPane.showMessageDialog(null, "El grupo no puede ser creado. Usted no posee la carta adecuada");
				}



			}

		
	});
		lblCount.setHorizontalAlignment(SwingConstants.CENTER);
		lblCount.setFont(new Font("Tahoma", Font.BOLD, 28));
		panel.add(lblCount, BorderLayout.CENTER);
		lblCount.setBackground(Color.WHITE);
		offPanelCards();

		Game.getInstance().createDeck();
		Game.getInstance().shuffleDeck();

		Game.getInstance().onTable();
}



private void fillTablePanelsBounds(int xStart, int commonYUp, int width, int height, int xSpacing, int ySpacing, int panelNumber) {
	int commonYDown = commonYUp + height + ySpacing;
	int x[] = new int[panelNumber];

	for (int i = 0; i < panelNumber; i++) {
		//x[i] = 517 + i*153;
		x[i] = xStart + i*xSpacing;
	}
	int order[] = {3,2,4,1,5,0}; 

	for (int i = 0; i < (panelNumber/2); i++) {
		tablePanelsBounds.add(new Bounds(x[order[2*i]]-45, commonYUp, width, height));
		tablePanelsBounds.add(new Bounds(x[order[2*i + 1]]-45, commonYUp, width, height));
		tablePanelsBounds.add(new Bounds(x[order[2*i]]+45, commonYDown, width, height));
		tablePanelsBounds.add(new Bounds(x[order[2*i + 1]]+45, commonYDown, width, height));
	}
}

private void fillPlayersPanelsBounds(int xStart, int commonYPC, int width, int height, int xSpacing, int ySpacing, int panelNumber) {
	int x[] = new int[panelNumber];
	int commonYPlayer = commonYPC + height + ySpacing;

	for (int i = 0; i < panelNumber; i++) {
		x[i] = xStart + i*xSpacing;

		playerPanelsBounds.add(new Bounds(x[i], commonYPlayer, width, height));
		PCPanelsBounds.add(new Bounds(x[i], commonYPC, width, height));
	}
}

public void generatePanels() {
	for (Bounds bound : tablePanelsBounds) {
		JPanel panel = new JPanelBackground();
		panel.setBorder(null);
		panel.setVisible(false);
		panel.setBounds(bound.getX(),bound.getY(),bound.getWidth(),bound.getHeight());
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		tablePanels.add(panel);
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Card aux = Game.getInstance().cardByAddress(((JPanelBackground)panel).getAddress());
				Integer cardNumber = aux.getNumber();
				Integer cardBefore = Integer.valueOf(lblCount.getText());
				Integer finalNumber = cardNumber + cardBefore;
				if(finalNumber>14) {
					JOptionPane.showMessageDialog(null, "El valor del grupo a crear sobrepasa 14");
				}else {
					lblCount.setText(finalNumber.toString());
					setToCreate.add(aux);
				}
			}
		});
	}
	for (Bounds bound : playerPanelsBounds) {
		JPanel panel = new JPanelBackground();
		panel.setBorder(null);
		panel.setVisible(false);
		panel.setBounds(bound.getX(),bound.getY(),bound.getWidth(),bound.getHeight());
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		playerPanels.add(panel);
	}
	for (Bounds bound : PCPanelsBounds) {
		JPanel panel = new JPanelBackground();
		panel.setBorder(null);
		panel.setVisible(false);
		panel.setBounds(bound.getX(),bound.getY(),bound.getWidth(),bound.getHeight());
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		PCPanels.add(panel);
	}

}


public void setCardInPanel(int choice, int i) {
	//if choice is 0, it means that we will show card for player, 1 for PC backCards, 2 for table and 3 for PC cards
	switch (choice) {
	case 0:
		((JPanelBackground)playerPanels.get(i)).setBackground("Images/"+ Game.getInstance().getPlayer().getHand().get(i).getAddressName());
		break;
	case 1:
		((JPanelBackground)PCPanels.get(i)).setBackground("Images/CardBack4.jpg");	
		break;
	case 2:
		((JPanelBackground)tablePanels.get(i)).setBackground("Images/"+ Game.getInstance().getTable().getCards().get(i).getAddressName());	
		break;
	case 3:
		((JPanelBackground)PCPanels.get(i)).setBackground("Images/"+ Game.getInstance().getPC().getHand().get(i).getAddressName());	
		break;	
	}
}

/*public void addCardToTable(Card card) {
		JPanel PT1 = new JPanelBackground();
		PT1.setBorder(null);
		PT1.setBounds(714, 430, 95, 145);
		contentPane.add(PT1);
		PT1.setLayout(new BorderLayout(0, 0));
		PT1.setVisible(false);
	}*/

public void dealCards(int step) throws IOException, InterruptedException {
	TimerStop timerPlayer = new TimerStop();
	TimerStop timerPC = new TimerStop();

	ActionListener dealPlayer = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			setCardInPanel(0, timerPlayer.getRepetitions());
			playerPanels.get(timerPlayer.getRepetitions()).setVisible(true);
			timerPlayer.incrementRepetitions();
			if(timerPlayer.getRepetitions()==4) {
				timerPlayer.stopTimer();
			}
		}
	};
	ActionListener dealPC = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			setCardInPanel(1, timerPC.getRepetitions());
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

public void dealCardsTable(int step) throws IOException, InterruptedException {
	TimerStop timer = new TimerStop();


	ActionListener dealTable = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			setCardInPanel(2, timer.getRepetitions());
			tablePanels.get(timer.getRepetitions()).setVisible(true);
			timer.incrementRepetitions();
			if(timer.getRepetitions()==4) {
				timer.stopTimer();
			}
		}
	};
	timer.setTimer(new Timer(step, dealTable));
	timer.startTimer();
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
