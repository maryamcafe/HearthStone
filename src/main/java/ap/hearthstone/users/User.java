package ap.hearthstone.users;

public class User {
    //setting a unique ID for every individual user
    private int id;
    //User's username is the key to finding it
    private String username;
    private String password;
    //this field shows whether the user has deleted or not:
    private boolean isActive;


    User(String username, String password) {
        this.username = username;
        this.password = password;
        isActive = true;
        id = readLastID()+ 1;
    }

    private int readLastID() {
        return readLastID();
    }


    //Getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public double getId() {
        return id;
    }

    public boolean isActive() {
        return isActive;
    }

    //Setters
    private void setPassword(String password) {
        this.password = password;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void delete(){

    }

}
