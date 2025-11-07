package main.dusza.render;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.dusza.gameElements.Card;
import io.github.palexdev.materialfx.controls.MFXButton;

import java.net.URL;
import java.util.ResourceBundle;

public class DungeonController implements Initializable {
    @FXML public Label usernameLabel;
    @FXML public HBox cardsHBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameLabel.setText(GameController.currentPlayer.getName());
    }

    protected void cardGenerator () {

    }
}
