package ua.nure.smirnov.finalproject.web.command;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import ua.nure.smirnov.finalproject.entity.Path;
import ua.nure.smirnov.finalproject.exception.AppException;

public class LinkingMap extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4552196959354009932L;
	private static final Logger LOG = Logger.getLogger(LinkingMap.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		String path = request.getParameter("path");
		String forward = getPage(path);
		LOG.trace("carrent page <--" + path);
		return forward;
	}

	public String getPage(String path) {
		String page = Path.PAGE_MENU;
		switch (path) {
		case "menu_page":
			page = Path.PAGE_MENU;
			return page;
		case "acceptPayDamage_page":
			page = Path.CLIENT_ACCEPT_PAY_DAMAGE;
			return page;
		case "acceptPay_page":
			page = Path.CLIENT_ACCEPT_PAY;
			return page;
		case "client_page":
			page = Path.PAGE_CLIENT_MENU;
			return page;
		case "client_old_orders_page":
			page = Path.CLIENT_SHOW_OLD_ORDERS;
			return page;
		case "regAdmin_page":
			page = Path.PAGE_ADMIN_REG;
			return page;
		case "reg_manager":
			page = Path.PAGE_MANAGER_REG;
			return page;
		case "chooseLang_page":
			page = Path.PAGE_CHOOSE_LANGUAGE;
			return page;
		case "clientDamageBill_page":
			page = Path.PAGE_CLIENT_DAMAGE_BILL;
			return page;
		case "admin_page":
			page = Path.PAGE_ADMIN_MENU;
			return page;
		case "manager_page":
			page = Path.PAGE_MANAGER_MENU;
			return page;
		case "manager_free_orders_page":
			page = Path.MANAGER_SHOW_ALL_ORDER;
			return page;
		case "regCar_page":
			page = Path.PAGE_CAR_REG;
			return page;
		case "firedManager_page":
			page = Path.PAGE_ADMIN_FIRED_MANAGER;
			return page;
		case "trnsfrOrder_page":
			page = Path.PAGE_ADMIN_TRANSFER_ORDER;
			return page;
		case "blockClient_page":
			page = Path.PAGE_CLIENT_BLOCK;
			return page;
		case "unblockClient_page":
			page = Path.PAGE_CLIENT_UNBLOCK;
			return page;
		case "manager_old_orders_page":
			page = Path.MANAGER_SHOW_OLD_ORDERS;
			return page;
		case "dismissalManager_page":
			page = Path.PAGE_ADMIN_DISSMISAL_MANAGER;
			return page;
		case "manager_return_orders_page":
			page = Path.MANAGER_RETURN_ORDER;
			return page;
		case "login_page":
			page = Path.PAGE_LOGIN;
			return page;
		case "damage_orders":
			page = Path.MANAGER_DAMAGE_ORDERS;
			return page;
		case "error_page":
			page = Path.PAGE_ERROR_PAGE;
			return page;

		}
		return page;
	}

}