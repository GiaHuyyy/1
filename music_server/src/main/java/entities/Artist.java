package entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Artist")
public class Artist implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ArtistID")
    private String id;

    @Column(name = "Name")
    private String name;

    @Column(name = "BirthDate")
    private LocalDate birthDate;

    @Column(name = "Url")
    private String url;

    @ManyToMany(mappedBy = "artists", fetch = FetchType.LAZY)
    private Set<Album> albums = new HashSet<>();

    public Artist() {
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Set<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(Set<Album> albums) {
		this.albums = albums;
	}

	@Override
	public String toString() {
		return "Artist [id=" + id + ", name=" + name + ", birthDate=" + birthDate + ", url=" + url + ", albums="
				+ albums + "]";
	}

    
}
