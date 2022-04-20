package fr.fms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.fms.entities.Article;

public class ArticleDao implements Dao<Article> {

	private ArrayList<Article> articles;

	/**
	 * constructeur
	 */
	public ArticleDao() {
		articles=new ArrayList<Article>();
	}



	@Override
	public void create(Article obj) throws SQLException {
		String strSql="INSERT INTO T_Articles(Description, Brand, UnitaryPrice) VALUES(?, ?, ?);";						// une fois connecté, réalisation d'un requête
		try(PreparedStatement ps =connection.prepareStatement(strSql)){ // de java.sql
			ps.setString(1, obj.getDescription());
			ps.setString(2, obj.getBrand());
			ps.setDouble(3, obj.getUnitaryPrice());
			
			if (ps.executeUpdate()==1) {
				System.out.println("insertion ok");
			}
		} catch (SQLException e) {
			throw new SQLException("Erreur de création");
		}
	}

	@Override
	public Article read(int id) throws SQLException {
		Article art = null;
		String strSql="SELECT * FROM t_articles WHERE IdArticle = ?;";		// une fois connecté, réalisation d'un requête
		try(PreparedStatement ps =connection.prepareStatement(strSql)){ // de java.sql
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();  // ResultSet de java.sql
			while (rs.next()) {
				int rsidArticle=rs.getInt(1);  // soit index(de 1 à n) de la colonne, soit le nom de la colonne
				String rsdescription=rs.getString(2);
				String rsbrand=rs.getString(3);
				double rsunitaryPrice=rs.getDouble(4);

				art=new Article(rsidArticle,rsdescription,rsbrand,rsunitaryPrice);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Erreur de lecture.");
		}

		return art;
	}



	@Override
	public boolean update(Article obj) throws SQLException {

		String strSql="UPDATE t_articles SET description = ?, brand = ?, UnitaryPrice = ? WHERE IdArticle = ? ;";						// une fois connecté, réalisation d'un requête
		try(PreparedStatement ps =connection.prepareStatement(strSql)){ // de java.sql
			ps.setString(1, obj.getDescription());
			ps.setString(2, obj.getBrand());
			ps.setDouble(3, obj.getUnitaryPrice());
			ps.setInt(4, obj.getIdArticle());
			
			if (ps.executeUpdate()==1) {
				System.out.println("Mise à jour réussie.");
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Erreur de mise à jour.");
		}
		return false;
	}

	@Override
	public boolean delete(int id) throws SQLException {

		String strSql="DELETE FROM t_articles WHERE IdArticle = ?;";						// une fois connecté, réalisation d'un requête
		try(PreparedStatement ps =connection.prepareStatement(strSql)){ // de java.sql
			ps.setInt(1, id);
			
			if (ps.executeUpdate()==1) {
				System.out.println("Suppression réussie.");
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Erreur de suppréssion.");
		}

		return false;
	}

	@Override
	public ArrayList<Article> readAll() throws SQLException {

		String strSql="SELECT * FROM T_articles";						// une fois connecté, réalisation d'un requête
		try(Statement statement =connection.createStatement()){
			try(ResultSet resultSet=statement.executeQuery(strSql)){   // ResultSet de java.sql
				while (resultSet.next()) {
					int rsidArticle=resultSet.getInt(1);  // soit index(de 1 à n) de la colonne, soit le nom de la colonne
					String rsdescription =resultSet.getString(2);
					String rsbrand=resultSet.getString(3);
					double rsunitaryPrice=resultSet.getDouble(4);
					articles.add((new Article(rsidArticle,rsdescription,rsbrand,rsunitaryPrice)));
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Erreur de lecture de la table.");
		}

		return articles;
	}

}
