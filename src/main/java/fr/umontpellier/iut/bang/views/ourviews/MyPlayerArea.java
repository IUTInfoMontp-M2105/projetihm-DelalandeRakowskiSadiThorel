package fr.umontpellier.iut.bang.views.ourviews;

import fr.umontpellier.iut.bang.ICard;
import fr.umontpellier.iut.bang.IPlayer;
import fr.umontpellier.iut.bang.logic.cards.BlueCard;
import fr.umontpellier.iut.bang.logic.cards.Card;
import fr.umontpellier.iut.bang.views.CardView;
import fr.umontpellier.iut.bang.views.GameView;
import fr.umontpellier.iut.bang.views.PlayerArea;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class MyPlayerArea extends PlayerArea {
    private HBox mainJoueur;
    private Label nomDuJoueur;
    private HBox inPlay;
    private MyPlayerSelectionArea selection;
    ImageView card = new ImageView();
    ImageView cardRole = new ImageView();
    public MyPlayerArea(IPlayer player, GameView gameView) {
        super(player, gameView);
        selection = new MyPlayerSelectionArea(this);
        String cardCharacter = this.getImageCharacter(player.getPlayer());
        String cardRoleString = this.getImageRole(player.getPlayer());
        card.setImage(new Image(cardCharacter));
        cardRole.setImage(new Image(cardRoleString));
        mainJoueur = new HBox();
        inPlay=new HBox();
        inPlay.setSpacing(-25);
        inPlay.setLayoutY(5);
        mainJoueur.setSpacing(-25);
        nomDuJoueur = new Label(player.getName());
        setHandListener(whenHandIsUpdated);
        setInPlayListener(whenInplayIsUpdated);

    }

    private ListChangeListener<Card> whenHandIsUpdated = new ListChangeListener<Card>() {
        @Override
        public void onChanged(Change<? extends Card> change) {
            while(change.next()){
                if(change.wasAdded()){
                    for (Card c : change.getAddedSubList()){
                        MyCardView cardBleu = new MyCardView(new ICard(c),MyPlayerArea.this);
                        mainJoueur.getChildren().add(cardBleu);
                    }
                }else if (change.wasRemoved()){
                    for (Card c : change.getRemoved()){
                        mainJoueur.getChildren().remove(findCardView(mainJoueur,c));
                    }
                }
            }
        }
    };

    private  ListChangeListener<BlueCard> whenInplayIsUpdated = new ListChangeListener<BlueCard>() {
        @Override
        public void onChanged(Change<? extends BlueCard> change) {
            while (change.next()) {
                if (change.wasAdded()) {
                    for (Card c : change.getAddedSubList()) {
                        inPlay.getChildren().add(new MyCardView(new ICard(c), MyPlayerArea.this));
                    }
                } else if (change.wasRemoved()) {
                    for (Card c : change.getAddedSubList()) {
                        inPlay.getChildren().remove(findCardView(inPlay, c));
                    }
                }
            }
        }
    };
    private CardView findCardView(HBox container, Card card) {
        for (Node n : container.getChildren()) {
            CardView nodeCardView = (CardView) n;
            Card nodeCard = nodeCardView.getCard();
            if (nodeCard.equals(card))
                return nodeCardView;
        }
        return null;
    }
    @Override
    public void highlightCurrentArea() {
        setStyle("-fx-background-color:#8fa9ca");

    }

    @Override
    public void deHightlightCurrentArea() {
        setStyle("-fx-background-color:transparent");
    }

    public Label getLabelName(){
        return nomDuJoueur;
    }

    public HBox getMainJoueur() {
        return mainJoueur;
    }

    public ImageView getCard() {
        return card;
    }

    public ImageView getCardRole() {
        return cardRole;
    }

    public HBox getInPlayJoueur() {
        return inPlay;
    }
}
