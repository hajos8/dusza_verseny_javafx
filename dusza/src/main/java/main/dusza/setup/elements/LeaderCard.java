package main.dusza.setup.elements;

public class LeaderCard extends Card{
    public String powerUp;

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


    public LeaderCard(String name, int dmg, int hp, String type, String powerUp) {
        super(name, dmg, hp, type);

        this.powerUp = powerUp;

        switch(powerUp){
            case "sebez" -> this.dmg *= 2;
            case "elet" -> this.hp *= 2;
        }
    }
}
