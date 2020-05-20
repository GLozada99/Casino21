package visual;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import logic.Group;

//This class does not belong to me
/**
 * 
 * Clase que extiende de JPanel y permite poner una imagen como fondo.
 * 
 * @author Guille Rodriguez Gonzalez ( http://www.driverlandia.com )
 * @version 1.0 | 05-2014
 * 
 */

public class JPanelBackground extends JPanel {
	 
	// Atributo que guardara la imagen de Background que le pasemos.
	private Image background;
	private String backgroundAddress = null;
	private ArrayList<JPanelBackground> groupCards = new ArrayList<JPanelBackground>();
	private Group myGroup = null;
	private ArrayList<Integer> previousPositions = new ArrayList<Integer>();
	private ArrayList<Bounds> previousBounds = new ArrayList<Bounds>();//this attribute and the one who is above were added to provide functionality when creating groups
	
 
	// Metodo que es llamado automaticamente por la maquina virtual Java cada vez que repinta
	public void paintComponent(Graphics g) {
 
		/* Obtenemos el tamaño del panel para hacer que se ajuste a este
		cada vez que redimensionemos la ventana y se lo pasamos al drawImage */
		int width = this.getSize().width;
		int height = this.getSize().height;
 
		// Mandamos que pinte la imagen en el panel
		if (this.background != null) {
			g.drawImage(this.background, 0, 0, width, height, null);
		}
 
		super.paintComponent(g);
	}
 
	// Metodo donde le pasaremos la dirección de la imagen a cargar.
	public void setBackground(String imagePath) {
		
		// Construimos la imagen y se la asignamos al atributo background.
		this.setOpaque(false);
		this.background = new ImageIcon(imagePath).getImage();
		this.backgroundAddress = imagePath;
		repaint();
	}
	
	public void setGroupAddress(String address) {
		this.backgroundAddress = address;
	}
	
	public String getAddress(){
		String address = "No Address";
		if(backgroundAddress != null) {
			address = backgroundAddress;
		}
		return address;
	}
	
	public void setGroupCards(ArrayList<JPanelBackground> groupCards, Group group) {
		this.groupCards = groupCards;
		this.myGroup = group;
	}
	public ArrayList<JPanelBackground> getGroupCards(){
		return groupCards;
	}
	public Group getGroup() {
		return myGroup;
	}

	public ArrayList<Integer> getPreviousPositions() {
		return previousPositions;
	}

	public void setPreviousPositions(ArrayList<Integer> previousPositions) {
		this.previousPositions = previousPositions;
	}

	public ArrayList<Bounds> getPreviousBounds() {
		return previousBounds;
	}

	public void setPreviousBounds(ArrayList<Bounds> previousBounds) {
		this.previousBounds = previousBounds;
	}
	
	public int componentPosition(JPanelBackground panel) {
		int position = 0;
		boolean found = false;
		Component[] panels = getComponents();
		for (int i = 0; i < panels.length && !found; i++) {
			if(panel==panels[i]) {
				position = i;
				found = true;
			}
		}
		return position;
	}
 
}
