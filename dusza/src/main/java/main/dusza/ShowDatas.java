package main.dusza;

import main.dusza.main.GameData;
import main.dusza.gameElements.Card;
import main.dusza.gameElements.Dungeon;
import main.dusza.gameElements.LeaderCard;
import main.dusza.gameElements.Player;

import java.util.ArrayList;

/*
    ONLY FOR TESTING PURPOSES

    public static ArrayList<Card> wordCardsList = new ArrayList<Card>();
    public static ArrayList<Dungeon> worldDungeonList = new ArrayList<Dungeon>();

    public static ArrayList<Player> PlayersList = new ArrayList<Player>();

 */
public class ShowDatas {

    public static void showData() {
        //world cards
        System.out.println("World Cards:");

        printCards(GameData.worldCardsList);

        System.out.println("--------------------------");

        System.out.println("World Dungeons:");

        for(Dungeon i : GameData.worldDungeonList) {
            System.out.println(
                    "Dungeon Name: " + i.getName() +
                            ", Dungeon type: " + i.getType() +
                            ", Dungeon reward: " + i.getPrize() +
                            ", Dungeon Deck: "
            );
            printCards(i.getDungeonDeck());
            System.out.println();
        }

        System.out.println("-------------------------");

        Player firstPlayer = GameData.PlayersList.get(0);

        System.out.println("First Player Data:");
        System.out.println(
                "Player Name: " + firstPlayer.getName()
        );
        System.out.println("First Player Collection:");
        printCards(firstPlayer.getCollection());

        System.out.println();

        System.out.println("First Player Deck:");
        if(firstPlayer.getDeck() != null) {
            printCards(firstPlayer.getDeck());
        }
        else {
            System.out.println("Deck is null");
        }

    }

    public static void printCards(ArrayList<Card> cards) {
        for(Card i : cards) {
            if(i.getClass().equals(LeaderCard.class)) {
                System.out.println(
                        "Card Name: " + i.getName() +
                                ", Card dmg: " + i.getDmg() +
                                ", Card hp: " + i.getHp() +
                                ", Card type: " + i.getType() +
                                ", Power up: " + ((LeaderCard) i).getPowerUp()

                );
            }
            else{
                System.out.println(
                        "Card Name: " + i.getName() +
                                ", Card dmg: " + i.getDmg() +
                                ", Card hp: " + i.getHp() +
                                ", Card type: " + i.getType()
                );
            }
        }
    }
}
