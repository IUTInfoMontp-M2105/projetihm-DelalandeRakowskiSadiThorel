package fr.umontpellier.iut.bang.views.ourviews;

import fr.umontpellier.iut.bang.IGame;
import fr.umontpellier.iut.bang.views.GameView;

public class MyGameView extends GameView {

    public MyGameView(IGame game) {
        super(game);
    }

    @Override
    protected void bindNextActionMessage() {

    }

    public void executeSetPassSelectedListener(){
        setPassSelectedListener();
    }

    @Override
    protected void setPassSelectedListener() {getIGame().onPass();

    }
}
