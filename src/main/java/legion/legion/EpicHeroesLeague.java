package legion.legion;

import org.json.JSONArray;
import org.json.JSONObject;

public class EpicHeroesLeague {
	private String playerID;
	private String playerName;
	private EpicHero[] fighters = new EpicHero[2];
	
	
	public EpicHeroesLeague(String playerID, String playername, EpicHero[] fighters) {
		super();
		this.playerID = playerID;
		this.playerName = playername;
		this.fighters = fighters;
	}
	
	public EpicHeroesLeague() {}


	public String getPlayerID() {
		return playerID;
	}


	public void setPlayerID(String playerID) {
		this.playerID = playerID;
	}


	public String getPlayerName() {
		return playerName;
	}


	public void setPlayerName(String playername) {
		this.playerName = playername;
	}


	public EpicHero[] getFighters() {
		return fighters;
	}


	public void setFighters(EpicHero[] fighters) {
		this.fighters = fighters;
	}


	public void update(String playerId, String playerName, JSONArray epicHero) {
		
		//MAJ du playerId et du playerName
		setPlayerID(playerId);
		setPlayerName(playerName);
		
		//Mise à jour du tableau fighters 
		for (int i=0; i<epicHero.length(); i++) {
			
			//Récupération du ième EpicHero
		    JSONObject item = epicHero.getJSONObject(i);
		    
		    //Récupération de ses paramètres
		    String fighterClass = item.getString("fighterClass");
		    int orderNumberInTeam = (int)item.get("orderNumberInTeam");
		    boolean isDead = (boolean)item.get("isDead");
		    int maxAvailableMana = (int)item.get("maxAvailableMana");
		    int maxAvailableLife = (int)item.get("maxAvailableLife");
		    int currentMana = (int)item.get("currentMana");
		    int currentLife = (int)item.get("currentLife");
		    String fighterID = item.getString("fighterID");
		    
		    //Récupération des state
		    JSONArray states = item.getJSONArray("states");
		    
		    fighters[i].update(fighterClass,orderNumberInTeam,isDead,maxAvailableMana,
		    		maxAvailableLife,currentMana,currentLife,fighterID,states);
			
		}
		
		
		
	}


	
}
