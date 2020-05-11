package cards;

import com.google.gson.Gson;

import java.io.*;


public class Main {
    public static void main(String[] args) {
        //read from file and create a card
        Gson gson = new Gson();
        try {
            //instantiating a Cards
//            Card ArgentSquire = new Card(Type.MINION, Class.NEUTRAL, "Argent Squire", 1, CardConstants.Rarity.COMMON, "Divine Shield",1,1);
            // Writing the details in a file:
//            FileWriter fileWriter = new FileWriter(ArgentSquire.getName()+".json");
//            fileWriter.write(gson.toJson(ArgentSquire, Card.class));
//            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FileReader reader = new FileReader("src/test/java/cards/Crystallizer.json");
            Card card = gson.fromJson(reader, Card.class);
            System.out.println(card.toString());

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
