package ap.hearthstone.model.gameModels.ability;

import java.util.List;

public class Ability {
    private Type type;
    private Target target;
    private AbilityAction action;
    private String cardName;
    private int number;

    public Ability(Type type, Target target, AbilityAction action) {
        this.type = type;
        this.target = target;
        this.action = action;
    }

    public Ability(Type type){
        this.type = type;
    }

    public Ability(Type type, String cardName, int number){
        this.type = type;
        this.cardName = cardName;
    }

    public Type getType() {
        return type;
    }

    public Target getTarget() {
        return target;
    }

    public AbilityAction getAction() {
        return action;
    }

    public enum Type{
        Summon,
//        SetAttack,
//        ReduceAttack,
//        giveAttack,
//        SetHealth,
//        ReduceHealth,
//        Heal,
        AddCard,
        Battlecry,  //MINION, WEAPON
        Deathrattle,  //MINION, WEAPON
        EndOfTurn,
        StartOfTurn,
        Discover,
        Rush, // MINION
        Charge,  //MINION
        DivineShield,  //MINION
        Taunt,  //MINION
        LifeSteal,  //MINION, SPELL, WEAPON
        Poisonous,  //WEAPON, MINION
        Overkill,  //MINION, WEAPON, SPELL (damage related)
        TwinSpell,  //SPELL
        Reborn,  //MINION
        Outcast, //MINION, SPELL, WEAPON
        Dormant,  //MINION
        Inspire, //MINION, SPELL, WEAPON
        Windfury, //CHRACTER
        MegaWindfury, //CHARACTER
        Restore, // HERO POWER, SPELL, BATTLECRY, DEATHRATTLE, etc.
        Echo,  //MINION, SPELL, WEAPON
        Repeatable,
        Stealth
    }
}
