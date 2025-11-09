package main.dusza.render;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.dusza.gameElements.Card;
import main.dusza.gameElements.Dungeon;
import main.dusza.main.BattleRoundManager;
import main.dusza.main.GameData;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;

public class DungeonBattle implements Initializable {
    @FXML HBox enemyTable;
    @FXML HBox playerTable;
    @FXML HBox playerDeckHBox;
    @FXML HBox dungeonDeckHBox;
    @FXML Label dungeonName;
    @FXML Label usernameLabel;

    public boolean isDungeon = false;
    private volatile CompletableFuture<Card> waitingChoice;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String dungeonnNameString = "";

        dungeonDeckHBox.setAlignment(Pos.CENTER);
        dungeonDeckHBox.setSpacing(12);

        playerDeckHBox.setAlignment(Pos.CENTER);
        playerDeckHBox.setSpacing(12);

        enemyTable.setAlignment(Pos.CENTER);
        enemyTable.setSpacing(12);

        playerTable.setAlignment(Pos.CENTER);
        playerTable.setSpacing(12);

        usernameLabel.setText(GameData.PlayersList.getFirst().getName());

        isDungeon = true;
        for(Dungeon dungeon : GameData.worldDungeonList) {
            if(dungeon.getType().equals(DungeonsController.chosenDungeonType)) {
                deckGenerator(dungeonDeckHBox, dungeon.getDungeonDeck());
                dungeonnNameString = dungeon.getName();
            }
        }

        dungeonName.setText(dungeonnNameString);
        isDungeon = false;
        deckGenerator(playerDeckHBox, GameController.playerDeck);

        // BattleRoundManager.battle(dungeonnNameString);
        String finalDungeonnNameString = dungeonnNameString;
        new Thread(() -> BattleRoundManager.battle(finalDungeonnNameString, this), "Battle-Thread").start();
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
        if (!isDungeon) box.setOnMouseClicked(this::handleCardClick);
        return box;
    }

    public void updateTable (String name) {
        switch (name) {
            case "jatekos" -> {
                deckGenerator(playerDeckHBox, BattleRoundManager.playerHand);
                deckGenerator(playerTable, BattleRoundManager.playerTable);
            }
            case "kazamata" -> {
                deckGenerator(dungeonDeckHBox, BattleRoundManager.enemyHand);
                deckGenerator(enemyTable, BattleRoundManager.enemyTable);
            }
        }
    }

    public Card awaitPlayerChoiceFromHand(ArrayList<Card> playerHand) {
        Platform.runLater(() -> {
            deckGenerator(playerDeckHBox, playerHand);
        });

        waitingChoice = new CompletableFuture<>();
        return waitingChoice.join();
    }

    private void handleCardClick(MouseEvent e) {
        Node src = (Node) e.getSource();
        String id = src.getId();

        Card clicked = null;
        for (Card card : BattleRoundManager.playerHand) {
            if (card.getName().equals(id)) {
                clicked = card;
            }
        }
        if (clicked == null) return;

        if (waitingChoice != null && !waitingChoice.isDone()) {
            waitingChoice.complete(clicked);
            waitingChoice = null;
        }
    }
}
