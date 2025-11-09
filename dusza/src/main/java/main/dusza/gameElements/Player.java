package main.dusza.gameElements;

import java.util.ArrayList;

public class Player {
    public String name;
    public ArrayList<Card> collection;
    public ArrayList<Card> deck;

    public String getName() {
        return name;
    }
    public ArrayList<Card> getCollection() {
        return collection;
    }
    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setCollection(ArrayList<Card> collection) {
        this.collection = collection;
    }
    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck != null ? deck : new ArrayList<>();
    }
}
