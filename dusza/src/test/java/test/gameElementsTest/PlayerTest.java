package test.gameElementsTest;

import main.dusza.gameElements.Card;
import main.dusza.gameElements.Player;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @BeforeEach
            public void playerSetup(){
        player = new Player();

    }


    private Player player;

    @Test
    public void testPlayerSetters() {

        Card c1 = new Card("Aragorn", 2, 5, "water");
        Card c2 = new Card("Eowyn", 2, 5, "water");
        Card c3 = new Card("ObiWan", 2, 5, "water");
        Card c4 = new Card("Quastic", 2, 5, "water");

        ArrayList<Card> PlayerCollection = new ArrayList<Card>(Arrays.asList(c1, c2, c3, c4));
        ArrayList<Card> PlayerDeck = new ArrayList<Card>(Arrays.asList(c2, c4));


        player.setName("Quastic");
        player.setCollection(PlayerCollection);
        player.setDeck(PlayerDeck);


        assertEquals("Quastic", player.getName());
        assertEquals(PlayerCollection, player.getCollection());
        assertEquals(PlayerDeck, player.getDeck());

    }

}
