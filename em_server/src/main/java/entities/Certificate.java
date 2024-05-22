package entities;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "certificates")
public class Certificate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "certificate_id")
	protected String id;

	private LocalDate issue_date;
	private String name;
	private String organization;

	@ManyToOne
	@JoinColumn(name = "candidate_id")
	private Candidate candidate;

	public Certificate() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Certificate(String id, LocalDate issue_date, String name, String organization, Candidate candidate) {
		super();
		this.id = id;
		this.issue_date = issue_date;
		this.name = name;
		this.organization = organization;
		this.candidate = candidate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDate getIssue_date() {
		return issue_date;
	}

	public void setIssue_date(LocalDate issue_date) {
		this.issue_date = issue_date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Certificate [id=" + id + ", issue_date=" + issue_date + ", name=" + name + ", organization="
				+ organization + ", candidate=" + candidate + "]";
	}

}
