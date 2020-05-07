package ua.nure.smirnov.finalproject.web.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.smirnov.finalproject.Controller.ManagerMenuController;
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
import ua.nure.smirnov.finalproject.validator.Validator;

public class RegClientCommand extends Command {

	private static final Logger LOG = Logger.getLogger(RegClientCommand.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		DAOInterfaceManager daoManager = new DAOManager();
		String forward = null;
		Validator validator = new Validator();
		HttpSession session = request.getSession();
		String name = request.getParameter("username");
		LOG.trace("Name" + name);
		String surname = request.getParameter("surname");
		LOG.trace("Surname" + surname);
		String dataOfBorn = request.getParameter("age");
		LOG.trace("Age" + dataOfBorn);
		String password = request.getParameter("password");
		LOG.trace("Password" + password);
		String login = request.getParameter("login");
		LOG.trace("Login" + login);
		String passport = request.getParameter("passport");
		LOG.trace("Passport" + passport);
		String number = request.getParameter("number");
		LOG.trace("Number" + number);
		String sex = request.getParameter("sex");
		LOG.trace("Sex" + sex);
		if (name.isEmpty() || surname.isEmpty() || password.isEmpty() || login.isEmpty() || passport.isEmpty()) {
			throw new AppException(Message.FELDS_IS_EMPTY);
		}
		if (password.length() < 6) {
			throw new AppException(Message.PASSWORD_IS_SMALL);
		}
		if (!validator.validatorMail(login)) {
			throw new AppException(Message.MAILL_DOESNT_EXIST);
		}
		
		
		if(validator.validatorNameEngRusUa(name)) {
				throw new AppException(Message.NAME_INCORECT);
		}

		Manager manager = new Manager();
		Client client = new Client();
		DAOInterfaceAdmin daoAdmin = new DAOAdmin();
		DAOInterfaceClient dao = new DAOClient();

		List<Admins> listAdmin = new ArrayList<Admins>();
		try {
			listAdmin = daoAdmin.selectAllAdmins();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		for (Admins a : listAdmin) {
			if (a.getLoginAdmin().contentEquals(login)) {
				throw new AppException(Message.DUBLICATE_DATA);
			}
		}

		try {
			client = dao.findUserForLogin(login);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		if (client.getIdClient() != 0) {
			throw new AppException(Message.DUBLICATE_DATA);
		}

		client = new Client();

		try {
			client = dao.findUserForPassport(passport);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		if (client.getIdClient() != 0) {
			throw new AppException(Message.DUBLICATE_DATA);
		}
		manager = new Manager();
		try {
			manager = daoManager.findManagerByPassport(passport);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (manager.getId() != 0) {
			throw new AppException(Message.DUBLICATE_DATA);
		}
		client = new Client();
		client.setLoginClient(login);
		client.setPassword(password);
		client.setName(name);
		client.setAge(dataOfBorn);
		client.setSurname(surname);
		client.setRole("client");
		client.setStatus("unblock");
		client.setPassport(passport);
		client.setNumberClient(number);
		client.setSex(sex);

		try {
			dao.insertClient(client);
		} catch (Exception e) {
			e.printStackTrace();
		}

		session.setAttribute("client", client);
		forward = Path.COMMAND_MENU_LOGIN;
		return forward;
	}

}
