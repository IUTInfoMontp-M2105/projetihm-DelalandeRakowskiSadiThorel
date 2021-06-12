package fr.umontpellier.iut.bang.views.ourviews;

import fr.umontpellier.iut.bang.IGame;
import fr.umontpellier.iut.bang.IPlayer;
import fr.umontpellier.iut.bang.views.PlayerArea;
import fr.umontpellier.iut.bang.views.PlayerSelectionArea;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class MyPlayerSelectionArea extends PlayerSelectionArea {
    private BorderPane zoneClique;
    private Label nomJoueur;
    private HBox inPlay;
    public MyPlayerSelectionArea(MyPlayerArea playerArea) {

        super(playerArea);
        zoneClique = new BorderPane();
        nomJoueur = new Label(playerArea.getPlayer().getName());
        inPlay=playerArea.getInPlayJoueur();
        HBox triche = new HBox(); // triche permet un espace entre la carte Charactere et les cartes inplay
        triche.setMinHeight(5);
        triche.setMaxHeight(5); // inutile mais principalement pour éviter les problèmes
        // les setLayout et autre de inplay sont dans MyPlayerArea


        /*zoneClique.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,new BorderWidths(1))));
        zoneClique.setStyle("-fx-background-color: White");*/

        ImageView carteCharacter = playerArea.getCard();
        carteCharacter.setPreserveRatio(true);
        carteCharacter.setFitHeight(125);
        carteCharacter.setLayoutX(110);
        Pane h = new Pane();
        h.getChildren().add(carteCharacter);
        h.getChildren().add(nomJoueur);

        nomJoueur.setLayoutX(118);
        nomJoueur.setLayoutY(85);

        zoneClique.setPrefSize(300,200);
        zoneClique.setTop(h);
        zoneClique.setCenter(triche);
        zoneClique.setBottom(inPlay);
        this.getChildren().add(zoneClique);
        setPlayerSelectedListener();
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
        zoneClique.setOnMouseClicked(playerSelected);
    }

    private EventHandler<MouseEvent> playerSelected = event ->{
        IPlayer playerSelect = getPlayerArea().getIPlayer();
        IGame currentGame = getPlayerArea().getGameView().getIGame();
        currentGame.onTargetSelection(playerSelect);
        System.out.println(this.getPlayerArea().getPlayer().getName()+"viiissssse" + playerSelect.getName());
    };
}
