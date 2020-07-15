package ap.hearthstone.UI.api;

import ap.hearthstone.interfaces.RequestType;

public class Request {

    private String title;
    private String[] requestBody;
    private RequestType type;

    public Request(String title, String... requestBody) {
        this.title = title;
        this.requestBody = requestBody;
    }

    public Request(String title) {
        this.title = title;
    }

    public Request(RequestType type) {
        this.type = type;
    }

    public String[] getRequestBody() {
        return requestBody;
    }

    public String getTitle() {
        if (title == null) {
            title = type.toString();
        }
        return title;
    }

    public RequestType getType() {
        return type;
    }
}
