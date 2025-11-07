package test.fileHandlingTest;

import main.dusza.fileHandling.WorldSaver;
import main.dusza.gameElements.Card;
import main.dusza.gameElements.Dungeon;
import main.dusza.gameElements.LeaderCard;
import main.dusza.gameElements.Player;
import main.dusza.main.GameData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class WorldSaverTest {
    @BeforeEach
    public void beforeTest(){



        WorldSaver.isRunningTest=true;

        GameData.worldCardsList.add(new Card("hajos", 200, 5, "water"));
        GameData.worldCardsList.add((new LeaderCard("sunshine", 3, 5, "levego", "beefsunshine", "eletero")));

        GameData.worldDungeonList.add(new Dungeon("31. terem", "nagy", "kartya", GameData.worldCardsList ));

    }

    @Test
    public void testSaveWorld(){
        WorldSaver.saveWorld("WorldSave");
    }

    @AfterEach
    public void afterTest(){
        WorldSaver.isRunningTest=false;
    }
}
