package tk.bolaodacopa.bets.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tk.bolaodacopa.bets.payload.response.RankingDTO;
import tk.bolaodacopa.bets.services.BetService;

@RestController
@RequestMapping(path = "/api/ranking")
public class RankingController {
	
	private final BetService service;

	@Autowired
	public RankingController(BetService service) {
		super();
		this.service = service;
	}
	
	@GetMapping
	public List<RankingDTO> generateRanking(@RequestHeader("x-token-subject") String username) {
		return this.service.generateRanking();
	}	

}
