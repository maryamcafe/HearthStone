package ap.hearthstone.logic.users;

public class NoUserFoundException extends Exception {
    public NoUserFoundException(String username) {
        super("No user found with username: " + username);
    }
}
