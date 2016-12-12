//Cette classe permet d'implémenter les parties

package legion.legion;


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
	 
}
