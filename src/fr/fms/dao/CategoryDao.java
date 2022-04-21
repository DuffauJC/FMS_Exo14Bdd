package fr.fms.dao;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import fr.fms.entities.Category;

public class CategoryDao implements Dao<Category> {

	private ArrayList<Category> categories;

	/**
	 * constructeur
	 */
	public CategoryDao() {
		categories=new ArrayList<Category>();
	}



	@Override
	public void create(Category obj) {
		String strSql="INSERT INTO T_Categories (CatName,Descriptions) VALUES( ?, ?);";						// une fois connecté, réalisation d'un requête
		try(PreparedStatement ps =connection.prepareStatement(strSql)){ // de java.sql
			ps.setString(1, obj.getCatName());
			ps.setString(2, obj.getDescription());
		
		
			if (ps.executeUpdate()==1) {
				System.out.println("insertion ok");
			}
		} catch (SQLException e) {
			System.out.println("creation : Erreur ");
			
		}
	}

	@Override
	public Category read(int id) {
		Category art = null;
		String strSql="SELECT * FROM t_Categories WHERE IdCategory = ?;";		// une fois connecté, réalisation d'un requête
		try(PreparedStatement ps =connection.prepareStatement(strSql)){ // de java.sql
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();  // ResultSet de java.sql
			while (rs.next()) {
				int rsidCategory=rs.getInt(1);  // soit index(de 1 à n) de la colonne, soit le nom de la colonne
				String rscatName=rs.getString(2);
				String rsdescription=rs.getString(3);
				
				art=new Category(rsidCategory,rscatName,rsdescription);

			}

		} catch (SQLException e) {
			System.out.println("Lecture avec id : Erreur");
		}

		return art;
	}



	@Override
	public boolean update(Category obj){

		String strSql="UPDATE t_Categories SET CatName = ?, Descriptions = ?, WHERE IdCategory = ? ;";						// une fois connecté, réalisation d'un requête
		try(PreparedStatement ps =connection.prepareStatement(strSql)){ // de java.sql
			ps.setString(1, obj.getCatName());
			ps.setString(2, obj.getDescription());
			ps.setInt(3, obj.getIdCategory());
			
			if (ps.executeUpdate()==1) {
				System.out.println("Mise à jour réussie.");
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Mise à jour : Erreur");
		}
		return false;
	}

	@Override
	public boolean delete(int id) {

		String strSql="DELETE FROM t_Categories WHERE IdCategory = ?;";						// une fois connecté, réalisation d'un requête
		try(PreparedStatement ps =connection.prepareStatement(strSql)){ // de java.sql
			ps.setInt(1, id);
			
			if (ps.executeUpdate()==1) {
				System.out.println("Suppression réussie.");
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Suppression : Erreur");
		}

		return false;
	}

	@Override
	public ArrayList<Category> readAll() {

		String strSql="SELECT * FROM T_Categories";						// une fois connecté, réalisation d'un requête
		try(Statement statement =connection.createStatement()){
			try(ResultSet resultSet=statement.executeQuery(strSql)){   // ResultSet de java.sql
				while (resultSet.next()) {
					int rsidCategory=resultSet.getInt(1);  // soit index(de 1 à n) de la colonne, soit le nom de la colonne
					String rscatname=resultSet.getString(2);
					String rsdescription =resultSet.getString(3);
			
					categories.add((new Category(rsidCategory,rscatname,rsdescription)));
				}
			}
		}
		catch (SQLException e) {
			System.out.println("Lecture table : Erreur");
		}

		return categories;
	}



}
