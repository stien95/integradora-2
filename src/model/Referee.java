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

    @Override
    public String toString() {
        return super.toString() + "\nTipo: " + type;
    }
    
}
