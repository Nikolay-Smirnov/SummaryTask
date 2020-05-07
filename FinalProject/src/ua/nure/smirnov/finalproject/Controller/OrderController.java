package ua.nure.smirnov.finalproject.Controller;

import java.util.ArrayList;

import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.smirnov.finalproject.Controller.DAO.DAOInterfaceOrder;
import ua.nure.smirnov.finalproject.Controller.DAO.mySQL.DAOOrder;
import ua.nure.smirnov.finalproject.entity.ManagerList;
import ua.nure.smirnov.finalproject.entity.OrderList;
import ua.nure.smirnov.finalproject.exception.AppException;

public class OrderController {

	private static final Logger LOG = Logger.getLogger(OrderController.class);
	private String index;
	private int idOrder;
	private String indexClient;
	private String loginClient;

	public String getLoginClient() {
		return loginClient;
	}

	public void setLoginClient(String loginClient) {
		this.loginClient = loginClient;
	}

	private static final String inactive = "no active";

	public void setIndex(String index) {
		this.index = index;
	}

	public int getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public String getIndexClient() {
		return indexClient;
	}

	public void setIndexClient(String indexClient) {
		this.indexClient = indexClient;
	}

	public String getIndex() {
		return index;
	}

	public List<ManagerList> getManagerFreeOrders() throws AppException {
		List<ManagerList> list = new ArrayList<>();
		DAOInterfaceOrder daoOrder = new DAOOrder();
		try {
			list = daoOrder.selectFreeOrdersManager();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<ManagerList> getManagerOrders() throws AppException {
		List<ManagerList> list = new ArrayList<>();
		List<ManagerList> mList = new ArrayList<>();
		List<ManagerList> sortManagerList = new ArrayList<>();
		DAOInterfaceOrder daoOrder = new DAOOrder();
		String login = getLoginClient();
		String index = getIndexClient();
		try {
			list = daoOrder.selectAllOrders();
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOG.trace("current passport -->" + getIndexClient());
		LOG.trace("current login -->" + getLoginClient());

		for (ManagerList ml : list) {
			if (getIndex().equals(ml.getIndexManager()) && !inactive.equals(ml.getOrderActivity())) {
				mList.add(ml);

			}
		}
		if (!index.equals("")) {
			for (ManagerList m : mList) {
				if (m.getIndex().contentEquals(index)) {
					sortManagerList.add(m);
				}
			}
			if (sortManagerList.isEmpty()) {
				for (ManagerList m : mList) {
					if (login.contentEquals(m.getLoginClient())) {
						sortManagerList.add(m);
					}
				}
			}
			return sortManagerList;
		}
		return mList;
	}

	public List<ManagerList> getManagerOldOrders() throws AppException {
		List<ManagerList> list = new ArrayList<ManagerList>();
		List<ManagerList> managerList = new ArrayList<ManagerList>();
		List<ManagerList> sortManagerList = new ArrayList<ManagerList>();
		DAOInterfaceOrder daoOrder = new DAOOrder();

		try {
			list = daoOrder.selectAllOrders();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (("").equals(getIndex())) {

			managerList = list;
		}

		if (!list.isEmpty() && !("").equals(getIndex())) {
			for (ManagerList ml : list) {
				if (inactive.equals(ml.getOrderActivity()) && getIndex().equals(ml.getIndexManager())) {
					managerList.add(ml);
				}
			}
		}
		if (!("").equals(getIndexClient())) {
			if (!getIndexClient().equals("")) {
				for (ManagerList m : managerList) {
					if (m.getIndex().contentEquals(getIndexClient())) {
						sortManagerList.add(m);
					}
				}
				if (sortManagerList.isEmpty()) {
					for (ManagerList m : managerList) {
						if (getLoginClient().contentEquals(m.getLoginClient())) {
							sortManagerList.add(m);
						}
					}
				}
				return sortManagerList;
			}
		}

		return managerList;

	}

	public List<OrderList> getClientOldOrders() throws AppException {
		List<OrderList> ol = new ArrayList<OrderList>();
		DAOInterfaceOrder daoOrder = new DAOOrder();
		try {
			ol = daoOrder.selectOldOrdersForClient(getIndex());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ol;
	}

	public List<OrderList> getClientOrders() throws AppException {
		DAOInterfaceOrder daoOrder = new DAOOrder();
		List<OrderList> listClient = new ArrayList<>();
		try {
			listClient.add(daoOrder.selectOrderList(getIndex()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listClient;
	}

	public ManagerList getManagerList() throws AppException {
		DAOInterfaceOrder daoOrder = new DAOOrder();
		ManagerList order = new ManagerList();
		try {
			order = daoOrder.findOrdersForManagerById(getIdOrder());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return order;

	}

	public List<ManagerList> getOrderById() throws AppException {
		List<ManagerList> oList = new ArrayList<ManagerList>();
		DAOInterfaceOrder daoOrder = new DAOOrder();
		List<ManagerList> mList = new ArrayList<>();
		try {
			mList = daoOrder.selectAllOrders();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (ManagerList ml : mList) {
			if (ml.getIdOrder() == getIdOrder()) {
				oList.add(ml);
			}
		}
		return oList;
	}

}
