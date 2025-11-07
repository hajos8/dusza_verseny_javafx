package main.dusza.render;

import java.util.ArrayList;

public class Enemy {
    public String enemyName;
    public String type;
    public String prize;
    public ArrayList<Card> enemyDeck;

    public String getEnemyName() {
        return enemyName;
    }
    public String getType() {
        return type;
    }
    public String getPrize() {
        return prize;
    }
    public ArrayList<Card> getEnemyDeck() {
        return enemyDeck;
    }

    public void setEnemyName(String name) {
        this.enemyName = name;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setPrize(String prize) {
        this.prize = prize;
    }
    public void setEnemyDeck(ArrayList<Card> deck) {
        this.enemyDeck = deck;
    }
}
