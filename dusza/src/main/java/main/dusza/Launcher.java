package main.dusza;

import javafx.application.Application;
import main.dusza.setup.Setup;

public class Launcher {
    public static void main(String[] args) {
        String path = "src/main/resources/main/dusza/in.txt";
        Setup.generateWorld(path); // Generates the world from the input file

        Application.launch(DamareenApplication.class, args);
    }
}
