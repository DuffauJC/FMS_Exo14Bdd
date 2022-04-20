/**
 * 
 */
package fr.fms.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;



/**
 * @author Stagiaires08P
 *
 */
public interface Dao<T> {

	public Connection connection=ConnectBdd.Connexion().getConnection();
	public void create(T obj)throws SQLException;         // ajout d'une nouvelle occurence en base
	public T read(int id)throws SQLException;			// renvoi un objet correspondant à l'id en base
	public boolean update(T obj)throws SQLException;     // met à jour l'objet en base, renvoi vrai si c'est fait
	public boolean delete(int id)throws SQLException;    // supprime l'objet en base, renvoi vrai si c'est fait
	public ArrayList<T> readAll() throws SQLException;			 // renvoi tous les objets de la table correspondante
	
}
