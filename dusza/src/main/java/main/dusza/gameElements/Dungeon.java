package main.dusza.gameElements;

import java.util.ArrayList;

public class Dungeon {
    public String name;
    public String type;
    public String prize;
    public ArrayList<Card> DungeonDeck;

    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    public String getPrize() {
        return prize;
    }
    public ArrayList<Card> getDungeonDeck() {
        return DungeonDeck;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setPrize(String prize) {
        this.prize = prize;
    }
    public void setDungeonDeck(ArrayList<Card> deck) {
        this.DungeonDeck = deck;
    }

    public Dungeon(String name, String type, String prize, ArrayList<Card> dungeonDeck) {
        this.name = name;
        this.type = type;
        this.prize = prize;
        this.DungeonDeck = dungeonDeck;
    }
}
