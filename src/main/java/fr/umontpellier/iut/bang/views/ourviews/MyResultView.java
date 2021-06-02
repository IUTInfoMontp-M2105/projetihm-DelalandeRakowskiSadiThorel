package fr.umontpellier.iut.bang.views.ourviews;

import fr.umontpellier.iut.bang.BangIHM;
import fr.umontpellier.iut.bang.views.ResultsView;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class MyResultView extends ResultsView {
    private Button buttonPlayAgain;
    private Button buttonStop;

    public MyResultView(BangIHM bangIHM){
        super(bangIHM);
        buttonPlayAgain = new Button();
        buttonStop = new Button();
    }

    @Override
    public void playAgain(){

    }

    @Override
    public void stop(){

    }

}
