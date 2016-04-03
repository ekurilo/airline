package ua.khpi.kurylo.airline.command;

import ua.khpi.kurylo.airline.dao.ApplicationDAO;
import ua.khpi.kurylo.airline.dao.DAOFactory;
import ua.khpi.kurylo.airline.entity.Application;
import ua.khpi.kurylo.airline.entity.status.ApplicationStatus;
import ua.khpi.kurylo.airline.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateAppCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, AppException {
        String message = request.getParameter("message");
        Integer brigadeId = Integer.parseInt(request.getParameter("brigadeId"));
        if (message == null || message.isEmpty() || brigadeId == null) {
            throw new AppException();
        }
        Application app = new Application();
        app.setBrigadeId(brigadeId);
        app.setMessage(message);
        app.setStatusId(ApplicationStatus.OPENED.ordinal()); //TODO refactor this!!!
        DAOFactory factory = DAOFactory.getDAOFactory();
        ApplicationDAO dao = factory.getApplicationDAO();
        dao.addApplication(app);
        String forward = "/controller?command=brigadeInf&id=" + brigadeId;
        return forward;
    }

}
