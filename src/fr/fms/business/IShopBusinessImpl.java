/**
 * 
 */
package fr.fms.business;

import java.util.HashMap;
import java.util.Map;

import fr.fms.entities.Article;

/**
 * @author Stagiaires08P
 *
 */
public class IShopBusinessImpl implements IShopBusiness {
	
	private HashMap<Integer,Article> caddy;
	
	public IShopBusinessImpl() {
		caddy = new HashMap<Integer,Article>();	
	}

	@Override
	public void addCaddy(Article article) {
		caddy.put(article.getIdArticle(),article);		// ajouter un article au caddy, s'il existe déjà, ça ne marche pas	

	}

	@Override
	public Article readCaddy(int id) {
		Article art = null;
		art=caddy.get(id);
		return art;
	}

	@Override
	public boolean updateCaddy(Article article) {
		int key=article.getIdArticle();
		caddy.replace(key, article);
		return true;
	}

	@Override
	public boolean deleteCaddy(int id) {
		caddy.remove(id);
		return true;
	}

	@Override
	public void order() {
		// faire un new Command() plus tard.
		caddy.clear();
	}

	@Override
	public Map<Integer, Article> readAll() {
		return caddy;
	}

}
