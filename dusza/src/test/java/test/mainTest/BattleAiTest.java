package test.mainTest;

import main.dusza.gameElements.Card;
import main.dusza.gameElements.Dungeon;
import main.dusza.main.BattleAI;
import main.dusza.main.BattleHandler;
import main.dusza.main.BattleRoundManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.Random;

public class BattleAiTest {
    /*
    Card c1 = new Card("hajos", 2, 5, "fire");
    Card c2 = new Card("sunshine", 2, 6, "water");

    @BeforeEach
    public void beforeTest(){
        BattleRoundManager.testMode = true;
        //BattleAI.isRunningTest = false;
        //BattleAI.rand = new Random(0);
        BattleHandlerStub.lastAction = null;

        BattleRoundManager.playerTable = new ArrayList<>();
        BattleRoundManager.enemyTable = new ArrayList<>();
        BattleRoundManager.playerHand = new ArrayList<>();
        BattleRoundManager.enemyHand = new ArrayList<>();

    }


    @Test
    public void testingPlayerAttacksWhenTableIsNotEmpty(){
        BattleRoundManager.playerTable.add(c1);
        BattleRoundManager.enemyTable.add(c2);

        BattleAI.generateTurn("player");


        assertEquals("attack", BattleHandlerStub.lastAction);
        assertEquals("jatekos", BattleHandlerStub.lastSide);
        assertEquals(c1, BattleHandlerStub.lastAttacker);
        assertEquals(c2, BattleHandlerStub.lastDefender);

        BattleAI battleAI = new BattleAI();

    }

     @Test
     public void testingPlayerAttacksWhenTableIsEmpty(){
        BattleRoundManager.playerTable.add(c1);

        BattleAI.generateTurn("player");

        assertEquals("attack", BattleHandlerStub.lastAction);
        assertEquals("jatekos", BattleHandlerStub.lastSide);
        assertEquals(c1, BattleHandlerStub.lastAttacker);
        assertEquals(c2, BattleAI.lastPlayedPlayerCard);

     }
    @Test
    public void testingEnemyAttacksWhenTableIsNotEmpty(){
        BattleRoundManager.playerTable.add(c1);
        BattleRoundManager.enemyTable.add(c2);

        BattleAI.generateTurn("kazamata");


        assertEquals("attack", BattleHandlerStub.lastAction);
        assertEquals("kazamata", BattleHandlerStub.lastSide);
        assertEquals(c1, BattleHandlerStub.lastAttacker);
        assertEquals(c2, BattleHandlerStub.lastDefender);

        BattleAI battleAI = new BattleAI();

    }

    @Test
    public void testingEnemyAttacksWhenTableIsEmpty(){
        BattleRoundManager.enemyTable.add(c1);

        BattleAI.generateTurn("kazamata");

        assertEquals("attack", BattleHandlerStub.lastAction);
        assertEquals("kazamata", BattleHandlerStub.lastSide);
        assertEquals(c1, BattleHandlerStub.lastAttacker);
        assertEquals(c2, BattleAI.lastPlayedPlayerCard);

    }


    /*@AfterEach
    public void afterTest(){
        BattleAI.isRunningTest = false;
    }*/

}
