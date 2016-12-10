package legion.legion;

import java.util.Vector;
import java.util.ArrayList;


public class EpicHero {
	private String fighterClass;
	private int orderNumberInTeam;
	private boolean isDead;
	private int maxAvalaibleMana;
	private int maxAvalaibleLife;
	private int currentMana;
	private int currentLife;
	private ArrayList<State> states = new ArrayList<State>();
	private String fighterID;
	
	
	public EpicHero(String fighterClass, int orderNumberInTeam, boolean isDead, int maxAvalaibleMana,
			int maxAvalaibleLife, int currentMana, int currentLife, State states, String fighterID) {
		super();
		this.fighterClass = fighterClass;
		this.orderNumberInTeam = orderNumberInTeam;
		this.isDead = isDead;
		this.maxAvalaibleMana = maxAvalaibleMana;
		this.maxAvalaibleLife = maxAvalaibleLife;
		this.currentMana = currentMana;
		this.currentLife = currentLife;
		this.states = states;
		this.fighterID = fighterID;
	}


	public String getFighterClass() {
		return fighterClass;
	}


	public void setFighterClass(String fighterClass) {
		this.fighterClass = fighterClass;
	}


	public int getOrderNumberInTeam() {
		return orderNumberInTeam;
	}


	public void setOrderNumberInTeam(int orderNumberInTeam) {
		this.orderNumberInTeam = orderNumberInTeam;
	}


	public boolean isDead() {
		return isDead;
	}


	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}


	public int getMaxAvalaibleMana() {
		return maxAvalaibleMana;
	}


	public void setMaxAvalaibleMana(int maxAvalaibleMana) {
		this.maxAvalaibleMana = maxAvalaibleMana;
	}


	public int getMaxAvalaibleLife() {
		return maxAvalaibleLife;
	}


	public void setMaxAvalaibleLife(int maxAvalaibleLife) {
		this.maxAvalaibleLife = maxAvalaibleLife;
	}


	public int getCurrentMana() {
		return currentMana;
	}


	public void setCurrentMana(int currentMana) {
		this.currentMana = currentMana;
	}


	public int getCurrentLife() {
		return currentLife;
	}


	public void setCurrentLife(int currentLife) {
		this.currentLife = currentLife;
	}


	public ArrayList<State> getStates() {
		return states;
	}


	public void setStates(ArrayList<State> states) {
		this.states = states;
	}


	public String getFighterID() {
		return fighterID;
	}


	public void setFighterID(String fighterID) {
		this.fighterID = fighterID;
	}
	
	
	
	
}
