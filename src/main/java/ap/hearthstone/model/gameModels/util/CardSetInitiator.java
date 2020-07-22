package ap.hearthstone.model.gameModels.util;

import ap.hearthstone.model.gameModels.HeroClass;
import ap.hearthstone.model.gameModels.ability.Ability;
import ap.hearthstone.model.gameModels.ability.AbilityAction;
import ap.hearthstone.model.gameModels.ability.Target;
import ap.hearthstone.model.gameModels.cards.Card;
import ap.hearthstone.model.gameModels.cards.MinionCard;
import ap.hearthstone.model.gameModels.cards.SpellCard;
import ap.hearthstone.model.gameModels.cards.WeaponCard;
import com.fasterxml.jackson.databind.node.MissingNode;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

public class CardSetInitiator {

    static Card[] cards = new Card[38];

    static Card[] spells = new Card[15];
    static Card[] minions = new Card[20];
    static Card[] weapons = new Card[3];

    public static Card[] allCards() {
        cards[0] = new MinionCard(HeroClass.NEUTRAL, "Wisp", 0,
                Card.Rarity.COMMON, "", 1, 1);

        cards[1] = new SpellCard(HeroClass.MAGE, "Polymorph", 4,
                Card.Rarity.FREE, "transform a minion into a 1/1 sheep");

        cards[2] = new MinionCard(HeroClass.MAGE, "Khadgar", 2,
                Card.Rarity.LEGENDARY, "Your cards that summon minions summon twice as many.",
                2, 2);

        cards[3] = new SpellCard(HeroClass.ROGUE, "Friendly Smith", 1,
                Card.Rarity.FREE, "Discover a weapon from any class." +
                "Add it to your deck with +2/+2"); //note that it does'nt add to your hand, but to your deck.

        cards[4] = new MinionCard(HeroClass.ROGUE, "Pharaoh Cat", 1,
                Card.Rarity.COMMON, "Battlecry: Add a random Reborn minion to your hand.",
                1, 1);

        cards[5] = new SpellCard(HeroClass.WARLOCK, "Siphon Soul", 6,
                Card.Rarity.RARE, "Destroy a minion. Restore 3 Health to your hero.");

        cards[6] = new MinionCard(HeroClass.WARLOCK, "Dreadscale", (byte) 3,
                Card.Rarity.LEGENDARY, "At the end of your turn, deal 1 damage to all other minions.",
                4, 2);

        cards[7] = new SpellCard(HeroClass.HUNTER, "Rapid Fire", 1,
                Card.Rarity.COMMON, "Twinspell: Deal 1 damage.");

        cards[8] = new MinionCard(HeroClass.HUNTER, "Swamp King Dred", 7,
                Card.Rarity.LEGENDARY, "After your opponent plays a minion, attack it.",
                9, 9);

        cards[9] = new SpellCard(HeroClass.PRIEST, "Apotheosis", 3,
                Card.Rarity.COMMON, "Give a minion +2/+3 and Lifesteal.");

        cards[10] = new MinionCard(HeroClass.PRIEST, "High Priest Amet", 4,
                Card.Rarity.LEGENDARY, "Whenever you summon a minion, set its Health equal to this minion's.",
                2, 7);

        cards[11] = new SpellCard(HeroClass.NEUTRAL, "Learn Draconic", 1,
                Card.Rarity.COMMON, "Sidequest: Spend 8 Mana on spells. Reward: Summon a 6/6 Dragon.");

        cards[12] = new MinionCard(HeroClass.NEUTRAL, "Draconic Emissary", 6,
                Card.Rarity.FREE, "", 6, 6);

        cards[13] = new SpellCard(HeroClass.NEUTRAL, "Strength in Numbers", 1,
                Card.Rarity.COMMON, "Sidequest: Spend 10 Mana on minions.\n" +
                "Reward: Summon a minion from your deck.");

        cards[14] = new MinionCard(HeroClass.NEUTRAL, "Security Rover", 6,
                Card.Rarity.RARE, "Whenever this minion takes damage, summon a 2/3 Mech with Taunt.",
                2, 6);

        cards[15] = new SpellCard(HeroClass.NEUTRAL, "Swarm of Locusts", 6,
                Card.Rarity.RARE, "Summon seven 1/1 Locusts with Rush");

        cards[16] = new MinionCard(HeroClass.NEUTRAL, "Locust", 1,
                Card.Rarity.FREE, "Rush", 1, 1);

        cards[17] = new SpellCard(HeroClass.NEUTRAL, "Sprint", 7,
                Card.Rarity.FREE, "Draw 4 cards.");

        cards[18] = new MinionCard(HeroClass.NEUTRAL, "Sathrovarr", 9,
                Card.Rarity.LEGENDARY, "Choose a friendly minion. Add a copy of it to your hand, deck and battlefield.",
                5, 5);

        cards[19] = new SpellCard(HeroClass.NEUTRAL, "Pharaoh's Blessing", 6,
                Card.Rarity.RARE, "Give a minion +4/+4, Divine Shield, and Taunt.");

        cards[20] = new MinionCard(HeroClass.NEUTRAL, "Tomb Warden", 8,
                Card.Rarity.RARE, "Battlecry: Summon a copy of this minion.",
                3, 6);

        cards[21] = new SpellCard(HeroClass.NEUTRAL, "Book of Specters", 2,
                Card.Rarity.EPIC, "Draw 3 cards. Discard any spells drawn.");

        cards[22] = new MinionCard(HeroClass.NEUTRAL, "Guard Bot", 2,
                Card.Rarity.FREE, "Taunt", 2, 3);

        cards[23] = new SpellCard(HeroClass.NEUTRAL, "Shadow Word: Pain", 2,
                Card.Rarity.FREE, "Destroy a minion with 3 or less Attack.");

        cards[24] = new MinionCard(HeroClass.NEUTRAL, "Fire Hawk", 3,
                Card.Rarity.COMMON, "Battlecry: Gain +1 Attack for each card in your opponent's hand.",
                1, 3);

        cards[25] = new SpellCard(HeroClass.NEUTRAL, "Humility", 1,
                Card.Rarity.FREE, "Change a minion's Attack to 1.");

        cards[26] = new MinionCard(HeroClass.NEUTRAL, "Curio Collector", 5,
                Card.Rarity.RARE, "Whenever you draw a card, gain +1/+1",
                4, 4);

        cards[25] = new SpellCard(HeroClass.NEUTRAL, "Mirror Image Spell", 1,
                Card.Rarity.FREE, "Summon two 0/2 minions with Taunt.",
                new Ability(Ability.Type.Summon, "mirrorImage", 2));

        Ability a = new Ability(Ability.Type.Summon, "mirrorImage", 2);
//        a.getAction().act(); //it is not initiated at all!
        AbilityAction abilityAction;

        cards[26] = new MinionCard(HeroClass.NEUTRAL, "Mirror Image", 0,
                Card.Rarity.FREE, "Taunt", 0, 2, new Ability(Ability.Type.Taunt));

        cards[27] = new SpellCard(HeroClass.NEUTRAL, "Overflow", 7,
                Card.Rarity.RARE, "Restore 5 Health to all characters. Draw 5 cards.");

        cards[28] = new MinionCard(HeroClass.NEUTRAL, "Akali", 8,
                Card.Rarity.LEGENDARY, "Rush, Overkill: Draw a Rush minion and give it +5/+5.",
                5, 5);

        cards[29] = new MinionCard(HeroClass.NEUTRAL, "Serpent Ward", 2,
                Card.Rarity.RARE, "At the end of your turn, deal 2 damage to the enemy hero.",
                0, 2);

        cards[30] = new MinionCard(HeroClass.NEUTRAL, "Gyrocopter", 6,
                Card.Rarity.COMMON, "Rush, Windfury", 4, 5);

        cards[31] = new MinionCard(HeroClass.NEUTRAL, "Arena Patron", 5,
                Card.Rarity.RARE, "Overkill: Summon another Arena Patron.", 3, 3);

        cards[32] = new MinionCard(HeroClass.NEUTRAL, "Omega Medic", 3,
                Card.Rarity.RARE, "Battlecry: If you have 10 Mana Crystals, restore 10 Health to your hero.",
                3, 4);

        cards[33] = new MinionCard(HeroClass.NEUTRAL, "Bone Wraith", 4,
                Card.Rarity.COMMON, "Taunt, Reborn", 2, 5);

        cards[34] = new WeaponCard(HeroClass.NEUTRAL, "Ashbringer", 5,
                Card.Rarity.FREE, "", 5, 3);

        cards[35] = new WeaponCard(HeroClass.NEUTRAL, "Heavy Axe", 1,
                Card.Rarity.COMMON, "", 1, 3);

        cards[36] = new WeaponCard(HeroClass.NEUTRAL, "Blood Fury", 3,
                Card.Rarity.COMMON, "", 3, 8);

        cards[37] = new SpellCard(HeroClass.NEUTRAL, "Circle of Healing", 0,
                Card.Rarity.COMMON, "Restore 4 Health to ALL minions.");

        for (int i = 0; i < 14; i++) {
            minions[i] = cards[2 * i];
            spells[i] = cards[2 * i + 1];
        }
        spells[14] = cards[37];

        System.arraycopy(cards, 28, minions, 14, 6);

        System.arraycopy(cards, 34, weapons, 0, 3);

        return cards; //this is not useful for us. we need cards in different types.
    }

    public static void main(String[] args) {
        allCards();
//        writeCardsArrayToFile(Arrays.asList(allCards()), "allCards");
        writeCardsArrayToFile(Arrays.asList(spells), "spellCards");
//        writeCardsArrayToFile(Arrays.asList(minions), "minionCards");
//        writeCardsArrayToFile(Arrays.asList(weapons), "weaponCards");

    }

    /*
    It is not a good idea to serialize abstract classes such as Card.
    The Only way to use it is through separate json conversions, using each of MinionCard, SpellCard
     and WeaponCard types; which is very hard when all types are in one file.
     */

    public static void writeCardsArrayToFile(List<Card> cardList, String fileName) {
        Type type = new TypeToken<List<Card>>() {
        }.getType();
        Gson gson = new Gson();
        File file = new File("src/main/resources/collection/cards/" + fileName + ".json");
        try {
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write(gson.toJson(cardList, type));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    the file created here is not readable.
     */
    public void writeCardsMapToFile(Map<String, Card> cardMap) {
        Type type = new TypeToken<Map<String, Card>>() {
        }.getType();
        Gson gson = new Gson();
        File file = new File("src/main/resources/collection/cards/AllCardsMap.json");
        try {
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write(gson.toJson(cardMap, type));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
