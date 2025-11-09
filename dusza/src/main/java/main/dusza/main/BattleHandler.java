package main.dusza.main;

import main.dusza.gameElements.Card;
import main.dusza.gameElements.Dungeon;

import java.util.ArrayList;
import java.util.Objects;

public class BattleHandler {

    public static void playCard(String name, Card card) {
        switch (name){
            case "jatekos" ->{
                BattleRoundManager.playerHand.remove(card);
                BattleRoundManager.playerTable.add(card);

            }
            case "kazamata" -> {
                BattleRoundManager.enemyHand.remove(card);
                BattleRoundManager.enemyTable.add(card);
            }
        }

        BattleRoundManager.logBuilder
                .append("kijatszik")
                .append(";")
                .append(card.getName())
                .append(";")
                .append(card.getDmg())
                .append(";")
                .append(card.getHp())
                .append(";")
                .append(card.getType())
                .append("\n");
    }

    //attack
    public static void attack(String attackingPlayer, Card attacker, Card defender){
        BattleRoundManager.logBuilder
                .append("tamad")
                .append(";")
                .append(attacker.getName())
                .append(";");

        switch (attacker.getType()){
            case "tuz" -> {
                switch (defender.getType()){
                    case "levego" -> {
                            defender.setHp(defender.getHp() - attacker.getDmg() / 2);
                            BattleRoundManager.logBuilder.append((attacker.getDmg() / 2)).append(";");
                    }
                    case "fold", "viz" -> {
                            defender.setHp(defender.getHp() - attacker.getDmg() * 2);
                            BattleRoundManager.logBuilder.append((attacker.getDmg() * 2)).append(";");
                    }
                    case "tuz" -> {
                        defender.setHp(defender.getHp() - attacker.getDmg());
                        BattleRoundManager.logBuilder.append(attacker.getDmg()).append(";");
                    }
                }
            }

            case "viz" -> {
                switch (defender.getType()){
                    case "levego", "tuz" -> {
                        defender.setHp(defender.getHp() - attacker.getDmg() * 2);
                        BattleRoundManager.logBuilder.append((attacker.getDmg() * 2)).append(";");
                    }
                    case "fold" -> {
                        defender.setHp(defender.getHp() - attacker.getDmg() / 2);
                        BattleRoundManager.logBuilder.append((attacker.getDmg() / 2)).append(";");
                    }
                    case "viz" -> {
                        defender.setHp(defender.getHp() - attacker.getDmg());
                        BattleRoundManager.logBuilder.append(attacker.getDmg()).append(";");
                    }
                }
            }

            case "levego" -> {
                switch (defender.getType()){
                    case "levego" -> {
                        defender.setHp(defender.getHp() - attacker.getDmg());
                        BattleRoundManager.logBuilder.append(attacker.getDmg()).append(";");
                    }
                    case "fold", "viz" -> {
                        defender.setHp(defender.getHp() - attacker.getDmg() * 2);
                        BattleRoundManager.logBuilder.append((attacker.getDmg() * 2)).append(";");
                    }
                    case "tuz" -> {
                        defender.setHp(defender.getHp() - attacker.getDmg() / 2);
                        BattleRoundManager.logBuilder.append((attacker.getDmg() / 2)).append(";");
                    }
                }
            }

            case "fold" -> {
                switch (defender.getType()){
                    case "levego", "tuz" -> {
                        defender.setHp(defender.getHp() - attacker.getDmg() * 2);
                        BattleRoundManager.logBuilder.append((attacker.getDmg() * 2)).append(";");
                    }
                    case "fold" -> {
                        defender.setHp(defender.getHp() - attacker.getDmg());
                        BattleRoundManager.logBuilder.append(attacker.getDmg()).append(";");
                    }
                    case "viz" -> {
                        defender.setHp(defender.getHp() - attacker.getDmg() / 2);
                        BattleRoundManager.logBuilder.append((attacker.getDmg() / 2)).append(";");
                    }
                }
            }
        }
        BattleRoundManager.logBuilder
                .append(defender.getName())
                .append(";");

        if(defender.getHp() <= 0){
            BattleRoundManager.logBuilder.append("0").append("\n");

            if(attackingPlayer.equals("kazamata")){
                BattleRoundManager.playerTable.remove(defender);
                if(!BattleRoundManager.playerTable.isEmpty()){
                    // masodik tamadas, ha van meg ellenfel a tablan
                    BattleAI.generateTurn("kazamata");
                }

            }
        }
        else{
            BattleRoundManager.logBuilder.append(defender.getHp()).append("\n");
        }
    }

    //setting up the decks
    public static void fillUpBattleHands(String dungeonName){

        var player = GameData.PlayersList.getFirst();
        var safeDeck  = (player.getDeck() != null) ? player.getDeck() : new ArrayList<Card>();

        var p = GameData.PlayersList.getFirst();
        var deck = p.getDeck();
        if (deck == null) {
            deck = new ArrayList<>();
            p.setDeck(deck);
        }

        // deep-copy deck
        for(Card card : safeDeck){
            BattleRoundManager.playerHand
                    .add(new Card(card.getName(), card.getDmg(), card.getHp(), card.getType()));
        }

        //dungeon deck setup
        for(Dungeon dungeon : GameData.worldDungeonList){
            if(Objects.equals(dungeon.getName(), dungeonName)){
                // deep-copy deck
                for(Card card : dungeon.getDungeonDeck()){
                    BattleRoundManager.enemyHand
                            .add(new Card(card.getName(), card.getDmg(), card.getHp(), card.getType()));
                }
                break;
            }
        }
    }
}
