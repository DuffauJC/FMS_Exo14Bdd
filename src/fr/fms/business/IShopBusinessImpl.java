/**
 * 
 */
package fr.fms.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

import fr.fms.dao.DAOFactory;
import fr.fms.dao.Dao;
import fr.fms.entities.Article;
import fr.fms.entities.Category;
import fr.fms.entities.User;

/**
 * @author Stagiaires08P
 *
 */
public class IShopBusinessImpl implements IShopBusiness {

	private HashMap<Integer,Article> caddy;
	private ArrayList<Article> caddyToList= null;

	private static Dao<Article> articleDao;
	private static Dao<User> userDao;
	private static Dao<Category> categoryDao;


	public IShopBusinessImpl() {
		caddy = new HashMap<Integer,Article>();	
		articleDao=DAOFactory.getArticleDao();
		userDao=DAOFactory.getUserDao();
		categoryDao=DAOFactory.getCategoryDao();
	}
	/**
	 * add caddy
	 * @param Article article
	 * @Override
	 */
	public void addCaddy(Article article) {
		caddy.put(article.getIdArticle(),article);

	}
	/**
	 * read caddy by id
	 * @return
	 * @Override
	 */
	public Article readCaddy(int id)  {
		Article art = null;
		if (caddy.get(id)!=null) {
			art=caddy.get(id);
		} else {
			throw new RuntimeException("article inexistant dans le panier !");
		}

		return art;
	}
	/**
	 * update caddy
	 * @param id qty
	 * @Override
	 * @return
	 */
	public boolean updateCaddy(int id,int qty) {
		if (caddy.get(id)!=null) {
			caddy.get(id).setQty(qty);
		} else {
			throw new RuntimeException("article inexistant dans le panier !");
		}
		return true;	
	}

	/**
	 * delete item caddy by id
	 * @return
	 * @Override
	 */
	public boolean deleteCaddy(int id)  {
		if (caddy.get(id)!=null) {
			caddy.remove(id);
		} else {
			throw new RuntimeException("article inexistant dans le panier !");
		}

		return true;
	}
	/**
	 * clear caddy
	 * @Override
	 */
	public void order() {
		// faire un new Command() plus tard.
		caddy.clear();
	}

	/**
	 * get caddy
	 * @Override
	 * @return
	 */
	public ArrayList<Article> readCaddy() {
		caddyToList = caddy.values().stream().collect(Collectors.toCollection(ArrayList::new));     
		return  caddyToList;
	}

	/**
	 * get users
	 * @return
	 */
	public ArrayList<User> getListUsers() {
		ArrayList<User> users = userDao.readAll();
		return users;
	}

	/**
	 * get articles
	 * @return
	 */
	public ArrayList<Article> getListArticles() {
		ArrayList<Article> articles = articleDao.readAll();
		return articles;
	}


	/**
	 * get categories
	 * @return
	 */
	public ArrayList<Category> getListCategory() {
		ArrayList<Category> categories = categoryDao.readAll();
		return categories;
	}

	/**
	 * get article by id
	 * @param id
	 */
	public void ArticleById(int id) {
		Article art = null;
		art=articleDao.read(id); 
		if (art!=null) {
			addCaddy(art);
		} else {
			throw new RuntimeException("article inexistant dans la liste !");
		}

	}

	/**
	 * get article by category
	 * @param id
	 * @return
	 */
	public  ArrayList<Article> getArticlesByCategory(int id) {
		ArrayList<Article> articles = null;
		if (categoryDao.read(id)!=null) {
			articles=articleDao.readByCategory(id);

		} else {
			throw new RuntimeException("categorie inexistante dans la liste !");
		}

		return articles;
	}

}
