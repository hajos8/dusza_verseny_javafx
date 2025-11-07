package main.dusza;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import main.dusza.render.RenderFunctions;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class StartDamareenController implements Initializable {

    @FXML public AnchorPane container;
    @FXML public MFXButton startButton;
    @FXML public Label titleLabel;
    @FXML public Label usernameLabel;
    @FXML public MFXTextField usernameTextField;
    @FXML public MFXButton playButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String path = "src/main/java/main/dusza/render/in.txt";
        RenderFunctions.generateWorld(path); //TODO refactor out of the render package
    }

    @FXML
    protected void handleStartButton() {
        startButton.setVisible(false);
        titleLabel.setVisible(false);
        usernameLabel.setVisible(true);
        usernameTextField.setVisible(true);
    }

    @FXML
    protected void handlePlayButton() {
        playButton.setVisible(false);
        titleLabel.setVisible(false);
        usernameLabel.setVisible(false);
    }
}

