import java.util.Scanner;
import users.*;
public class PlayState {

    //so we have several states: store - collections -
    // in each of above states, you have a Scanner, and you get input from user
    // you also have a
    public static void main(String[] args) {
//        Player player = new Player();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        switch(input.toLowerCase()){
            case "exit":
                exit();
                break;
            case "exit -a":
                exitA();
                break;
            case "delete-player":
//                player.delete();
                break;
//                case ""

        }

    }

    public static void exit(){
        //this exits the game
    }

    public static void exitA() {
        //this exits All, closing the program
    }
}
