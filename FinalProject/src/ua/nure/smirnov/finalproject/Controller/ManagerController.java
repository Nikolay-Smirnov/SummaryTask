package ua.nure.smirnov.finalproject.Controller;

import java.util.ArrayList;
import java.util.List;

import ua.nure.smirnov.finalproject.Controller.DAO.DAOInterfaceManager;
import ua.nure.smirnov.finalproject.Controller.DAO.mySQL.DAOManager;
import ua.nure.smirnov.finalproject.entity.Manager;
import ua.nure.smirnov.finalproject.exception.AppException;

public class ManagerController {

	private int idManager;

	public int getIdManager() {
		return idManager;
	}

	public void setIdManager(int idManager) {
		this.idManager = idManager;
	}

	public List<Manager> getAllManagers() throws AppException {
		DAOInterfaceManager daoManager = new DAOManager();
		List<Manager> list = new ArrayList<Manager>();
		List<Manager> listManager = new ArrayList<Manager>();

		try {
			list = daoManager.selectAllManagers();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (Manager mngr : list) {
			if (mngr.getStatus().contentEquals("work")) {
				listManager.add(mngr);
			}
		}
		return listManager;
	}

	public List<Manager> getManagerById() throws AppException {
		DAOInterfaceManager daoManager = new DAOManager();
		List<Manager> list = new ArrayList<Manager>();
		List<Manager> listManager = new ArrayList<Manager>();

		try {
			list = daoManager.selectAllManagers();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (Manager mngr : list) {
			if (mngr.getId() == getIdManager()) {
				listManager.add(mngr);
			}
		}
		return listManager;
	}

	public List<Manager> getWorkedManagers() throws AppException {
		DAOInterfaceManager daoManager = new DAOManager();
		List<Manager> list = new ArrayList<Manager>();
		List<Manager> listManager = new ArrayList<Manager>();

		try {
			list = daoManager.selectAllManagers();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (Manager mngr : list) {
			if (mngr.getStatus().contentEquals("work") && mngr.getId() != getIdManager()) {
				listManager.add(mngr);
			}
		}
		return listManager;
	}

}
