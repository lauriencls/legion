package legion.legion;

import org.json.JSONArray;
import org.json.JSONObject;

public class Board {


	private EpicHeroesLeague ourTeam;
	private EpicHeroesLeague ennemieTeam;
	private int nbrTurnsLeft;

	
	
	public Board(EpicHeroesLeague ourTeam, EpicHeroesLeague ennemieTeam, int nbrTurnLeft) {
		super();
		this.ourTeam = ourTeam;
		this.ennemieTeam = ennemieTeam;
		this.nbrTurnsLeft = nbrTurnLeft;
	}


	public Board() {
		nbrTurnsLeft=0;
	}

	public EpicHeroesLeague getOurTeam() {
		return ourTeam;
	}


	public void setOurTeam(EpicHeroesLeague ourTeam) {
		this.ourTeam = ourTeam;
	}


	public EpicHeroesLeague getEnnemieTeam() {
		return ennemieTeam;
	}


	public void setEnnemieTeam(EpicHeroesLeague ennemieTeam) {
		this.ennemieTeam = ennemieTeam;
	}


	public int getNbrTurnLeft() {
		return nbrTurnsLeft;
	}


	public void setNbrTurnsLeft(int nbrTurnsLeft) {
		this.nbrTurnsLeft = nbrTurnsLeft;
	}


	public void update(int nbrTurnsLeftParam,JSONArray epicHeroes) {
		
		System.out.println("Début de update"); //N'affiche pas ce message donc ne rentre pas dans la méthode
		System.out.println("nbrtunrns " + nbrTurnsLeftParam);
		System.out.println("taille de epicHeres : " +epicHeroes.length());			
		//MAJ du nbrTurnsLeft
		
		setNbrTurnsLeft(nbrTurnsLeftParam);
		
		//Mise à jour du tableau playerBoards
		for (int i=0; i<epicHeroes.length(); i++) {
			
			//Récupération du ième EpicHeroesLeague
		    JSONObject item = epicHeroes.getJSONObject(i);
		    
		    //Récupération de ses paramètres
		    String playerId = item.getString("playerId");
		    System.out.println("PlayerID : "+playerId);
		    String playerName = item.getString("playerName");
		    System.out.println("playerName : "+playerName);
		    
		    
		    //Récupération de la liste des fighters dans un tableau
			//EpicHero fighters = item.get("fighters");	
			
			//playerBoards[i].update(playerId, playerName, fighters);
			
			//System.out.println("Tableau vide");
			
			
		    
		    
		}
		
	}
	
}
