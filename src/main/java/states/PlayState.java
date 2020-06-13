package states;

import CLI.GameCLI;
import UI.Listener;
import control.KeyManager;
import control.Request;
import model.cards.Collection;
import model.cards.Deck;
import model.heros.Hero;
import swing.GamePanel;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayState extends State {

    private List<String> requests;
    private static State instance;

    private PlayState() {
    }


    @Override
    public void run() {
        running = true;
        while (running) {
            //get requests from controller and run them
        }
    }

    @Override
    public void execute(control.Request request) {

    }



    public static State getInstance(){
        if(instance == null){
            instance = new PlayState();
        }
        return instance;
    }

    private void runRequest(String request) {
    }

    public void viewCurrentHero() {
    }

    public void allHeros() {

    }


    public void setHero(String heroName) {
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

    private enum Request{
        SUMMON,
        ATTACK,

    }
}
