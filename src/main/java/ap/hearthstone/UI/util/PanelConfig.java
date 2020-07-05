package ap.hearthstone.UI.util;

public class PanelConfig {

    private static PanelConfig instance;

    private int mainFrameWidth = 1200;
    private int mainFrameHeight = 800;
    private int errorFrameWidth = 400;
    private int errorFrameHeight = 200;

    private PanelConfig(){

    }

    public static PanelConfig getInstance() {
        if(instance==null){
            instance = new PanelConfig();
        }
        return instance;
    }

    public int getMainFrameWidth() {
        return mainFrameWidth;
    }

    public int getMainFrameHeight() {
        return mainFrameHeight;
    }

    public int getErrorFrameWidth() {
        return errorFrameWidth;
    }

    public int getErrorFrameHeight() {
        return errorFrameHeight;
    }
}
