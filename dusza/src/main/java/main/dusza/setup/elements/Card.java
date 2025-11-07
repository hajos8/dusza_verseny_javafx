package main.dusza.setup.elements;

public class Card {
    public String name;
    public int dmg;
    public int hp;
    public String type;

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

    public Card(String name, int dmg, int hp, String type) {
        this.name = name;
        this.dmg = dmg;
        this.hp = hp;
        this.type = type;
    }
}
