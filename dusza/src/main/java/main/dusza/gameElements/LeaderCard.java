package main.dusza.gameElements;

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
    public String getPowerUp() {
        return powerUp;
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
    public void setPowerUp(String powerUp) {
        this.powerUp = powerUp;
    }

    public LeaderCard(String name, int dmg, int hp, String type, String newName, String powerUp) {
        super(name, dmg, hp, type);

        this.name = newName;
        this.powerUp = powerUp;

        switch(powerUp){
            case "sebzes" -> this.dmg *= 2;
            case "eletero" -> this.hp *= 2;
        }
    }
}
