package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entities.Song;

public interface SongDAO extends Remote {
	public boolean updateNameOfSong(String id, String newName) throws RemoteException;
	public List<Song> listSongByAlbum(String title, int year) throws RemoteException;
}
