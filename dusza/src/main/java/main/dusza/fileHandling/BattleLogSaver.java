package main.dusza.fileHandling;

import main.dusza.main.BattleRoundManager;

import java.io.FileWriter;
import java.util.ArrayList;

public class BattleLogSaver {
    public static void saveBattleLog(String filename) {
        try{
            FileWriter fileWriter = new FileWriter(filename);
            fileWriter.write(BattleRoundManager.logBuilder.toString());
            fileWriter.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
