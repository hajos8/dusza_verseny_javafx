package main.dusza.render;

public class Card {
    public String name;
    public int dmg;
    public int hp;
    public String type;
    boolean isLeader = false;

    public String getName() {
        return name;
    }
    public int getDmg() {
        return dmg;
    }
    public int getHp() {
        return hp;
    }
    public String getType() {
        return type;
    }
    public boolean isLeader() {
        return isLeader;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setDmg(int dmg) {
        this.dmg = dmg;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setLeader(boolean leader, String powerUp) {
        switch (powerUp) {
            case "sebzes" -> {
                this.dmg *= 2;
            }
            case "eletero" -> {
                this.hp *= 2;
            }
        }
        isLeader = leader;
    }


    public Card(String name, int dmg, int hp, String type) {
        this.name = name;
        this.dmg = dmg;
        this.hp = hp;
        this.type = type;
        this.isLeader = false;
    }
}
