package fr.umontpellier.iut.bang.views.ourviews;

import fr.umontpellier.iut.bang.ICard;
import fr.umontpellier.iut.bang.IPlayer;
import fr.umontpellier.iut.bang.logic.cards.BlueCard;
import fr.umontpellier.iut.bang.logic.cards.Card;
import fr.umontpellier.iut.bang.logic.cards.Colt;
import fr.umontpellier.iut.bang.logic.cards.WeaponCard;
import fr.umontpellier.iut.bang.views.CardView;
import fr.umontpellier.iut.bang.views.GameView;
import fr.umontpellier.iut.bang.views.PlayerArea;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.List;

public class MyPlayerArea extends PlayerArea {
    private HBox mainJoueur;
    private Label nomDuJoueur;
    private HBox inPlay;
    private VBox health;
    private MyPlayerSelectionArea selection;
    private MyCardView gun;
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
        mainJoueur.setSpacing(-25);

        inPlay=new HBox();
        inPlay.setSpacing(-25);
        inPlay.setLayoutY(5);
        inPlay.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,new BorderWidths(1))));
        nomDuJoueur = new Label(player.getName());

        health=new VBox();
        for (int i = 0; i<player.getHealthPoints();i++){
            ImageView vie = new ImageView("src/main/resources/images/bullet.png");
            vie.setPreserveRatio(true);
            vie.setFitHeight(15);
            health.getChildren().add(vie);
        }
        gun = new MyCardView(new ICard(new Colt()),this);
        gun.imageCarte.setPreserveRatio(true);
        gun.imageCarte.setFitHeight(85);


        setHandListener(whenHandIsUpdated);
        setInPlayListener(whenInplayIsUpdated);
        setHealthPointsListener(whenHealthPointsIsUpdated);
        setWeaponListener(whenWeaponChanges);

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
    private ChangeListener<WeaponCard> whenWeaponChanges = new ChangeListener<WeaponCard>() {
        @Override
        public void changed(ObservableValue<? extends WeaponCard> observableValue, WeaponCard weaponCard, WeaponCard t1) {
            gun.imageCarte.setImage(new Image (t1.getImageName()));
        }
    };

    private ChangeListener <Number> whenHealthPointsIsUpdated = new ChangeListener<Number>() {
        @Override
        public void changed(ObservableValue<? extends Number> observableValue, Number ancientNumber, Number newNumber) {
            int dif = ancientNumber.intValue() - newNumber.intValue();
            if (dif > 0) {
                for (int i = 0; i <dif; i++) {
                    health.getChildren().remove(i);
                    ImageView pointEnMoins = new ImageView("src/main/resources/images/bullet_grey.png");
                    pointEnMoins.setPreserveRatio(true);
                    pointEnMoins.setFitHeight(15);
                    health.getChildren().add(i,pointEnMoins);
                }
            }
            if (dif < 0) {
                System.out.println("je passe par ici mon reuf");
                for (int i = 0; i > dif; i--) {
                    health.getChildren().remove(i);
                    ImageView pointEnPlus = new ImageView("src/main/resources/images/bullet.png");
                    pointEnPlus.setPreserveRatio(true);
                    pointEnPlus.setFitHeight(15);
                    health.getChildren().add(i,pointEnPlus);
                }
            }
        }
    } ;

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

    public VBox getHealth() {
        return health;
    }

    public MyCardView getGun() {
        return gun;
    }
}
