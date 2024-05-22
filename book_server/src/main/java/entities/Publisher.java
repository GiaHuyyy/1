package entities;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "publishers")
public class Publisher implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "publisher_id")
	private String id;

	private String address;
	
	@Column(unique = true)
	private String email;
	
	@Column(unique = true)
	private String name;
	
	private String phone;

	@OneToMany(mappedBy = "publisher")
	private Set<Book> book;

	public Publisher() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Publisher(String id, String address, String email, String name, String phone) {
		super();
		this.id = id;
		this.address = address;
		this.email = email;
		this.name = name;
		this.phone = phone;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Set<Book> getBook() {
		return book;
	}

	public void setBook(Set<Book> book) {
		this.book = book;
	}

	@Override
	public String toString() {
		return "Publisher [id=" + id + ", address=" + address + ", email=" + email + ", name=" + name + ", phone="
				+ phone + "]";
	}

}
