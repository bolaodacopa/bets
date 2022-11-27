package tk.bolaodacopa.bets.payload.response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import tk.bolaodacopa.bets.models.Bet;
import tk.bolaodacopa.bets.models.Match;

public class BetsByMatchResponseDTO {

	private Match match;

	private List<BetByMatchResponse> bets;

	public BetsByMatchResponseDTO(Match match, List<Bet> listBets) {
		super();
		this.match = match;
		this.bets = new ArrayList<BetByMatchResponse>();
		for(Bet bet : listBets) {
			BetByMatchResponse betByMatchResponse = new BetByMatchResponse(bet.getAccount().getUsername()
					, bet.getAccount().getName(), bet.getHometeamgoals(), bet.getAwayteamgoals(),
					bet.getHometeamresult(), bet.getAwayteamresult(), getResult(bet));
			this.bets.add(betByMatchResponse);
		}

		Collections.sort(this.bets, (b1, b2) -> {
			int compareTo = 0;
			if(b1.getResult() != b2.getResult()) {
				if("PLACAR".equals(b1.getResult()))
					compareTo = -1;
				else if("PLACAR".equals(b2.getResult()))
					compareTo = 1;
				else if("RESULTADO".equals(b1.getResult()))
					compareTo = -1;
				else if("RESULTADO".equals(b2.getResult()))
					compareTo = 1;				
			}
			return compareTo;
		});
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	public List<BetByMatchResponse> getBets() {
		return bets;
	}

	public void setBets(List<BetByMatchResponse> bets) {
		this.bets = bets;
	}

	private String getResult(Bet bet) {
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
