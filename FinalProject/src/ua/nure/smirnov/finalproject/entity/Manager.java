package ua.nure.smirnov.finalproject.entity;

import java.io.Serializable;
import java.time.LocalDate;

public class Manager implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String login;
	private String password;
	private String surname;
	private String age;
	private String name;
	private String role;
	private int idAdmin;
	private String passport;
	private String numberManager;
	private String status;
	private String sex;

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNumberManager() {
		return numberManager;
	}

	public void setNumberManager(String numberManager) {
		this.numberManager = numberManager;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setIdAdmin(int idAdmin) {
		this.idAdmin = idAdmin;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public int getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getAge() {
		return age;
	}

	public String getSurname() {
		return surname;
	}

	public String getRole() {
		return role;
	}

	public int getIdAdmin() {
		return idAdmin;
	}

	public String getPassport() {
		return passport;
	}
	
	@Override
	public String toString() {
		return "Clients [IdCars: " + id + ", " + "Login: " + login + ", " + "Password: " + password + ","
				+ "Surname: " + surname + "," + "Age: " + age + "," + "Name: " + name + "," + "Role: " + role + ","+ "IdAdmin: " + idAdmin
				+ "," + "Status: " + status + "," + "Passport: " + passport + "," + "Number of phone: " + numberManager
				+ "," + "Sex" + sex + "]";
	}
	
}
