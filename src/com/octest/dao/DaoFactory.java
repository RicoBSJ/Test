package com.octest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {
	private String url;
	private String username;
	private String password;

	DaoFactory(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}

	public static DaoFactory getInstance() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {

		}

		DaoFactory instance = new DaoFactory("jdbc:postgresql://127.0.0.1:5432/javaee", "postgres", "postgres");
		return instance;
	}

	public Connection getConnection() throws SQLException {
		Connection connexion = DriverManager.getConnection(url, username, password);
		connexion.setAutoCommit(false);
		return connexion;
	}

	// Récupération du Dao
	public UtilisateurDao getUtilisateurDao() {
		return new UtilisateurDaoImpl(this);
	}
}