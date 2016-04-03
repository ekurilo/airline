package ua.khpi.kurylo.airline.dao;

import ua.khpi.kurylo.airline.entity.Employee;
import ua.khpi.kurylo.airline.exception.DBException;

import java.util.List;

public interface EmployeeDAO {
    Employee getEmployeeById(Integer id) throws DBException;

    Employee getEmployeeByLastName(String lastName) throws DBException;

    List<Employee> getEmployees() throws DBException;

    void addEmployee(Employee employee) throws DBException;

    void removeEmployee(Integer id) throws DBException;

    void changeEmployeeData(Employee employee) throws DBException;


}
