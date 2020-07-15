package ap.hearthstone.model.gameModels.exceptions;

public class FullDeckException extends Exception {
    public FullDeckException() {
        super("deck is full, card can't be added");
    }
}
