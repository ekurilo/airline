package ua.khpi.kurylo.airline.command;

import org.apache.log4j.Logger;
import ua.khpi.kurylo.airline.dao.DAOFactory;
import ua.khpi.kurylo.airline.dao.UserDAO;
import ua.khpi.kurylo.airline.entity.User;
import ua.khpi.kurylo.airline.entity.UserRole;
import ua.khpi.kurylo.airline.exception.AppException;
import ua.khpi.kurylo.airline.web.Pages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCommand extends Command {

    private static final Logger LOG = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, AppException {

        LOG.info("start login command");
        HttpSession session = request.getSession();
        DAOFactory factory = DAOFactory.getDAOFactory();
        UserDAO userDAO = factory.getUserDAO();
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        System.err.println("user = " + login);
        System.err.println("pass = " + password);
        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            throw new AppException();
            //TODO realize exception with message
        }
        User user = userDAO.getUserByLogin(login);
        if (user == null || !password.equals(user.getPassword())) {
            throw new AppException();
        }
        UserRole role = UserRole.getRole(user);
        String page = Pages.PAGE_ERROR;
        if (role == UserRole.ADMIN) {
            page = Pages.PAGE_ADMIN;
        }
        if (role == UserRole.DISPATCHER) {
            page = Pages.PAGE_USER;
        }

        LOG.info("user --> " + user);

        session.setAttribute("user", user);
        session.setAttribute("userRole", role);
        LOG.info("finished login command");

        return page;
    }

}
