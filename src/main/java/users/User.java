package users;

//this class is used to create valid users,
// saving the list of users in a file and passing that to a new class
public class User {
    private String username;
    private String password;
    //we'll set a unique ID for every instance of User:
    private static int firstId = 11_111;
    private final double userId;
    //this field shows whether the user has deleted or not:
    private boolean isActive;

    //constructor
    public User(String username, String password) {
        //we suppose that the function caller has passed a valid username
        //we could write this block later with an exception handler: throws,
        // and in the caller ask for another username.
        this.username = username;
        this.password = password;
        //THE ESSENTIAL and NON-CHANGING part of class:
        this.userId = firstId++;
        //now that the parameters are set successfully,
        // we can save them in a file.
        //read the json file and write at the end of it:

    }


    //Setters
    public void setPassword(String password) {this.password = password;}
    public void setActive(boolean active) {isActive = active;}

    //Getters
    public String getPassword() {return password;}
    public double getUserId() {return userId;}
    public boolean isActive() {return isActive;}

    class PasswordAndId {
        private String username;
        private String password;
        public PasswordAndId(String username, String password) {
            this.username = username;
            this.password = password;
        }
    }



}
