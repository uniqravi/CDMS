package application.cdms.report.meta.holder;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class AbstractHeader {

	@XmlAttribute(name="valType")
	private String valType;
	
	@XmlAttribute(name="headercolspan")
	private int headercolspan;
	
	@XmlAttribute(name="headerrowspan")
	private int headerrowspan;
	
	@XmlAttribute(name="headerbgcolor")
	private String headerbgcolor;
	
	@XmlAttribute(name="headerfontcolor")
	private String headerfontcolor;
	
	public String getValType() {
		return valType;
	}

	public void setValType(String valType) {
		this.valType = valType;
	}

	public int getHeadercolspan() {
		return headercolspan;
	}

	public void setHeadercolspan(Integer headercolspan) {
		this.headercolspan = headercolspan;
	}

	public int getHeaderrowspan() {
		return headerrowspan;
	}

	public void setHeaderrowspan(Integer headerrowspan) {
		this.headerrowspan = headerrowspan;
	}

	public String getHeaderbgcolor() {
		return headerbgcolor;
	}

	public void setHeaderbgcolor(String headerbgcolor) {
		this.headerbgcolor = headerbgcolor;
	}

	public String getHeaderfontcolor() {
		return headerfontcolor;
	}

	public void setHeaderfontcolor(String headerfontcolor) {
		this.headerfontcolor = headerfontcolor;
	}
}