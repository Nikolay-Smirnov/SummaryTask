package ua.nure.smirnov.finalproject.web.Link;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.smirnov.finalproject.Controller.DBManager;
import ua.nure.smirnov.finalproject.Controller.DAO.DAOInterfaceCar;
import ua.nure.smirnov.finalproject.Controller.DAO.mySQL.DAOCar;
import ua.nure.smirnov.finalproject.entity.Cars;
import ua.nure.smirnov.finalproject.entity.Path;
import ua.nure.smirnov.finalproject.exception.AppException;
import ua.nure.smirnov.finalproject.exception.Message;
import ua.nure.smirnov.finalproject.web.command.Command;

public class LinkOrderCar extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		HttpSession session = request.getSession();
		DAOInterfaceCar daoCar = new DAOCar();
		String role = (String) session.getAttribute("role");
		if (role == null) {
			role = "role";
		}
		String forward = null;
		if (role.equals("client")) {
			forward = Path.PAGE_CAR_ORDER;
		} else if (role.equals("admin")) {

			throw new AppException(Message.ADMIN_TAKE_ORDER);

		} else if (role.equals("manager")) {

			throw new AppException(Message.MANAGER_TAKE_ORDER);

		} else {

			throw new AppException(Message.USER_NOT_REGISTER);

		}
		int id = Integer.parseInt(request.getParameter("id"));
		Cars car = new Cars();
		try {
			car = daoCar.findCarById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.setAttribute("car", car);
		return forward;
	}

}
