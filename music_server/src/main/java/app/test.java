package app;

import jakarta.persistence.Persistence;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Persistence.createEntityManagerFactory("music_server");
	}

}
