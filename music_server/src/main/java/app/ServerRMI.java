package app;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import dao.SongDAO;
import dao.AlbumDAO;
import jakarta.persistence.EntityManager;
import service.AlbumService;
import service.EntityManagerFactoryUtil;
import service.SongService;


public class ServerRMI {
	public static void main(String[] args) throws RemoteException, AlreadyBoundException {

		// Create a registry
		Registry registry = LocateRegistry.createRegistry(2020);

		EntityManagerFactoryUtil entityManagerFactory = new EntityManagerFactoryUtil();
		EntityManager entityManager = entityManagerFactory.getEnManager();
		
		SongDAO songDao = new SongService(entityManager);
		AlbumDAO albumService = new AlbumService(entityManager);

		registry.bind("SongDao", songDao);
		registry.bind("AlbumDao", albumService);
		System.out.println("RMI Server ready");
	}
}
