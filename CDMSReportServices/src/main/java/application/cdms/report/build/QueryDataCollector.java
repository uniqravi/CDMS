package application.cdms.report.build;

import java.util.Map;

import application.cdms.report.meta.holder.XlsReportMetaInfo;

public class QueryDataCollector implements ReportDataCollector{

	XlsReportMetaInfo reportMetaInfo = null;
	
	public QueryDataCollector(XlsReportMetaInfo reportMetaInfo){
		this.reportMetaInfo=reportMetaInfo;
	}
	
	@Override
	public Map<String, String[]> collectReportData() {
		
		return null;
	}

}
