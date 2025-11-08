package main.dusza.fileHandling;

import main.dusza.Launcher;
import main.dusza.main.BattleRoundManager;

import java.io.FileWriter;

public class BattleLogSaver {
    public static void saveBattleLog(String filename) {
        try{
            FileWriter fileWriter = new FileWriter(Launcher.defaultOutputPath + filename);
            fileWriter.write(BattleRoundManager.logBuilder.toString());
            fileWriter.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
