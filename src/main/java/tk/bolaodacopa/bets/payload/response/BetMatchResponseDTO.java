package tk.bolaodacopa.bets.payload.response;

import tk.bolaodacopa.bets.models.Account;
import tk.bolaodacopa.bets.models.Bet;
import tk.bolaodacopa.bets.models.Match;

public class BetMatchResponseDTO {

	private Account account;

	private Match match;

	private BetResponse bet;

	public BetMatchResponseDTO() {

	}
	
	public BetMatchResponseDTO(Account account, Match match, BetResponse bet) {
		super();
		this.account = account;
		this.match = match;
		this.bet = bet;
	}
	
	public BetMatchResponseDTO(Account account, Match match, Bet bet) {
		super();
		this.account = account;
		this.match = match;
		if(bet != null)
			this.bet = new BetResponse(bet);
	}
	
	public BetMatchResponseDTO(Account account, Match match) {
		super();
		this.account = account;
		this.match = match;
	}	

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	public BetResponse getBet() {
		return bet;
	}

	public void setBet(BetResponse bet) {
		this.bet = bet;
	}

	public String getResult() {
		String result = null;

		if((match != null && match.getFinishedmatch() != null)
				&& (bet != null && bet.getId() != null)) {
			result = "ERROU";
			if((bet.getHometeamgoals() == match.getHometeamgoals())
					&& (bet.getAwayteamgoals() == match.getAwayteamgoals()))
				result = "PLACAR";
			else if ((bet.getHometeamresult().equals(match.getHometeamresult()))
					&& (bet.getAwayteamresult().equals(match.getAwayteamresult())))
				result = "RESULTADO";
		}

		return result;
	}
}
