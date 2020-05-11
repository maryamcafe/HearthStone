package users;


import CLI.BasicCLI;
import game.State;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import static util.LogInConstants.*;

import util.LogInConstants;
import util.PlayerFileManager;
import util.UsersFilesManager;


//LogIn is a state.
public class LogIn extends BasicCLI implements State {
    private static Scanner scanner = new Scanner(System.in);
    private String username;
    private String password;
    private boolean isSuccessful = false;  //for debugging
    private int i = 0;
    private User user = null;
    private Player player;
    private List<Boolean> running;

    public LogIn() {
    }

    public User getUser() {
        return user;
    }

    public Player getPlayer() {
        return player;
    }


    public void run(List<Boolean> running) {
        this.running = running;
        running.add(1, true);
        Boolean isNew = isNewUser();
        while (isNew != null) {
            if (isNew) {
                signUp();
            } else {
                signIn();
            }
            player = PlayerFileManager.playerInitializer(user);
            menu();
        }
        scanner.close();
    }


    private void menu() {
        String input = scanner.nextLine();
        switch (input.trim().toLowerCase()) {
            case "help":
                System.out.println(LogInConstants.LoggedInMenu);
                menu();
                break;
            case "\n":
                break;
            case "exit":
                BasicCLI.exit();
                break;
            case "exit -a":
                BasicCLI.exitAll();
                break;
            default:
                System.out.println(LogInConstants.wrongInput);
                menu();
                break;
        }
    }


    private Boolean isNewUser() {
        System.out.println("Already have an account? [y/n]");
        String input = scanner.next();
        switch (input.trim().toLowerCase()) {
            case "y":
                return false;
            case "n":
                return true;
            case "help":
                System.out.println(LogInConstants.isNewerHelp);
                return isNewUser();
            case "exit":
                BasicCLI.exit();
                return null;
            case "exit -a":
                BasicCLI.exitAll();
                return null;
            default:
                System.out.println(LogInConstants.wrongInput);
                return isNewUser();
        }
    }

    //SignUp
    private void signUp() {
        System.out.println("Set your Username and Password: [username password] ");
        username = scanner.next();
        System.out.println("username is: " + username);
        password = scanner.next();
        System.out.println("password is: " + password);
        try {
            if (UsersFilesManager.isUsernameTaken(username)) {
                System.out.println(LogInConstants.usernameTakenBeforeMsg);
                if (tryAgain()) {
                    signUp();
                }
                //Otherwise the program will exit
            } else {
                isSuccessful = true;
                System.out.println(LogInConstants.signUpSuccessful);
                user = new User(username, password);
                UsersFilesManager.addUserToFile(user);
                //logger.info("A new User just created.");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    //SignIn
    private void signIn() {
        System.out.println("Enter your username and password: [username password] ");
        username = scanner.next();
        password = scanner.next();
        try {
            if (!UsersFilesManager.isPasswordCorrect(username.trim(), password.trim())) {
                System.out.println(LogInConstants.wrongPassword);
                if (tryAgain()) {
                    signIn();
                }
                //Otherwise the program will exit
            } else {
                isSuccessful = true;
                System.out.println(LogInConstants.signInSuccessful);
                user = UsersFilesManager.findUser(username);
            }
        } catch (Exception e) {// the username doesn't exist:
            System.out.println(e.getMessage());
            //try again or exit
            if (tryAgain()) {
                signIn();
            }
        }
    }


    private boolean tryAgain() {
        System.out.println(LogInConstants.tryAgainMsg);
        String msg = scanner.next();
        switch (msg.trim().toLowerCase()) {
            case "exit":
                BasicCLI.exit();
                return false;
            case "exit -a":
                BasicCLI.exitAll();
                return false;
            case "again":
                return true;
            default:
                System.out.println(LogInConstants.wrongInput);
                return tryAgain();
        }
    }


}
