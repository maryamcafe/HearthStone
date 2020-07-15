package ap.hearthstone.UI.api.exceptions;

public class NoSuchViewException extends Exception{
    public NoSuchViewException(String viewName) {
        super("There is no view in the MainFrame named as " + viewName +" to display.");
    }
}
