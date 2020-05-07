package ua.nure.smirnov.finalproject.entity;

/**
 * Path holder (jsp pages, controller commands).
 * 
 * @author D.Kolesnikov
 * 
 */
public final class Path {

	// pages
	public static final String PAGE_ADMIN_DISSMISAL_MANAGER = "/WEB-INF/jsp/admin/dismissalManager.jsp";
	public static final String PAGE_LOGIN = "/login.jsp";
	public static final String PAGE_MENU = "/menu.jsp";
	public static final String PAGE_CHOOSE_LANGUAGE = "/settings.jsp";
	public static final String PAGE_ERROR_PAGE = "/WEB-INF/jsp/error_page.jsp";
	public static final String PAGE_ADMIN_FIRED_MANAGER = "/WEB-INF/jsp/admin/firedManager.jsp";
	public static final String PAGE_ADMIN_MENU = "/WEB-INF/jsp/admin/adminMenu.jsp";
	public static final String PAGE_MANAGER_MENU = "/WEB-INF/jsp/manager/managerMenu.jsp";
	public static final String PAGE_ADMIN_REG = "/WEB-INF/jsp/admin/regAdmin.jsp";
	public static final String PAGE_CAR_REG = "/WEB-INF/jsp/admin/regCar.jsp";
	public static final String PAGE_ADMIN_TRANSFER_ORDER = "/WEB-INF/jsp/admin/transferOrder.jsp";
	public static final String PAGE_CAR_UPDATE = "/WEB-INF/jsp/admin/updateCar.jsp";
	public static final String PAGE_CLIENT_BLOCK = "/WEB-INF/jsp/admin/blockClient.jsp";
	public static final String PAGE_CLIENT_UNBLOCK = "/WEB-INF/jsp/admin/unblockClient.jsp";
	public static final String PAGE_CAR_ORDER = "/WEB-INF/jsp/client/orderCar.jsp";
	public static final String PAGE_MANAGER_REG = "/WEB-INF/jsp/admin/MenegerReg.jsp";
	public static final String PAGE_CLIENT_DAMAGE_BILL = "/WEB-INF/jsp/client/damageBill.jsp";
	public static final String PAGE_CLIENT_MENU = "/WEB-INF/jsp/client/clientMenu.jsp";
	public static final String PAGE_SETTINGS = "/WEB-INF/jsp/settings.jsp";
	public static final String MANAGER_SHOW_ALL_ORDER = "/WEB-INF/jsp/manager/showFreeOrder.jsp";
	public static final String MANAGER_RETURN_ORDER = "/WEB-INF/jsp/manager/returnOrderManager.jsp";
	public static final String MANAGER_SHOW_OLD_ORDERS = "/WEB-INF/jsp/manager/showOldOrders.jsp";
	public static final String CLIENT_SHOW_OLD_ORDERS = "/WEB-INF/jsp/client/showOldOrder.jsp";
	public static final String MANAGER_DAMAGE_BILL = "/WEB-INF/jsp/manager/damageBill.jsp";
	public static final String MANAGER_DAMAGE_ORDERS = "/WEB-INF/jsp/manager/damageOrders.jsp";
	public static final String CLIENT_ACCEPT_PAY = "/WEB-INF/jsp/client/acceptPay.jsp";
	public static final String CLIENT_ACCEPT_PAY_DAMAGE = "/WEB-INF/jsp/client/acceptPayDamage.jsp";	
	// commands
	public static final String COMMAND_ACCEPT_PAY_DAMAGE= "controller?command=map&path=acceptPayDamage_page";
	public static final String COMMAND_ACCEPT_PAY= "controller?command=map&path=acceptPay_page";
	public static final String COMMAND_CHOOSE_LANGUAGE = "controller?command=map&path=chooseLang_page";
	public static final String COMMAND_MENU_TO_CLIENT_DAMAGE_BILL = "controller?command=map&path=clientDamageBill_page";
	public static final String COMMAND_MENU_ADMIN_FIRED_PAGE = "controller?command=map&path=trnsfrOrder_page";
	public static final String COMMAND_MENU_ADMIN_TRANSFER_ORDER = "controller?command=map&path=trnsfrOrder_page";
	public static final String COMMAND_MENU_ADMIN_DISSMISAL_PAGE = "controller?command=map&path=dismissalManager_page";
	public static final String COMMAND_MENU_ADMIN_UNBLOCK_CLIENT = "controller?command=map&path=unblockClient_page";
	public static final String COMMAND_MENU_ADMIN_BLOCK_CLIENT = "controller?command=map&path=blockClient_page";
	public static final String COMMAND_MENU_ADMIN_REG_CAR = "controller?command=map&path=regCar_page";
	public static final String COMMAND_MENU_ADMIN_REG_ADMIN = "controller?command=map&path=regAdmin_page";
	public static final String COMMAND_MENU_ADMIN = "controller?command=map&path=admin_page";
	public static final String COMMAND_ERROR_PAGE = "controller?command=map&path=error_page";
	public static final String COMMAND_DAMAGE_ORDERS = "controller?command=map&path=damage_orders";
	public static final String COMMAND_MENU_ADMIN_REG_MANAGER = "controller?command=map&path=reg_manager";
	public static final String COMMAND_MENU_LOGIN = "controller?command=map&path=login_page";
	public static final String COMMAND_MENU_MANAGER_RETURN_ORDERS = "controller?command=map&path=manager_return_orders_page";
	public static final String COMMAND_MENU_MANAGER_OLD_ORDERS = "controller?command=map&path=manager_old_orders_page";
	public static final String COMMAND_MENU_MANAGER_FREE_ORDERS = "controller?command=map&path=manager_free_orders_page";
	public static final String COMMAND_MENU_MANAGER = "controller?command=map&path=manager_page";
	public static final String COMMAND_CLIENT_OLD_ORDERS = "controller?command=map&path=client_old_orders_page";
	public static final String COMMAND_FIRST_MENU = "controller?command=linkToMenu&path=menu_page";
	public static final String COMMAND_MENU = "controller?command=map&path=menu_page";
	public static final String COMMAND_MENU_CLIENT = "controller?command=map&path=client_page";
	public static final String COMMAND_LIST_ORDERS = "/controller?command=listOrders";
	public static final String COMMAND_LIST_MENU = "/controller?command=listMenu";

}