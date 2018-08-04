package application.cdms.component.data.handler;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class CustomNodeUtils {

	public static Node imageAddedLabel(String path,Label label){
		HBox hbox = new HBox();
	    Image image = new Image(path);
	    label.setGraphic(new ImageView(image));
	    hbox.setSpacing(10);
	    hbox.getChildren().add((label));
	    return hbox;
	}
}
