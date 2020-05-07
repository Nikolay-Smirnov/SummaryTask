package ua.nure.smirnov.finalproject.web.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.smirnov.finalproject.Controller.DBManager;
import ua.nure.smirnov.finalproject.Controller.OrderController;
import ua.nure.smirnov.finalproject.Controller.DAO.DAOInterfaceAdmin;
import ua.nure.smirnov.finalproject.Controller.DAO.DAOInterfaceClient;
import ua.nure.smirnov.finalproject.Controller.DAO.DAOInterfaceManager;
import ua.nure.smirnov.finalproject.Controller.DAO.mySQL.DAOAdmin;
import ua.nure.smirnov.finalproject.Controller.DAO.mySQL.DAOClient;
import ua.nure.smirnov.finalproject.Controller.DAO.mySQL.DAOManager;
import ua.nure.smirnov.finalproject.entity.Admins;
import ua.nure.smirnov.finalproject.entity.Client;
import ua.nure.smirnov.finalproject.entity.Manager;
import ua.nure.smirnov.finalproject.entity.Path;
import ua.nure.smirnov.finalproject.exception.AppException;
import ua.nure.smirnov.finalproject.exception.Message;

public class LoginCommand extends Command {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getLogger(LoginCommand.class);

	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException{
		HttpSession session = request.getSession();
		LOG.trace("START");
		String forward = Path.PAGE_ERROR_PAGE;
		DAOInterfaceClient dao = new DAOClient();
		DAOInterfaceManager daoManager = new DAOManager();
		try {
			DBManager db = DBManager.getInstance();

			Client client = new Client();
			Admins admin = new Admins();
			Manager manager = new Manager();

			String login = request.getParameter("login");
			LOG.trace("Request parameter: login  <---" + login);
			String password = request.getParameter("password");
			if (login.isEmpty() || password.isEmpty()) {
				throw new AppException(Message.LOGIN_PASSWORD_EMPTY);
			}

			client = dao.findUserForLogin(login);

			List<Admins> listAdm = new ArrayList<>();
			DAOInterfaceAdmin daoAdmin = new DAOAdmin();
			listAdm = daoAdmin.selectAllAdmins();
			
			for(Admins adm: listAdm) {
				if((login).contentEquals(adm.getLoginAdmin())) {
					admin = adm;
				}
			}

			manager = daoManager.findManagerForLogin(login);

			OrderController oc = new OrderController();
			if (client.getIdClient() != 0) {
				if (client.getPassword().equals(password)) {

					session.setAttribute("client", client);
					session.setAttribute("role", client.getRole());
					session.setAttribute("status", client.getStatus());
					session.setAttribute("index", client.getPassport());
					oc.setIndex(client.getPassport());
					LOG.trace("Current user  <---" + client.getStatus());
					if (client.getStatus().equals("block")) {
						throw new AppException(Message.USER_IS_BLOCKED);
					}
					forward = Path.COMMAND_FIRST_MENU;
				} else {
					throw new AppException(Message.USER_NOT_FOUND);
				}
			} else if (admin.getIdAdmin() != 0) {
				if (admin.getPasswordAdmin().equals(password)) {
					session.setAttribute("admin", admin);
					session.setAttribute("role", admin.getRole());
					forward = Path.COMMAND_FIRST_MENU;
				} else {
					throw new AppException(Message.USER_NOT_FOUND);
				}
			} else if (manager.getId() != 0) {
				if (manager.getPassword().equals(password)) {
					if(manager.getStatus().contentEquals("dismissed")) {
						throw new AppException(Message.MANAGERS_IS_DISMISSED);
					}
					session.setAttribute("manager", manager);
					session.setAttribute("role", manager.getRole());
					session.setAttribute("index", manager.getPassport());
					oc.setIndex(manager.getPassport());
					forward = Path.COMMAND_FIRST_MENU;
				} else {
					throw new AppException(Message.USER_NOT_FOUND);
				}
			}

			session.setMaxInactiveInterval(-1);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return forward;

	}
}
