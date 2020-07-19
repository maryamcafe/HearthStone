package ap.hearthstone.model.gameModels.entities;

public class Minion extends GameCharacter {

    private final int initialHealth;
    private boolean overkill = false;

    public Minion(int attack, int initialHealth) {
        this.attack = attack;
        this.initialHealth = initialHealth;
        health = initialHealth;
    }

    @Override
    public void addHealth(int i) {
        if (health + i >= 0) {
            health += i;
        } else {
            health = 0;
            overkill = true;
        }
    }

    public boolean isOverkill() {
        return overkill;
    }

}
