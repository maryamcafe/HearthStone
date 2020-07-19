package ap.hearthstone.model.gameModels.entities;

public class Weapon extends GameEntity{

    private int attack, durability;

    public Weapon(int attack, int durability) {
        this.attack = attack;
        this.durability = durability;
    }

    @Override
    protected void addLife(int i) {
        addDurability(i);
    }

    @Override
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

    public int getAttack() {
        return attack;
    }

    public int getDurability() {
        return durability;
    }
}
