package ap.hearthstone.model.gameModels.entities;

public class Minion extends GameCharacter {

    private final int initialHealth;
    private boolean overkill = false;

    public Minion(int attack, int initialHealth) {
        this.attack = attack;
        this.initialHealth = initialHealth;
        health = initialHealth;
    }

    public void receiveHealth(byte health) {
        if(this.health + health <= initialHealth){
            this.health += health;
        } else {
            restoreInitialHealth();
        }
    }

    public void restoreInitialHealth() {
        health = initialHealth;
    }

    public void receiveDamage(byte damage) {
        if(health-damage < 0){
            health = 0;
            overkill = true;  //this is only for minions. therefore we can not move this method to super class.
        } else {
            health -= damage;
        }
    }

    public void setHealth(byte health){
        if(health < initialHealth && health >= 0){
            this.health = health;
        }
    }

    public void setAttack(byte attack){
        if(attack >= 0){
            this.attack = attack;
        }
    }

    public boolean isAlive(){
        return health > 0;
    }

    public boolean isOverkill() {
        return overkill;
    }
}
