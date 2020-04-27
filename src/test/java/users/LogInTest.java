package users;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

import static users.LogInConstants.tryAgainMsg;
import static users.LogInConstants.wrongInput;

public class LogInTest {
    {
        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
        StackTraceElement e = stacktrace[2];//maybe this number needs to be corrected
        String methodName = e.getMethodName();
    }

    public static void main(String[] args) throws IOException {
//        System.out.println("isUsernameTaken: " + isUsernameTaken("user222"));
//        LogIn login = new LogIn();
//        login.newLogIn();
//        if(tryAgain()){
//            System.out.println("I reached here!");
//
//        List<Boolean> running = new ArrayList<>();
//        running.add(true);
        boolean[] running = new boolean[]{true, true};
        run(running);
        System.out.println("after the method completed, running is: " + Arrays.toString(running));

//        HashMap<Integer, String> map = new HashMap<Integer, String>();
//        map = readMap(Integer.TYPE, String.class, "idUsernameMap.json");
//        map.put(3, "hasan");
//        System.out.println(map.toString());

//        Scanner scanner = new Scanner(System.in);
//        String username = scanner.nextLine();
//        String password = scanner.nextLine();
//        scanner.close();
//        System.out.println(username);
//        System.out.println(password);

    }

    static boolean tryAgain() {
        System.out.println("I reached inside the function");
        Scanner scanner = new Scanner(System.in);
        System.out.println(tryAgainMsg);
        String msg = scanner.next();
        switch (msg.trim().toLowerCase()) {
            case "back":
                return false;
            case "again":
                break;
            default:
                System.out.println(wrongInput);
                tryAgain();
                break;
        }
        return true;
    }

    // This method was written for idUsernamefile
    //but then we decided to use a linkedList
    static <keyType, valueType> HashMap<keyType, valueType> readMap(Type keyType, Type valueType, String fileName) throws IOException {
        Gson gson = new Gson();
        //load files and check the username and password,
        Reader reader = new FileReader(new File(fileName));
        Type type = new TypeToken<HashMap<keyType, valueType>>() {
        }.getType();
        HashMap<keyType, valueType> map = gson.fromJson(reader, type);
        reader.close();
        return map;
    }

    private static void run(boolean[] running) {
        int i = 1;
        while (running[0]) {
            System.out.println("this is the loop number " + i++);
            if (i == 3) {
                running[0] = false;
                System.out.println("running is now: " + Arrays.toString(running));
            }
        }
//        while (running.contains(true)) {
//            System.out.println("this is the loop number " + i++);
//            if (i == 3) {
//                running.remove(true);
//                running.add(false);
//                System.out.println("running is now: " + running);
//            }
//        }
    }
}