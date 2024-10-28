package model;

public class Player extends Person {
    private int dorsal;
    private PlayerPosition position;

    public Player(String id, String name, String country, int dorsal, int positionOption) {
        super(id, name, country);
        this.dorsal = dorsal;
        setPosition(positionOption);
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public PlayerPosition getPosition() {
        return position;
    }

    public void setPosition(int position) {
        switch (position) {
            case 1:
                this.position = PlayerPosition.GOALKEEPER;
                break;
            case 2:
                this.position = PlayerPosition.DEFENDER;
                break;
            case 3:
                this.position = PlayerPosition.MIDFIELDER;
                break;
            case 4:
                this.position = PlayerPosition.FORWARD;
                break;
        }
    }

    @Override
    public String toString() {
        return super.toString() + "\nDorsal: " + dorsal + "\nPosición: " + position;
    }

}
