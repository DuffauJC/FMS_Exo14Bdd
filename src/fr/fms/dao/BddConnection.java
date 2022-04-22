package fr.fms.dao;

import java.io.IOException;




import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class BddConnection {


	private static String url;
	private static String login;
	private static String password;
	private static String driver;
	private static Connection connection=null;

	private static final BddConnection INSTANCE=null;

	// méthode qui crée la connexion
	private  BddConnection() {

		try {
			// création des identifiants de connexion
			CreateConfigFile config=new CreateConfigFile();
			Properties prop = config.readPropertiesFile("config.properties");

			url=prop.getProperty("db.url");
			login=prop.getProperty("db.login");
			password=prop.getProperty("db.password");
			driver=prop.getProperty("db.driver.class");


			// récupére une connection à partir d'une url + id + pwd
			connection=DriverManager.getConnection(url,login,password);// connection de java sql

		}catch (SQLException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		if (connection != null) {
			System.out.println("Vous êtes connecté.");
		}
			
	}
	
	public static Connection getConnection() {
		return BddConnection.connection;
	}

}
