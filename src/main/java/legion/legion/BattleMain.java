package legion.legion;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource; 

public class BattleMain {

	private static String serverUrl;
	private static String name;
	private static String password;
	private static String format;
	
	public static void main(String[] args) throws IOException {
		
		//Instanciation des variables
		serverUrl = "";
		name = "";
		password = "";
		format = "";		
		
		initProperties("properties.txt");
		
		
		if(args.length > 0) {
			
			//Affichage de "pong" (-p dans le terminal)
			if(args[0].equals("-p")) {
				getPong();
			}	
			
			//Affichage du fichier properties (-config dans le terminal)
			if(args[0].equals("-config")) {
				String nomFichier = "properties.txt"; 
				afficheProperties(nomFichier);
			}
			
			//Création d'une partie
			if(args[0].equals("-e")) {
				String level = args[1]; //Récupération du niveau de la partie
				
				Game game = game.init(level);
			}
			
			
		}
		
		

		
	} //Fin du main

	/*
	 * @brief Affiche le contenu du fichier donné en paramètre
	 * @param String nomFichier : Nom du fichier 
	 */
	public static void afficheProperties(String nomFichier) {
		
		try {
			
			InputStream flux=new FileInputStream(nomFichier); 
			InputStreamReader lecture=new InputStreamReader(flux);
			BufferedReader buff=new BufferedReader(lecture);
			String ligne;
			
			while ((ligne=buff.readLine())!=null) {
				System.out.println(ligne);
			}
			
			buff.close(); 
		}		
		catch (Exception e) {
			System.out.println(e.toString());
		}
	} //Fin de la fonction afficheProperties
	
	/**
	 * @brief Récupère les propriétés
	 * @param file
	 */
	public static void initProperties(String file) {
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream(file);
			// load a properties file
			prop.load(input);
			serverUrl = prop.getProperty("rest.base.url");
			name = prop.getProperty("team.name");
			password = prop.getProperty("team.password");
			format = prop.getProperty("format");
			
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/*
	 * @brief Renvoie le nom de l'équipe
	 */
	public static String getTeamName() {
		String TeamName="";
		try {
			String nomFichier = "properties.txt";
			InputStream flux=new FileInputStream(nomFichier); 
			InputStreamReader lecture=new InputStreamReader(flux);
			BufferedReader buff=new BufferedReader(lecture);
			String ligne1 = buff.readLine();
			String ligne2 = buff.readLine();
			String ligne3 = buff.readLine();
			
			TeamName = ligne2.substring(10);
			buff.close(); 
			//return TeamName;
		}		
		catch (Exception e) {
			System.out.println(e.toString());
		}
		return TeamName;
	}
	
	/*
	 * @brief Renvoie le mot de passe
	 */
	public static String getTeamPwd() {
		String Pwd="";
		try {
			String nomFichier = "properties.txt";
			InputStream flux=new FileInputStream(nomFichier); 
			InputStreamReader lecture=new InputStreamReader(flux);
			BufferedReader buff=new BufferedReader(lecture);
			String ligne1 = buff.readLine();
			String ligne2 = buff.readLine();
			String ligne3 = buff.readLine();
			
			Pwd = ligne3.substring(14);
			buff.close(); 
			//return TeamName;
		}		
		catch (Exception e) {
			System.out.println(e.toString());
		}
		return Pwd;
	}
	
	
	/*
	 * @brief
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

			System.out.println("Output from Server .... \n");
			//System.out.println(output);

		  } catch (Exception e) {

			e.printStackTrace();

		  }
		
		return(output);

		
		
	}
	
	/**
	 * @brief Envoie une requête de pong vers l'API
	 */

	public static void getPong() {
		String request = serverUrl + "/ping";
		get(request);
		//get("http://codeandplay.date/epic-ws/epic/ping");
	}
	
	/**
	 * @brief Création d'une nouvelle partie
	 * @param String level : Niveau de la partie
	 */
	public static void newGame(String level) {
		
		//Initialisation des variables locales
		int tries = 0;
		boolean ok = false;
		
		//Récupération de l'identifiant
		String nomEquipe = getTeamName();
		String motDePasse = getTeamPwd();
		
		//Récupération de l'identifiant de l'équipe
		String request = serverUrl +"/player/getIdEquipe/"+nomEquipe+"/"+motDePasse;
		String teamId = get(request);
		System.out.println(teamId);
		
		
		while(tries<3 && !ok) {
			if(result.equals("NA")) {
				System.out.println("La partie ne peut pas être créée");
				tries++;
			} else { //On a reçu l'identifiant de l'équipe, la partie peut être initialisée
				ok = true;
				Game game = Game.init(teamId,level);
				
				//Choix du type de la partie
				
			}
		}
		
		//Demande de création d'une nouvelle partie

		
		
		
	//	 //Initialisation de la partie
		
	}
} //Fin de la classe Battlemain

