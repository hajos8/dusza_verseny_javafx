package main.dusza.render;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.dusza.ShowDatas;
import main.dusza.gameElements.Card;
import main.dusza.gameElements.Player;
import main.dusza.main.BattleRoundManager;
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
    @FXML public Label task;
    @FXML public AnchorPane collectionContainer;
    @FXML public ScrollPane collectionScroll;
    @FXML public AnchorPane collectionPane;

    public static Player currentPlayer = GameData.PlayersList.getFirst();
    public static ArrayList<Card> playerDeck = new ArrayList<>();

    ArrayList<Card> playerCollection = new ArrayList<>();

    int collectionHalfSize = 0;
    int chosenCardsCount = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        collectionHBox.setAlignment(Pos.CENTER);
        deckHBox.setAlignment(Pos.CENTER);
        collectionHBox.setSpacing(12);
        deckHBox.setSpacing(12);

        // deep-copy deck
        for(Card card : currentPlayer.getCollection()){
            playerCollection.add(new Card(card.getName(), card.getDmg(), card.getHp(), card.getType()));
        }
        GameData.PlayersList.getFirst().setDeck(new ArrayList<>());
        playerDeck.clear();

        if (playerCollection.size() % 2 != 0) {
            collectionHalfSize = currentPlayer.getCollection().size() / 2 + 1;
        } else {
            collectionHalfSize = currentPlayer.getCollection().size() / 2;
        }

        chosenCardsCount = playerDeck.size();

        task.setText("Válassz ki " + (collectionHalfSize - chosenCardsCount) + " darab kártyát!");
        renderAll();
    }

    private void renderAll() {
        collectionGenerator();
        deckGenerator(deckHBox, playerDeck);
    }

    private void collectionGenerator() {
        collectionHBox.getChildren().clear();

        for (Card card : playerCollection) {
            atlantafx.base.controls.Card uiCard = buildUiCard(card);
            VBox wrapper = wrapCard(uiCard, card.getName());
            collectionHBox.getChildren().add(wrapper);
        }
    }

    public void deckGenerator(HBox deckHBox, ArrayList<Card> deck) {
        deckHBox.getChildren().clear();

        for (Card card : deck) {
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

    @FXML
    private void handleCardClick(MouseEvent e) {
        Node source = (Node) e.getSource();
        String id = source.getId();

        Card inCollection = findByName(playerCollection, id);
        Card inDeck = findByName(playerDeck, id);

        boolean stateChanged = false;

        if (inCollection != null && inDeck == null) {
            if (playerDeck.size() < collectionHalfSize) {
                playerDeck.add(inCollection);
                playerCollection.remove(inCollection);
                stateChanged = true;
                chosenCardsCount++;
                task.setText("Válassz ki " + (collectionHalfSize - chosenCardsCount) + " darab kártyát!");
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
        if (playerDeck.size() == collectionHalfSize) {
            URL fxml = getClass().getResource("/main/dusza/dungeons-view.fxml");
            if (fxml != null) {
                Parent root = FXMLLoader.load(fxml);
                Scene scene = nextButton.getScene();
                scene.setRoot(root);
                GameData.PlayersList.getFirst().setDeck(playerDeck);
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