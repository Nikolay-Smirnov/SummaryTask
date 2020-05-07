package ua.nure.smirnov.finalproject.entity;

public class DamageBillForClient extends DamageBill{
	private String nameManager;
	private String surnameManager;
	private String modelCar;
	private String carBrand;
	private String loginManager;
	private String numberManager;

	public String getNameManager() {
		return nameManager;
	}
	public void setNameManager(String nameManager) {
		this.nameManager = nameManager;
	}
	public String getSurnameManager() {
		return surnameManager;
	}
	public void setSurnameManager(String surnameManager) {
		this.surnameManager = surnameManager;
	}
	public String getModelCar() {
		return modelCar;
	}
	public void setModelCar(String modelCar) {
		this.modelCar = modelCar;
	}
	public String getCarBrand() {
		return carBrand;
	}
	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}
	public String getLoginManager() {
		return loginManager;
	}
	public void setLoginManager(String loginManager) {
		this.loginManager = loginManager;
	}
	public String getNumberManager() {
		return numberManager;
	}
	public void setNumberManager(String numberManager) {
		this.numberManager = numberManager;
	}
	
}
