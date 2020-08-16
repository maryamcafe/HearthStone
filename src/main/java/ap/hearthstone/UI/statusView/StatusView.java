package ap.hearthstone.UI.statusView;

import ap.hearthstone.UI.api.UpdatingPanel;
import ap.hearthstone.UI.menuView.LoginView;
import ap.hearthstone.UI.menuView.MenuView;

public class StatusView extends MenuView {

    public StatusView(){
        super("l:total wins");
        setName("status");
    }

    @Override
    protected void organize() {
        organizeLabel(labelMap.get("total wins"), 1, 1);
    }

    @Override
    protected void addListeners() {

    }

    @Override
    protected void executeResponses() {

    }

    @Override
    public void initView() {

    }
}
