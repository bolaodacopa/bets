package tk.bolaodacopa.bets.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import tk.bolaodacopa.bets.models.Bet;
import tk.bolaodacopa.bets.payload.request.BetCreateRequest;
import tk.bolaodacopa.bets.payload.request.BetUpdateRequest;
import tk.bolaodacopa.bets.payload.response.BetMatchResponseDTO;
import tk.bolaodacopa.bets.services.BetService;

@RestController
@RequestMapping(path = "/api/bets")
public class BetController {

	private final BetService service;

	@Autowired
	public BetController(BetService service) {
		super();
		this.service = service;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public List<Bet> addAll(@RequestHeader("x-token-subject") String username, 
			@RequestBody @NotEmpty(message = "Lista de apostas não pode ser nula.") List<@Valid BetCreateRequest> bets) {
		return this.service.addAll(username, bets);
	}	
	
    @PutMapping(path = {"/{matchcode}"})
    @ResponseStatus(HttpStatus.OK)
    public Bet updateOneBet(@RequestHeader("x-token-subject") String username,
    		@PathVariable("matchcode") String matchcode,
    		@RequestBody @NotEmpty(message = "Aposta não pode ser nula.") @Valid BetUpdateRequest bet) {
    	return this.service.updateBet(username, matchcode, bet);
    }	

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Bet> findAllByUsername(@RequestHeader("x-token-subject") String username,
			@RequestParam Map<String,String> allParams) {
		return this.service.findAllByUsernameRequestParam(username, allParams);
	}

	@GetMapping(path = {"/matches"})
	@ResponseStatus(HttpStatus.OK)
	public List<BetMatchResponseDTO> findAllBetsAndMatches(@RequestHeader("x-token-subject") String username,
			@RequestParam Map<String,String> allParams) {
		return this.service.findAllBetsAndMatches(username, allParams);
	}
	
	
}
