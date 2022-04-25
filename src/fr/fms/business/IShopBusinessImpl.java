/**
 * 
 */
package fr.fms.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.stream.Collectors;

import fr.fms.dao.DAOFactory;
import fr.fms.dao.Dao;
import fr.fms.entities.Article;
import fr.fms.entities.Category;
import fr.fms.entities.Order;
import fr.fms.entities.OrderItem;
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
	private static Dao<Order> orderDao;
	private static Dao<OrderItem> orderItemDao;

	public IShopBusinessImpl() {
		caddy = new HashMap<Integer,Article>();	
		articleDao=DAOFactory.getArticleDao();
		userDao=DAOFactory.getUserDao();
		categoryDao=DAOFactory.getCategoryDao();
		orderDao=DAOFactory.getOrderDao();
		orderItemDao=DAOFactory.getOrderItemDao();
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
	public int order(int idUser) {
		if(userDao.read(idUser) != null) {
			double total = getTotal(); 
			Order order = new Order(total, new Date(), idUser);
			if(orderDao.create(order)) {	//ajout en base de la commande
				for(Article article : caddy.values()) {	//ajout des commandes minifiées associées
					orderItemDao.create(new OrderItem(0, article.getIdArticle(), article.getQty(), article.getUnitaryPrice(), order.getIdOrder()));
				}
				return order.getIdOrder();
			}
		}
		
		return 0;
	}
	/**
	 * login user
	 * @param login
	 * @param password
	 * @return
	 */
	public User login(String login, String password) {

		User user = null;
		
		for (User u : getListUsers()) {
			if (u.getLogin().equalsIgnoreCase(login) && u.getPassword().equals(password)) {
				user=new User(u.getIdUser(),u.getLogin());
			}
		}
		return user;
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
	 * get orders
	 * @return
	 */
	public ArrayList<Order> getListOrder() {
		ArrayList<Order> orders = orderDao.readAll();
		return orders;
	}
	/**
	 * renvoi le total de la commande en cours
	 * @return total
	 */
	public double getTotal() {
		double [] total = {0};
		caddy.values().forEach((a) -> total[0] += a.getUnitaryPrice() * a.getQty()); 	
		return total[0];
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
	public void clearCart() {
		caddy.clear();		
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
