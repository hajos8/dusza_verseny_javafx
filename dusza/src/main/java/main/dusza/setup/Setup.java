package main.dusza.setup;

import main.dusza.fileHandling.BattleLogSaver;
import main.dusza.fileHandling.PlayerSaver;
import main.dusza.fileHandling.TxtParser;
import main.dusza.fileHandling.WorldSaver;
import main.dusza.gameElements.Card;
import main.dusza.gameElements.Dungeon;
import main.dusza.gameElements.LeaderCard;
import main.dusza.gameElements.Player;
import main.dusza.main.BattleRoundManager;

import java.util.ArrayList;

import static main.dusza.main.GameData.*;

public class Setup {

    public static boolean isRunningTest = false;
    public static ArrayList<String> testData;
    public static void generateWorld(String path){



        ArrayList<String> data = isRunningTest? testData : TxtParser.parseTxt(path);
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
                    String type = parts[1];
                    String dungeonName = parts[2];
                    String dungeDeckString = parts[3];
                    String dungeonLeaderCard = null;
                    String prize = null;

                    switch (type) {
                        case "egyszeru" -> prize = parts[4];
                        case "kis" -> {
                            dungeonLeaderCard = parts[4];
                            prize = parts[5];
                        }
                        case "nagy" -> {
                            dungeonLeaderCard = parts[4];
                            prize = "kartya";
                        }
                    }


                    ArrayList<Card> dungeonDeck = new ArrayList<Card>();

                    for(String i : dungeDeckString.split(",")){

                        for (Card card : worldCardsList) {
                            if (card.getName().equals(i)) {
                                dungeonDeck.add(card);
                                break;
                            }
                        }
                    }

                    if(dungeonLeaderCard != null){;
                        for (Card card : worldCardsList) {
                            if (card.getName().equals(dungeonLeaderCard)) {
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

                    Player currentPlayer = PlayersList.getFirst(); // TODO multiplayer

                    ArrayList<Card> playerDeck = currentPlayer.getDeck() == null ? new ArrayList<Card>() : currentPlayer.getDeck();
                    for (String cardName : cardNames) {
                        for (Card card : worldCardsList) {
                            if (card.getName().equals(cardName)) {
                                //deep copy
                                playerDeck.add(new Card(card.getName(), card.getDmg(), card.getHp(), card.getType()));
                                break;
                            }
                        }
                    }
                    currentPlayer.setDeck(playerDeck);
                }
                case "export vilag" -> {
                    WorldSaver.saveWorld(parts[1]);
                }
                case "export jatekos" -> {
                    PlayerSaver.savePlayerData(parts[1]);
                }
                case "harc" -> {
                    BattleRoundManager.battle(parts[1], null);
                    BattleLogSaver.saveBattleLog(parts[2]);
                }

            }

        }
    }
}
