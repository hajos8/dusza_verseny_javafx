package main.dusza.render;

import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
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
                box.setOnMouseClicked(this::handleAttackClick);
            } else {
                box.setOnMouseClicked(this::handleCardClick);
            }
        }
        return box;
    }

    public void updateTable (String name) {

        switch (name) {
            case "jatekos" -> {
                deckGenerator(playerDeckHBox, BattleRoundManager.playerHand, "handleCardClick", false);
                deckGenerator(playerTable, BattleRoundManager.playerTable, "handleAttackClick", false);
            }
            case "kazamata" -> {
                deckGenerator(dungeonDeckHBox, BattleRoundManager.enemyHand, "handleCardClick", true);
                deckGenerator(enemyTable, BattleRoundManager.enemyTable, "handleAttackClick", true);
            }
        }
    }

    public void updateLogs(StringBuilder logs){
        //actionsListview
        /*
        harc kezdodik;Teszt1a Kazamata

        1.kor;kazamata;kijatszik;Sadan;2;4;levego
        1.kor;jatekos;kijatszik;Corky;2;4;fold

        2.kor;kazamata;tamad;Sadan;4;Corky;0
        2.kor;jatekos;kijatszik;Kira;2;7;levego

        3.kor;kazamata;tamad;Sadan;2;Kira;5
        3.kor;jatekos;tamad;Kira;2;Sadan;2

        4.kor;kazamata;tamad;Sadan;2;Kira;3
        4.kor;jatekos;tamad;Kira;2;Sadan;0

        jatekos nyert;eletero;Kira
        */
        String readableLogs = String.valueOf(logs);
        StringBuilder formattedLogs = new StringBuilder();

        for(String i : readableLogs.split("\n")) {
            String[] parts = i.split(";");

            String printOut = "";

            if(parts.length > 3){
                String name = Objects.equals(parts[1], "jatekos") ? GameData.PlayersList.getFirst().getName() : "Kazamata";
                printOut = parts[0] + name + " ";

                printOut = printOut.replace("kor", " Kör: ");

                switch(parts[2]){
                    case "kijatszik" -> {
                        /*parts[3] //neve
                        parts[4] //dmg
                        parts[5] //hp
                        parts[6] //element*/
                        // TODO - A típusok szép kiírása mindenhol!
                        printOut += " kijátszotta a " + parts[3] + " kártyát (" + parts[4] + "/" + parts[5] + ", " + parts[6] + ")";
                    }
                    case "tamad" ->{
                        /*
                        parts[3] //tamado
                        parts[4] //tamado sebzese
                        parts[5] //vedo
                        parts[6] //vedo maradek elete
                        */
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
    
    private void handleAttackClick (MouseEvent e) {
        Node src = (Node) e.getSource();
        String id = src.getId();
    }
}
