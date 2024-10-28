package model;

public class Goal {
  // Attributes
  private int minute;
  private boolean ownGoal;
  // Associations
  private Player scorer;
  private Player assistant;

  /**
   * Constructs a Goal object with the specified scorer, assistant, minute, and
   * ownGoal flag.
   *
   * @param scorer    the player who scored the goal
   * @param assistant the player who assisted the goal
   * @param minute    the minute in which the goal was scored
   * @param ownGoal   a boolean indicating whether the goal was an own goal
   */
  public Goal(Player scorer, Player assistant, int minute, boolean ownGoal) {
    this.scorer = scorer;
    this.assistant = assistant;
    this.minute = minute;
    this.ownGoal = ownGoal;
  }

  /**
   * Returns the player who scored the goal.
   * 
   * @return the player who scored the goal
   */
  public Player getScorer() {
    return scorer;
  }

  /**
   * Sets the player who scored the goal.
   * 
   * @param scorer the player who scored the goal
   */
  public void setScorer(Player scorer) {
    this.scorer = scorer;
  }

  /**
   * Returns the player who assisted the goal.
   * 
   * @return the player who assisted the goal
   */
  public Player getAssistant() {
    return assistant;
  }

  /**
   * Sets the player who assisted the goal.
   *
   * @param assistant the player who assisted the goal
   */
  public void setAssistant(Player assistant) {
    this.assistant = assistant;
  }

  /**
   * Returns the minute in which the goal was scored.
   * 
   * @return the minute in which the goal was scored
   */
  public int getMinute() {
    return minute;
  }

  /**
   * Sets the minute in which the goal was scored.
   * 
   * @param minute the new minute in which the goal was scored
   */
  public void setMinute(int minute) {
    this.minute = minute;

  }

  /**
   * Returns whether the goal was an own goal or not.
   * 
   * @return true if the goal was an own goal, false otherwise
   */
  public boolean isOwnGoal() {
    return ownGoal;
  }

  /**
   * Sets whether the goal was an own goal or not.
   * 
   * @param ownGoal true if the goal was an own goal, false otherwise
   */
  public void setOwnGoal(boolean ownGoal) {
    this.ownGoal = ownGoal;
  }

  /**
   * Returns a string representation of the goal, including the name of the player
   * who scored the goal, the name of the player who assisted the goal, the minute
   * in which the goal was scored, and whether the goal was an own goal or not.
   * 
   * @return a string representation of the goal
   */
  @Override
  public String toString() {
    return "Gol hecho por: " + scorer.getName() + "\nAsistidor: " + assistant.getName() + "\nMinuto: " + minute
        + "\nGol en contra: " + ownGoal;
  }

}
