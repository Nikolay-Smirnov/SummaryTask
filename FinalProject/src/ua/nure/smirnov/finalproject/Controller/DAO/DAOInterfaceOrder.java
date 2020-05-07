package ua.nure.smirnov.finalproject.Controller.DAO;

import java.util.List;

import ua.nure.smirnov.finalproject.entity.ManagerList;
import ua.nure.smirnov.finalproject.entity.OrderList;
import ua.nure.smirnov.finalproject.entity.Orders;
import ua.nure.smirnov.finalproject.exception.AppException;

public interface DAOInterfaceOrder {
	public void acceptOrder(int id, String index) throws Exception, AppException;

	public List<OrderList> selectOldOrdersForClient(String index) throws Exception, AppException;

	public void insertNewOrder(Orders order) throws Exception, AppException;

	public void acceptPay(int id) throws Exception, AppException;

	public void rejectOrder(int id) throws Exception, AppException;

	public void updateDescriptionOrder(int id, String d) throws Exception, AppException;

	public void updateOrderActive(int id) throws Exception, AppException;

	public List<ManagerList> selectAllOrders() throws Exception, AppException;

	public ManagerList findOrdersForManagerById(int id) throws Exception, AppException;

	public OrderList selectOrderList(String id) throws Exception, AppException;
	
	public List<ManagerList> selectFreeOrdersManager() throws Exception, AppException;
	
	public List<Orders> selectOrderClient(String index) throws Exception, AppException;
	
	public List<Orders> selectAllOrder() throws Exception, AppException;
	
	public Orders findOrderById(int id) throws Exception, AppException;

}
