package fr.fms;

import java.sql.SQLException;


import java.util.ArrayList;
import java.util.Scanner;

import fr.fms.business.IShopBusinessImpl;

import fr.fms.entities.Article;
import fr.fms.entities.Category;
import fr.fms.entities.User;


public class ShopApp {


	private static IShopBusinessImpl shopJob;	

	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) {
		shopJob = new IShopBusinessImpl();

		shopJob.shopInit();
		welcome();

		Scanner scan = new Scanner(System.in); 

		String login = "";
		String password = "";
		User userOk=null;

		while (true) {
			System.out.println("Tapez votre login et mot de passe pour accéder au menu.");
			// Verfie si le compte existe.
			userOk=	shopJob.login(scan,login,password);

			if (userOk!=null) {
				mainFunction(userOk,scan);
			} else {
				System.out.println("Client inexistant.");
			}

		}

		////////////// Methodes ////////////
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
					ArrayList<Article>art=	shopJob.getListArticles();
					System.out.println("Liste des articles : ");
					for(Article a : art)
						System.out.println(a);
					System.out.println("Tapez la référence à ajouter.");

					while(!scan.hasNextInt()) {
						System.out.println("La valeur rentrée est incorrecte, saisir une nouvelle entrée.");
						scan.next();
					}

					index =scan.nextInt();

					// lecture d'un article en fonction de son identifiant
					Article readArticle=shopJob.getArticleById(index);
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
			shopJob.readAll().forEach((value)->{	
				System.out.println("Ref : "+value.getIdArticle()+" "+value.getDescription()+" "+value.getBrand()+" "+value.getUnitaryPrice()+" $ "+" qty : "+value.getQty());
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

					System.out.println("Entrez la nouvelle quantité.");
					int qty=scan.nextInt();
					
					shopJob.updateCaddy(index,qty);
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

