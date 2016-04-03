package ua.khpi.kurylo.airline.entity.status;

import ua.khpi.kurylo.airline.entity.Application;

public enum ApplicationStatus {
    OPENED, DONE, REJECTED;

    public static ApplicationStatus getStatus(Application app) {
        return values()[app.getStatusId()];
    }

    public String getName() {
        return name().toLowerCase();
    }

}
