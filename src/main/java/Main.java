import com.google.gson.Gson;

import java.io.Console;
import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String a = " again";

        switch (a.trim().toLowerCase()){
            case "back":
                System.out.println("Back!");
                break;
            case "again":
                System.out.println("Again!");
                break;
            default:
                System.out.println("default");
                break;
        }

    }
}
