package ap.hearthstone.model.gameModels.cards;

import ap.hearthstone.interfaces.Playable;
import ap.hearthstone.model.gameModels.HeroClass;
import ap.hearthstone.model.gameModels.ability.Ability;
import ap.hearthstone.interfaces.ManaHandler;

import java.util.Arrays;
import java.util.List;


public abstract class Card implements ManaHandler, Playable{
    protected CardType type;
    protected final HeroClass heroClass;
    protected final String name;
    protected int mana;
    protected final Rarity rarity;
    protected final String descriptionText;
    protected List<Ability> abilities;

    public Card(CardType type, HeroClass heroClass, String name, int mana, Rarity rarity, String descriptionText) {
        this.type = type;
        this.heroClass = heroClass;
        this.name = name;
        this.rarity = rarity;
        this.descriptionText = descriptionText;
        if(mana <0 ){
            throw new IllegalArgumentException("mana should be greater than zero.");
        } else {
            this.mana = mana;
        }
    }

    public Card(CardType type, HeroClass heroClass, String name, int mana, Rarity rarity, String descriptionText, Ability... abilities) {
        this(type, heroClass, name, mana, rarity, descriptionText);
        this.abilities = Arrays.asList(abilities);
    }

    @Override
    public void reduceMana(int i) {
        if (mana - i < 0) {
            mana = 0;
        } else {
            mana -= i;
        }
    }

    @Override
    public void increaseMana(int i) {
        mana += i;
    }

    //GETTERS

    public CardType getType() {
        return type;
    }

    public HeroClass getHeroClass() {
        return heroClass;
    }

    public String getName() {
        return name;
    }

    public int getMana() {
        return mana;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public String getDescriptionText() {
        return descriptionText;
    }

    public List<Ability> getAbilities() {
        return abilities;
    }

    public enum Rarity {
        FREE,
        COMMON,
        RARE,
        EPIC,
        LEGENDARY
    }

}





