
package ua.nure.smirnov.finalproject.web.command;

import java.io.IOException;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.smirnov.finalproject.Controller.DAO.DAOInterfaceCar;
import ua.nure.smirnov.finalproject.Controller.DAO.mySQL.DAOCar;
import ua.nure.smirnov.finalproject.entity.Cars;
import ua.nure.smirnov.finalproject.entity.Client;
import ua.nure.smirnov.finalproject.entity.Path;
import ua.nure.smirnov.finalproject.exception.AppException;
import ua.nure.smirnov.finalproject.exception.Message;

public class MenuCommand extends Command {
	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(MenuCommand.class);

	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		HttpSession session = request.getSession();
		DAOInterfaceCar daoCar = new DAOCar();
		Client client = (Client) session.getAttribute("client");
		String role = (String) session.getAttribute("role");
		if (role == null) {
			role = "role";
		}
		String forward = null;

		String index = (String) session.getAttribute("index");
		if (request.getParameter("first") != null) {
			if (request.getParameter("first") != null) {
				if (role.equals("admin")) {
					forward = Path.PAGE_ADMIN_MENU;
				}
				if (role.equals("client")) {
					request.setAttribute("indexClient", index);
					forward = Path.COMMAND_MENU_CLIENT;
				}
				if (role.equals("manager")) {
					request.setAttribute("indexManager", index);
					request.setAttribute("index", index);
					forward = Path.PAGE_MANAGER_MENU;
				}
			} else {
				throw new AppException(Message.USER_NOT_REGISTER);
			}
		}
		String carBrand = request.getParameter("brandCar");
		LOG.trace("Cars's Brand --> " + carBrand);
		request.setAttribute("carBrand", carBrand);
		request.setAttribute("carModel", carBrand);
		request.setAttribute("carModelAndBrand", carBrand);

		List<Cars> carList = new ArrayList<>();
		try {
			carList = daoCar.selectAllCars();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (request.getParameter("exit") != null) {
			String log = (String) request.getParameter("exit");
			if (log.contentEquals("Выйти")) {
				LOG.trace("current state --> " + log);

				request.setAttribute("log", "Войти");
				session.invalidate();
				return forward = Path.COMMAND_MENU;
			} else if (log.contentEquals("Войти")) {
				LOG.trace("current state --> " + log);
				request.setAttribute("log", "Выйти");
				return forward = Path.COMMAND_MENU_LOGIN;
			}

		}

		if (request.getParameter("chooseLanguage") != null) {
			return forward = Path.COMMAND_CHOOSE_LANGUAGE;
		}

		if (request.getParameter("second") != null) {
			if (role.contentEquals("role")) {
				LOG.trace("current state --> " + session.isNew());
				request.setAttribute("log", "Войти");
			} else {
				LOG.trace("current state --> " + session.isNew());
				request.setAttribute("log", "Выйти");
			}
			LocalDate dateOrder = LocalDate.parse(request.getParameter("calendar"));
			LocalDate dateNow = LocalDate.now();
			int date = (int) ChronoUnit.DAYS.between(dateNow, dateOrder);
			if (date < 0) {
				throw new AppException(Message.DATA_INCORECT);
			}
			request.setAttribute("date", date);
			return forward = Path.COMMAND_MENU;
		}

		if (request.getParameter("findCar") != null) {
			if (role.contentEquals("role")) {
				LOG.trace("current state --> " + session.isNew());
				request.setAttribute("log", "Войти");
			} else {
				LOG.trace("current state --> " + session.isNew());
				request.setAttribute("log", "Выйти");
			}

			forward = Path.COMMAND_MENU;

		}

		if (request.getParameter("acceptSort") != null) {
			String sortToHigh = "sortToHigh";
			String sortToLow = "sortToLow";
			if (role.contentEquals("role")) {
				LOG.trace("current state --> " + session.isNew());
				request.setAttribute("log", "Войти");
			} else {
				LOG.trace("current state --> " + session.isNew());
				request.setAttribute("log", "Выйти");
			}

			if (sortToHigh.equals(request.getParameter("sort"))) {
				Collections.sort(carList, new Comparator<Cars>() {
					public int compare(Cars pr1, Cars pr2) {
						return pr1.getPrice() - pr2.getPrice();
					}
				});
				request.setAttribute("carList", carList);
				forward = Path.COMMAND_MENU;
			}

			if (sortToLow.equals(request.getParameter("sort"))) {
				Collections.sort(carList, new Comparator<Cars>() {
					public int compare(Cars pr1, Cars pr2) {
						return pr2.getPrice() - pr1.getPrice();
					}
				});
				forward = Path.COMMAND_MENU;
			}

			String carLevel = request.getParameter("carLevel");
			if (carLevel != "") {
				LOG.trace(carLevel);
				request.setAttribute("carLevel", carLevel);
			}
			request.setAttribute("carList", carList);
			forward = Path.COMMAND_MENU;
		}
		return forward;
	}

}
