package test.gameElementsTest;

import main.dusza.gameElements.Card;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CardTest {
    @Test
    public void testConstructorsAndGetters() {
        Card card = new Card("testCard", 2, 5, "fire");
        assertEquals("testCard", card.getName());
        assertEquals(2, card.getDmg());
        assertEquals(5, card.getHp());
        assertEquals("fire", card.getType());



}


//--add-opens=main.dusza/main.dusza.gameElements=ALL-UNNAMED

    @Test
    public void testSetters(){
        Card card = new Card("testCard2", 2, 5, "water");

        card.setType("water");
        card.setName("testCard2");
        card.setHp(3);
        card.setDmg(8);

        assertEquals("testCard2", card.getName());
        assertEquals(8, card.getDmg());
        assertEquals(3, card.getHp());
        assertEquals("water", card.getType());
    }
}
