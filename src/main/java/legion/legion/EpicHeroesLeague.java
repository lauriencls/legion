package legion.legion;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

public class EpicHeroesLeague {
	private String playerID;
	private String playerName;
	private ArrayList<EpicHero> epicHeroes;
	
	
	public EpicHeroesLeague(String playerID, String playername) {
		super();
		this.playerID = playerID;
		this.playerName = playername;
		this.epicHeroes = new ArrayList<EpicHero>();
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


	public ArrayList<EpicHero> getEpicHeroes() {
		return this.epicHeroes;
	}


	public void setFighters(ArrayList<EpicHero> epicHeroes) {
		this.epicHeroes = epicHeroes;
	}
		
	
}
