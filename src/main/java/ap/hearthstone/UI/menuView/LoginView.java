package ap.hearthstone.UI.menuView;

import ap.hearthstone.UI.api.Request;

import javax.swing.*;

public class LoginView extends MenuView {

    public LoginView() {
        super("f:username", "p:password", "b:LOGIN", "l:or :", "b:SIGN UP");
        setName("Login Menu");
        logger.debug("Login Menu is initialized");
    }

    public LoginView(String... componentNames) {
        super(componentNames);
    }

    @Override
    protected void organize() {
        organizeLabel(new JLabel(""), 0, 2);

        organizeField("username", 1, 0.1);
        organizeField("password", 2, 0.1);
        organizeButton(buttonMap.get("login"), 3, 1);

        organizeLabel(labelMap.get("or :"), 4, 0.1);
        organizeButton(buttonMap.get("sign up"), 5, 2);
    }

    @Override
    protected void addListeners() {
        buttonMap.get("login").addActionListener(e -> requestSender.send(new Request("login",
                fieldMap.get("username").getText(),
                fieldMap.get("password").getText())));
        buttonMap.get("sign up").addActionListener(e -> requestSender.send(new Request("sign up")));
    }

    @Override
    protected void executeResponses() {
        while(requestList.size()>0){
            Request request = requestList.remove(0);
            if ("error".equals(request.getTitle())) {
                error(request.getRequestBody()[0]);
            } else if("successful".equals(request.getTitle())){
                info(request.getRequestBody()[0]);
            } else if("again".equals(request.getTitle())){
                tryAgain();
            }
        }
    }

    /*
    The program waits in info and error dialogues for the user's click on "OK" button,
     and then moves to the next view.
     Also the logging happens here, not in the request sending object.
     */
    protected void info(String message) {
        logger.info(message);
        JOptionPane.showMessageDialog(this, message);
    }

    protected void error(String message) {
        logger.error(message);
        JOptionPane.showMessageDialog(this,
                message,
                "Error in Login",
                JOptionPane.ERROR_MESSAGE);
    }

    protected void tryAgain(){
        logger.info("Trying again.");
        fieldMap.forEach((s, field) -> field.setText(""));
    }


}
