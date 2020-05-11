package CLI;

import game.GameState;

import java.util.Scanner;

import static game.GameConstants.*;
import static util.LogInConstants.wrongInput;

public class GameCLI extends BasicCLI {

    private static Scanner scanner = new Scanner(System.in);

    private GameState gameState;

    public GameCLI(GameState gameState) {
        super();
        this.gameState = gameState;
    }

//These menus are so that each menu:
//Step1: takes some input,
//Step2: does some action with respect to input,
//Step3: leads to another menu (back or foeward)

    public void menu() {
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
                gameState.allHeros();
                heroSelectionMenu();
                break;
            case "ls -m -heroes":
                gameState.viewCurrentHero();
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
                gameState.setHero(heroName);
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
                gameState.allHeroCards();
                CardMenu();
                break;
            case "ls -m -cards":
                gameState.currentDeckCards();
                removeCardMenu();
                break;
            case "ls -n -cards":
                gameState.notInDeckCards();
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
                gameState.addToDeck(cardName);
                gameState.removeFromNotInDeck(cardName);
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
                gameState.removeFromDeck(cardName);
                gameState.addToNotInDeck(cardName);
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
                gameState.viewBuyable();
                buyMenu();
                break;
            case "ls -s":
                gameState.viewSellable();
                sellMenu();
                break;
            case "wallet":
                gameState.wallet();
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
                gameState.buyStore(cardName);
                store();
                break;
            case "wallet":
                gameState.wallet();
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
                gameState.sellStore(cardName);
                store();
                break;
            case "wallet":
                gameState.wallet();
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
