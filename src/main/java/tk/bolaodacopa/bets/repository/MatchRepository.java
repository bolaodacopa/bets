package tk.bolaodacopa.bets.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tk.bolaodacopa.bets.models.Match;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {	
	
	Optional<Match> findByMatchcode(String matchcode);
	List<Match> findAllByStageName(String stage);
	List<Match> findAllByMatchgroup(String group);
	List<Match> findAllByMatchgroupAndStageName(String group, String stage);	

}
