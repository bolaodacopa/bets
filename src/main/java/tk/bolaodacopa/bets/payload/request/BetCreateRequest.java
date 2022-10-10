package tk.bolaodacopa.bets.payload.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BetCreateRequest {
	
	@NotBlank
	@Size(max = 20)
	private String matchcode;
	
	@NotNull
	@Min(0)
	private Integer hometeamgoals;

	@NotNull
	@Min(0)
	private Integer awayteamgoals;

	
	
	public BetCreateRequest(@NotBlank @Size(max = 20) String matchcode, @NotNull @Min(0) Integer hometeamgoals,
			@NotNull @Min(0) Integer awayteamgoals) {
		super();
		this.matchcode = matchcode;
		this.hometeamgoals = hometeamgoals;
		this.awayteamgoals = awayteamgoals;
	}

	public String getMatchcode() {
		return matchcode;
	}

	public void setMatchcode(String matchcode) {
		this.matchcode = matchcode;
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
