package fr.umontpellier.iut.bang.views.ourviews;

import fr.umontpellier.iut.bang.BangIHM;
import fr.umontpellier.iut.bang.IPlayer;
import fr.umontpellier.iut.bang.logic.Player;
import fr.umontpellier.iut.bang.logic.cards.Card;
import fr.umontpellier.iut.bang.views.ResultsView;

import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class MyResultView extends ResultsView {
    private Button buttonPlayAgain;
    private Button buttonStop;
    private HBox basbutton;
    private ImageView logo;
    private ImageView titre;
    private HBox haut;
    private Label label;
    private HBox millieu;
    private VBox gagnant;
    private Rectangle rectangle;
    private ArrayList<Player> winners;
    private GridPane aliV ;
    private Pane imageder;
    private BorderPane tout;
    public MyResultView(BangIHM bangIHM, ArrayList<Player> winners){
        super(bangIHM);
        tout = new BorderPane();
        Scene scene =new Scene(tout);
        this.winners=winners;

        //label Est gagant
        label = new Label();
        label.setAlignment(Pos.CENTER);
        label.setText(" Le Gagnant est : ");
        label.setFont(Font.loadFont("file:src/main/resources/fonts/Graduate.ttf", 50));
        label.setTextFill(Color.web("#000000"));
        label.setStyle("-fx-font-weight: bold");
        label.setPadding(new Insets(100,0,0,250));

        //background
        ImageView background = new ImageView("src/main/resources/images/background.png");
        background.setFitWidth(1500);
        background.setFitHeight(750);
        tout.getChildren().add(background);

        // image derriere gagnant
        imageder = new Pane();
        rectangle = new Rectangle();
        rectangle.setWidth(350);
        rectangle.setHeight(380);
        rectangle.setArcWidth(140);
        rectangle.setArcHeight(140);
        rectangle.setFill(Color.rgb(217, 217, 217, 0.7));
        imageder.getChildren().add(rectangle);

        //alignement de image et nom
        aliV = new GridPane();
        aliV.setAlignment(Pos.CENTER);
        aliV.setPrefWidth(350);
        aliV.setPrefHeight(300);


        for (int i =0 ; i<winners.size();i++) {
            System.out.println("je suis rentré dans le for");
            String charactereImage= getImageCharacter(winners.get(i));
            System.out.println(charactereImage);
            ImageView imageCarte = new ImageView(charactereImage);
            imageCarte.setPreserveRatio(true);
            imageCarte.setFitHeight(200);
            Label nomJoueur = new Label(winners.get(i).getName());
            nomJoueur.setFont(Font.loadFont("file:src/main/resources/fonts/Graduate.ttf", 25));
            nomJoueur.setTextFill(Color.web("#000000"));
            nomJoueur.setStyle("-fx-font-weight: bold");
            nomJoueur.setAlignment(Pos.CENTER);
            aliV.setAlignment(Pos.CENTER);
            aliV.setHgap(5);
            aliV.add(imageCarte, i, 1);
            aliV.add(nomJoueur, i, 2);
        }
        imageder.getChildren().add(aliV);


        //création button Rejouer et set de l'action quand pressé
        buttonPlayAgain = new Button("Rejouer");
        buttonPlayAgain.setOnAction(event -> playAgain());
        buttonPlayAgain.setPrefWidth(250.0);
        buttonPlayAgain.setPrefHeight(50.0);
        buttonPlayAgain.setId("but");
        buttonPlayAgain.setFont(Font.loadFont("file:src/main/resources/fonts/Bangers.ttf", 40));
        buttonPlayAgain.getStylesheets().add(this.getClass().getClassLoader().getResource("src/main/resources/Css/accueil.css").toExternalForm());

        //création button quitter et set de l'action quand pressé
        buttonStop = new Button("Quitter");
        buttonStop.setOnAction(event -> stop());
        buttonStop.setPrefWidth(250.0);
        buttonStop.setPrefHeight(50.0);
        buttonStop.setId("but");
        buttonStop.setFont(Font.loadFont("file:src/main/resources/fonts/Bangers.ttf", 40));
        buttonStop.getStylesheets().add(this.getClass().getClassLoader().getResource("src/main/resources/Css/accueil.css").toExternalForm());


        //button en bas
        basbutton = new HBox(50);
        basbutton.getChildren().add(buttonPlayAgain);
        basbutton.getChildren().add(buttonStop);
        basbutton.setAlignment(Pos.CENTER);
        basbutton.setPadding(new Insets(0,0,25,0));

        // milieu
        millieu = new HBox();
        millieu.getChildren().add(label);
        millieu.getChildren().add(imageder);


        //logo
        logo = new ImageView("src/main/resources/images/logo.png");
        titre = new ImageView("src/main/resources/images/titre.png");

        logo.setFitWidth(200);
        logo.setFitHeight(200);
        titre.setFitHeight(200);
        haut = new HBox();
        haut.getChildren().addAll(logo, titre);
        haut.setAlignment(Pos.CENTER);



        tout.setBottom(basbutton);
        tout.setTop(haut);
        tout.setCenter(millieu);
        setWidth(1500);
        setHeight(750);
        setWinnersListener(winnerListenner);
        setScene(scene);
    }

    @Override
    public void playAgain(){
        getBangIHM().getPrimaryStage().hide();
        getBangIHM().initPlayersNames();
    }

    @Override
    public void stop(){
        getBangIHM().onStopGame();
    }

    private MyResultView getMyResultView (){
        return this;
    }


    private ListChangeListener<Player> winnerListenner = new ListChangeListener<Player>() {
        @Override
        public void onChanged(Change<? extends Player> change) {
            change.next();
            if(change.wasAdded()){
                winners.addAll(change.getAddedSubList());
                getBangIHM().initResultView(winners);
            }
        }
    };

    public String getImageCharacter(Player playerImg) {
        return "images/characters/" + playerImg.getBangCharacter().getName().toLowerCase().replaceAll(" ", "")+".png";
    }

}
