package ua.nure.smirnov.finalproject.web.Link;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.smirnov.finalproject.Controller.DAO.DAOInterfaceCar;
import ua.nure.smirnov.finalproject.Controller.DAO.mySQL.DAOCar;
import ua.nure.smirnov.finalproject.entity.Admins;
import ua.nure.smirnov.finalproject.entity.Cars;
import ua.nure.smirnov.finalproject.entity.Path;
import ua.nure.smirnov.finalproject.exception.AppException;
import ua.nure.smirnov.finalproject.web.command.Command;

public class LinkUpdateCar extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		HttpSession session = request.getSession();
		DAOInterfaceCar daoCar = new DAOCar();
		Admins admin = (Admins) session.getAttribute("admin");
		String role = admin.getRole();
		int id = Integer.parseInt(request.getParameter("id"));
		String forward = Path.PAGE_ERROR_PAGE;
		if (role.equals("admin")) {
			forward = Path.PAGE_CAR_UPDATE;
		}
		Cars car = new Cars();
		try {
			car = daoCar.findCarById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("car", car);
		return forward;
	}

}
