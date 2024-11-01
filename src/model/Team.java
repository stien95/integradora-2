package model;

public class Team {
    // Attributes
    private String name;
    private String country;
    private String coachName;
    // Associations
    private Player[] players;

    /**
     * Constructs a new team with the specified name, country, and coach name.
     * <br/>
     * 
     * @param name      the name of the team
     * @param country   the country of the team
     * @param coachName the coach name of the team
     */
    public Team(String name, String country, String coachName) {
        this.name = name;
        this.country = country;
        this.coachName = coachName;
    }

    /**
     * Gets the name of the team.
     * <br/>
     * Pre: None
     * <br/>
     * Post: The name of the team has been returned.
     * 
     * @return the name of the team
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the team.
     * <br/>
     * Pre: The name must not be null
     * <br/>
     * Post: The name of the team has been set.
     * 
     * @param name the name of the team
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the country of the team.
     * <br/>
     * Pre: None
     * <br/>
     * Post: The country of the team has been returned.
     * 
     * @return the country of the team
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the country of the team.
     * <br/>
     * Pre: None
     * Post: The country of the team has been set.
     * 
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Returns the coach name of the team.
     * <br/>
     * Pre: None
     * <br/>
     * Post: The coach name of the team is returned.
     * 
     * @return the coach name
     */
    public String getCoachName() {
        return coachName;
    }

    /**
     * Sets the coach name of the team.
     * <br/>
     * Pre: None
     * <br/>
     * Post: The coach name of the team has been set.
     * 
     * @param coachName the coach name of the team
     */
    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    /**
     * Gets the players of the team.
     * <br/>
     * Pre: The players of the team must have been set.
     * <br/>
     * Post: The players of the team are returned.
     * 
     * @return The array of players of the team.
     */
    public Player[] getPlayers() {
        return players;
    }

    /**
     * Sets the players of the team.
     * <br/>
     * Pre: The array of players must not be null.
     * <br/>
     * Post: The players of the team have been set.
     * 
     * @param players The array of players to set.
     */
    public void setPlayers(Player[] players) {
        this.players = players;
    }

    /**
     * Adds a player to the team.
     * <br/>
     * Pre: The player must not be null.
     * <br/>
     * Post: The player has been added to the team.
     * 
     * @param player The player to add.
     * @return A message indicating whether the player was added or not.
     */
    public String addPlayer(Player player) {
        String message = "";
        boolean added = false;
        for (int i = 0; i < players.length && !added; i++) {
            if (players[i] == null) {
                players[i] = player;
                added = true;
                message = "Jugador agregado";
            }
        }
        if (!added) {
            message = "No se pueden agregar más jugadores";
        }
        return message;
    }

    /**
     * Returns a string representation of the team, including name, country, and
     * coach name.
     * 
     * @return a string representation of the team
     */
    @Override
    public String toString() {
        return "    Nombre: " + name + "\n    País: " + country + "\n    Entrenador: " + coachName;
    }

}
