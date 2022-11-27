package tk.bolaodacopa.bets.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tk.bolaodacopa.bets.models.Bet;
import tk.bolaodacopa.bets.payload.response.RankingDTO;

@Repository
public interface BetRepository extends JpaRepository<Bet, Long>  {
	
	List<Bet> findAllByAccountUsername(String username);
	
	Optional<Bet> findByAccountUsernameAndMatchMatchcode(String username, String matchcode);

	List<Bet> findAllByAccountUsernameAndMatchMatchgroupAndMatchStageName(String username, String group, String stage);

	List<Bet> findAllByAccountUsernameAndMatchStageName(String username, String stage);

	List<Bet> findAllByAccountUsernameAndMatchMatchgroup(String username, String group);
	
	List<Bet> findAllByMatchMatchcode(String matchcode);
	
/*
    @Query(value = "select "
    		+ " new tk.bolaodacopa.bets.payload.response.RankingDTO(TEMP.username, TEMP.name, TEMP.total, Integer correctmatches, rank() over (order by TEMP.total desc, TEMP.correctmatches desc))"
    		+ "from"
    		+ "("
    		+ "	select"
    		+ "		A.username"
    		+ "		,A.name"
    		+ "		, sum(case"
    		+ "	            	when B.hometeamgoals = M.hometeamgoals and B.awayteamgoals = M.awayteamgoals then 5"
    		+ "	            	when B.hometeamresult = M.hometeamresult and B.awayteamresult = M.awayteamresult then 3"
    		+ "	            	else 0"
    		+ "	      	end) as 'total'"
    		+ "		, sum(case "
    		+ "	            	when B.hometeamgoals = M.hometeamgoals and B.awayteamgoals = M.awayteamgoals then 1"
    		+ "	            	else 0"
    		+ "	      	end) as 'correctmatches'"
    		+ "	from bets B"
    		+ "	left join matches M on (B.match_id = M.id)"
    		+ "	left join accounts A on (B.account_id = A.id)"
    		+ "	group by A.username, A.name"
    		+ "	order by total desc, correctmatches desc, A.username"
    		+ ") TEMP", nativeQuery = true)
*/    		    		
/*	
	@Query("select "
			+ " new tk.bolaodacopa.bets.payload.response.RankingDTO(username, name, 0, 0, 0)"
			+ " from Account"
			)
*/
	
	
	@Query(value = "call ranking();", nativeQuery = true)
    List<RankingDTO> generateRanking();
}
