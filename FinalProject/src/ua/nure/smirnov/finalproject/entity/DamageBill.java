package ua.nure.smirnov.finalproject.entity;

import java.time.LocalDate;

public class DamageBill {
	private int idPayment;
	private int idOrder;
	private String indexClient;
	private String indexManager;
	private String status;
	private String activity;
	private int price;
	private String description;
	private LocalDate date;

	

	public int getIdPayment() {
		return idPayment;
	}

	public void setIdPayment(int idPayment) {
		this.idPayment = idPayment;
	}

	public int getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public String getIndexClient() {
		return indexClient;
	}

	public void setIndexClient(String indexClient) {
		this.indexClient = indexClient;
	}

	public String getIndexManager() {
		return indexManager;
	}

	public void setIndexManager(String indexManager) {
		this.indexManager = indexManager;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return "Clients [IdBill: " + idPayment + ", " + "Id order: " + idOrder + ", " + "Passport client: " + indexClient + ","
				+ "Passport manager: " + indexManager + "," + "Status order: " + status + "," + "Activity order: " + activity  + "," + "Price: " + price + ","+ "Description: " + description
				+ "," + "Date: " + date + "]";
	}
	
}
