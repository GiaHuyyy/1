package dao;

import java.util.List;

import entities.Book;

public interface BookDAO {

	List<Book> listRatedBooks(String authorName, int rating);

	boolean updateReviews(String isbn, String readerID, int rating, String comment);

}
