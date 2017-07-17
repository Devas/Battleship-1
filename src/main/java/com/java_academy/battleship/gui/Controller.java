package sample;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;


import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {


    public void printId(MouseEvent e) {
        System.out.println(e.getSource());
        Pane pane = (Pane) e.getSource();
        if ((e.getSource().toString()).equals("Pane[id=0]") || (e.getSource().toString()).equals("Pane[id=1]") || (e.getSource().toString()).equals("Pane[id=2]") || (e.getSource().toString()).equals("Pane[id=3]"))
            pane.setStyle("-fx-background-color: red;");
        else
            pane.setStyle("-fx-background-color: cornflowerblue;");

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
