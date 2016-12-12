package legion.legion;


import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

//Méthodes d'appels à l'API
public class ApiCaller {
	
	private String url;
	private String format;
	
	/*
	 * @brief Constructeur de l'ApiCaller
	 * @param String url : Url de base pour appeler l'API
	 */
	public ApiCaller(String url, String format) {
		this.url = url;
		this.format = format;
	}
	
	/**
	 * @brief Renvoie la réponse de la requête donnée en paramètre
	 * @param String url : Url auquel on souhaite accéder 
	 */
	public static String get(String url){
		
		String output = "";
		try {
			Client client = Client.create();
			WebResource webResource = client.resource(url);

			ClientResponse response = webResource.accept("application/json")
	                   .get(ClientResponse.class);

			if (response.getStatus() != 200) {
			   throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatus());
			}

			output = response.getEntity(String.class);

		  } catch (Exception e) {

			e.printStackTrace();

		  }
		
		return(output);		
	}
	
	
	/**
	 * @brief Envoie une requête de pong vers l'API
	 */
	public void getPong() {
		String request = url + "/ping";
		get(request);
	}
	
	/**
	 * @brief Renvoie l'id de l'équipe pour commencer une partie
	 * @param String level : Niveau de la partie
	 */
	public String getIdEquipe(String name, String password) {
		
		//Récupération de l'identifiant de l'équipe
		String request = url +"/player/getIdEquipe/"+name+"/"+password;
		String teamId = get(request);
		
		return teamId;
		}
	
	/**
	 * @brief Initialisation d'une partie de type PRACTICE
	 * 		  Donc une partie contre des bots
	 * @param String numberBot : Numéro du bot, entre 1 à 12 et 30 à 32
	 * @param String idEquipe : Identifiant de l'équipe qui souhaite jouer
	 */
	public String newGame(String numberBot, String idEquipe) {
		

		//Récupération de l'id de la partie
		String request = url +"/practice/new/"+numberBot+"/"+idEquipe;
		return get(request);	
		
	}	
	
	/**
	 * @brief Initialisation d'une partie de type VERSUS
	 * 		  Donc une partie contre d'autres joueurs
	 */	
	public String nextGame(String idEquipe) {
		String request = url +"/versus/next/"+idEquipe;
		return get(request);	
	}
	
	/**
	 * @brief Retourne le statut de la partie 
	 * @param String serverUrl : URL de base pour accéder au serveur
	 * @param String idPartie : Identifiant de la partie
	 */
	public String getStatus(String idPartie, String idEquipePartie) {
		
		//Récupération de l'id de la partie
		String request = url +"/game/status/"+idPartie+"/"+idEquipePartie;
		return(get(request));
	}
	
	public String getBoard(String idPartie) {
		String request = url + "/game/board/"+idPartie+"?format="+format;
		return(get(request));
	}
}
