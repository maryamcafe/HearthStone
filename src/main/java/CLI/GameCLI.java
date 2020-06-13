package CLI;

import states.PlayState;
import states.State;

import java.util.Scanner;

import static states.GameConstants.*;
import static util.LogInConstants.wrongInput;

public class GameCLI extends CLI {

    private static Scanner scanner = new Scanner(System.in);

    private State playState;

    public GameCLI(State playState) {
        super();
        this.playState = playState;
    }



    public void run() {
        menu();
    }
//These menus are so that each menu:
//Step1: takes some input,
//Step2: does some action with respect to input,

//Step3: leads to another menu (back or foeward)

    private void menu() {
        String input = scanner.nextLine();
        switch (input.trim().toLowerCase()) {
            case "collections":
                System.out.println(collectionMenuHelp);
                heroMenu();
                break;
            case "store":
                store();
                break;
            case "help":
                System.out.println(gameMainMenu);
                menu();
                break;
            case "exit":
                exit();
                break;
            case "exit -a":
                exitAll();
                break;
            default:
                System.out.println(wrongInput);
                menu();
                break;
        }
    }


    private void heroMenu() {
        System.out.println("View your Heros");
        String input = scanner.nextLine();
        switch (input.trim().toLowerCase()) {
            case "ls -a -heroes":
//                playState.allHeros();
                heroSelectionMenu();
                break;
            case "ls -m -heroes":
//                playState.viewCurrentHero();
                heroMenu();
                break;
            case "back":
                menu();
                break;
            case "help":
                System.out.println(heroMenuHelp);
                heroMenu();
                break;
            default:
                System.out.println(wrongInput);
                heroMenu();
                break;
        }
    }


    private void heroSelectionMenu() {
        System.out.println("Select Hero");
        String input = scanner.next();
        switch (input.trim().toLowerCase()) {
            case "select":
                String heroName = scanner.next();
//                playState.setHero(heroName);
                CardMenu();
                break;
            case "back":
                heroMenu();
                break;
            case "help":
                System.out.println(heroSelectionHelp);
                heroSelectionMenu();
                break;
            default:
                System.out.println(wrongInput);
                heroSelectionMenu();
                break;
        }
    }
    //when a hero is chosen, player can Manage his cards.

    private void CardMenu() {
        System.out.println("Cards Menu");
        String input = scanner.nextLine();
        switch (input.trim().toLowerCase()) {
            case "ls -a -cards":
//                playState.allHeroCards();
                CardMenu();
                break;
            case "ls -m -cards":
//                playState.currentDeckCards();
                removeCardMenu();
                break;
            case "ls -n -cards":
//                playState.notInDeckCards();
                addCardMenu();
                break;
            case "back":
                heroSelectionMenu();
                break;
            case "help":
                System.out.println(CardMenuHelp);
                CardMenu();
                break;
            default:
                System.out.println(wrongInput);
                CardMenu();
                break;
        }
    }

    private void addCardMenu() {
        System.out.println("Edit Your Deck");
        String input = scanner.next();
        switch (input.trim().toLowerCase()) {
            case "remove":
                String cardName = scanner.next();
//                playState.addToDeck(cardName);
//                playState.removeFromNotInDeck(cardName);
                CardMenu();
                break;
            case "back":
                CardMenu();
                break;
            case "help":
                System.out.println(addCardHelp);
                addCardMenu();
                break;
            default:
                System.out.println(wrongInput);
                addCardMenu();
                break;
        }
    }


    private void removeCardMenu() {
        System.out.println("Edit Your Deck");
        String input = scanner.next();
        switch (input.trim().toLowerCase()) {
            case "remove":
                String cardName = scanner.next();
//                playState.removeFromDeck(cardName);
//                playState.addToNotInDeck(cardName);
                CardMenu();
                break;
            case "back":
                CardMenu();
                break;
            case "help":
                System.out.println(removeCardHelp);
                removeCardMenu();
                break;
            default:
                System.out.println(wrongInput);
                removeCardMenu();
                break;
        }
    }


    private void store() {
        System.out.println("Store");
        String input = scanner.nextLine();
        switch (input.trim().toLowerCase()) {
            case "ls -b":
//                playState.viewBuyable();
                buyMenu();
                break;
            case "ls -s":
//                playState.viewSellable();
                sellMenu();
                break;
            case "wallet":
//                playState.wallet();
                store();
                break;
            case "help":
                System.out.println(storeHelp);
                removeCardMenu();
                break;
            case "back":
                menu();
                break;
            default:
                System.out.println(wrongInput);
                store();
                break;
        }
    }

    private void buyMenu() {
        System.out.println("Buy Card");
        String input = scanner.next();
        switch (input.trim().toLowerCase()) {
            case "buy":
                String cardName = scanner.next();
//                playState.buyStore(cardName);
                store();
                break;
            case "wallet":
//                playState.wallet();
                buyMenu();
                break;
            case "back":
                store();
                break;
            case "help":
                System.out.println(buyHelp);
                buyMenu();
                break;
            default:
                System.out.println(wrongInput);
                buyMenu();
                break;
        }
    }


    private void sellMenu() {
        System.out.println("Sell Card");
        String input = scanner.next();
        switch (input.trim().toLowerCase()) {
            case "sell":
                String cardName = scanner.next();
//                playState.sellStore(cardName);
                store();
                break;
            case "wallet":
//                playState.wallet();
                sellMenu();
                break;
            case "back":
                store();
                break;
            case "help":
                System.out.println(sellHelp);
                sellMenu();
                break;
            default:
                System.out.println(wrongInput);
                sellMenu();
                break;
        }
    }

}
