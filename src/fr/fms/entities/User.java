package fr.fms.entities;

public class User {

	private int idUser;
	private String login;
	private String password;
	/**
	 * 
	 * @param idUser
	 * @param login
	 * @param password
	 */
	public User(int idUser, String login, String password) {
		setIdUser(idUser);
		setLogin(login);
		setPassword(password);
		
	}
	/**
	 * 
	 * @param login
	 * @param password
	 */
	public User( String login, String password) {
		setLogin(login);
		setPassword(password);
		
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Utilsateur [ idUser = " + idUser + ", login = " + login + ", password = " + password + " ]";
	}
	
}
