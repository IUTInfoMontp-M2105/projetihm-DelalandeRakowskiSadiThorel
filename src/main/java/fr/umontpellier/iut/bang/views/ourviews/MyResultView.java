package fr.umontpellier.iut.bang.views.ourviews;

import fr.umontpellier.iut.bang.BangIHM;
import fr.umontpellier.iut.bang.views.ResultsView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Optional;

import static javafx.application.Application.launch;

public class MyResultView extends ResultsView {
    private Button buttonPlayAgain;
    private Button buttonStop;
    private HBox basbutton;
    private ImageView logo;
    private ImageView titre;
    private HBox haut;
    private Label label;
    private HBox millieu;


    public MyResultView(BangIHM bangIHM){
        super(bangIHM);
        BorderPane tout = new BorderPane();
        Scene scene =new Scene(tout);

        //label
        label = new Label();
        label.setAlignment(Pos.CENTER);
        label.setText(" Le Gagnant est : ");
        label.setFont(Font.loadFont("file:src/main/resources/fonts/Graduate.ttf", 50));
        label.setTextFill(Color.web("#000000"));
        label.setStyle("-fx-font-weight: bold");
        label.setPadding(new Insets(100,0,0,250));

        //background
        ImageView background = new ImageView("src/main/resources/images/background.png");
        background.setFitWidth(1550);
        background.setFitHeight(750);
        tout.getChildren().add(background);

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
        basbutton.setPadding(new Insets(0,0,50,0));

        // milieu
        millieu = new HBox();
        millieu.getChildren().add(label);

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
        getBangIHM().startGame();
    }

    @Override
    public void stop(){
        getBangIHM().onStopGame();
    }

}
