package ap.hearthstone.model.gameModels.entities;

public abstract class GameEntity {

    protected void addLifeAttack(int life, int attack){
        addLife(life);
        addAttack(attack);
    }

    protected abstract void addAttack(int i);

    protected abstract void addLife(int i);

}
