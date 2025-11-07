package main.dusza.render;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.dusza.gameElements.Card;
import main.dusza.gameElements.Player;
import main.dusza.main.GameData;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    @FXML public HBox cardsHBox;
    @FXML public MFXButton nextButton;
    @FXML public Label usernameLabel;
    @FXML private AnchorPane collectionPane;

    public static Player currentPlayer = GameData.PlayersList.getFirst();
    public static ArrayList<Card> playerCollection = currentPlayer.getCollection();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cardGenerator();
        usernameLabel.setText(currentPlayer.getName());
    }

    protected void cardGenerator () {
        for (Card  card : playerCollection) {
            atlantafx.base.controls.Card newCard = new atlantafx.base.controls.Card();
            VBox vBox = new VBox();

            Label nameLabel = new Label(card.getName());
            Label dmgHpLabel = new Label(card.getDmg() + "/" + card.getHp());
            Label typeLabel = new Label(card.getType());

            newCard.setHeader(nameLabel);
            newCard.setBody(dmgHpLabel);
            newCard.setFooter(typeLabel);

            vBox.getChildren().addAll(newCard);
            cardsHBox.getChildren().add(vBox);
        }
    }

    @FXML
    protected void handleNextButton() throws IOException {

        URL fxml = getClass().getResource("/main/dusza/dungeons-view.fxml");
        if (fxml != null) {
            Parent root = FXMLLoader.load(fxml);
            Scene scene = nextButton.getScene();
            scene.setRoot(root);
        }
        else {
            System.err.println("Fxml file not found!");
        }
    }
}
