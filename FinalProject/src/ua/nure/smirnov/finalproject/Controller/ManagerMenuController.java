package ua.nure.smirnov.finalproject.Controller;

import java.io.IOException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.smirnov.finalproject.Controller.DAO.DAOInterfaceCar;
import ua.nure.smirnov.finalproject.Controller.DAO.DAOInterfaceDamageBill;
import ua.nure.smirnov.finalproject.Controller.DAO.DAOInterfaceOrder;
import ua.nure.smirnov.finalproject.Controller.DAO.mySQL.DAOCar;
import ua.nure.smirnov.finalproject.Controller.DAO.mySQL.DAODamageBill;
import ua.nure.smirnov.finalproject.Controller.DAO.mySQL.DAOOrder;
import ua.nure.smirnov.finalproject.entity.Cars;
import ua.nure.smirnov.finalproject.entity.DamageBill;
import ua.nure.smirnov.finalproject.entity.DamageBillForManager;
import ua.nure.smirnov.finalproject.entity.Manager;
import ua.nure.smirnov.finalproject.entity.ManagerList;
import ua.nure.smirnov.finalproject.entity.Orders;
import ua.nure.smirnov.finalproject.entity.Path;
import ua.nure.smirnov.finalproject.exception.AppException;
import ua.nure.smirnov.finalproject.exception.Message;
import ua.nure.smirnov.finalproject.web.command.Command;

public class ManagerMenuController extends Command {

	/**
	 * 
	 */
	private static final Logger LOG = Logger.getLogger(ManagerMenuController.class);
	private static final long serialVersionUID = 2451558511511391222L;
	public static final String payStatusUnpaid = "unpaid";
	public static final String payStatusPaid = "paid";
	public static final String empty = "";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		DAOInterfaceOrder daoOrder = new DAOOrder();
		DAOInterfaceDamageBill daoDamage = new DAODamageBill();
		HttpSession session = request.getSession();

		Manager managerSession = (Manager) session.getAttribute("manager");
		DAOInterfaceCar daoCar = new DAOCar();
		String forward = Path.COMMAND_ERROR_PAGE;

		if (request.getParameter("findClient") != null) {
			String indexClient = request.getParameter("pasportClient");
			String loginClient = request.getParameter("pasportClient");
			if (("").contentEquals(indexClient)) {
				throw new AppException(Message.FIELD_IS_EMPTY);
			}
			request.setAttribute("indexClient", indexClient);
			request.setAttribute("loginClient", loginClient);
			forward = Path.COMMAND_MENU_MANAGER_OLD_ORDERS;
		}

		if (request.getParameter("findClientOrder") != null) {
			String indexClient = request.getParameter("passportClient");
			String loginClient = request.getParameter("passportClient");
			if (("").equals(indexClient)) {
				throw new AppException(Message.FIELD_IS_EMPTY);
			}
			request.setAttribute("loginClient", loginClient);
			request.setAttribute("indexManager", session.getAttribute("index"));
			LOG.trace("Current manager's passport --> " + session.getAttribute("index"));
			request.setAttribute("indexClient", indexClient);
			forward = Path.COMMAND_MENU_MANAGER;
		}

		if (request.getParameter("rejectOrder") != null) {
			int id = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("index", session.getAttribute("index"));
			session.setAttribute("idOrder", id);
			Orders order = new Orders();
			try {
				order = daoOrder.findOrderById(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (("paid").equals(order.getStatus())) {
				throw new AppException(Message.MANAGER_DELETE_PAID_ORDER);
			}
			forward = Path.MANAGER_RETURN_ORDER;
		}

		if (request.getParameter("menu") != null) {
			List<Cars> list = new ArrayList<Cars>();
			request.setAttribute("index", session.getAttribute("index"));
			try {
				list = daoCar.selectAllCars();
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("cars", list);
			request.setAttribute("log", "Выйти");
			return forward = Path.COMMAND_MENU;
		}

		if (request.getParameter("allOrders") != null) {
			return forward = Path.COMMAND_MENU_MANAGER_FREE_ORDERS;
		}

		if (request.getParameter("returnOrder") != null) {
			String id = request.getParameter("id");
			if (id == null) {
				throw new AppException(Message.ORDER_DID_NOT_PICK);
			}
			int idOrder = Integer.parseInt(id);

			LOG.trace("current idOrder's --> " + idOrder);
			Orders order = new Orders();
			try {
				order = daoOrder.findOrderById(idOrder);
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			List<DamageBillForManager> list = null;
			try {
				list = daoDamage.selectAllDamageBill();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			DamageBillForManager damage = new DamageBillForManager();
			for (DamageBillForManager bill : list) {
				if (bill.getIdOrder() == idOrder) {
					damage = bill;
				}
			}
			LOG.trace("Status of damage bill --> " + damage.getIdPayment());
			LOG.trace("Status of damage bill --> " + damage.getActivity());
			LOG.trace("Status of order --> " + order.getStatus());
			if (payStatusPaid.equals(order.getStatus()) && !payStatusUnpaid.equals(damage.getActivity())) {
				ManagerList mOrder = new ManagerList();
				try {
					daoOrder.findOrdersForManagerById(idOrder);
				} catch (Exception e) {
					e.printStackTrace();
				}
				LOG.trace("Status of damage bill --> " + damage.getStatus());
				request.setAttribute("order", order);
				request.setAttribute("mOrder", mOrder);
				session.setAttribute("idOrder", idOrder);
				forward = Path.MANAGER_RETURN_ORDER;
			} else {
				if (payStatusUnpaid.equals(damage.getActivity())) {
					throw new AppException(Message.MANAGER_DELETE_ORDER_WITH_DAMAGE);
				} else {
					throw new AppException(Message.MANAGER_GET_ORDER_FOR_DAMAGE);
				}
			}
		}

		if (request.getParameter("acceptReturn") != null) {
			int idOrder = (Integer) session.getAttribute("idOrder");
			String d = (String) request.getParameter("desc");
			request.setAttribute("index", session.getAttribute("index"));
			try {
				daoOrder.updateOrderActive(idOrder);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			try {
				daoOrder.updateDescriptionOrder(idOrder, d);
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("indexManager", managerSession.getPassport());
			forward = Path.COMMAND_MENU_MANAGER;
			session.removeAttribute("idOrder");
		}

		if (request.getParameter("managersCabinet") != null) {
			request.setAttribute("indexManager", managerSession.getPassport());
			forward = Path.COMMAND_MENU_MANAGER;
		}

		if (request.getParameter("damageOrders") != null) {
			List<DamageBillForManager> dList = null;
			try {
				dList = daoDamage.selectAllDamageBill();
			} catch (Exception e) {
				e.printStackTrace();
			}
			String index = (String) session.getAttribute("index");
			System.out.println(dList.isEmpty());
			request.setAttribute("index", index);
			request.setAttribute("damageBillList", dList);
			forward = Path.COMMAND_DAMAGE_ORDERS;

		}

		if (request.getParameter("acceptPaidBill") != null) {
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				daoDamage.updateDamageBillStatus(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("indexManager", managerSession.getPassport());
			forward = Path.COMMAND_MENU_MANAGER;
		}

		if (request.getParameter("acceptOrder") != null) {
			int id = Integer.parseInt(request.getParameter("id"));
			String index = (String) session.getAttribute("index");
			try {
				daoOrder.acceptOrder(id, index);
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("indexManager", managerSession.getPassport());
			forward = Path.COMMAND_MENU_MANAGER;
		}

		if (request.getParameter("myOldOrders") != null) {
			String index = (String) session.getAttribute("index");
			request.setAttribute("indexManager", index);
			forward = Path.COMMAND_MENU_MANAGER_OLD_ORDERS;
		}
		if (request.getParameter("allOldOrders") != null) {
			request.setAttribute("indexManager", empty);
			forward = Path.COMMAND_MENU_MANAGER_OLD_ORDERS;
		}

		if (request.getParameter("damage") != null) {
			int idOrder = Integer.parseInt(request.getParameter("id"));
			DamageBill bill = null;
			try {
				bill = daoDamage.findDamageBillByOrderId(idOrder);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			LOG.trace("current id of damage bill --> " + bill.getIdPayment());

			ManagerList nOrder = new ManagerList();
			try {
				nOrder = daoOrder.findOrdersForManagerById(idOrder);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (bill.getIdPayment() == 0 && payStatusPaid.equals(nOrder.getStatusOrder())) {
				session.setAttribute("idOrder", idOrder);
				request.setAttribute("order", nOrder);
				forward = Path.MANAGER_DAMAGE_BILL;
			} else if (bill.getIdPayment() != 0) {
				throw new AppException(Message.ORDER_HAVE_DAMAGE);
			} else {
				throw new AppException(Message.MANAGER_GET_ORDER_FOR_DAMAGE);
			}
		}

		if (request.getParameter("acceptDamage") != null) {
			DamageBill bill = new DamageBill();
			int idOrder = (Integer) session.getAttribute("idOrder");
			Orders order = new Orders();
			try {
				order = daoOrder.findOrderById(idOrder);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (order.getStatus() != "unpaid") {
				int price = Integer.parseInt(request.getParameter("price"));
				String desc = (String) request.getParameter("desc");
				LocalDate date = LocalDate.now();
				String indexClient = order.getIndexClient();
				String indexManager = order.getIndexManager();
				bill.setActivity("unpaid");
				bill.setDate(date);
				bill.setIdOrder(idOrder);
				bill.setDescription(desc);
				bill.setIndexClient(indexClient);
				bill.setIndexManager(indexManager);
				bill.setStatus("active");
				bill.setPrice(price);
				try {
					daoDamage.insertNewDamageBill(bill);
				} catch (Exception e) {
					e.printStackTrace();
				}
				request.setAttribute("indexManager", managerSession.getPassport());
				forward = Path.COMMAND_MENU_MANAGER;
				session.removeAttribute("idOrder");
			} else {
				throw new AppException(Message.MANAGER_GET_ORDER_FOR_DAMAGE);
			}
		}

		if (request.getParameter("managerCabinet") != null) {
			String index = (String) session.getAttribute("index");
			request.setAttribute("indexManager", index);
			forward = Path.COMMAND_MENU_MANAGER;
		}

		if (request.getParameter("acceptPay") != null) {
			int id = Integer.parseInt(request.getParameter("id"));
			Orders order = new Orders();
			try {
				order = daoOrder.findOrderById(id);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			request.setAttribute("indexManager", session.getAttribute("index"));
			if (order.getStatus().equals("unpaid")) {
				try {
					daoOrder.acceptPay(id);
				} catch (Exception e) {
					e.printStackTrace();
				}
				forward = Path.COMMAND_MENU_MANAGER;
			} else {
				throw new AppException(Message.MANAGER_ACCEPT_PAID_ORDER_PAY);
			}

		}

		return forward;
	}

}
