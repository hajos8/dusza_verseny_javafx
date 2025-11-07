package main.dusza.setup;

import main.dusza.fileHandling.TxtParser;
import main.dusza.gameElements.Card;
import main.dusza.gameElements.Dungeon;
import main.dusza.gameElements.LeaderCard;
import main.dusza.gameElements.Player;

import java.util.ArrayList;

import static main.dusza.main.GameData.*;

public class Setup {
    public static void generateWorld(String path){
        ArrayList<String> data = TxtParser.parseTxt(path);
        for (String line : data) {
            String[] parts = line.split(";");

            switch(parts[0]){
                case "uj kartya" ->{
                    String name = parts[1];
                    int dmg = Integer.parseInt(parts[2]);
                    int hp = Integer.parseInt(parts[3]);
                    String type = parts[4];

                    Card card = new Card(name, dmg, hp, type);
                    worldCardsList.add(card);
                }
                case "uj vezer" ->{
                    String newName = parts[1];
                    String oldName = parts[2];
                    String powerUp = parts[3];

                    for (Card card : worldCardsList) {
                        if (card.getName().equals(oldName)) {
                            LeaderCard leaderCard = new LeaderCard(card.getName(), card.getDmg(), card.getHp(), card.getType(), newName, powerUp);
                            worldCardsList.add(leaderCard);
                            break;
                        }
                    }

                }
                case "uj kazamata" -> {
                    String dungeonName = parts[1];
                    String type = parts[2];
                    String dungeDeckString = parts[3];
                    String prize = parts[4];

                    ArrayList<Card> dungeonDeck = new ArrayList<Card>();

                    for(String i : dungeDeckString.split(",")){

                        for (Card card : worldCardsList) {
                            if (card.getName().equals(i)) {
                                dungeonDeck.add(card);
                                break;
                            }
                        }
                    }


                    Dungeon dungeon = new Dungeon(
                            dungeonName,
                            type,
                            prize,
                            dungeonDeck
                    );

                    worldDungeonList.add(dungeon);
                }

                case "uj jatekos" -> {
                    Player player = new Player();
                    PlayersList.add(player);
                    //TODO multiplayer
                }
                case "felvetel gyujtemenybe" -> {
                    String cardName = parts[1];
                    Player currentPlayer = PlayersList.getLast(); // TODO multiplayer

                    for (Card card : worldCardsList) {
                        if (card.getName().equals(cardName)) {
                            ArrayList<Card> playerCollection = currentPlayer.getCollection() == null ? new ArrayList<Card>() : currentPlayer.getCollection();
                            playerCollection.add(card);
                            currentPlayer.setCollection(playerCollection);
                            break;
                        }
                    }

                } //innentol vannak a csak!!! test funkciok
                case "uj pakli" -> {
                    String[] cardNames = parts[1].split(",");

                    Player currentPlayer = PlayersList.getLast(); // TODO multiplayer

                    ArrayList<Card> playerDeck = currentPlayer.getDeck();
                    for (String cardName : cardNames) {
                        for (Card card : worldCardsList) {
                            if (card.getName().equals(cardName)) {
                                playerDeck.add(card);
                                break;
                            }
                        }
                    }
                    currentPlayer.setDeck(playerDeck);
                }
                case "export vilag" -> {
                    // TODO
                }
                case "export jatekos" -> {
                    // TODO
                }
                case "harc" -> {
                    // TODO
                }

            }

        }
    }
}
