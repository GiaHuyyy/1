package service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.AlbumDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class AlbumService extends UnicastRemoteObject implements AlbumDAO{
	private EntityManager entityManager;
    private static final long serialVersionUID = 1L;
    public AlbumService(EntityManager entityManager) throws RemoteException {
        this.entityManager = entityManager;
    }

    @Override
    public Map<String, Integer> getNumberOfAlbumsByGenre() throws RemoteException {
        try {
            Query query = entityManager.createQuery("SELECT a.genre.name, COUNT(a) FROM Album a GROUP BY a.genre.name ORDER BY a.genre.name");
            List<Object[]> results = query.getResultList();

            Map<String, Integer> numberOfAlbumsByGenre = new HashMap<>();
            for (Object[] result : results) {
                String genreName = (String) result[0];
                Long count = (Long) result[1];
                numberOfAlbumsByGenre.put(genreName, count.intValue());
            }

            return numberOfAlbumsByGenre;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
