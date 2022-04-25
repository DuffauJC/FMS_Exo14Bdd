package fr.fms.dao;

import fr.fms.entities.Article;
import fr.fms.entities.Category;
import fr.fms.entities.Order;
import fr.fms.entities.OrderItem;
import fr.fms.entities.User;

public class DAOFactory {

	public static Dao<Article> getArticleDao() {
		return new ArticleDao();
	}
	
	public static Dao<User> getUserDao() {
		return new UserDao();
	}
	
	public static Dao<Category> getCategoryDao() {
		return new CategoryDao();
	}
	
	public static Dao<Order> getOrderDao() {
 		return new OrderDao();
 	}
 	
 	public static Dao<OrderItem> getOrderItemDao() {
 		return new OrderItemDao();
 	}

}
