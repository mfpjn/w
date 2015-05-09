package at.mfpjn.workflow.model;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "city")
public class City{
	@Id
	@GeneratedValue
	@Column(name = "Id")
	private int id;
	
	@Pattern(regexp="^[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*$", message="This is not a valid city name")
	@Column(name = "Name", nullable = false, length = 70)
	private String name;
	
	@OneToOne
	@JoinColumn(name = "STATE_FK")
	private State stateFK;

	public City(){
		
	}
	
	public City(int id, String name, State stateFK) {
		super();
		this.id = id;
		this.name = name;
		this.stateFK = stateFK;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public State getStateFK() {
		return stateFK;
	}

	public void setStateFK(State stateFK) {
		this.stateFK = stateFK;
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", stateFK=" + stateFK
				+ "]";
	}
}
