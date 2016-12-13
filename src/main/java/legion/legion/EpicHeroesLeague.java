package legion.legion;

import java.util.*;

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


	public List<EpicHero> getEpicHeroes() {
		return this.epicHeroes;
	}


	public void setFighters(ArrayList<EpicHero> epicHeroes) {
		this.epicHeroes = epicHeroes;
	}
	
	public String getFighterIDPos(int position) {
		return this.epicHeroes.get(position).getFighterID();
	}
		
	/*
	public void afficherEpicHeroesLeague() {
		
		System.out.println("------ EPIC HEROES LEAGUE ------");
		System.out.println("PLAYER ID : "+this.playerID);
		System.out.println("PLAYER NAME : "+this.playerName);
		
		for(EpicHero e : epicHeroes) {
			e.afficherEpicHeroe();
		}
	}*/
}
