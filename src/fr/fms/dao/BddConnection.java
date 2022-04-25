package fr.fms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class BddConnection {

	private static String url;
	private static String login;
	private static String password;
	private static String driver;
	private static Connection connection=null;

	static {
		try {
			// création des identifiants de connexion
			CreateConfigFile config=new CreateConfigFile();
			Properties prop = config.readPropertiesFile("config.properties");

			url=prop.getProperty("db.url");
			login=prop.getProperty("db.login");
			password=prop.getProperty("db.password");
			driver=prop.getProperty("db.driver.class");

			// enregistre la class auprès du driver manager
			// (charge le pilote)
			Class.forName(driver);

			// récupére une connection à partir d'une url + id + pwd
			connection=DriverManager.getConnection(url,login,password);// connection de java sql

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("erreur");

			if (connection != null) {
				System.out.println("Vous êtes connecté.");
			}
		}
	}
	// méthode qui crée la connexion
	public static Connection getConnection() {

		return connection;	
	}

}
