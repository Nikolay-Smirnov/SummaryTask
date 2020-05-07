package ua.nure.smirnov.finalproject.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.smirnov.finalproject.Controller.DAO.DAOInterfaceClient;
import ua.nure.smirnov.finalproject.Controller.DAO.DAOInterfaceDamageBill;
import ua.nure.smirnov.finalproject.Controller.DAO.DAOInterfaceOrder;
import ua.nure.smirnov.finalproject.Controller.DAO.mySQL.DAOClient;
import ua.nure.smirnov.finalproject.Controller.DAO.mySQL.DAODamageBill;
import ua.nure.smirnov.finalproject.Controller.DAO.mySQL.DAOOrder;
import ua.nure.smirnov.finalproject.entity.Client;
import ua.nure.smirnov.finalproject.entity.DamageBill;
import ua.nure.smirnov.finalproject.entity.DamageBillForClient;
import ua.nure.smirnov.finalproject.entity.Orders;
import ua.nure.smirnov.finalproject.entity.Path;
import ua.nure.smirnov.finalproject.exception.AppException;
import ua.nure.smirnov.finalproject.exception.Message;
import ua.nure.smirnov.finalproject.validator.Validator;
import ua.nure.smirnov.finalproject.web.command.Command;

public class ClientMenuController extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(ClientMenuController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		DAOInterfaceDamageBill daoDamageBill = new DAODamageBill();
		HttpSession session = request.getSession();
		DAOInterfaceOrder daoOrder = new DAOOrder();
		Validator validate = new Validator();
		Client clientSession = (Client) session.getAttribute("client");
		String index = (String) session.getAttribute("index");
		String forward = null;
		if (request.getParameter("oldOrder") != null) {
			request.setAttribute("indexClient", index);
			forward = Path.COMMAND_CLIENT_OLD_ORDERS;
		}
		if (request.getParameter("menu") != null) {
			return forward = Path.COMMAND_FIRST_MENU;
		}
		if (request.getParameter("payBillOrder") != null) {
			return forward = Path.COMMAND_ACCEPT_PAY;
		}
		if (request.getParameter("payBill") != null) {
			return forward = Path.COMMAND_ACCEPT_PAY_DAMAGE;
		}
		if (request.getParameter("acceptPay") != null) {
			String cvv = (String) request.getParameter("CVV");
			LOG.trace("Current cvv --> " + cvv);
			String numberCart = (String) request.getParameter("numberCart");
			LOG.trace("Current cvv --> " + numberCart);
			if (cvv == "" || numberCart == "") {
				throw new AppException(Message.FELDS_IS_EMPTY);
			}

			if (validate.validatorcardCVV(cvv) || validate.validatorcardNumber(numberCart)) {
				throw new AppException(Message.CART_DONT_CORRECT);
			}

			Orders order = new Orders();
			List<Orders> orderList = new ArrayList<Orders>();
			try {
				orderList = daoOrder.selectAllOrder();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			for (Orders o : orderList) {
				if (o.getIndexClient().contentEquals(clientSession.getPassport())
						|| ("active").contentEquals(o.getOrderActivity()) || ("unpaid").equals(o.getStatus())) {
					LOG.trace("Currnt idor" + o.getIdOrders());
					order = o;
				}
			}
			if (order.getStatus().equals("unpaid")) {
				try {
					daoOrder.acceptPay(order.getIdOrders());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			request.setAttribute("indexClient", clientSession.getPassport());
			forward = Path.COMMAND_MENU_CLIENT;
		}
		if (request.getParameter("acceptPayDamage") != null) {
			String cvv = (String) request.getParameter("CVV");
			LOG.trace("Current cvv --> " + cvv);
			String numberCart = (String) request.getParameter("numberCart");
			LOG.trace("Current cvv --> " + numberCart);
			if (("").contentEquals(cvv) || ("").contentEquals(numberCart)) {
				throw new AppException(Message.FELDS_IS_EMPTY);
			}

			if (validate.validatorcardCVV(cvv) || validate.validatorcardNumber(numberCart)) {
				throw new AppException(Message.CART_DONT_CORRECT);
			}
			Orders order = new Orders();
			List<Orders> orderList = new ArrayList<Orders>();
			try {
				orderList = daoOrder.selectAllOrder();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			for (Orders o : orderList) {
				if (o.getIndexClient().contentEquals(clientSession.getPassport())
						|| !("no active").contentEquals(o.getOrderActivity()) || !("unpaid").equals(o.getStatus())) {
					order = o;
				}
			}
			DamageBill db = new DamageBill();
			try {
				db = daoDamageBill.findDamageBillByOrderId(order.getIdOrders());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		
			try {
				LOG.trace("Current id bill" + db.getIdPayment());
				daoDamageBill.updateDamageBillStatus(db.getIdPayment());
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("indexClient", clientSession.getPassport());
			forward = Path.COMMAND_MENU_CLIENT;
		}

		if (request.getParameter("clientCabinet") != null) {
			request.setAttribute("indexClient", clientSession.getPassport());
			LOG.trace("clients passport ---> " + clientSession.getPassport());
			return forward = Path.COMMAND_MENU_CLIENT;
		}

		if (request.getParameter("myOrder") != null) {
			LOG.trace("clients passport ---> " + index);
			request.setAttribute("indexClient", index);
			return forward = Path.COMMAND_MENU_CLIENT;
		}

		if (request.getParameter("damageBill") != null) {
			Client client = (Client) session.getAttribute("client");
			request.setAttribute("indexClient", client.getPassport());
			return forward = Path.COMMAND_MENU_TO_CLIENT_DAMAGE_BILL;
		}

		return forward;

	}
}
