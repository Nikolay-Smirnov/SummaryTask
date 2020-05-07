package ua.nure.smirnov.finalproject.Controller.DAO.mySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.smirnov.finalproject.Controller.DBManager;
import ua.nure.smirnov.finalproject.Controller.DAO.DAOInterfaceDamageBill;
import ua.nure.smirnov.finalproject.entity.DamageBill;
import ua.nure.smirnov.finalproject.entity.DamageBillForClient;
import ua.nure.smirnov.finalproject.entity.DamageBillForManager;
import ua.nure.smirnov.finalproject.exception.AppException;
import ua.nure.smirnov.finalproject.exception.Message;

public class DAODamageBill implements DAOInterfaceDamageBill {

	private static final String SQL_FIND_DAMAGEBILL_BY_ID_ORDER = "SELECT * FROM damage WHERE id_fk_order = ?";

	private static final String SQL_FIND_DAMAGEBILL_FOR_CLIENT = "SELECT d.price, d.description_payment,"
			+ " d.invoice_date, c.model, c.car_brand, m.login_manager, m.name_manager, m.manager_phone, m.surname, o.id_fk_car, d.id_fk_order, d.status_order FROM damage d, orders o, cars c,"
			+ "managers m WHERE d.passport_fk_client = ? AND o.id_Order = d.id_fk_order AND c.id_car = o.id_fk_car AND m.index_passport = d.passport_fk_manager";
	private static final String SQL_FIND_DAMAGEBILL_BY_ID_ORDER_FOR_MANAGER = "SELECT d.id_payment, d.payment_activity, d.price, d.passport_fk_client,d.status_order, d.description_payment,"
			+ " d.invoice_date, d.id_fk_order, o.id_fk_car, c.model, c.car_brand, cl.login, d.passport_fk_manager , cl.name_client, cl.surname, cl.client_phone FROM damage d, orders o, cars c,"
			+ "clients cl WHERE  o.id_Order = d.id_fk_order AND c.id_car = o.id_fk_car AND cl.index_passport = d.passport_fk_client";
	private static final String SQL_UPDATE_DAMAGE_BILL_STATUS = "UPDATE damage SET payment_activity = 'paid', status_order = 'inactive' WHERE id_payment = ?";
	private static final String SQL_INSERT_NEW_DAMAGE_BILL = "INSERT INTO damage (id_fk_order, passport_fk_client, passport_fk_manager, invoice_date, status_order, "
			+ "payment_activity, price, description_payment) Values(?, ?, ?, ?, ?, ?, ?, ?)";
	private static final Logger LOG = Logger.getLogger(DAODamageBill.class);

	@Override
	public DamageBill findDamageBillByOrderId(int id) throws Exception, AppException {
		DamageBill bill = new DamageBill();
		DBManager db = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			int k = 0;
			db = DBManager.getInstance();
			conn = db.getConnection();
			stmt = conn.prepareStatement(SQL_FIND_DAMAGEBILL_BY_ID_ORDER);
			stmt.setInt(++k, id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				bill = db.executeDamageBill(rs);
			}
			conn.commit();
		} catch (Exception e) {
			db.rollback(conn);
			LOG.error(Message.MISTACE_DB, e);
			throw new AppException(Message.MISTACE_DB);
		} finally {
			db.close(conn, stmt, rs);
		}
		return bill;
	}

	@Override
	public List<DamageBillForClient> selectDamageBillForClient(String passport) throws Exception, AppException {
		List<DamageBillForClient> bill = new ArrayList<DamageBillForClient>();
		DBManager db = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			db = DBManager.getInstance();
			conn = db.getConnection();
			stmt = conn.prepareStatement(SQL_FIND_DAMAGEBILL_FOR_CLIENT);
			stmt.setString(1, passport);
			rs = stmt.executeQuery();
			while (rs.next()) {
				bill.add(db.executeDamageBillForClient(rs));
			}
			conn.commit();
		} catch (Exception e) {
			db.rollback(conn);
			LOG.error(Message.MISTACE_DB, e);
			throw new AppException(Message.MISTACE_DB);
		} finally {
			db.close(conn, stmt, rs);
		}
		return bill;
	}

	@Override
	public List<DamageBillForManager> selectAllDamageBill() throws Exception, AppException {
		List<DamageBillForManager> bill = new ArrayList<DamageBillForManager>();
		DBManager db = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			db = DBManager.getInstance();
			conn = db.getConnection();
			stmt = conn.prepareStatement(SQL_FIND_DAMAGEBILL_BY_ID_ORDER_FOR_MANAGER);
			rs = stmt.executeQuery();
			while (rs.next()) {
				bill.add(db.executeDamageBillForManager(rs));
			}
			conn.commit();
		} catch (Exception e) {
			db.rollback(conn);
			LOG.error(Message.MISTACE_DB, e);
			throw new AppException(Message.MISTACE_DB);
		} finally {
			db.close(conn, stmt, rs);
		}
		return bill;
	}

	@Override
	public void updateDamageBillStatus(int id) throws Exception, AppException {
		int k = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		DBManager db = null;
		try {
			db = DBManager.getInstance();
			conn = db.getConnection();
			stmt = conn.prepareStatement(SQL_UPDATE_DAMAGE_BILL_STATUS);
			stmt.setInt(++k, id);
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
	public void insertNewDamageBill(DamageBill bill) throws Exception, AppException {
		int k = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		DBManager db = null;
		try {
			db = DBManager.getInstance();
			conn = db.getConnection();
			stmt = conn.prepareStatement(SQL_INSERT_NEW_DAMAGE_BILL);
			stmt.setInt(++k, bill.getIdOrder());
			stmt.setString(++k, bill.getIndexClient());
			stmt.setString(++k, bill.getIndexManager());
			stmt.setString(++k, bill.getDate().toString());
			stmt.setString(++k, bill.getStatus());
			stmt.setString(++k, bill.getActivity());
			stmt.setInt(++k, bill.getPrice());
			stmt.setString(++k, bill.getDescription());
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
