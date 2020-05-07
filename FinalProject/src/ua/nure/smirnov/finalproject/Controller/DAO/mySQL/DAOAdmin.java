package ua.nure.smirnov.finalproject.Controller.DAO.mySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.smirnov.finalproject.Controller.DBManager;
import ua.nure.smirnov.finalproject.Controller.DAO.DAOInterfaceAdmin;
import ua.nure.smirnov.finalproject.entity.Admins;
import ua.nure.smirnov.finalproject.exception.AppException;
import ua.nure.smirnov.finalproject.exception.Message;
import ua.nure.smirnov.finalproject.web.command.MenuCommand;

public class DAOAdmin implements DAOInterfaceAdmin {

	private static final String SQL_BLOCK_CLIENT = "UPDATE clients SET status_client = ? WHERE id_client = ?";
	private static final String SQL_INSERT_INTO_ADMINS = "INSERT INTO admins (login_admin, name_admin, surname_admin, password_admin, age_admin, role_admin, index_passport, admin_phone, sex) Values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public final static String SQL_SELECT_ALL_ADMINS = "SELECT * FROM admins";
	private static final Logger LOG = Logger.getLogger(DAOAdmin.class);
	@Override
	public void blockClient(int id) throws Exception, AppException {
		DBManager db = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			db = DBManager.getInstance();

			conn = db.getConnection();
			stmt = conn.prepareStatement(SQL_BLOCK_CLIENT);
			stmt.setString(1, "block");
			stmt.setInt(2, id);
			stmt.executeUpdate();
			conn.commit();

		} catch (Exception e) {
			db.rollback(conn);
			throw new AppException(Message.MISTACE_DB); 
		} finally {
			db.close(conn, stmt);
		}
	}

	@Override
	public void unblockClient(int id) throws Exception, AppException {
		DBManager db = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			db = DBManager.getInstance();
			conn = db.getConnection();
			stmt = conn.prepareStatement(SQL_BLOCK_CLIENT);
			stmt.setString(1, "unblock");
			stmt.setInt(2, id);
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
	public void insertNewAdmin(Admins admin) throws AppException {
		Connection conn = null;
		PreparedStatement stmt = null;
		DBManager db = null;
		try {
			db = DBManager.getInstance();

			conn = db.getConnection();
			stmt = conn.prepareStatement(SQL_INSERT_INTO_ADMINS);
			stmt.setString(1, admin.getLoginAdmin());
			stmt.setString(2, admin.getNameAdmin());
			stmt.setString(3, admin.getSurnameAdmin());
			stmt.setString(4, admin.getPasswordAdmin());
			stmt.setString(5, admin.getAgeAdmin());
			stmt.setString(6, admin.getRole());
			stmt.setString(7, admin.getPassport());
			stmt.setString(8, admin.getNumberOfPhone());
			stmt.setString(9, admin.getSex());
			stmt.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			db.rollback(conn);
			LOG.error(Message.MISTACE_DB, e);
		} finally {
			db.close(conn, stmt);
		}

	}

	@Override
	public List<Admins> selectAllAdmins() throws AppException {
		List<Admins> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		DBManager db = null;
		try {
			db = DBManager.getInstance();

			conn = db.getConnection();
			stmt = conn.prepareStatement(SQL_SELECT_ALL_ADMINS);
			rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(db.executeAdmin(rs));
			}
			conn.commit();
		} catch (Exception e) {
			db.rollback(conn);
			LOG.error(Message.MISTACE_DB, e);
		}
		return list;
	}

	

}
