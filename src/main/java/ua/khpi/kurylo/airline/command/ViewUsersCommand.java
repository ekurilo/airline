package ua.khpi.kurylo.airline.command;

import ua.khpi.kurylo.airline.dao.DAOFactory;
import ua.khpi.kurylo.airline.dao.UserDAO;
import ua.khpi.kurylo.airline.entity.User;
import ua.khpi.kurylo.airline.exception.AppException;
import ua.khpi.kurylo.airline.web.Pages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ViewUsersCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, AppException {
        DAOFactory factory = DAOFactory.getDAOFactory();
        UserDAO dao = factory.getUserDAO();
        List<User> users = dao.getUsers();
        request.setAttribute("users", users);
        return Pages.PAGE_VIEW_USERS;
    }

}
