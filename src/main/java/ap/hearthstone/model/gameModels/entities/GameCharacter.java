package ap.hearthstone.model.gameModels.entities;

public abstract class GameCharacter extends GameEntity {

    protected int health;
    protected int attack;
    protected int initialHealth;

    @Override
    protected void addLife(int i) {
        addHealth(i);
    }

    /* If attack is reduced to an amount below zero, it will be zero then. */
    @Override
    public void addAttack(int i) {
        if(attack+i >= 0){
            attack += i;
        } else {
            attack = 0;
        }
    }

    public abstract void addHealth(int i);

    public void restoreHealth(int health) {
        if (this.health + health > initialHealth) {
            this.health = initialHealth;
        } else {
            addHealth(health);
        }
    }

    public void receiveDamage(int damage) {
        addHealth(-damage);
    }

    public boolean isAlive() {
        return health > 0;
    }

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }

    public int getInitialHealth() {
        return initialHealth;
    }
}
