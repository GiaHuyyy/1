package entities;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "positions")
public class Position implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "position_id")
	protected String id;

	private String description;
	private String name;
	private int number;
	private double salary;

	@OneToMany(mappedBy = "position")
	private Set<Candidate> candidate;

	@OneToMany(mappedBy = "position")
	private Set<Experience> experience;

	public Position() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Position(String id) {
		super();
		// TODO Auto-generated constructor stub
		this.id = id;
	}

	public Position(String id, String description, String name, int number, double salary, Set<Candidate> candidate,
			Set<Experience> experience) {
		super();
		this.id = id;
		this.description = description;
		this.name = name;
		this.number = number;
		this.salary = salary;
		this.candidate = candidate;
		this.experience = experience;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Set<Candidate> getCandidate() {
		return candidate;
	}

	public void setCandidate(Set<Candidate> candidate) {
		this.candidate = candidate;
	}

	public Set<Experience> getExperience() {
		return experience;
	}

	public void setExperience(Set<Experience> experience) {
		this.experience = experience;
	}

	@Override
	public String toString() {
		return "Position [id=" + id + ", description=" + description + ", name=" + name + ", number=" + number
				+ ", salary=" + salary + "]";
	}

}
