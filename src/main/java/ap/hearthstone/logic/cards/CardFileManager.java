package ap.hearthstone.logic.cards;

import ap.hearthstone.model.gameModels.cards.*;
import ap.hearthstone.utils.ConfigLoader;
import ap.hearthstone.utils.FileManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class CardFileManager extends FileManager {

    private final String cardsURL;
    private final Map<String, String> cardNameToHeroMap;

    private final Map<String, Card> allCardsMap;
    private final Map<String, Set<String>> heroToCardNameMap;    //TODO change the List<Card> in here back to Set<Card>.

    private final Set<SpellCard> spellCardSet;
    private final Set<MinionCard> minionCardSet;
    private final Set<WeaponCard> weaponCardSet;

    private Map<String, Object> typeToCardsMap;
    private Map<String, Type> typeMap;
    private final Gson gson;

    private static CardFileManager instance;

    /* Using this pattern to avoid excess file loading.     */
    public static CardFileManager getInstance() {
        if (instance == null) {
            instance = new CardFileManager();
        }
        return instance;
    }

    private CardFileManager() {
        cardsURL = ConfigLoader.getInstance().getCardsURL();
        cardNameToHeroMap = new HashMap<>();
        heroToCardNameMap = new HashMap<>();
        allCardsMap = new HashMap<>();
        spellCardSet = new HashSet<>();
        minionCardSet = new HashSet<>();
        weaponCardSet = new HashSet<>();
        gson = new Gson();
        initTypes();
    }

    private void initTypeToCardsMap() {
        typeToCardsMap = new HashMap<>();
        typeToCardsMap.put("spell", spellCardSet);
        typeToCardsMap.put("minion", minionCardSet);
        typeToCardsMap.put("weapon", weaponCardSet);
    }

    private void initTypes() {
        typeMap = new HashMap<>();
        typeMap.put("spell", new TypeToken<Set<SpellCard>>() {
        }.getType());
        typeMap.put("minion", new TypeToken<Set<MinionCard>>() {
        }.getType());
        typeMap.put("weapon", new TypeToken<Set<WeaponCard>>() {
        }.getType());
    }

    public Map<String, String> getCardNameToHeroMap() {
        if (cardNameToHeroMap.size() == 0) {
            initCardToHeroMap();
        }
        return cardNameToHeroMap;
    }

    public void initCardToHeroMap() {
        getSpellCardSet().forEach(card -> cardNameToHeroMap.put(card.getName(), card.getHeroClass().name()));
        getMinionCardSet().forEach(card -> cardNameToHeroMap.put(card.getName(), card.getHeroClass().name()));
        getWeaponCardSet().forEach(card -> cardNameToHeroMap.put(card.getName(), card.getHeroClass().name()));
    }

    public Map<String, Set<String>> getHeroToCardNameMap() {
        if (heroToCardNameMap.size() == 0) {
            initHeroToCardMap();
        }
        return heroToCardNameMap;
    }

    private void initHeroToCardMap() {
        if (cardNameToHeroMap.size() == 0) {
            initCardToHeroMap();
        }
        cardNameToHeroMap.forEach((cardName, heroName) -> {
            if (!heroToCardNameMap.containsKey(heroName)) {
                heroToCardNameMap.put(heroName, new HashSet<>());
            }
            heroToCardNameMap.get(heroName).add(cardName);
        });
    }

    public Card getCard(String cardName) {
        return getAllCardsMap().get(cardName);
    }

    public Map<String, Card> getAllCardsMap() {
        if (allCardsMap.size() == 0) {
            getSpellCardSet().forEach(spellCard -> allCardsMap.put(spellCard.getName(), spellCard));
            getMinionCardSet().forEach(minionCard -> allCardsMap.put(minionCard.getName(), minionCard));
            getWeaponCardSet().forEach(weaponCard -> allCardsMap.put(weaponCard.getName(), weaponCard));
        }
        return allCardsMap;
    }

    private Set<SpellCard> getSpellCardSet() {
        if (spellCardSet.size() == 0) {
            readCards("spell");
        }
        return spellCardSet;
    }

    private Set<MinionCard> getMinionCardSet() {
        if (minionCardSet.size() == 0) {
            readCards("minion");
        }
        return minionCardSet;
    }

    private Set<WeaponCard> getWeaponCardSet() {
        if (weaponCardSet.size() == 0) {
            readCards("weapon");
        }
        return weaponCardSet;
    }

    //Test
    public Object getCardSet(String cardType) {
        readCards(cardType);
        return typeToCardsMap.get(cardType);
    }

    private void readCards(String cardType) {
        try (FileReader reader = new FileReader(getFile(cardsURL + cardType + "Cards.json"))) {
            switch (cardType) {
                case "spell":
                    spellCardSet.addAll(gson.fromJson(reader, typeMap.get(cardType)));
                    break;
                case "minion":
                    minionCardSet.addAll(gson.fromJson(reader, typeMap.get(cardType)));
                    break;
                case "weapon":
                    weaponCardSet.addAll(gson.fromJson(reader, typeMap.get(cardType)));
                    break;
            }  //typeToCardsMap.put(cardType, gson.fromJson(reader, typeMap.get(cardType)));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    private void writeCards(String cardType) {
        try (FileWriter writer = new FileWriter(getFile(cardsURL + cardType + "Cards.json"))) {
            switch (cardType) {
                case "spell":
                    gson.toJson(spellCardSet, typeMap.get(cardType), writer);
                    break;
                case "minion":
                    gson.toJson(minionCardSet, typeMap.get(cardType), writer);
                    break;
                case "weapon":
                    gson.toJson(weaponCardSet, typeMap.get(cardType), writer);
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addCard(Card card) {
        switch (card.getType()) {
            case SPELL:
                spellCardSet.add((SpellCard) card);
                writeCards("spell");
                break;
            case MINION:
                minionCardSet.add((MinionCard) card);
                writeCards("minion");
                break;
            case WEAPON:
                weaponCardSet.add((WeaponCard) card);
                writeCards("weapon");
                break;
        }
    }

    public void printCardsMap() {
        System.out.println(getCardNameToHeroMap());
    }

    public List<String> geDefaultCards() {
        List<String> defaultCards = new LinkedList<>();
        try(Reader reader = new FileReader(getFile(cardsURL + "defaultCards.json"))){
            defaultCards.addAll(gson.fromJson(reader, new TypeToken<List<String>>(){}.getType()));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return defaultCards;
    }


}



