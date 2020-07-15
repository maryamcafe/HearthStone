package ap.hearthstone.model.gameModels.entities;

public abstract class GameCharacter {

    protected int health;
    protected int attack;

    protected abstract void receiveHealth(byte health);

    protected abstract void receiveDamage(byte damage);

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }
}
