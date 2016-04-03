package ua.khpi.kurylo.airline.entity;

public enum Profession {
    PILOT, NAVIGATOR, RADIOMAN, STEWARDESS;

    public static Profession getProfession(Employee employee) {
        return values()[employee.getProfessionId()];
    }

    public Profession[] getProfessions() {
        return values();
    }

    public String getName() {
        return name().toLowerCase();
    }

}
