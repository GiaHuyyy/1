package entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reviews")
public class Reviews implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String comment;
	private int rating; 
	
	@Id
	@ManyToOne
	@JoinColumn(name = "ISBN")
	private Book book;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "person_id")
	private Person person;

	public Reviews() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reviews(String comment, int rating, Book book, Person person) {
		super();
		this.comment = comment;
		this.rating = rating;
		this.book = book;
		this.person = person;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Override
	public String toString() {
		return "Reviews [comment=" + comment + ", rating=" + rating + ", book=" + book + ", person=" + person + "]";
	}

}
