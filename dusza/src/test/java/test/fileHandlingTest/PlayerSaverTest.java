package test.fileHandlingTest;

import main.dusza.fileHandling.PlayerSaver;
import main.dusza.gameElements.Card;
import main.dusza.gameElements.Player;
import main.dusza.main.GameData;
import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;



public class PlayerSaverTest {

    @BeforeEach
    public void beforeTest(){
        PlayerSaver.isRunningTest=true;

        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card("hajos", 200, 5, "water"));
        Player player = new Player();
        player.setCollection(cards);
        player.setDeck(cards);
        GameData.PlayersList.add(player);

    }

    @Test
    public void testSavePlayer(){
        PlayerSaver.savePlayerData("PlayerSave");
    }

    @AfterEach
    public void afterTest(){
        PlayerSaver.isRunningTest=false;
    }

}