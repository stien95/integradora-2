package model;

public class Referee extends Person {
    // Enums
    private RefereeType type;

    /**
     * Constructs a new Referee object with the specified id, name, country, and
     * type option.
     *
     * @param id         the unique identifier for the referee
     * @param name       the name of the referee
     * @param country    the country of the referee
     * @param typeOption the type option for the referee
     */
    public Referee(String id, String name, String country, int typeOption) {
        super(id, name, country);
        setType(typeOption);
    }

    /**
     * Returns the type of referee.
     *
     * @return the type of referee
     */
    public RefereeType getType() {
        return type;
    }

    /**
     * Sets the type of referee based on the type option.
     * <p>
     * The type option should be 1 for a central referee, or 2 for an assistant
     * referee.
     * <p>
     * 
     * @param typeOption the type option for the referee
     */
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

    /**
     * Compares the referee's type with the specified type option.
     *
     * @param typeOption an integer representing the type to compare:
     *                   1 for CENTRAL, 2 for ASSISTANT.
     * @return true if the referee's type matches the specified type option, false
     *         otherwise.
     */
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

    /**
     * Returns a string representation of the Referee object, including the type of
     * referee.
     *
     * @return A string that represents the Referee object, including the type of
     *         referee.
     */
    @Override
    public String toString() {
        return super.toString() + "\nTipo de árbitro: " + type;
    }
}
