package entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Instructor")
public class Instructor extends Person implements Serializable {
	private static final long serialVersionUID = 8065808962349626612L;
	private LocalDateTime hireDate;

	public Instructor() {
	}
	
	public Instructor(String firstName, String lastName, LocalDateTime hireDate) {
		super(firstName, lastName);
		this.hireDate = hireDate;
	}

	public LocalDateTime getHireDate() {
		return hireDate;
	}

	public void setHireDate(LocalDateTime hireDate) {
		this.hireDate = hireDate;
	}

	@Override
	public String toString() {
		return String.format("Instructor [hireDate=%s, toString()=%s]", hireDate, super.toString());

	}

}