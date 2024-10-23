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

}