package fr.umontpellier.iut.bang.views.ourviews;

import fr.umontpellier.iut.bang.BangIHM;
import fr.umontpellier.iut.bang.ICard;
import fr.umontpellier.iut.bang.IGame;
import fr.umontpellier.iut.bang.IPlayer;
import fr.umontpellier.iut.bang.logic.Game;
import fr.umontpellier.iut.bang.logic.GameState;
import fr.umontpellier.iut.bang.logic.Player;
import fr.umontpellier.iut.bang.logic.Role;
import fr.umontpellier.iut.bang.logic.cards.Card;
import fr.umontpellier.iut.bang.views.CardView;
import fr.umontpellier.iut.bang.views.GameView;
import fr.umontpellier.iut.bang.views.PlayerArea;
import fr.umontpellier.iut.bang.views.PlayerSelectionArea;
import javafx.application.HostServices;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MyGameView extends GameView {
    private Button buttonPasser;
    private Pane tout = new Pane();
    private List<MyPlayerArea> mains = new ArrayList<>();
    private IGame game;
    private BangIHM bangIHM;
    private Label currentState = new Label();
    private List<MyPlayerSelectionArea> joueursArea;
    private ImageView hautDefosse;
    private Pane defausse;


    public MyGameView(IGame game,BangIHM bangIHM) {
        super(game);
        this.game=game;
        this.bangIHM=bangIHM;
        joueursArea = new ArrayList<>();
        //initialisation main joueur
        for (Player p : game.getPlayers()) {
            MyPlayerArea imageMain = new MyPlayerArea(new IPlayer(p), this);
            mains.add(imageMain);
        }

        //background
        ImageView background = new ImageView("src/main/resources/images/background.png");
        background.setFitWidth(1500);
        background.setFitHeight(750);
        tout.getChildren().add(background);
        //logo
        ImageView logo = new ImageView("src/main/resources/images/logo.png");
        logo.setFitWidth(75);
        logo.setPreserveRatio(true);
        tout.getChildren().add(logo);


        // image derriere Jeu (joueur ect...)
        Pane imageJeu = new Pane();
        imageJeu.setLayoutX(75);
        imageJeu.setLayoutY(7.5);
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(1000);
        rectangle.setHeight(700);
        rectangle.setArcWidth(140);
        rectangle.setArcHeight(140);
        rectangle.setFill(Color.rgb(217, 217, 217, 0.7));
        Label votreTable = new Label("Table de Jeu :");
        votreTable.setLayoutX(45);
        votreTable.setLayoutY(15);
        votreTable.setStyle("-fx-font-size: 20");
        imageJeu.getChildren().add(rectangle);
        imageJeu.getChildren().add(votreTable);
        tout.getChildren().add(imageJeu);

        /** image derriere Main (main du joueur ect ....)**/

        /*Player p = bangIHM.getIGame().getPlayers().get(0);
        p.addToHand(p.drawCard());
        System.out.println(p.getHand());*/

        for (MyPlayerArea p : mains) {

            p.setLayoutX(1100);
            p.setLayoutY(300);
            Rectangle rectangleMain = new Rectangle();
            rectangleMain.setWidth(375);
            rectangleMain.setHeight(350);
            rectangleMain.setArcWidth(140);
            rectangleMain.setArcHeight(140);
            rectangleMain.setFill(Color.rgb(217, 217, 217, 0.7));
            Label votreMain = new Label("Votre Main "+p.getPlayer().getName()+" :");
            votreMain.setLayoutX(45);
            votreMain.setLayoutY(15);
            votreMain.setStyle("-fx-font-size: 20");
            p.getChildren().add(rectangleMain);
            p.getChildren().add(votreMain);
            p.getChildren().add(p.getMainJoueur());
            p.getMainJoueur().setLayoutX(10);
            p.getMainJoueur().setLayoutY(100);

        }


        //Cartes


        //Outils
        ImageView parchemin = new ImageView("src/main/resources/images/parchemin.png");
        Pane outils = new Pane(parchemin);
        parchemin.setPreserveRatio(true);
        parchemin.setFitHeight(125);
        outils.setLayoutX(1375);
        //label outils
        Text labelOutils = new Text("Outils :");
        labelOutils.setUnderline(true);
        labelOutils.setLayoutY(25);
        labelOutils.setLayoutX(30);
        outils.getChildren().add(labelOutils);
        //label consulter les r??gles
        Label rules = new Label("R??gles");
        rules.setStyle("-fx-font-weight: bold");
        ImageView pdf = new ImageView("src/main/resources/images/pdf.png");
        pdf.setPreserveRatio(true);
        pdf.setFitHeight(15);
        rules.setGraphic(pdf);
        rules.setLayoutY(50);
        rules.setLayoutX(25);
        rules.setOnMouseClicked(mouseEvent -> lirePdfRegles(bangIHM));
        outils.getChildren().add(rules);

        tout.getChildren().add(outils);

        //Bouton passer
        //cr??ation button passer et set de l'action quand press??
        buttonPasser = new Button("Passer");
        buttonPasser.setLayoutX(1167);
        buttonPasser.setLayoutY(660);
        buttonPasser.setPrefWidth(250.0);
        buttonPasser.setPrefHeight(25);
        buttonPasser.setId("but");
        buttonPasser.setFont(Font.loadFont("file:src/main/resources/fonts/Bangers.ttf", 25));
        buttonPasser.getStylesheets().add(this.getClass().getClassLoader().getResource("src/main/resources/Css/accueil.css").toExternalForm());
        tout.getChildren().add(buttonPasser);


        //Emplacement pour les joueurs
        List<Node> listJoueurPointer = new ArrayList<>();
        for (int i=0; i<4;i++){ // seulement 4 joueur changer 4 pour game.getPlayers().size() pour plus de joueur
            MyPlayerArea v = new MyPlayerArea(new IPlayer(game.getPlayers().get(i)),this);

            MyPlayerSelectionArea pA = new MyPlayerSelectionArea(v);
            listJoueurPointer.add(pA);
            tout.getChildren().add(pA);
            joueursArea.add(pA);

        }
        listJoueurPointer.get(0).setLayoutX(425);
        listJoueurPointer.get(0).setLayoutY(15);
        listJoueurPointer.get(1).setLayoutX(750);
        listJoueurPointer.get(1).setLayoutY(250);
        listJoueurPointer.get(2).setLayoutX(425); // c'est seulement pour l'instant (pr??voit que 4 joueur)
        listJoueurPointer.get(2).setLayoutY(450); // c'est d??gueux d'ailleurs
        listJoueurPointer.get(3).setLayoutX(100);
        listJoueurPointer.get(3).setLayoutY(250);


        //Pioche et d??fausse
        //pioche
        VBox emplacementPioche = new VBox();
        Pane pioche = new Pane();
        Label labelPioche = new Label("Pioche");
        labelPioche.setId("but");
        labelPioche.setFont(Font.loadFont("file:src/main/resources/fonts/Bangers.ttf", 15));
        labelPioche.setMaxHeight(15);
        emplacementPioche.setAlignment(Pos.CENTER);
        emplacementPioche.getChildren().add(labelPioche);
        emplacementPioche.getChildren().add(pioche);
        emplacementPioche.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,new BorderWidths(1))));
        emplacementPioche.setLayoutX(460);
        emplacementPioche.setLayoutY(265);
        emplacementPioche.setPrefHeight(180);
        emplacementPioche.setPrefWidth(100);
        tout.getChildren().add(emplacementPioche);

        ImageView piochec = new ImageView(CardView.getBack());
        piochec.setPreserveRatio(true);
        piochec.setFitHeight(150);
        pioche.getChildren().add(piochec); // exemple card


        //d??fausse
        VBox emplacementDefausse = new VBox();
        defausse = new Pane();
        //Card haut = game.getGame().getDiscardPile().getFirst();

        Label labelDefausse = new Label("D??fausse");
        labelDefausse.setId("but");
        labelDefausse.setFont(Font.loadFont("file:src/main/resources/fonts/Bangers.ttf", 15));
        labelDefausse.setMaxHeight(15);
        emplacementDefausse.setAlignment(Pos.CENTER);
        emplacementDefausse.getChildren().add(labelDefausse);
        emplacementDefausse.getChildren().add(defausse);
        emplacementDefausse.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,new BorderWidths(1))));
        emplacementDefausse.setLayoutX(585);
        emplacementDefausse.setLayoutY(265);
        emplacementDefausse.setPrefHeight(180);
        emplacementDefausse.setPrefWidth(100);
        tout.getChildren().add(emplacementDefausse);

        /*Image hautDefausse = new Image(haut.getImageName());
        ImageView defaussec = new ImageView(hautDefausse);
        defaussec.setPreserveRatio(true);
        defaussec.setFitHeight(150);
        defausse.getChildren().add(defaussec); // exemple card */


        //Label Current State
        currentState.setLayoutX(1090);
        currentState.setLayoutY(250);
        currentState.setMaxHeight(50);
        currentState.setFont(Font.loadFont("file:src/main/resources/fonts/Bangers.ttf", 23));
        tout.getChildren().add(currentState);
        setCurrentStateChangesListener(whenCurrentStateChanges);

        setRemoveDeadPlayerAreaListener(deadPlayerAreaListener);
        setWidth(1500);
        setHeight(750);
        getChildren().add(tout);
        setCurrentPlayerChangesListener(whenCurrentPlayerChanges); // quand le joueur courant change faire
        setPassSelectedListener();
        getIGame().run();
        setCurrentAttackChangesListener(currentAttackChangesListener);
        currentState.setText(game.getCurrentPlayer().getName() + " Choose card to play"); // texte initiale de currentState


    }
    private void lirePdfRegles(BangIHM bangIHM){
        File file = new File("src/main/resources/pdf/Bang-regles.pdf");
        HostServices hostServices = bangIHM.getHostServices();
        hostServices.showDocument(file.getAbsolutePath());
    }

    @Override
    protected void bindNextActionMessage() {

    }


    @Override
    protected void setPassSelectedListener() {          // ??a marche pas car current player ne change pas
        buttonPasser.setOnAction(event -> getIGame().onPass());


    }
    private MyPlayerArea findPlayerArea(Player player) {
        for (Node n : mains) {
            MyPlayerArea nodePlayerArea = (MyPlayerArea) n;
            Player nodePlayer = nodePlayerArea.getPlayer();
            if (nodePlayer.equals(player))
                return nodePlayerArea;
        }
        return null;
    }
    private ChangeListener<? super Player> whenCurrentPlayerChanges = new ChangeListener<Player>() {
        @Override
        public void changed(ObservableValue<? extends Player> observableValue, Player oldplayer, Player newPlayer) {
            //System.out.println("le Joueur courrant ?? chang??"); // change la main du joueur pour celle du courant
            Rectangle r = new Rectangle();
            if(oldplayer!=null){

                for(MyPlayerSelectionArea p : joueursArea){
                    if (p.getPlayerArea().getPlayer().equals(oldplayer)){
                        p.setUnVisible();
                        p.setRectangleTransparant();
                    }
                }
                tout.getChildren().remove(findPlayerArea(oldplayer));


            }
            for(MyPlayerSelectionArea p : joueursArea){
                if (p.getPlayerArea().getPlayer().equals(newPlayer)){
                    p.setVisible();

                    r.setHeight(200);
                    r.setWidth(300);
                    r.setOpacity(0.4);
                    r.setFill(Color.LIGHTBLUE);
                    p.setRectangle(r);
                }
            }

            tout.getChildren().add(findPlayerArea(newPlayer));

        }
    };


    private ChangeListener<GameState> whenCurrentStateChanges = new ChangeListener<GameState>() {
        @Override
        public void changed(ObservableValue<? extends GameState> observableValue, GameState gameState, GameState t1) {
            currentState.setText(t1.toString());
        }
    };

    /**
     * Pour d??finir l'action ?? ex??cuter lorsqu'une carte d'attaque vient d'??tre jou??e
     */
    private ChangeListener<Card> currentAttackChangesListener = new ChangeListener<Card>() {
        @Override
        public void changed(ObservableValue<? extends Card> observableValue, Card oldCard, Card newCard) {
            if(newCard !=null){
                System.out.println("je rentre dans l'attaque");
                //setHautDefosse(newCard);
                /*for (MyPlayerSelectionArea myPlayerSelectionArea : joueursArea) {
                    System.out.println(getIGame().getPossibleTargets());
                    if (getIGame().getPossibleTargets().contains(myPlayerSelectionArea.getPlayerArea().getPlayer())) {
                    }
                }*/

            }
        }
    };

    private ListChangeListener<Player> deadPlayerAreaListener = new ListChangeListener<Player>() {
        @Override
        public void onChanged(Change<? extends Player> change) {
            change.next();
            if(change.wasRemoved()){
                Rectangle dead = new Rectangle();
                Label isDead = new Label("Dead");
                isDead.setTextFill(Color.RED);
                isDead.setAlignment(Pos.CENTER);
                dead.setOpacity(0.9);
                dead.setFill(Color.BLACK);
                dead.setOpacity(0.5);
                dead.setHeight(200);
                dead.setWidth(300);
                isDead.setFont(Font.loadFont("file:src/main/resources/fonts/Bangers.ttf", 15));

                for (Player p : change.getRemoved()){
                    for(MyPlayerSelectionArea pA : joueursArea){
                        if (pA.getPlayerArea().getPlayer().equals(p)){
                            pA.setDead(dead);
                            pA.setLabelDead(isDead);
                        }
                    }

                }
            }
        }
    };

    public MyPlayerArea getPlayerAeraCourante(MyPlayerArea courante){
        return findPlayerArea(courante.getGameView().getIGame().getCurrentPlayer());
    }

    public void setHautDefosse(Card haut){
        Image carte = new Image(haut.getImageName());
        hautDefosse = new ImageView(carte);
        hautDefosse.setPreserveRatio(true);
        hautDefosse.setFitHeight(150);
        defausse.getChildren().add(hautDefosse);
    }

    public MyGameView getMyGameView(){
        return this;
    }
}