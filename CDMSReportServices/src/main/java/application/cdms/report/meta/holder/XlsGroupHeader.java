package application.cdms.report.meta.holder;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class XlsGroupHeader extends AbstractHeader{

	@XmlElement(name="header",namespace="http://www.example.org/reportMetadata")
	private List<XlsHeader> headers;
	
	@XmlAttribute(name="headerText")
	private String headerText;

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
	
}
