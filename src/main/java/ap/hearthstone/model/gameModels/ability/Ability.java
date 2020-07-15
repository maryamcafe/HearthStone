package ap.hearthstone.model.gameModels.ability;

public class Ability {
    private AbilityType type;
    private Target target;
    private AbilityAction action;

    public Ability(AbilityType type, Target target, AbilityAction action) {
        this.type = type;
        this.target = target;
        this.action = action;
    }

    public AbilityType getType() {
        return type;
    }

    public Target getTarget() {
        return target;
    }

    public AbilityAction getAction() {
        return action;
    }
}
