package main.dusza.fileHandling;

import main.dusza.gameElements.Card;
import main.dusza.gameElements.Dungeon;
import main.dusza.gameElements.LeaderCard;
import main.dusza.main.GameData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;



public class PlayerSaver {

    public static boolean isRunningTest = false;


    public static void savePlayerData(String filename){
        try{
            StringBuilder fileContent = new StringBuilder();

            for(Card card : GameData.PlayersList.getFirst().getCollection()){
                fileContent
                        .append("gyujtemeny;")
                        .append(card.getName())
                        .append(";")
                        .append(card.getDmg())
                        .append(";")
                        .append(card.getHp())
                        .append(";")
                        .append(card.getType())
                        .append("\n");
            }

            fileContent.append("\n");

            if(GameData.PlayersList.getFirst().getDeck() != null){
                for(Card card : GameData.PlayersList.getFirst().getDeck()){
                    fileContent
                            .append("pakli;")
                            .append(card.getName())
                            .append("\n");
                }
            }

            if(!isRunningTest){
                FileWriter fileWriter = new FileWriter(filename);
                fileWriter.write(fileContent.toString());
                fileWriter.close();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
