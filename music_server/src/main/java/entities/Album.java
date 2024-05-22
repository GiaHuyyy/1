package entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Album")
public class Album implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "AlbumID")
    private String id;

    @Column(name = "Title")
    private String title;

    @Column(name = "Price")
    private double price;

    @Column(name = "YearOfRelease")
    private int yearOfRelease;

    @Column(name = "DownloadLink")
    private String downloadLink;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GenreID")
    private Genre genre;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "AlbumArtist", joinColumns = @JoinColumn(name = "AlbumID"), inverseJoinColumns = @JoinColumn(name = "ArtistID"))
    private Set<Artist> artists = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "AlbumSong", joinColumns = @JoinColumn(name = "AlbumID"), inverseJoinColumns = @JoinColumn(name = "SongID"))
    private Set<Song> songs = new HashSet<>();

    public Album() {
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getYearOfRelease() {
		return yearOfRelease;
	}

	public void setYearOfRelease(int yearOfRelease) {
		this.yearOfRelease = yearOfRelease;
	}

	public String getDownloadLink() {
		return downloadLink;
	}

	public void setDownloadLink(String downloadLink) {
		this.downloadLink = downloadLink;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public Set<Artist> getArtists() {
		return artists;
	}

	public void setArtists(Set<Artist> artists) {
		this.artists = artists;
	}

	public Set<Song> getSongs() {
		return songs;
	}

	public void setSongs(Set<Song> songs) {
		this.songs = songs;
	}

	@Override
	public String toString() {
		return "Album [id=" + id + ", title=" + title + ", price=" + price + ", yearOfRelease=" + yearOfRelease
				+ ", downloadLink=" + downloadLink + ", genre=" + genre + ", artists=" + artists + ", songs=" + songs
				+ "]";
	}

    
}
