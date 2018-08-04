package application.cdms.report.meta.holder;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class XlsReportHeader {

	@XmlElement(name="topHeader",namespace="http://www.example.org/reportMetadata")
	private XlsTopHeader topHeader;
	
	@XmlElement(name="groupHeader",namespace="http://www.example.org/reportMetadata")
	private List<XlsGroupHeader> groupHeader;

	public XlsTopHeader getTopHeader() {
		return topHeader;
	}

	public void setTopHeader(XlsTopHeader topHeader) {
		this.topHeader = topHeader;
	}

	public List<XlsGroupHeader> getGroupHeader() {
		return groupHeader;
	}

	public void setGroupHeader(List<XlsGroupHeader> groupHeader) {
		this.groupHeader = groupHeader;
	}
}