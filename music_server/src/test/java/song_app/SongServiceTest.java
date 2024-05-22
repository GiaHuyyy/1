package song_app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dao.AlbumDAO;
import dao.SongDAO;
import entities.Song;
import service.AlbumService;
import service.EntityManagerFactoryUtil;
import service.SongService;

public class SongServiceTest {
    private static EntityManagerFactoryUtil entityManagerFactoryUtil;
    private static SongDAO songDao;
    private static AlbumDAO albumDao;

    @BeforeAll
    static void setup() throws RemoteException {
        entityManagerFactoryUtil = new EntityManagerFactoryUtil();
        songDao = new SongService(entityManagerFactoryUtil.getEnManager());
        albumDao = new AlbumService(entityManagerFactoryUtil.getEnManager());
    }

    @Test
    @DisplayName("Test updateNameOfSong method")
    void testUpdateNameOfSong() throws RemoteException {
        String songIdToUpdate = "S001";
        String newSongName = "New Song Name";

        assertTrue(songDao.updateNameOfSong(songIdToUpdate, newSongName));
    }

    @Test
    @DisplayName("Test listSongByAlbum method")
    void testListSongByAlbum() throws RemoteException {
        String albumTitle = "Greatest Hits";
        int albumYear = 2005;

        List<Song> songs = songDao.listSongByAlbum(albumTitle, albumYear);

        assertNotNull(songs);
        assertFalse(songs.isEmpty());
    }

    @Test
    @DisplayName("Test getNumberOfAlbumsByGenre method")
    void testGetNumberOfAlbumsByGenre() throws RemoteException {
        Map<String, Integer> numberOfAlbumsByGenre = albumDao.getNumberOfAlbumsByGenre();

        assertNotNull(numberOfAlbumsByGenre);
        assertFalse(numberOfAlbumsByGenre.isEmpty());
    }

    @AfterAll
    static void afterAll() {
        entityManagerFactoryUtil.closeEnManager();
        entityManagerFactoryUtil.closeEnManagerFactory();
    }
}

