package ap.hearthstone.model.gameModels.ability;

public enum AbilityType {
    SPELL,
    Battlecry,  //MINION, WEAPON
    Deathrattle,  //MINION, WEAPON
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
