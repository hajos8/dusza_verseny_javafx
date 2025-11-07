package main.dusza.setup.elements;

import java.util.ArrayList;

public class Player {
    public String playerName;
    public ArrayList<Card> playerCollection;
    public ArrayList<Card> playerDeck;

    public String getPlayerName() {
        return playerName;
    }
    public ArrayList<Card> getPlayerDeck() {
        return playerDeck;
    }
    public ArrayList<Card> getPlayerCollection() {
        return playerCollection;
    }

    public void setPlayerName(String name) {
        this.playerName = name;
    }
    public void setPlayerDeck(ArrayList<Card> deck) {
        this.playerDeck = deck;
    }
    public void setPlayerCollection(ArrayList<Card> collection) {
        this.playerCollection = collection;
    }
}
