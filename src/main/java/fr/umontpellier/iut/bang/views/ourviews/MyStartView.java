package fr.umontpellier.iut.bang.views.ourviews;

import fr.umontpellier.iut.bang.views.StartView;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ObservableValueBase;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MyStartView extends StartView {

    private ImageView logo;
    private ImageView titre;
    private GridPane parametrePartie;
    private HBox haut;
    private List<TextField> joueurs;
    private BorderPane root;
    private TextField nbJ;
    private ArrayList<String> listeDesNoms;
    


    public MyStartView() {
        super();

        setWidth(1500);
        setHeight(750);
        listeDesNoms = new ArrayList<>();
        root = new BorderPane();
        joueurs = new ArrayList<>();
        haut = new HBox();
        parametrePartie = new GridPane();
        ImageView background = new ImageView("src/main/resources/images/background.png");
        nbJ = new TextField("4");

        nbJ.setPrefWidth(40);
        nbJ.setPrefHeight(10);



        logo = new ImageView("src/main/resources/images/logo.png");
        titre = new ImageView("src/main/resources/images/titre.png");

        logo.setFitWidth(200);
        logo.setFitHeight(200);
        titre.setFitHeight(200);
        haut.getChildren().addAll(logo, titre);
        haut.setAlignment(Pos.CENTER);
        haut.setPadding(new Insets(50,0,0,0));

        setNbPlayersChangeListener(whenNbPlayersChanged);

        background.setFitWidth(1550);
        background.setFitHeight(750);
        Button start = new Button("Commencer");
        start.setAlignment(Pos.CENTER);
        start.setMinSize(100, 100);
        start.setStyle("-fx-background-color: grey");

        VBox bouton = new VBox();
        bouton.setAlignment(Pos.CENTER);
        bouton.getChildren().add(start);

        root.getChildren().add(background);
        root.setBottom(bouton);

        parametrePartie.setStyle("-fx-background-color: #b8b2b2");
        parametrePartie.setOpacity(0.8);
       // parametrePartie.setStyle("-fx-arc-height: 140");
        //parametrePartie.setStyle("-fx-arc-width: 140");
        parametrePartie.setAlignment(Pos.CENTER);
        parametrePartie.setMaxHeight(300);
        parametrePartie.setMaxWidth(1000);
        parametrePartie.add(nbJ,4,3);
        for (int i = 0; i<4; i++){
            Label j = new Label("J"+ (i+1));
            parametrePartie.add(j,1,i);
            parametrePartie.add(new TextField(),2,i);

        }


        root.setCenter(parametrePartie);
        root.setTop(haut);


        Scene scene = new Scene(root);

        setScene(scene);
    }

    /**
     * Définit l'action à exécuter lorsque la liste des participants est correctement initialisée
     */

    @Override
    public void setPlayersListSetListener(ListChangeListener<String> whenPlayersNamesListIsSet) {
        playersNamesListProperty().addListener(whenPlayersNamesListIsSet);
    }



    /**
     * Définit l'action à exécuter lorsque le nombre de participants change
     */
    private ChangeListener <String> whenNbPlayersChanged = new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observableValue, String ancientNombre, String nouveauNombre) {
            if(ancientNombre.compareTo(nouveauNombre)<0) {
                for (int i = 0; i < getnbCourant(nouveauNombre); i++) {
                    Label j = new Label("J" + (i + 1));
                    parametrePartie.add(j, 1, i);
                    parametrePartie.add(new TextField(), 2, i);
                }
            }
            /*if(ancientNombre.compareTo(nouveauNombre)>=0){
                for (int i = getnbCourant(ancientNombre); i> getnbCourant(ancientNombre) - getnbCourant(nouveauNombre) ; i--) {
                    parametrePartie.getChildren().remove(1,i);
                    parametrePartie.getChildren().remove(2, i);
                }
            }*/
        }
    };

    @Override
    protected void setNbPlayersChangeListener(ChangeListener<String> whenNbPlayersChanged) {
        nbJ.textProperty().addListener(whenNbPlayersChanged);


    }





    /**
     * Retourne le nombre de participants à la partie que l'utilisateur a renseigné
     */
    @Override
    protected int getNumberOfPlayers() {
        return listeDesNoms.size();

    }
    public int getnbCourant(String n){
        int nb = 4;
        switch (n){
            case "5": nb = 5; break;
            case "6": nb = 6; break;
            case "7": nb = 7; break;
            case "8": nb = 8; break;
        }

        return nb;
    }
    /**
     * Retourne le nom que l'utilisateur a renseigné pour le ième participant à la partie
     * @param playerNumber : le numéro du participant
     */
    @Override
    protected String getPlayerNameByNumber(int playerNumber) {
        return listeDesNoms.get(playerNumber);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
