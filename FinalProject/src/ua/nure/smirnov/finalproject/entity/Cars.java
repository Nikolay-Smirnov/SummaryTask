package ua.nure.smirnov.finalproject.entity;

import java.io.Serializable;

public class Cars implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5090819278791568795L;
	private String model;
	private int price;
	private int idCars;
	private String carLevel;
	private String status;
	private int idAdmin;
	private String conditionCar;
	private String descriptionCar;
	private String carBrand;
	private String vin;
	
	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getCarBrand() {
		return carBrand;
	}

	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}

	public String getDescriptionCar() {
		return descriptionCar;
	}

	public void setDescriptionCar(String descriptionCar) {
		this.descriptionCar = descriptionCar;
	}

	public void setIdAdmin(int id) {
		idAdmin = id;
	}

	public String getConditionCar() {
		return conditionCar;
	}

	public void setConditionCar(String conditionCar) {
		this.conditionCar = conditionCar;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setCarLevel(String carLevel) {
		this.carLevel = carLevel;
	}

	public void setIdCar(int idCar) {
		this.idCars = idCar;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public String getModel() {
		return model;
	}

	public int getPrice() {
		return price;
	}

	public int getIdCars() {
		return idCars;
	}

	public String getCarLevel() {
		return carLevel;
	}

	public int getIdAdmin() {
		return idAdmin;
	}
}
