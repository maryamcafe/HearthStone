package ap.hearthstone.logic.cards;

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
    private final Type listType;
    private final Map<String, String> cardNameToHeroMap;


    //TODO change the List<Card> in here back to Set<Card>.
    // (go through collectionMapper and collectionView)
    private final Map<String, List<String>> heroToCardNameMap;
    private final Map<String, Card> allCardsMap;

    private final Set<SpellCard> spellCardSet;
    private final Set<MinionCard> minionCardSet;
    private final Set<WeaponCard> weaponCardSet;

    //TODO: remove final declarations from constructor ans generalize all read, write and get methods.


    private static CardFileManager instance;

    /* Using this pattern to avoid excess file loading.     */
    public static CardFileManager getInstance(){
        if(instance == null){
            instance = new CardFileManager();
        }
        return instance;
    }

    public CardFileManager() {
        cardsURL = ConfigLoader.getInstance().getCardsURL();
        cardNameToHeroMap = new HashMap<>();
        heroToCardNameMap = new HashMap<>();
        allCardsMap = new HashMap<>();
        spellCardSet = new HashSet<>();
        minionCardSet = new HashSet<>();
        weaponCardSet = new HashSet<>();

        listType = new TypeToken<List<Card>>() {}.getType();
    }


    public Map<String, String> getCardNameToHeroMap() {
        if (cardNameToHeroMap.size() == 0) {
            initCardNameMap();
        }
        return cardNameToHeroMap;
    }

    public void initCardNameMap() {
        getSpellCardSet().forEach(card -> cardNameToHeroMap.put(card.getName(), card.getHeroClass().name()));
        getMinionCardSet().forEach(card -> cardNameToHeroMap.put(card.getName(), card.getHeroClass().name()));
        getWeaponCardSet().forEach(card -> cardNameToHeroMap.put(card.getName(), card.getHeroClass().name()));
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

    public Card getCard(String cardName){
        return getAllCardsMap().get(cardName);
    }

    private Map<String, Card> getAllCardsMap() {
        if(allCardsMap.size() == 0){
            getSpellCardSet().forEach(spellCard -> allCardsMap.put(spellCard.getName(), spellCard));
            getMinionCardSet().forEach(minionCard -> allCardsMap.put(minionCard.getName(), minionCard));
            getWeaponCardSet().forEach(weaponCard -> allCardsMap.put(weaponCard.getName(), weaponCard));
        }
        return new HashMap<>(allCardsMap);
    }

    private Set<SpellCard> getSpellCardSet() {
        if(spellCardSet.size() == 0){
            readSpellCards();
        }
        return spellCardSet;
    }

    private Set<MinionCard> getMinionCardSet() {
        if(minionCardSet.size() == 0){
            readMinionCards();
        }
        return minionCardSet;
    }

    private Set<WeaponCard> getWeaponCardSet() {
        if(weaponCardSet.size() == 0){
            readWeaponCards();
        }
        return weaponCardSet;
    }

    private void readSpellCards() {
        try (FileReader reader = new FileReader(getFile(cardsURL + "spellCards.json"))) {
            Type spellSetType = new TypeToken<Set<SpellCard>>() {}.getType();
            spellCardSet.addAll(new Gson().fromJson(reader, spellSetType));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readMinionCards() {
        try (FileReader reader = new FileReader(getFile(cardsURL + "minionCards.json"))) {
            Type minionSetType = new TypeToken<Set<MinionCard>>() {}.getType();
            minionCardSet.addAll(new Gson().fromJson(reader, minionSetType));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readWeaponCards() {
        try (FileReader reader = new FileReader(getFile(cardsURL + "weaponCards.json"))) {
            Type weaponSetType = new TypeToken<Set<WeaponCard>>() {}.getType();
            weaponCardSet.addAll(new Gson().fromJson(reader, weaponSetType));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeSpellCards() {
        try (FileWriter writer = new FileWriter(getFile(cardsURL + "spellCards.json"))){
            Gson gson = new Gson();
            Type spellSetType = new TypeToken<Set<SpellCard>>() {}.getType();
            gson.toJson(spellCardSet, spellSetType, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeMinionCards() {
        try (FileWriter writer1 = new FileWriter(getFile(cardsURL + "minionCards.json"))){
            Type minionSetType = new TypeToken<Set<MinionCard>>() {}.getType();
            new Gson().toJson(minionCardSet, minionSetType, writer1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeWeaponCards() {
        try (FileWriter writer = new FileWriter(getFile(cardsURL + "weaponCards.json"))){
            Gson gson = new Gson();
            Type weaponSetType = new TypeToken<Set<WeaponCard>>() {}.getType();
            gson.toJson(weaponCardSet, weaponSetType, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addCard(Card card) {
        switch (card.getType()){
            case SPELL:
                spellCardSet.add((SpellCard) card);
                writeSpellCards();
                break;
            case MINION:
                minionCardSet.add((MinionCard) card);
                writeMinionCards();
                break;
            case WEAPON:
                weaponCardSet.add((WeaponCard) card);
                writeWeaponCards();
                break;
        }
    }

    public void printCardsMap() {
        System.out.println(getCardNameToHeroMap());
    }
}



