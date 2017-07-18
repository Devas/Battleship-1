package com.java_academy.battleship.gui;

import javafx.scene.layout.Pane;

public class View {
	
	public void shipHitted(Pane pane) {
		pane.setStyle("-fx-background-color: red;");
	}
	
	public void dismissShot(Pane pane) {
		pane.setStyle("-fx-background-color: cornflowerblue;");
	}

}
