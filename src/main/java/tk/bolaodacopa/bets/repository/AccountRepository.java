package tk.bolaodacopa.bets.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tk.bolaodacopa.bets.models.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	
  Optional<Account> findByUsername(String username);

}
