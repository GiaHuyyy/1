package entities;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "candidates")
public class Candidate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "candidate_id")
	protected String id;

	private String description;
	private String email;
	private String full_name;
	private String gender;
	private String phone;
	private int year_of_birth;

	@ManyToOne
	@JoinColumn(name = "position_id")
	private Position position;

	@OneToMany(mappedBy = "candidate")
	private Set<Experience> experience;

	@OneToMany(mappedBy = "candidate")
	private Set<Certificate> certificate;

	public Candidate() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Candidate(String id, String description, String email, String full_name, String gender, String phone,
			int year_of_birth, Position position) {
		super();
		this.id = id;
		this.description = description;
		this.email = email;
		this.full_name = full_name;
		this.gender = gender;
		this.phone = phone;
		this.year_of_birth = year_of_birth;
		this.position = position;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getYear_of_birth() {
		return year_of_birth;
	}

	public void setYear_of_birth(int year_of_birth) {
		this.year_of_birth = year_of_birth;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Set<Experience> getExperience() {
		return experience;
	}

	public void setExperience(Set<Experience> experience) {
		this.experience = experience;
	}

	public Set<Certificate> getCertificate() {
		return certificate;
	}

	public void setCertificate(Set<Certificate> certificate) {
		this.certificate = certificate;
	}

	@Override
	public String toString() {
		return "Candidate [id=" + id + ", description=" + description + ", email=" + email + ", full_name=" + full_name
				+ ", gender=" + gender + ", phone=" + phone + ", year_of_birth=" + year_of_birth + ", position="
				+ position + "]";
	}

}
