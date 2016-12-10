package legion.legion;

public class Board {
	private EpicHeroesLeague[] playerBoards = new EpicHeroesLeague[1];
	private int nbrTurnLeft;
	
	
	public Board(EpicHeroesLeague[] playerBoards, int nbrTurnLeft) {
		super();
		this.playerBoards = playerBoards;
		this.nbrTurnLeft = nbrTurnLeft;
	}


	public EpicHeroesLeague[] getPlayerBoards() {
		return playerBoards;
	}


	public void setPlayerBoards(EpicHeroesLeague[] playerBoards) {
		this.playerBoards = playerBoards;
	}


	public int getNbrTurnLeft() {
		return nbrTurnLeft;
	}


	public void setNbrTurnLeft(int nbrTurnLeft) {
		this.nbrTurnLeft = nbrTurnLeft;
	}
	
}
