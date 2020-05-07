package ua.nure.smirnov.finalproject.Controller;

import java.util.ArrayList;
import java.util.List;

import ua.nure.smirnov.finalproject.Controller.DAO.DAOInterfaceDamageBill;
import ua.nure.smirnov.finalproject.Controller.DAO.mySQL.DAODamageBill;
import ua.nure.smirnov.finalproject.entity.DamageBillForClient;
import ua.nure.smirnov.finalproject.entity.DamageBillForManager;
import ua.nure.smirnov.finalproject.exception.AppException;
import ua.nure.smirnov.finalproject.exception.Message;

public class DamageBillController {

	private String index;
	private String indexClient;
	private List<DamageBillForManager> damageBillList;

	public void setIndexClient(String indexClient) {
		this.indexClient = indexClient;
	}

	public String getIndex() {
		return index;
	}

	public String getIndexClient() {
		return indexClient;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public List<DamageBillForManager> getDamageBillList() {
		return damageBillList;
	}

	public void setDamageBillList(List<DamageBillForManager> damageBillList) {
		this.damageBillList = damageBillList;
	}

	public List<DamageBillForManager> getAllDamageBill() {
		return null;
	}

	public List<DamageBillForClient> getDamageBillForClient() throws AppException {
		DAOInterfaceDamageBill daoDamage = new DAODamageBill();
		List<DamageBillForClient> list = new ArrayList<>();
		List<DamageBillForClient> listClient = new ArrayList<>();
		try {
			list = daoDamage.selectDamageBillForClient(getIndexClient());
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(DamageBillForClient dbfr : list) {
			if(("active").contentEquals(dbfr.getStatus())) {
				listClient.add(dbfr);
			}
		}
		return listClient;
	}

	public List<DamageBillForManager> getDamageBillForManager() throws AppException {
		List<DamageBillForManager> list = getDamageBillList();
		List<DamageBillForManager> damageBillList = new ArrayList<>();
		for (DamageBillForManager bill : list) {
			if (bill.getIndexManager().contentEquals(getIndex()) && bill.getStatus().contentEquals("active")) {
				damageBillList.add(bill);
			}
		}

		return damageBillList;
	}

}
