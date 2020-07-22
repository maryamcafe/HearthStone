package ap.hearthstone.logic.game;

import ap.hearthstone.model.gameModels.cards.Card;
import ap.hearthstone.model.gameModels.cards.MinionCard;
import ap.hearthstone.model.gameModels.cards.SpellCard;
import ap.hearthstone.model.gameModels.cards.WeaponCard;
import ap.hearthstone.utils.ConfigLoader;
import ap.hearthstone.utils.FileManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class CardFileManager extends FileManager {

    private final String cardsURL;
    private final Gson gson;
    private final Type spellSetType, minionSetType, weaponSetType, listType;
    private final Map<String, String> cardNameToHeroMap;
    private final Map<String, List<String>> heroToCardNameMap;

    private final Set<SpellCard> spellCardSet;
    private final Set<MinionCard> minionCardSet;
    private final Set<WeaponCard> weaponCardSet;


    public CardFileManager() {
        cardsURL = ConfigLoader.getInstance().getCardsURL();        //get the address from ConfigLoader
        cardNameToHeroMap = new HashMap<>();
        heroToCardNameMap = new HashMap<>();
        spellCardSet = new HashSet<>();
        minionCardSet = new HashSet<>();
        weaponCardSet = new HashSet<>();
        gson = new Gson();
        spellSetType = new TypeToken<Set<SpellCard>>() {
        }.getType();
        minionSetType = new TypeToken<Set<MinionCard>>() {
        }.getType();
        weaponSetType = new TypeToken<Set<WeaponCard>>() {
        }.getType();
        listType = new TypeToken<List<Card>>() {
        }.getType();
    }

    private void readCards() {
        try (FileReader reader = new FileReader(getFile(cardsURL + "spellCards.json"));
             FileReader reader1 = new FileReader(getFile(cardsURL + "minionCards.json"));
             FileReader reader2 = new FileReader(getFile(cardsURL + "weaponCards.json"))) {
            spellCardSet.addAll(gson.fromJson(reader, spellSetType));
            minionCardSet.addAll(gson.fromJson(reader1, minionSetType));
            weaponCardSet.addAll(gson.fromJson(reader2, weaponSetType));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeCards() {
        try (FileWriter writer = new FileWriter(getFile(cardsURL + "spellCards.json"));
             FileWriter writer1 = new FileWriter(getFile(cardsURL + "minionCards.json"));
             FileWriter writer2 = new FileWriter(getFile(cardsURL + "weaponCards.json"))){
            gson.toJson(spellCardSet, spellSetType, writer);
            gson.toJson(minionCardSet, minionSetType, writer1);
            gson.toJson(weaponCardSet, weaponSetType, writer2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> getCardNameToHeroMap() {
        if (cardNameToHeroMap.size() == 0) {
            initCardNameMap();
        }
        return cardNameToHeroMap;
    }

    public void initCardNameMap() {
        readCards();
        spellCardSet.forEach(card -> cardNameToHeroMap.put(card.getName(), card.getHeroClass().name()));
        minionCardSet.forEach(card -> cardNameToHeroMap.put(card.getName(), card.getHeroClass().name()));
        weaponCardSet.forEach(card -> cardNameToHeroMap.put(card.getName(), card.getHeroClass().name()));
    }

    public Map<String, List<String>> getHeroToCardNameMap() {
        if (heroToCardNameMap.size() == 0) {
            initHeroCardNameMap();
        }
        return heroToCardNameMap;
    }

    private void initHeroCardNameMap() {
        if(cardNameToHeroMap.size() == 0){
            initCardNameMap();
        }
        cardNameToHeroMap.forEach((cardName, heroName) -> {
            if (!heroToCardNameMap.containsKey(heroName)) {
                heroToCardNameMap.put(heroName, new ArrayList<>());
            }
            heroToCardNameMap.get(heroName).add(cardName);
        });
    }

    public void addCard(Card card) {

    }

    public void printCardsMap() {
        System.out.println(cardNameToHeroMap);
    }

    public Set<SpellCard> getSpellCardSet() {
        if(spellCardSet.size() == 0){
            readCards();
        }
        return spellCardSet;
    }
}



