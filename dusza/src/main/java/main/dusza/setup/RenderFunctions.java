package main.dusza.setup;

import main.dusza.fileHandling.TxtParser;
import main.dusza.setup.elements.Card;
import main.dusza.setup.elements.Dungeon;
import main.dusza.setup.elements.LeaderCard;
import main.dusza.setup.elements.Player;

import java.util.ArrayList;

import static main.dusza.main.GameData.*;

public class RenderFunctions {
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
                    wordCardsList.add(card);
                }
                case "uj vezer" ->{
                    String newName = parts[1];
                    String oldName = parts[2];

                    for (Card card : wordCardsList) {
                        if (card.getName().equals(oldName)) {
                            LeaderCard leaderCard = new LeaderCard(card.getName(), card.getDmg(), card.getHp(), card.getType(), newName);
                            wordCardsList.add(leaderCard);
                            break;
                        }
                    }

                }
                case "uj kazamata" -> {
                    Dungeon dungeon = new Dungeon();
                    String dungeonName = parts[1];
                    String type = parts[2];
                    String prize = parts[parts.length-1];

                    ArrayList<Card> dungeonDeck = new ArrayList<Card>();
                    for(int i = 3; i < parts.length-2; i++){
                        String cardName = parts[i];

                        for (Card card : wordCardsList) {
                            if (card.getName().equals(cardName)) {
                                dungeonDeck.add(card);
                            }
                        }
                    }

                    dungeon.setDungeonName(dungeonName);
                    dungeon.setType(type);
                    dungeon.setPrize(prize);
                    dungeon.setDungeonDeck(dungeonDeck);

                    worldEnemiesList.add(dungeon);
                }

                case "uj jatekos" -> {
                    Player player = new Player();
                    PlayersList.add(player);
                    //TODO multiplayer
                }
                case "felvetel gyujtemenybe" -> {
                    String cardName = parts[1];
                    Player currentPlayer = PlayersList.getLast(); // TODO multiplayer

                    for (Card card : wordCardsList) {
                        if (card.getName().equals(cardName)) {
                            ArrayList<Card> playerCollection = currentPlayer.getPlayerCollection();
                            playerCollection.add(card);
                            currentPlayer.setPlayerCollection(playerCollection);
                            break;
                        }
                    }

                } //innentol vannak a csak!!! test funkciok
                case "uj pakli" -> {
                    String[] cardNames = parts[1].split(",");

                    Player currentPlayer = PlayersList.getLast(); // TODO multiplayer

                    ArrayList<Card> playerDeck = currentPlayer.getPlayerDeck();
                    for (String cardName : cardNames) {
                        for (Card card : wordCardsList) {
                            if (card.getName().equals(cardName)) {
                                playerDeck.add(card);
                                break;
                            }
                        }
                    }
                    currentPlayer.setPlayerDeck(playerDeck);
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
