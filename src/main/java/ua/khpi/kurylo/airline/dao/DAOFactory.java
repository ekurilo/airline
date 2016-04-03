package ua.khpi.kurylo.airline.dao;

import ua.khpi.kurylo.airline.dao.mysql.MySqlDAOFactory;
import ua.khpi.kurylo.airline.exception.DBException;

public abstract class DAOFactory {
    public static DAOFactory getDAOFactory() throws DBException {
        return MySqlDAOFactory.getInstance();
    }

    public abstract ApplicationDAO getApplicationDAO();

    public abstract BrigadeDAO getBrigadeDAO();

    public abstract FlightDAO getFlightDAO();

    public abstract EmployeeDAO getEmployeeDAO();

    public abstract UserDAO getUserDAO();


}
