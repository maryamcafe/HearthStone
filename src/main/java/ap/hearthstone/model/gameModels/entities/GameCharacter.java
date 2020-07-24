package ap.hearthstone.model.gameModels.entities;

public abstract class GameCharacter  {
    protected int attack;
    protected int attacksAllowed;
    protected int health;
    protected int initialHealth;
//    protected StartOfTurnVisi

    /* If attack is reduced to an amount below zero, it will be zero then. */
    public void addAttack(int i) {
        if(attack+i >= 0){
            attack += i;
        } else {
            attack = 0;
        }
    }

    public void giveTurn(){
        attacksAllowed = 1;

    }

    public void attack(GameCharacter target){
        assert attack>0 : new AssertionError("Character has no attack available.");
        assert attacksAllowed>0 : new AssertionError("Character is not allowed to attack");
        target.receiveDamage(attack);
        receiveDamage(target.getAttack());
        attacksAllowed --;
    }

    public abstract void receiveDamage(int damage);

    public void addHealth(int i) {
        health += i;
    }

    public void restoreHealth(int health) {
        if (this.health + health > initialHealth) {
            this.health = initialHealth;
        } else {
            addHealth(health);
        }
    }

    protected void setAttacksAllowed(int attacksAllowed){
        assert attacksAllowed>=0 : new AssertionError("allowed attacks can nor be less than zero.");
        this.attacksAllowed = attacksAllowed;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public int getHealth() {
        return health;
    }

    public int getInitialHealth() {
        return initialHealth;
    }

    public int getAttacksAllowed() {
        return attacksAllowed;
    }

    public int getAttack() {
        return attack;
    }
}
