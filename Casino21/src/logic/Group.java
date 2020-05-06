package logic;

import java.util.ArrayList;

public class Group {
	private Player player;
	private ArrayList<Set> mySets;
	private int number;
	private int turnNumber;
	
	
	public Group(Player player, ArrayList<Set> sets, int number, int turnNumber) {
		super();
		this.setPlayer(player);
		this.setMySets(sets);
		this.number = number;
		this.setTurnNumber(turnNumber);
	}
	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<Set> getMySets() {
		return mySets;
	}

	public void setMySets(ArrayList<Set> mySets) {
		this.mySets = mySets;
	}
	
	public float groupValue() {
		float value = 0;
		for (Set set : mySets) {
			value += set.getSetValue();
		}
		return value;
	}

	public int getTurnNumber() {
		return turnNumber;
	}

	public void setTurnNumber(int turnNumber) {
		this.turnNumber = turnNumber;
	}
}
