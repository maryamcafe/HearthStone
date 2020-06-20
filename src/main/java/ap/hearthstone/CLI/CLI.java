package ap.hearthstone.CLI;

import java.util.ArrayList;
import java.util.List;

public class CLI {
    protected static List<Boolean> running = new ArrayList<>();

    protected static void exit() {
        running.add(1, false);
    }

    protected static void exitAll() {
        running.add(0, false);
    }
}
