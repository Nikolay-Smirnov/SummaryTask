package ua.nure.smirnov.finalproject.Controller.DAO;

import java.util.List;

import ua.nure.smirnov.finalproject.entity.DamageBill;
import ua.nure.smirnov.finalproject.entity.DamageBillForClient;
import ua.nure.smirnov.finalproject.entity.DamageBillForManager;
import ua.nure.smirnov.finalproject.exception.AppException;

public interface DAOInterfaceDamageBill {

	public DamageBill findDamageBillByOrderId(int id) throws Exception, AppException;

	public List<DamageBillForClient> selectDamageBillForClient(String passport) throws Exception, AppException;

	public List<DamageBillForManager> selectAllDamageBill() throws Exception, AppException;

	public void updateDamageBillStatus(int id) throws Exception, AppException;

	public void insertNewDamageBill(DamageBill bill) throws Exception, AppException;
	
}
