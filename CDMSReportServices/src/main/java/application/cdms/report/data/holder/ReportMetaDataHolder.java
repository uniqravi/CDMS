package application.cdms.report.data.holder;

public class ReportMetaDataHolder {

	private String xlsWorkSheetNm;
	
	private String exportfileNm;
	
	private String inputSourcetype;
	
	private ReportHeaderHolder reportHeader;
	
	public ReportMetaDataHolder(){
		
	}
	
	public ReportMetaDataHolder(String xlsWorkSheetNm, String exportfileNm, String inputSourcetype) {
		super();
		this.xlsWorkSheetNm = xlsWorkSheetNm;
		this.exportfileNm = exportfileNm;
		this.inputSourcetype = inputSourcetype;
	}

	public String getXlsWorkSheetNm() {
		return xlsWorkSheetNm;
	}

	public void setXlsWorkSheetNm(String xlsWorkSheetNm) {
		this.xlsWorkSheetNm = xlsWorkSheetNm;
	}

	public String getExportfileNm() {
		return exportfileNm;
	}

	public void setExportfileNm(String exportfileNm) {
		this.exportfileNm = exportfileNm;
	}

	public String getInputSourcetype() {
		return inputSourcetype;
	}

	public void setInputSourcetype(String inputSourcetype) {
		this.inputSourcetype = inputSourcetype;
	}

	public ReportHeaderHolder getReportHeader() {
		return reportHeader;
	}

	public void setReportHeader(ReportHeaderHolder reportHeader) {
		this.reportHeader = reportHeader;
	}
}