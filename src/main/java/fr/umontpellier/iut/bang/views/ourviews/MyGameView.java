package fr.umontpellier.iut.bang.views.ourviews;

import fr.umontpellier.iut.bang.BangIHM;
import fr.umontpellier.iut.bang.ICard;
import fr.umontpellier.iut.bang.IGame;
import fr.umontpellier.iut.bang.IPlayer;
import fr.umontpellier.iut.bang.logic.Player;
import fr.umontpellier.iut.bang.views.GameView;
import fr.umontpellier.iut.bang.views.PlayerArea;
import javafx.application.HostServices;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MyGameView extends GameView {
    Button buttonPasser;
    Pane tout = new Pane();
    List<MyPlayerArea> mains = new ArrayList<>();
    IGame game;
    BangIHM bangIHM;

    public MyGameView(IGame game,BangIHM bangIHM) {
        super(game);
        this.game=game;
        this.bangIHM=bangIHM;


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
        //label consulter les règles
        Label rules = new Label("Règles");
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
        //création button passer et set de l'action quand pressé
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
            VBox v = new VBox();
            Pane joueur = new Pane();
            HBox inGame = new HBox();
            v.getChildren().add(joueur);
            v.getChildren().add(inGame);
            listJoueurPointer.add(v);
            v.setBorder(new Border(new BorderStroke(Color.BLACK,
                    BorderStrokeStyle.SOLID,
                    CornerRadii.EMPTY,new BorderWidths(1))));
            tout.getChildren().add(v);
            v.setPrefHeight(225);
            v.setPrefWidth(300);
            ImageView carteCharacter = mains.get(i).getCard();
            carteCharacter.setPreserveRatio(true);
            carteCharacter.setFitHeight(125);
            carteCharacter.setLayoutX(110);
            Label nomJoueur = new Label(mains.get(i).getPlayer().getName());
            nomJoueur.setLayoutX(118);
            nomJoueur.setLayoutY(85);
            joueur.getChildren().add(carteCharacter);
            joueur.getChildren().add(nomJoueur);
        }
        listJoueurPointer.get(0).setLayoutX(425);
        listJoueurPointer.get(0).setLayoutY(20);
        listJoueurPointer.get(1).setLayoutX(425);
        listJoueurPointer.get(1).setLayoutY(475);
        listJoueurPointer.get(2).setLayoutX(100); // c'est seulement pour l'instant (prévoit que 4 joueur)
        listJoueurPointer.get(2).setLayoutY(250); // c'est dégueux d'ailleurs
        listJoueurPointer.get(3).setLayoutX(750);
        listJoueurPointer.get(3).setLayoutY(250);


        //Pioche et défausse
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
        emplacementPioche.setLayoutY(270);
        emplacementPioche.setPrefHeight(180);
        emplacementPioche.setPrefWidth(100);
        tout.getChildren().add(emplacementPioche);

        ImageView piochec = new ImageView("src/main/resources/images/characters/bartcassidy.png");
        piochec.setPreserveRatio(true);
        piochec.setFitHeight(150);
        pioche.getChildren().add(piochec); // exemple card


        //défausse
        VBox emplacementDefausse = new VBox();
        Pane defausse = new Pane();
        Label labelDefausse = new Label("Défausse");
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
        emplacementDefausse.setLayoutY(270);
        emplacementDefausse.setPrefHeight(180);
        emplacementDefausse.setPrefWidth(100);
        tout.getChildren().add(emplacementDefausse);

        ImageView defaussec = new ImageView("src/main/resources/images/characters/bartcassidy.png");
        defaussec.setPreserveRatio(true);
        defaussec.setFitHeight(150);
        defausse.getChildren().add(defaussec); // exemple card


        setWidth(1500);
        setHeight(750);
        getChildren().add(tout);
        setCurrentPlayerChangesListener(whenCurrentPlayerChanges); // quand le joueur courant cange faire
        setPassSelectedListener();
        getIGame().run();

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
    protected void setPassSelectedListener() {          // ça marche pas car current player ne change pas
        buttonPasser.setOnAction(event -> getIGame().onPass());


    }
    private PlayerArea findPlayerArea(Player player) {
        for (Node n : mains) {
            PlayerArea nodePlayerArea = (PlayerArea) n;
            Player nodePlayer = nodePlayerArea.getPlayer();
            if (nodePlayer.equals(player))
                return nodePlayerArea;
        }
        return null;
    }
    private ChangeListener<? super Player> whenCurrentPlayerChanges = new ChangeListener<Player>() {
        @Override
        public void changed(ObservableValue<? extends Player> observableValue, Player oldplayer, Player newPlayer) {
            System.out.println("le Joueur courrant à changé"); // change la main du joueur pour celle du courant
            if(oldplayer!=null){
                tout.getChildren().remove(findPlayerArea(oldplayer));
            }
            tout.getChildren().add(findPlayerArea(newPlayer));
        }
    };
}
