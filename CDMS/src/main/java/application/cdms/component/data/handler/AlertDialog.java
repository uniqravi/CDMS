package application.cdms.component.data.handler;

import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialog.DialogTransition;
import com.jfoenix.controls.JFXDialogLayout;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class AlertDialog {
	
	private JFXDialogLayout dilogLayout=null;
	
	private JFXDialog alertDiloag=null;
	
	public void buildBody(){
		
	}

	public  void showAlertDilogue(StackPane stackPane, String alertMsg,List<JFXButton> buttonLst) {
		this.dilogLayout = new JFXDialogLayout();
		Label heading = new Label(alertMsg);
		heading.setStyle("-fx-font-size: 18px;");
		//dilogLayout.setHeading(heading);
		heading.setPadding(new Insets(7));
		dilogLayout.setStyle("-fx-background-color: #FFFFFF;");
		VBox vbox = new VBox();
		HBox hbox = new HBox();
		hbox.setAlignment(Pos.BOTTOM_CENTER);
		hbox.setSpacing(6);
		for (JFXButton node : buttonLst){
			node.setStyle("-fx-font-size: 15px;-fx-background-color:#3F51B5;-fx-text-fill: WHITE;-fx-pref-width: 50;");
			//node.setOnAction();
			hbox.getChildren().add(node);
		}
		vbox.getChildren().add(heading);
		vbox.getChildren().add(hbox);
		dilogLayout.setBody(vbox);
		alertDiloag = new JFXDialog(stackPane, dilogLayout, DialogTransition.TOP);
		alertDiloag.setOverlayClose(false);
		alertDiloag.show();
	}
	
	public void closeAlertDialog(){
		alertDiloag.close();
	}
	
	/*interface buttonsActions{
		void setYesButtonAction();
		void setNoButtonAction();
	}*/
}
