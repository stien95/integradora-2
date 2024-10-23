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
        players = new Player[20];
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
    public String addPlayer(Player player) {
        String message = "";
        boolean added = false;
        for (int i = 0; i < players.length && !added; i++) {
            if (players[i] == null) {
                players[i] = player;
                added = true;
            }
        }
        if (added) {
            message = "Jugador agregado";
        } else {
            message = "No se pueden agregar más jugadores";
        }
        return message;
    }
    public Player searchPlayer(String id) {
        Player player = null;
        for (int i = 0; i < players.length && player == null; i++) {
            if (players[i] != null && players[i].getId().equals(id)) {
                player = players[i];
            }
        }
        return player;
    }
    
    
}
