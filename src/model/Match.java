package model;

public class Match {
    private Team local;
    private Team visitor;
    private Referee[] referees;
    private Goal[] goals;
    private boolean played;
    private String date;
    private String time;
    private int[] yellowCards;
    private int[] redCards;

    public Match(Team local, Team visitor, String date, String time) {
        this.local = local;
        this.visitor = visitor;
        this.date = date;
        this.time = time;
        referees = new Referee[3];
        yellowCards = new int[2];
        redCards = new int[2];
    }

    public Team getLocal() {
        return local;
    }

    public void setLocal(Team local) {
        this.local = local;
    }

    public Team getVisitor() {
        return visitor;
    }

    public void setVisitor(Team visitor) {
        this.visitor = visitor;
    }

    public Referee[] getReferees() {
        return referees;
    }

    public void setReferees(Referee[] referees) {
        this.referees = referees;
    }

    // Se debe poder agregar los referees de un partido
    // Solo puede ser 1 central y dos asistentes, el país del arbitro no puede
    // coincidir con el país de los equipos
    public String assignReferee(Referee[] referees) {
        String message = "Árbitros asignados correctamente.";

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

    public Goal[] getGoals() {
        return goals;
    }

    public void setGoals(Goal[] goals) {
        this.goals = goals;
    }

    public String registerResult(Player[] scorers, Player[] assistants, int[] scorersMinutes, boolean[] ownGoals,
            int[] yellowCards, int[] redCards) {
        String message = "";
        if (scorers[0] == null) {
            message = "No hubo goles";
        } else {
            for (int i = 0; i < scorers.length; i++) {
                addGoal(scorers[i], assistants[i], scorersMinutes[i], ownGoals[i]);
            }
        }

        for (int i = 0; i < yellowCards.length; i++) {
            this.yellowCards[i] = yellowCards[i];
            this.redCards[i] = redCards[i];
        }
        this.played = true;
        return message;
    }

    public String addGoal(Player scorer, Player assistant, int minute, boolean ownGoal) {
        Goal goal = new Goal(scorer, assistant, minute, ownGoal);
        String message = "";
        boolean added = false;
        for (int i = 0; i < goals.length && !added; i++) {
            if (goals[i] == null) {
                goals[i] = goal;
                added = true;
            }
        }
        if (added) {
            message = "Gol agregado";
        } else {
            message = "No se pueden agregar más goles";
        }
        return message;
    }

    public boolean isPlayed() {
        return played;
    }

    public void setPlayed(boolean played) {
        this.played = played;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Local: " + local.getName() + "\nVisitante: " + visitor.getName() + "\nFecha: " + date.toString();
    }
}