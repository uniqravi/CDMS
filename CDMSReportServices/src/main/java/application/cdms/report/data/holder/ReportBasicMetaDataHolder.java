package application.cdms.report.data.holder;

public class ReportBasicMetaDataHolder {
	 private Integer colspan;

	 private String fontcolor;

	 private Integer rowspan;

	 private String bgcolor;
	 
	public ReportBasicMetaDataHolder(Integer colspan, String fontcolor, Integer rowspan, String bgcolor) {
		super();
		this.colspan = colspan;
		this.fontcolor = fontcolor;
		this.rowspan = rowspan;
		this.bgcolor = bgcolor;
	}

	public ReportBasicMetaDataHolder(){
		
	}
	
	public Integer getColspan() {
		return colspan;
	}

	public void setColspan(Integer colspan) {
		this.colspan = colspan;
	}

	public String getFontcolor() {
		return fontcolor;
	}

	public void setFontcolor(String fontcolor) {
		this.fontcolor = fontcolor;
	}

	public Integer getRowspan() {
		return rowspan;
	}

	public void setRowspan(Integer rowspan) {
		this.rowspan = rowspan;
	}

	public String getBgcolor() {
		return bgcolor;
	}

	public void setBgcolor(String bgcolor) {
		this.bgcolor = bgcolor;
	}
}