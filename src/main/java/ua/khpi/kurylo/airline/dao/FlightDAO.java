package ua.khpi.kurylo.airline.dao;

import ua.khpi.kurylo.airline.entity.Flight;
import ua.khpi.kurylo.airline.exception.DBException;

import java.sql.Date;
import java.util.List;

public interface FlightDAO {
    Flight getFlightById(Integer id) throws DBException;

    List<Flight> getFlightsByParameters(String from, String to, Date date) throws DBException;

    List<Flight> getFlights() throws DBException;

    void addFlight(Flight flight) throws DBException;

    void removeFlight(Integer id) throws DBException;

    void updateFlight(Integer id, Flight flight) throws DBException;

}
