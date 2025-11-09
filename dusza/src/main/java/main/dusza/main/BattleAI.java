package main.dusza.main;

import main.dusza.gameElements.Card;

public class BattleAI {
    public static Card lastPlayedPlayerCard;

    public static void generateTurn(String player){
        //TODO implement AI based on difficutly

        switch(player){
            case "player" -> { //test modnal player is AI
                if(!BattleRoundManager.playerTable.isEmpty()){
                    //ha nem ures a tabla tamad
                    Card attacker = BattleRoundManager.playerTable.getFirst();

                    Card defender = BattleRoundManager.enemyTable.getFirst();
                    BattleHandler.attack("jatekos", attacker, defender);

                    lastPlayedPlayerCard = BattleRoundManager.playerTable.getFirst();
                }
                else{
                    //ha ures a tabla kijatszik
                    Card cardToPlay = BattleRoundManager.playerHand.getFirst();
                    BattleHandler.playCard("jatekos", cardToPlay);

                    lastPlayedPlayerCard = cardToPlay;
                }

            }
            case "kazamata" ->{
                if(!BattleRoundManager.enemyTable.isEmpty()){
                    //ha nem ures a tabla tamad
                    Card attacker = BattleRoundManager.enemyTable.getFirst();

                    Card defender = BattleRoundManager.playerTable.getFirst();
                    BattleHandler.attack("kazamata", attacker, defender);
                }
                else{
                    //ha ures a tabla kijatszik
                    Card cardToPlay = BattleRoundManager.enemyHand.getFirst();
                    BattleHandler.playCard("kazamata", cardToPlay);
                }
            }
        }
    }
}
