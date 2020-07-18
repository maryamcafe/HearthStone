package ap.hearthstone.model.gameModels.util;

import ap.hearthstone.model.gameModels.HeroClass;
import ap.hearthstone.model.gameModels.cards.Card;
import ap.hearthstone.model.gameModels.cards.MinionCard;
import ap.hearthstone.model.gameModels.cards.SpellCard;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

public class CardGenerator {
    public static void main(String[] args) {
        Card[] cards = new Card[37];
        cards[1] = new SpellCard(HeroClass.MAGE, "Polymorph", 4,
                Card.Rarity.FREE, "transform a minion into a 1/1 sheep");

        cards[2] = new MinionCard(HeroClass.MAGE, "Khadgar", 2,
                Card.Rarity.LEGENDARY, "Your cards that summon minions summon twice as many.",
                2,2);

        cards[3] = new SpellCard(HeroClass.ROGUE, "Friendly Smith", 1,
                Card.Rarity.FREE, "Discover a weapon from any class." +
                "Add it to your deck with +2/+2"); //note that it does'nt add to your hand, but to your deck.

        cards[4] = new MinionCard(HeroClass.ROGUE, "Pharaoh Cat", 1,
                Card.Rarity.COMMON, "Battlecry: Add a random Reborn minion to your hand.",
                1, 1);

        cards[5] = new MinionCard(HeroClass.WARLOCK, "Dreadscale", (byte) 3,
                Card.Rarity.LEGENDARY, "At the end of your turn, deal 1 damage to all other minions.",
                4, 2);

        cards[6] = new MinionCard(HeroClass.WARLOCK, "Expired Merchant", 2,
                Card.Rarity.RARE, "Battlecry: Discard your highest Cost card. Deathrattle: Add 2 copies of it to your hand.",
                2, 1);

        cards[7] = new MinionCard(HeroClass.HUNTER, "Swamp King Dred", 7,
                Card.Rarity.LEGENDARY, "After your opponent plays a minion, attack it.",
                9, 9);

        cards[8] = new SpellCard(HeroClass.HUNTER, "Rapid Fire", 1,
                Card.Rarity.COMMON, "Twinspell: Deal 1 damage.");
//        cards[9] = new SpellCard(HeroClass.PALADIN, "GnomishArmyKnife", 5,
//                Card.Rarity.FREE, "Give a minion Charge, Windfury, Divine Shield, Lifesteal, Poisonous, Taunt, and Stealth.");

        cards[9] = new MinionCard(HeroClass.PRIEST, "High Priest Amet", 4,
                Card.Rarity.LEGENDARY, "Whenever you summon a minion, set its Health equal to this minion's.",
                2, 7);

        cards[10] = new SpellCard(HeroClass.PRIEST, "Lazul's Scheme", 0,
                Card.Rarity.EPIC, "Reduce the Attack of an enemy minion by 1 until your next turn. (Upgrades each turn!)");

        cards[11] = new SpellCard(HeroClass.NEUTRAL, "Learn Draconic", 1,
                Card.Rarity.COMMON, "Sidequest: Spend 8 Mana on spells. Reward: Summon a 6/6 Dragon.");

        cards[12] = new MinionCard(HeroClass.NEUTRAL, "Draconic Emissary", 6,
                Card.Rarity.FREE, "", 6, 6);

        cards[13] = new SpellCard(HeroClass.NEUTRAL, "Strength in Numbers", 1,
                Card.Rarity.COMMON, "Sidequest: Spend 10 Mana on minions.\n" +
                "Reward: Summon a minion from your deck.");

        cards[14] = new SpellCard(HeroClass.NEUTRAL, "Book of Specters", 2,
                Card.Rarity.EPIC, "Draw 3 cards. Discard any spells drawn.");

        cards[15] = new SpellCard(HeroClass.NEUTRAL, "Pharaoh’s Blessing", 6,
                Card.Rarity.RARE, "Give a minion +4/+4, Divine Shield, and Taunt.");

        cards[14] = new SpellCard(HeroClass.NEUTRAL, "Swarm of Locusts", 6,
                Card.Rarity.RARE, "Summon seven 1/1 Locusts with Rush");

        cards[15] = new MinionCard(HeroClass.NEUTRAL, "Locust", 1,
                Card.Rarity.FREE, "Rush", 1, 1);

        cards[16] = new SpellCard(HeroClass.NEUTRAL, "Sprint", 7,
                Card.Rarity.FREE, "Draw 4 cards.");

        cards[17] = new MinionCard(HeroClass.NEUTRAL, "Sathrovarr", 9,
                Card.Rarity.LEGENDARY, "Choose a friendly minion. Add a copy of it to your hand, deck and battlefield.",
                5,5);

        cards[18] = new MinionCard(HeroClass.NEUTRAL, "Tomb Warden", 8,
                Card.Rarity.RARE, "Battlecry: Summon a copy of this minion.",
                3, 6);

        cards[19] = new MinionCard(HeroClass.NEUTRAL,"Security Rover", 6,
                Card.Rarity.RARE, "Whenever this minion takes damage, summon a 2/3 Mech with Taunt.",
                2, 6);
         cards[20] = new MinionCard(HeroClass.NEUTRAL, "Guard Bot", 2,
                 Card.Rarity.FREE, "Taunt", 2,3);

         cards[21] = new MinionCard(HeroClass.NEUTRAL, "Curio Collector", 5,
                 Card.Rarity.RARE, "Whenever you draw a card, gain +1/+1",
                 4, 4);

         cards[22] = new SpellCard(HeroClass.NEUTRAL, "Humility", 1,
                 Card.Rarity.FREE, "Change a minion's Attack to 1.");

         cards[23] = new SpellCard(HeroClass.NEUTRAL, "Shadow Word: Pain", 2,
                 Card.Rarity.FREE, "Destroy a minion with 3 or less Attack.");

         cards[24] = new MinionCard(HeroClass.NEUTRAL,"Fire Hawk", 3,
                 Card.Rarity.COMMON, "Battlecry: Gain +1 Attack for each card in your opponent's hand.",
                 1, 3);

        writeCardsArrayToFile(Arrays.asList(cards));

        Map<String, Card> cardMap = new HashMap<>();
        Arrays.stream(cards).filter(Objects::nonNull).forEach(card -> cardMap.put(card.getName(), card));
        writeCardsMapToFile(cardMap);
    }

    public static void writeCardsArrayToFile(List<Card> cardList) {
        Type type = new TypeToken<List<Card>>() {
        }.getType();
        Gson gson = new Gson();
        File file = new File("src/main/resources/collection/cards/AllCardsArray.json");
        try {
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write(gson.toJson(cardList, type));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeCardsMapToFile(Map<String, Card> cardcardMap) {
        Type type = new TypeToken<Map<String, Card>>() {
        }.getType();
        Gson gson = new Gson();
        File file = new File("src/main/resources/collection/cards/AllCardsMap.json");
        try {
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write(gson.toJson(cardcardMap, type));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
