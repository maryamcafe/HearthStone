package ap.hearthstone.model.gameModels.exceptions;

public class FullDeckException extends Exception {
    public FullDeckException() {
        super("Deck is full, Please select less cards.");
    }
}
