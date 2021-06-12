package fr.umontpellier.iut.bang.views.ourviews;

import fr.umontpellier.iut.bang.ICard;
import fr.umontpellier.iut.bang.IGame;
import fr.umontpellier.iut.bang.IPlayer;
import fr.umontpellier.iut.bang.views.CardView;
import fr.umontpellier.iut.bang.views.PlayerArea;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class MyCardView extends CardView {
    public MyCardView(ICard card, PlayerArea playerArea) {
        super(card, playerArea);
        Label nomCarte = new Label(card.getName());
        getChildren().add(nomCarte);
        setCardSelectionListener();
    }

    @Override
    public void setVisible() {

    }

    @Override
    public void setUnVisible() {

    }

    @Override
    protected void setCardSelectionListener() {
        setOnMouseClicked(whenCardSelected);
    }

    private EventHandler<MouseEvent> whenCardSelected = mouseEvent -> {
        MyCardView selectedCardView = (MyCardView) mouseEvent.getSource();
        ICard selectedCard = selectedCardView.getICard();
        IGame currentGame = selectedCardView.getPlayerArea().getGameView().getIGame();
        IPlayer targetPlayer = selectedCardView.getPlayerArea().getIPlayer();
        currentGame.onCardSelection(selectedCard, targetPlayer);
    };
}
