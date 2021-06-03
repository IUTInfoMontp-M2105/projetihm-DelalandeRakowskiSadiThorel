package fr.umontpellier.iut.bang.views.ourviews;

import fr.umontpellier.iut.bang.views.StartView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MyStartView extends StartView {

    private Label titre;
    private GridPane parametrePartie;


    public MyStartView() {
        super();
        setWidth(1500);
        setHeight(750);
        /*BorderPane root;
        Pane panneau;
        Scene scene;

        root=new BorderPane();
        root.setMaxWidth(1500);
        root.setMaxHeight(1000);

        panneau=new Pane();
        root.setCenter(panneau);
        root.setId("pane");
        scene = new Scene(root);

        scene.getStylesheets().add(this.getClass().getClassLoader().getResource("src/main/resources/Css/accueil.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setHeight(1000);
        primaryStage.setWidth(1500);
        primaryStage.show();*/
        BorderPane root = new BorderPane();
        TextField joueurNom = new TextField("saisir nom");
        joueurNom.setAlignment(Pos.CENTER);
        parametrePartie = new GridPane();
        titre = new Label("BANGGG!!!!");
        Label j = new Label("J1");
        setNbPlayersChangeListener(whenNbPlayersChanged);
        parametrePartie.setStyle("-fx-background-color: #b8b2b2");
        parametrePartie.setAlignment(Pos.CENTER);
        parametrePartie.setMaxHeight(300);
        parametrePartie.setMaxWidth(1000);
        parametrePartie.add(joueurNom,2,1);
        parametrePartie.add(j,1,1);
        root.setCenter(parametrePartie);
        root.setTop(titre);

        Scene scene = new Scene(root);

        setScene(scene);
    }

    /**
     * Définit l'action à exécuter lorsque la liste des participants est correctement initialisée
     */
    @Override
    public void setPlayersListSetListener(ListChangeListener<String> whenPlayersNamesListIsSet) {

    }


    /**
     * Définit l'action à exécuter lorsque le nombre de participants change
     */
    @Override
    protected void setNbPlayersChangeListener(ChangeListener<Integer> whenNbPlayersChanged) {


    }

    private ChangeListener <Integer> whenNbPlayersChanged = new ChangeListener<Integer>() {
        @Override
        public void changed(ObservableValue<? extends Integer> observableValue, Integer ancientNombre, Integer nouveauNombre) {

        }
    };

    /**
     * Retourne le nombre de participants à la partie que l'utilisateur a renseigné
     */
    @Override
    protected int getNumberOfPlayers() {
        return getPlayersNamesList().size();
    }

    /**
     * Retourne le nom que l'utilisateur a renseigné pour le ième participant à la partie
     * @param playerNumber : le numéro du participant
     */
    @Override
    protected String getPlayerNameByNumber(int playerNumber) {
        return getPlayersNamesList().get(playerNumber);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
