package ua.nure.smirnov.finalproject.entity;

import java.io.Serializable;
import java.time.LocalDate;

public class ManagerList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String index;
	private LocalDate startDate;
	private LocalDate finishDate;
	private String loginClient;

	private String nameClient;
	private String surnameClients;
	private int idOrder;
	private int idCar;
	private String brandCar;

	private String model;
	private String function;
	private int price;
	private String numberClient;
	private String description;
	private String indexManager;

	public String getIndexManager() {
		return indexManager;
	}

	public void setIndexManager(String indexManager) {
		this.indexManager = indexManager;
	}

	private String statusOrder;
	private String orderActivity;

	public String getStatusOrder() {
		return statusOrder;
	}

	public void setStatusOrder(String statusOrder) {
		this.statusOrder = statusOrder;
	}

	public String getOrderActivity() {
		return orderActivity;
	}

	public void setOrderActivity(String orderActivity) {
		this.orderActivity = orderActivity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBrandCar() {
		return brandCar;
	}

	public void setBrandCar(String brandCar) {
		this.brandCar = brandCar;
	}

	public String getNumberClient() {
		return numberClient;
	}

	public void setNumberClient(String numberManager) {
		this.numberClient = numberManager;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getLoginClient() {
		return loginClient;
	}

	public void setLoginClient(String loginClient) {
		this.loginClient = loginClient;
	}

	public int getIdCar() {
		return idCar;
	}

	public void setIdCar(int idCar) {
		this.idCar = idCar;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(LocalDate finishDate) {
		this.finishDate = finishDate;
	}

	public String getNameClient() {
		return nameClient;
	}

	public void setNameClient(String nameClient) {
		this.nameClient = nameClient;
	}

	public String getSurnameClients() {
		return surnameClients;
	}

	public void setSurnameClients(String surnamrClients) {
		this.surnameClients = surnamrClients;
	}

	public int getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

}
