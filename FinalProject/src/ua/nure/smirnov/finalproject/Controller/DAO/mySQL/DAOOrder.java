package ua.nure.smirnov.finalproject.Controller.DAO.mySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.smirnov.finalproject.Controller.DBManager;
import ua.nure.smirnov.finalproject.Controller.DAO.DAOInterfaceOrder;
import ua.nure.smirnov.finalproject.entity.ManagerList;
import ua.nure.smirnov.finalproject.entity.OrderList;
import ua.nure.smirnov.finalproject.entity.Orders;
import ua.nure.smirnov.finalproject.exception.AppException;
import ua.nure.smirnov.finalproject.exception.Message;

public class DAOOrder implements DAOInterfaceOrder {

	private static final String SQL_UPDATE_ORDER_ACTIVE = "UPDATE orders o SET o.order_activity = ?, o.passport_fk_manager = ? WHERE o.id_Order = ? ";
	private static final String SQL_SELECT_OLD_ORDERS_FOR_CLIENT = "SELECT c.id_car, c.model, m.name_manager, m.surname,"
			+ "m.login_manager, o.id_Order, o.start_order_date, o.finish_order_date, o.order_Price, o.fk_name_function, o.passport_fk_manager, "
			+ "m.manager_phone FROM orders o, managers m, cars c WHERE o.passport_fk_client = ? AND c.id_car = o.id_fk_car AND m.index_passport = o.passport_fk_manager AND o.order_activity = 'no active'";
	private static final String SQL_INSERT_NEW_ORDER = "INSERT INTO orders (start_order_date, finish_order_date, order_Price, fk_name_function, id_fk_car, passport_fk_client, "
			+ "status_order, order_activity) Values(?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_UPDATE_ORDER_PAY = "UPDATE orders SET status_order = ? WHERE id_Order = ?";
	private static final String SQL_REJECT_ORDER = "DELETE FROM orders WHERE id_Order = ?";
	private static final String SQL_UPDATE_ORDER_DESCRIPTION = "UPDATE orders SET description_order = ? WHERE id_Order = ?";
	private static final String SQL_UPDATE_ACTIVE_OF_ORDER = "UPDATE orders SET order_activity = ? WHERE id_Order = ?";
	private static final String SQL_SELECT_ALL_ORDERS = "SELECT c.car_brand, c.id_car, c.model, cl.name_client, cl.surname,"
			+ "cl.login, o.id_Order, o.start_order_date, o.finish_order_date, o.order_Price, o.fk_name_function,"
			+ "cl.client_phone, o.description_order, o.status_order, o.order_activity, o.passport_fk_manager, o.passport_fk_client FROM orders o, cars c, clients cl WHERE c.id_car = o.id_fk_car "
			+ "AND cl.index_passport = o.passport_fk_client";
	private static final String SQL_FIND_MANAGERLIST_BY_ID = "SELECT c.id_car, c.car_brand, c.model, cl.name_client, cl.surname,"
			+ "cl.login, o.id_Order, o.start_order_date, o.finish_order_date, o.order_Price, o.fk_name_function,"
			+ "cl.client_phone, o.description_order, o.status_order, o.order_activity, o.passport_fk_manager, o.passport_fk_client FROM orders o, cars c, clients cl WHERE o.id_Order = ? AND cl.index_passport = o.passport_fk_client";
	private static final String SQL_SELECT_FOR_CLIENTS_FROM_ORDERS_AND_MANAGERS_CARS = "SELECT c.id_car, c.model, m.name_manager, m.surname,"
			+ "m.login_manager, o.id_Order, o.start_order_date, o.finish_order_date, o.order_Price, o.fk_name_function,"
			+ "m.manager_phone FROM orders o, managers m, cars c WHERE o.passport_fk_client = ? AND c.id_car = o.id_fk_car AND m.index_passport = o.passport_fk_manager AND o.order_activity != 'no active'";
	private static final String SQL_SELECT_ALL_FREE_ORDERS = "SELECT c.id_car, c.car_brand ,c.model, cl.name_client, cl.surname,"
			+ "cl.login, o.id_Order, o.start_order_date, o.finish_order_date, o.order_Price, o.fk_name_function,"
			+ "cl.client_phone FROM orders o, cars c, clients cl WHERE o.order_activity = ? AND c.id_car = o.id_fk_car AND cl.index_passport = passport_fk_client";
	private static final String SQL_SELECT_ALL_ORDERS_CLIENT_BY_INDEX = "SELECT * FROM orders WHERE passport_fk_client = ?";
	private static final String SQL_SELECT_ALL_ACTIVE_ORDER = "SELECT * FROM orders WHERE order_activity != 'inactive'";
	private static final String SQL_FIND_ORDERS_BY_ID = "SELECT * FROM orders WHERE id_Order = ?";
	private static final Logger LOG = Logger.getLogger(DAOOrder.class);
	
	@Override
	public void acceptOrder(int id, String index) throws Exception, AppException {
		Connection conn = null;
		DBManager db = null;
		PreparedStatement stmt = null;
		try {
			db = DBManager.getInstance();
			conn = db.getConnection();
			int k = 0;
			stmt = conn.prepareStatement(SQL_UPDATE_ORDER_ACTIVE);
			stmt.setString(++k, "busy");
			stmt.setString(++k, index);
			stmt.setInt(++k, id);
			stmt.executeUpdate();
			conn.commit();
			;
		} catch (Exception e) {
			db.rollback(conn);
			LOG.error(Message.MISTACE_DB, e);
			throw new AppException(Message.MISTACE_DB);
		} finally {
			db.close(conn, stmt);
		}
	}

	@Override
	public List<OrderList> selectOldOrdersForClient(String index) throws Exception, AppException {
		List<OrderList> cList = new ArrayList<OrderList>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		DBManager db = null;
		try {
			db = DBManager.getInstance();
			conn = db.getConnection();
			stmt = conn.prepareStatement(SQL_SELECT_OLD_ORDERS_FOR_CLIENT);
			int k = 0;
			stmt.setString(++k, index);
			rs = stmt.executeQuery();

			while (rs.next()) {
				cList.add(db.executeOrderList(rs));
			}
			conn.commit();
		} catch (Exception e) {
			db.rollback(conn);
			LOG.error(Message.MISTACE_DB, e);
			throw new AppException(Message.MISTACE_DB);
		} finally {
			db.close(conn, stmt, rs);
		}

		return cList;
	}

	@Override
	public void insertNewOrder(Orders order) throws Exception, AppException {
		Connection conn = null;
		PreparedStatement stmt = null;
		DBManager db = null;
		try {
			db = DBManager.getInstance();

			int k = 1;
			conn = db.getConnection();
			stmt = conn.prepareStatement(SQL_INSERT_NEW_ORDER);
			stmt.setString(k++, order.getDateStart().toString());
			stmt.setString(k++, order.getDateFinish().toString());
			stmt.setInt(k++, order.getPrice());
			stmt.setString(k++, order.getFunction());
			stmt.setInt(k++, order.getIdCar());
			stmt.setString(k++, order.getIndexClient());
			stmt.setString(k++, order.getStatus());
			stmt.setString(k++, order.getOrderActivity());
			stmt.executeUpdate();
		} catch (Exception e) {
			db.rollback(conn);
			LOG.error(Message.MISTACE_DB, e);
			throw new AppException(Message.MISTACE_DB);
		} finally {
			db.close(conn, stmt);
		}

	}

	@Override
	public void acceptPay(int id) throws Exception, AppException {
		Connection conn = null;
		DBManager db = null;
		PreparedStatement stmt = null;
		try {
			int k = 0;
			db = DBManager.getInstance();
			conn = db.getConnection();
			stmt = conn.prepareStatement(SQL_UPDATE_ORDER_PAY);
			stmt.setString(++k, "paid");
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
	public void rejectOrder(int id) throws Exception, AppException {
		Connection conn = null;
		PreparedStatement stmt = null;
		DBManager db = null;
		try {
			int k = 0;
			db = DBManager.getInstance();
			conn = db.getConnection();
			stmt = conn.prepareStatement(SQL_REJECT_ORDER);
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
	public void updateDescriptionOrder(int id, String d) throws Exception, AppException {
		Connection conn = null;
		PreparedStatement stmt = null;
		DBManager db = null;
		try {
			int k = 0;
			db = DBManager.getInstance();
			conn = db.getConnection();
			stmt = conn.prepareStatement(SQL_UPDATE_ORDER_DESCRIPTION);
			stmt.setString(++k, d);
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
	public void updateOrderActive(int id) throws Exception, AppException {
		Connection conn = null;
		PreparedStatement stmt = null;
		DBManager db = null;
		try {
			int k = 0;
			db = DBManager.getInstance();
			conn = db.getConnection();
			stmt = conn.prepareStatement(SQL_UPDATE_ACTIVE_OF_ORDER);
			stmt.setString(++k, "no active");
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
	public List<ManagerList> selectAllOrders() throws Exception, AppException {
		List<ManagerList> listM = new ArrayList<ManagerList>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		DBManager db = null;
		try {
			db = DBManager.getInstance();
			conn = db.getConnection();
			stmt = conn.prepareStatement(SQL_SELECT_ALL_ORDERS);
			rs = stmt.executeQuery();
			while (rs.next()) {
				listM.add(db.executeManagerList(rs));
			}
			conn.commit();
			db.rollback(conn);
			stmt.close();
			conn.close();
		} catch (Exception e) {
			db.rollback(conn);
			LOG.error(Message.MISTACE_DB, e);
			throw new AppException(Message.MISTACE_DB);
		} finally {
			db.close(conn, stmt, rs);
		}
		return listM;
	}

	@Override
	public ManagerList findOrdersForManagerById(int id) throws Exception, AppException {
		ManagerList mOrder = new ManagerList();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		DBManager db = null;
		try {
			int k = 0;
			db = DBManager.getInstance();
			conn = db.getConnection();
			stmt = conn.prepareStatement(SQL_FIND_MANAGERLIST_BY_ID);
			stmt.setInt(++k, id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				mOrder = db.executeManagerList(rs);
			}
			conn.commit();
		} catch (Exception e) {
			db.rollback(conn);
			LOG.error(Message.MISTACE_DB, e);
			throw new AppException(Message.MISTACE_DB);
		} finally {
			db.close(conn, stmt, rs);
		}
		return mOrder;
	}

	@Override
	public OrderList selectOrderList(String id) throws Exception, AppException {
		DBManager db = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		OrderList orderList = new OrderList();
		try {
			int k = 0;
			orderList = new OrderList();
			db = DBManager.getInstance();
			conn = db.getConnection();
			stmt = conn.prepareStatement(SQL_SELECT_FOR_CLIENTS_FROM_ORDERS_AND_MANAGERS_CARS);
			stmt.setString(++k, id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				orderList.setFinishDate(LocalDate.parse(rs.getString("finish_order_date")));
				orderList.setStartDate(LocalDate.parse(rs.getString("start_order_date")));
				orderList.setIdOrder(rs.getInt("id_Order"));
				orderList.setOrderPrice(rs.getInt("order_Price"));
				orderList.setFunction(rs.getString("fk_name_function"));
				orderList.setModel(rs.getString("model"));
				orderList.setLoginManager(rs.getString("login_Manager"));
				orderList.setNameManager(rs.getString("name_manager"));
				orderList.setSurnameManager(rs.getString("m.surname"));
				orderList.setNumberManager(rs.getString("manager_phone"));
			}
			conn.commit();
		} catch (Exception e) {
			db.rollback(conn);
			LOG.error(Message.MISTACE_DB, e);
			throw new AppException(Message.MISTACE_DB);
		} finally {
			db.close(conn, stmt, rs);
		}

		return orderList;
	}

	@Override
	public List<ManagerList> selectFreeOrdersManager() throws Exception, AppException {
		DBManager db = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<ManagerList> list = null;
		try {
			list = new ArrayList<>();
			int k = 0;
			db = DBManager.getInstance();
			conn = db.getConnection();
			stmt = conn.prepareStatement(SQL_SELECT_ALL_FREE_ORDERS);
			stmt.setString(++k, "free");
			db = DBManager.getInstance();
			rs = stmt.executeQuery();
			while (rs.next()) {
				ManagerList mList = new ManagerList();
				mList.setBrandCar(rs.getString("c.car_brand"));
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
				list.add(mList);
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
	public List<Orders> selectOrderClient(String index) throws Exception, AppException {
		List<Orders> list = new ArrayList<Orders>();
		;
		PreparedStatement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		DBManager db = null;
		try {
			db = DBManager.getInstance();

			conn = db.getConnection();
			stmt = conn.prepareStatement(SQL_SELECT_ALL_ORDERS_CLIENT_BY_INDEX);
			stmt.setString(1, index);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Orders order = new Orders();
				order.setIdOrders(rs.getInt("id_Order"));
				order.setAddFunction(rs.getString("fk_name_function"));
				order.setIdCar(rs.getInt("id_fk_car"));
				order.setIndexClient(rs.getString("passport_fk_client"));
				order.setIndexManager(rs.getString("passport_fk_manager"));
				order.setDateStart(rs.getDate("start_order_date").toLocalDate());
				order.setDateFinish(rs.getDate("finish_order_date").toLocalDate());
				order.setPrice(rs.getInt("order_Price"));
				order.setStatus(rs.getString("status_order"));
				order.setOrderActivity(rs.getString("order_activity"));
				list.add(order);
			}
			conn.commit();
		} catch (Exception ex) {
			db.rollback(conn);
			LOG.error(Message.MISTACE_DB, ex);
			throw new AppException(Message.MISTACE_DB);
		} finally {
			db.close(conn, stmt, rs);
		}
		return list;
	}

	@Override
	public List<Orders> selectAllOrder() throws Exception, AppException {
		List<Orders> list = new ArrayList<Orders>();
		PreparedStatement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		DBManager db = null;
		try {
			db = DBManager.getInstance();
			conn = db.getConnection();
			stmt = conn.prepareStatement(SQL_SELECT_ALL_ACTIVE_ORDER);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Orders order = new Orders();
				order.setIdOrders(rs.getInt("id_Order"));
				order.setAddFunction(rs.getString("fk_name_function"));
				order.setIdCar(rs.getInt("id_fk_car"));
				order.setIndexClient(rs.getString("passport_fk_client"));
				order.setIndexManager(rs.getString("passport_fk_manager"));
				order.setDateStart(rs.getDate("start_order_date").toLocalDate());
				order.setDateFinish(rs.getDate("finish_order_date").toLocalDate());
				order.setPrice(rs.getInt("order_Price"));
				order.setStatus(rs.getString("status_order"));
				order.setOrderActivity(rs.getString("order_activity"));
				list.add(order);
			}
			conn.commit();
			db.rollback(conn);
			stmt.close();
			conn.close();
		} catch (Exception ex) {
			db.rollback(conn);
			LOG.error(Message.MISTACE_DB, ex);
			throw new AppException(Message.MISTACE_DB);
		} finally {
			db.close(conn, stmt, rs);
		}
		return list;
	}

	@Override
	public Orders findOrderById(int id) throws Exception, AppException {
		Orders order = null;
		DBManager db = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			int k = 0;
			db = DBManager.getInstance();
			conn = db.getConnection();
			stmt = conn.prepareStatement(SQL_FIND_ORDERS_BY_ID);
			stmt.setInt(++k, id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				order = new Orders();
				order.setAddFunction(rs.getString("fk_name_function"));
				order.setDateFinish(rs.getDate("finish_order_date").toLocalDate());
				order.setDateStart(rs.getDate("start_order_date").toLocalDate());
				order.setIdCar(rs.getInt("id_fk_car"));
				order.setIdOrders(rs.getInt("id_Order"));
				order.setIndexClient(rs.getString("passport_fk_client"));
				order.setIndexManager(rs.getString("passport_fk_manager"));
				order.setOrderActivity(rs.getString("order_activity"));
				order.setPrice(rs.getInt("order_Price"));
				order.setStatus(rs.getString("status_order"));
				order.setDescripton(rs.getString("description_order"));
			}
			conn.commit();

			stmt.close();
			conn.close();
		} catch (Exception e) {
			db.rollback(conn);
			LOG.error(Message.MISTACE_DB, e);
			throw new AppException(Message.MISTACE_DB);
		} finally {
			db.close(conn, stmt, rs);
		}

		return order;
	}

}
