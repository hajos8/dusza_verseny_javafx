package main.dusza.main;

import main.dusza.gameElements.Card;
import main.dusza.gameElements.Dungeon;

import java.util.ArrayList;

import static main.dusza.main.BattleAI.*;

public class BattleRoundManager {
    public static boolean testMode = false;

    public static ArrayList<Card> playerHand = new ArrayList<Card>();
    public static ArrayList<Card> enemyHand = new ArrayList<Card>();

    public static ArrayList<Card> playerTable = new ArrayList<Card>();
    public static ArrayList<Card> enemyTable = new ArrayList<Card>();

    public static StringBuilder logBuilder = new StringBuilder();

    public static void battle(String dungeonName){
        logBuilder
                .append("harc kezdodik")
                .append(";")
                .append(dungeonName)
                .append("\n");

        BattleHandler.fillUpBattleHands(dungeonName);

        logBuilder.append("\n");

        int roundCounter = 1;

        do{
            //TODO implement double attack after defeating one card if there are more cards on the table
            logBuilder
                    .append(roundCounter)
                    .append(".kor")
                    .append(";")
                    .append("kazamata")
                    .append(";");

            generateTurn("kazamata");

            logBuilder
                    .append(roundCounter)
                    .append(".kor")
                    .append(";")
                    .append("jatekos")
                    .append(";");

            if(testMode) generateTurn("player");
            else{
                //TODO implement player turn
            }
            logBuilder.append("\n");
            roundCounter++;
        }
        while(
                !(playerHand.isEmpty() && playerTable.isEmpty()) //check if player have more cards
                &&
                !(enemyHand.isEmpty() && enemyTable.isEmpty()) //check if enemy have more cards
        );

        if(playerHand.isEmpty() && playerTable.isEmpty()){
            logBuilder
                    .append("kazamata nyert")
                    .append(";")
                    .append("\n");
        }
        else{
            logBuilder
                    .append("jatekos nyert")
                    .append(";");

            for(Dungeon dungeon : GameData.worldDungeonList){
                if(dungeon.getName().equals(dungeonName)){
                    logBuilder
                            .append(dungeon.getPrize())
                            .append(";")
                            .append(lastPlayedPlayerCard.getName());

                    //TODO prize handling

                    switch(dungeon.getPrize()){
                        case "eletero" -> {
                            for(Card card : GameData.PlayersList.getFirst().getDeck()){
                                if(card.getName().equals(lastPlayedPlayerCard.getName())) {
                                    card.setHp(card.getHp() + 2);
                                    break;
                                }
                            }
                        }
                        case "sebzes" -> {
                            for(Card card : GameData.PlayersList.getFirst().getDeck()){
                                if(card.getName().equals(lastPlayedPlayerCard.getName())) {
                                    card.setDmg(card.getDmg() + 1);
                                    break;
                                }
                            }
                        }
                        case "kartya" -> {
                            if(testMode){
                                for(Card card : GameData.worldCardsList){
                                    if(!GameData.PlayersList.getFirst().getCollection().contains(card)) {
                                        GameData.PlayersList.getFirst().getCollection().add(card);
                                        break;
                                    }
                                }
                            }
                            else{
                                //TODO player chooses a card
                            }

                        }
                    }

                    break;
                }
            }

        }
    }
}
