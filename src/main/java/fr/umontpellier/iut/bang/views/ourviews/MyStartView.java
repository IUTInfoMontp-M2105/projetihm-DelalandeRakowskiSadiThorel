package fr.umontpellier.iut.bang.views.ourviews;

import fr.umontpellier.iut.bang.BangIHM;
import fr.umontpellier.iut.bang.views.StartView;
import javafx.application.HostServices;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MyStartView extends StartView {

    private ImageView logo;
    private ImageView titre;
    private GridPane parametrePartie;
    private HBox haut;
    private ObservableList<TextField> joueurs;
    private ListChangeListener<TextField> changementNom;
    private BorderPane root;
    private TextField nbJ;
    private ArrayList<String> listeDesNoms;
    


    public MyStartView(BangIHM bangIHM) {
        super();

        joueurs = FXCollections.observableArrayList(joueurs -> new Observable[] {joueurs.textProperty()} );
        setWidth(1500);
        setHeight(750);
        listeDesNoms = new ArrayList<>();
        root = new BorderPane();
        haut = new HBox();
        parametrePartie = new GridPane();
        ImageView background = new ImageView("src/main/resources/images/background.png");


        logo = new ImageView("src/main/resources/images/logo.png");
        titre = new ImageView("src/main/resources/images/titre.png");

        //nombre de Joueurs
        Label nbJoueurs = new Label("Nombre de Joueurs");
        nbJoueurs.setId("but");
        nbJoueurs.setFont(Font.loadFont("file:src/main/resources/fonts/Bangers.ttf", 20));
        nbJoueurs.setLayoutX(850);
        nbJoueurs.setLayoutY(100);
        nbJ = new TextField("4");
        nbJ.setPrefWidth(40);
        nbJ.setPrefHeight(10);
        nbJ.setLayoutX(900);
        nbJ.setLayoutY(150);

        changementNom = new ListChangeListener<TextField>(){
            @Override
            public void onChanged(Change<? extends TextField> change){
                change.next();
                if(change.wasAdded()) {
                    for (int i = 0; i < change.getAddedSize(); i++) {
                        listeDesNoms.add(change.getAddedSubList().get(i).getText());
                        System.out.println("ajout");
                    }
                }
                if(change.wasRemoved()){
                    for(int i = 0; i<change.getRemovedSize(); i++){
                        listeDesNoms.remove(change.getRemoved().get(change.getRemovedSize()-i).getText());
                    }
                }
                if(change.wasUpdated()){
                    for(int i = change.getFrom(); i < change.getTo(); i++){
                        listeDesNoms.remove(i);
                        listeDesNoms.add(i,change.getList().get(i).getText());
                        System.out.println(listeDesNoms);
                    }
                }
            }
        };
        joueurs.addListener(changementNom);

        logo.setFitWidth(150);
        logo.setPreserveRatio(true);
        titre.setFitHeight(200);
        haut.getChildren().addAll(logo, titre);
        haut.setAlignment(Pos.CENTER);
        haut.setPadding(new Insets(50,0,0,0));

        setNbPlayersChangeListener(whenNbPlayersChanged);

        background.setFitWidth(1550);
        background.setFitHeight(750);
        Button start = new Button("Lancer la partie");
        start.setId("but");
        start.setFont(Font.loadFont("file:src/main/resources/fonts/Bangers.ttf", 35));
        start.getStylesheets().add(this.getClass().getClassLoader().getResource("src/main/resources/Css/accueil.css").toExternalForm());
        start.setAlignment(Pos.CENTER);
        start.setLayoutX(600);
        start.setLayoutY(-25);
        start.setOnAction(commancer);

        Pane bouton = new Pane();

        bouton.getChildren().add(start);


        root.getChildren().add(background);

        //label
        Label rules = new Label("Règles");
        rules.setStyle("-fx-font-weight: bold");
        rules.setStyle("-fx-font-size: 40");
        rules.setTextFill(Color.web("White"));
        ImageView pdf = new ImageView("src/main/resources/images/pdf.png");
        pdf.setPreserveRatio(true);
        pdf.setFitHeight(40);
        rules.setGraphic(pdf);
        rules.setLayoutX(1300);
        rules.setOnMouseClicked(mouseEvent -> lirePdfRegles(bangIHM));
        bouton.getChildren().add(rules);

        root.setBottom(bouton);

        Pane centre= new Pane();
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(750);
        rectangle.setHeight(340);
        rectangle.setArcWidth(140);
        rectangle.setArcHeight(140);
        rectangle.setLayoutX(350);
        rectangle.setFill(Color.rgb(217, 217, 217, 0.7));
        centre.getChildren().add(rectangle);
        parametrePartie.setLayoutY(75);
        parametrePartie.setLayoutX(650);
        parametrePartie.setMaxHeight(300);
        parametrePartie.setMaxWidth(1000);
        parametrePartie.setVgap(5);



        //ajout des différents nodes au millieu
        centre.getChildren().add(parametrePartie);
        centre.getChildren().add(nbJ);
        centre.getChildren().add(nbJoueurs);
        for (int i = 0; i<4; i++){
            Label j = new Label("J"+ (i+1));
            parametrePartie.add(j,1,i);
            TextField t = new TextField();
            t.setPromptText("Saisir un Nom");
            parametrePartie.add(t,2,i);
            joueurs.add(t);

        }

        root.setCenter(centre);
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
     * Le bouton
     */
    private EventHandler<ActionEvent> commancer = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            setAllPlayersNamesList();
            System.out.println("youpi");
            System.out.println(listeDesNoms);
        }
    };

    /**
     * Définit l'action à exécuter lorsque le nombre de participants change
     */
    private ChangeListener <String> whenNbPlayersChanged = new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observableValue, String ancientNombre, String nouveauNombre) {
            if(ancientNombre.compareTo(nouveauNombre)<0 ) {
                for (int i = getnbCourant(ancientNombre); i < getnbCourant(nouveauNombre); i++) {
                    Label j = new Label("J" + (i + 1));
                    TextField t = new TextField();
                    t.setPromptText("Saisir un Nom");
                    parametrePartie.add(j, 1, i);
                    parametrePartie.add(t, 2, i);
                    joueurs.add(t);
                }
            }
            /*else if(!nouveauNombre.isEmpty()){
                System.out.println("Je suis passer par là");
                for (int i = 0; i< getnbCourant(ancientNombre) - getnbCourant(nouveauNombre) +1; i++) {
                        parametrePartie.getChildren().remove(1,getnbCourant(ancientNombre)-i); y'a un soucis avec remove clairement
                    parametrePartie.getChildren().remove(2, getnbCourant(ancientNombre)-i);

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
    private void lirePdfRegles(BangIHM bangIHM) {
        File file = new File("src/main/resources/pdf/Bang-regles.pdf");
        HostServices hostServices = bangIHM.getHostServices();
        hostServices.showDocument(file.getAbsolutePath());
    }
}
