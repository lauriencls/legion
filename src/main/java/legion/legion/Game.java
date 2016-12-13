//Cette classe permet d'implémenter les parties

package legion.legion;


import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class Game {
	
	String idPartie; //Identifiant de la partie
	String idEquipePartie; //Identifiant de l'équipe qui joue
	Board boardPartie; //Tableau de bord de la partie
	ApiCaller apiCaller;
	
	/**
	 * @brief Constructeur de Game
	 */
	public Game(ApiCaller apiCaller) {
		boardPartie = new Board();
		this.apiCaller = apiCaller;
	}
	
	public String getIdPartie() {
		return idPartie;
	}

	public void setIdPartie(String idPartie) {
		this.idPartie = idPartie;
	}

	public String getIdEquipePartie() {
		return idEquipePartie;
	}

	public void setIdEquipePartie(String idEquipePartie) {
		this.idEquipePartie = idEquipePartie;
	}

	public Board getBoardPartie() {
		return boardPartie;
	}

	public void setBoardPartie(Board boardPartie) {
		this.boardPartie = boardPartie;
	}

	/**
	 * @brief Initialisation d'une partie de type PRACTICE
	 * 		  Donc une partie contre des bots
	 * @param String numberBot : Numéro du bot, entre 1 à 12 et 30 à 32
	 * @param String idEquipe : Identifiant de l'équipe qui souhaite jouer
	 */
	public String newGame(String numberBot, String idEquipe) {
		
		this.idPartie = this.apiCaller.newGame(numberBot,idEquipe);
		return this.idPartie;
		
	}
	
	/**
	 * @brief Initialisation d'une partie de type VERSUS
	 * 		  Donc une partie contre d'autres joueurs
	 */
	public String nextGame(String idEquipe) {
		
		//Récupération de l'id de la partie
		this.idPartie = this.apiCaller.nextGame(idEquipe);
		return this.idPartie;
	}
	
	 private ArrayList<EpicHero> getAliveHeroes(ArrayList<EpicHero> team){
	        ArrayList<EpicHero> res = new ArrayList<EpicHero>();

	        for (EpicHero hero : team) {
	            if (!hero.isDead()){
	                res.add(hero);
	            }
	        }

	        return res;
	    }

	private void composeTeam(int turnNumber){
		if (turnNumber == 53){
			this.apiCaller.move(this.idPartie, this.idEquipePartie, "PRIEST");
		} else {
			if (turnNumber == 52){
				this.apiCaller.move(this.idPartie, this.idEquipePartie, "ORC");
			} else {
				if (turnNumber == 51){
					this.apiCaller.move(this.idPartie, this.idEquipePartie, "GUARD");
				}
			}
		}
	}
	 
	public void peacefull() {
		//Phase d'initialisation des heroes (LOL limité à un hero par type)
		if (this.boardPartie.getNbrTurnLeft() > 50){
			//Créer un prêtre, orc, guarde sur les trois premiers tours
			composeTeam(this.boardPartie.getNbrTurnLeft());
			
		//Action durant la partie	
		} else {
			
			//On récupère notre team
			EpicHeroesLeague team = this.boardPartie.getOurTeam();
			//Les heroes encore en vies
			ArrayList<EpicHero> teamHeroes = this.getAliveHeroes(team.getEpicHeroes());
			
			//Déclare le mouvement
			String move = "";
			
			//Pour tous les héroes de notre équipe encore en vie 
			for (EpicHero epicHero : teamHeroes) {
				
				//Stratégie basé sur la paix
				move += "A" + epicHero.getOrderNumberInTeam() + "," + "REST" + "," + "A" + epicHero.getOrderNumberInTeam() + "$";
			}
			
			//On enlève le dernier '$'
			if (move != null && move.length() > 0 && move.charAt(move.length()-1)=='x') {
				move = move.substring(0, move.length()-1);
			    }
			
			this.apiCaller.move(this.idPartie, this.idEquipePartie, move);
			
		}
		
		
	}

	public void draw() {
		//Phase d'initialisation des heroes (LOL limité à un hero par type)
		if (this.boardPartie.getNbrTurnLeft() > 50){
			//Créer un prêtre, orc, guarde sur les trois premiers tours
			composeTeam(this.boardPartie.getNbrTurnLeft());
					
		//Action durant la partie	
		} else {
			//On récupère notre team
			EpicHeroesLeague team = this.boardPartie.getOurTeam();
			//Les heroes encore en vies
			ArrayList<EpicHero> teamHeroes = this.getAliveHeroes(team.getEpicHeroes());
			
			//Déclare le mouvement
			String move = "";
			
			//Pour tous les héroes de notre équipe encore en vie 
			for (EpicHero epicHero : teamHeroes) {
				
			}
		
			//On enlève le dernier '$'
			if (move != null && move.length() > 0 && move.charAt(move.length()-1)=='x') {
				move = move.substring(0, move.length()-1);
		    }
			
			this.apiCaller.move(this.idPartie, this.idEquipePartie, move);
		}
		
	}

	public void strategie() {
		//Phase d'initialisation des heroes (LOL limité à un hero par type)
		if (this.boardPartie.getNbrTurnLeft() > 50){
			//Créer un prêtre, orc, guarde sur les trois premiers tours
			composeTeam(this.boardPartie.getNbrTurnLeft());
					
		//Action durant la partie	
		} else {
			//On récupère notre team
			EpicHeroesLeague team = this.boardPartie.getOurTeam();
			//Les heroes encore en vies
			ArrayList<EpicHero> teamHeroes = this.getAliveHeroes(team.getEpicHeroes());
			
			//Déclare le mouvement
			String move = "";
			
			//Pour tous les héroes de notre équipe encore en vie 
			for (EpicHero epicHero : teamHeroes) {
				//STRATEGIE
				
				
				
				
				
				
				
			}
		
			//On enlève le dernier '$'
			if (move != null && move.length() > 0 && move.charAt(move.length()-1)=='x') {
				move = move.substring(0, move.length()-1);
		    }
			
			this.apiCaller.move(this.idPartie, this.idEquipePartie, move);
		}
	}
	 
}
