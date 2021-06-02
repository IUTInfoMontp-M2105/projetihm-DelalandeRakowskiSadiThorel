package fr.umontpellier.iut.bang.views.ourviews;

import fr.umontpellier.iut.bang.views.StartView;
import javafx.beans.value.ChangeListener;
import javafx.collections.ListChangeListener;
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
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;

public class MyStartView extends StartView {

    private HBox haut; // logo + titre
    private Label label; // message bienvenue
    private VBox bas; // le bouton
    private Button buttonLancer = new Button("Lancer une partie");

    public MyStartView() {
        super();

        ImageView logo = new ImageView("ressources/logo.png");
        ImageView titre = new ImageView("ressources/titre.png");
        haut = new HBox();

        logo.setFitWidth(200);
        logo.setFitHeight(200);
        titre.setFitHeight(200);
        haut.getChildren().addAll(logo, titre);
        haut.setAlignment(Pos.CENTER);
        haut.setPadding(new Insets(50,0,0,0));

        bas = new VBox(50);
        bas.setAlignment(Pos.CENTER);
        bas.setPadding(new Insets(0,0,50,0));

        buttonLancer.setTextFill(Color.BLACK);
        buttonLancer.setFont(Font.loadFont("file:ressources/Bangers.ttf", 40));
        buttonLancer.setPrefWidth(500);


    }

    @Override
    public void setPlayersListSetListener(ListChangeListener<String> whenPlayersNamesListIsSet) {

    }

    @Override
    protected void setNbPlayersChangeListener(ChangeListener<Integer> whenNbPlayersChanged) {

    }

    @Override
    protected int getNumberOfPlayers() {
        return 0;
    }

    @Override
    protected String getPlayerNameByNumber(int playerNumber) {
        return null;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
