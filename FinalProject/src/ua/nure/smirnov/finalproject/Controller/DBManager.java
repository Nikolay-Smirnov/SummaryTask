package ua.nure.smirnov.finalproject.Controller;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import ua.nure.smirnov.finalproject.entity.Admins;
import ua.nure.smirnov.finalproject.entity.Cars;
import ua.nure.smirnov.finalproject.entity.Client;
import ua.nure.smirnov.finalproject.entity.DamageBill;
import ua.nure.smirnov.finalproject.entity.DamageBillForClient;
import ua.nure.smirnov.finalproject.entity.DamageBillForManager;
import ua.nure.smirnov.finalproject.entity.Function;
import ua.nure.smirnov.finalproject.entity.Manager;
import ua.nure.smirnov.finalproject.entity.ManagerList;
import ua.nure.smirnov.finalproject.entity.OrderList;
import ua.nure.smirnov.finalproject.exception.AppException;

public class DBManager {

	private static final Logger LOG = Logger.getLogger(DBManager.class);
	private static DBManager instance;
	private DataSource dataSource;

	public static synchronized DBManager getInstance() throws AppException {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}

	public DBManager() throws AppException {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/carrent");
			LOG.trace("Data source ==> " + dataSource);
		} catch (NamingException ex) {
			LOG.error("Connection exception");
			throw new AppException("Connection exception");

		}
	}

	public Connection getConnection() throws Exception {
		Connection connnection = null;
		try {
			connnection = dataSource.getConnection();
		} catch (SQLException ex) {
			LOG.error("Connection exception");
			throw new Exception("Connection exception");
		}
		return connnection;
	}

	//////////////////// EXECUTE///EXECUTE///EXTCUTE///EXECUTE///EXECUTE///////////////////////////////////////////////////////////////////////////////////

	public Admins executeAdmin(ResultSet rs) throws SQLException {
		Admins admin = new Admins();
		admin.setIdAdmin(rs.getInt("id_admin"));
		admin.setNameAdmin(rs.getString("name_admin"));
		admin.setSurnameAdmin(rs.getString("surname_admin"));
		admin.setLoginAdmin(rs.getString("login_admin"));
		admin.setAgeAdmin(rs.getString("age_admin"));
		admin.setPasswordAdmin(rs.getString("password_admin"));
		admin.setRole(rs.getString("role_admin"));
		admin.setPassport(rs.getString("index_passport"));
		admin.setNumberAdmin(rs.getString("admin_phone"));
		admin.setSex(rs.getString("sex"));
		return admin;
	}

	public OrderList executeOrderList(ResultSet rs) throws SQLException {
		OrderList orderList = new OrderList();
		orderList.setFinishDate(LocalDate.parse(rs.getString("finish_order_date")));
		orderList.setStartDate(LocalDate.parse(rs.getString("start_order_date")));
		orderList.setIdOrder(rs.getInt("id_Order"));
		orderList.setOrderPrice(rs.getInt("order_Price"));
		orderList.setFunction(rs.getString("fk_name_function"));
		orderList.setModel(rs.getString("model"));
		orderList.setLoginManager(rs.getString("login_Manager"));
		orderList.setNameManager(rs.getString("name_manager"));
		orderList.setSurnameManager(rs.getString("surname"));
		orderList.setNumberManager(rs.getString("manager_phone"));
		return orderList;
	}

	public Client executeClient(ResultSet rs) {

		Client user = new Client();
		try {

			user.setIdClient(rs.getInt("id_client"));
			user.setPassword(rs.getString("password_client"));
			user.setLoginClient(rs.getString("login"));
			user.setName(rs.getString("name_client"));
			user.setSurname(rs.getString("surname"));
			user.setAge(rs.getString("age"));
			user.setRole(rs.getString("role_client"));
			user.setStatus(rs.getString("status_client"));
			user.setPassport(rs.getString("index_passport"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public Manager executeManager(ResultSet rs) throws SQLException {
		Manager manager = new Manager();
		manager.setId(rs.getInt("id_manager"));
		manager.setPassword(rs.getString("password_manager"));
		manager.setLogin(rs.getString("login_Manager"));
		manager.setName(rs.getString("name_manager"));
		manager.setSurname(rs.getString("surname"));
		manager.setRole(rs.getString("role_manager"));
		manager.setIdAdmin(rs.getInt("id_fk_admin"));
		manager.setPassport(rs.getString("index_passport"));
		manager.setNumberManager(rs.getString("manager_phone"));
		manager.setStatus(rs.getString("status_manager"));
		return manager;
	}

	public ManagerList executeManagerList(ResultSet rs) throws SQLException {
		ManagerList mList = new ManagerList();
		mList.setFinishDate(LocalDate.parse(rs.getString("o.finish_order_date")));
		mList.setStartDate(LocalDate.parse(rs.getString("o.start_order_date")));
		mList.setModel(rs.getString("c.model"));
		mList.setPrice(rs.getInt("o.order_Price"));
		mList.setFunction(rs.getString("o.fk_name_function"));
		mList.setNumberClient(rs.getString("cl.client_phone"));
		mList.setNameClient(rs.getString("cl.name_client"));
		mList.setSurnameClients(rs.getString("cl.surname"));
		mList.setLoginClient(rs.getString("cl.login"));
		mList.setIdOrder(rs.getInt("o.id_Order"));
		mList.setIdCar(rs.getInt("c.id_car"));
		mList.setBrandCar(rs.getString("c.car_brand"));
		mList.setDescription(rs.getString("o.description_order"));
		mList.setStatusOrder(rs.getString("o.status_order"));
		mList.setOrderActivity(rs.getString("o.order_activity"));
		mList.setIndexManager(rs.getString("o.passport_fk_manager"));
		mList.setIndex(rs.getString("o.passport_fk_client"));
		return mList;
	}

	public Cars executeCars(ResultSet rs) throws SQLException {
		Cars car = new Cars();
		car.setIdCar(rs.getInt("id_car"));
		car.setCarLevel(rs.getString("car_level"));
		car.setModel(rs.getString("model"));
		car.setPrice(rs.getInt("price"));
		car.setStatus(rs.getString("status_car"));
		return car;
	}

	public DamageBill executeDamageBill(ResultSet rs) throws SQLException {
		DamageBill bill = new DamageBill();
		bill.setActivity(rs.getString("payment_activity"));
		bill.setIdOrder(rs.getInt("id_fk_order"));
		bill.setIdPayment(rs.getInt("id_payment"));
		bill.setIndexClient(rs.getString("passport_fk_client"));
		bill.setIndexManager(rs.getString("passport_fk_manager"));
		bill.setPrice(rs.getInt("price"));
		bill.setStatus(rs.getString("status_order"));
		bill.setDescription(rs.getString("description_payment"));
		bill.setDate(LocalDate.parse(rs.getString("invoice_date")));

		return bill;
	}

	public DamageBillForManager executeDamageBillForManager(ResultSet rs) throws SQLException {
		DamageBillForManager bill = new DamageBillForManager();
		bill.setActivity(rs.getString("d.payment_activity"));
		bill.setIdOrder(rs.getInt("d.id_fk_order"));
		bill.setIdPayment(rs.getInt("d.id_payment"));
		bill.setIndexClient(rs.getString("d.passport_fk_client"));
		bill.setIndexManager(rs.getString("d.passport_fk_manager"));
		bill.setNameClient(rs.getString("cl.name_client"));
		bill.setSurnameClient(rs.getString("cl.surname"));
		bill.setPrice(rs.getInt("d.price"));
		bill.setStatus(rs.getString("d.status_order"));
		bill.setDescription(rs.getString("d.description_payment"));
		bill.setDate(LocalDate.parse(rs.getString("d.invoice_date")));
		bill.setCarId(rs.getInt("o.id_fk_car"));
		bill.setModel(rs.getString("c.model"));
		bill.setCarBrand(rs.getString("c.car_brand"));
		bill.setLoginClient(rs.getString("cl.login"));
		bill.setNumberClient(rs.getString("cl.client_phone"));
		return bill;
	}

	public DamageBillForClient executeDamageBillForClient(ResultSet rs) throws SQLException {
		DamageBillForClient bill = new DamageBillForClient();
		bill.setNameManager(rs.getString("m.name_manager"));
		bill.setSurnameManager(rs.getString("m.surname"));
		bill.setPrice(rs.getInt("d.price"));
		bill.setDescription(rs.getString("d.description_payment"));
		bill.setDate(LocalDate.parse(rs.getString("d.invoice_date")));
		bill.setModelCar(rs.getString("c.model"));
		bill.setCarBrand(rs.getString("c.car_brand"));
		bill.setLoginManager(rs.getString("m.login_manager"));
		bill.setNumberManager(rs.getString("m.manager_phone"));
		bill.setStatus(rs.getString("d.status_order"));
		return bill;
	}

	/**
	 * Rollbacks a connection.
	 * 
	 * @param con Connection to be rollbacked.
	 */

	public void rollback(Connection con) {
		if (con != null) {
			try {
				con.rollback();
			} catch (SQLException ex) {
				LOG.error("Cannot rollback transaction", ex);
			}
		}
	}

	public void close(Connection con, Statement stmt, ResultSet rs) {
		close(rs);
		close(stmt);
		close(con);
	}

	public void close(Connection con, Statement stmt) {
		close(stmt);
		close(con);
	}

	private void close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException ex) {
				LOG.error("Cannot close a statement", ex);
			}
		}
	}

	private void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException ex) {
				LOG.error("Cannot close a ResultSet", ex);
			}
		}
	}

	private void close(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException ex) {
				LOG.error("Cannot close a connection", ex);
			}
		}
	}
}
