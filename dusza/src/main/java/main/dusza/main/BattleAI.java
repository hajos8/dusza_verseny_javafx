package main.dusza.main;

import main.dusza.gameElements.Card;

import java.util.Random;

public class BattleAI {
    public static Random rand = new Random();

    public static void generateTurn(String player){
        //TODO implement AI based on difficutly

        switch(player){
            case "player" -> { //test modnal player is AI
                if(!Battle.playerTable.isEmpty()){
                    //ha nem ures a tabla tamad
                    int randomAttackerIndex = rand.nextInt(Battle.playerTable.size());
                    Card attacker = Battle.playerTable.get(randomAttackerIndex);


                    int randomDefenderIndex = rand.nextInt(Battle.enemyTable.size());
                    Card defender = Battle.enemyTable.get(randomDefenderIndex);
                    BattleHandler.attack("player", attacker, defender);
                }
                else{
                    //ha ures a tabla kijatszik
                    int randomIndex = rand.nextInt(Battle.playerHand.size());
                    Card cardToPlay = Battle.playerHand.get(randomIndex);
                    BattleHandler.playCard("player", cardToPlay);
                }

            }
            case "kazamata" ->{
                if(!Battle.enemyTable.isEmpty()){
                    //ha nem ures a tabla tamad
                    int randomAttackerIndex = rand.nextInt(Battle.enemyTable.size());
                    Card attacker = Battle.enemyTable.get(randomAttackerIndex);

                    int randomDefenderIndex = rand.nextInt(Battle.playerTable.size());
                    Card defender = Battle.playerTable.get(randomDefenderIndex);
                    BattleHandler.attack("kazamata", attacker, defender);
                }
                else{
                    //ha ures a tabla kijatszik
                    int randomIndex = rand.nextInt(Battle.enemyHand.size());
                    Card cardToPlay = Battle.enemyHand.get(randomIndex);
                    BattleHandler.playCard("kazamata", cardToPlay);
                }
            }
        }
    }
}
