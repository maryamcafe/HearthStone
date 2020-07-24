package ap.hearthstone.logic.exceptions;


public class NotEnoughManaException extends Exception{
    public NotEnoughManaException(int manaRequired, int manaAvailable){
        super(String.format("To play this card/heropower %d mana is required, but there's %d available",
                manaRequired, manaAvailable));
    }
}
