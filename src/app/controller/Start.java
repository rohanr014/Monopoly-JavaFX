package app.controller;

import javafx.application.Application;
import javafx.stage.Stage;

public class Start extends Application {
    private MainController myMainController;



    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws RuntimeException{
        myMainController = new MainController(stage);
    }

}
