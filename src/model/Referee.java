package model;

public class Referee extends Person {
    private RefereeType type;

    public Referee(String id, String name, String country, int typeOption) {
        super(id, name, country);
        setType(typeOption);
    }

    public RefereeType getType() {
        return type;
    }

    public void setType(int typeOption) {
        switch (typeOption) {
            case 1:
                this.type = RefereeType.CENTRAL;
                break;
            case 2:
                this.type = RefereeType.ASSISTANT;
                break;
        }
    }

    public boolean equalsType(int typeOption) {
        boolean equals = false;
        switch (typeOption) {
            case 1:
                equals = type == RefereeType.CENTRAL;
                break;
            case 2:
                equals = type == RefereeType.ASSISTANT;
                break;
        }
        return equals;
    }

    @Override
    public String toString() {
        return super.toString() + "\nTipo de árbitro: " + type;
    }
}
