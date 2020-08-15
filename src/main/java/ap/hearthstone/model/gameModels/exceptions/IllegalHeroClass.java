package ap.hearthstone.model.gameModels.exceptions;

public class IllegalHeroClass extends Exception {
    public IllegalHeroClass(){
        super("Ypu can only choose from your own hero class and Neutral cards.");
    }
}
