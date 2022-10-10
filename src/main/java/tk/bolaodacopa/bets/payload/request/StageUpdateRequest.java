package tk.bolaodacopa.bets.payload.request;

public class StageUpdateRequest {
	
	private String finishedbets;
	
	public StageUpdateRequest() {
		super();
	}

	public StageUpdateRequest(String finishedbets) {
		super();
		this.finishedbets = finishedbets;
	}

	public String getFinishedbets() {
		return finishedbets;
	}

	public void setFinishedbets(String finishedbets) {
		this.finishedbets = finishedbets;
	}
		

}
