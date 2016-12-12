//Test github val
//GREGOIRE

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
	
	private static final int pause = 500;
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		//Instanciation des variables
		serverUrl = "";
		name = "";
		password = "";
		format = "";	
		
		initProperties("properties.txt");
		
		
		//Analyse des arguments		
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
				
				System.out.println("Je suis dans le -e");
				
				String idPartie = "";
				
				//1. On récupère l'identifiant de l'équipe
				String idEquipe = getIdEquipe();
				System.out.println("ID EQUIPE : "+idEquipe);
				
				if(idEquipe !="") {
					
					//2. Création d'une partie : 
					// - versus si on a un nombre en argument 
					// - practice si on n'a pas d'argument supplémentaire
					
					Game game = new Game();
					
					if(args.length > 1) {
						
						String numberBot = args[1];
						
						System.out.println("Lancement d'une nouvelle partie VERSUS");
						game.newGame(serverUrl,numberBot,idEquipe);
						
					} else {
						
						System.out.println("Lancement d'une nouvelle partie PRACTICE");
						game.nextGame(serverUrl,idEquipe);
								
					}
					
					//Récupération de l'id de la partie après création
					idPartie = game.getGameId();
					System.out.println("ID PARTIE : "+idPartie);
					if(idPartie.equals("NA")) {
						System.out.println("La partie n'a pas pu être créée"); 

					} else {
						System.out.println("On a récupéré l'identifiant, la partie peut commencer !");
						
						String status = "";
						
						boolean finished = false;
						
						
						//while(!finished) {
							
						//Je récupère le statut de la partie
						status = game.getStatus(serverUrl);
						System.out.println("Statut de la partie : "+status);
							
						
						//Je mets à jour le Board (voir méthode en base de Game.java
						game.updateBoard(serverUrl, format, idPartie);
							
							/*
							switch(status) {
							
								case "CANPLAY" : //On peut jouer
									//board = game.getBoard(serverUrl, format, idPartie);
									break;
									
								case "CANTPLAY" : // On ne peut pas encore jouer
									
									System.out.println("Vous ne pouvez pas encore jouer, veuillez patienter ...");
									Thread.sleep(pause);
									System.out.println("CANPLAY");
									break;
									
								case "VICTORY" : // On a gagné la partie
									System.out.println("VICTORY");
									break;
									
									
								case "DEFEAT" : //On a perdu la partie
									System.out.println("DEFEAT");
									break;
									
								case "DRAW" : //Match nul
									System.out.println("DRAW");
									break;
									
								case "CANCELLED" : //Abandon
									finished = true;
									System.out.println("CANCELLED");
									break;
								
							}
							
						}
						*/
						
						//Fin du jeu
						
					}
					
				} else {
					System.out.println("Le système n'a pas pu récupérer le nom de la team");
				}
				
			
			}
			
			
		}
		
		

		
	} //Fin du main

	/**
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
	
	

	


	
} //Fin de la classe Battlemain

