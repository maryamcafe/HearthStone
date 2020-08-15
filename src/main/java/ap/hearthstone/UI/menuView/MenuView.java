package ap.hearthstone.UI.menuView;

import ap.hearthstone.UI.api.UpdatingPanel;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/* The purpose of this class is to organize a menu panel properly.
 */
public abstract class MenuView extends UpdatingPanel {

    private final String[] componentNames;
    protected Map<String, JButton> buttonMap;
    protected Map<String, JTextField> fieldMap;
    protected Map<String, JLabel> labelMap;
    //panels should get the needed data from states and draw them on them selves
    protected GridBagConstraints gc;

    MenuView(String... componentNames) {
        this.componentNames = componentNames;
    }

    @Override
    public void initView() {
        buttonMap = new HashMap<>();
        fieldMap = new HashMap<>();
        labelMap = new HashMap<>();
        for (String fullName : componentNames) {
            String lower = fullName.toLowerCase();
            String easyName = lower.substring(2);
            if (lower.startsWith("b")) {
                buttonMap.put(easyName, new JButton(easyName.toUpperCase()));
            } else if (lower.startsWith("f")) {
                fieldMap.put(easyName, new JTextField(10));
            } else if (lower.startsWith("l")) {
                labelMap.put(easyName, new JLabel(easyName));
            } else if(lower.startsWith("p") ){
                fieldMap.put(easyName, new JPasswordField(10));
            }
        }
        setLayout(new GridBagLayout());
        gc = new GridBagConstraints();
        organize();
        addListeners();
    }

    protected abstract void organize();

    protected void organizeField(String fieldName, int row, double weighty) {
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

    protected void organizeButton(JButton button, int row, double weighty) {
        Insets inset = new Insets(20, 0, 0, 0);
        gc.weightx = 1;
        gc.weighty = weighty;

        gc.gridx = 2;
        gc.gridy = row;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = inset;
        add(button, gc);
    }

    void organizeLabel(JLabel label, int row, double weighty) {
        Insets inset = new Insets(20, 0, 0, 0);
        gc.weightx = 1;
        gc.weighty = weighty;

        gc.gridx = 2;
        gc.gridy = row;
        gc.anchor = GridBagConstraints.LAST_LINE_START;
        gc.insets = inset;
        add(label, gc);
    }

}
