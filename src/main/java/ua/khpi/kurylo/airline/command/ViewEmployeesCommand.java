package ua.khpi.kurylo.airline.command;

import ua.khpi.kurylo.airline.dao.DAOFactory;
import ua.khpi.kurylo.airline.dao.EmployeeDAO;
import ua.khpi.kurylo.airline.entity.Employee;
import ua.khpi.kurylo.airline.entity.Profession;
import ua.khpi.kurylo.airline.exception.AppException;
import ua.khpi.kurylo.airline.web.Pages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ViewEmployeesCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, AppException {
        DAOFactory factory = DAOFactory.getDAOFactory();
        EmployeeDAO dao = factory.getEmployeeDAO();
        List<Employee> employees = dao.getEmployees();
        request.setAttribute("employees", employees);
        request.setAttribute("profs", Profession.values());
        System.err.println(employees.size());

        return Pages.PAGE_VIEW_EMPLOYEES;
    }

}
