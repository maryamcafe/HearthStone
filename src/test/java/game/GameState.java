package game;

import CLI.GameCLI;
import cards.Card;
import cards.Collection;
import cards.Hero;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GameState implements State {

    public static Collection collection;
    private Map<String, Hero> OpenHeroes = new HashMap<>();
    private Hero currentHero;
    private GameCLI gameCLI;


    @Override
    public void run(List<Boolean> running) {
        gameCLI = new GameCLI(this);
        gameCLI.menu();
    }

    public void viewCurrentHero() {
        System.out.println(currentHero.toString());
    }

    public void allHeros() {

    }


    public void setHero(String heroName) {
        currentHero =
    }

    public void allHeroCards() {

    }

    public void currentDeckCards() {

    }

    public void notInDeckCards() {

    }

    public void removeFromNotInDeck(String cardName) {
    }

    public void addToDeck(String cardName) {
    }

    public void removeFromDeck(String cardName) {
    }

    public void wallet() {
    }

    public void viewSellable() {
    }

    public void viewBuyable() {
    }

    public void addToNotInDeck(String cardName) {
    }

    public void buyStore(String cardName) {
    }

    public void sellStore(String cardName) {
    }
}
