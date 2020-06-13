package mvvm;

public class LoginData implements StateData{

    private boolean isSuccessful;
    private Message message;

    public LoginData(boolean isSuccessful, Message message) {
        this.isSuccessful = isSuccessful;
        this.message = message;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }

    public enum Message{
        USER_NOT_FOUND("No User Found with this username"),
        INCORRECT_PASSWORD("Password is incorrect"),
        SUCCESSFUL("You're In!\n" +
                "Welcome To HeathStone");
        String display;
        Message(String display){
            this.display = display;
        }
    }
}
