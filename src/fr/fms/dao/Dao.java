
package fr.fms.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * @author Stagiaires08P
 *
 */
public interface Dao<T> {
	
	//public Connection connection=ConnectBdd.Connexion().getConnection();
	public Connection connection=BddConnection.getConnection();
	public static final Logger logger = Logger.getLogger( "SqlExceptions" );
	
	public  boolean create(T obj);        
	public  T read(int id);			
	public  boolean update(T obj);    
	public  boolean delete(int id);    
	public  ArrayList<T> readAll() ;			 
	public ArrayList<T> readByCategory(int id);	
	
}
