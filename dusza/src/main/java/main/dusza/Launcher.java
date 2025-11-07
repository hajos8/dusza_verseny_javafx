package main.dusza;

import javafx.application.Application;
import main.dusza.fileHandling.PlayerSaver;
import main.dusza.fileHandling.WorldSaver;
import main.dusza.setup.Setup;

public class Launcher {
    public static void main(String[] args) {
        String path = "src/main/resources/main/dusza/in.txt";
        Setup.generateWorld(path); // Generates the world from the input file

        //testing
        //ShowDatas.showData();

        //WorldSaver.saveWorld("asd1.txt");
        //PlayerSaver.savePlayerData("asd2.txt");

        Application.launch(DamareenApplication.class, args);
    }
}
