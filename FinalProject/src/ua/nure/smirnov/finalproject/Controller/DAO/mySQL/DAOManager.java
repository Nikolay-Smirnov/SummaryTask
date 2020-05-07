package ua.nure.smirnov.finalproject.Controller.DAO.mySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.smirnov.finalproject.Controller.DBManager;
import ua.nure.smirnov.finalproject.Controller.DAO.DAOInterfaceManager;
import ua.nure.smirnov.finalproject.entity.DamageBill;
import ua.nure.smirnov.finalproject.entity.Manager;
import ua.nure.smirnov.finalproject.entity.Orders;
import ua.nure.smirnov.finalproject.exception.AppException;
import ua.nure.smirnov.finalproject.exception.Message;

public class DAOManager implements DAOInterfaceManager {
	private static final String SQL_INSERT_INTO_MANAGER = "INSERT INTO managers (login_manager, name_manager, surname, password_manager, role_manager, id_fk_admin, index_passport, manager_phone, status_manager, age_manager, sex) "
			+ "Values(?, ?, ?, ?, ?, ?, ?, ?, 'work', ?, ?)";
	private static final String SQL_UPDATE_ORDER_MANAGER = "UPDATE orders SET passport_fk_manager = ? WHERE id_Order = ?";
	private static final String SQL_UPDATE_DAMAGEBILL_MANAGER = "UPDATE damage SET passport_fk_manager = ? WHERE id_payment = ?";
	private static final String SQL_FIRED_MANAGER = "UPDATE managers SET status_manager = 'dismissed' WHERE id_manager = ?";
	public final static String SQL_SELECT_ALL_MANAGERS = "SELECT * FROM managers";
	public final static String SQL_FIND_MANAGER_BY_LOGIN = "SELECT * FROM managers WHERE login_manager = ?";
	public final static String SQL_FIND_MANAGER_BY_PASSPORT = "SELECT * FROM managers WHERE index_passport=?";
	private static final Logger LOG = Logger.getLogger(DAOManager.class);

	@Override
	public void insertManager(Manager manager) throws Exception, AppException {
		PreparedStatement stmt = null;
		Connection conn = null;
		DBManager db = null;
		try {
			db = DBManager.getInstance();

			conn = db.getConnection();
			stmt = conn.prepareStatement(SQL_INSERT_INTO_MANAGER);

			stmt.setString(1, manager.getLogin());
			stmt.setString(4, manager.getPassword());
			stmt.setString(2, manager.getName());
			stmt.setString(3, manager.getSurname());
			stmt.setString(5, manager.getRole());
			stmt.setInt(6, manager.getIdAdmin());
			stmt.setString(7, manager.getPassport());
			stmt.setString(8, manager.getNumberManager());
			stmt.setString(9, manager.getAge());
			stmt.setString(10, manager.getSex());
			stmt.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			db.rollback(conn);
			LOG.error(Message.MISTACE_DB, e);
			throw new AppException(Message.MISTACE_DB);
		} finally {
			db.close(conn, stmt);
		}
	}

	@Override
	public void changeManager(Orders order, String passport) throws Exception, AppException {
		PreparedStatement stmt = null;
		Connection conn = null;
		DBManager db = null;
		try {
			db = DBManager.getInstance();
			conn = db.getConnection();
			stmt = conn.prepareStatement(SQL_UPDATE_ORDER_MANAGER);
			stmt.setInt(2, order.getIdOrders());
			stmt.setString(1, passport);
			stmt.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			db.rollback(conn);
			LOG.error(Message.MISTACE_DB, e);
			throw new AppException(Message.MISTACE_DB);
		} finally {
			db.close(conn, stmt);
		}

	}

	@Override
	public void firedManager(int id) throws Exception, AppException {
		PreparedStatement stmt = null;
		Connection conn = null;
		DBManager db = null;
		try {
			db = DBManager.getInstance();
			conn = db.getConnection();
			stmt = conn.prepareStatement(SQL_FIRED_MANAGER);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			db.rollback(conn);
			LOG.error(Message.MISTACE_DB, e);
			throw new AppException(Message.MISTACE_DB);
		} finally {
			db.close(conn, stmt);
		}

	}

	@Override
	public List<Manager> selectAllManagers() throws Exception, AppException {
		List<Manager> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		DBManager db = null;
		try {
			db = DBManager.getInstance();

			conn = db.getConnection();
			stmt = conn.prepareStatement(SQL_SELECT_ALL_MANAGERS);
			rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(db.executeManager(rs));
			}
			conn.commit();
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			db.rollback(conn);
			LOG.error(Message.MISTACE_DB, e);
			throw new AppException(Message.MISTACE_DB);
		} finally {
			db.close(conn, stmt, rs);
		}
		return list;
	}

	@Override
	public Manager findManagerForLogin(String login) throws Exception, AppException {
		Manager manager = new Manager();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		DBManager db = null;
		try {
			db = DBManager.getInstance();

			conn = db.getConnection();
			stmt = conn.prepareStatement(SQL_FIND_MANAGER_BY_LOGIN);
			stmt.setString(1, login);
			rs = stmt.executeQuery();
			while (rs.next()) {
				manager = db.executeManager(rs);
			}
			conn.commit();

			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			db.rollback(conn);
			LOG.error(Message.MISTACE_DB, e);
			throw new AppException(Message.MISTACE_DB);
		} finally {
			db.close(conn, stmt, rs);
		}
		return manager;
	}

	@Override
	public Manager findManagerByPassport(String passport) throws Exception, AppException {
		Manager manager = new Manager();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		DBManager db = null;
		try {
			db = DBManager.getInstance();

			conn = db.getConnection();
			stmt = conn.prepareStatement(SQL_FIND_MANAGER_BY_PASSPORT);
			stmt.setString(1, passport);
			rs = stmt.executeQuery();

			while (rs.next()) {
				manager = db.executeManager(rs);
			}
			conn.commit();
		} catch (Exception e) {
			db.rollback(conn);
			LOG.error(Message.MISTACE_DB, e);
			throw new AppException(Message.MISTACE_DB);
		} finally {
			db.close(conn, stmt, rs);
		}
		return manager;

	}

	@Override
	public void changeManagerDamageBill(DamageBill dBill, String passport) throws Exception, AppException {
		PreparedStatement stmt = null;
		Connection conn = null;
		DBManager db = null;
		try {
			db = DBManager.getInstance();
			conn = db.getConnection();
			stmt = conn.prepareStatement(SQL_UPDATE_DAMAGEBILL_MANAGER);
			stmt.setInt(2, dBill.getIdPayment());
			stmt.setString(1, passport);
			stmt.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			db.rollback(conn);
			LOG.error(Message.MISTACE_DB, e);
			throw new AppException(Message.MISTACE_DB);
		} finally {
			db.close(conn, stmt);
		}

	}

}
