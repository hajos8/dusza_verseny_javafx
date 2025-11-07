package main.dusza.render;

import java.util.ArrayList;

public class Player {
    public ArrayList<Card> playerCollection;
    public ArrayList<Card> playerDeck;

    public ArrayList<Card> getPlayerDeck() {
        return playerDeck;
    }
    public ArrayList<Card> getPlayerCollection() {
        return playerCollection;
    }

    public void setPlayerDeck(ArrayList<Card> deck) {
        this.playerDeck = deck;
    }
    public void setPlayerCollection(ArrayList<Card> collection) {
        this.playerCollection = collection;
    }
}
