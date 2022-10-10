package tk.bolaodacopa.bets.models;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "bets",
uniqueConstraints={
	    @UniqueConstraint(columnNames = {"account_id", "match_id"})
	})
public class Bet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	
	@NotNull
	@ManyToOne
	private Account account;
	
	@NotNull
	@ManyToOne
	private Match match;	
	
	@NotNull
	@Min(0)
	private Integer hometeamgoals;

	@NotNull
	@Min(0)
	private Integer awayteamgoals;

	@NotNull
	@Size(max = 20)
	private String hometeamresult;

	@NotNull
	@Size(max = 20)
	private String awayteamresult;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", nullable = false)
	private Date created;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated", nullable = false)
	private Date updated;

	@PrePersist
	protected void onCreate() {
		updated = created = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		updated = new Date();
	}	
	
	public Bet() {
		
	}
	


	public Bet(Long id, @NotNull Account account, @NotNull Match match, @NotNull @Min(0) Integer hometeamgoals,
			@NotNull @Min(0) Integer awayteamgoals, @NotNull @Size(max = 20) String hometeamresult,
			@NotNull @Size(max = 20) String awayteamresult, Date created, Date updated) {
		super();
		this.id = id;
		this.account = account;
		this.match = match;
		this.hometeamgoals = hometeamgoals;
		this.awayteamgoals = awayteamgoals;
		this.hometeamresult = hometeamresult;
		this.awayteamresult = awayteamresult;
		this.created = created;
		this.updated = updated;		
	}

	public Bet(Account account, @NotNull Match match, @NotNull @Min(0) Integer hometeamgoals,
			@NotNull @Min(0) Integer awayteamgoals, @NotNull @Size(max = 20) String hometeamresult,
			@NotNull @Size(max = 20) String awayteamresult) {
		super();
		this.account = account;
		this.match = match;
		this.hometeamgoals = hometeamgoals;
		this.awayteamgoals = awayteamgoals;
		this.hometeamresult = hometeamresult;
		this.awayteamresult = awayteamresult;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}



	public Integer getHometeamgoals() {
		return hometeamgoals;
	}



	public void setHometeamgoals(Integer hometeamgoals) {
		this.hometeamgoals = hometeamgoals;
	}



	public Integer getAwayteamgoals() {
		return awayteamgoals;
	}



	public void setAwayteamgoals(Integer awayteamgoals) {
		this.awayteamgoals = awayteamgoals;
	}



	public String getHometeamresult() {
		return hometeamresult;
	}



	public void setHometeamresult(String hometeamresult) {
		this.hometeamresult = hometeamresult;
	}



	public String getAwayteamresult() {
		return awayteamresult;
	}



	public void setAwayteamresult(String awayteamresult) {
		this.awayteamresult = awayteamresult;
	}

		
	
}
