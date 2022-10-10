package tk.bolaodacopa.bets.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.bolaodacopa.bets.models.Account;
import tk.bolaodacopa.bets.models.Bet;
import tk.bolaodacopa.bets.models.Match;
import tk.bolaodacopa.bets.payload.request.BetCreateRequest;
import tk.bolaodacopa.bets.payload.request.BetUpdateRequest;
import tk.bolaodacopa.bets.repository.AccountRepository;
import tk.bolaodacopa.bets.repository.BetRepository;
import tk.bolaodacopa.bets.repository.MatchRepository;

@Service
public class BetService {

	private final BetRepository betRepository;
	private final AccountRepository accountRepository;
	private final MatchRepository matchRepository;

	@Autowired
	public BetService(BetRepository betRepository,
			AccountRepository accountRepository,
			MatchRepository matchRepository) {
		super();
		this.betRepository = betRepository;
		this.accountRepository = accountRepository;
		this.matchRepository = matchRepository;
	}

	public List<Bet> addAll(String username, List<BetCreateRequest> bets) {
		String hometeamresult;
		String awayteamresult;
		List<Bet> listBets = new ArrayList<Bet>();

		Account account = accountRepository.findByUsername(username)
				.orElseThrow(() -> new RuntimeException("Erro: Usuário não encontrado: " + username));

		if(bets.stream().map((BetCreateRequest bet) -> bet.getMatchcode()).distinct().count() != bets.size())
			throw new RuntimeException("Erro: Requisição de aposta duplicada");

		for(BetCreateRequest request : bets) {			
			if(betRepository.findByAccountUsernameAndMatchMatchcode(username, request.getMatchcode()).isPresent())
				throw new RuntimeException("Erro: Já existe uma aposta cadastrada com o código: " + request.getMatchcode());

			Match match = matchRepository.findByMatchcode(request.getMatchcode())
					.orElseThrow(() -> new RuntimeException("Erro: Partida não encontrada: " + request.getMatchcode()));	

			if("SIM".equals(match.getFinishedmatch()))
				throw new RuntimeException("Erro: Partida finalizada: " + request.getMatchcode());

			if("SIM".equals(match.getStage().getFinishedbets()))
				throw new RuntimeException("Erro: Fase finalizada para apostas: " + match.getStage().getName());

			if (request.getHometeamgoals() > request.getAwayteamgoals()) {
				hometeamresult = "VITORIA";
				awayteamresult = "DERROTA";			
			} else if (request.getHometeamgoals() < request.getAwayteamgoals()) {
				hometeamresult = "DERROTA";
				awayteamresult = "VITORIA";			
			} else {
				hometeamresult = "EMPATE";
				awayteamresult = "EMPATE";			
			}			

			Bet bet = new Bet(account, match, request.getHometeamgoals(), request.getAwayteamgoals(), 
					hometeamresult,awayteamresult);

			listBets.add(bet);
		}

		return betRepository.saveAll(listBets);
	}

	public List<Bet> findAllByUsername(String username) {
		return betRepository.findAllByAccountUsername(username);
	}

	public Bet updateBet(String username, String matchcode, BetUpdateRequest betUpdateRequest) {
		String hometeamresult;
		String awayteamresult;

		Bet bet = betRepository.findByAccountUsernameAndMatchMatchcode(username, matchcode)
				.orElseThrow(() -> new RuntimeException("Erro: Aposta não encontrada: " + matchcode));

		if("SIM".equals(bet.getMatch().getFinishedmatch()))
			throw new RuntimeException("Erro: Partida finalizada: " + bet.getMatch().getMatchcode());		

		if("SIM".equals(bet.getMatch().getStage().getFinishedbets()))
			throw new RuntimeException("Erro: Fase finalizada para apostas: " + bet.getMatch().getStage().getName());		

		if (betUpdateRequest.getHometeamgoals() > betUpdateRequest.getAwayteamgoals()) {
			hometeamresult = "VITORIA";
			awayteamresult = "DERROTA";			
		} else if (betUpdateRequest.getHometeamgoals() < betUpdateRequest.getAwayteamgoals()) {
			hometeamresult = "DERROTA";
			awayteamresult = "VITORIA";			
		} else {
			hometeamresult = "EMPATE";
			awayteamresult = "EMPATE";			
		}

		bet.setHometeamgoals(betUpdateRequest.getHometeamgoals());
		bet.setAwayteamgoals(betUpdateRequest.getAwayteamgoals());
		bet.setHometeamresult(hometeamresult);
		bet.setAwayteamresult(awayteamresult);

		return betRepository.save(bet);
	}	

}