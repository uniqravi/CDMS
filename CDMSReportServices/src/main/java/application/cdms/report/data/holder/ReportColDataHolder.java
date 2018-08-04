package application.cdms.report.data.holder;

public class ReportColDataHolder {
	
	private ReportColMetaDataHolder colMetaData;
	
	private String[] data;

	public ReportColMetaDataHolder getColMetaData() {
		return colMetaData;
	}

	public void setColMetaData(ReportColMetaDataHolder colMetaData) {
		this.colMetaData = colMetaData;
	}

	public String[] getData() {
		return data;
	}

	public void setData(String[] data) {
		this.data = data;
	}
}
