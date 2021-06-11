package fr.umontpellier.iut.bang.views.ourviews;

import fr.umontpellier.iut.bang.BangIHM;
import fr.umontpellier.iut.bang.views.ResultsView;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class MyResultView extends ResultsView {
    private Button buttonPlayAgain;
    private Button buttonStop;
    private HBox basbutton;
    private ImageView logo;
    private ImageView titre;
    private HBox haut;
    private Label label;
    private Label nomJoueur;
    private HBox millieu;
    private VBox gagnant;
    private Rectangle rectangle;

    public MyResultView(BangIHM bangIHM){
        super(bangIHM);
        BorderPane tout = new BorderPane();
        Scene scene =new Scene(tout);

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
        Pane imageder = new Pane();
        rectangle = new Rectangle();
        rectangle.setWidth(350);
        rectangle.setHeight(380);
        rectangle.setArcWidth(140);
        rectangle.setArcHeight(140);
        rectangle.setFill(Color.rgb(217, 217, 217, 0.7));
        imageder.getChildren().add(rectangle);

        //alignement de image et nom
        VBox aliV = new VBox();
        /*aliV.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,new BorderWidths(1))));*/
        HBox aliNom = new HBox();
        HBox aliImg = new HBox();
        aliV.setAlignment(Pos.CENTER);
        aliV.setPrefWidth(350);
        aliV.setPrefHeight(300);
        aliImg.setAlignment(Pos.CENTER);
        aliNom.setAlignment(Pos.CENTER);
        aliV.getChildren().add(aliImg);
        aliV.getChildren().add(aliNom);
        imageder.getChildren().add(aliV);

        // gagnant :
        /*bangIHM.getIGame().winnersProperty().get(0).getBangCharacter();*/

        // nomDuJoueur (label)
        Label nomDuJoueur = new Label("Super Man");
        nomDuJoueur.setAlignment(Pos.CENTER);
        nomDuJoueur.setPrefWidth(350);
                /*nomDuJoueur.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,new BorderWidths(1))));*/
        nomDuJoueur.setFont(Font.loadFont("file:src/main/resources/fonts/Graduate.ttf", 25));
        nomDuJoueur.setTextFill(Color.web("#000000"));
        nomDuJoueur.setStyle("-fx-font-weight: bold");
        aliNom.getChildren().add(nomDuJoueur);

        // image du gagnant
        String stringGa = "src/main/resources/images/characters/bartcassidy.png";
        ImageView imgGagnant = new ImageView(stringGa);
        imgGagnant.setPreserveRatio(true);
        imgGagnant.setFitHeight(200);
        aliImg.getChildren().add(imgGagnant);


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

}
