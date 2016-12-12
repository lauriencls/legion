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
		
	
}
