package application.cdms.component.data.handler;

import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

public class DialogueCreator {

	
	//first  0. Node //1.row Index //2 column index //3 row span 4 colSpan
	public static Pane createGridDialogue(Object[][] nodes,double prefWidth) {
		GridPane gridPane = new GridPane();
		gridPane.setPrefWidth(prefWidth);
		int rowsNo = -1;
		int colsNo = -1;
		ColumnConstraints constraints = new ColumnConstraints();
		constraints.setPercentWidth(2.5);
		ColumnConstraints constraints1 = new ColumnConstraints();
		constraints1.setPercentWidth(35);
		ColumnConstraints constraints2 = new ColumnConstraints();
		constraints2.setPercentWidth(10);
		ColumnConstraints constraints3 = new ColumnConstraints();
		constraints3.setPercentWidth(50);
		ColumnConstraints constraints4 = new ColumnConstraints();
		constraints4.setPercentWidth(2.5);
		gridPane.getColumnConstraints().addAll(constraints,constraints1,constraints2,constraints3,constraints4);
		for(Object[] objArr :  nodes) {
			Node node = (Node) objArr[0];
			int rowi = (int) objArr[1];
			int coli = (int) objArr[2];
			int rowSpan = (int) objArr[3];
			int colSpan = (int) objArr[4];
			if(rowsNo<rowi) {
				RowConstraints rowContraints = new RowConstraints(48.0);
				rowContraints.setVgrow(Priority.SOMETIMES);
				gridPane.getRowConstraints().add(rowContraints);
				rowsNo=rowi;
			}
			if(colsNo<coli) {
				colsNo=coli;
			}
			gridPane.add(node,coli+1 , rowi);
			if(rowSpan!=0) {
				GridPane.setRowSpan(node,rowSpan);
			}
			if(colSpan!=0) {
				GridPane.setColumnSpan(node,colSpan);
			}
		}
		return gridPane;
	}
}
