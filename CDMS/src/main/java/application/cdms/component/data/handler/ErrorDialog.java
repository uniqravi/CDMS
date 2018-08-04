package application.cdms.component.data.handler;

import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialog.DialogTransition;
import com.jfoenix.controls.JFXDialogLayout;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class ErrorDialog {
	public static void showErrorDilogue(List<Node> errors, StackPane stackPane, String errHeading) {
		JFXDialogLayout dilogLayout = new JFXDialogLayout();
		Label heading = new Label(errHeading);
		heading.setStyle("-fx-font-size: 15px;");
		dilogLayout.setHeading(heading);
		dilogLayout.setStyle("-fx-background-color: #FFCDD2;");
		VBox vbox = new VBox();
		for (Node node : errors) {
			node.setStyle("-fx-font-size: 14px;");
			vbox.getChildren().add(node);
		}
		dilogLayout.setBody(vbox);
		JFXDialog errorMsgDialog = new JFXDialog(stackPane, dilogLayout, DialogTransition.TOP);
		JFXButton jfxButton = new JFXButton("Ok");
		jfxButton.setOnAction((e) -> {
			errorMsgDialog.close();
		});
		dilogLayout.setActions(jfxButton);
		errorMsgDialog.show();
	}
	public static void showErrorDilogue(Node errorText, StackPane stackPane, String errHeading) {
		JFXDialogLayout dilogLayout = new JFXDialogLayout();
		Label heading = new Label(errHeading);
		heading.setStyle("-fx-font-size: 15px;");
		dilogLayout.setHeading(heading);
		dilogLayout.setStyle("-fx-background-color: #FFCDD2;");
		VBox vbox = new VBox();
		//for (Node node : errors) {
			errorText.setStyle("-fx-font-size: 14px;");
			vbox.getChildren().add(errorText);
		//}
		dilogLayout.setBody(vbox);
		JFXDialog errorMsgDialog = new JFXDialog(stackPane, dilogLayout, DialogTransition.TOP);
		JFXButton jfxButton = new JFXButton("Ok");
		jfxButton.setOnAction((e) -> {
			errorMsgDialog.close();
		});
		dilogLayout.setActions(jfxButton);
		errorMsgDialog.show();
	}
}
