package ua.nure.smirnov.finalproject.Controller.DAO;

import java.util.List;

import ua.nure.smirnov.finalproject.entity.DamageBill;
import ua.nure.smirnov.finalproject.entity.Manager;
import ua.nure.smirnov.finalproject.entity.Orders;
import ua.nure.smirnov.finalproject.exception.AppException;

public interface DAOInterfaceManager {

	public void insertManager(Manager manager) throws Exception, AppException;

	public void changeManager(Orders order, String passport) throws Exception, AppException;

	public void firedManager(int id) throws Exception, AppException;

	public List<Manager> selectAllManagers() throws Exception, AppException;

	public Manager findManagerForLogin(String login) throws Exception, AppException;

	public Manager findManagerByPassport(String passport) throws Exception, AppException;

	public void changeManagerDamageBill(DamageBill db, String passport) throws Exception, AppException;;

}
