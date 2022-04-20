package fr.fms.dao;

import fr.fms.entities.Article;
import fr.fms.entities.User;

public class DAOFactory {

	public static Dao<Article> getArticleDao() {
		return new ArticleDao();
	}
	
	public static Dao<User> getUserDao() {
		return new UserDao();
	}

}
