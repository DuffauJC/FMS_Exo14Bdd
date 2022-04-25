package fr.fms.dao;


import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import fr.fms.entities.User;

public class UserDao implements Dao<User> {

	private ArrayList<User> users;
	
	public UserDao() {
		users=new ArrayList<User>();
	}


	@Override
	public boolean create(User obj) {
		String strSql="INSERT INTO T_Users(Login, Password) VALUES(?, ?);";	
		try(PreparedStatement ps =connection.prepareStatement(strSql)){ 
			ps.setString(1, obj.getLogin());
			ps.setString(2, obj.getPassword());
			
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
	public User read(int id) {
		User user = null;
		String strSql="SELECT * FROM t_users WHERE IdUser = ?;";		
		try(PreparedStatement ps =connection.prepareStatement(strSql)){ 
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();  
			while (rs.next()) {
				int rsidUser=rs.getInt(1);  
				String rslogin =rs.getString(2);
				String rspassword=rs.getString(3);

				user=new User(rsidUser,rslogin,rspassword);

			}

		} catch (SQLException e) {
			System.out.println("Lecture avec id : Erreur");
		}

		return user;
	}

	@Override
	public boolean update(User obj) {
		String strSql="UPDATE t_users SET login = ?, password = ? WHERE IdUser = ? ;";						
		try(PreparedStatement ps =connection.prepareStatement(strSql)){ 
			ps.setString(1, obj.getLogin());
			ps.setString(2, obj.getPassword());
			ps.setDouble(3, obj.getIdUser());
		

			if (ps.executeUpdate()==1) {
				System.out.println("mise à jour ok");
				return true;
			}

		} catch (SQLException e) {
			System.out.println("Mise à jour : Erreur");
		}
		return false;
	}

	@Override
	public boolean delete(int id)  {
		String strSql="DELETE FROM t_users WHERE IdUser = ?;";	
		try(PreparedStatement ps =connection.prepareStatement(strSql)){ 
			ps.setInt(1, id);

			if (ps.executeUpdate()==1) {
				System.out.println("suppression ok");
				return true;
			}

		} catch (SQLException e) {
			System.out.println("Suppression : Erreur");
		}


		return false;
	}

	@Override
	public ArrayList<User> readAll()  {
		String strSql="SELECT * FROM T_users";						
		try(Statement statement =connection.createStatement()){
			try(ResultSet resultSet=statement.executeQuery(strSql)){  
				while (resultSet.next()) {
					int rsidUser=resultSet.getInt(1);  
					String rslogin =resultSet.getString(2);
					String rspassword=resultSet.getString(3);
					
					users.add((new User(rsidUser,rslogin,rspassword)));
				}
			}
		}
		catch (SQLException e) {
			System.out.println("Lecture table : Erreur");
		}

		return users;
	}

	@Override
	public ArrayList<User> readByCategory(int id) {
		// TODO Auto-generated method stub
		return null;
	}



}
