package tk.bolaodacopa.bets.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "matches", 
uniqueConstraints = { 
		@UniqueConstraint(columnNames = "matchcode")
})
public class Match {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 20)
	private String matchcode;

	@NotNull
	@ManyToOne
	private Stage stage;

	@Size(max = 20)
	private String matchgroup;

	@NotNull
	private Date date;	

	@NotNull
	private Integer round;

	@NotNull
	@ManyToOne
	private Team hometeam;

	@NotNull
	@ManyToOne
	private Team awayteam;	

	private Integer hometeamgoals;

	private Integer awayteamgoals;

	@Size(max = 20)
	private String hometeamresult;

	@Size(max = 20)
	private String awayteamresult;	

	@Size(max = 20)
	private String finishedmatch;


	public Match() {
	}	

	public Match(Long id, @NotBlank @Size(max = 20) String matchcode, @NotNull Stage stage,
			@Size(max = 20) String matchgroup, @NotNull Date date, @NotNull Integer round, @NotNull Team hometeam,
			@NotNull Team awayteam, Integer hometeamgoals, Integer awayteamgoals,
			@Size(max = 20) String hometeamresult, @Size(max = 20) String awayteamresult,
			@Size(max = 20) String finishedmatch) {
		super();
		this.id = id;
		this.matchcode = matchcode;
		this.stage = stage;
		this.matchgroup = matchgroup;
		this.date = date;
		this.round = round;
		this.hometeam = hometeam;
		this.awayteam = awayteam;
		this.hometeamgoals = hometeamgoals;
		this.awayteamgoals = awayteamgoals;
		this.hometeamresult = hometeamresult;
		this.awayteamresult = awayteamresult;
		this.finishedmatch = finishedmatch;
	}



	public Match(@NotBlank @Size(max = 20) String matchcode, @NotNull Stage stage,
			@Size(max = 20) String matchgroup, @NotNull Date date, @NotNull Integer round, @NotNull Team hometeam,
			@NotNull Team awayteam) {
		super();
		this.matchcode = matchcode;
		this.stage = stage;
		this.matchgroup = matchgroup;
		this.date = date;
		this.round = round;
		this.hometeam = hometeam;
		this.awayteam = awayteam;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMatchcode() {
		return matchcode;
	}

	public void setMatchcode(String matchcode) {
		this.matchcode = matchcode;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getFinishedmatch() {
		return finishedmatch;
	}

	public void setFinishedmatch(String finishedmatch) {
		this.finishedmatch = finishedmatch;
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

	public Integer getRound() {
		return round;
	}

	public void setRound(Integer round) {
		this.round = round;
	}

	public String getMatchgroup() {
		return matchgroup;
	}

	public void setMatchgroup(String matchgroup) {
		this.matchgroup = matchgroup;
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

	public Team getHometeam() {
		return hometeam;
	}

	public void setHometeam(Team hometeam) {
		this.hometeam = hometeam;
	}

	public Team getAwayteam() {
		return awayteam;
	}

	public void setAwayteam(Team awayteam) {
		this.awayteam = awayteam;
	}	

	public String getFormatteddate() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return df.format(this.date);
	}

}
