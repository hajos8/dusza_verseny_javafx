package main.dusza.main;

import main.dusza.gameElements.Card;

import java.util.Random;

public class BattleAI {

    public static boolean isRunningTest = false;

    public static Random rand = new Random();

    public static Card lastPlayedPlayerCard;

    public static void generateTurn(String player){
        //TODO implement AI based on difficutly

        switch(player){
            case "player" -> { //test modnal player is AI

                if (!isRunningTest) {
                    if (!BattleRoundManager.playerTable.isEmpty()) {
                        //ha nem ures a tabla tamad
                        int randomAttackerIndex = rand.nextInt(BattleRoundManager.playerTable.size());
                        Card attacker = BattleRoundManager.playerTable.get(randomAttackerIndex);

                        int randomDefenderIndex = rand.nextInt(BattleRoundManager.enemyTable.size());
                        Card defender = BattleRoundManager.enemyTable.get(randomDefenderIndex);
                        BattleHandler.attack("jatekos", attacker, defender);

                        lastPlayedPlayerCard = BattleRoundManager.playerTable.get(randomAttackerIndex);
                    } else {
                        //ha ures a tabla kijatszik
                        int randomIndex = rand.nextInt(BattleRoundManager.playerHand.size());
                        Card cardToPlay = BattleRoundManager.playerHand.get(randomIndex);
                        BattleHandler.playCard("jatekos", cardToPlay);

                        lastPlayedPlayerCard = cardToPlay;
                    }

                }
            }
            case "kazamata" -> {


                if (!isRunningTest) {
                    if (!BattleRoundManager.enemyTable.isEmpty()) {
                        //ha nem ures a tabla tamad
                        int randomAttackerIndex = rand.nextInt(BattleRoundManager.enemyTable.size());
                        Card attacker = BattleRoundManager.enemyTable.get(randomAttackerIndex);

                        int randomDefenderIndex = rand.nextInt(BattleRoundManager.playerTable.size());
                        Card defender = BattleRoundManager.playerTable.get(randomDefenderIndex);
                        BattleHandler.attack("kazamata", attacker, defender);
                    } else {
                        //ha ures a tabla kijatszik
                        int randomIndex = rand.nextInt(BattleRoundManager.enemyHand.size());
                        Card cardToPlay = BattleRoundManager.enemyHand.get(randomIndex);
                        BattleHandler.playCard("kazamata", cardToPlay);
                    }
                }
            }
        }
    }
}
