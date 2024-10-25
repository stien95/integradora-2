package model;

public class Team {
    private String name;
    private String country;
    private Person coach;

    public Team(String name, String country, Person coach) {
        this.name = name;
        this.country = country;
        this.coach = coach;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Person getCoach() {
        return coach;
    }

    public void setCoach(Person coach) {
        this.coach = coach;
    }
}
