package application.cdms.report.build;

import java.util.Map;

import javafx.scene.Node;

public interface ReportBuilder {

	public static void buildReport(String reportNm,Node node,Map<String,String> elValuesMap){
		ReportBuildProcessor buildProcessor = new ReportBuildProcessor();
		buildProcessor.startBuildProcess(reportNm, node,elValuesMap);
	}
}
