package application.cdms.report.data.holder;

public class ReportHeaderHolder {

	private ReportTopHeaderDataHolder topHeader;
	
	private ReportGroupHeaderDataHolder[] groupHeader;

	public ReportTopHeaderDataHolder getTopHeader() {
		return topHeader;
	}

	public void setTopHeader(ReportTopHeaderDataHolder topHeader) {
		this.topHeader = topHeader;
	}

	public ReportGroupHeaderDataHolder[] getGroupHeader() {
		return groupHeader;
	}

	public void setGroupHeader(ReportGroupHeaderDataHolder[] groupHeader) {
		this.groupHeader = groupHeader;
	}
}