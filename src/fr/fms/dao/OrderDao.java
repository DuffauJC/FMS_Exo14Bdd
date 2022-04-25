package fr.fms.dao;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import fr.fms.entities.Order;

public class OrderDao implements Dao<Order> {

	private ArrayList<Order> orders;


	public OrderDao() {
		orders=new ArrayList<Order>();
	}
	
	@Override
	public boolean create(Order obj) {
		String str = "INSERT INTO T_Orders (Amount , IdUser) VALUES (?,?);";	
		try (PreparedStatement ps = connection.prepareStatement(str,Statement.RETURN_GENERATED_KEYS)){	
			ps.setDouble(1, obj.getAmount());
			ps.setInt(2, obj.getIdUser());
			ps.executeUpdate();
			try(ResultSet generatedKeySet = ps.getGeneratedKeys()){
				if(generatedKeySet.next()) {
					obj.setIdOrder(generatedKeySet.getInt(1));
					return true;
				}	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Order read(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Order obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Order> readAll() {
		String strSql="SELECT * FROM T_orders";						
		try(Statement statement =connection.createStatement()){
			try(ResultSet resultSet=statement.executeQuery(strSql)){  
				while (resultSet.next()) {
					int rsidOrder=resultSet.getInt(1);  
					double rsAmount=resultSet.getDouble(2);
					Date rsdate=resultSet.getDate(3);
					int rsidUser=resultSet.getInt(4);
					
					orders.add((new Order(rsidOrder,rsAmount,rsdate,rsidUser)));
				}
			}
		}
		catch (SQLException e) {
			System.out.println("Lecture table : Erreur");
		}

		return orders;
		
	}

	@Override
	public ArrayList<Order> readByCategory(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
