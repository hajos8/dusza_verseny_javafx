package test.gameElementsTest;

import main.dusza.gameElements.LeaderCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LeaderCardTest {

    private LeaderCard leaderCard;
    private LeaderCard leaderCard2;

    @BeforeEach
    public void setUp() {
                leaderCard = new LeaderCard("Pitypang", 4, 5, "fire", "PitypangNagyobb", "sebzes");
                leaderCard2 = new LeaderCard("Pitypang", 4, 5, "fire", "PitypangNagyobb", "eletero");

    }
    @Test
    public void testConstructorAndGetter() {
       assertEquals("PitypangNagyobb", leaderCard.getName());
       assertEquals(8, leaderCard.getDmg());
       assertEquals(5, leaderCard.getHp());
       assertEquals("fire", leaderCard.getType());
       assertEquals("sebzes", leaderCard.getPowerUp());

    }

    @Test
    public void testSetters() {
        leaderCard.setName("PitypangNagyobb");
        leaderCard.setDmg(8);
        leaderCard.setHp(5);
        leaderCard.setType("fire");
        leaderCard.setPowerUp("sebzes");

        assertEquals("PitypangNagyobb", leaderCard.getName());
        assertEquals(8, leaderCard.getDmg());
        assertEquals(5, leaderCard.getHp());
        assertEquals("fire", leaderCard.getType());
        assertEquals("sebzes", leaderCard.getPowerUp());

    }
@Test
    public void testPowerUpEffectHealthMultiplier() {
        leaderCard2.setName("PitypangNagyobb");
        leaderCard2.setDmg(4);
        leaderCard2.setHp(10);
        leaderCard2.setType("fire");
        leaderCard2.setPowerUp("eletero");

        assertEquals("PitypangNagyobb", leaderCard2.getName());
        assertEquals(4, leaderCard2.getDmg());
        assertEquals(10, leaderCard2.getHp());
        assertEquals("fire", leaderCard2.getType());
        assertEquals("eletero", leaderCard2.getPowerUp());
    }

}
