package ua.nure.smirnov.finalproject.Controller.DAO.mySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

import ua.nure.smirnov.finalproject.Controller.DBManager;
import ua.nure.smirnov.finalproject.Controller.DAO.DAOInterfaceFunction;
import ua.nure.smirnov.finalproject.entity.DamageBill;
import ua.nure.smirnov.finalproject.entity.Function;
import ua.nure.smirnov.finalproject.exception.AppException;
import ua.nure.smirnov.finalproject.exception.Message;

public class DAOFunction implements DAOInterfaceFunction {
	
	public final static String SQL_FIND_FUNCTION_BY_NAME = "SELECT * FROM functions WHERE name_function=?";
	private static final Logger LOG = Logger.getLogger(DAOFunction.class);
	

	@Override
	public Function findFunctionForName(String name) throws Exception, AppException {
		Function fnctn = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		DBManager db = null;
		try {
			db = DBManager.getInstance();

			conn = db.getConnection();
			stmt = conn.prepareStatement(SQL_FIND_FUNCTION_BY_NAME);
			stmt.setString(1, name);
			rs = stmt.executeQuery();
			if (rs.next()) {
				fnctn = new Function();
				fnctn.setFunction(rs.getString("name_function"));
				fnctn.setPrice(rs.getInt("price"));
			}
			conn.commit();
		} catch (Exception e) {
			db.rollback(conn);
			LOG.error(Message.MISTACE_DB, e);
			throw new AppException(Message.MISTACE_DB);
		} finally {
			db.close(conn, stmt, rs);
		}
		return fnctn;
	}

}
