package model;
public class Match {
    private Team local;
    private Team visitor;
    private Referee[] referees;
    private int localGoals;
    private int visitorGoals;
    private boolean played;
    private String date;
    private String time;
    public Match(Team local, Team visitor, String date, String time) {
        this.local = local;
        this.visitor = visitor;
        this.date = date;
        this.time = time;
        referees = new Referee[3];
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
    public int getLocalGoals() {
        return localGoals;
    }
    public void setLocalGoals(int localGoals) {
        this.localGoals = localGoals;
    }
    public int getVisitorGoals() {
        return visitorGoals;
    }
    public void setVisitorGoals(int visitorGoals) {
        this.visitorGoals = visitorGoals;
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

    public String asignReferee(Referee referee) {
        // Debe haber 1 árbitro central y 2 asistentes, ninguno de los arbitros deben ser del mismo país de alguno de los equipos
        String message = "";
        // Verificar que el árbitro no sea del mismo país de alguno de los equipos
        if (!local.getCountry().equals(referee.getCountry()) && !visitor.getCountry().equals(referee.getCountry())) {
            boolean foundCentral = false;
            int countAssistants = 0;
            for (int i = 0; i < referees.length; i++) {
                if (referees[i] != null && referees[i].equalsType(1)) {
                    foundCentral = true;
                } else if (referees[i] != null && referees[i].equalsType(2)) {
                    countAssistants++;
                }
            }
            if (foundCentral && referee.equalsType(1)) {
                message = "Ya hay un árbitro central";
            } else if (countAssistants == 2 && referee.equalsType(2)) {
                message = "Ya hay dos árbitros asistentes";
            } else {
                boolean added = false;
                for (int i = 0; i < referees.length && !added; i++) {
                    if (referees[i] == null) {
                        referees[i] = referee;
                        added = true;
                    }
                }
                if (added) {
                    message = "Árbitro asignado";
                } else {
                    message = "No se pueden asignar más árbitros";
                }
            }
        } else {
            message = "El árbitro no puede ser del mismo país de alguno de los equipos";
        }
        return message;
    }
    @Override
    public String toString() {
        return "Local: " + local.getName() + "\nVisitante: " + visitor.getName() + "\nFecha: "+ date.toString();
    }
}