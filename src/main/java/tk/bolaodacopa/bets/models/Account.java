package tk.bolaodacopa.bets.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "accounts", 
uniqueConstraints = { 
		@UniqueConstraint(columnNames = "username"),
		@UniqueConstraint(columnNames = "email") 
})
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 20)
	private String username;

	@NotBlank
	@Size(max = 255)
	private String name;  

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;


	public Account() {
	}

	public Account(String username, String name, String email, String password) {
		this.username = username;
		this.name = name;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}