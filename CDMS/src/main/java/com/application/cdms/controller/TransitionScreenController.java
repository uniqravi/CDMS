package com.application.cdms.controller;

import java.io.IOException;
import java.util.HashMap;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class TransitionScreenController extends StackPane {

	private HashMap<String, Node> screens = new HashMap<String, Node>();

	public TransitionScreenController(){
		//super();
		//setStyle("-fx-pref-width: 1100px;-fx-pref-height:600px;");
	}
	
	/** screen transition methods------------------start */
	public void addScreen(String name, Node node) {
		screens.put(name, node);
	}

	public boolean loadScreen(String name, String resource) throws IOException {
		FXMLLoader fxloader = new FXMLLoader(getClass().getResource(resource));
		Parent loadScreen = (Parent) fxloader.load();
		ParentScreenController controller = fxloader.getController();
		controller.setParentScreen(this);
		addScreen(name, loadScreen);
		return true;
	}

	public boolean setScreen(final String name) {
		if (screens.get(name) != null) { // check screen already loaded
			final DoubleProperty opacity = opacityProperty();
			// Is there is more than one screen
			if (!getChildren().isEmpty()) {
				// @SuppressWarnings("unchecked")
				Timeline fade = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)),
						new KeyFrame(new Duration(1000), (e) -> {

							// remove displayed screen
							getChildren().remove(0);
							// add new screen
							getChildren().add(0, screens.get(name));
							Timeline fadeIn = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
									new KeyFrame(new Duration(800), new KeyValue(opacity, 1.0)));
							fadeIn.play();
						}, new KeyValue(opacity, 0.0)));
				fade.play();
			} else {
				// no one else been displayed, then just show
				setOpacity(0.0);
				getChildren().add(screens.get(name));
				Timeline fadeIn = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
						new KeyFrame(new Duration(2500), new KeyValue(opacity, 1.0)));
				fadeIn.play();
			}
		} else {
			System.out.println("screen hasn't been loaded!\n");
			return false;
		}
		return true;
	}

	public boolean unloadScreen(String name) {
		if (screens.remove(name) == null) {
			System.out.println("Screen didn't exist");
			return false;
		} else {
			return true;
		}
	}

	public boolean isNodeNotAvailable(String name) {
		if (screens.get(name) != null) {
			return false;
		}
		return true;
	}
}
