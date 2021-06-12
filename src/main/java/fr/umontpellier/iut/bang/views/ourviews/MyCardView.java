package fr.umontpellier.iut.bang.views.ourviews;

import fr.umontpellier.iut.bang.ICard;
import fr.umontpellier.iut.bang.IGame;
import fr.umontpellier.iut.bang.IPlayer;
import fr.umontpellier.iut.bang.views.CardView;
import fr.umontpellier.iut.bang.views.PlayerArea;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class MyCardView extends CardView {
    ImageView imageCarte = new ImageView();
    public MyCardView(ICard card, PlayerArea playerArea) {
        super(card, playerArea);
        String name = card.getImageName();
        imageCarte.setImage(new Image(name));
        imageCarte.setPreserveRatio(true);
        Label nomCarte = new Label(name);
        imageCarte.setFitHeight(125);
        getChildren().add(imageCarte);
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

    public ImageView getImageCarte() {
        return imageCarte;
    }

    private EventHandler<MouseEvent> whenCardSelected = mouseEvent -> {
        MyCardView selectedCardView = (MyCardView) mouseEvent.getSource();
        ICard selectedCard = selectedCardView.getICard();
        IGame currentGame = selectedCardView.getPlayerArea().getGameView().getIGame();
        IPlayer targetPlayer = selectedCardView.getPlayerArea().getIPlayer();
        currentGame.onCardSelection(selectedCard, targetPlayer);
    };
}
