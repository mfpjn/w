package at.mfpjn.workflow.model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "state")
public class State implements Serializable {
	@Id
	@GeneratedValue
	@Column(name = "Id")
	private int id;

	public State() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	
}
