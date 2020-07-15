package ap.hearthstone.model.gameModels.util;

import ap.hearthstone.model.gameModels.cards.Card;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CardLoader {

    private Gson gson;

    public CardLoader() {
        gson = new Gson();
    }

    public List<Card> loadCards(List<String> names) { //load the cards with their names.
        List<Card> cards = names.stream().map(this::load).collect(Collectors.toList());
        return cards;
    }

    private Card load(String name) {
        Card c = null;
        try {
            String fileName = name.replace(" ", "_");
            FileReader reader = new FileReader(fileName + ".json");
            c = gson.fromJson(reader, Card.class);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return c;
    }
}
