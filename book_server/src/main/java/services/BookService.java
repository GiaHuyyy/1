package services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.BookDAO;
import entities.Book;
import entities.Person;
import entities.Reviews;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class BookService implements BookDAO {
	private EntityManager entityManager;

	public BookService(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	// A. Liệt kê danh sách các cuốn sách được viết bởi tác giả nào đó khi biết tên
	// tác giả và
	// có điểm đánh giá từ mấy sao trở lên.
	@Override
	public List<Book> listRatedBooks(String authorName, int rating) {
		List<Book> books = new ArrayList<Book>();
		try {
			TypedQuery<Book> query = entityManager.createQuery(
					"SELECT DISTINCT b FROM Book b JOIN b.authors a JOIN b.review r  WHERE a = :author AND r.rating >= :rating",
					Book.class);
			query.setParameter("author", authorName);
			query.setParameter("rating", rating);

			return books = query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return books;
	}

	// B. Thống kê số cuốn sách được dịch sang ngôn ngữ khác của từng tác giả, kết
	// quả sắp
	// xếp theo tên tác giả.
	public Map<String, Long> countBooksByAuthor() {
		String jpql = "SELECT a, COUNT(bt) FROM Book b JOIN b.authors a JOIN b.bookTranslation bt GROUP BY a ORDER BY a";
		TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class);
		List<Object[]> results = query.getResultList();

		Map<String, Long> countMap = new HashMap<>();
		for (Object[] result : results) {
			String author = (String) result[0];
			Long count = (Long) result[1];
			countMap.put(author, count);
		}
		return countMap;
	}

	// C. Cập nhật thêm một lượt đánh giá cho một cuốn sách, cập nhật thành công nếu
	// cuốn
	// sách và người đọc đã tồn tại, rating phải từ 1 đến 5 và bình luận không được
	// để trống hay rỗng.
	@Override
	public boolean updateReviews(String isbn, String readerID, int rating, String comment) {
		if (rating < 1 || rating > 5 || comment == null || comment.trim().isEmpty()) {
			return false;
		}

		EntityTransaction trans = entityManager.getTransaction();
		try {
			trans.begin();

			// Kiểm tra sự tồn tại của cuốn sách
			Book book = entityManager.find(Book.class, isbn);
			if (book == null) {
				return false;
			}

			// Kiểm tra sự tồn tại của người đọc (giả sử bạn có thực thể Person và bảng
			// tương ứng)
			Person person = entityManager.find(Person.class, readerID);
			if (person == null) {
				return false;
			}

			// Kiểm tra sự tồn tại của review
			TypedQuery<Reviews> query = entityManager.createQuery(
					"SELECT r FROM Reviews r WHERE r.book.ISBN = :isbn AND r.person.id = :readerID", Reviews.class);
			query.setParameter("ISBN", isbn);
			query.setParameter("person_id", readerID);

			Reviews review;
			List<Reviews> results = query.getResultList();
			if (results.isEmpty()) {
				// Tạo mới review nếu chưa tồn tại
				review = new Reviews();
				review.setBook(book);
				review.setPerson(person);
				;
			} else {
				// Cập nhật review nếu đã tồn tại
				review = results.get(0);
			}

			review.setRating(rating);
			review.setComment(comment);
			entityManager.persist(review);

			trans.commit();
			return true;
		} catch (Exception e) {
			if (trans.isActive()) {
				trans.rollback();
			}
			e.printStackTrace();
			return false;
		}
	}
}
