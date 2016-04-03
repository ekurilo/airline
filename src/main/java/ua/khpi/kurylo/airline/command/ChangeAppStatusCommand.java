package ua.khpi.kurylo.airline.command;

import ua.khpi.kurylo.airline.dao.ApplicationDAO;
import ua.khpi.kurylo.airline.dao.DAOFactory;
import ua.khpi.kurylo.airline.entity.status.ApplicationStatus;
import ua.khpi.kurylo.airline.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeAppStatusCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, AppException {

        Integer id = Integer.parseInt(request.getParameter("id"));
        String status = request.getParameter("statusId");
        if (status == null || status.isEmpty()) {
            throw new AppException();
        }

        DAOFactory factory = DAOFactory.getDAOFactory();
        ApplicationDAO dao = factory.getApplicationDAO();
        if (status.equals("1")) {
            dao.changeStatus(id, ApplicationStatus.DONE);
        }
        if (status.equals("2")) {
            dao.changeStatus(id, ApplicationStatus.REJECTED);
        }
        return "/controller?command=viewOpenedApps";
    }

}
