package ap.hearthstone.model.gameModels.exceptions;

public class IllegalSummonException extends Exception {
    public IllegalSummonException(String reason){
        super("You can not summon this minion due to: " + reason);
    }
}
