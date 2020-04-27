import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.lang.reflect.Type;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.Map;

public class UserClassJsonStringToMap {
    private static final Logger logger = LogManager.getLogger(UserClassJsonStringToMap.class);
    private static Object JsonReader;

    public static void main(String[] args) {
        try {
            //first we read from file:
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Type type = new TypeToken<HashMap<String, Person>>() {
            }.getType();

            Reader reader = new FileReader(new File("Person.json"));
//            String jsonString = gson.fromJson(reader, type);

            //Converting to Map:
            Map<String, Person> nameMap = gson.fromJson(reader, type);

            //THEN we add the new member of our Map:)
            nameMap.put("newUser1", new Person("name", "pass4"));
            //CHECK:
            System.out.println(nameMap);
            //GREAT!
            //Now we can search in the file, look for certain keys
            boolean isThereAnyBob = nameMap.containsKey("Bob");
            System.out.println(isThereAnyBob);

            //Writing on the file, using non-appendable WRITERS
            FileWriter fileWriter = new FileWriter("Person.json");
            //JsonWriter jsonWriter = new JsonWriter(fileWriter);
            // gson.toJson(employeeList, type, jsonWriter);
            fileWriter.write(gson.toJson(nameMap, type));
            //closing the FileWriter
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String a = "11";
        int b = Integer.parseInt(a);
        System.out.println(b);
    }

}

