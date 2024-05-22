package entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "experiences")
public class Experience implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "company_name")
	protected String id;

	private String description;
	private LocalDate from_date;
	private LocalDate to_date;

	@ManyToOne
	@JoinColumn(name = "position_id")
	private Position position;

	@ManyToOne
	@JoinColumn(name = "candidate_id")
	private Candidate candidate;

	public Experience() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Experience(String id, String description, LocalDate from_date, LocalDate to_date, Position position,
			Candidate candidate) {
		super();
		this.id = id;
		this.description = description;
		this.from_date = from_date;
		this.to_date = to_date;
		this.position = position;
		this.candidate = candidate;
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

	public LocalDate getFrom_date() {
		return from_date;
	}

	public void setFrom_date(LocalDate from_date) {
		this.from_date = from_date;
	}

	public LocalDate getTo_date() {
		return to_date;
	}

	public void setTo_date(LocalDate to_date) {
		this.to_date = to_date;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	@Override
	public String toString() {
		return "Experience [id=" + id + ", description=" + description + ", from_date=" + from_date + ", to_date="
				+ to_date + ", position=" + position + ", candidate=" + candidate + "]";
	}

}
