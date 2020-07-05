package ap.hearthstone.UI.menuView.data;

import ap.hearthstone.UI.util.ViewData;

public class LoginData extends ViewData {
    private String username, password;

    public LoginData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
