package main.dusza;

import javafx.application.Application;
import main.dusza.main.BattleRoundManager;
import main.dusza.setup.Setup;

import java.util.Objects;

public class Launcher {
    public static String defaultOutputPath;

    public static void main(String[] args) {
        if(args.length == 0){
            System.out.println("No input file provided.");
        }
        else if(args.length == 1 && Objects.equals(args[0], "--ui")){
            //GUI mode
            String path = "in.txt"; //default world for GUI
            Setup.generateWorld(path); // Generates the world from the input file

            Application.launch(DamareenApplication.class, args);
        }
        else{
            //test mode
            defaultOutputPath = args[0] + "/";
            String path = defaultOutputPath + "in.txt";
            BattleRoundManager.testMode = true;
            Setup.generateWorld(path); // Generates the world from the input file
        }


        //testing
        //ShowDatas.showData();
    }
}
