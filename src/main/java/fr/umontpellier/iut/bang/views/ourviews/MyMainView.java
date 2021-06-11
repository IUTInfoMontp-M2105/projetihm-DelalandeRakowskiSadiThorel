package fr.umontpellier.iut.bang.views.ourviews;

import fr.umontpellier.iut.bang.BangIHM;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;

public class MyMainView extends Stage {


    public MyMainView(BangIHM bangIHM) {
        Pane tout = new Pane();
        Scene scene = new Scene(tout);

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
        MyGameView imageJeu = new MyGameView(bangIHM.getIGame());
        imageJeu.setLayoutX(75);
        imageJeu.setLayoutY(7.5);
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(1000);
        rectangle.setHeight(700);
        rectangle.setArcWidth(140);
        rectangle.setArcHeight(140);
        rectangle.setFill(Color.rgb(217, 217, 217, 0.7));
        imageJeu.getChildren().add(rectangle);
        tout.getChildren().add(imageJeu);

        // image derriere Main (main du joueur ect ....)
        MyGameView imageMain = new MyGameView(bangIHM.getIGame()); // A CHANGER IMPERATIVEMENT CAR CE
        imageMain.setLayoutX(1100);                                 // ne doit pas être une GameView
        imageMain.setLayoutY(350);
        Rectangle rectangleMain = new Rectangle();
        rectangleMain.setWidth(375);
        rectangleMain.setHeight(350);
        rectangleMain.setArcWidth(140);
        rectangleMain.setArcHeight(140);
        rectangleMain.setFill(Color.rgb(217, 217, 217, 0.7));
        imageMain.getChildren().add(rectangleMain);
        tout.getChildren().add(imageMain);


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












        setWidth(1500);
        setHeight(750);
        setScene(scene);
    }
    private void lirePdfRegles(BangIHM bangIHM){
        File file = new File("src/main/resources/pdf/Bang-regles.pdf");
        HostServices hostServices = bangIHM.getHostServices();
        hostServices.showDocument(file.getAbsolutePath());
    }
}
