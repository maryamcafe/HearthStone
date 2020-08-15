package ap.hearthstone.UI.api;

public class Request {

    private final String title;
//    private String ID;
    private String[] requestBody;

    public Request(String title) {
        this.title = title;
    }

//    public Request(String title, String ID) {
//        this(title);
//        this.ID = ID;
//    }

    public Request(String title, String... requestBody) {
        this(title);
        this.requestBody = requestBody;
    }

//    public Request(String title, String ID, String... requestBody){
//        this(title, ID);
//        this.requestBody = requestBody;
//    }

    // Getters
    public String getTitle() {
        return title;
    }

//    public String getID() {
//        return ID;
//    }

    public String[] getRequestBody() {
        return requestBody;
    }

}
