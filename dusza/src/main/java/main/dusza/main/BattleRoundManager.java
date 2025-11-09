package main.dusza.main;

import javafx.application.Platform;
import main.dusza.gameElements.Card;
import main.dusza.gameElements.Dungeon;
import main.dusza.render.DungeonBattle;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;

import static main.dusza.main.BattleAI.*;

public class BattleRoundManager {
    public static boolean testMode = false;

    public static ArrayList<Card> playerHand = new ArrayList<Card>();
    public static ArrayList<Card> enemyHand = new ArrayList<Card>();

    public static ArrayList<Card> playerTable = new ArrayList<Card>();
    public static ArrayList<Card> enemyTable = new ArrayList<Card>();

    public static StringBuilder logBuilder = new StringBuilder();

    public static void battle(String dungeonName, DungeonBattle ui){

        logBuilder
                .append("harc kezdodik")
                .append(";")
                .append(dungeonName)
                .append("\n");

        BattleHandler.fillUpBattleHands(dungeonName);

        logBuilder.append("\n");

        int roundCounter = 1;

        do{
            logBuilder
                    .append(roundCounter)
                    .append(".kor")
                    .append(";")
                    .append("kazamata")
                    .append(";");

            generateTurn("kazamata");

            if (!testMode) {
                Platform.runLater(() -> ui.updateTable("kazamata"));
            }

            logBuilder
                    .append(roundCounter)
                    .append(".kor")
                    .append(";")
                    .append("jatekos")
                    .append(";");

            if(testMode) generateTurn("player");
            else{
                Card chosen = ui.awaitPlayerChoiceFromHand(playerHand);
                if (chosen != null) {
                    lastPlayedPlayerCard = chosen;
                    playerHand.remove(chosen);
                    playerTable.add(chosen);
                    Platform.runLater(() -> ui.updateTable("jatekos"));
                }
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
                    .append("jatekos vesztett")
                    .append("\n");
        }
        else{
            logBuilder
                    .append("jatekos nyert")
                    .append(";");

            for(Dungeon dungeon : GameData.worldDungeonList){
                if(dungeon.getName().equals(dungeonName)){
                    logBuilder
                            .append(Objects.equals(dungeon.getPrize(), "kartya") ? "" : dungeon.getPrize() + ";")
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
                                        if(card instanceof main.dusza.gameElements.LeaderCard){
                                            continue;
                                        }
                                        GameData.PlayersList.getFirst().getCollection().add(card);
                                        break;
                                    }
                                }
                            }
                            else{
                                Card chosen = ui.awaitPlayerChoiceFromHand(playerHand);
                                if (chosen != null) {
                                    lastPlayedPlayerCard = chosen;
                                    playerHand.remove(chosen);
                                    playerTable.add(chosen);
                                    Platform.runLater(() -> ui.updateTable("jatekos"));
                                }
                            }

                        }
                    }

                    break;
                }
            }

        }
    }
}
