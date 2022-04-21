
package fr.fms.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

import fr.fms.entities.Article;



/**
 * @author Stagiaires08P
 *
 */
public interface Dao<T> {
	
	public Connection connection=ConnectBdd.Connexion().getConnection();
	
	public  void create(T obj);         // ajout d'une nouvelle occurence en base
	public  T read(int id);			// renvoi un objet correspondant à l'id en base
	public  boolean update(T obj);     // met à jour l'objet en base, renvoi vrai si c'est fait
	public  boolean delete(int id);    // supprime l'objet en base, renvoi vrai si c'est fait
	public  ArrayList<T> readAll() ;			 // renvoi tous les objets de la table correspondante
	public ArrayList<T> readByCategory(int id);		// lit par categorie
	
}
