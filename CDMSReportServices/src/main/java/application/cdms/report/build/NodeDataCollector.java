package application.cdms.report.build;

import java.util.HashMap;
import java.util.Map;

import application.cdms.exception.handler.CDMSGeneralException;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

@SuppressWarnings({"unchecked","rawtypes"})
public class NodeDataCollector implements ReportDataCollector{
	
	private Node dataNode=null;
	
	private Map<String,String[]> dataMap=null;
	
	public NodeDataCollector(Node node){
		this.dataNode=node;
	}
	
	@Override
	
	public Map<String, String[]> collectReportData() throws Exception {
		if(dataNode instanceof TableView){
			TableView tblView = (TableView) dataNode;
			this.setData(tblView);
		}
		else{
			throw new CDMSGeneralException("Data Node is not table view");
		}
		return dataMap;
	}
	
	private void setData(TableView tableView) throws CDMSGeneralException{
		mapKeys(tableView);
	}
	

	private void mapKeys(TableView tableView) throws CDMSGeneralException{
		ObservableList<TableColumn> tablecols = tableView.getColumns();
		if(tablecols.size()>0){
			dataMap = new HashMap<>();
			setColTextAsKey(tablecols);
		}
		else{
			throw new CDMSGeneralException("Table must be have columns");
		}
	}
	
	
	private void setColTextAsKey(ObservableList<TableColumn> tablecols){
			for(TableColumn tbalCol : tablecols){
				setColText(tbalCol);
			}
	}
	
	private void setColText(TableColumn tableCol){
		if(tableCol.getColumns().size()>0){
			setColTextAsKey(tableCol.getColumns());
		}
		else{
			//dataMap.put(tableCol.getText(),null);
			mapValues(tableCol);
		}
	}
	private void mapValues(TableColumn tableCol) {
		if(tableCol.getTableView().getItems().size()>0){
			int size = tableCol.getTableView().getItems().size();
			String[] strArr = new String[size];
			for(int i=0;i<size;i++){
				strArr[i]=tableCol.getCellData(i)!=null?tableCol.getCellData(i).toString():null;
			}
			dataMap.put(tableCol.getText(), strArr);
		}
	}
}