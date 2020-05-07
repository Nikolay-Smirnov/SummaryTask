package ua.nure.smirnov.finalproject.web.command;

import java.util.Map;

import java.util.TreeMap;

import org.apache.log4j.Logger;

import ua.nure.smirnov.finalproject.Controller.AdminMenuController;
import ua.nure.smirnov.finalproject.Controller.ClientMenuController;
import ua.nure.smirnov.finalproject.Controller.ManagerMenuController;
import ua.nure.smirnov.finalproject.web.Link.LinkOrderCar;
import ua.nure.smirnov.finalproject.web.Link.LinkToMenu;
import ua.nure.smirnov.finalproject.web.Link.LinkUpdateCar;

public class CommandConteiner {
	private static final Logger LOG = Logger.getLogger(CommandConteiner.class);
	private static Map<String, Command> commands = new TreeMap<String, Command>();

	static {
		// common commands
		commands.put("map", new LinkingMap());
		commands.put("login", new LoginCommand());
		commands.put("Menu", new MenuCommand());
		commands.put("reg", new RegClientCommand());
		commands.put("regCar", new AdminMenuController());
		commands.put("deleteCars", new AdminMenuController());
		commands.put("LinkOrderCar", new LinkOrderCar());
		commands.put("LinkUpdateCar", new LinkUpdateCar());
		commands.put("UpdateCar", new UpdateCarCommand());
		commands.put("OrderCar", new OrderCarCommand());
		commands.put("BlockClient", new AdminMenuController());
		commands.put("UnblockClient", new AdminMenuController());
		commands.put("ManagerCabinet", new ManagerMenuController());
		commands.put("ManagerShowFreeOrders", new ManagerMenuController());
		commands.put("UpdateDescriptionOrder", new ManagerMenuController());
		commands.put("clientCabinet", new ClientMenuController());
		commands.put("oldClientOrders", new ClientMenuController());
		commands.put("damageBillClient", new ClientMenuController());
		commands.put("DamageBill", new ManagerMenuController());
		commands.put("linkToMenu", new LinkToMenu());
		commands.put("damageOrders", new ManagerMenuController());
		commands.put("managerOldOrders", new ManagerMenuController());
		commands.put("freeOrders", new ManagerMenuController());
		commands.put("deleteCars", new AdminMenuController());
		commands.put("regManager", new AdminMenuController());
		commands.put("RegNewAdmin", new AdminMenuController());
		commands.put("dissmialManager", new AdminMenuController());
		commands.put("firedManager", new AdminMenuController());
		commands.put("transferOrder", new AdminMenuController());
		commands.put("acceptPay", new ClientMenuController());
		commands.put("acceptPayDamage", new ClientMenuController());
	}

	/**
	 * Returns command object with the given name.
	 * 
	 * @param commandName Name of the command.
	 * @return Command object.
	 */

	public static Command get(String commandName) {
		LOG.trace("name of command -->" + commandName);
		if (commandName == null || !commands.containsKey(commandName)) {
			return commands.get("noCommand");
		}

		return commands.get(commandName);
	}
}
