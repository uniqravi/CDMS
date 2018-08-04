package application.cdms.report.build;

import java.util.Map;

@FunctionalInterface
public interface ReportDataCollector {
	Map<String,String[]> collectReportData() throws Exception;
}