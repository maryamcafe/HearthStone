package ap.hearthstone.model.gameModels;

import ap.hearthstone.logic.cards.DeckFileManager;
import ap.hearthstone.model.gameModels.cards.Card;
import ap.hearthstone.model.gameModels.deck.Deck;
import ap.hearthstone.model.gameModels.entities.Minion;
import ap.hearthstone.model.gameModels.exceptions.FullDeckException;
import ap.hearthstone.model.gameModels.exceptions.IllegalSummonException;
import ap.hearthstone.model.gameModels.heros.Hero;
import ap.hearthstone.model.gameModels.heros.HeroMage;
import ap.hearthstone.utils.ConfigLoader;
import ap.hearthstone.utils.Configs;

import java.util.LinkedList;
import java.util.List;

public class Player {
    private String name;

    private Hero hero;
    private int availableMana;
    private int turn;

    private List<Minion> summonedMinions;
    private List<Minion> playedMinions;
    private List<Minion> deadMinions;

    private List<Card> handCards;
    private Deck deck;
    private List<Card> playedCards;

    private int maxSummonedMinions;
    private int maxHandCards;


    public Player(){
        //default setting which cannot be read from file
        this("The Innkeeper", HeroMage.getInstance(), new DeckFileManager().getDeck("classic"));
    }

    public Player(String name, Hero hero, Deck deck) {
        this.name = name;
        this.hero = hero;
        this.deck = deck;

        turn = 0;
        availableMana = 0;

        summonedMinions = new LinkedList<>();
        playedMinions = new LinkedList<>();
        deadMinions = new LinkedList<>();

        handCards = new LinkedList<>();
        playedCards = new LinkedList<>();

        initConstants();
    }

    private void initConstants() {
        Configs constants = ConfigLoader.getInstance().getGameConstants();
        maxSummonedMinions = constants.readInt("maxSummonedMinions");
        maxHandCards = constants.readInt("maxHandCards");
    }

    public void playTurn(){
        turn++;
        availableMana = Math.min(turn, 10);

    }

    public void setAvailableMana(int availableMana) {
        this.availableMana = availableMana;
    }

    public void addToDeadMinions(Minion minion) {
        deadMinions.add(minion);
    }

    public void addToSummonedMinions(Minion minion) throws IllegalSummonException {
        if (summonedMinions.size() > maxSummonedMinions) {
            throw new IllegalSummonException("number of minions on the battleground has reached maximum.");
        }
        summonedMinions.add(minion);
    }

    public void addToPlayedMinions(Minion minion) throws IllegalSummonException {
        addToSummonedMinions(minion);
        playedMinions.add(minion);
    }

    public void addToHandCards(Card card) throws FullDeckException {
        if(handCards.size() > maxHandCards){
            throw new FullDeckException();
        }
        handCards.add(card);
    }

    // GETTERS

    public String getName() {
        return name;
    }

    public Hero getHero() {
        return hero;
    }

    public int getAvailableMana() {
        return availableMana;
    }

    public List<Minion> getSummonedMinions() {
        return summonedMinions;
    }

    public List<Minion> getPlayedMinions() {
        return playedMinions;
    }

    public List<Minion> getDeadMinions() {
        return deadMinions;
    }

    public List<Card> getHandCards() {
        return handCards;
    }

    public Deck getDeck() {
        return deck;
    }

    public List<Card> getPlayedCards() {
        return playedCards;
    }
}
