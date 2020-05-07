package ua.nure.smirnov.finalproject.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.smirnov.finalproject.Controller.DBManager;
import ua.nure.smirnov.finalproject.Controller.ManagerMenuController;
import ua.nure.smirnov.finalproject.Controller.DAO.DAOInterfaceCar;
import ua.nure.smirnov.finalproject.Controller.DAO.mySQL.DAOCar;
import ua.nure.smirnov.finalproject.entity.Cars;
import ua.nure.smirnov.finalproject.entity.Path;
import ua.nure.smirnov.finalproject.exception.AppException;

public class UpdateCarCommand extends Command {
	private static final Logger LOG = Logger.getLogger(ManagerMenuController.class);

	private static final long serialVersionUID = 6159124673555025378L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {

		String forward = null;
		if (request.getParameter("updateCar") != null) {
			int id = Integer.parseInt(request.getParameter("id"));
			DAOInterfaceCar daoCar = new DAOCar();
			LOG.trace("Current car's id --> " + id);
			String model = request.getParameter("model");
			LOG.trace("Current car's model --> " + model);
			String statusCar = request.getParameter("statusCar");
			LOG.trace("Current car's status --> " + statusCar);
			int price = Integer.parseInt(request.getParameter("price"));
			LOG.trace("Current car's price --> " + price);
			int idAdmin = Integer.parseInt(request.getParameter("idAdmin"));
			LOG.trace("Current idAdmin --> " + idAdmin);
			String carLevel = request.getParameter("carLevel");
			LOG.trace("Current car's level --> " + carLevel);
			String condCar = request.getParameter("conditionCar");
			LOG.trace("Current car's description --> " + condCar);
			String carBrand = request.getParameter("carBrand");
			LOG.trace("Current car's brand --> " + carBrand);
			String vin = request.getParameter("vin");
			LOG.trace("Current car's vin --> " + vin);
			Cars car = new Cars();
			car.setCarBrand(carBrand);
			car.setIdCar(id);
			car.setCarLevel(carLevel);
			car.setIdAdmin(idAdmin);
			car.setModel(model);
			car.setPrice(price);
			car.setStatus(statusCar);
			car.setConditionCar(condCar);
			car.setVin(vin);
			try {

				daoCar.updateCar(car);
			} catch (Exception e) {
				e.printStackTrace();
			}

			forward = Path.COMMAND_MENU_ADMIN;
			return forward;
		}

		if (request.getParameter("deleteCars") != null) {
			forward = Path.COMMAND_MENU_ADMIN;
		}

		if (request.getParameter("menu") != null) {
			forward = Path.COMMAND_FIRST_MENU;
		}

		return forward;

	}
}
