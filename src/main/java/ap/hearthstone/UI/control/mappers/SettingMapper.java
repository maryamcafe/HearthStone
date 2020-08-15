package ap.hearthstone.UI.control.mappers;

import ap.hearthstone.UI.api.Request;
import ap.hearthstone.UI.api.Mapper;
import ap.hearthstone.logic.users.PlayerManager;
import ap.hearthstone.logic.users.UserFactory;

public class SettingMapper extends Mapper {

    private final String username;

    public SettingMapper(String username) {
        this.username = username;
    }

    @Override
    protected void doForRequest(Request request) {
        switch (request.getTitle()) {
            case "deleteUser":
                deleteUser();
            default:
                break;
        }
    }

    private void deleteUser() {
        UserFactory userFactory = new UserFactory();
        userFactory.deleteUser(username);
        PlayerManager playerManager = new PlayerManager(username);
        playerManager.deletePlayer();
        responseSender.send(new Request("info", "User deleted."));
    }


}
