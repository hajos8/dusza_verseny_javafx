package main.dusza.setup.elements;

import java.util.ArrayList;

public class Dungeon {
    public String DungeonName;
    public String type;
    public String prize;
    public ArrayList<Card> DungeonDeck;

    public String getDungeonName() {
        return DungeonName;
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

    public void setDungeonName(String name) {
        this.DungeonName = name;
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
}
