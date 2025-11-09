package main.dusza.fileHandling;

import main.dusza.Launcher;
import main.dusza.gameElements.Card;
import main.dusza.gameElements.Dungeon;
import main.dusza.gameElements.LeaderCard;
import main.dusza.main.GameData;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WorldSaver {

    public static boolean isRunningTest = false;

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

                ArrayList<LeaderCard> leaderCards = new ArrayList<>();
                ArrayList<Card> NormalCards = new ArrayList<>();

                for(Card card : dungeon.getDungeonDeck()){
                    if(card instanceof LeaderCard){
                        leaderCards.add((LeaderCard) card);
                    }
                    else{
                        NormalCards.add(card);
                    }
                }

                for(int i = 0; i < NormalCards.size(); i++){
                    if(i + 1 == NormalCards.size()){
                        fileContent
                                .append(NormalCards.get(i).getName());
                    }
                    else{
                        fileContent
                                .append(NormalCards.get(i).getName())
                                .append(",");
                    }
                }

                fileContent.append(";");

                for(int i = 0; i < leaderCards.size(); i++){
                    if(i + 1 == leaderCards.size()){
                        fileContent
                                .append(leaderCards.get(i).getName());
                    }
                    else{
                        fileContent
                                .append(leaderCards.get(i).getName())
                                .append(",");
                    }
                }

                if(dungeon.getType().equals("nagy")){
                    fileContent
                            .append("\n");
                }
                else{
                    fileContent
                            .append(";")
                            .append(dungeon.getPrize())
                            .append("\n");
                }
            }

            if(!isRunningTest) {
                FileWriter fileWriter = new FileWriter(Launcher.defaultOutputPath + filename);
                fileWriter.write(fileContent.toString());
                fileWriter.close();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
