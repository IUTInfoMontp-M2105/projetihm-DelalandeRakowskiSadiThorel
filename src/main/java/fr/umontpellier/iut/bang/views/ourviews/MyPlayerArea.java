package fr.umontpellier.iut.bang.views.ourviews;

import fr.umontpellier.iut.bang.ICard;
import fr.umontpellier.iut.bang.IPlayer;
import fr.umontpellier.iut.bang.logic.cards.Card;
import fr.umontpellier.iut.bang.views.CardView;
import fr.umontpellier.iut.bang.views.GameView;
import fr.umontpellier.iut.bang.views.PlayerArea;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MyPlayerArea extends PlayerArea {
    private HBox mainJoueur;
    private Label nomDuJoueur;
    public MyPlayerArea(IPlayer player, GameView gameView) {
        super(player, gameView);
        mainJoueur = new HBox();
        mainJoueur.setSpacing(-25);
        nomDuJoueur = new Label(player.getName());
        setHandListener(whenHandIsUpdated);
    }

    private ListChangeListener<Card> whenHandIsUpdated = new ListChangeListener<Card>() {
        @Override
        public void onChanged(Change<? extends Card> change) {
            while(change.next()){
                if(change.wasAdded()){
                    for (Card c : change.getAddedSubList()){
                        mainJoueur.getChildren().add(new MyCardView(new ICard(c),MyPlayerArea.this));
                    }
                }else if (change.wasRemoved()){
                    for (Card c : change.getRemoved()){
                        mainJoueur.getChildren().remove(findCardView(mainJoueur,c));
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
    public void highlightCurrentArea() {setStyle("-fx-background-color:lightblue");

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
}
