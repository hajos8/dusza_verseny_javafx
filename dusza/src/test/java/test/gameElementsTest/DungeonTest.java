package test.gameElementsTest;

import main.dusza.gameElements.Card;
import main.dusza.gameElements.Dungeon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

public class DungeonTest {

    @Test

    public void testConstructorsAndGetters() {

        Card c1 = new Card("Aragorn", 2, 5, "water");
        Card c2 = new Card("Eowyn", 2, 5, "water");
        Card c3 = new Card("ObiWan", 2, 5, "water");
        Card c4 = new Card("Quastic", 2, 5, "water");

        ArrayList<Card> DungeonDeck = new ArrayList<Card>(Arrays.asList(c1, c2, c3, c4));

        Dungeon dungeon = new Dungeon("Quastic's lair", "nagy", "kartya", DungeonDeck);

        assertEquals("Quastic's lair", dungeon.getName());
        assertEquals("nagy", dungeon.getType());
        assertEquals("kartya", dungeon.getPrize());
        assertEquals(DungeonDeck, dungeon.getDungeonDeck());
    }

        @Test

        public void testSetters(){
            Card c1 = new Card("Aragorn", 2, 5, "water");
            Card c2 = new Card("Eowyn", 2, 5, "water");
            Card c3 = new Card("ObiWan", 2, 5, "water");
            Card c4 = new Card("Quastic", 2, 5, "water");

            ArrayList<Card> DungeonDeck = new ArrayList<Card>(Arrays.asList(c1, c2, c3, c4));

            Dungeon dungeon = new Dungeon("Quastic's lair", "nagy", "kartya", DungeonDeck);

            dungeon.setName("Quastic's lair");
            dungeon.setType("nagy");
            dungeon.setPrize("kartya");
            dungeon.setDungeonDeck(DungeonDeck);


            assertEquals("Quastic's lair", dungeon.getName());
            assertEquals("kartya", dungeon.getPrize());
            assertEquals("nagy", dungeon.getType());
            assertEquals(DungeonDeck, dungeon.getDungeonDeck());
        }





}
