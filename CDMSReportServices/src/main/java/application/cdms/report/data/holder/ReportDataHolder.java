package application.cdms.report.data.holder;

public class ReportDataHolder {
    
	 private ReportMetaDataHolder reportMetaData;
	 
	 private ReportHeaderHolder reportHeader;

	public ReportMetaDataHolder getReportMetaData() {
		return reportMetaData;
	}

	public void setReportMetaData(ReportMetaDataHolder reportMetaData) {
		this.reportMetaData = reportMetaData;
	}

	public ReportHeaderHolder getReportHeader() {
		return reportHeader;
	}

	public void setReportHeader(ReportHeaderHolder reportHeader) {
		this.reportHeader = reportHeader;
	}
}