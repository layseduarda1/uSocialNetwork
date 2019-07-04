/*
 *
 * IFPE - Federal Institute of Education, Science and Technology of Pernambuco
 * Informatics for the Internet
 * Object Oriented Programming
 * Professor: Allan Lima - allan.lima@igarassu.ifpe.edu.br
 * 
 * Public domain code, feel free to use, modify and redistribute it 
 *
 */

package br.edu.ifpe.igarassu.ipi.poo.usn.data.entity.user;

import br.edu.ifpe.igarassu.ipi.poo.usn.data.entity.Entity;

/**
 * 
 * Represents an user on the social network 
 * 
 * @author Allan Diego Silva Lima - allan.lima@igarassu.ifpe.edu.br
 *
 */
public class User extends Entity {
	// the name of the user
	private String name;
	
	// the passord of the user
	private String password;
	
	// the email of the user
	private String email;
	
	// the telefone of the user
	private int telefone;
	
	// the name of the user account
	private String name_account;
	

	/**
	 * 
	 * Initializes the classe's parameters with the given parameters 
	 * 
	 * @param id the id the of user
	 * @param name the name of the user
	 * @param password the password of the user
	 */
	public User(long id, String name, String password,String email, int telefone, String name_account) {
		super(id);
		this.setName(name);
		this.setPassword(password);
		this.setEmail(email);
		this.setName_account(name_account);
		this.setTelefone(telefone);
	}

	/**
	 * 
	 * Returns the name of the user
	 * 
	 * @return the name of the user
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * Sets the name of the user
	 * 
	 * @param name the new name of the user
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * Returns the password of the user
	 * 
	 * @return the password of the user
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 
	 * Sets the password of the user
	 * 
	 * @param password the new password of the user
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTelefone() {
		return telefone;
	}

	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}

	public String getName_account() {
		return name_account;
	}

	public void setName_account(String name_account) {
		this.name_account = name_account;
	}

}
