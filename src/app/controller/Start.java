package app.controller;

import app.views.SplashView;
import app.views.IView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Start extends Application {


    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws RuntimeException{
        SplashView splash = new SplashView();


        stage.setTitle("monopoly_tobe_replaced");
        stage.setResizable(false);
        stage.setScene(splash.getMyScene());
        stage.show();


    }

}
