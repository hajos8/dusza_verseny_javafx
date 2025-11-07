package main.dusza.fileHandling;

import main.dusza.gameElements.Card;
import main.dusza.gameElements.Dungeon;
import main.dusza.gameElements.LeaderCard;
import main.dusza.main.GameData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WorldSaver {
    public static void saveWorld(String filename){
        try{
            ArrayList<Card> cards = new ArrayList<Card>();
            ArrayList<LeaderCard> leaders = new ArrayList<LeaderCard>();

            StringBuilder fileContent = new StringBuilder();

            for(Card card : GameData.worldCardsList){
                if(card instanceof LeaderCard){
                    leaders.add((LeaderCard) card);
                }
                else{
                    cards.add(card);
                }
            }
            for(Card card : cards){
                fileContent
                        .append("kartya;")
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

            for(LeaderCard leaderCard : leaders){
                fileContent
                        .append("vezer;")
                        .append(leaderCard.getName())
                        .append(";")
                        .append(leaderCard.getDmg())
                        .append(";")
                        .append(leaderCard.getHp())
                        .append(";")
                        .append(leaderCard.getType())
                        .append("\n");
            }

            fileContent.append("\n");

            for(Dungeon dungeon : GameData.worldDungeonList){
                fileContent
                        .append("kazamata;")
                        .append(dungeon.getType())
                        .append(";")
                        .append(dungeon.getName())
                        .append(";");

                for(Card card : dungeon.getDungeonDeck()){
                    fileContent
                            .append(card.getName())
                            .append(";");
                }

                if(dungeon.getType().equals("nagy")){
                    fileContent
                            .append("\n");
                }
                else{
                    fileContent
                            .append(dungeon.getPrize())
                            .append("\n");
                }
            }

            FileWriter fileWriter = new FileWriter(new File(filename));
            fileWriter.write(fileContent.toString());
            fileWriter.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
