package ua.nure.smirnov.finalproject.Controller.DAO;

import java.util.List;

import ua.nure.smirnov.finalproject.entity.Cars;
import ua.nure.smirnov.finalproject.exception.AppException;

public interface DAOInterfaceCar {

	public List<Cars> selectAllCars() throws Exception, AppException;
	public void deleteCar(int id) throws Exception, AppException;
	public void updateCar(Cars car) throws Exception, AppException;
	public Cars findCarById(int id) throws Exception, AppException;
	public void insertCar(Cars car)throws Exception, AppException;
	
}
