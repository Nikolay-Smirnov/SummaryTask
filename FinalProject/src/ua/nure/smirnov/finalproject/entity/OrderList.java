package ua.nure.smirnov.finalproject.entity;

import java.time.LocalDate;

public class OrderList extends Orders {

	private String model;
	private int idOrder;
	private String nameManager;
	private String surname;
	private String loginManager;
	private LocalDate startDate;
	private LocalDate finishDate;
	private int orderPrice;
	private String function;
	private String numberManager;

	public String getNumberManager() {
		return numberManager;
	}

	public void setNumberManager(String numberManager) {
		this.numberManager = numberManager;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public void setNameManager(String nameManager) {
		this.nameManager = nameManager;
	}

	public void setSurnameManager(String surnameManager) {
		this.surname = surnameManager;
	}

	public void setLoginManager(String login) {
		loginManager = login;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public void setFinishDate(LocalDate finishDate) {
		this.finishDate = finishDate;
	}

	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public int getIdOrder() {
		return idOrder;
	}

	public String getModel() {
		return model;
	}

	public String getNameManager() {
		return nameManager;
	}

	public String getSurnameSurnameManager() {
		return surname;
	}

	public String getLoginManager() {
		return loginManager;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getFinishDate() {
		return finishDate;
	}

	public int getOrderPrice() {
		return orderPrice;
	}

	public String getFunction() {
		return function;
	}

}
