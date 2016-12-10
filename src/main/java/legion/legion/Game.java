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
	
	/**
	 * @brief Constructeur de Game
	 */
	public Game() {
		idPartie = "";
	}
	
	/**
	 * @brief Initialisation d'une partie de type PRACTICE
	 * 		  Donc une partie contre des bots
	 * @param String numberBot : Numéro du bot, entre 1 à 12 et 30 à 32
	 * @param String idEquipe : Identifiant de l'équipe qui souhaite jouer
	 */
	public void newGame(String serverUrl,String numberBot, String idEquipe) {
		

		//Récupération de l'id de la partie
		String request = serverUrl +"/practice/new/"+numberBot+"/"+idEquipe;
		idPartie = get(request);	
		idEquipePartie=idEquipe;
		
	}
	
	/**
	 * @brief Initialisation d'une partie de type VERSUS
	 * 		  Donc une partie contre d'autres joueurs
	 */
	public void nextGame(String serverUrl, String idEquipe) {
		
		//Récupération de l'id de la partie
		String request = serverUrl +"/versus/next/"+idEquipe;
		idPartie = get(request);	
		idEquipePartie = idEquipe;
	}
	
	/**
	 * @brief Retourne l'identifiant de la partie
	 */
	public String getGameId() {
		return idPartie;
	}
	
	/**
	 * @brief Renvoie la réponse de la requête donnée en paramètre
	 * @param String url : Url auquel on souhaite accéder 
	 */
	public static String get(String url){

		
		String output = "";
		try {

			Client client = Client.create();

			WebResource webResource = client
			   .resource(url);

			ClientResponse response = webResource.accept("application/json")
	                   .get(ClientResponse.class);

			if (response.getStatus() != 200) {
			   throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatus());
			}

			output = response.getEntity(String.class);

			//Affichage de la réponse
			//System.out.println("Output from Server .... \n");
			//System.out.println(output);

		  } catch (Exception e) {
			e.printStackTrace();
		  }
		
		return(output);

	}
	
	/**
	 * @brief Retourne le statut de la partie 
	 * @param String serverUrl : URL de base pour accéder au serveur
	 * @param String idPartie : Identifiant de la partie
	 */
	public String getStatus(String serverUrl) {
		
		//Récupération de l'id de la partie
		String request = serverUrl +"/game/status/"+idPartie+"/"+idEquipePartie;
		System.out.println(request);
		return(get(request));
	}
	
	/**
	 * @brief Met à jour le plateau de jeu de la partie concernée
	 * @param String serverUrl : URL de base pour accéder au serveur
	 * @param String format : Format de retour des données 
	 * @param String idPartie : Identifiant de la partie
	 */
	public void updateBoard(String serverUrl, String format, String idPartie) {
		
		String request = serverUrl +"/game/board/"+idPartie+"?format="+format;
		System.out.println(request);
		JSONObject res = new JSONObject(get(request));
		
		//Mise à jour de nbTurnsLeft
		int nbrTurnsLeft = (int) res.get("nbrTurnsLeft");
		System.out.println("Nombre de tours restants : "+nbrTurnsLeft);	
		
		//Récupération des EpicHeroesLeague et MAJ
		JSONArray playerBoards = res.getJSONArray("playerBoards");
		
		//MAJ du board
		System.out.println("Nombre de EpicHeroesLeague : "+playerBoards.length());
		boardPartie.update(nbrTurnsLeft,playerBoards); //ICI PROBLEME
		
	
	}
	 
}
