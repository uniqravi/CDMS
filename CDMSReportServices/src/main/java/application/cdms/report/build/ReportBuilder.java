package application.cdms.report.build;

import javafx.scene.Node;

public interface ReportBuilder {

	public static void buildReport(String reportNm,Node node){
		ReportBuildProcessor buildProcessor = new ReportBuildProcessor();
		buildProcessor.startBuildProcess(reportNm, node);
	}
}
