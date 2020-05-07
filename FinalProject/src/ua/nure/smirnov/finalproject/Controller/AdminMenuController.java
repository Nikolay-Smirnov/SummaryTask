package ua.nure.smirnov.finalproject.Controller;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.smirnov.finalproject.Controller.DAO.DAOInterfaceAdmin;
import ua.nure.smirnov.finalproject.Controller.DAO.DAOInterfaceCar;
import ua.nure.smirnov.finalproject.Controller.DAO.DAOInterfaceClient;
import ua.nure.smirnov.finalproject.Controller.DAO.DAOInterfaceDamageBill;
import ua.nure.smirnov.finalproject.Controller.DAO.DAOInterfaceManager;
import ua.nure.smirnov.finalproject.Controller.DAO.DAOInterfaceOrder;
import ua.nure.smirnov.finalproject.Controller.DAO.mySQL.DAOAdmin;
import ua.nure.smirnov.finalproject.Controller.DAO.mySQL.DAOCar;
import ua.nure.smirnov.finalproject.Controller.DAO.mySQL.DAOClient;
import ua.nure.smirnov.finalproject.Controller.DAO.mySQL.DAODamageBill;
import ua.nure.smirnov.finalproject.Controller.DAO.mySQL.DAOManager;
import ua.nure.smirnov.finalproject.Controller.DAO.mySQL.DAOOrder;
import ua.nure.smirnov.finalproject.entity.Admins;
import ua.nure.smirnov.finalproject.entity.Cars;
import ua.nure.smirnov.finalproject.entity.Client;
import ua.nure.smirnov.finalproject.entity.DamageBill;
import ua.nure.smirnov.finalproject.entity.Manager;
import ua.nure.smirnov.finalproject.entity.Orders;
import ua.nure.smirnov.finalproject.entity.Path;
import ua.nure.smirnov.finalproject.exception.AppException;
import ua.nure.smirnov.finalproject.exception.Message;
import ua.nure.smirnov.finalproject.validator.Validator;
import ua.nure.smirnov.finalproject.web.command.Command;

public class AdminMenuController extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(ManagerMenuController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		DAOInterfaceManager daoManager = new DAOManager();
		DAOInterfaceCar daoCar = new DAOCar();
		DAOInterfaceAdmin daoAdmin = new DAOAdmin();
		DAOInterfaceDamageBill daoDamage = new DAODamageBill();
		Validator validator = new Validator();
		String forward = null;
		HttpSession session = request.getSession();
		Admins admin = (Admins) session.getAttribute("admin");
		String role = admin.getRole();
		LOG.trace("current role -->" + admin.getRole());
		if (!("admin").equals(role)) {
			throw new AppException(Message.IS_NOT_ADMIN);
		}

		if (request.getParameter("RegNewCar") != null) {
			forward = Path.COMMAND_MENU_ADMIN_REG_CAR;
		}

		if (request.getParameter("BlockClient") != null) {
			forward = Path.COMMAND_MENU_ADMIN_BLOCK_CLIENT;
		}

		if (request.getParameter("UnblockClient") != null) {
			forward = Path.COMMAND_MENU_ADMIN_UNBLOCK_CLIENT;
		}

		if (request.getParameter("findUnblockClient") != null) {
			String pass = request.getParameter("passportClient");
			String loginClient = request.getParameter("passportClient");
			request.setAttribute("passportClient", pass);
			request.setAttribute("loginClient", loginClient);
			forward = Path.COMMAND_MENU_ADMIN_UNBLOCK_CLIENT;
		}

		if (request.getParameter("findBlockClient") != null) {
			String pass = request.getParameter("passportClient");
			String loginClient = request.getParameter("passportClient");
			request.setAttribute("passportClient", pass);
			request.setAttribute("loginClient", loginClient);
			forward = Path.COMMAND_MENU_ADMIN_BLOCK_CLIENT;
		}

		if (request.getParameter("unblockClient") != null) {
			DAOInterfaceAdmin dao = new DAOAdmin();
			if (("").equals(request.getParameter("id"))) {
				throw new AppException(Message.USER_DID_NOT_PICK);
			}
			int id = Integer.parseInt(request.getParameter("id"));

			try {
				dao.unblockClient(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			forward = Path.COMMAND_MENU_ADMIN_UNBLOCK_CLIENT;
		}

		if (request.getParameter("deleteCars") != null) {
			forward = Path.PAGE_ADMIN_MENU;
		}

		if (request.getParameter("acceptBlockClient") != null) {
			DAOInterfaceAdmin dao = new DAOAdmin();
			if (("").equals(request.getParameter("id"))) {
				throw new AppException(Message.USER_DID_NOT_PICK);
			}
			int id = Integer.parseInt(request.getParameter("id"));

			try {
				dao.blockClient(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			forward = Path.COMMAND_MENU_ADMIN_BLOCK_CLIENT;
		}

		if (request.getParameter("AcceptRegCar") != null) {
			String model = request.getParameter("model");
			String carLevel = request.getParameter("carLevel");
			if (("").equals(request.getParameter("price"))) {
				throw new AppException(Message.FELDS_IS_EMPTY);
			}
			int price = Integer.parseInt(request.getParameter("price"));
			String carCond = request.getParameter("carCondition");
			String carBrand = request.getParameter("carBrand");
			String vin = request.getParameter("vin");
			if (("").equals(model) || ("").equals(carLevel) || price == 0 || ("").equals(carCond)
					|| ("").equals(carBrand) || ("").equals(vin)) {
				throw new AppException(Message.FELDS_IS_EMPTY);
			}
			Cars car = new Cars();
			car.setCarBrand(carBrand);
			car.setCarLevel(carLevel);
			car.setModel(model);
			car.setPrice(price);
			car.setStatus("free");
			car.setIdAdmin(admin.getIdAdmin());
			car.setConditionCar(carCond);
			car.setConditionCar(carCond);
			car.setVin(vin);
			try {
				daoCar.insertCar(car);
			} catch (Exception e) {
				e.printStackTrace();
			}
			forward = Path.COMMAND_MENU_ADMIN;
		}

		if (request.getParameter("RegNewManager") != null) {
			forward = Path.COMMAND_MENU_ADMIN_REG_MANAGER;
		}

		if (request.getParameter("dismissalManager") != null) {
			forward = Path.COMMAND_MENU_ADMIN_DISSMISAL_PAGE;
		}

		if (request.getParameter("firedManager") != null) {
			if (request.getParameter("id") == null) {
				throw new AppException(Message.USER_DID_NOT_PICK);
			}
			int id = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("idManager", id);
			session.setAttribute("idManager", id);
			forward = Path.COMMAND_MENU_ADMIN_TRANSFER_ORDER;
		}

		if (request.getParameter("transferOrder") != null) {
			DAOInterfaceOrder daoOrder = new DAOOrder();
			int id = (Integer) session.getAttribute("idManager");
			LOG.trace("Current id manager's --> " + id);
			if (request.getParameter("passport") == null) {
				throw new AppException(Message.USER_DID_NOT_PICK);
			}

			String passport = request.getParameter("passport");
			LOG.trace("Current manager's passport --> " + passport);
			Manager manager = new Manager();
			List<Manager> listM = new ArrayList<>();
			try {
				listM = daoManager.selectAllManagers();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			List<Orders> listO = new ArrayList<Orders>();
			for (Manager m : listM) {
				if (m.getId() == id) {
					manager = m;
				}
			}

			List<Orders> list = new ArrayList<>();
			try {
				list = daoOrder.selectAllOrder();
			} catch (Exception e) {
				e.printStackTrace();
			}
			for (Orders o : list) {
				if (o.getIndexManager().equals(manager.getPassport())) {
					listO.add(o);
				}
			}
			for (Orders order : listO) {
				if (!("no active").equals(order.getOrderActivity())) {
					try {
						System.out.println(order.getIdOrders());
						daoManager.changeManager(order, passport);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

			List<DamageBill> listDamage = new ArrayList<DamageBill>();
			for (Orders o : listO) {
				try {
					listDamage.add(daoDamage.findDamageBillByOrderId(o.getIdOrders()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			for (DamageBill db : listDamage) {
				System.out.println(db.getStatus() + "" + db.getIdPayment());
				if (("active").equals(db.getStatus())) {
					
					try {
						daoManager.changeManagerDamageBill(db, passport);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

			try {
				daoManager.firedManager(id);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return forward = Path.COMMAND_MENU_ADMIN;
		}

		if (request.getParameter("acceptManagerReg") != null) {
			Client client = new Client();
			String name = request.getParameter("username");
			LOG.trace("Name manager's --> " + name);
			String surname = request.getParameter("surname");
			LOG.trace("Surname manager's --> " + surname);
			String password = request.getParameter("password");
			LOG.trace("Password manager's --> " + password);
			String login = request.getParameter("login");
			LOG.trace("Login manager's --> " + login);
			String passport = request.getParameter("passport");
			LOG.trace("Passport manager's --> " + passport);
			String number = request.getParameter("number");
			LOG.trace("Number manager's --> " + number);
			String sex = request.getParameter("sex");
			String age = request.getParameter("age");
			if (name.isEmpty() || surname.isEmpty() || password.isEmpty() || login.isEmpty() || number.isEmpty()
					|| passport.isEmpty() || sex.isEmpty() || age.isEmpty()) {

				throw new AppException(Message.FELDS_IS_EMPTY);

			}

			if (password.length() < 6) {
				throw new AppException(Message.PASSWORD_IS_SMALL);
			}
			if (!validator.validatorMail(login)) {
				throw new AppException(Message.MAILL_DOESNT_EXIST);
			}

			if (validator.validatorNameEngRusUa(name)) {
				throw new AppException(Message.NAME_INCORECT);
			}

			List<Admins> listAdmin = new ArrayList<>();
			Manager manager = new Manager();
			try {
				listAdmin = daoAdmin.selectAllAdmins();
			} catch (Exception e2) {
				e2.printStackTrace();
			}

			for (Admins a : listAdmin) {
				if (passport.contentEquals(a.getPassport()) || login.contentEquals(a.getLoginAdmin())) {
					throw new AppException(Message.DUBLICATE_DATA);
				}
			}

			try {
				manager = daoManager.findManagerForLogin(login);

			} catch (Exception e) {
				e.printStackTrace();
			}
			if (manager.getId() != 0) {
				throw new AppException(Message.DUBLICATE_DATA);
			}
			try {
				manager = daoManager.findManagerByPassport(passport);

			} catch (Exception e) {
				e.printStackTrace();
			}
			if (manager.getId() != 0) {
				throw new AppException(Message.DUBLICATE_DATA);
			}
			DAOInterfaceClient dao = new DAOClient();
			try {
				client = dao.findUserForPassport(passport);
				client = dao.findUserForLogin(login);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			if (client.getIdClient() != 0) {
				throw new AppException(Message.DUBLICATE_DATA);
			}
			manager = new Manager();
			manager.setLogin(login);
			manager.setPassword(password);
			manager.setName(name);
			manager.setSurname(surname);
			manager.setRole("manager");
			manager.setIdAdmin(admin.getIdAdmin());
			manager.setPassport(passport);
			manager.setNumberManager(number);
			manager.setAge(age);
			manager.setSex(sex);
			try {
				daoManager.insertManager(manager);
			} catch (Exception e) {
				e.printStackTrace();
			}
			forward = Path.COMMAND_MENU_ADMIN;
		}

		if (request.getParameter("RegNewAdmin") != null) {
			forward = Path.COMMAND_MENU_ADMIN_REG_ADMIN;
		}

		if (request.getParameter("menu") != null) {
			if (session.isNew()) {
				request.setAttribute("log", "Войти");
			} else {
				request.setAttribute("log", "Выйти");
			}
			forward = Path.COMMAND_FIRST_MENU;
		}

		if (request.getParameter("deleteCars") != null) {
			forward = Path.COMMAND_MENU_ADMIN;
		}

		if (request.getParameter("deleteCar") != null) {
			if (request.getParameter("id") == null) {
				throw new AppException();
			}
			int id = Integer.parseInt(request.getParameter("id"));
			Cars car = new Cars();
			try {
				car = daoCar.findCarById(id);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			if (("busy").contentEquals(car.getStatus())) {
				throw new AppException(Message.CANNOT_DELETE_CAR);
			}
			LOG.trace("Id car --> " + id);
			try {
				daoCar.deleteCar(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			forward = Path.COMMAND_MENU_ADMIN;
		}

		if (request.getParameter("AcceptRegAdmin") != null) {
			DAOInterfaceAdmin dao = new DAOAdmin();
			String name = request.getParameter("username");
			LOG.trace("Name admin's -->" + name);
			String surname = request.getParameter("surname");
			LOG.trace("Surname admin's -->" + surname);
			String dataOfBorn = request.getParameter("age");
			LOG.trace("Data of born admin's -->" + dataOfBorn);
			String password = request.getParameter("password");
			LOG.trace("Password admin's -->" + password);
			String login = request.getParameter("login");
			LOG.trace("Login admin's -->" + login);
			String passport = request.getParameter("passport");
			LOG.trace("Passport admin's -->" + passport);
			String numberOfPhone = request.getParameter("numberOfPhone");
			LOG.trace("numberOfPhone admin's -->" + numberOfPhone);
			String sex = request.getParameter("sex");
			LOG.trace("numberOfPhone admin's -->" + numberOfPhone);
			if (("").equals(name) || ("").equals(surname) || ("").equals(password) || ("").equals(login)
					|| ("").equals(numberOfPhone) || sex.isEmpty() || dataOfBorn.isEmpty()) {
				throw new AppException(Message.FELDS_IS_EMPTY);
			}
			if (password.length() < 6) {
				throw new AppException(Message.PASSWORD_IS_SMALL);
			}
			if (!validator.validatorMail(login)) {
				throw new AppException(Message.MAILL_DOESNT_EXIST);
			}

			if (validator.validatorNameEngRusUa(name)) {
				throw new AppException(Message.NAME_INCORECT);
			}
			List<Admins> listAdm = new ArrayList<>();
			try {
				listAdm = dao.selectAllAdmins();
			} catch (Exception e) {
				e.printStackTrace();
			}

			for (Admins adm : listAdm) {
				System.out.println("++" + adm.getPassport());
				if ((login).equals(adm.getLoginAdmin()) || (passport).equals(adm.getPassport())
						|| (numberOfPhone).contentEquals(adm.getNumberAdmin())) {
					throw new AppException(Message.DUBLICATE_DATA);
				}
			}

			List<Manager> listManager = new ArrayList<>();

			try {
				listManager = daoManager.selectAllManagers();
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			for (Manager m : listManager) {
				if ((login).equals(m.getLogin()) || (passport).equals(m.getPassport())
						|| (numberOfPhone).contentEquals(m.getNumberManager())) {
					throw new AppException(Message.DUBLICATE_DATA);
				}
			}

			List<Client> listClient = new ArrayList<Client>();

			for (Client c : listClient) {
				if ((login).equals(c.getLoginClient()) || (passport).equals(c.getPassport())
						|| (numberOfPhone).contentEquals(c.getNumberClient())) {
					throw new AppException(Message.DUBLICATE_DATA);
				}
			}

			admin = new Admins();
			admin.setLoginAdmin(login);
			admin.setPasswordAdmin(password);
			admin.setNameAdmin(name);
			admin.setAgeAdmin(dataOfBorn);
			admin.setSurnameAdmin(surname);
			admin.setRole("admin");
			admin.setPassport(passport);
			admin.setNumberOfPhone(numberOfPhone);
			admin.setSex(sex);
			try {
				dao.insertNewAdmin(admin);
			} catch (Exception e) {
				e.printStackTrace();
			}
			forward = Path.COMMAND_MENU_ADMIN;
		}

		return forward;
	}

}
