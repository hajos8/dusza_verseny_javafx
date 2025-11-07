package main.dusza.render;

import java.util.ArrayList;

public class RenderFunctions {
    public static ArrayList<Card> wordCardsList = new ArrayList<Card>();
    public static ArrayList<Enemy> worldEnemiesList = new ArrayList<Enemy>();

    public static ArrayList<Player> PlayersList = new ArrayList<Player>();

    public static void GenerateWorld(ArrayList<String> data){
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
                            // Upgrade to leader and it's stats
                            card.setLeader(true, parts[3]);
                            card.setName(newName);
                        }
                    }

                }
                case "uj kazamata" -> {
                    Enemy enemy = new Enemy();
                    String enemyName = parts[1];
                    String type = parts[2];
                    String prize = parts[parts.length-1];

                    ArrayList<Card> enemyDeck = new ArrayList<Card>();
                    for(int i = 3; i < parts.length-2; i++){
                        String cardName = parts[i];

                        for (Card card : wordCardsList) {
                            if (card.getName().equals(cardName)) {
                                enemyDeck.add(card);
                            }
                        }
                    }

                    enemy.setEnemyName(enemyName);
                    enemy.setType(type);
                    enemy.setEnemyDeck(enemyDeck);
                    enemy.setPrize(prize);

                    worldEnemiesList.add(enemy);
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

                }
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

    public static ArrayList<Card> createWorldCards(ArrayList<String> data) {
        ArrayList<Card> list = new ArrayList<>();

        return list;

    }
}
