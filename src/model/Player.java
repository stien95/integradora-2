package model;

import java.util.ArrayList;

public class Player extends Person {
    // Attributes
    private int dorsal;
    // Enums
    private PlayerPosition position;
    // Associations
    private ArrayList<Goal> goalsAssisted;
    private ArrayList<Goal> goalsScored;

    /**
     * Constructs a new Player object with the specified details.
     *
     * @param id             The unique identifier for the player.
     * @param name           The name of the player.
     * @param country        The country of the player.
     * @param dorsal         The dorsal number of the player.
     * @param positionOption The position option of the player.
     */
    public Player(String id, String name, String country, int dorsal, int positionOption) {
        super(id, name, country);
        this.dorsal = dorsal;
        setPosition(positionOption);
    }

    /**
     * Returns the dorsal number of the player.
     * 
     * @return the dorsal number
     */
    public int getDorsal() {
        return dorsal;
    }

    /**
     * Sets the dorsal number for the player.
     *
     * @param dorsal the dorsal number to set
     */
    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    /**
     * Returns the position of the player.
     * 
     * @return the position of the player
     */
    public PlayerPosition getPosition() {
        return position;
    }

    /**
     * Sets the position of the player, given an option.
     * <ul>
     * <li>1. GOALKEEPER</li>
     * <li>2. DEFENDER</li>
     * <li>3. MIDFIELDER</li>
     * <li>4. FORWARD</li>
     * </ul>
     * 
     * @param position The position option of the player.
     */
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

    public ArrayList<Goal> getGoalsAssisted() {
        return goalsAssisted;
    }

    public ArrayList<Goal> getGoalScored() {
        return goalsScored;
    }

    public void addGoalAssisted(Goal goal) {
        goalsAssisted.add(goal);
    }

    public void addGoalScored(Goal goal) {
        goalsScored.add(goal);
    }

    /**
     * Returns a string representation of the Player object, including the dorsal
     * number and position.
     *
     * @return A string that represents the Player object, including the dorsal
     *         number and position.
     */
    @Override
    public String toString() {
        return super.toString() + "\nDorsal: " + dorsal + "\nPosición: " + position;
    }

}
