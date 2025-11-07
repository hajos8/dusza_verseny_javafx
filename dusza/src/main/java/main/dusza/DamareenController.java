package main.dusza;

import javafx.fxml.Initializable;
import main.dusza.setup.RenderFunctions;

import java.net.URL;
import java.util.ResourceBundle;


public class DamareenController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String path = "src/main/java/main/dusza/render/in.txt";
        RenderFunctions.generateWorld(path); //TODO refactor out of the render package


    }
}

