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

		
	/**
	 * @brief Affiche le tableau de bord
	 */
	/*
	public void afficherBoard(){
		System.out.println("--------------------------");
		System.out.println("TABLEAU DE BORD");
		System.out.println("NB TURNS LEFT : "+this.nbrTurnsLeft);
		System.out.println("----- NOTRE EQUIPE");
		ourTeam.afficherEpicHeroesLeague();;
		System.out.println("----- ENNEMI");
		ennemieTeam.afficherEpicHeroesLeague();;
		
	}*/
		
	
	
}
