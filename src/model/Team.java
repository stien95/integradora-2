package model;

public class Team {
    private String name;
    private String country;
    private String coachName;
    private Player[] players;

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

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

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

    @Override
    public String toString() {
        return "    Nombre: " + name + "\n    País: " + country + "\n    Entrenador: " + coachName;
    }

}
