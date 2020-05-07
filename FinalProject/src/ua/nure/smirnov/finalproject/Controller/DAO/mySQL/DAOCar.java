package ua.nure.smirnov.finalproject.Controller.DAO.mySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.smirnov.finalproject.Controller.DBManager;
import ua.nure.smirnov.finalproject.Controller.DAO.DAOInterfaceCar;
import ua.nure.smirnov.finalproject.entity.Cars;
import ua.nure.smirnov.finalproject.exception.AppException;
import ua.nure.smirnov.finalproject.exception.Message;

public class DAOCar implements DAOInterfaceCar {

	private static final String SQL_SELECT_ALL_CARS = "SELECT * FROM cars";
	private static final String SQL_SELECT_ALL_CARS_BY_STATUS = "SELECT * FROM cars WHERE status_car = 'free'";
	private static final String SQL_DELETE_CAR = "DELETE FROM cars WHERE id_car = ?";
	private static final String SQL_UPDATE_CAR = "UPDATE cars SET car_brand = ?, model = ?, car_level = ?, price = ?, status_car = ?, id_fk_admin = ?, VIN = ?, condition_car = ? WHERE id_car = ?";
	public final static String SQL_FIND_CAR_DY_ID = "SELECT * FROM cars WHERE id_car = ?";
	private static final String SQL_INSERT_INTO_CARS = "INSERT INTO cars (model, car_brand, car_level, price, status_car, id_fk_admin, VIN, condition_car) Values(?, ?, ?, ?, ?, ?, ?, ?)";
	private static final Logger LOG = Logger.getLogger(DAOCar.class);

	@Override
	public List<Cars> selectAllCars() throws Exception, AppException {
		List<Cars> listCars = new ArrayList<Cars>();
		PreparedStatement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		DBManager db = null;
		try {
			db = DBManager.getInstance();

			conn = db.getConnection();
			stmt = conn.prepareStatement(SQL_SELECT_ALL_CARS);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Cars car = new Cars();
				car.setIdCar(rs.getInt("id_car"));
				car.setCarLevel(rs.getString("car_level"));
				car.setModel(rs.getString("model"));
				car.setPrice(rs.getInt("price"));
				car.setCarBrand(rs.getString("car_brand"));
				car.setStatus(rs.getString("status_car"));
				car.setIdAdmin(rs.getInt("id_fk_admin"));
				car.setVin(rs.getString("VIN"));
				car.setConditionCar(rs.getString("condition_car"));
				listCars.add(car);

			}
			conn.commit();
		} catch (Exception e) {
			db.rollback(conn);
			LOG.error(Message.MISTACE_DB, e);
			throw new AppException(Message.MISTACE_DB);
		} finally {
			db.close(conn, stmt, rs);
		}
		return listCars;
	}
	
	@Override
	public List<Cars> selectAllCarsByStatus() throws Exception, AppException {
		List<Cars> listCars = new ArrayList<Cars>();
		PreparedStatement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		DBManager db = null;
		try {
			db = DBManager.getInstance();

			conn = db.getConnection();
			stmt = conn.prepareStatement(SQL_SELECT_ALL_CARS_BY_STATUS);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Cars car = new Cars();
				car.setIdCar(rs.getInt("id_car"));
				car.setCarLevel(rs.getString("car_level"));
				car.setModel(rs.getString("model"));
				car.setPrice(rs.getInt("price"));
				car.setCarBrand(rs.getString("car_brand"));
				car.setStatus(rs.getString("status_car"));
				car.setIdAdmin(rs.getInt("id_fk_admin"));
				car.setVin(rs.getString("VIN"));
				car.setConditionCar(rs.getString("condition_car"));
				listCars.add(car);

			}
			conn.commit();
		} catch (Exception e) {
			db.rollback(conn);
			LOG.error(Message.MISTACE_DB, e);
			throw new AppException(Message.MISTACE_DB);
		} finally {
			db.close(conn, stmt, rs);
		}
		return listCars;
	}

	@Override
	public void deleteCar(int id) throws Exception, AppException {
		DBManager db = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			db = DBManager.getInstance();
			conn = db.getConnection();
			stmt = conn.prepareStatement(SQL_DELETE_CAR);
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
	public void updateCar(Cars car) throws Exception, AppException {
		Connection conn = null;
		PreparedStatement stmt = null;
		DBManager db = null;
		try {
			int k = 0;
		db = DBManager.getInstance();
			conn = db.getConnection();
			stmt = conn.prepareStatement(SQL_UPDATE_CAR);
			stmt.setString(++k, car.getCarBrand());
			stmt.setString(++k, car.getModel());
			stmt.setString(++k, car.getCarLevel());
			stmt.setInt(++k, car.getPrice());
			stmt.setString(++k, car.getStatus());
			stmt.setInt(++k, car.getIdAdmin());
			stmt.setString(++k, car.getVin());
			stmt.setString(++k, car.getConditionCar());
			stmt.setInt(++k, car.getIdCars());
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
	public Cars findCarById(int id) throws Exception, AppException {
		Cars car = new Cars();
		DBManager db = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			db = DBManager.getInstance();

			conn = db.getConnection();
			stmt = conn.prepareStatement(SQL_FIND_CAR_DY_ID);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				car.setIdCar(rs.getInt("id_car"));
				car.setModel(rs.getString("model"));
				car.setCarBrand(rs.getString("car_brand"));
				car.setCarLevel(rs.getString("car_level"));
				car.setIdAdmin(rs.getInt("id_fk_admin"));
				car.setPrice(rs.getInt("price"));
				car.setStatus(rs.getString("status_car"));
				car.setVin(rs.getString("VIN"));
				car.setConditionCar(rs.getString("condition_car"));
			}
			conn.commit();
		} catch (Exception e) {
			db.rollback(conn);
			LOG.error(Message.MISTACE_DB, e);
			throw new AppException(Message.MISTACE_DB);
		} finally {
			db.close(conn, stmt);
		}
		return car;
	}

	@Override
	public void insertCar(Cars car) throws Exception, AppException {
		PreparedStatement stmt = null;
		Connection conn = null;
		DBManager db = null;
		try {
			db = DBManager.getInstance();
			conn = db.getConnection();
			int k = 0;
			stmt = conn.prepareStatement(SQL_INSERT_INTO_CARS);
			stmt.setString(++k, car.getModel());
			stmt.setString(++k, car.getCarBrand());
			stmt.setString(++k, car.getCarLevel());
			stmt.setInt(++k, car.getPrice());
			stmt.setString(++k, car.getStatus());
			stmt.setInt(++k, car.getIdAdmin());
			stmt.setString(++k, car.getVin());
			stmt.setString(++k, car.getConditionCar());
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
