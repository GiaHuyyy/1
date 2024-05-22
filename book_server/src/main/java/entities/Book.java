package entities;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
@Inheritance(strategy= InheritanceType.JOINED )
public class Book implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String ISBN;

	private String name;
	private int num_of_pages;
	private double price;
	private int publish_year;

	@OneToMany(mappedBy = "book")
	private Set<Reviews> review;

	@ManyToOne
	@JoinColumn(name = "publisher_id")
	private Publisher publisher;

	@OneToMany(mappedBy = "book")
	private Set<BookTranslation> bookTranslation;

	@ElementCollection
	@CollectionTable(name = "books_authors", joinColumns = @JoinColumn(name = "ISBN"))
	@Column(name = "author")
	private Set<String> authors;

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Book(String iSBN, String name, int num_of_pages, double price, int publish_year, Publisher publisher) {
		super();
		ISBN = iSBN;
		this.name = name;
		this.num_of_pages = num_of_pages;
		this.price = price;
		this.publish_year = publish_year;
		this.publisher = publisher;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNum_of_pages() {
		return num_of_pages;
	}

	public void setNum_of_pages(int num_of_pages) {
		this.num_of_pages = num_of_pages;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getPublist_year() {
		return publish_year;
	}

	public void setPublist_year(int publish_year) {
		this.publish_year = publish_year;
	}

	public Set<Reviews> getReview() {
		return review;
	}

	public void setReview(Set<Reviews> review) {
		this.review = review;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public Set<BookTranslation> getBookTranslation() {
		return bookTranslation;
	}

	public void setBookTranslation(Set<BookTranslation> bookTranslation) {
		this.bookTranslation = bookTranslation;
	}

	@Override
	public String toString() {
		return "Book [ISBN=" + ISBN + ", name=" + name + ", num_of_pages=" + num_of_pages + ", price=" + price
				+ ", publist_year=" + publish_year + ", publisher=" + publisher + "]";
	}

}
