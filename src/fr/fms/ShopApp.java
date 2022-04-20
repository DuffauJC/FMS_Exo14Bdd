package fr.fms;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Scanner;

import fr.fms.business.IShopBusinessImpl;
import fr.fms.dao.ArticleDao;

import fr.fms.dao.UserDao;
import fr.fms.entities.Article;
import fr.fms.entities.User;


public class ShopApp {

	private static ArticleDao article;	
	private static UserDao user;
	private static ArrayList<User> users;
	private static IShopBusinessImpl shopJob;	

	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) {

		try {
			shopInit();
			welcome();

			Scanner scan = new Scanner(System.in); 

			String login = "";
			String password = "";
			User userOk=null;

			while (true) {
				System.out.println("Tapez votre login et mot de passe pour accéder au menu.");
				// Verfie si le compte existe.
				userOk=	scanLogin(scan,login,password);

				if (userOk!=null) {
					mainFunction(userOk,scan);
				} else {
					System.out.println("Client inexistant.");
				}

			}


		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		////////////// Methodes ////////////
	}
	// lecture de la table articles
	private static void listArticles() throws SQLException {

		System.out.println("Liste des articles : ");
		for(Article a : article.readAll())
			System.out.println(a);

	}

	private static User scanLogin(Scanner scan, String login, String password) {
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
	/**
	 * initialisation de la boutique
	 * @throws SQLException 
	 */
	private static void shopInit() throws SQLException {
		// TODO Auto-generated method stub
		article = new ArticleDao();
		user=new UserDao();
		shopJob = new IShopBusinessImpl();
		users=user.readAll();

	}

	/**
	 * affiche message bienvenue
	 */
	private static void welcome() {
		System.out.println();
		System.out.println("************************************");
		System.out.println("   BIENVENU CHEZ SHOPPING-SHOPPANG");
		System.out.println("************************************");		
		System.out.println();
	}
	/**
	 * affichage du menu
	 */
	public static  void showMenu() {

		System.out.print("1.Ajouter au panier - ");
		System.out.print("2.Voir le panier - ");
		System.out.print("3.Modifier panier - ");
		System.out.print("4.Valider commande - ");
		System.out.print("5.Sortir \n");
	}

	public static void mainFunction(User user, Scanner scan) {

		int action=0;
		int index;

		System.out.println("Bienvenue "+user.getLogin()+" que souhaitez-vous faire ?");

		while(action != 5) {
			try {

				// Affichage du menu
				showMenu();

				action = scan.nextInt();

				switch(action) {
				case 1 : // ajouter au panier
					listArticles();
					System.out.println("Tapez la référence à ajouter.");

					while(!scan.hasNextInt()) {
						System.out.println("La valeur rentrée est incorrecte, saisir une nouvelle entrée.");
						scan.next();
					}

					index =scan.nextInt();

					// lecture d'un article en fonction de son identifiant
					Article readArticle=article.read(index); 
					if (readArticle!=null) {
						shopJob.addCaddy(readArticle);
					} 
					else {
						System.out.println("Article inexistant"); 
					}

					break;

				case 2 : // voir panier
					caddy();

					break;

				case 3 : // modifier panier
					displayCaddy(scan);

					break;

				case 4 : // valider panier
					displayCommand(scan);

					break;

				case 5 : // Exit account
					System.out.println("Exit shop.");
					break;

				default : System.out.println("Mauvaise saisie, votre choix : "+action+" est inexistant dans le menu");
				}	


			} catch (Exception e) {
				System.out.println(e.getMessage());
			}


		}

	}
	private static void caddy() {
		if (shopJob.readAll().isEmpty()) {
			System.out.println("Panier inexistant.");
		} else {
			System.out.println("Articles du panier : ");
			shopJob.readAll().forEach((key,value)->{	
				System.out.println("Ref : "+key+" "+value.getDescription()+" "+value.getBrand()+" "+value.getUnitaryPrice()+" $ "+" qty : "+value.getQty());
			});
		}

	}
	/** M�thode qui affiche le panier  */
	public static void displayCaddy(Scanner scan) {

		caddy();
		// caddy not empty, show menu
		if (!shopJob.readAll().isEmpty()) {

			int rep=1;


			while(rep != 3) {

				System.out.println("1 : Pour modifier une quantité.");
				System.out.println("2 : Supprimer un article.");
				System.out.println("3 : Quitter le panier.");

				while(!scan.hasNextInt()) {
					System.out.println("La valeur rentrée n'était pas du type voulu");
					scan.next();
				}
				rep = scan.nextInt();

				switch(rep) {
				case 1 : // Modification d'une quantité
					System.out.println("Pour modifier une quantité sasir ref.");
					int	index=scan.nextInt();

					// mise à jour d'un article que je récupère
					Article modifArticle=shopJob.readCaddy(index);

					// modification de la quantitée de l'article
					System.out.println("Entrez la nouvelle quantité.");
					int qty=scan.nextInt();
					modifArticle.setQty(qty);
					shopJob.updateCaddy(modifArticle);
					caddy();
					break;

				case 2 : // Suppression d'un article par sa ref
					System.out.println("Pour supprimer un article taper sa ref.");
					index=scan.nextInt();
					shopJob.deleteCaddy(index);
					caddy();
					break;

				case  3: // Exit application
					System.out.println("Sortie panier.");
					break;

				default : System.out.println("mauvaise saisie, votre choix : "+rep+" est inexistant dans le menu");
				}	
			}
		}


	}
	/** M�thode qui affiche la commande  */
	public static void displayCommand(Scanner scan) {

		if (shopJob.readAll().isEmpty()) {
			System.out.println("-----------------------------");
			System.out.println("Vous n'avez pas de commande.");
			System.out.println("-----------------------------");
		} else {
			caddy();
			// si commande on peut valider
			System.out.println("Valider la commande Y/N ?");
			String rep = scan.next().toUpperCase();

			if (rep.equals("Y")) {
				shopJob.order();
				System.out.println("Commande validée.");
			} else {
				System.out.println("Panier toujours valide");

			}

		}

	}
}

