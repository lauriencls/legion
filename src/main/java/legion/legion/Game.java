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
			System.out.println("Il reste 53 tours");
			this.apiCaller.move(this.idPartie, this.idEquipePartie, "GUARD");
			System.out.println("Move sent   : GUARD");
		} else {
			if (turnNumber == 52){
				System.out.println("Il reste 52 jours");
				this.apiCaller.move(this.idPartie, this.idEquipePartie, "ORC");
				System.out.println("Move sent   : ORC");
			} else {
				if (turnNumber == 51){
					System.out.println("Il reste 51 tours");
					this.apiCaller.move(this.idPartie, this.idEquipePartie, "PRIEST");
					System.out.println("Move sent   : PRIEST");
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
			ArrayList<EpicHero> teamHeroes = this.getAliveHeroes(team.getFighters());
			
		
			//Déclare le mouvement
			String move = "";
			
			//Pour tous les héroes de notre équipe encore en vie 
			for (EpicHero epicHero : teamHeroes) {
				
				//Stratégie basé sur la paix
				move += "A" + epicHero.getOrderNumberInTeam() + "," + "REST" + "," + "A" + epicHero.getOrderNumberInTeam() + "$";
			}
			
			//On enlève le dernier '$'
			if (move != null && move.length() > 0) {
				move = move.substring(0, move.length()-1);
			    }
			
			String answer = this.apiCaller.move(this.idPartie, this.idEquipePartie, move);
			System.out.println("Move sent   : " + move);		
		}
	}

	public void draw() {
		//Phase d'initialisation des heroes
		if (this.boardPartie.getNbrTurnLeft() > 50){
			//Créer un prêtre, orc, guarde sur les trois premiers tours
			composeTeam(this.boardPartie.getNbrTurnLeft());
					
		//Action durant la partie	
		} else {
			//On récupère notre team
			EpicHeroesLeague team = this.boardPartie.getOurTeam();
			//Les heroes encore en vies
			ArrayList<EpicHero> teamHeroes = this.getAliveHeroes(team.getFighters());
			
			//On récupère la team ennemie 
			EpicHeroesLeague teamEnnemie = this.boardPartie.getOurTeam();
			//Les heroes encore en vies
			ArrayList<EpicHero> ennemieTeamHeroes = this.getAliveHeroes(teamEnnemie.getFighters());
			
			//Déclare le mouvement
			String move = "";
			
			//Pour tous les héroes de notre équipe encore en vie 
			for (EpicHero epicHero : teamHeroes) {
				//Le hero est un prêtre
				if(epicHero.getFighterClass().equals("PRIEST")){
							
					//Le pretre à assez de mana pour heal quelqu'un
					if (epicHero.getCurrentMana()>1){
						boolean heal = false; //Pour savoir s'il a heal quelqu'un ou non
						//On vérifie si quelqu'un doit être heal
						for (EpicHero heroToHeal : teamHeroes) {
							if(heroToHeal.getCurrentLife() <= heroToHeal.getMaxAvailableLife()-4){
								//Un allié doit être heal
								move += "A" + epicHero.getOrderNumberInTeam() + "," + "HEAL" + "," + "A" + heroToHeal.getOrderNumberInTeam() + "$";
								heal = true;
								System.out.println("Le prête fait un heal !");
								//break;
							}
						}
						if (!heal){
							//Personne n'a été heal
							if (epicHero.getCurrentMana()<epicHero.getMaxAvalaibleMana()){
								//Le prêtre peut se reposer pour récupérer de la mana
								move += "A" + epicHero.getOrderNumberInTeam() + "," + "REST" + "," + "A" + epicHero.getOrderNumberInTeam() + "$";
							} else {
								//Random attack 
								move += "A" + epicHero.getOrderNumberInTeam() + "," + "ATTACK" + "," + "E" + ennemieTeamHeroes.get(0).getOrderNumberInTeam() + "$";
							}
						}
					} else {
						//Le prêtre n'a pas assez de mana -> repos 
						move += "A" + epicHero.getOrderNumberInTeam() + "," + "REST" + "," + "A" + epicHero.getOrderNumberInTeam() + "$";
					}
					
					
				} else {
					//Le hero est un orc ou un garde
					if(epicHero.getFighterClass().equals("ORC") || epicHero.getFighterClass().equals("GUARD")){
						boolean priestAttacked = false;
						for (EpicHero heroToAttack : ennemieTeamHeroes) {
							//Recherche si l'équipe adverse à un prêtre, et le focus
							if (heroToAttack.getFighterClass().equals("PRIEST")){
								move += "A" + epicHero.getOrderNumberInTeam() + "," + "ATTACK" + "," + "E" + heroToAttack.getOrderNumberInTeam() + "$";
								priestAttacked = true;
								break;
							}
						}
						if (!priestAttacked){
							//Random attack
							move += "A" + epicHero.getOrderNumberInTeam() + "," + "ATTACK" + "," + "E" + ennemieTeamHeroes.get(0).getOrderNumberInTeam() + "$";
						}						
					}
				}
			}
		
			//On enlève le dernier '$'
			if (move != null && move.length() > 0) {
				move = move.substring(0, move.length()-1);
		    }
			
			String answer = this.apiCaller.move(this.idPartie, this.idEquipePartie, move);
			System.out.println("Move sent   : " + move);
		}	
	}
	
	
	public void strategie() {
		//Phase d'initialisation des heroes
		if (this.boardPartie.getNbrTurnLeft() > 50){
			//Créer un prêtre, orc, guarde sur les trois premiers tours
			composeTeam(this.boardPartie.getNbrTurnLeft());
					
		//Action durant la partie	
		} else {
			//On récupère notre team
			EpicHeroesLeague team = this.boardPartie.getOurTeam();
			//Les heroes encore en vies
			ArrayList<EpicHero> teamHeroes = this.getAliveHeroes(team.getFighters());
			
			//On récupère la team ennemie 
			EpicHeroesLeague teamEnnemie = this.boardPartie.getOurTeam();
			//Les heroes encore en vies
			ArrayList<EpicHero> ennemieTeamHeroes = this.getAliveHeroes(teamEnnemie.getFighters());
			
			//Déclare le mouvement
			String move = "";
			
			//Pour tous les héroes de notre équipe encore en vie 
			for (EpicHero epicHero : teamHeroes) {
				//STRATEGIE DEFINISSANT LES ACTIONS DE LA TEAM
				
				//Deux premiers tours, on attaque le prêtre ennemi
				if (this.boardPartie.getNbrTurnLeft() > 48){
					for (EpicHero heroToAttack : ennemieTeamHeroes) {
						//Recherche si l'équipe adverse à un prêtre, et le focus
						if (heroToAttack.getFighterClass().equals("PRIEST")){
							
							String attaque = "";
							
							if(this.boardPartie.getNbrTurnLeft()%3==0) {
								attaque = "ATTACK";
							} else if (this.boardPartie.getNbrTurnLeft()%3==1) {
								attaque = "HEAL";
							} else {
								attaque = "REST";
							}
							
							System.out.println("Attaque pretre"+attaque);
								
							move += "A" + epicHero.getOrderNumberInTeam() + "," + attaque + "," + "E" + heroToAttack.getOrderNumberInTeam() + "$";
							break;
						}
					}
				} else {
					//Tous les 5 tours
					if (this.boardPartie.getNbrTurnLeft()%5 == 0){
						System.out.println("5 tours");
						boolean first = true;
						EpicHero memberFocused = null;
						for (EpicHero heroFocus : teamHeroes) {
							if (first){
								memberFocused = heroFocus;
								first = false;
							} else {
								if (heroFocus.getCurrentLife() < memberFocused.getCurrentLife()){
									memberFocused = heroFocus;
								}
							}
						}
						//Prêtre heal, guarde protège le focused, orc taunt le prêtre de préférence sinon peu importe
						if (epicHero.getFighterClass().equals("PRIEST")){
							move += "A" + epicHero.getOrderNumberInTeam() + "," + "HEAL" + "," + "A" + memberFocused.getOrderNumberInTeam() + "$";
						} else {
							if (epicHero.getFighterClass().equals("GUARD")){
								move += "A" + epicHero.getOrderNumberInTeam() + "," + "PROTECT" + "," + "A" + memberFocused.getOrderNumberInTeam() + "$";
							} else {
								boolean priestFound = false;
								for (EpicHero heroToAttack : ennemieTeamHeroes) {
									//Recherche si l'équipe adverse à un prêtre, et le focus
									if (heroToAttack.getFighterClass().equals("PRIEST")){
										move += "A" + epicHero.getOrderNumberInTeam() + "," + "YELL" + "," + "E" + heroToAttack.getOrderNumberInTeam() + "$";
										priestFound = true;
										break;
									}
								}
								if (!priestFound){
									move += "A" + epicHero.getOrderNumberInTeam() + "," + "YELL" + "," + "E" + ennemieTeamHeroes.get(0).getOrderNumberInTeam() + "$";
								}
							}
						}
					} else {
						//Tous les 3 tours?
						if (this.boardPartie.getNbrTurnLeft()%3 == 1){
							System.out.println("3 tours");
							move += "A" + epicHero.getOrderNumberInTeam() + "," + "REST" + "," + "A" + epicHero.getOrderNumberInTeam() + "$";
						} else {
							//Autres tours 
							System.out.println("autres");
							boolean priestFound = false;
							for (EpicHero heroToAttack : ennemieTeamHeroes) {
								//Recherche si l'équipe adverse à un prêtre, et le focus
								if (heroToAttack.getFighterClass().equals("PRIEST")){
									move += "A" + epicHero.getOrderNumberInTeam() + "," + "ATTACK" + "," + "E" + heroToAttack.getOrderNumberInTeam() + "$";
									priestFound = true;
									break;
								}
							}
							if (!priestFound){
								move += "A" + epicHero.getOrderNumberInTeam() + "," + "ATTACK" + "," + "E" + ennemieTeamHeroes.get(0).getOrderNumberInTeam() + "$";
							}
						}
					}
				}
			}
		
			//On enlève le dernier '$'
			if (move != null && move.length() > 0) {
				move = move.substring(0, move.length()-1);
		    }
			
			System.out.println("Move sent   : " + move);
			this.apiCaller.move(this.idPartie, this.idEquipePartie, move);
			
		}
	}
	
	public void strategieLaurie() {
		//Phase d'initialisation des heroes
				if (this.boardPartie.getNbrTurnLeft() > 50){
					//Créer un prêtre, orc, guarde sur les trois premiers tours
					composeTeam(this.boardPartie.getNbrTurnLeft());
							
				//Action durant la partie	
				} else {
					//On récupère notre team
					EpicHeroesLeague team = this.boardPartie.getOurTeam();
					//Les heroes encore en vies
					ArrayList<EpicHero> teamHeroes = this.getAliveHeroes(team.getFighters());
					
					
					//On récupère la team ennemie 
					EpicHeroesLeague teamEnnemie = this.boardPartie.getEnnemieTeam();
					//Les heroes encore en vies
					ArrayList<EpicHero> ennemieTeamHeroes = this.getAliveHeroes(teamEnnemie.getFighters());
					
					//Déclare le mouvement
					String move = "";
					int needHeal = 0;
					int minlife = 0;
					//Pour tous les héroes de notre équipe encore en vie 
					for (EpicHero epicHero : teamHeroes) {
						//Le hero est un prêtre
						if(epicHero.getFighterClass().equals("PRIEST")){
									
							//Le pretre à assez de mana pour heal quelqu'un
							if (boardPartie.getNbrTurnLeft()%5==0){
								boolean priestAttacked = false;
								int minlifeEnnemi = 0;
								int noToAttack=0;

								for (EpicHero heroToAttack : ennemieTeamHeroes) {
									
									//Recherche si l'équipe adverse à un prêtre, et le focus
									if (heroToAttack.getFighterClass().equals("PRIEST")){
										move += "A" + epicHero.getOrderNumberInTeam() + "," + "ATTACK" + "," + "E" + heroToAttack.getOrderNumberInTeam() + "$";
										priestAttacked = true;
										break;
									} else {
										if (minlifeEnnemi==0 || heroToAttack.getCurrentLife() < minlifeEnnemi) {
											minlifeEnnemi = heroToAttack.getCurrentLife();
											noToAttack = heroToAttack.getOrderNumberInTeam();
										}
									}
								}
								//Random attack 
								move += "A" + epicHero.getOrderNumberInTeam() + "," + "ATTACK" + "," + "E" + noToAttack + "$";
							
							} else if(boardPartie.getNbrTurnLeft()%5==4 || boardPartie.getNbrTurnLeft()%5==2) {
							
								move += "A" + epicHero.getOrderNumberInTeam() + "," + "REST" + "," + "A" + epicHero.getOrderNumberInTeam() + "$";
							
							} else if(boardPartie.getNbrTurnLeft()%5==3|| boardPartie.getNbrTurnLeft()%5==1) {
								System.out.println("Je rentre dans heal");
								boolean heal = false; //Pour savoir s'il a heal quelqu'un ou non
								//On vérifie si quelqu'un doit être heal
								for (EpicHero heroToHeal : teamHeroes) {
									if (minlife==0 || heroToHeal.getCurrentLife() < minlife) {
										minlife = heroToHeal.getCurrentLife();
										needHeal = heroToHeal.getOrderNumberInTeam();
										
									}
									System.out.println("Type du héros : "+heroToHeal.getFighterClass());
									System.out.println("Current life du hero"+heroToHeal.getCurrentLife());
									System.out.println("Max availablelife"+heroToHeal.getMaxAvailableLife());
										//Un allié doit être heal
										heal = true;
										System.out.println("Le prêtre fait un heal !");
										//break;
								}
								
								
								if (!heal){
									//Personne n'a été heal
									if (epicHero.getCurrentMana()<epicHero.getMaxAvalaibleMana()){
										//Le prêtre peut se reposer pour récupérer de la mana
										move += "A" + epicHero.getOrderNumberInTeam() + "," + "REST" + "," + "A" + epicHero.getOrderNumberInTeam() + "$";
									} else {
										//Random attack 
										move += "A" + epicHero.getOrderNumberInTeam() + "," + "ATTACK" + "," + "E" + ennemieTeamHeroes.get(0).getOrderNumberInTeam() + "$";
									}
								} else {
									move += "A" + epicHero.getOrderNumberInTeam() + "," + "HEAL" + "," + "A" + needHeal + "$";

								}
								
							} else {
								//Le prêtre n'a pas assez de mana -> repos 
								move += "A" + epicHero.getOrderNumberInTeam() + "," + "REST" + "," + "A" + epicHero.getOrderNumberInTeam() + "$";
							}
							
							
						} else {
							int minlifeEnnemi = 0;
							
							int noToAttack=0;
							//Le hero est un orc ou un garde
							if(epicHero.getFighterClass().equals("ORC") || epicHero.getFighterClass().equals("GUARD")){
								boolean priestAttacked = false;
								for (EpicHero heroToAttack : ennemieTeamHeroes) {
									//Recherche si l'équipe adverse à un prêtre, et le focus
									if (heroToAttack.getFighterClass().equals("PRIEST")){
										move += "A" + epicHero.getOrderNumberInTeam() + "," + "ATTACK" + "," + "E" + heroToAttack.getOrderNumberInTeam() + "$";
										priestAttacked = true;
										break;
									} else {
										if (minlifeEnnemi==0 || heroToAttack.getCurrentLife() < minlifeEnnemi) {
											minlifeEnnemi = heroToAttack.getCurrentLife();
											noToAttack = heroToAttack.getOrderNumberInTeam();
										}
									}
								}
								if (!priestAttacked){
									//Il faut attaquer le héro qui a le moins de vies et qui n'est pas mort
									move += "A" + epicHero.getOrderNumberInTeam() + "," + "ATTACK" + "," + "E" + noToAttack + "$";
											
								}
									
													
							}
						}
					}
				
					//On enlève le dernier '$'
					if (move != null && move.length() > 0) {
						move = move.substring(0, move.length()-1);
				    }
					
					String answer = this.apiCaller.move(this.idPartie, this.idEquipePartie, move);
					System.out.println("Move sent   : " + move);
				}	
	}
	 
}
