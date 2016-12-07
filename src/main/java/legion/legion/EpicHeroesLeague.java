package legion.legion;

public class EpicHeroesLeague {
	private String playerID;
	private String playername;
	private EpicHero fighters;
	
	
	public EpicHeroesLeague(String playerID, String playername, EpicHero fighters) {
		super();
		this.playerID = playerID;
		this.playername = playername;
		this.fighters = fighters;
	}


	public String getPlayerID() {
		return playerID;
	}


	public void setPlayerID(String playerID) {
		this.playerID = playerID;
	}


	public String getPlayername() {
		return playername;
	}


	public void setPlayername(String playername) {
		this.playername = playername;
	}


	public EpicHero getFighters() {
		return fighters;
	}


	public void setFighters(EpicHero fighters) {
		this.fighters = fighters;
	}
	
}
