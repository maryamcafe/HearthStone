package ap.hearthstone.UI.api;

import ap.hearthstone.UI.menuView.data.LoginData;

public class Request {

    private String title;
    private String[] requestBody;
    private LoginData loginData;
    private RequestType type;

    public Request(String... requestBody) {
        this.requestBody = requestBody;
    }

    public Request(String title) {
        this.title = title;
    }

    public Request(RequestType type) {
        this.type = type;
    }

//    public Request(String title, String... messageBody) {
//        this.title = title;
//        this.requestBody = messageBody;
//    }
//
//    public Request(String login, String username, String password) {
//        title = login;
//        loginData = new LoginData(username, password);
//    }

//    @Deprecated
//    // custom decoding from String array
//    public LoginData getLoginData() {
//        if (loginData == null) {
//            String username = null, password = null;
//            for (int i = 0; i < requestBody.length; i++) {
//                if (requestBody[i].equals("username")) {
//                    username = requestBody[++i];
//                } else if (requestBody[i].equals("password")) {
//                    password = requestBody[++i];
//                }
//            }
//            loginData = new LoginData(username, password);
//        }
//        return loginData;
//    }

    public String[] getRequestBody() {
        return requestBody;
    }

    public String getTitle() {
        if (title == null) {
            title = requestBody[0];
        }
        return title;
    }

    public RequestType getType() {
        return type;
    }
}
