package model;

import java.util.ArrayList;

public class Match {
    // Attributes
    private String date;
    private String time;
    private boolean played;
    // Associations
    private Team local;
    private Team visitor;
    private Referee[] referees;
    private ArrayList<Goal> goals;
    private ArrayList<Card> cards;

    /**
     * Constructs a Match object with the specified local team, visitor team, date,
     * and time.
     *
     * @param local   the local team participating in the match
     * @param visitor the visiting team participating in the match
     * @param date    the date of the match
     * @param time    the time of the match
     */
    public Match(Team local, Team visitor, String date, String time) {
        this.local = local;
        this.visitor = visitor;
        this.date = date;
        this.time = time;
        referees = new Referee[3];
    }

    /**
     * Gets the local team of the match.
     * <br/>
     * Post: The local team of the match has been returned.
     * 
     * @return the local team of the match
     */
    public Team getLocal() {
        return local;
    }

    /**
     * Sets the local team of the match.
     * <br/>
     * Pre: The team must exist.
     * <br/>
     * Post: The local team of the match has been set.
     * 
     * @param local the local team of the match
     */
    public void setLocal(Team local) {
        this.local = local;
    }

    /**
     * Gets the visitor team of the match.
     * <br/>
     * Post: The visitor team of the match has been returned.
     * 
     * @return the visitor team of the match
     */
    public Team getVisitor() {
        return visitor;
    }

    /**
     * Sets the visitor team of the match.
     * <br/>
     * Pre: The team must exist.
     * <br/>
     * Post: The visitor team of the match has been set.
     * 
     * @param visitor the visitor team of the match
     */
    public void setVisitor(Team visitor) {
        this.visitor = visitor;
    }

    /**
     * Gets the referees assigned to the match.
     * <br/>
     * Post: The referees assigned to the match have been returned.
     * 
     * @return an array of referees assigned to the match
     */
    public Referee[] getReferees() {
        return referees;
    }

    /**
     * Assigns referees to a match.
     * <br/>
     * Pre: The tournament must be initialized.
     * <br/>
     * Post: The referees have been assigned to the match.
     * 
     * @param referees the referees to assign to the match
     */
    public void setReferees(Referee[] referees) {
        this.referees = referees;
    }

    /**
     * Assigns referees to a match.
     * <br/>
     * Pre: The tournament must be initialized.
     * <br/>
     * Post: The referees have been assigned to the match.
     * 
     * @param referees The referees to assign to the match.
     * @return A message indicating whether the referees were assigned successfully.
     */
    public String assignReferee(Referee[] referees) {
        String message = "Arbitros asignados correctamente.";

        if (referees == null || referees.length != 3) {
            message = "Error: Debe haber exactamente 3 árbitros.";
        } else {
            boolean hasCentral = false;
            int assistantCount = 0;
            boolean stop = false;
            for (int i = 0; i < referees.length && !stop; i++) {
                Referee referee = referees[i];
                if (referee == null) {
                    message = "Error: Ningún árbitro puede ser nulo.";
                    stop = true;
                }

                if (referee.getCountry().equals(local.getCountry())
                        || referee.getCountry().equals(visitor.getCountry())) {
                    message = "Error: Un árbitro no puede ser del mismo país que los equipos en el partido.";
                    stop = true;
                }

                if (referee.getType().equals("central")) {
                    if (hasCentral) {
                        message = "Error: Solo puede haber un árbitro central.";
                        stop = true;
                    }
                    hasCentral = true;
                } else if (referee.getType().equals("asistente")) {
                    assistantCount++;
                } else {
                    message = "Error: Tipo de árbitro no válido.";
                    stop = true;
                }
            }

            if (!message.startsWith("Error") && (!hasCentral || assistantCount != 2)) {
                message = "Error: Debe haber un árbitro central y dos asistentes.";
            }
        }

        return message;
    }

    /**
     * Returns the goals of the match.
     * 
     * @return the goals of the match
     */
    public ArrayList<Goal> getGoals() {
        return goals;
    }

    /**
     * Sets the goals of the match.
     * 
     * @param goals the goals of the match
     */
    public void setGoals(ArrayList<Goal> goals) {
        this.goals = goals;
    }

    /**
     * Registers the result of a match by specifying the local and visitor teams,
     * goals, own goals, yellow cards, and red cards.
     * <br/>
     * Pre: The match must exist.
     * <br/>
     * Post: The result of the match has been registered.
     * 
     * @param scorers        The players who scored the goals.
     * @param assistants     The players who assisted the goals.
     * @param scorersMinutes The minutes of the goals.
     * @param ownGoals       Whether the goals were own goals or not.
     * @param yellowCards    The number of yellow cards of each team.
     * @param redCards       The number of red cards of each team.
     * @return A message indicating whether the result was registered successfully.
     */
    public String registerResult(Player[] scorers, Player[] assistants, String[] scorersMinutes, boolean[] ownGoals,
            Player[] receivedCardPlayers, int[] cardTypes, String[] cardTimes, Referee[] cardReferees) {
        String message = "";
        if (scorers[0] == null) {
            message = "No hubo goles";
        } else {
            for (int i = 0; i < scorers.length; i++) {
                addGoal(scorers[i], assistants[i], scorersMinutes[i], ownGoals[i]);
            }
        }

        for (int i = 0; i < receivedCardPlayers.length; i++) {
            Card card = new Card(cardTypes[i], receivedCardPlayers[i], cardReferees[i], cardTimes[i]);
            cards.add(card);
        }
        this.played = true;
        return message;
    }

    /**
     * Adds a goal to the match.
     * <br/>
     * Pre: The goal must not be null.
     * <br/>
     * Post: The goal has been added to the match.
     * 
     * @param scorer    The player who scored the goal.
     * @param assistant The player who assisted the goal.
     * @param minute    The minute of the goal.
     * @param ownGoal   Whether the goal was an own goal or not.
     * @return A message indicating whether the goal was added or not.
     */
    public String addGoal(Player scorer, Player assistant, String minute, boolean ownGoal) {
        Goal goal = new Goal(scorer, assistant, minute, ownGoal);
        goals.add(goal);
        return "Gol agregado";
    }

    /**
     * Returns whether the match has been played or not.
     * 
     * @return true if the match has been played, false otherwise
     */
    public boolean isPlayed() {
        return played;
    }

    /**
     * Sets whether the match has been played or not.
     * <br/>
     * Pre: The match must exist.
     * <br/>
     * Post: The match has been marked as played or not played.
     * 
     * @param played Whether the match has been played or not.
     */
    public void setPlayed(boolean played) {
        this.played = played;
    }

    /**
     * Returns the date of the match in the format "dd/mm/yyyy".
     * 
     * @return the date of the match
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date of the match.
     * <br/>
     * Pre: The date must be in the format "dd/mm/yyyy".
     * <br/>
     * Post: The date of the match has been updated.
     * 
     * @param date The date of the match in the format "dd/mm/yyyy".
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Returns the time of the match in the format "hh:mm".
     * 
     * @return the time of the match
     */
    public String getTime() {
        return time;
    }

    /**
     * Sets the time of the match in the format "hh:mm".
     * <br/>
     * Pre: The time must be in the format "hh:mm".
     * <br/>
     * Post: The time of the match has been updated.
     * 
     * @param time The time of the match in the format "hh:mm".
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Returns a string representation of the match, including the names of the
     * local and visitor teams, and the date of the match.
     * 
     * @return a string representation of the match
     */
    @Override
    public String toString() {
        return "Local: " + local.getName() + "\nVisitante: " + visitor.getName() + "\nFecha: " + date.toString();
    }
}