package main.dusza.render;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.dusza.gameElements.Card;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DungeonsController implements Initializable {
    public static String chosenDungeonType = "";

    @FXML public Label usernameLabel;
    @FXML public HBox deckHBox;
    @FXML public Label smallDungeonLabel;
    @FXML public Label hardDungeonLabel;
    @FXML public Label simpleDungeonLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameLabel.setText(GameController.currentPlayer.getName());
        deckHBox.setAlignment(Pos.CENTER);
        deckHBox.setSpacing(12);
        renderAll();
    }

    private void renderAll() {
        deckGenerator();
    }

    private void deckGenerator() {
        deckHBox.getChildren().clear();

        for (Card card : GameController.playerDeck) {
            atlantafx.base.controls.Card uiCard = buildUiCard(card);
            VBox wrapper = wrapCard(uiCard, card.getName());
            deckHBox.getChildren().add(wrapper);
        }
    }

    private atlantafx.base.controls.Card buildUiCard(Card card) {
        atlantafx.base.controls.Card uiCard = new atlantafx.base.controls.Card();
        uiCard.setHeader(new Label(card.getName()));
        uiCard.setBody(new Label(card.getDmg() + "/" + card.getHp()));
        uiCard.setFooter(new Label(card.getType()));
        uiCard.setId(card.getName());
        return uiCard;
    }

    private VBox wrapCard(atlantafx.base.controls.Card uiCard, String id) {
        VBox box = new VBox(uiCard);
        box.setId(id);
        return box;
    }

    @FXML
    public void handleSmallDungeon () throws IOException {
        chosenDungeonType = "kis";
        handleStartDungeon();
    }

    @FXML
    public void handleHardDungeon() throws IOException {
        chosenDungeonType = "nagy";
        handleStartDungeon();
    }

    @FXML
    public void handleSimpleDungeon() throws IOException {
        chosenDungeonType = "egyszeru";
        handleStartDungeon();
    }

    @FXML
    public void handleStartDungeon() throws IOException {
        URL fxml = getClass().getResource("/main/dusza/dungeonBattle-view.fxml");
        if (fxml != null) {
            Parent root = FXMLLoader.load(fxml);
            Scene scene = usernameLabel.getScene();
            scene.setRoot(root);
        }
        else {
            throw new FileNotFoundException("/main/dusza/dungeonBattle-view.fxml");
        }
    }

    public void handleNewDeck() throws IOException {
        URL fxml = getClass().getResource("/main/dusza/collection-view.fxml");
        if (fxml != null) {
            Parent root = FXMLLoader.load(fxml);
            Scene scene = simpleDungeonLabel.getScene();
            scene.setRoot(root);
        }
        else {
            throw new FileNotFoundException("FXML not found: /main/dusza/simpleDungeon-view.fxml");
        }
    }
}
