package com.java_academy.battleship.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URI;
import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        URI url=new File("src/main/java/com/java_academy/battleship/gui/sample.fxml").toURI();
        Parent root = FXMLLoader.load(url.toURL());
        primaryStage.setTitle("Battleships");
        primaryStage.setScene(new Scene(root, 950, 700));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
