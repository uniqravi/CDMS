package application.cdms.report.data.holder;

public class ReportColHeaderDataHolder {
   
	 private ReportColDataHolder colData;
	 
	 private ReportHeaderMetaDataHolder headerMetaData;

	public ReportColDataHolder getColData() {
		return colData;
	}

	public void setColData(ReportColDataHolder colData) {
		this.colData = colData;
	}

	public ReportHeaderMetaDataHolder getHeaderMetaData() {
		return headerMetaData;
	}

	public void setHeaderMetaData(ReportHeaderMetaDataHolder headerMetaData) {
		this.headerMetaData = headerMetaData;
	}
}