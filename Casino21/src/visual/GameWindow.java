package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
import logic.Group;
import logic.Player;
import logic.Set;
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
import java.awt.Component;

import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.SwingConstants;

public class GameWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MouseAdapter clickToGroup = null;
	private MouseAdapter clickToTakeGroup = null;
	private MouseAdapter clickTablePanels = null;
	private MouseAdapter clickPlayerPanels = null;
	private MouseAdapter clickDropPanel = null;
	private ActionListener timerPlayerCards = null;
	private ActionListener timerPCCards = null;
	private ActionListener timerTableCards = null;
	private ActionListener dealCardsButton = null;








	private JButton btnDeal = null;
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
	private int currentTurnNumber = 0;
	private Card playerCard = null;





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
		generateActionListeners();

		//fillPlayersPanelsBounds(xStart, commonYPC, width, height, xSpacing, ySpacing);
		//fillTablePanelsBounds(xStart, commonYUp, width, height, xSpacing, ySpacing, panelNumber);
		fillTablePanelsBounds(517, 333, 95, 145, 153, 50, 6);
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

		btnDeal = new JButton("Deal");
		btnDeal.addActionListener(dealCardsButton);


		btnDeal.setBounds(1785, 13, 97, 25);
		contentPane.add(btnDeal);

		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(1785, 89, 97, 68);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		lblCount = new JLabel("0");
		lblCount.addMouseListener(clickToGroup);

		lblCount.setHorizontalAlignment(SwingConstants.CENTER);
		lblCount.setFont(new Font("Tahoma", Font.BOLD, 28));
		panel.add(lblCount, BorderLayout.CENTER);
		lblCount.setBackground(Color.WHITE);

		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblCount.setText("0");
				setToCreate.clear();
				playerCard = null;
			}
		});
		btnReset.setBounds(1785, 51, 97, 25);
		contentPane.add(btnReset);
		offPanelCards();

		Game.getInstance().createDeck();
		Game.getInstance().shuffleDeck();

		Game.getInstance().onTable();

	}


	////////////////////////////////Moving and creating panels for groups
	private void movePanels(Group group, boolean duplicate) {
		ArrayList<JPanelBackground> panels = new ArrayList<JPanelBackground>();
		ArrayList<Integer> previousPositions = new ArrayList<Integer>();
		ArrayList<Bounds> previousBounds = new ArrayList<Bounds>();
		int i = 0;
		for (Card card : setToCreate) {
			JPanel panel = tablePanelByAddress("Images/"+card.getAddressName());
			panels.add((JPanelBackground) panel);
			previousBounds.add(new Bounds(panel.getX(), panel.getY(), panel.getWidth(), panel.getHeight()));
			previousPositions.add(((JPanelBackground)contentPane).componentPosition((JPanelBackground) panel));
			if(i>0) {
				panel.setBounds(panels.get(i-1).getX()+20, panels.get(i-1).getY()+20, panel.getWidth(), panel.getHeight());
				contentPane.remove(panel);
				contentPane.add(panel, 0);
			}
			i++;
		}
		createGroupPanel(panels, group, i-1, duplicate,previousPositions,previousBounds);
	}

	private void createGroupPanel(ArrayList<JPanelBackground> panels, Group group, int slides, boolean duplicate, ArrayList<Integer> previousPositions, ArrayList<Bounds> previousBounds) {
		if(!duplicate) {
			JPanel groupPanel = new JPanelBackground();
			groupPanel.setBorder(null);
			groupPanel.setVisible(true);
			groupPanel.setOpaque(false);
			((JPanelBackground)groupPanel).setGroupCards((ArrayList<JPanelBackground>) panels.clone(), group);
			((JPanelBackground)groupPanel).setPreviousBounds((ArrayList<Bounds>) previousBounds.clone());
			((JPanelBackground)groupPanel).setPreviousPositions((ArrayList<Integer>) previousPositions.clone());
			groupPanel.setBounds(panels.get(0).getX(),panels.get(0).getY(),panels.get(0).getWidth()+(slides*20),panels.get(0).getHeight()+(slides*20));
			contentPane.add(groupPanel);
			groupPanel.setLayout(new BorderLayout(0, 0));
			contentPane.add(groupPanel, 0);


			clickToTakeGroupAL(group, previousPositions, previousBounds, groupPanel);
			groupPanel.addMouseListener(clickToTakeGroup); 

		}
	}
	/////////////////////////////////////////////////Moving and creating panels for groups


	///////////////////////////////////GeneratePanels
	public void generatePanels() {
		generateTablePanels();
		generatePlayerPanels();
		generatePCPanels();
		generateDropPanel();
	}

	public void generateTablePanels() {
		for (Bounds bound : tablePanelsBounds) {
			JPanel panel = new JPanelBackground();
			panel.setBorder(null);
			panel.setVisible(false);
			panel.setBounds(bound.getX(),bound.getY(),bound.getWidth(),bound.getHeight());
			contentPane.add(panel);
			panel.setLayout(new BorderLayout(0, 0));
			tablePanels.add(panel);
			clickTablePanelsAL(panel);
			panel.addMouseListener(clickTablePanels);
		}
	}

	public void generatePlayerPanels() {
		for (Bounds bound : playerPanelsBounds) {
			JPanel panel = new JPanelBackground();
			panel.setBorder(null);
			panel.setVisible(false);
			panel.setBounds(bound.getX(),bound.getY(),bound.getWidth(),bound.getHeight());
			contentPane.add(panel);
			panel.setLayout(new BorderLayout(0, 0));
			playerPanels.add(panel);
			clickPlayerPanelsAL(panel, bound);
			panel.addMouseListener(clickPlayerPanels);
		}
	}

	public void generatePCPanels() {
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

	public void generateDropPanel() {
		JPanel firstPanel = new JPanelBackground();
		firstPanel.setBorder(null);
		firstPanel.setVisible(true);
		firstPanel.setOpaque(false);
		firstPanel.setBounds(tablePanelsBounds.get(10).getX()-25,tablePanelsBounds.get(10).getY()-30,6*tablePanelsBounds.get(10).getWidth()+425,2*tablePanelsBounds.get(10).getHeight()+110);
		contentPane.add(firstPanel);
		firstPanel.setLayout(new BorderLayout(0, 0));
		firstPanel.addMouseListener(clickDropPanel);

	}
	//////////////////////////////////////////////////////////////////GeneratePanels
	

	///////////////////////////////////////////////////////////////////////////////Bounds
	private void fillTablePanelsBounds(int xStart, int commonYUp, int width, int height, int xSpacing, int ySpacing, int panelNumber) {
		int commonYDown = commonYUp + height + ySpacing;
		int x[] = new int[panelNumber];

		for (int i = 0; i < panelNumber; i++) {

			x[i] = xStart + i*xSpacing;
		}
		int order[] = {3,2,4,1,5,0}; 

		for (int i = 0; i < panelNumber; i++) {
			tablePanelsBounds.add(new Bounds(x[order[i]]-45, commonYUp, width, height));
			tablePanelsBounds.add(new Bounds(x[order[i]]+45, commonYDown, width, height));
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
	//////////////////////////////////////////////////////////////////////////////////////////////////////////Bounds


	///////////////////////////////////////////////////////////////Deal Cards	
	public void dealCards(int step) throws IOException, InterruptedException {
		TimerStop timerPlayer = new TimerStop();
		timerPlayerCardsAL(timerPlayer,step);
		timerPlayer.setTimer(new Timer(step, timerPlayerCards));
		
		TimerStop timerPC = new TimerStop();
		timerPCCardsAL(timerPC, step);
		timerPC.setTimer(new Timer(step, timerPCCards));
		
		timerPlayer.startTimer();
		Thread.sleep(step/2);
		timerPC.startTimer();
	}

	public void dealCardsTable(int step) throws IOException, InterruptedException {
		TimerStop timerTable = new TimerStop();
		timerTableCards(timerTable,step);
		timerTable.setTimer(new Timer(step, timerTableCards));
		timerTable.startTimer();
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
	//////////////////////////////////////////////////////////////////////////////////////////////////////Deal Cards	


	public void generateActionListeners(){
		dealCardsButton = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int step = 750;
				try {

					Game.getInstance().getPlayer().getHand().clear();
					Game.getInstance().getPC().getHand().clear();
					Game.getInstance().deal();
					dealCards(step);
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
		};
		clickToGroup = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {//Click to group

				boolean found = false;
				boolean result = true;
				int setValue = 0;
				for (Group group : Game.getInstance().getTable().getGroups()) {
					for (Set set : group.getMySets()) {
						for (Card card1 : set.getCards()) {
							for (Card card2 : setToCreate) {
								if(card2==card1) {
									result = false;
								}
							}

						}
					}	
				}
				if(result) {
					for (int i = 0; i < currentPlayer.getHand().size() && !found; i++) {
						setValue = currentPlayer.getHand().get(i).getNumber();
						if(Game.getInstance().createGroup(currentPlayer, (ArrayList<Card>) setToCreate.clone(), setValue, currentTurnNumber)) {
							found = true;
						}
					}
					if(found) {
						int index = 0;
						boolean duplicate = false;
						if(Game.getInstance().getTable().getGroups().size()>1) {
							Group lastGroup = Game.getInstance().getTable().getGroups().get(Game.getInstance().getTable().getGroups().size()-1);
							Set lastSet = lastGroup.getMySets().get(0);
							Game.getInstance().getTable().getGroups().remove(lastGroup);

							for (Group group : Game.getInstance().getTable().getGroups()) {
								if(group.getNumber()==lastGroup.getNumber()) {
									duplicate = true;
									group.getMySets().add(lastSet);
									index = Game.getInstance().getTable().getGroups().indexOf(group);
								}
							}
							if(!duplicate) {
								Game.getInstance().getTable().getGroups().add(lastGroup);
								index = Game.getInstance().getTable().getGroups().size()-1;
							}
						}
						movePanels(Game.getInstance().getTable().getGroups().get(index), duplicate);
					}else {
						JOptionPane.showMessageDialog(null, "El grupo no puede ser creado. Usted no posee la carta adecuada");
					}
				}else {
					JOptionPane.showMessageDialog(null, "El grupo no puede ser creado. Las cartas a usar no pueden pertenecer a otro grupo");
				}
				lblCount.setText("0");
				setToCreate.clear();
			}
		};
		
		clickDropPanel = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(playerCard!=null) {
					boolean found = false;


					for (int i = 0; i < tablePanels.size() && !found ; i++) {
						if(!tablePanels.get(i).isVisible()) {
							((JPanelBackground)tablePanels.get(i)).setBackground("Images/"+ playerCard.getAddressName());	
							tablePanels.get(i).setVisible(true);
							found = true;
						}
					}
					found = false;
					for (int i = 0; i < playerPanels.size() && !found; i++) {
						//setValue = currentPlayer.getHand().get(i).getNumber();
						if(Game.getInstance().cardByAddress(((JPanelBackground)playerPanels.get(i)).getAddress())==playerCard) {
							found = true;
							playerPanels.get(i).setBounds(playerPanelsBounds.get(i).getX(), playerPanelsBounds.get(i).getY(), playerPanelsBounds.get(i).getWidth(), playerPanelsBounds.get(i).getHeight());
							playerPanels.get(i).setVisible(false);
							Game.getInstance().playCard(currentPlayer, playerCard);
						}
					}
					playerCard = null;
				}
			}
		};

	}

	public void clickToTakeGroupAL(Group group, ArrayList<Integer> previousPositions,  ArrayList<Bounds> previousBounds, JPanel groupPanel ) {
		clickToTakeGroup = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(playerCard!=null) {
					//Collections.sort(perviousPositions);
					if(!Game.getInstance().takeGroup(currentPlayer, playerCard, group)) {
						JOptionPane.showMessageDialog(null, "La carta que ha utilizado no es la correcta");	
					}else {
						while(!previousPositions.isEmpty()) {
							int minimunPosition = previousPositions.get(0);
							int indexBounds = 0;
							for (Integer index : previousPositions) {
								if(minimunPosition>index) {
									minimunPosition = index;
								}
								indexBounds++;
							}
							JPanel thePanel = ((JPanelBackground)groupPanel).getGroupCards().get(indexBounds-1);
							Bounds theBounds = previousBounds.get(indexBounds-1);
							thePanel.setBounds(theBounds.getX(),theBounds.getY(),theBounds.getWidth(),theBounds.getHeight());
							thePanel.setVisible(false);
							contentPane.add(thePanel, minimunPosition);
							previousPositions.remove(Integer.valueOf(minimunPosition));
							previousBounds.remove(theBounds);
							((JPanelBackground)groupPanel).getGroupCards().remove(thePanel);
						}

						playerPanelByAddress("Images/"+playerCard.getAddressName()).setVisible(false);
						contentPane.remove(groupPanel);
						playerCard = null;
					}
				}
			}
		};

	}
	
	public void clickTablePanelsAL(JPanel panel) {
		clickTablePanels = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				Card aux = Game.getInstance().cardByAddress(((JPanelBackground)panel).getAddress());
				if(playerCard==null) {
					if(!setToCreate.contains(aux)) {
						int cardNumber = aux.getNumber();
						int cardBefore = Integer.valueOf(lblCount.getText());
						int finalNumber = cardNumber + cardBefore;

						if(finalNumber>14) {
							JOptionPane.showMessageDialog(null, "El valor del grupo a crear sobrepasa 14");
						}else {
							lblCount.setText(Integer.valueOf(finalNumber).toString());
							setToCreate.add(aux);
						}
					}
				}else {
					if(Game.getInstance().takeCard(currentPlayer, playerCard, aux)) {
						panel.setVisible(false);
						playerPanelByAddress("Images/"+playerCard.getAddressName()).setVisible(false);
						playerCard = null;
					}else {
						JOptionPane.showMessageDialog(null, "La carta que ha utilizado no es la correcta");
					}
				}
			}
		};
	}

	public void clickPlayerPanelsAL(JPanel panel, Bounds bound) {
		clickPlayerPanels = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				lblCount.setText("0");
				setToCreate.clear();
				int i = 0;
				for (JPanel jPanel : playerPanels) {
					jPanel.setBounds(playerPanelsBounds.get(i).getX(), playerPanelsBounds.get(i).getY(), playerPanelsBounds.get(i).getWidth(), playerPanelsBounds.get(i).getHeight());	
					i++;
				}
				if(playerCard!=Game.getInstance().cardByAddress(((JPanelBackground)panel).getAddress())){
					playerCard = Game.getInstance().cardByAddress(((JPanelBackground)panel).getAddress());
					panel.setBounds(bound.getX()-10,bound.getY()-10,bound.getWidth(),bound.getHeight());	
				}else {
					playerCard = null;
				}
			}
		};
	}
	
	public void timerPCCardsAL(TimerStop timerPC, int step) {
		timerPCCards = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCardInPanel(1, timerPC.getRepetitions());
				PCPanels.get(timerPC.getRepetitions()).setVisible(true);
				timerPC.incrementRepetitions();
				if(timerPC.getRepetitions()==4) {
					timerPC.stopTimer();
				}
			}
		};
		
	}

	public void timerPlayerCardsAL(TimerStop timerPlayer, int step) {
		timerPlayerCards = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCardInPanel(0, timerPlayer.getRepetitions());
				playerPanels.get(timerPlayer.getRepetitions()).setVisible(true);
				timerPlayer.incrementRepetitions();
				if(timerPlayer.getRepetitions()==4) {
					timerPlayer.stopTimer();
					if(dealCount==1) {
						try {
							Thread.sleep(step/2);
							dealCardsTable(step/2);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		};
	}
	
	public void timerTableCards(TimerStop timerTable, int step) {
		timerTableCards = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCardInPanel(2, timerTable.getRepetitions());
				tablePanels.get(timerTable.getRepetitions()).setVisible(true);
				timerTable.incrementRepetitions();
				if(timerTable.getRepetitions()==Game.getInstance().getTable().getCards().size()) {
					timerTable.stopTimer();
				}

			}
		};
	}

	///////////////////////////////////////////////////////////////////////////////Misc functions
	public JPanel tablePanelByAddress(String address) {
		JPanel aux = null;
		boolean found = false;
		int i = 0;
		while(i < tablePanels.size()&&!found) {
			if(((JPanelBackground)tablePanels.get(i)).getAddress().equalsIgnoreCase(address)) {
				found = true;
				aux = tablePanels.get(i);
			}
			i++;
		}
		return aux;
	}
	public JPanel playerPanelByAddress(String address) {
		JPanel aux = null;
		boolean found = false;
		int i = 0;
		while(i < playerPanels.size()&&!found) {
			if(((JPanelBackground)playerPanels.get(i)).getAddress().equalsIgnoreCase(address)) {
				found = true;
				aux = playerPanels.get(i);
			}
			i++;
		}
		return aux;
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
	///////////////////////////////////////////////////////////////////////////////////////////Misc functions



}
