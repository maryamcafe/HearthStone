package ap.hearthstone.model.gameModels.exceptions;

public class MaxEachCardException extends Exception {
    public MaxEachCardException() {
        super("You already have 2 of these cards, can't add more!");
    }
}
