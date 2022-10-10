package tk.bolaodacopa.bets.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

@Entity
@Table(name = "teams", 
uniqueConstraints = { 
		@UniqueConstraint(columnNames = "fifacode"),
		@UniqueConstraint(columnNames = "name") 
})
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	

	@NotBlank
	@Size(max = 20)
	private String fifacode;

	@NotBlank
	@Size(max = 255)	
	private String name;

	@NotBlank
	@Size(max = 255)	
	@URL
	private String flag;	

	public Team() {
	}	

	public Team(Long id, @NotBlank @Size(max = 20) String fifacode, @NotBlank @Size(max = 255) String name,
			@NotBlank @Size(max = 255) @URL String flag) {
		super();
		this.id = id;
		this.fifacode = fifacode;
		this.name = name;
		this.flag = flag;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFifacode() {
		return fifacode;
	}

	public void setFifacode(String fifacode) {
		this.fifacode = fifacode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}
