package main.dusza.render;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

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
    }

    @FXML
    protected void handlePlayButton() {

        String username = "Player";

        if (!usernameTextField.getText().isEmpty()) {
            playButton.setVisible(false);
            titleLabel.setVisible(false);
            usernameLabel.setVisible(false);
        }


    }
}

