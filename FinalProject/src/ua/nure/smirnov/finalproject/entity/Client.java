package ua.nure.smirnov.finalproject.entity;

import java.io.Serializable;

public class Client implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String loginClient;
	private String password;
	private String surname;
	private String age;
	private String nameClient;
	private String role;
	private String status;
	private String passport;
	private String numberClient;
	private String sex;

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getNumberClient() {
		return numberClient;
	}

	public void setNumberClient(String numberClient) {
		this.numberClient = numberClient;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public void setName(String name) {
		this.nameClient = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setLoginClient(String login) {
		this.loginClient = login;
	}

	public void setIdClient(int id) {
		this.id = id;
	}

	public String getLoginClient() {
		return loginClient;
	}

	public int getIdClient() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public String getNameClient() {
		return nameClient;
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

	public String getStatus() {
		return status;
	}

	public String getPassport() {
		return passport;

	}

	@Override
	public String toString() {
		return "Clients [IdCars: " + id + ", " + "Login: " + loginClient + ", " + "Password: " + password + ","
				+ "Surname: " + surname + "," + "Age: " + age + "," + "Name: " + nameClient + "," + "Role: " + role
				+ "," + "Status: " + status + "," + "Passport: " + passport + "," + "Number of phone: " + numberClient
				+ "," + "Sex" + sex + "]";
	}
}
