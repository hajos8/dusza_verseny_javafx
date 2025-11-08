package main.dusza.render;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

public class DungeonBattle implements Initializable {
    @FXML HBox dungeonDeckHBox, playerDeckHBox;
    @FXML Label dungeonName;

    public boolean isDungeon = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GameController gameController = new GameController();
        String dungeonnNameString = "";

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

    private void handleCardClick(MouseEvent mouseEvent) {
        System.out.println("handleClick");
    }
}
