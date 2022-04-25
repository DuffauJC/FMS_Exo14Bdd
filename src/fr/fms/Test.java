package fr.fms;


import fr.fms.dao.ArticleDao;
import fr.fms.dao.UserDao;
import fr.fms.entities.Article;
import fr.fms.entities.User;


public class Test {


	private static ArticleDao shop;	
	private static UserDao user;
	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) {

		shopInit();

		// insertion d'un article
		//Article art=new Article("Tapis de souris","Asus",45.99 );
		//shop.create(art);

		// mise à jour d'un article que je récupère
		//Article modifArticle=shop.read(4);
		//modif.setDescription("Tapis de douche");
		//modif.setBrand("Baleine bleue");
		//modif.setUnitaryPrice(499.99);
		//shop.update(modif);

		// suppression d'un article
		//shop.delete(4);

		// lecture d'un article en fonction de son identifiant
		/*
		 * Article readArticle=shop.read(16); if (readArticle!=null) {
		 * System.out.println("Affichage de l' "+readArticle.toString()); } else {
		 * System.out.println("Article inexistant"); }
		 */
		System.out.println("-------------------------------------------------------");


		// lecture de la table articles
		//System.out.println("Liste des articles : ");
		//for(Article a : shop.readAll())
		//System.out.println(a);
		System.out.println("-------------------------------------------------------");


		///////////////////////////////////////////// User

		// insertion d'un utilisateur
		//User util=new User("Donald","Daisy28" );
		//user.create(util);

		// mise à jour d'un utilisateur que je récupère
		//User modifUser=user.read(3);
		//modifUser.setPassword("Chewby82");
		//user.update(modifUser);

		// suppression d'un utilisateur
		//user.delete(5);

		// lecture d'un utilisateur en fonction de son identifiant

		//User readUser=user.read(4); if (readUser!=null) {
		//System.out.println("Affichage de l' "+readUser.toString()); } else {
		//System.out.println("Article inexistant"); }
		System.out.println("-------------------------------------------------------");

		// lecture de la table users
		System.out.println("Liste des utilisateurs : ");
		//for(User u : user.readAll())
		//System.out.println(u);
		System.out.println("-------------------------------------------------------");


	}
	/**
	 * initialisation de la boutique
	 */
	private static void shopInit() {
		// TODO Auto-generated method stub
		shop = new ArticleDao();
		user=new UserDao();
	}


}


