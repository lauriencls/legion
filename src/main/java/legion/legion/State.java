package legion.legion;

public class State {
	private String type;
	private int remainingDuration;
	
	
	public State(String type, int remainingDuration) {
		super();
		this.type = type;
		this.remainingDuration = remainingDuration;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public int getRemainingDuration() {
		return remainingDuration;
	}


	public void setRemainingDuration(int remainingDuration) {
		this.remainingDuration = remainingDuration;
	}
	
}

