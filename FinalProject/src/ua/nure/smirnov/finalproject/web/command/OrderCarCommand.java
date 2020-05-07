package ua.nure.smirnov.finalproject.web.command;

import java.io.IOException;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.smirnov.finalproject.Controller.ClientMenuController;
import ua.nure.smirnov.finalproject.Controller.DAO.DAOInterfaceFunction;
import ua.nure.smirnov.finalproject.Controller.DAO.DAOInterfaceOrder;
import ua.nure.smirnov.finalproject.Controller.DAO.mySQL.DAOFunction;
import ua.nure.smirnov.finalproject.Controller.DAO.mySQL.DAOOrder;
import ua.nure.smirnov.finalproject.entity.Cars;
import ua.nure.smirnov.finalproject.entity.Client;
import ua.nure.smirnov.finalproject.entity.Function;
import ua.nure.smirnov.finalproject.entity.Orders;
import ua.nure.smirnov.finalproject.entity.Path;
import ua.nure.smirnov.finalproject.exception.AppException;
import ua.nure.smirnov.finalproject.exception.Message;

public class OrderCarCommand extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7836118296062860170L;
	private static final Logger LOG = Logger.getLogger(ClientMenuController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		HttpSession session = request.getSession();
		DAOInterfaceFunction DAOFunction = new DAOFunction();
		DAOInterfaceOrder daoOrder = new DAOOrder();

		String forward = null;
		Client client = (Client) session.getAttribute("client");
		String index = client.getPassport();
		List<Orders> list = new ArrayList<Orders>();
		try {
			list = daoOrder.selectOrderClient(index);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if (!list.isEmpty()) {
			for (Orders order : list) {

				if (!order.getOrderActivity().equals("no active")) {
					throw new AppException(Message.CLIENT_HAS_ORDER);
				}
			}

		}
		String role = (String) session.getAttribute("role");
		if (role == null) {
			role = "role";
		}
		if (role.contentEquals("role")) {
			LOG.trace("current state --> " + session.isNew());
			request.setAttribute("log", "Войти");
		} else {
			LOG.trace("current state --> " + session.isNew());
			request.setAttribute("log", "Выйти");
		}

		LocalDate dateStart = LocalDate.now();
		LocalDate dateFinish = LocalDate.parse(request.getParameter("calendar"));
		int date = (int) ChronoUnit.DAYS.between(dateStart, dateFinish);
		if (date <= 0) {
			throw new AppException(Message.DATA_INCORECT);
		}
		String function = request.getParameter("function");
		Cars car = (Cars) session.getAttribute("car");
		int idCar = car.getIdCars();
		int price = car.getPrice();
		price = price * date;
		Function fnctn = new Function();

		if (function.equals("Personal Driver")) {
			try {
				fnctn = DAOFunction.findFunctionForName(function);
			} catch (Exception e) {
				e.printStackTrace();
			}
			price = price + fnctn.getPrice();
		} else if (function.equals("Without Function")) {
			try {
				fnctn = DAOFunction.findFunctionForName(function);
			} catch (Exception e) {
				e.printStackTrace();
			}
			price = price + fnctn.getPrice();
		}

		Orders order = new Orders();
		order.setAddFunction(function);
		order.setDateStart(dateStart);
		order.setDateFinish(dateFinish);
		order.setPrice(price);
		order.setIndexClient(index);
		order.setIdCar(idCar);
		order.setOrderActivity("free");
		order.setStatus("unpaid");
		try {
			daoOrder.insertNewOrder(order);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return forward = Path.COMMAND_FIRST_MENU;
	}

}
