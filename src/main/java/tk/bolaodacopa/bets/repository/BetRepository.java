package tk.bolaodacopa.bets.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tk.bolaodacopa.bets.models.Bet;

@Repository
public interface BetRepository extends JpaRepository<Bet, Long>  {
	
	List<Bet> findAllByAccountUsername(String username);
	
	Optional<Bet> findByAccountUsernameAndMatchMatchcode(String username, String matchcode);

}
