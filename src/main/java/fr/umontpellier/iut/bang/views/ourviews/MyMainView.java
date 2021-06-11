package fr.umontpellier.iut.bang.views.ourviews;

import fr.umontpellier.iut.bang.BangIHM;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MyMainView extends Stage {

    private ImageView logo;
    private Pane haut;
    private Rectangle rectangle;

    public MyMainView(BangIHM bangIHM){
        Pane tout = new Pane();
        Scene scene =new Scene(tout);



        //background
        ImageView background = new ImageView("src/main/resources/images/background.png");
        background.setFitWidth(1500);
        background.setFitHeight(750);
        tout.getChildren().add(background);

        // image derriere Jeu (joueur ect...)
        MyGameView imageJeu = new MyGameView(bangIHM.getIGame());
        imageJeu.setLayoutX(75);
        imageJeu.setLayoutY(7.5);
        rectangle = new Rectangle();
        rectangle.setWidth(1000);
        rectangle.setHeight(700);
        rectangle.setArcWidth(140);
        rectangle.setArcHeight(140);
        rectangle.setFill(Color.rgb(217, 217, 217, 0.7));
        imageJeu.getChildren().add(rectangle);

        //Outils
        ImageView parchemin = new ImageView("src/main/resources/images/parchemin.png");
        parchemin.setPreserveRatio(true);
        parchemin.setFitHeight(125);
        parchemin.setLayoutX(1375);
        tout.getChildren().add(parchemin);







        //logo
        logo = new ImageView("src/main/resources/images/logo.png");

        logo.setFitWidth(75);
        logo.setPreserveRatio(true);
        haut = new Pane();
        haut.getChildren().add(logo);




        tout.getChildren().add(haut);
        tout.getChildren().add(imageJeu);
        setWidth(1500);
        setHeight(750);
        setScene(scene);
    }
}
