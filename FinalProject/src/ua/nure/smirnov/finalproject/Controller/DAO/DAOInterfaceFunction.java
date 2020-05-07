package ua.nure.smirnov.finalproject.Controller.DAO;

import ua.nure.smirnov.finalproject.entity.Function;
import ua.nure.smirnov.finalproject.exception.AppException;

public interface DAOInterfaceFunction {

	public Function findFunctionForName(String name) throws Exception, AppException;
}
