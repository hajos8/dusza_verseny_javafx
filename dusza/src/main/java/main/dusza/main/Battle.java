package main.dusza.main;

import main.dusza.gameElements.Card;

import java.util.ArrayList;

import static main.dusza.main.BattleAI.*;

public class Battle {

    public static boolean isRunningTest=false;

    public static boolean testMode = true;

    public static ArrayList<Card> playerHand;
    public static ArrayList<Card> enemyHand;

    public static ArrayList<Card> playerTable;
    public static ArrayList<Card> enemyTable;

    public static void main(){
        BattleHandler.fillUpBattleHands("", "Teszt1a Kazamata");

        if(!isRunningTest) {
            do {
                generateTurn("kazamata");
                if (testMode) generateTurn("player");
            }
            while (
                    !(playerHand.isEmpty() && playerTable.isEmpty()) //check if player have more cards
                            &&
                            !(enemyHand.isEmpty() && enemyTable.isEmpty()) //check if enemy have more cards
            );
        }
    }
}
