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
    
    @Override
    public String toString() {
        return "Local: " + local.getName() + "\nVisitante: " + visitor.getName() + "\nFecha: "+ date.toString();
    }
}