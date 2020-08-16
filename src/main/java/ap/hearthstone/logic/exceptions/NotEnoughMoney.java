package ap.hearthstone.logic.exceptions;

public class NotEnoughMoney extends Exception {
    public NotEnoughMoney() {
        super("You don't have enough voins to buy this card");
    }
}
