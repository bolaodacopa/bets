package tk.bolaodacopa.bets.payload.response;

import tk.bolaodacopa.bets.models.Bet;

public class BetResponse {

	private Long id;	

	private Integer hometeamgoals;

	private Integer awayteamgoals;

	private String hometeamresult;

	private String awayteamresult;

	public BetResponse() {

	}

	public BetResponse(Long id, Integer hometeamgoals, Integer awayteamgoals, String hometeamresult,
			String awayteamresult) {
		super();
		this.id = id;
		this.hometeamgoals = hometeamgoals;
		this.awayteamgoals = awayteamgoals;
		this.hometeamresult = hometeamresult;
		this.awayteamresult = awayteamresult;
	}

	public BetResponse(Bet bet) {
		super();
		this.id = bet.getId();
		this.hometeamgoals = bet.getHometeamgoals();
		this.awayteamgoals = bet.getAwayteamgoals();
		this.hometeamresult = bet.getHometeamresult();
		this.awayteamresult = bet.getAwayteamresult();
	}	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getHometeamgoals() {
		return hometeamgoals;
	}

	public void setHometeamgoals(Integer hometeamgoals) {
		this.hometeamgoals = hometeamgoals;
	}

	public Integer getAwayteamgoals() {
		return awayteamgoals;
	}

	public void setAwayteamgoals(Integer awayteamgoals) {
		this.awayteamgoals = awayteamgoals;
	}

	public String getHometeamresult() {
		return hometeamresult;
	}

	public void setHometeamresult(String hometeamresult) {
		this.hometeamresult = hometeamresult;
	}

	public String getAwayteamresult() {
		return awayteamresult;
	}

	public void setAwayteamresult(String awayteamresult) {
		this.awayteamresult = awayteamresult;
	}	

}
