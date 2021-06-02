package fr.umontpellier.iut.bang;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Accueil extends Application {

    private BorderPane root;
        private Pane panneau;
        private Scene scene;
        private HBox haut; // logo + titre
        private Label label; // message bienvenue
        private HBox bas; // les boutons
        private Button buttonCreer = new Button("créer une partie");
        private Button buttonRejoindre = new Button("rejoindre une partie");


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

            label = new Label();
            label.setAlignment(Pos.CENTER);
            label.setText(" Bienvenue dans le far-west ");
            label.setFont(Font.loadFont("file:ressources/Graduate.ttf", 50));
            label.setTextFill(Color.web("#000000"));
            label.setStyle("-fx-font-weight: bold");
            label.setPadding(new Insets(-200,0,-100,0));


            bas = new HBox(50);
            bas.setAlignment(Pos.CENTER);
            bas.setPadding(new Insets(0,0,50,0));

            buttonCreer.setTextFill(Color.BLACK);
            buttonCreer.setFont(Font.loadFont("file:ressources/Bangers.ttf", 40));
            buttonCreer.setPrefWidth(500);

            buttonRejoindre.setTextFill(Color.BLACK);
            buttonRejoindre.setFont(Font.loadFont("file:ressources/Bangers.ttf", 40));
            buttonRejoindre.setPrefWidth(500);
            bas.getChildren().addAll(buttonCreer, buttonRejoindre);


            root.setCenter(panneau);
            root.setId("pane");
            root.setTop(haut);
            root.setCenter(label);
            root.setBottom(bas);
            scene = new Scene(root);
            scene.getStylesheets().add(this.getClass().getClassLoader().getResource("src/main/resources/Css/accueil.css").toExternalForm());

            buttonCreer.setId("but");
            buttonRejoindre.setId("but");
            buttonCreer.getStylesheets().add(this.getClass().getClassLoader().getResource("src/main/resources/Css/accueil.css").toExternalForm());

            primaryStage.setScene(scene);
            primaryStage.setTitle("Bang");
            primaryStage.setHeight(700);
            primaryStage.setWidth(1250);
            primaryStage.show();


        }


}
