package ap.hearthstone.UI.menuView;

import ap.hearthstone.UI.api.Request;
import ap.hearthstone.UI.menuView.data.LoginData;
import ap.hearthstone.interfaces.RequestSender;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends MenuPanel {

    public LoginPanel() {
        super("F:username", "F:password", "B:LOGIN", "L:or SIGN UP:", "B:SIGN UP");
        setName("Login Menu");
        logger.debug("Login Menu is initialized");
    }


    @Override
    protected void organize() {
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        organizeLabel(new JLabel(""), 0, gc, 2);

        organizeField("username", 1, gc, 0.1);
        organizeField("password", 2, gc, 0.1);
        organizeButton(buttonMap.get("login"), 3, gc, 1);

        organizeLabel(labelMap.get("or sign up:"), 4, gc, 0.1);
        organizeButton(buttonMap.get("sign up"), 5, gc, 2);
        logger.debug("everything is organized now:)");
    }

    @Override
    protected void addListeners() {
        buttonMap.get("login").addActionListener(e -> requestSender.send(new Request("login",
                fieldMap.get("username").getText(),
                fieldMap.get("password").getText())));
        buttonMap.get("sign up").addActionListener(e -> requestSender.send(new Request("sign up")));
    }


    void organizeField(String fieldName, int row, GridBagConstraints gc, double weighty) {
        Insets labelInset = new Insets(20, 0, 0, 5);
        Insets inset = new Insets(20, 0, 0, 0);

        gc.weightx = 1;
        gc.weighty = weighty;

        gc.gridx = 1;
        gc.gridy = row;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = labelInset;
        add(new JLabel(fieldName + ":"), gc);


        gc.gridx = 2;
        gc.gridy = row;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = inset;
        add(new JScrollPane(fieldMap.get(fieldName)), gc);
    }

    void organizeButton(JButton button, int row, GridBagConstraints gc, double weighty) {
        Insets inset = new Insets(20, 0, 0, 0);
        gc.weightx = 1;
        gc.weighty = weighty;

        gc.gridx = 2;
        gc.gridy = row;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = inset;
        add(button, gc);
    }

    void organizeLabel(JLabel label, int row, GridBagConstraints gc, double weighty) {
        Insets inset = new Insets(20, 0, 0, 0);
        gc.weightx = 1;
        gc.weighty = weighty;

        gc.gridx = 2;
        gc.gridy = row;
        gc.anchor = GridBagConstraints.LAST_LINE_START;
        gc.insets = inset;
        add(label, gc);
    }

//    private class LoginAction implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            logger.info("login button clicked");
//            String username = fieldMap.get("username").getText();
//            String password = fieldMap.get("password").getText();
//            if (viewDataSender == null) {
//                logger.error("viewDataSender not set.");
//                return;
//            } else {
//                viewDataSender.send(new LoginData(username, password));
//            }
//        }
//    }
}
