package main;

import jakarta.persistence.Persistence;

public class Test {
	public static void main(String[] args) {
		Persistence.createEntityManagerFactory("em_server");
	}
}
