package main.dusza.main;

import main.dusza.gameElements.Card;
import main.dusza.gameElements.Dungeon;

import java.util.ArrayList;
import java.util.Objects;

public class BattleHandler {
    public static void playCard(String name, Card card) {
        switch (name){
            case "jatekos" ->{
                Battle.playerHand.remove(card);
                Battle.playerTable.add(card);
            }
            case "kazamata" -> {
                Battle.enemyHand.remove(card);
                Battle.enemyTable.add(card);
            }
        }
    }

    //attack
    public static void attack(String attackingPlayer, Card attacker, Card defender){
        switch (attacker.getType()){
            case "tuz" -> {
                switch (defender.getType()){
                    case "levego" -> defender.setHp(defender.getHp() - attacker.getDmg() / 2);
                    case "fold" -> defender.setHp(defender.getHp() - attacker.getDmg() * 2);
                    case "viz" -> defender.setHp(defender.getHp() - attacker.getDmg() * 2);
                    case "tuz" -> defender.setHp(defender.getHp() - attacker.getDmg());
                }
            }

            case "viz" -> {
                switch (defender.getType()){
                    case "levego" -> defender.setHp(defender.getHp() - attacker.getDmg() * 2);
                    case "fold" -> defender.setHp(defender.getHp() - attacker.getDmg() / 2);
                    case "viz" -> defender.setHp(defender.getHp() - attacker.getDmg());
                    case "tuz" -> defender.setHp(defender.getHp() - attacker.getDmg() * 2);
                }
            }

            case "levego" -> {
                switch (defender.getType()){
                    case "levego" -> defender.setHp(defender.getHp() - attacker.getDmg());
                    case "fold" -> defender.setHp(defender.getHp() - attacker.getDmg() * 2);
                    case "viz" -> defender.setHp(defender.getHp() - attacker.getDmg() * 2);
                    case "tuz" -> defender.setHp(defender.getHp() - attacker.getDmg() / 2);
                }
            }

            case "fold" -> {
                switch (defender.getType()){
                    case "levego" -> defender.setHp(defender.getHp() - attacker.getDmg() * 2);
                    case "fold" -> defender.setHp(defender.getHp() - attacker.getDmg());
                    case "viz" -> defender.setHp(defender.getHp() - attacker.getDmg() / 2);
                    case "tuz" -> defender.setHp(defender.getHp() - attacker.getDmg() * 2);
                }
            }
        }

        if(defender.getHp() <= 0){
            switch (attackingPlayer){
                case "jatekos" -> Battle.enemyTable.remove(defender);
                case "kazamata" -> Battle.playerTable.remove(defender);
            }
        }
    }

    //setting up the decks
    public static void fillUpBattleHands(String playerName, String dungeonName){
        Battle.playerHand = GameData.PlayersList.getFirst().getDeck();

        //dungeon deck setup
        for(Dungeon dungeon : GameData.worldDungeonList){
            if(Objects.equals(dungeon.getName(), dungeonName)){
                Battle.enemyHand = dungeon.getDungeonDeck();
                break;
            }
        }
    }
}
