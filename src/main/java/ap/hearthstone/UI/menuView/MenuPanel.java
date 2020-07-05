package ap.hearthstone.UI.menuView;

import ap.hearthstone.UI.api.ViewPanel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public abstract class MenuPanel extends ViewPanel {

    Map<String, JButton> buttonMap;
    Map<String, JTextField> fieldMap;
    Map<String, JLabel> labelMap;
    //panels should get the needed data from states and draw them on them selves
    Logger logger;

    MenuPanel(String... componentNames) {
        logger = LogManager.getLogger(this.getClass());

        buttonMap = new HashMap<>();
        fieldMap = new HashMap<>();
        labelMap = new HashMap<>();
        for (String fullName : componentNames) {
            String lower = fullName.toLowerCase();
            String easyName = fullName.substring(2).toLowerCase();
            if (lower.startsWith("b")) {
                buttonMap.put(easyName, new JButton(easyName));
            } else if (lower.startsWith("f")) {
                fieldMap.put(easyName, new JTextField(10));
            } else if (lower.startsWith("l")) {
                labelMap.put(easyName, new JLabel(easyName));
            }
        }
        organize();
        addListeners();
    }

    abstract void addListeners();

    abstract void organize();

}
