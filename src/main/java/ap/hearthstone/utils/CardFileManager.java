package ap.hearthstone.utils;

//import com.google.gson.Gson;
import ap.hearthstone.model.cards.Card;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CardFileManager {

    private final String cardsURL;
//    private final Gson gson;

    public CardFileManager() {
        //get the address from ConfigLoader
        cardsURL = ConfigLoader.getInstance().getCardsURL();
//        gson = new Gson();
    }

    public Card read(String name) throws IOException {
        FileReader reader = new FileReader(cardsURL+ name +".json");
//        Card readCard = gson.fromJson(reader, Card.class);
        reader.close();
        return null;
    }

    public void write(Card card) {
        try {
            Path path = Paths.get(cardsURL + card.getName() + ".json");
            FileWriter fileWriter = new FileWriter(path.toFile());
//            fileWriter.write(gson.toJson(card, Card.class));
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
