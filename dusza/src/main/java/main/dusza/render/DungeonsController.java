package main.dusza.render;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import main.dusza.gameElements.Card;
import main.dusza.gameElements.Player;
import main.dusza.main.GameData;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DungeonsController implements Initializable {
    @FXML public Label usernameLabel;
    @FXML public HBox deckHBox;
    @FXML public HBox collectionHBox;

    public void smallDungeon(MouseEvent mouseEvent) {
    }

    public void hardDungeon(MouseEvent mouseEvent) {
    }

    public void simpleDungeon(MouseEvent mouseEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameLabel.setText(GameController.exportUsername);
        renderAll();
    }

    private void renderAll() {
        collectionGenerator();
        deckGenerator();
    }

    private void collectionGenerator() {
        collectionHBox.getChildren().clear();

        for (Card card : GameController.currentPlayer.getCollection()) {
            atlantafx.base.controls.Card uiCard = buildUiCard(card);
            VBox wrapper = wrapCard(uiCard, card.getName());
            collectionHBox.getChildren().add(wrapper);
        }
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
}
