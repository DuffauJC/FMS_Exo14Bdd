/**
 * 
 */
package fr.fms.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
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
	private  ArrayList<Article> caddyToList= null;
	private static Dao<Article> article;
	private static Dao<User> user;
	private static Dao<Category> category;
	private static ArrayList<User> users;

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
	public boolean updateCaddy(int id,int qty) {
		caddy.get(id).setQty(qty);
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
	public ArrayList<Article> readAll() {
		caddyToList = caddy.values().stream().collect(Collectors.toCollection(ArrayList::new));     
		return  caddyToList;
	}

	// get articles
	public ArrayList<Article> getListArticles() {
		ArrayList<Article> art = article.readAll();
		return art;
	}

	// get category
	public ArrayList<Category> getListCategory() {
		ArrayList<Category> cat = category.readAll();
		return cat;
	}
	// get article id
	public Article getArticleById(int id) {
		Article art = null;
		art=article.read(id); 
		return art;
	}
	// get article par category
	public  ArrayList<Article> getArticleReadByCategory(int id) {
		ArrayList<Article> art = null;
		art=article.readByCategory(id);
		return art;
	}

	// initialisation de la boutique
	public void shopInit() {

		article=DAOFactory.getArticleDao();
		user=DAOFactory.getUserDao();
		category=DAOFactory.getCategoryDao();
		users=user.readAll();
		
		ArrayList<Article> art=getArticleReadByCategory(3);
		System.out.println("Liste des articles de la categorie 3 : ");
		art.forEach(a->{
			System.out.println(a.toString());
		});
		
	}
	// login user
	public User login(Scanner scan, String login, String password) {

		User user = null;
		login=scan.next(); 
		password=scan.next();
		for (User u : users) {
			if (u.getLogin().equals(login) && u.getPassword().equals(password)) {
				user=new User(u.getIdUser(),u.getLogin(),u.getPassword());
			}
		}
		return user;
	}
}
