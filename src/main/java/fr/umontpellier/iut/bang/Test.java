package fr.umontpellier.iut.bang;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class Test extends Application {
    private BorderPane root;
    private Pane panneau;
    private Scene scene;
    private HBox haut;

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        root=new BorderPane();
        root.setMaxWidth(1500);
        root.setMaxHeight(1000);

        panneau=new Pane();
        ImageView logo = new ImageView("ressources/logo.png");
        ImageView titre = new ImageView("ressources/titre.png");
        haut = new HBox();

        logo.setFitWidth(200);
        logo.setFitHeight(200);
        titre.setFitHeight(200);
        haut.getChildren().addAll(logo, titre);
        haut.setAlignment(Pos.CENTER);
        haut.setPadding(new Insets(50,0,0,0));

        root.setCenter(panneau);
        root.setId("pane");
        root.setTop(haut);
        scene = new Scene(root);
        scene.getStylesheets().add(this.getClass().getClassLoader().getResource("src/main/resources/Css/accueil.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Bang");
        primaryStage.setHeight(700);
        primaryStage.setWidth(1250);
        primaryStage.show();


    }
}
