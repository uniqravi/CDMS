package application.cdms.report.data.holder;

public class ReportGroupHeaderDataHolder {
	
	ReportHeaderMetaDataHolder headerMetaData;
	
	ReportColHeaderDataHolder[] header;

	public ReportHeaderMetaDataHolder getHeaderMetaData() {
		return headerMetaData;
	}

	public void setHeaderMetaData(ReportHeaderMetaDataHolder headerMetaData) {
		this.headerMetaData = headerMetaData;
	}

	public ReportColHeaderDataHolder[] getHeader() {
		return header;
	}

	public void setHeader(ReportColHeaderDataHolder[] header) {
		this.header = header;
	}
}