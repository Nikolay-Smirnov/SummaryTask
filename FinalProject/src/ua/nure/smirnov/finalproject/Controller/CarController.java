package ua.nure.smirnov.finalproject.Controller;

import java.util.ArrayList;
import java.util.List;

import ua.nure.smirnov.finalproject.Controller.DAO.DAOInterfaceCar;
import ua.nure.smirnov.finalproject.Controller.DAO.mySQL.DAOCar;
import ua.nure.smirnov.finalproject.entity.Cars;
import ua.nure.smirnov.finalproject.exception.AppException;

public class CarController {
	private List<Cars> carList = new ArrayList<>();
	private String carBrand;
	private String levelCar;
	private String carModel;
	private String carModelAndBrand;

	public String getCarModelAndBrand() {
		return carModelAndBrand;
	}

	public void setCarModelAndBrand(String carModelAndBrand) {
		this.carModelAndBrand = carModelAndBrand;
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public String getCarBrand() {
		return carBrand;
	}

	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}

	public String getLevelCar() {
		return levelCar;
	}

	public void setLevelCar(String levelCar) {
		this.levelCar = levelCar;
	}

	public List<Cars> getCarList() {
		return carList;
	}

	public void setCarList(List<Cars> carList) {
		this.carList = carList;
	}

	public List<Cars> getSortCarMenu() throws Exception {
		DAOInterfaceCar daoCar = new DAOCar();
		List<Cars> list = getCarList();
		if (list == null) {
			try {
				list = daoCar.selectAllCarsByStatus();
			} catch (AppException e) {
				e.printStackTrace();
			}
		}


		String brand = getCarBrand();
		String model = getCarModel();
		String modelAndBrand = getCarModel();
		if (brand != "") {
			List<Cars> selectCars = new ArrayList<Cars>();
			for (Cars car : list) {
				if (car.getCarBrand().equals(brand)) {
					selectCars.add(car);
				}
			}
			if (!selectCars.isEmpty()) {
				return selectCars;
			}
			for (Cars car : list) {
				if (car.getModel().equals(model)) {
					selectCars.add(car);
				}
			}
			if (!selectCars.isEmpty()) {
				return selectCars;
			}
			if (modelAndBrand != "") {
				for (Cars car : list) {
					if (modelAndBrand.contentEquals(car.getCarBrand() + " " + car.getModel())) {
						selectCars.add(car);
					}
				}
			}
			if (!modelAndBrand.isEmpty()) {
				return selectCars;
			}
		}

		if (getLevelCar() != "") {
			List<Cars> selectCars = new ArrayList<Cars>();
			for (Cars car : list) {
				if (getLevelCar().equals(car.getCarLevel())) {
					selectCars.add(car);
				}

			}
			return selectCars;
		}

		return list;
	}

	public List<Cars> getAllCars() throws AppException {
		DAOInterfaceCar daoCar = new DAOCar();
		List<Cars> list = new ArrayList<>();

		try {
			list = daoCar.selectAllCars();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
