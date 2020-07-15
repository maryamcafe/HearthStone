package ap.hearthstone.UI.menuView;

import ap.hearthstone.UI.api.Request;

import javax.swing.*;

public class SignUpView extends LoginView {

    /* Updates as defined in ViewPanel; executing responses.
     Executes responses as defined in LoginView.
    */
    public SignUpView(){
        super("f:username", "p:password", "b:sign up", "b:back");
    }


    @Override
    protected void organize() {
        organizeLabel(new JLabel(""), 1, 0.8);
        organizeField("username", 2, 0.1);
        organizeField("password", 3, 0.1);
        organizeButton(buttonMap.get("sign up"), 4, 0.2);
        organizeButton(buttonMap.get("back"), 5, 0.8);
    }

    @Override
    protected void addListeners() {
        buttonMap.get("sign up").addActionListener(e-> requestSender.send(
                new Request("sign",
                fieldMap.get("username").getText(),
                fieldMap.get("password").getText())));
        buttonMap.get("back").addActionListener(e->requestSender.send(
                new Request("back")));
    }

}
