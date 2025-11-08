package main.dusza.render;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import main.dusza.gameElements.Dungeon;
import main.dusza.main.BattleRoundManager;
import main.dusza.main.GameData;

import java.net.URL;
import java.util.ResourceBundle;

public class DungeonBattle implements Initializable {
    @FXML HBox dungeonDeckHBox, playerDeckHBox;
    @FXML Label dungeonName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GameController gameController = new GameController();
        String dungeonnNameString = "";

        for(Dungeon dungeon : GameData.worldDungeonList) {
            if(dungeon.getType().equals(DungeonsController.chosenDungeonType)) {
                gameController.deckGenerator(dungeonDeckHBox, dungeon.getDungeonDeck());
                dungeonnNameString = dungeon.getName();
            }
        }

        dungeonName.setText(dungeonnNameString);

        gameController.deckGenerator(playerDeckHBox, GameController.playerDeck);

        BattleRoundManager.battle(dungeonnNameString);
    }
}
