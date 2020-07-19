package ap.hearthstone.model.gameModels.ability;

import ap.hearthstone.model.gameModels.entities.GameEntity;

import java.util.List;

public class Target {

    private List<GameEntity> entityList;
    private Target.Type type;

    public Target(List<GameEntity> entityList) {
        this.entityList = entityList;
    }

    public Target(Target.Type type) {
        this.type = type;
    }

    public List<GameEntity> getEntityList() {
        return entityList;
    }

    public Target.Type getType() {
        return type;
    }

    public enum Type {
        ALL,
        FRIENDLY,
        ENEMY,
        ENEMY_MINION,
        ENEMY_HERO,
        YOUR_MINION,
        YOUR_HERO,
        ENEMY_HAND,
        YOUR_HAND,
        NONE
    }
}
