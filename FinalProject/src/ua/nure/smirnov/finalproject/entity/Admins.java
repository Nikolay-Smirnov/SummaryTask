package ua.nure.smirnov.finalproject.entity;

public class Admins {
	private int idAdmin;
	private String nameAdmin;
	private String loginAdmin;
	private String surnameAdmin;
	private String ageAdmin;
	private String passwordAdmin;
	private String role;
	private String passport;
	private String numberAdmin;
	private String numberOfPhone;
	private String sex;

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getNumberOfPhone() {
		return numberOfPhone;
	}

	public void setNumberOfPhone(String numberOfPhone) {
		this.numberOfPhone = numberOfPhone;
	}

	public String getNumberAdmin() {
		return numberAdmin;
	}

	public void setNumberAdmin(String numberAdmin) {
		this.numberAdmin = numberAdmin;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public void setNameAdmin(String nameAdmin) {
		this.nameAdmin = nameAdmin;
	}

	public void setLoginAdmin(String login_admin) {
		this.loginAdmin = login_admin;
	}

	public void setPasswordAdmin(String password_admin) {
		this.passwordAdmin = password_admin;
	}

	public void setAgeAdmin(String dataOfBorn) {
		ageAdmin = dataOfBorn;
	}

	public void setSurnameAdmin(String surnameAdmin) {
		this.surnameAdmin = surnameAdmin;
	}

	public void setIdAdmin(int id) {
		idAdmin = id;
	}

	public int getIdAdmin() {
		return idAdmin;
	}

	public String getNameAdmin() {
		return nameAdmin;
	}

	public String getSurnameAdmin() {
		return surnameAdmin;
	}

	public String getLoginAdmin() {
		return loginAdmin;
	}

	public String getPasswordAdmin() {
		return passwordAdmin;
	}

	public String getAgeAdmin() {
		return ageAdmin;
	}

	public String getRole() {
		return role;
	}

	public String getPassport() {
		return passport;
	}

	@Override
	public String toString() {
		return "Clients [Id admin: " + idAdmin + ", " + "Login: " + loginAdmin + ", " + "Password: " + passwordAdmin + ","
				+ "Surname: " + surnameAdmin + "," + "Age: " + ageAdmin + "," + "Name: " + nameAdmin + "," + "Role: " + role
				+ "," + "Passport: " + passport + "," + "Number of phone: " + numberOfPhone
				+ "," + "Sex" + sex + "]";
	}

}
