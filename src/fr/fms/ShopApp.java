package fr.fms;

import java.util.ArrayList;
import java.util.Scanner;

import fr.fms.business.IShopBusinessImpl;
import fr.fms.entities.Article;
import fr.fms.entities.Category;
import fr.fms.entities.User;

public class ShopApp {


	private static IShopBusinessImpl shopJob;	
	private static String login = "";
	private static String password = "";
	private static User userOk=null;

	/**
	 * main
	 * @param args
	 */
	public static void main(String[] args) {

		// initialisation
		shopJob = new IShopBusinessImpl();

		welcome();

		Scanner scan = new Scanner(System.in); 

		while (true) {
			showMenu();
			mainFunction(scan);

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

		// list of article categories
		ArrayList<Category>cat=	shopJob.getListCategory();
	
		System.out.println("List of catégories.\n");
		System.out.println("-------------------------------------------------------------------------------------------------------");
		System.out.printf("| %-15s | %-14s | %-65s |%n", "REF", "NOM", "DESCRIPTION");
		System.out.println("|-----------------|----------------|-------------------------------------------------------------------|");

		for (int i = 0; i < cat.size(); i++) {
			System.out.printf("| %-15s | %-14s | %-65s |%n", cat.get(i).getIdCategory(), cat.get(i).getCatName(),
					cat.get(i).getDescription());
		}
		System.out.println("-------------------------------------------------------------------------------------------------------");
		cat.clear();
		System.out.println("Welcome, what do you want to do ?");

		System.out.print("1.Ajouter au panier - ");
		System.out.print("2.Voir le panier - ");
		System.out.print("3.Modifier panier - ");
		System.out.print("4.Valider commande - ");
		System.out.print("5.Sortir \n");
		System.out.println("----------------------------");
	}
	/**
	 * principal function
	 * @param user
	 * @param scan
	 */
	public static void mainFunction(Scanner scan) {

		int action=0;
		int index;

		while(action != 5) {
			try {

				//showMenu();

				action = scan.nextInt();

				switch(action) {
				case 1 : // add to caddy

					// category choose
					System.out.println("Choose the category.");
					while(!scan.hasNextInt()) {
						System.out.println("The entered value is incorrect, enter a new entry.");
						scan.next();
					}
					index =scan.nextInt();
					
					// list of articles of the chosen category
					showChoosenCategory(index);
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

					System.out.println("Type your login and password to valid order"); 
					//Check if the account exists. 
					userOk= login(scan,login,password);

					if (userOk!=null) { displayCommand(userOk,scan);
					} else {
						System.out.println("Non-existent customer."); 
						}

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
	private static void showChoosenCategory(int index) {
		
		ArrayList<Article> art=shopJob.getArticlesByCategory(index);
		System.out.println("List of articles in the category "+index+" : ");
	
		System.out.println("----------------------------------------------------------------------------------------------------------");
		System.out.printf("| %-5s| %-25s | %-38s | %-20s |%-3s |%n", "NO.", "DESCRIPTION", "MARQUE", " PRIX"," QTE");
		System.out.println("|------|---------------------------|----------------------------------------|----------------------|-----|");

		for (int i = 0; i < art.size(); i++) {
			System.out.printf("| %-5s| %-25s | %-38s | %-20s | %-3s |%n",art.get(i).getIdArticle(), 
					art.get(i).getDescription(),
					art.get(i).getBrand(),art.get(i).getUnitaryPrice(),art.get(i).getQty());
		}			

		System.out.println("----------------------------------------------------------------------------------------------------------");
		art.clear();
		
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
		
		ArrayList<Article> caddy=shopJob.readCaddy();
	
		if (caddy.isEmpty()) {
			System.out.println("---------------------");
			System.out.println("Votre panier est vide");
			System.out.println("---------------------");
		} else {
			System.out.println("Caddy\n");
			System.out.println("--------------------------------------------------------------------------------------------");
			System.out.printf("| %-5s| %-15s | %-8s | %-47s |%-3s |%n", "NO.", "DESCRIPTION", "MARQUE", " PRIX"," QTE");
			System.out.println("|------|-----------------|----------|-------------------------------------------------|-----|");

			for (int i = 0; i < caddy.size(); i++) {
				System.out.printf("| %-5s| %-15s | %-8s | %-47s | %-3s |%n",caddy.get(i).getIdArticle(), 
						caddy.get(i).getDescription(),
						caddy.get(i).getBrand(),caddy.get(i).getUnitaryPrice(),caddy.get(i).getQty());
			}			

			System.out.println("---------------------------------------------------------------------------------------------");
			// calcul du montant de la commande
			double total=0;
			for (int i = 0; i < caddy.size(); i++) {
				double prix =caddy.get(i).getUnitaryPrice();
				double qty =caddy.get(i).getQty();

				double subTotal=prix*qty;
				total+=subTotal;
			}
			// affichage du total de la commande
			System.out.printf("| %81s | %-2s |%n"," TOTAL COMMANDE",total);
			System.out.println("---------------------------------------------------------------------------------------------");		

		}
		
	}
	/**
	 * Method that modifies the basket
	 * @param scan
	 */
	public static void displayCaddy(Scanner scan) {

		caddy();
		// caddy not empty, show menu
		if (!shopJob.readCaddy().isEmpty()) {

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
	 * @param userOk 
	 * @param scan
	 */
	public static void displayCommand(User userOk, Scanner scan) {

		if (shopJob.readCaddy().isEmpty()) {
			System.out.println("---------------------------------------");
			System.out.println("	You have no order "+userOk.getLogin());
			System.out.println("---------------------------------------");
		} else {
			caddy();
			// if order we can validate
			System.out.println("Validate the  command ? Y/N");
			String rep = scan.next().toUpperCase();

			if (rep.equals("Y")) {
				shopJob.order();
				System.out.println("Order validated "+userOk.getLogin());
			} else {
				System.out.println("Cart still valid");

			}

		}

	}
}

