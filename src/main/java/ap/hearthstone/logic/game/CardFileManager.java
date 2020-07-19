package ap.hearthstone.logic.game;

import ap.hearthstone.model.gameModels.cards.Card;
import ap.hearthstone.model.gameModels.cards.SpellCard;
import ap.hearthstone.utils.ConfigLoader;
import ap.hearthstone.utils.FileManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardFileManager extends FileManager {

    private final String cardsURL;
    private final Gson gson;
    private final Type listType;
    private final Map<String, List<String>> cardsNamesMap;


    public CardFileManager() {
        cardsURL = ConfigLoader.getInstance().getCardsURL();        //get the address from ConfigLoader
        cardsNamesMap = new HashMap<>();
        gson = new Gson();
        listType = new TypeToken<List<String>>() {}.getType();
    }

    private void readCardsNames() {
        try {
            for (String cardTypeName : new String[]{"spell", "minion", "weapon"}) {
                FileReader reader = new FileReader(getFile(cardsURL + cardTypeName + "Cards.json"));
                cardsNamesMap.put(cardTypeName, gson.fromJson(reader, listType));
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeCardsNames(){
        try {
            for (String cardTypeName : new String[]{"spell", "minion", "weapon"}) {
                FileWriter writer = new FileWriter(getFile(cardsURL + cardTypeName + "Cards.json"));
                writer.write(gson.toJson(cardsNamesMap.get(cardTypeName), listType));
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, List<String>> getCardsNamesMap() {
        if (cardsNamesMap.size() == 0) {
            readCardsNames();
        }
        return cardsNamesMap;
    }

    public void addCard(Card card){

    }


    public void printCardsMap() {
        System.out.println(cardsNamesMap);
    }
}



