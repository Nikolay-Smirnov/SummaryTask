package ua.nure.smirnov.finalproject.Controller.DAO.mySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.smirnov.finalproject.Controller.DBManager;
import ua.nure.smirnov.finalproject.Controller.DAO.DAOInterfaceClient;
import ua.nure.smirnov.finalproject.entity.Client;
import ua.nure.smirnov.finalproject.exception.AppException;
import ua.nure.smirnov.finalproject.exception.Message;

public class DAOClient implements DAOInterfaceClient {
	public final static String SQL_FIND_CLIENT_BY_LOGIN = "SELECT * FROM clients WHERE login = ?";
	public final static String SQL_FIND_CLIENT_BY_PASSPORT = "SELECT * FROM clients WHERE index_passport=?";
	private static final String SQL_INSERT_INTO_CLIENTS = "INSERT INTO clients (login, name_client, password_client, surname, age, role_client, status_client, index_passport, "
			+ "client_phone, sex) Values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_FIND_CLIENT_BY_STATUS = "SELECT * FROM clients WHERE status_client = ?";
	public final static String SQL_SELECT_ALL_CLIENT = "SELECT * FROM clients";
	private static final Logger LOG = Logger.getLogger(DAOClient.class);
	
	@Override
	public Client findUserForLogin(String login) throws Exception, AppException {
		Client client = new Client();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		DBManager db = null;
		try {
			db = DBManager.getInstance();

			conn = db.getConnection();
			stmt = conn.prepareStatement(SQL_FIND_CLIENT_BY_LOGIN);
			stmt.setString(1, login);
			rs = stmt.executeQuery();
			if (rs.next()) {
				client = db.executeClient(rs);
			}
			conn.commit();
		} catch (Exception e) {
			db.rollback(conn);
			throw new AppException(Message.MISTACE_DB); 	
		} finally {
			db.close(conn, stmt, rs);
		}
		return client;

	}

	@Override
	public Client findUserForPassport(String passport) throws Exception, AppException {
		Client client = new Client();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		DBManager db = null;
		try {
			db = DBManager.getInstance();

			conn = db.getConnection();
			stmt = conn.prepareStatement(SQL_FIND_CLIENT_BY_PASSPORT);
			stmt.setString(1, passport);
			rs = stmt.executeQuery();
			if (rs.next()) {
				client = db.executeClient(rs);
			}
			conn.commit();

		} catch (Exception e) {
			db.rollback(conn);
			LOG.error(Message.MISTACE_DB, e);
			throw new AppException(Message.MISTACE_DB); 
		} finally {
			db.close(conn, stmt, rs);
		}
		return client;
	}

	@Override
	public void insertClient(Client client) throws Exception, AppException {
		PreparedStatement stmt = null;
		Connection conn = null;

		DBManager db = null;
		try {
			db = DBManager.getInstance();

			conn = db.getConnection();
			stmt = conn.prepareStatement(SQL_INSERT_INTO_CLIENTS);

			stmt.setString(1, client.getLoginClient());
			stmt.setString(3, client.getPassword());
			stmt.setString(2, client.getNameClient());
			stmt.setString(4, client.getSurname());
			stmt.setString(5, client.getAge());
			stmt.setString(6, client.getRole());
			stmt.setString(7, client.getStatus());
			stmt.setString(8, client.getPassport());
			stmt.setString(9, client.getNumberClient());
			stmt.setString(10, client.getSex());
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
	public List<Client> selectClientByStatus(String status) throws Exception, AppException {
		List<Client> list = new ArrayList<Client>();
		DBManager db = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			db = DBManager.getInstance();

			conn = db.getConnection();
			stmt = conn.prepareStatement(SQL_FIND_CLIENT_BY_STATUS);
			stmt.setString(1, status);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Client client = new Client();
				client.setIdClient(rs.getInt("id"));
				client.setAge(rs.getString("age"));
				client.setLoginClient(rs.getString("login"));
				client.setPassword(rs.getString("password"));
				client.setName(rs.getString("name"));
				client.setRole(rs.getString("role"));
				client.setStatus(rs.getString("status"));
				client.setSurname(rs.getString("surname"));
				client.setPassport(rs.getString("index_passport"));
				client.setNumberClient(rs.getString("client_phone"));
				list.add(client);
			}
			conn.commit();
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
	public List<Client> selectAllClient() throws Exception, AppException {
		List<Client> list = new ArrayList<Client>();
		DBManager db = null;
		Statement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			db = DBManager.getInstance();

			conn = db.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL_SELECT_ALL_CLIENT);
			while (rs.next()) {
				Client client = new Client();
				client.setIdClient(rs.getInt("id_client"));
				client.setLoginClient(rs.getString("login"));
				client.setAge(rs.getString("age"));
				client.setName(rs.getString("name_client"));
				client.setPassword(rs.getString("password_client"));
				client.setRole(rs.getString("role_client"));
				client.setStatus(rs.getString("status_client"));
				client.setSurname(rs.getString("surname"));
				client.setPassport(rs.getString("index_passport"));
				client.setNumberClient(rs.getString("client_phone"));
				list.add(client);
			}
			conn.commit();
		} catch (Exception e) {
			db.rollback(conn);
			LOG.error(Message.MISTACE_DB, e);
			throw new AppException(Message.MISTACE_DB); 
		} finally {
			db.close(conn, stmt, rs);
		}
		return list;
	}
}