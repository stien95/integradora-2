package model;

public class Goal {
  private Player scorer;
  private Player assistant;
  private int minute;
  private boolean ownGoal;

  public Goal(Player scorer, Player assistant, int minute, boolean ownGoal) {
    this.scorer = scorer;
    this.assistant = assistant;
    this.minute = minute;
    this.ownGoal = ownGoal;
  }

  public Player getScorer() {
    return scorer;
  }

  public void setScorer(Player scorer) {
    this.scorer = scorer;
  }

  public Player getAssistant() {
    return assistant;
  }

  public void setAssistant(Player assistant) {
    this.assistant = assistant;
  }

  public int getMinute() {
    return minute;
  }

  public void setMinute(int minute) {
    this.minute = minute;
  }

  public boolean isOwnGoal() {
    return ownGoal;
  }

  public void setOwnGoal(boolean ownGoal) {
    this.ownGoal = ownGoal;
  }
  
  @Override
  public String toString() {
    return "Gol hecho por: " + scorer.getName() + "\nAsistidor: "+ assistant.getName() + "\nMinuto: " + minute + "\nGol en contra: " + ownGoal;
  }
  
}
