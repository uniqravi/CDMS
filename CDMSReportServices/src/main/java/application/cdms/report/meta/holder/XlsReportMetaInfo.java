package application.cdms.report.meta.holder;



import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="xlsReportMetaDataDtl",namespace="http://www.example.org/reportMetadata")
@XmlAccessorType(XmlAccessType.FIELD)
public class XlsReportMetaInfo {

	@XmlAttribute(name="xlsWorkSheetNm")
	private String xlsWorkSheetNm;
	
	@XmlAttribute(name="exportfileNm")
	private String exportFileNm;
	
	@XmlAttribute(name="inputSourceNm")
	private String inputSourceNm;
	
	@XmlAttribute(name="inputSourcetype")
	private String inputSourcetype;
	
	@XmlElement(name="reportHeader",namespace="http://www.example.org/reportMetadata")
	private XlsReportHeader xlsReportHeader;

	public String getXlsWorkSheetNm() {
		return xlsWorkSheetNm;
	}

	public void setXlsWorkSheetNm(String xlsWorkSheetNm) {
		this.xlsWorkSheetNm = xlsWorkSheetNm;
	}

	public String getExportFileNm() {
		return exportFileNm;
	}

	public void setExportFileNm(String exportFileNm) {
		this.exportFileNm = exportFileNm;
	}

	public String getInputSourceNm() {
		return inputSourceNm;
	}

	public void setInputSourceNm(String inputSourceNm) {
		this.inputSourceNm = inputSourceNm;
	}

	public XlsReportHeader getXlsReportHeader() {
		return xlsReportHeader;
	}

	public void setXlsReportHeaderr(XlsReportHeader xlsReportHeader) {
		this.xlsReportHeader = xlsReportHeader;
	}

	public String getInputSourcetype() {
		return inputSourcetype;
	}

	public void setInputSourcetype(String inputSourcetype) {
		this.inputSourcetype = inputSourcetype;
	}

	public void setXlsReportHeader(XlsReportHeader xlsReportHeader) {
		this.xlsReportHeader = xlsReportHeader;
	}
}