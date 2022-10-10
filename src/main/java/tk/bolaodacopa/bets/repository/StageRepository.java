package tk.bolaodacopa.bets.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tk.bolaodacopa.bets.models.Stage;

@Repository
public interface StageRepository extends JpaRepository<Stage, Long> {
	
	Optional<Stage> findByName(String name);	

}
