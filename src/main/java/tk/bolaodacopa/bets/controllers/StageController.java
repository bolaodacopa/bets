package tk.bolaodacopa.bets.controllers;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import tk.bolaodacopa.bets.models.Stage;
import tk.bolaodacopa.bets.payload.request.StageUpdateRequest;
import tk.bolaodacopa.bets.services.StageService;

@RestController
@RequestMapping(path = "/api/stages")
public class StageController {

	private final StageService service;

	@Autowired
	public StageController(StageService service) {
		super();
		this.service = service;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public List<Stage> addAll(@RequestHeader("x-token-subject") String username, 
			@RequestBody @NotEmpty(message = "Lista de fase não pode ser nula.") List<Stage> stages) {
		return this.service.addAll(stages);
	}

	@PutMapping(path = {"/{name}"})
	@ResponseStatus(HttpStatus.OK)
	public Stage updateOneStage(@RequestHeader("x-token-subject") String username,
			@PathVariable("name") String name,
			@RequestBody @NotEmpty(message = "Fase não pode ser nula.") @Valid StageUpdateRequest stageUpdateRequest) {
		return this.service.updateStage(name, stageUpdateRequest);
	}	

	@GetMapping
	public List<Stage> findAll(@RequestHeader("x-token-subject") String username) {
		return this.service.findAll();
	}
}
