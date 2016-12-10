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


	public void update(String fighterClass, int orderNumberInTeam, boolean isDead, int maxAvailableMana,
			int maxAvailableLife, int currentMana, int currentLife, String fighterID, JSONArray statesTable) {

		//Mise à jour des infos avec des set
		setFighterClass(fighterClass);
		setOrderNumberInTeam(orderNumberInTeam);
		setIsDead(isDead);
		setMaxAvailableMana(maxAvailableMana);
		setMaxAvailableLife(maxAvailableLife);
		setCurrentMana(currentMana);
		setCurrentLife(currentLife);
		setFighterID(fighterID);
		
		//Parcours du JSONArray
		
		for (int i=0; i<statesTable.length(); i++) {
			
			//Récupération du ième EpicHero
		    JSONObject item = statesTable.getJSONObject(i);
		    
		    //Récupération de ses paramètres
		    String type = item.getString("type");
		    int remainingDuration = (int)item.get("remainingDuration");
		    
		    states.get(i).update(type,remainingDuration);
			
		}
		
	}


	
	
	
	
}
