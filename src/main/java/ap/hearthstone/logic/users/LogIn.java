package ap.hearthstone.logic.users;


import ap.hearthstone.CLI.CLI;
import java.util.List;
import java.util.Scanner;

import ap.hearthstone.model.user.Player;
import ap.hearthstone.model.user.User;


@Deprecated
public class LogIn extends CLI {
    private static Scanner scanner = new Scanner(System.in);
    private String username, password;
    private User user = null;
    private Player player;
    boolean isSuccessful = false;
    private  UsersFilesManager filesManager = new UsersFilesManager();
    private LoginConstants constants;
    private UserFactory userFactory;

    public LogIn() {
        constants = new LoginConstants();
        userFactory = new UserFactory();
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
//            player = PlayerFileManager.playerInitializer(user);
//            menu();
        }
        scanner.close();
    }


    private void menu() {
        String input = scanner.nextLine();
        switch (input.trim().toLowerCase()) {
            case "help":
                System.out.println(constants.getLoggedInMenu());
                menu();
                break;
            case "\n":
                break;
            case "exit":
                CLI.exit();
                break;
            case "exit -a":
                CLI.exitAll();
                break;
            default:
                System.out.println(constants.getWrongInput());
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
                System.out.println(constants.getLoggedInMenu());
                return isNewUser();
            case "exit":
                CLI.exit();
                return null;
            case "exit -a":
                CLI.exitAll();
                return null;
            default:
                System.out.println(constants.getWrongInput());
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
        if (filesManager.isUsernameTaken(username)) {
            System.out.println(constants.getUsernameTakenBeforeMsg());
            if (tryAgain()) {
                signUp();
            }
            //Otherwise the program will exit
        } else {
            isSuccessful = true;
            System.out.println(constants.getSignUpSuccessful());
            user = userFactory.createUser(username, password);
           filesManager.addUserToFile(user);
            //logger.info("A new User just created.");
        }
    }

    //SignIn
    private void signIn() {
        System.out.println("Enter your username and password: [username password] ");
        username = scanner.next();
        password = scanner.next();
        try {
            if (!filesManager.isPasswordCorrect(username.trim(), password.trim())) {
                System.out.println(constants.getPasswordNotCorrect());
                if (tryAgain()) {
                    signIn();
                }
                //Otherwise the program will exit
            } else {
                isSuccessful = true;
                System.out.println(constants.getLoginSuccessful());
//                user =filesManager.findUser(username);
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
        System.out.println(constants.getTryAgainMsg());
        String msg = scanner.next();
        switch (msg.trim().toLowerCase()) {
            case "exit":
                CLI.exit();
                return false;
            case "exit -a":
                CLI.exitAll();
                return false;
            case "again":
                return true;
            default:
                System.out.println(constants.getWrongInput());
                return tryAgain();
        }
    }


}
