package ua.nure.smirnov.finalproject.entity;

import java.time.LocalDate;

public class Orders {

	private int idOrders;
	private LocalDate dateFinish;
	private LocalDate dateStart;
	private int price;
	private String addFunction;
	private String indexClient;
	private String indexManager;
	private int idCar;
	private String status;
	private String descripton;

	public String getDescripton() {
		return descripton;
	}

	public void setDescripton(String descripton) {
		this.descripton = descripton;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrderActivity() {
		return orderActivity;
	}

	public void setOrderActivity(String orderActivity) {
		this.orderActivity = orderActivity;
	}

	private String orderActivity;

	public void setIndexClient(String indexClient) {
		this.indexClient = indexClient;
	}

	public void setIdCar(int idCar) {
		this.idCar = idCar;
	}

	public void setIndexManager(String indexManager) {
		this.indexManager = indexManager;
	}

	public void setIdOrders(int idOrders) {
		this.idOrders = idOrders;
	}

	public void setDateStart(LocalDate dateStart) {
		this.dateStart = dateStart;
	}

	public void setDateFinish(LocalDate dateFinish) {
		this.dateFinish = dateFinish;
	}

	public void setAddFunction(String addFunction) {
		this.addFunction = addFunction;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getIdOrders() {
		return idOrders;
	}

	public int getPrice() {
		return price;
	}

	public int getIdCar() {
		return idCar;
	}

	public LocalDate getDateStart() {
		return dateStart;
	}

	public LocalDate getDateFinish() {
		return dateFinish;
	}

	public String getFunction() {
		return addFunction;
	}

	public String getIndexClient() {
		return indexClient;
	}

	public String getIndexManager() {
		return indexManager;
	}

	@Override
	public String toString() {
		return "Clients [IdOrder: " + idOrders + ", " + "Data finish: " + dateFinish + ", " + "Data start: " + dateStart
				+ "," + "Price: " + price + "," + "Add function: " + addFunction + "," + "Activity order: "
				+ orderActivity + "," + "Index client : " + indexClient + "Index manager : " + indexManager + ","
				+ "Description : " + descripton + "Id car: " + idCar + "," + "Status order: " + status + "]";
	}
}
