package main.dusza;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import main.dusza.render.TxtParser;


public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText(TxtParser.data.toString());
    }
}
