package legion.legion;

import org.json.JSONArray;
import org.json.JSONObject;

public class Board {


	private EpicHeroesLeague[] playerBoards;
	private int nbrTurnsLeft;

	
	
	public Board(EpicHeroesLeague[] playerBoards, int nbrTurnLeft) {
		super();
		this.playerBoards = playerBoards;
		this.nbrTurnsLeft = nbrTurnLeft;
	}


	public Board() {}


	public EpicHeroesLeague[] getPlayerBoards() {
		return playerBoards;
	}


	public void setPlayerBoards(EpicHeroesLeague[] playerBoards) {
		this.playerBoards = playerBoards;
	}


	public int getNbrTurnLeft() {
		return nbrTurnsLeft;
	}


	public void setNbrTurnsLeft(int nbrTurnsLeft) {
		this.nbrTurnsLeft = nbrTurnsLeft;
	}


	public void update(int nbrTurnsLeft,JSONArray epicHeroes) {
		
		System.out.println("Début de update"); //N'affiche pas ce message donc ne rentre pas dans la méthode
		//MAJ du nbrTurnsLeft
		setNbrTurnsLeft(nbrTurnsLeft);
		
		//Mise à jour du tableau playerBoards
		for (int i=0; i<epicHeroes.length(); i++) {
			
			//Récupération du ième EpicHeroesLeague
		    JSONObject item = epicHeroes.getJSONObject(i);
		    
		    //Récupération de ses paramètres
		    String playerId = item.getString("playerId");
		    System.out.println("PlayerID : "+playerId);
		    String playerName = item.getString("playerName");
		    System.out.println("playerName : "+playerName);

			JSONArray fighters = item.getJSONArray("fighters");
			
		    playerBoards[i].update(playerId, playerName, fighters);
		    
		}
		
	}
	
}
