package tk.bolaodacopa.bets.payload.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class BetUpdateRequest {

	@NotNull
	@Min(0)
	private Integer hometeamgoals;

	@NotNull
	@Min(0)
	private Integer awayteamgoals;

	public BetUpdateRequest(@NotNull @Min(0) Integer hometeamgoals, @NotNull @Min(0) Integer awayteamgoals) {
		super();
		this.hometeamgoals = hometeamgoals;
		this.awayteamgoals = awayteamgoals;
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
	
	
	
	
	
}
