package sample;


import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    private List shipPositions;

    @FXML
    GridPane gridPane;

    private void setShipPositions() {
        Integer a[] = {0, 1, 2, 3, 99, 98, 97, 96};

        shipPositions = Arrays.asList(a);
    }

    public void printId(MouseEvent e) {
        setShipPositions();

        Pane pane = (Pane) e.getSource();
        int source = Integer.valueOf(e.getSource().toString().replaceAll("\\D+", ""));
        System.out.println(source);
        if (shipPositions.contains(source))
            pane.setStyle("-fx-background-color: red;");
        else
            pane.setStyle("-fx-background-color: cornflowerblue;");

        pane.setDisable(true);
    }

    public void method(MouseEvent e){
        System.out.println(e.getSource());
       // if (e.getButton() == MouseButton.SECONDARY){

        System.out.println(gridPane.toString());
        int source = Integer.valueOf(e.getSource().toString().replaceAll("\\D+", ""));
        int shipLength=3;
        getNodeByRowColumnIndex(gridPane, source, shipLength);
    }

    public void getNodeByRowColumnIndex (GridPane gridPane, int id, int shipLength) {
        Node result = null;
        int counter=0;
        List<Pane> lineList = new ArrayList<>();
        for (Node currentNode : gridPane.getChildren()){
            if (currentNode instanceof Pane){
                lineList.add((Pane)currentNode);
            }
        }
        for(int i=id-100; i<id+shipLength-100;++i){
        Pane pane=lineList.get(i);
        pane.setStyle("-fx-background-color: black;");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
