package ua.khpi.kurylo.airline.entity.status;

import ua.khpi.kurylo.airline.entity.Flight;

public enum FlightStatus {
    OPENED, DONE, CANCELED;

    public static FlightStatus getStatus(Flight flight) {
        return values()[flight.getStatusId()];
    }

    public String getName() {
        return name();
    }

}

