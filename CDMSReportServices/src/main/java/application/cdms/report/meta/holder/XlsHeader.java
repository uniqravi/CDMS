package application.cdms.report.meta.holder;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementWrapper;

public class XlsHeader extends AbstractHeader{

	@XmlAttribute(name="headerText")
	private String headerText;
	
	@XmlAttribute(name="dataColName")
	private String dataColName;
	
	@XmlAttribute(name="datacolspan")
	private int datacolspan;
	
	@XmlAttribute(name="datarowspan")
	private int datarowspan;
	
	@XmlAttribute(name="databgcolor")
	private String databgcolor;
	
	@XmlAttribute(name="datafontcolor")
	private String datafontcolor;
	
	@XmlElementWrapper(nillable=true)
	private List<XlsHeader> headers;

	public String getHeaderText() {
		return headerText;
	}

	public void setHeaderText(String headerText) {
		this.headerText = headerText;
	}

	public List<XlsHeader> getHeaders() {
		return headers;
	}

	public void setHeaders(List<XlsHeader> headers) {
		this.headers = headers;
	}

	public String getDataColName() {
		return dataColName;
	}

	public void setDataColName(String dataColName) {
		this.dataColName = dataColName;
	}

	public int getDatacolspan() {
		return datacolspan;
	}

	public void setDatacolspan(int datacolspan) {
		this.datacolspan = datacolspan;
	}

	public int getDatarowspan() {
		return datarowspan;
	}

	public void setDatarowspan(int datarowspan) {
		this.datarowspan = datarowspan;
	}

	public String getDatabgcolor() {
		return databgcolor;
	}

	public void setDatabgcolor(String databgcolor) {
		this.databgcolor = databgcolor;
	}

	public String getDatafontcolor() {
		return datafontcolor;
	}

	public void setDatafontcolor(String datafontcolor) {
		this.datafontcolor = datafontcolor;
	}
}