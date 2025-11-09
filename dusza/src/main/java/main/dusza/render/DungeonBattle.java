package main.dusza.render;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.dusza.gameElements.Card;
import main.dusza.gameElements.Dungeon;
import main.dusza.main.BattleHandler;
import main.dusza.main.BattleRoundManager;
import main.dusza.main.GameData;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;

public class DungeonBattle implements Initializable {
    @FXML public ListView<String> actionsListview;
    @FXML public HBox enemyTable;
    @FXML public HBox playerTable;
    @FXML public HBox playerDeckHBox;
    @FXML public HBox dungeonDeckHBox;
    @FXML public Label dungeonName;
    @FXML public Label usernameLabel;

    public boolean isDungeon = false;
    private volatile CompletableFuture<Card> waitingChoice;
    private volatile Card selectedAttacker;
    private volatile Card selectedDefender;

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
                deckGenerator(dungeonDeckHBox, dungeon.getDungeonDeck(), "handleCardClick", true);
                dungeonnNameString = dungeon.getName();
            }
        }

        dungeonName.setText(dungeonnNameString);
        deckGenerator(playerDeckHBox, GameController.playerDeck, "handleCardClick", false);

        // BattleRoundManager.battle(dungeonnNameString);
        String finalDungeonnNameString = dungeonnNameString;
        new Thread(() -> BattleRoundManager.battle(finalDungeonnNameString, this), "Battle-Thread").start();
    }

    public void deckGenerator(HBox deckHBox, ArrayList<Card> deck, String handlerName, boolean isDungeon) {
        deckHBox.getChildren().clear();

        for (Card card : deck) {
            atlantafx.base.controls.Card uiCard = buildUiCard(card);
            VBox wrapper = wrapCard(uiCard, card.getName(), handlerName, isDungeon);
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

    private VBox wrapCard(atlantafx.base.controls.Card uiCard, String id, String handlerName, boolean isDungeon) {
        VBox box = new VBox(uiCard);
        box.setId(id);
        if (!isDungeon) {
            if (handlerName.equals("handleAttackClick")) {
                box.setOnMouseClicked(this::handleAttackPlayerClick);
            } else {
                box.setOnMouseClicked(this::handleCardClick);
            }
        } else {
            if (handlerName.equals("handleAttackEnemyClick")) {
                box.setOnMouseClicked(this::handleAttackEnemyClick);
            }
        }
        return box;
    }

    public void updateTable (String name) {
        switch (name) {
            case "jatekos" -> {
                deckGenerator(playerDeckHBox, BattleRoundManager.playerHand, "handleCardClick", false);
                deckGenerator(playerTable, BattleRoundManager.playerTable, "handleAttackClick", false);
                updateLogs(BattleRoundManager.logBuilder);
            }
            case "kazamata" -> {
                deckGenerator(dungeonDeckHBox, BattleRoundManager.enemyHand, "handleCardClick", true);
                deckGenerator(enemyTable, BattleRoundManager.enemyTable, "handleAttackEnemyClick", true);
                updateLogs(BattleRoundManager.logBuilder);
            }
        }
    }

    public void updateLogs(StringBuilder logs){
        String readableLogs = String.valueOf(logs);
        StringBuilder formattedLogs = new StringBuilder();

        for(String i : readableLogs.split("\n")) {
            String[] parts = i.split(";");

            String printOut = "";

            if(parts.length > 3){
                String name = Objects.equals(parts[1], "jatekos") ? GameData.PlayersList.getFirst().getName() : "Kazamata";
                printOut = parts[0] + name + ": ";

                printOut = printOut.replace("kor", " Kör: ");

                switch(parts[2]){
                    case "kijatszik" -> {
                        printOut += " kijátszotta a " + parts[3] + " kártyát (" + parts[4] + "/" + parts[5] + ", " + parts[6] + ")";
                    }
                    case "tamad" ->{
                        printOut += parts[3] + " kártya támadt, sebzése: " + parts[4] + ", védője: " +  parts[5] + ", védője megmaradt élete: " +  parts[6];
                    }
                }
            }

            printOut += "\n";
            formattedLogs.append(printOut);
        }

        ObservableList<String> observableList = FXCollections.observableArrayList(formattedLogs.toString().split("\n"));
        actionsListview.setItems(observableList);

    }

    public Card awaitPlayerChoiceFromHand(ArrayList<Card> playerHand) {
        Platform.runLater(() -> {
            deckGenerator(playerDeckHBox, playerHand, "handleCardClick", false);
            deckGenerator(playerTable, BattleRoundManager.playerTable, "handleAttackClick", false);
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

    private void handleAttackPlayerClick(MouseEvent e) {
        Node src = (Node) e.getSource();
        String id = src.getId();

        selectedAttacker = null;
        for (Card card : BattleRoundManager.playerTable) {
            if (card != null && Objects.equals(card.getName(), id)) {
                selectedAttacker = card;
                break;
            }
        }
        // reset selected defender when choosing attacker
        selectedDefender = null;
    }

    private void handleAttackEnemyClick(MouseEvent e) {
        // require an attacker selected first
        if (selectedAttacker == null) return;

        Node src = (Node) e.getSource();
        String id = src.getId();

        selectedDefender = null;
        for (Card card : BattleRoundManager.enemyTable) {
            if (card != null && Objects.equals(card.getName(), id)) {
                selectedDefender = card;
                break;
            }
        }
        if (selectedDefender == null) return;

        // perform attack (FX thread) and refresh enemy table
        BattleHandler.attack("jatekos", selectedAttacker, selectedDefender);
        deckGenerator(enemyTable, BattleRoundManager.enemyTable, "handleAttackEnemyClick", true);

        // if battle thread is waiting for a play, complete with null to indicate attack happened
        if (waitingChoice != null && !waitingChoice.isDone()) {
            waitingChoice.complete(null);
        }

        // clear selections
        selectedAttacker = null;
        selectedDefender = null;
    }

    private void handleBattleEnd(){
        DungeonsController dungeonsController = new DungeonsController();
        dungeonsController.initialize(null, null);
    }
}
