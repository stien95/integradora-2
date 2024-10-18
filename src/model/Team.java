package model;

public class Team {
    private String name;
    private String country;
    private String coachName;

    public Team(String name, String country, String coachName) {
        this.name = name;
        this.country = country;
        this.coachName = coachName;
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

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    
    
}
