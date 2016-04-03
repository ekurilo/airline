package ua.khpi.kurylo.airline.command;

import ua.khpi.kurylo.airline.dao.BrigadeDAO;
import ua.khpi.kurylo.airline.dao.DAOFactory;
import ua.khpi.kurylo.airline.entity.Brigade;
import ua.khpi.kurylo.airline.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddBrigadeCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, AppException {
        DAOFactory factory = DAOFactory.getDAOFactory();
        BrigadeDAO dao = factory.getBrigadeDAO();
        String name = request.getParameter("name");
        Integer userId = Integer.parseInt(request.getParameter("userId"));
        Brigade b = new Brigade();
        b.setName(name);
        b.setUserId(userId);
        dao.createBrigade(b);
        return "controller?command=viewBrigades"; //TODO to replace constant
    }

}
