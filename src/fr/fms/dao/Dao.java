
package fr.fms.dao;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * @author Stagiaires08P
 *
 */
public interface Dao<T> {
	
	public Connection connection=ConnectBdd.Connexion().getConnection();

	public  void create(T obj);        
	public  T read(int id);			
	public  boolean update(T obj);    
	public  boolean delete(int id);    
	public  ArrayList<T> readAll() ;			 
	public ArrayList<T> readByCategory(int id);	
	
}
