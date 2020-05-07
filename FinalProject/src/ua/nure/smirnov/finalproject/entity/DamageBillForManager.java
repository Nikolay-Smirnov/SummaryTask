package ua.nure.smirnov.finalproject.entity;

public class DamageBillForManager extends DamageBill {
	
	private int carId;
	private String modelCar;
	private String carBrand;
	private String loginClient;
	private String nameClient;
	private String surnameClient;
	private String numberClient;
	
	public String getNumberClient() {
		return numberClient;
	}

	public void setNumberClient(String numberClient) {
		this.numberClient = numberClient;
	}

	public String getNameClient() {
		return nameClient;
	}

	public void setNameClient(String nameClient) {
		this.nameClient = nameClient;
	}

	public String getSurnameClient() {
		return surnameClient;
	}

	public void setSurnameClient(String surnameClient) {
		this.surnameClient = surnameClient;
	}

	public String getLoginClient() {
		return loginClient;
	}

	public void setLoginClient(String loginClient) {
		this.loginClient = loginClient;
	}

	public String getCarBrand() {
		return carBrand;
	}

	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}

	public String getModel() {
		return modelCar;
	}

	public void setModel(String modelCar) {
		this.modelCar = modelCar;
	}

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

}
