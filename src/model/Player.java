package model;

public class Player extends Person {
    private int dorsal;
    private Position position;
    private Team team;

    public Player(String id, String name, String country, int dorsal, int positionOption, Team team) {
        super(id, name, country);
        this.dorsal = dorsal;
        this.team = team;
        setPosition(positionOption);
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(int position) {
        switch (position) {
            case 1:
                this.position = Position.GOALKEEPER;
                break;
            case 2:
                this.position = Position.DEFENDER;
                break;
            case 3:
                this.position = Position.MIDFIELDER;
                break;
            case 4:
                this.position = Position.FORWARD;
                break;
        }
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return super.toString() + "\nDorsal: " + dorsal + "\nPosición: " + position;
    }
    
}
