package ua.nure.smirnov.finalproject.Controller;

import java.io.IOException;

import ua.nure.smirnov.finalproject.exception.AppException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.smirnov.finalproject.web.command.CommandConteiner;
import ua.nure.smirnov.finalproject.entity.Path;
import ua.nure.smirnov.finalproject.web.command.Command;

public class Controller extends HttpServlet {
	private static final Logger LOG = Logger.getLogger(Controller.class);
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String GET = "GET";
			process(request, response, GET);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String POST = "POST";
			process(request, response, POST);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void process(HttpServletRequest request, HttpServletResponse response, String method)
			throws IOException, ServletException {

		String commandName = request.getParameter("command");

		Command command = CommandConteiner.get(commandName);

		String forward = Path.PAGE_ERROR_PAGE;

		try {
			forward = command.execute(request, response);
			LOG.trace("Forward has way --> " + forward);
		} catch (AppException e) {
			LOG.trace("Execption's message --> " + e.getMessage());
			request.setAttribute("errorMessage", e.getMessage());
		} catch (Exception ex) {
			LOG.trace("Execption's message --> " + ex.getMessage());
			request.setAttribute("errorMessage", ex.getMessage());
		}

		if (forward == Path.PAGE_ERROR_PAGE) {
			request.getRequestDispatcher(forward).forward(request, response);
		}

		if (forward != Path.PAGE_ERROR_PAGE) {
			if (method.contentEquals("GET")) {
				request.getRequestDispatcher(forward).forward(request, response);
			} else {
				response.sendRedirect(forward);
			}
		}
	}
}
