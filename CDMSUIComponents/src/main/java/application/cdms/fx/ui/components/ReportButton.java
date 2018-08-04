package application.cdms.fx.ui.components;

import application.cdms.report.build.ReportBuilder;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

public class ReportButton extends Button{

	private String reportName;
	
	private String reportInputType;
	
	private TableView<?> reportDataSource;
	
	public ReportButton (){
		this.setOnAction((e) -> {
			System.out.println(reportName);
			System.out.println(reportDataSource!=null ? reportDataSource.getItems().size():"Empty");
			if(reportDataSource!=null && reportDataSource.getItems().size()>0){
				ReportBuilder.buildReport(reportName,reportDataSource);
			}
		});
	}
	
	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getReportInputType() {
		return reportInputType;
	}

	public void setReportInputType(String reportInputType) {
		this.reportInputType = reportInputType;
	}

	public TableView<?> getReportDataSource() {
		return reportDataSource;
	}

	public void setReportDataSource(TableView<?> reportDataSource){
			this.reportDataSource = reportDataSource;
	}	
}