package ua.nure.smirnov.finalproject.Controller;

import java.util.ArrayList;
import java.util.List;

import ua.nure.smirnov.finalproject.Controller.DAO.DAOInterfaceClient;
import ua.nure.smirnov.finalproject.Controller.DAO.mySQL.DAOClient;
import ua.nure.smirnov.finalproject.entity.Client;
import ua.nure.smirnov.finalproject.exception.AppException;

public class ClientController {

	private String passportClient;
	private String loginClient;

	public String getLoginClient() {
		return loginClient;
	}

	public void setLoginClient(String loginClient) {
		this.loginClient = loginClient;
	}

	public String getPassportClient() {
		return passportClient;
	}

	public void setPassportClient(String passportClient) {
		this.passportClient = passportClient;
	}

	public List<Client> getAllClient() throws AppException {

		DAOInterfaceClient dao = new DAOClient();

		List<Client> list = null;
		try {
			list = dao.selectAllClient();
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Client> listClient = new ArrayList<>();
		for (Client cl : list) {
			if (("unblock").contentEquals(cl.getStatus())) {
				listClient.add(cl);
			}
		}
		return listClient;
	}

	public List<Client> getBlockedClient() throws AppException {
		DAOInterfaceClient dao = new DAOClient();
		List<Client> list = null;
		try {
			list = dao.selectAllClient();
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Client> listClient = new ArrayList<>();
		List<Client> listSortClient = new ArrayList<>();
		for (Client cl : list) {
			if (("unblock").equals(cl.getStatus())) {
				listClient.add(cl);
			}
		}
		if (!("").equals(getPassportClient())) {
			for (Client cl : listClient) {
				if (cl.getPassport().equals(getPassportClient())) {
					listSortClient.add(cl);
				}
			}
			if (listSortClient.isEmpty()) {
				for (Client cl : listClient) {
					if (getLoginClient().equals(cl.getLoginClient())) {
						listSortClient.add(cl);
					}
				}
			}
			return listSortClient;
		}

		return listClient;
	}

	public List<Client> getUnblockedClient() throws AppException {
		DAOInterfaceClient dao = new DAOClient();
		System.out.println("clients --> " + getPassportClient());
		List<Client> list = null;
		try {
			list = dao.selectAllClient();
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Client> listClient = new ArrayList<>();
		List<Client> listSortClient = new ArrayList<>();
		for (Client cl : list) {
			if (("block").equals(cl.getStatus())) {
				listClient.add(cl);
			}
		}
		if (!("").equals(getPassportClient())) {
			for (Client cl : listClient) {
				if (cl.getPassport().equals(getPassportClient())) {
					listSortClient.add(cl);
				}
			}
			if (listSortClient.isEmpty()) {
				for (Client cl : listClient) {
					if (getLoginClient().equals(cl.getLoginClient())) {
						listSortClient.add(cl);
					}
				}
			}
			return listSortClient;
		}
		return listClient;
	}
}
