package legion.legion;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.SimpleTimeZone; 

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
			
			ApiCaller api = new ApiCaller(serverUrl, format);
			
			//Affichage de "pong" (-p dans le terminal)
			if(args[0].equals("-p")) {
				api.getPong();
			}	
			
			//Affichage du fichier properties (-config dans le terminal)
			if(args[0].equals("-config")) {
				String nomFichier = "properties.txt"; 
				afficheProperties(nomFichier);
			}
			
			//CrÃ©ation d'une partie
			if(args[0].equals("-e")) {
				
				String idPartie = "";
				
				//1. On rÃ©cupÃ¨re l'identifiant de l'Ã©quipe
				String idEquipe = api.getIdEquipe(name, password);
				System.out.println("ID EQUIPE : "+idEquipe);
				
				Game game = new Game(api);
				game.setIdEquipePartie(idEquipe);
				
				if(idEquipe !="") {
					
					//2. CrÃ©ation d'une partie : 
					// - versus si on a un nombre en argument 
					// - practice si on n'a pas d'argument supplÃ©mentaire
					
					String strat = "";
					
					if(args.length > 1) {
						
						String numberBot = args[1];
						
						
						System.out.println("Lancement d'une nouvelle partie PRACTICE");
						game.newGame(numberBot, idEquipe);
						
						strat = args[2];
						
					} else {
						
						System.out.println("Lancement d'une nouvelle partie VERSUS");
						game.nextGame(idEquipe);
						strat = args[1];
								
					}
					
					//RÃ©cupÃ©ration de l'id de la partie aprÃ¨s crÃ©ation
					idPartie = game.getIdPartie();
					System.out.println("ID PARTIE : "+idPartie);
					
					
					
					if(idPartie.equals("null")) {
						System.out.println("La partie n'a pas pu Ãªtre crÃ©Ã©e"); 

					} else {
						System.out.println("On a rÃ©cupÃ©rÃ© l'identifiant, la partie peut commencer !");
						
						String status = "";
						
						boolean finished = false;
						Converter converter = new Converter(name);
						
						Board board;
						
						while(!finished) {
							
						//Je rÃ©cupÃ¨re le statut de la partie
						status = api.getStatus(idPartie, idEquipe);
						System.out.println("Statut de la partie : "+status);
							
						
						//Je mets Ã  jour le Board (voir mÃ©thode en base de Game.java							
							
							switch(status) {
							
								case "CANPLAY" : //On peut jouer									
									
									//Le board est récupéré, il faut maintenant élaborer l'équipe et la stratégie
									game.setBoardPartie(converter.convert(api.getBoard(idPartie)));
									
									System.out.println("Coup ennemie : " + api.getLastMove(idPartie, idEquipe));
									
									if (strat.equals("peacefull")){
										game.peacefull();
									} else {
										if (strat.equals("draw")) {
											game.draw();
										} else {
											if (strat.equals("strategie")) {
												game.strategie();
											}
										}
									}
									
									
									
									break;
									
								case "CANTPLAY" : // On ne peut pas encore jouer
									
									System.out.println("Vous ne pouvez pas encore jouer, veuillez patienter ...");
									Thread.sleep(pause);
									System.out.println("CANTPLAY");
									break;
									
								case "VICTORY" : // On a gagnÃ© la partie
									System.out.println("VICTORY");
									finished = true;
									break;
									
									
								case "DEFEAT" : //On a perdu la partie
									System.out.println("DEFEAT");
									finished = true;
									break;
									
								case "DRAW" : //Match nul
									System.out.println("DRAW");
									finished = true;
									break;
									
								case "CANCELLED" : //Abandon
									finished = true;
									System.out.println("CANCELLED");
									finished = true;
									break;
								
							}
						System.out.println("\n---FIN DE TOUR ---\n");	
						}
						
						
						//Fin du jeu
						
					}
					
				} else {
					System.out.println("Le systÃ¨me n'a pas pu rÃ©cupÃ©rer le nom de la team");
				}
			
			}
		
	} 
	
}//Fin du main

	/**
	 * @brief Affiche le contenu du fichier donnÃ© en paramÃ¨tre
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
	 * @brief RÃ©cupÃ¨re les propriÃ©tÃ©s
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

