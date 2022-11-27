package tk.bolaodacopa.bets.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.bolaodacopa.bets.models.Account;
import tk.bolaodacopa.bets.models.Bet;
import tk.bolaodacopa.bets.models.Match;
import tk.bolaodacopa.bets.payload.request.BetCreateRequest;
import tk.bolaodacopa.bets.payload.request.BetUpdateRequest;
import tk.bolaodacopa.bets.payload.response.BetMatchResponseDTO;
import tk.bolaodacopa.bets.payload.response.BetsByMatchResponseDTO;
import tk.bolaodacopa.bets.payload.response.RankingDTO;
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
			Bet bet = null;
			//if(betRepository.findByAccountUsernameAndMatchMatchcode(username, request.getMatchcode()).isPresent())
			//	throw new RuntimeException("Erro: Já existe uma aposta cadastrada com o código: " + request.getMatchcode());

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

			Optional<Bet> optionalBet = betRepository.findByAccountUsernameAndMatchMatchcode(username, request.getMatchcode());
			if(optionalBet.isEmpty()) {
				bet = new Bet(account, match, request.getHometeamgoals(), request.getAwayteamgoals(), 
						hometeamresult,awayteamresult);				
			} else {
				bet = optionalBet.get();
				bet.setHometeamgoals(request.getHometeamgoals());
				bet.setAwayteamgoals(request.getAwayteamgoals());
				bet.setHometeamresult(hometeamresult);
				bet.setAwayteamresult(awayteamresult);
			}

			listBets.add(bet);
		}

		return betRepository.saveAll(listBets);
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

	public List<Bet> findAllByUsernameRequestParam(String username, Map<String, String> allParams) {
		List<Bet> bets = new ArrayList<Bet>();
		String stage = allParams.getOrDefault("stage", null);
		String group = allParams.getOrDefault("group", null);

		if((stage != null) && (group != null)) {
			bets = betRepository.findAllByAccountUsernameAndMatchMatchgroupAndMatchStageName(username, group, stage);
		} else if((stage != null)) {
			bets = betRepository.findAllByAccountUsernameAndMatchStageName(username, stage);
		} else if((group != null)) {
			bets = betRepository.findAllByAccountUsernameAndMatchMatchgroup(username, group);
		} else {
			bets = betRepository.findAllByAccountUsername(username);
		}		

		return bets;
	}

	public List<BetMatchResponseDTO> findAllBetsAndMatches(String username, Map<String, String> allParams) {
		Account account = null;
		List<BetMatchResponseDTO> listaBetsMatches = new ArrayList<BetMatchResponseDTO>();
		List<Match> matches = new ArrayList<Match>();
		String stage = allParams.getOrDefault("stage", null);
		String group = allParams.getOrDefault("group", null);
		String paramusername = allParams.getOrDefault("username", null);

		if(paramusername == null) {
			account = accountRepository.findByUsername(username)
					.orElseThrow(() -> new RuntimeException("Erro: Usuário não encontrado: " + username));		

			if((stage != null) && (group != null)) {
				matches = matchRepository.findAllByMatchgroupAndStageName(group, stage);
			} else if((stage != null)) {
				matches = matchRepository.findAllByStageName(stage);
			} else if((group != null)) {
				matches = matchRepository.findAllByMatchgroup(group);
			} else {
				matches = matchRepository.findAll();
			}		
		} else {
			account = accountRepository.findByUsername(paramusername)
					.orElseThrow(() -> new RuntimeException("Erro: Usuário não encontrado: " + paramusername));
			
			matches = matchRepository.findAllByStageFinishedbets("SIM");
		}

		for(Match match : matches) {
			BetMatchResponseDTO betMatchResponseDTO = null;

			Optional<Bet> bet = betRepository.findByAccountUsernameAndMatchMatchcode(((paramusername == null)?username:paramusername), match.getMatchcode());
			if(bet.isPresent()) {
				betMatchResponseDTO = new BetMatchResponseDTO(account, match, bet.get());
			} else {
				betMatchResponseDTO = new BetMatchResponseDTO(account, match);
			}

			listaBetsMatches.add(betMatchResponseDTO);			
		}

		return listaBetsMatches;
	}	

	public BetsByMatchResponseDTO findAllBetsByMatch(String username, String matchcode,
			Map<String, String> allParams) {

		Match match = matchRepository.findByMatchcode(matchcode)
				.orElseThrow(() -> new RuntimeException("Partida não encontrada: " + matchcode));
		
		if(!"SIM".equals(match.getStage().getFinishedbets()))
			throw new RuntimeException("Fase não finalizada para apostas: " + match.getStage().getName());
		
		List<Bet> listBets = betRepository.findAllByMatchMatchcode(matchcode);
		
		return new BetsByMatchResponseDTO(match, listBets);
	}	

	public List<RankingDTO> generateRanking() {
		return betRepository.generateRanking();
	}
	
}
