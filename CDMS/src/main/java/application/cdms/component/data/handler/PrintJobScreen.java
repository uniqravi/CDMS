package application.cdms.component.data.handler;

import java.util.List;
import java.util.Map;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialog.DialogTransition;
import com.jfoenix.controls.JFXDialogLayout;

import application.cdms.controllers.ScreenTransitionController;
import application.cdms.utilities.GenerateReport;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class PrintJobScreen<T> {

	private ScreenTransitionController<?> t;
	
	String heading=null;
	
	public PrintJobScreen(ScreenTransitionController<?> t,String heading){
		this.t=t;
		this.heading=heading;
	}
	
	public boolean printInvoice(List<T> jasperObjectList,Map<String, Object> jasperParams,String jasperTemplate,String outputFileNm){
		try {
			VBox vbox=new VBox();
			vbox.setPrefWidth(t.getRootStage().getWidth()-400);
			Node node=new GenerateReport().showPrintReport(jasperTemplate,jasperObjectList,jasperParams,outputFileNm);
			vbox.getChildren().add(node);
			StackPane stackpane=(StackPane) t.getCurrentNode();
			JFXDialog productDilogu=null;
			JFXDialogLayout dilogLayout = new JFXDialogLayout();
			Label heading = new Label(this.heading);
			heading.setStyle("-fx-font-size: 22px;-fx-text-fill:#3F51B5");
			dilogLayout.setHeading(heading);
			dilogLayout.setBody(vbox);
			productDilogu = new JFXDialog(stackpane, dilogLayout, DialogTransition.CENTER);
			productDilogu.show();
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	public boolean printInvoice_modified(List<T> jasperObjectList,Map<String, Object> jasperParams,String jasperTemplate,String outputFileNm){
		try {
			VBox vbox=new VBox();
			vbox.setPrefWidth(t.getRootStage().getWidth()-400);
			Node node=new GenerateReport().compileShowPrintReport(jasperTemplate,jasperObjectList,jasperParams,outputFileNm);
			vbox.getChildren().add(node);
			StackPane stackpane=(StackPane) t.getCurrentNode();
			JFXDialog productDilogu=null;
			JFXDialogLayout dilogLayout = new JFXDialogLayout();
			Label heading = new Label(this.heading);
			heading.setStyle("-fx-font-size: 22px;-fx-text-fill:#3F51B5");
			dilogLayout.setHeading(heading);
			dilogLayout.setBody(vbox);
			productDilogu = new JFXDialog(stackpane, dilogLayout, DialogTransition.CENTER);
			productDilogu.show();
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
}
