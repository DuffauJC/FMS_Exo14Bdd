package fr.fms;

import java.util.ArrayList;
import java.util.Scanner;

import fr.fms.business.IShopBusinessImpl;
import fr.fms.entities.Article;
import fr.fms.entities.Category;
import fr.fms.entities.User;

public class ShopApp {


	private static IShopBusinessImpl shopJob;	

	/**
	 * main
	 * @param args
	 */
	public static void main(String[] args) {

		// initialisation
		shopJob = new IShopBusinessImpl();

		welcome();

		Scanner scan = new Scanner(System.in); 

		String login = "";
		String password = "";
		User userOk=null;

		while (true) {
			System.out.println("Type your login and password to access the menu");
			// Check if the account exists.
			userOk=	login(scan,login,password);

			if (userOk!=null) {
				mainFunction(userOk,scan);
			} else {
				System.out.println("Non-existent customer.");
			}

		}

	}

	/**
	 * welcome message poster
	 */
	private static void welcome() {
		System.out.println();
		System.out.println("************************************");
		System.out.println("   WELCOME TO SHOPPING-SHOPPANG");
		System.out.println("************************************");		
		System.out.println();
	}
	/**
	 * menu poster
	 */
	public static  void showMenu() {

		System.out.print("1.Ajouter au panier - ");
		System.out.print("2.Voir le panier - ");
		System.out.print("3.Modifier panier - ");
		System.out.print("4.Valider commande - ");
		System.out.print("5.Sortir \n");
	}
	/**
	 * principal function
	 * @param user
	 * @param scan
	 */
	public static void mainFunction(User user, Scanner scan) {

		int action=0;
		int index;

		System.out.println("Welcome "+user.getLogin()+", what do you want to do ?");

		while(action != 5) {
			try {

				showMenu();

				action = scan.nextInt();

				switch(action) {
				case 1 : // add to caddy

					// list of article categories
					ArrayList<Category>cat=	shopJob.getListCategory();
					System.out.println("List of catégories : ");
					cat.forEach(c->{
						System.out.println(c.toString());
					});
					cat.clear();
					// category choose
					System.out.println("Choose the category.");
					while(!scan.hasNextInt()) {
						System.out.println("The entered value is incorrect, enter a new entry.");
						scan.next();
					}
					index =scan.nextInt();
					// list of articles of the chosen category
					ArrayList<Article> art=shopJob.getArticleByCategory(index);
					System.out.println("List of articles in the category "+index+" : ");
					art.forEach(a->{
						System.out.println(a.toString());
					});
					art.clear();
					System.out.println("Type the reference to add.");

					while(!scan.hasNextInt()) {
						System.out.println("The entered value is incorrect, enter a new entry.");
						scan.next();
					}

					index =scan.nextInt();

					// read an article according to its identifier
					// if returned add to cart in business layer
					shopJob.ArticleById(index);

					break;

				case 2 : // show caddy
					caddy();

					break;

				case 3 : // update caddy
					displayCaddy(scan);

					break;

				case 4 : // validate basket
					displayCommand(scan);

					break;

				case 5 : // Exit account
					System.out.println("Exit shop.");
					break;

				default : System.out.println(" Wrong entry, your choice: "+action+" does not exist in the menu");

				}	

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}

	}
	/**
	 * login user
	 * @param scan
	 * @param login
	 * @param password
	 * @return
	 */
	public static User login(Scanner scan, String login, String password) {

		User user = null;
		login=scan.next(); 
		password=scan.next();
		for (User u : shopJob.getListUsers()) {
			if (u.getLogin().equals(login) && u.getPassword().equals(password)) {
				user=new User(u.getIdUser(),u.getLogin(),u.getPassword());
			}
		}
		return user;
	}
	/**
	 * show caddy
	 */
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
	/**
	 * Method that modifies the basket
	 * @param scan
	 */
	public static void displayCaddy(Scanner scan) {

		caddy();
		// caddy not empty, show menu
		if (!shopJob.readAll().isEmpty()) {

			int rep=1;


			while(rep != 3) {

				System.out.println("1 : To edit a quantity.");
				System.out.println("2 : Delete an item.");
				System.out.println("3 : Leave cart.");

				while(!scan.hasNextInt()) {
					System.out.println("The entered value is incorrect, enter a new entry.");
					scan.next();
				}
				rep = scan.nextInt();

				switch(rep) {
				case 1 : // Editing a quantity
					System.out.println("To modify a quantity enter ref.");
					int	index=scan.nextInt();

					System.out.println("Enter the new quantity.");
					int qty=scan.nextInt();

					shopJob.updateCaddy(index,qty);
					caddy();
					break;

				case 2 : // Delete an article by its ref
					System.out.println("To delete an article type its ref.");
					index=scan.nextInt();
					shopJob.deleteCaddy(index);
					caddy();
					break;

				case  3: // Exit 
					System.out.println("Basket exit.");
					break;

				default : System.out.println("Wrong entry, your choice: "+rep+" does not exist in the menu");
				}	
			}
		}

	}
/**
 * Method that validates the command
 * @param scan
 */
	public static void displayCommand(Scanner scan) {

		if (shopJob.readAll().isEmpty()) {
			System.out.println("-----------------------------");
			System.out.println("	You have no order.");
			System.out.println("-----------------------------");
		} else {
			caddy();
			// if order we can validate
			System.out.println("Validate the  command ? Y/N");
			String rep = scan.next().toUpperCase();

			if (rep.equals("Y")) {
				shopJob.order();
				System.out.println("Order validated.");
			} else {
				System.out.println("Cart still valid");

			}

		}

	}
}

