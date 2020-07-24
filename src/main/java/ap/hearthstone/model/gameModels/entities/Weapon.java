package ap.hearthstone.model.gameModels.entities;

public class Weapon{

    private int durability;
    private int attack;

    public Weapon(int attack, int durability) {
        this.attack = attack;
        this.durability = durability;
    }

    public void addAttack(int additional){
        if(attack+additional >= 0){
            attack += additional;
        }
    }

    public void addDurability(int i){
        if(durability+i >= 0){
            durability += i;
        }
    }

    public boolean isAvailable() {
        return durability > 0;
    }

    public int getDurability() {
        return durability;
    }

    public int getAttack() {
        return attack;
    }

    public void use() {
        durability --;
    }


}
