package ua.khpi.kurylo.airline.dao;

import ua.khpi.kurylo.airline.entity.Application;
import ua.khpi.kurylo.airline.entity.status.ApplicationStatus;
import ua.khpi.kurylo.airline.exception.DBException;

import java.util.List;

public interface ApplicationDAO {

    Application getApplicationById(Integer id) throws DBException;

    List<Application> getApplications() throws DBException;

    List<Application> getOpenedApplications() throws DBException;

    void changeStatus(Integer id, ApplicationStatus status) throws DBException;

    void addApplication(Application app) throws DBException;


}
