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

public class SuccessDialog {

	public static void showSuccessMessage(List<Node> suceesMessage, StackPane stackPane, String errHeading) {
		JFXDialogLayout dilogLayout = new JFXDialogLayout();
		Label heading = new Label(errHeading);
		heading.setStyle("-fx-font-size: 15px;");
		dilogLayout.setHeading(heading);
		dilogLayout.setStyle("-fx-background-color: #00e676;");
		VBox vbox = new VBox();
		if (suceesMessage != null) {
			for (Node node : suceesMessage) {
				node.setStyle("-fx-font-size: 14px;");
				vbox.getChildren().add(node);
			}
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
}