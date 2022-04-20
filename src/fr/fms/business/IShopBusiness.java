/**
 * 
 */
package fr.fms.business;

import java.util.Map;

import fr.fms.entities.Article;



/**
 * @author Stagiaires08P
 *
 */
public interface IShopBusiness {

	public void addCaddy(Article article);					//ajoute un article au panier
	public Article readCaddy(int id);						// renvoi un objet correspondant à l'id du panier
	public boolean updateCaddy(Article article);			//modifie un article du panier
	public boolean deleteCaddy(int id);						// supprime l'article du panier
	public void order() ;									//valide la commande du panier
	public Map<Integer,Article> readAll(); 				// retourne le panier
	
}
