/**
 * 
 */
package fr.fms.business;

import java.util.ArrayList;

import fr.fms.entities.Article;



/**
 * @author Stagiaires08P
 *
 */
public interface IShopBusiness {

	public void addCaddy(Article article);				// ajoute un aerticle au panier	
	public Article readCaddy(int id);					// retourne un article du panier	
	public boolean updateCaddy(int id,int qty);			// met à jour la quantitée d'un article du panier
	public boolean deleteCaddy(int id);					// supprime un article du panier
	public int order(int idUser) ;								// pour creer une commande	
	public ArrayList<Article> readCaddy();              // retoune la liste du panier
	public  ArrayList<Article> getArticlesByCategory(int id); // retourne la liste des articles d'une catégorie
	
}
