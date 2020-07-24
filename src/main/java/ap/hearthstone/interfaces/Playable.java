package ap.hearthstone.interfaces;

import ap.hearthstone.logic.game.PlayVisitor;

public interface Playable {
    void play(PlayVisitor playVisitor);
}
