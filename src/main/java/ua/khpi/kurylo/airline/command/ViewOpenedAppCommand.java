package ua.khpi.kurylo.airline.command;

import ua.khpi.kurylo.airline.dao.ApplicationDAO;
import ua.khpi.kurylo.airline.dao.DAOFactory;
import ua.khpi.kurylo.airline.entity.Application;
import ua.khpi.kurylo.airline.exception.AppException;
import ua.khpi.kurylo.airline.web.Pages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ViewOpenedAppCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, AppException {
        DAOFactory factory = DAOFactory.getDAOFactory();
        ApplicationDAO dao = factory.getApplicationDAO();
        List<Application> applications = dao.getOpenedApplications();
        request.setAttribute("apps", applications);
        request.setAttribute("status", "opened");
        return Pages.PAGE_VIEW_APPS;
    }

}
