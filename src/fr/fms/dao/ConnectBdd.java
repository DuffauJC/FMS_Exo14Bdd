package fr.fms.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectBdd {


	private  String url;
	private  String login;
	private  String password;
	private  String driver;
	private  Connection connection=null;
	private  CreateConfigFile config=null;
	private  Properties prop=null;
	private static ConnectBdd connectBdd_instance=null;


	private ConnectBdd() {
		setConfig(config);
		setProp(prop);
		setDriver(driver);
		setUrl(url);
		setLogin(login);
		setPassword(password);
		setConnection(connection);
		

		try {
			
			// enregistre la class auprès du driver manager
			// (charge le pilote)
			Class.forName(driver);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
	}


	// Static methode pour créer une instance de ConnectBdd
	public static ConnectBdd Connexion() 
	{
		// vérification de l'instance de connection
		if (connectBdd_instance == null) {
			connectBdd_instance = new ConnectBdd();
			//System.out.println(" connectBdd_instance : Création de la connexion.");
		}
		return connectBdd_instance;
	}


	
	// guetteurs et setteurs
	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		url=prop.getProperty("db.url");
		this.url = url;
	}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		login=prop.getProperty("db.login");
		this.login = login;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		password=prop.getProperty("db.password");
		this.password = password;
	}


	public String getDriver() {
		return driver;
	}


	public void setDriver(String driver) {
		driver=prop.getProperty("db.driver.class");
		this.driver = driver;
	}


	public Connection getConnection() {
		return connection;
	}


	public void setConnection(Connection connection)  {
		try {
			connection=DriverManager.getConnection(url,login,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		this.connection = connection;
	}


	public CreateConfigFile getConfig() {
		return config;
	}


	public void setConfig(CreateConfigFile config) {
		config=new CreateConfigFile();
		this.config = config;
	}


	public Properties getProp() {
		return prop;
	}


	public void setProp(Properties prop) {
		try {
			prop = config.readPropertiesFile("config.properties");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.prop = prop;
	}



}
