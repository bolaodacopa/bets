package tk.bolaodacopa.bets.services;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.bolaodacopa.bets.models.Stage;
import tk.bolaodacopa.bets.payload.request.StageUpdateRequest;
import tk.bolaodacopa.bets.repository.StageRepository;

@Service
public class StageService {

	private final StageRepository stageRepository;

	@Autowired
	public StageService(StageRepository stageRepository) {
		super();
		this.stageRepository = stageRepository;
	}

	public List<Stage> addAll(@NotEmpty(message = "Lista de fase não pode ser nula.") List<Stage> stages) {		
		return stageRepository.saveAll(stages);
	}

	public List<Stage> findAll() {
		return stageRepository.findAll();
	}

	public Stage updateStage(String name,StageUpdateRequest stageUpdateRequest) {
		Stage stage = stageRepository.findByName(name)
				.orElseThrow(() -> new RuntimeException("Erro: Fase não encontrada: " + name));		

		if("SIM".equals(stageUpdateRequest.getFinishedbets())) {
			stage.setFinishedbets("SIM");
		} else {
			stage.setFinishedbets(null);
		}

		return stageRepository.save(stage);
	}

}
