package main.dusza.render;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import main.dusza.gameElements.Player;
import main.dusza.main.GameData;

import java.io.IOException;
import java.net.URL;

public class StartDamareenController {

    @FXML public AnchorPane container;
    @FXML public MFXButton startButton;
    @FXML public Label titleLabel;
    @FXML public Label usernameLabel;
    @FXML public MFXTextField usernameTextField;
    @FXML public MFXButton playButton;


    @FXML
    protected void handleStartButton() {
        startButton.setVisible(false);
        titleLabel.setVisible(false);
        usernameLabel.setVisible(true);
        usernameTextField.setVisible(true);
        playButton.setVisible(true);
    }

    @FXML
    protected void handlePlayButton() throws IOException {

        String username = "Player";

        if (!usernameTextField.getText().isEmpty()) {
            username = usernameTextField.getText();
        }
        GameData.PlayersList.getFirst().setName(username);

        URL fxml = getClass().getResource("/main/dusza/collection-view.fxml");
        if (fxml != null) {
            Parent root = FXMLLoader.load(fxml);
            Scene scene = playButton.getScene();
            scene.setRoot(root);
        }
        else {
            System.err.println("Fxml file not found!");
        }
    }
}

