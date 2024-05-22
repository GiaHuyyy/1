package service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import dao.SongDAO;
import entities.Song;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

public class SongService extends UnicastRemoteObject implements SongDAO{
	private EntityManager entityManager;
	private static final long serialVersionUID = 1L;
	public SongService(EntityManager entityManager) throws RemoteException{
		this.entityManager = entityManager;
	}
	@Override
	public boolean updateNameOfSong(String id, String newName) throws RemoteException {
	    EntityTransaction trans = entityManager.getTransaction();
	    try {
	        trans.begin();
	        Song song = entityManager.find(Song.class, id);
	        if (song != null) {
	            song.setName(newName);
	            entityManager.merge(song);
	            trans.commit();
	            return true;
	        } else {
	            System.out.println("Song not found with ID: " + id);
	            return false;
	        }
	    } catch (Exception e) {
	        if (trans.isActive()) {
	            trans.rollback();
	        }
	        e.printStackTrace();
	        return false;
	    }
	}
	
	@Override
    public List<Song> listSongByAlbum(String title, int year) throws RemoteException {
        try {
            Query query = entityManager.createQuery("SELECT s FROM Song s JOIN s.albums a WHERE a.title = :title AND a.yearOfRelease = :year");
            query.setParameter("title", title);
            query.setParameter("year", year);
            List<Song> songs = query.getResultList();
            return songs;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
