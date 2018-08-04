package application.cdms.report.data.holder;

public class ReportHeaderMetaDataHolder {

	private String valType;
	
    private String headerText;
    
    private ReportBasicMetaDataHolder metaData;

	public String getValType() {
		return valType;
	}

	public void setValType(String valType) {
		this.valType = valType;
	}

	public String getHeaderText() {
		return headerText;
	}

	public void setHeaderText(String headerText) {
		this.headerText = headerText;
	}

	public ReportBasicMetaDataHolder getMetaData() {
		return metaData;
	}

	public void setMetaData(ReportBasicMetaDataHolder metaData) {
		this.metaData = metaData;
	}
}
