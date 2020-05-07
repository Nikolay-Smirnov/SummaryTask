package ua.nure.smirnov.finalproject.Controller.DAO;

import java.util.List;

import ua.nure.smirnov.finalproject.entity.Admins;
import ua.nure.smirnov.finalproject.exception.AppException;

public interface DAOInterfaceAdmin {
	public void blockClient(int id) throws Exception, AppException;
	public void unblockClient(int id) throws Exception, AppException;
	public void insertNewAdmin(Admins admin) throws Exception, AppException;
	public List<Admins> selectAllAdmins() throws Exception, AppException;
	
}
