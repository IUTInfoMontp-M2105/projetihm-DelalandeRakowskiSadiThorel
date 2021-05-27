package fr.umontpellier.iut.bang;

import javafx.application.Application;
import javafx.css.Style;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Test extends Application {
    private BorderPane root;
    private Pane panneau;
    private Scene scene;

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        root=new BorderPane();
        root.setMaxWidth(1500);
        root.setMaxHeight(750);

        panneau=new Pane();
        root.setCenter(panneau);
        root.setId("pane");
        scene = new Scene(root);

        scene.getStylesheets().add(this.getClass().getClassLoader().getResource("src/main/resources/Css/accueil.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Bang");
        primaryStage.setHeight(750);
        primaryStage.setWidth(1500);
        primaryStage.show();
    }
}
