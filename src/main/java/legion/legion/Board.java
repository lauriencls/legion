package legion.legion;

public class Board {
	private EpicHeroesLeague playerBoards;
	private int nbTurnsLeft;
	
	
	public Board(EpicHeroesLeague playerBoards, int nbrTurnLeft) {
		super();
		this.playerBoards = playerBoards;
		this.nbTurnsLeft = nbrTurnLeft;
	}


	public EpicHeroesLeague getPlayerBoards() {
		return playerBoards;
	}


	public void setPlayerBoards(EpicHeroesLeague playerBoards) {
		this.playerBoards = playerBoards;
	}


	public int getNbrTurnLeft() {
		return nbTurnsLeft;
	}


	public void setNbTurnsLeft(int nbTurnsLeft) {
		this.nbTurnsLeft = nbTurnsLeft;
	}
	
}
