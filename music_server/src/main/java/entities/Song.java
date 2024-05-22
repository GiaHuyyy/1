package entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Song")
public class Song implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "SongID")
    private String id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Runtime")
    private String runtime;

    @Column(name = "Lyric")
    private String lyric;

    @Column(name = "FileLink")
    private String fileLink;

    @ManyToMany(mappedBy = "songs")
    private Set<Album> albums = new HashSet<>();

    public Song() {
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

	public String getRuntime() {
		return runtime;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	public String getLyric() {
		return lyric;
	}

	public void setLyric(String lyric) {
		this.lyric = lyric;
	}

	public String getFileLink() {
		return fileLink;
	}

	public void setFileLink(String fileLink) {
		this.fileLink = fileLink;
	}

	public Set<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(Set<Album> albums) {
		this.albums = albums;
	}

	@Override
	public String toString() {
		return "Song [id=" + id + ", name=" + name + ", runtime=" + runtime + ", lyric=" + lyric + ", fileLink="
				+ fileLink + ", albums=" + albums + "]";
	}

    
}
