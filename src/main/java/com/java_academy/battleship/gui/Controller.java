package com.java_academy.battleship.gui;


import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;


public class Controller {

    private final List<Integer> shipPositions = new ArrayList<>();

    @FXML
    GridPane gridPane;

    public void handleMouseEventDuringShot(MouseEvent e) {
        Pane pane = (Pane) e.getSource();
        int source = Integer.valueOf(e.getSource().toString().replaceAll("\\D+", ""));
        if (shipPositions.contains(source))
            pane.setStyle("-fx-background-color: red;");
        else
            pane.setStyle("-fx-background-color: cornflowerblue;");

        //jesli chcemy by nie mozna bylo strzelac w to miejsce dwa razy
        // pane.setDisable(true);
    }

    private List<Pane> getListOfPanes() {
        List<Pane> lineList = new ArrayList<>();
        for (Node currentNode : gridPane.getChildren()) {
            if (currentNode instanceof Pane) {
                lineList.add((Pane) currentNode);
            }
        }
        return lineList;
    }

    public void handleMouseClickDuringShipPlacement(MouseEvent e) {
        int source = Integer.valueOf(e.getSource().toString().replaceAll("\\D+", ""));
        int shipLength = 2;

        List<Pane> panes = getListOfPanes();

        if (e.getButton() == MouseButton.SECONDARY)
            drawShipVertically(source, shipLength, panes);
        else
            drawShipHorizontaly(source, shipLength, panes);
    }

    private void drawShipVertically(int id, int shipLength, List<Pane> lineList) {
        for (int i = id - 100; i < id + shipLength - 90; i += 10) {
            Pane pane = lineList.get(i);
            pane.setStyle("-fx-background-color: black;");
            shipPositions.add(i);
        }

    }

    private void drawShipHorizontaly(int id, int shipLength, List<Pane> lineList) {
        for (int i = id - 100; i < id + shipLength - 100; ++i) {
            Pane pane = lineList.get(i);
            pane.setStyle("-fx-background-color: black;");
            shipPositions.add(i);
        }
    }

    public void placeShipsRandomly() {
        System.out.println("Random ships");
    }

    public void deployNuke() {
        System.out.println("Deploy nuke");
    }

}
