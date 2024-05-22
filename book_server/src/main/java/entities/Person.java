package entities;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "people")
public class Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "person_id")
	private int id;

	@Column(unique = true)
	private String email;
	
	private String first_name;
	private String last_name;
	private String professional_title;

	@OneToMany(mappedBy = "person")
	private Set<Reviews> review;

	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Person(int id, String email, String first_name, String last_name, String professional_title) {
		super();
		this.id = id;
		this.email = email;
		this.first_name = first_name;
		this.last_name = last_name;
		this.professional_title = professional_title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getProfessional_title() {
		return professional_title;
	}

	public void setProfessional_title(String professional_title) {
		this.professional_title = professional_title;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", email=" + email + ", first_name=" + first_name + ", last_name=" + last_name
				+ ", professional_title=" + professional_title + "]";
	}

}
