package legion.legion;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;


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
			int maxAvalaibleLife, int currentMana, int currentLife, ArrayList<State> states, String fighterID) {
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
	
	public EpicHero() {}


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


	public void setIsDead(boolean isDead) {
		this.isDead = isDead;
	}


	public int getMaxAvalaibleMana() {
		return maxAvalaibleMana;
	}


	public void setMaxAvailableMana(int maxAvalaibleMana) {
		this.maxAvalaibleMana = maxAvalaibleMana;
	}


	public int getMaxAvailableLife() {
		return maxAvalaibleLife;
	}


	public void setMaxAvailableLife(int maxAvalaibleLife) {
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
		return this.states;
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

	

	/*
	public void afficherEpicHeroe() {
		
		System.out.println("------ EPIC HEROE ------");
		System.out.println("FIGHTER CLASS : "+this.fighterClass);
		System.out.println("ORDER NUMBER IN TEAM : "+this.orderNumberInTeam);
		System.out.println("IS DEAD : "+this.isDead);
		System.out.println("MAX AVAILABLE MANA : "+this.maxAvalaibleMana);
		System.out.println("MAX AVAILABLE LIFE : "+this.maxAvalaibleLife);
		System.out.println("CURRENT MANA : "+this.currentMana);
		System.out.println("CURRENT LIFE : "+this.currentLife);
		System.out.println("FIGHTER ID : "+this.fighterID);
		

		for(State s : states) {
			s.afficherState();
		}

		this.fighterClass = fighterClass;
		this.orderNumberInTeam = orderNumberInTeam;
		this.isDead = isDead;
		this.maxAvalaibleMana = maxAvalaibleMana;
		this.maxAvalaibleLife = maxAvalaibleLife;
		this.currentMana = currentMana;
		this.currentLife = currentLife;
		this.states = states;
		this.fighterID = fighterID;
	}*/
	
	


	
	
	
	
}
