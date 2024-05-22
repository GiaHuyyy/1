package entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "book_translations")
public class BookTranslation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String language;
	private String translate_name;

	@Id
	@ManyToOne
	@JoinColumn(name = "ISBN")
	private Book book;

	public BookTranslation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookTranslation(String language, String translate_name, Book book) {
		super();
		this.language = language;
		this.translate_name = translate_name;
		this.book = book;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getTranslate_name() {
		return translate_name;
	}

	public void setTranslate_name(String translate_name) {
		this.translate_name = translate_name;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@Override
	public String toString() {
		return "BookTranslation [language=" + language + ", translate_name=" + translate_name + ", book=" + book + "]";
	}

}
