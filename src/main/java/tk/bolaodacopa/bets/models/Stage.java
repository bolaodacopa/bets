package tk.bolaodacopa.bets.models;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "stages", 
uniqueConstraints = { 
		@UniqueConstraint(columnNames = "name")
})
public class Stage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	

	@NotBlank
	@Size(max = 255)	
	private String name;	


	private String finishedbets;


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

	public Stage() {
		
	}
	
	public Stage(Long id, @NotBlank @Size(max = 255) String name, String finishedbets, Date created, Date updated) {
		super();
		this.id = id;
		this.name = name;
		this.finishedbets = finishedbets;
		this.created = created;
		this.updated = updated;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFinishedbets() {
		return finishedbets;
	}

	public void setFinishedbets(String finishedbets) {
		this.finishedbets = finishedbets;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}


}
