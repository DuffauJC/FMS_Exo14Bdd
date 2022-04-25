package fr.fms.dao;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import fr.fms.entities.Category;

public class CategoryDao implements Dao<Category> {

	private ArrayList<Category> categories;

	public CategoryDao() {
		categories=new ArrayList<Category>();
	}

	@Override
	public boolean create(Category obj) {
		String strSql="INSERT INTO T_Categories (CatName,Descriptions) VALUES( ?, ?);";	
		try(PreparedStatement ps =connection.prepareStatement(strSql)){ 
			ps.setString(1, obj.getCatName());
			ps.setString(2, obj.getDescription());
		
			if (ps.executeUpdate()==1) {
				System.out.println("insertion ok");
				return true;
			}
		} catch (SQLException e) {
			System.out.println("creation : Erreur ");
			
		}
		return false;
	}

	@Override
	public Category read(int id) {
		Category cat = null;
		String strSql="SELECT * FROM t_Categories WHERE IdCategory = ?;";		
		try(PreparedStatement ps =connection.prepareStatement(strSql)){ 
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery(); 
			while (rs.next()) {
				int rsidCategory=rs.getInt(1);  
				String rscatName=rs.getString(2);
				String rsdescription=rs.getString(3);
				
				cat=new Category(rsidCategory,rscatName,rsdescription);

			}

		} catch (SQLException e) {
			System.out.println("Lecture avec id : Erreur");
		}

		return cat;
	}



	@Override
	public boolean update(Category obj){

		String strSql="UPDATE t_Categories SET CatName = ?, Descriptions = ?, WHERE IdCategory = ? ;";	
		try(PreparedStatement ps =connection.prepareStatement(strSql)){ 
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

		String strSql="DELETE FROM t_Categories WHERE IdCategory = ?;";	
		try(PreparedStatement ps =connection.prepareStatement(strSql)){ 
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

		String strSql="SELECT * FROM T_Categories";						
		try(Statement statement =connection.createStatement()){
			try(ResultSet resultSet=statement.executeQuery(strSql)){  
				while (resultSet.next()) {
					int rsidCategory=resultSet.getInt(1);  
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



	@Override
	public ArrayList<Category> readByCategory(int id) {
		// TODO Auto-generated method stub
		return null;
	}



}
