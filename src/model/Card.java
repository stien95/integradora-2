package model;

import java.time.LocalTime;

public class Card {
    private ColorCard color;
    private Player player;
    private Referee referee;
    private LocalTime minute;

    public Card(int colorOption, Player player, Referee referee, String minute) {
        setColor(colorOption);
        this.player = player;
        this.referee = referee;
        setMinute(minute);
    }

    public ColorCard getColor() {
        return color;
    }

    public void setColor(int colorOption) {
        if (colorOption == 1) {
            this.color = ColorCard.YELLOW;
        } else {
            this.color = ColorCard.RED;
        }
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Referee getReferee() {
        return referee;
    }

    public void setReferee(Referee referee) {
        this.referee = referee;
    }

    public LocalTime getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = LocalTime.parse(minute);
    }

}
