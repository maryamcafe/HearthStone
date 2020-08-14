package ap.hearthstone.model.gameModels.deck;

import ap.hearthstone.logic.cards.CardFileManager;
import ap.hearthstone.model.gameModels.cards.Card;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DeckStatus {

    private Deck deck;
    private int totalPlays;
    private int totalWins;
    private Map<String, Integer> cardUsage;

    public DeckStatus(Deck deck) {
        this.deck = deck;
        cardUsage = new HashMap<>();
    }

    // Getters
    public String getDeckName() {
        return deck.getName();
    }

    public String getDeckHero() {
        return deck.getHeroClass().name();
    }

    public double getWinPercentage() {
        return (double) totalWins / totalPlays;
    }

    public int getTotalPlays() {
        return totalPlays;
    }

    public int getTotalWins() {
        return totalWins;
    }

    public void updateDeck(Deck deck) {
        this.deck = deck;
    }

    /* Calculates the most used card with these priorities in sequence:
     Rarity, Mana, MinionCard. Considering all priorities if we were left with more than 1 card,
     we will show one randomly. */
    public String getMostUsedCard() throws Exception {
        Optional<Integer> max = cardUsage.values().stream().reduce((i, j) -> (i >= j) ? i : j);
        if (max.isPresent()) {  // is true always
            int maximum = max.get();
            CardFileManager fileManager = CardFileManager.getInstance();
            List<Card> topCards = cardUsage.keySet().stream().filter(card -> cardUsage.get(card) == maximum)
                    .map(fileManager::getCard).collect(Collectors.toList());
            if (topCards.size() > 1) {
                topCards.sort(new CardComparator());
            }
            return topCards.get(0).getName();
        }
        throw new Exception("Can not find the most used card.");
    }

    static class CardComparator implements Comparator<Card>{
        @Override
        public int compare(Card card1, Card card2) {
            if(!card1.getRarity().equals(card2.getRarity())) {
                return card1.getRarity().compareTo(card2.getRarity());
            } else if (card1.getMana()!=card2.getMana()) {
                return card1.getMana() - card2.getMana();
            } else if (!card1.getType().equals(card2.getType())) {
                return card1.getType().compareTo(card2.getType());
            }
            return 0;
        }
    }

}
