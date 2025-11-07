package main.dusza.render;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.dusza.gameElements.Card;
import main.dusza.gameElements.Player;
import main.dusza.main.GameData;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    @FXML public HBox collectionHBox;
    @FXML public HBox deckHBox;
    @FXML public MFXButton nextButton;
    @FXML public Label usernameLabel;

    public static String exportUsername;

    public static Player currentPlayer = GameData.PlayersList.getFirst();
    ArrayList<Card> playerCollection = currentPlayer.getCollection();
    public static ArrayList<Card> playerDeck = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        exportUsername = currentPlayer.getName();
        renderAll();
    }

    private void renderAll() {
        collectionGenerator();
        deckGenerator();
    }

    private void collectionGenerator() {
        collectionHBox.getChildren().clear();

        for (Card card : playerCollection) {
            atlantafx.base.controls.Card uiCard = buildUiCard(card);
            VBox wrapper = wrapCard(uiCard, card.getName());
            collectionHBox.getChildren().add(wrapper);
        }
    }

    private void deckGenerator() {
        deckHBox.getChildren().clear();

        for (Card card : playerDeck) {
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
        box.setOnMouseClicked(this::handleCardClick);
        return box;
    }

    private void handleCardClick(MouseEvent e) {
        Node source = (Node) e.getSource();
        String id = source.getId();

        Card inCollection = findByName(playerCollection, id);
        Card inDeck = findByName(playerDeck, id);

        boolean stateChanged = false;

        if (inCollection != null && inDeck == null) {
            if (playerDeck.size() < 2) {
                playerDeck.add(inCollection);
                playerCollection.remove(inCollection);
                stateChanged = true;
            } else {
                Card lastFromDeck = playerDeck.getLast();
                playerCollection.add(lastFromDeck);
                playerCollection.remove(inCollection);
                playerDeck.set(1, playerDeck.getFirst());
                playerDeck.set(0, inCollection);
                stateChanged = true;
            }
        } else if (inDeck != null) {
            playerDeck.remove(inDeck);
            playerCollection.add(inDeck);
            stateChanged = true;
        }

        if (stateChanged) {
            renderAll();
        }
    }

    private Card findByName(ArrayList<Card> list, String name) {
        for (Card card : list) {
            if (card.getName().equals(name)) return card;
        }
        return null;
    }

    @FXML
    public void handleNextButton() throws IOException {
        if (playerDeck.size() == 2) {
            URL fxml = getClass().getResource("/main/dusza/dungeons-view.fxml");
            if (fxml != null) {
                Parent root = FXMLLoader.load(fxml);
                Scene scene = nextButton.getScene();
                scene.setRoot(root);
            }
            else {
                throw new FileNotFoundException("FXML not found: /main/dusza/dungeons-view.fxml");
            }
        }
    }

    public void hardDungeon() { /* TODO */ }

    public void simpleDungeon() { /* TODO */ }

    public void smallDungeon() {
        /* TODO */
    }
}