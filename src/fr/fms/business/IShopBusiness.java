/**
 * 
 */
package fr.fms.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import fr.fms.entities.Article;



/**
 * @author Stagiaires08P
 *
 */
public interface IShopBusiness {

	public void addCaddy(Article article);					
	public Article readCaddy(int id);						
	public boolean updateCaddy(int id,int qty);			
	public boolean deleteCaddy(int id);					
	public void order() ;									
	public ArrayList<Article> readAll(); 				
	
}
