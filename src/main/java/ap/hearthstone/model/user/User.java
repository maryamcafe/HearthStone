package ap.hearthstone.model.user;
public class User {

    private String username, password;
    private long id;

    public User(String username, String password, long id) {
        this.username = username;
        this.password = password;
        this.id = id;
    }

    //Getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    } //To Do: encode with hash.

    public long getId() {
        return id;
    }

}
