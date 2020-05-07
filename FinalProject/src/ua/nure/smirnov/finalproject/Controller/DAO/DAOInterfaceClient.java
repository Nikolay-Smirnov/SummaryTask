package ua.nure.smirnov.finalproject.Controller.DAO;

import java.util.List;

import ua.nure.smirnov.finalproject.entity.Client;
import ua.nure.smirnov.finalproject.exception.AppException;

public interface DAOInterfaceClient {

	public Client findUserForLogin(String login) throws Exception, AppException;
	public Client findUserForPassport(String passport) throws Exception, AppException;
	public void insertClient(Client client) throws Exception, AppException;
	public List<Client> selectClientByStatus(String status) throws Exception, AppException;
	public List<Client> selectAllClient() throws Exception, AppException;
	
}
