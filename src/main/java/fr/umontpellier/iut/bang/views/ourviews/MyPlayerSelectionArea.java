package fr.umontpellier.iut.bang.views.ourviews;

import fr.umontpellier.iut.bang.views.PlayerArea;
import fr.umontpellier.iut.bang.views.PlayerSelectionArea;

public class MyPlayerSelectionArea extends PlayerSelectionArea {
    public MyPlayerSelectionArea(PlayerArea playerArea) {
        super(playerArea);
    }

    @Override
    public void setVisible() {
        if(getPlayerArea().getPlayer().equals(getPlayerArea().getGameView().getIGame().getCurrentPlayer())){
            getPlayerArea().highlightCurrentArea();
        }else setUnVisible();
    }

    @Override
    public void setUnVisible() {
        getPlayerArea().deHightlightCurrentArea();
    }

    @Override
    protected void setPlayerSelectedListener() {
        getPlayerArea().getGameView().getIGame().onTargetSelection(getPlayerArea().getIPlayer());
    }
}
