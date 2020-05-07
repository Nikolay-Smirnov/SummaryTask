package ua.nure.smirnov.finalproject.web.Link;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import ua.nure.smirnov.finalproject.web.command.Command;

public class LinkToMenu extends Command{

	/**
	 * 
	 */
	private static final long serialVersionUID = -636451746905219200L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException{
		String forward = Path.PAGE_ERROR_PAGE;

		DAOInterfaceCar daoCar = new DAOCar();
		List<Cars> list = new ArrayList<Cars>();	
		try {
			list = daoCar.selectAllCarsByStatus();
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("carList", list);
		HttpSession session = request.getSession();
		if(session.isNew()) {
			request.setAttribute("log", "Войти");
		}else {
			request.setAttribute("log", "Выйти");
		}
		 forward = Path.PAGE_MENU;
		return forward;
	}
	

}
