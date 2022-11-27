package tk.bolaodacopa.bets.payload.response;


public class BetByMatchResponse {

	private String username;

	private String name;  

	private Integer hometeamgoals;

	private Integer awayteamgoals;

	private String hometeamresult;

	private String awayteamresult;
	
	private String result;

	public BetByMatchResponse(String username, String name, Integer hometeamgoals, Integer awayteamgoals,
			String hometeamresult, String awayteamresult, String result) {
		super();
		this.username = username;
		this.name = name;
		this.hometeamgoals = hometeamgoals;
		this.awayteamgoals = awayteamgoals;
		this.hometeamresult = hometeamresult;
		this.awayteamresult = awayteamresult;
		this.result = result;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}	

}
