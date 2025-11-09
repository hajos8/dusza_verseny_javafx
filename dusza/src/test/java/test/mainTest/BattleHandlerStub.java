package test.mainTest;

import main.dusza.gameElements.Card;

public class BattleHandlerStub {

        public static String lastAction = "";
        public static String lastSide = "";
        public static Card lastAttacker;
        public static Card lastDefender;
        public static Card lastCardPlayed;

        public static void attack(String lastAction, String lastSide, Card lastAttacker, Card lastDefender){
            lastAction = "attack";
            lastSide = lastSide;
            lastAttacker = lastAttacker;
            lastDefender = lastDefender;

        }

        public static void playCard(String side, Card card){
            lastAction = "playCard";
            lastSide = side;
            lastAttacker = card;
        }

    public static void reset() {
        lastAction = null;
        lastSide = null;
        lastAttacker = null;
        lastDefender = null;
        lastCardPlayed = null;

    }
}
