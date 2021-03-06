package fr.umontpellier.iut.bang.views.ourviews;

import fr.umontpellier.iut.bang.ICard;
import fr.umontpellier.iut.bang.IGame;
import fr.umontpellier.iut.bang.IPlayer;
import fr.umontpellier.iut.bang.logic.Player;
import fr.umontpellier.iut.bang.logic.Role;
import fr.umontpellier.iut.bang.logic.cards.Card;
import fr.umontpellier.iut.bang.views.CardView;
import fr.umontpellier.iut.bang.views.PlayerArea;
import fr.umontpellier.iut.bang.views.PlayerSelectionArea;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class MyPlayerSelectionArea extends PlayerSelectionArea {
    private BorderPane zoneClique;
    private Label nomJoueur;
    private HBox inPlay;
    private ImageView role;
    private Image r;
    private VBox health;
    private MyCardView gun;
    private MyPlayerArea playerArea;
    private Rectangle rectangle;
    private Rectangle dead;
    //private Button useMissed;

    public MyPlayerSelectionArea(MyPlayerArea playerArea) {
        super(playerArea);

        this.playerArea = playerArea;

        zoneClique = new BorderPane();
        nomJoueur = new Label(playerArea.getPlayer().getName());
        inPlay=playerArea.getInPlayJoueur();
        health=playerArea.getHealth();
        gun=playerArea.getGun();
        rectangle = new Rectangle();
        dead = new Rectangle();
        HBox triche = new HBox(); // triche permet un espace entre la carte Charactere et les cartes inplay
        triche.setMinHeight(5);
        triche.setMaxHeight(5); // inutile mais principalement pour éviter les problèmes
        // les setLayout et autre de inplay sont dans MyPlayerArea


        /*zoneClique.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,new BorderWidths(1))));
        zoneClique.setStyle("-fx-background-color: White");*/

        r = new Image(playerArea.getImageRole(playerArea.getPlayer()));
        ImageView carteCharacter = playerArea.getCard();
        role = new ImageView(r);
        role.setPreserveRatio(true);
        role.setFitHeight(125);
        role.setLayoutX(155);
        role.setLayoutY(10);
        role.setRotate(30);
        setVisible();
        setUnVisible();
        carteCharacter.setPreserveRatio(true);
        carteCharacter.setFitHeight(125);
        carteCharacter.setLayoutX(110);
        Pane h = new Pane();
        BorderPane règleproblème = new BorderPane(); // j'arrive pas a ajouter bien les points de vie donc je crée ça
        règleproblème.setLayoutX(100);
        règleproblème.setCenter(health);
        BorderPane gunRègleProblème = new BorderPane();
        gunRègleProblème.setLayoutX(50);
        gunRègleProblème.setLayoutY(40);
        gunRègleProblème.setCenter(gun);
        h.getChildren().add(role);
        h.getChildren().add(carteCharacter);
        h.getChildren().add(nomJoueur);
        h.getChildren().add(règleproblème);
        h.getChildren().add(gunRègleProblème);

        nomJoueur.setLayoutX(118);
        nomJoueur.setLayoutY(85);
        zoneClique.setPrefSize(300,200);
        zoneClique.setTop(h);
        zoneClique.setCenter(triche);
        /*zoneClique.setLeft(health);*/ // faut changer wola mais j'y arrive pas
        zoneClique.setBottom(inPlay);

        setPlayerSelectedListener();
        this.getChildren().add(zoneClique);

    }

    @Override
    public void setVisible() {
        role.setImage(r);
    }

    @Override
    public void setUnVisible() {
        if(getPlayerArea().getIPlayer().getRole() != Role.SHERIFF ){
            role.setImage(CardView.getBack());
        }
    }

    @Override
    protected void setPlayerSelectedListener() {
        zoneClique.setOnMouseClicked(playerSelected);
    }

    //Mettre en couleur les joueurs que je peux cibler
    /* public void setOtherPlayerArea(MyGameView myGameView) {
        for (Player p :getPlayerArea().getPlayer().getPlayersInRange(getPlayerArea().getPlayer().getWeaponRange())){
            myGameView.findPlayerArea(p).highlightCurrentArea();
            if (myGameView.game.getCurrentPlayer().)
        }

    }*/
    private EventHandler<MouseEvent> playerSelected = event ->{
        IPlayer playerSelect = getPlayerArea().getIPlayer();
        IGame currentGame = getPlayerArea().getGameView().getIGame();
        currentGame.onTargetSelection(playerSelect);


    };

    @Override
    public PlayerArea getPlayerArea() {
        return playerArea;
    }

    public void setRectangle(Rectangle rect){
        rectangle = rect;
        getChildren().add(rectangle);
    }

    public void setRectangleTransparant(){
        rectangle.setOpacity(0);
        getChildren().remove(rectangle);
    }

    public void setDead(Rectangle rect){
        dead = rect;
        getChildren().add(dead);
    }

    public void setLabelDead(Label d){
        getChildren().add(d);
    }



   /* public void setBouttonMissed(Button useMissed){
        this.useMissed = useMissed;
        getChildren().add(this.useMissed);
    }
    public void removeMissed(){
        getChildren().remove(useMissed);
    }*/

}
