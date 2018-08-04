package application.cdms.report.build;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import application.cdms.report.meta.holder.XlsReportMetaInfo;
import application.cdms.utilities.PropertyResourceBundle;

public class ReportXMLMetaReader implements ReportMetaReader{

	@Override
	public XlsReportMetaInfo readReportMetaInfo(String reportNm) throws JAXBException {
		XlsReportMetaInfo reportMetaInfo=null;
		try {
		JAXBContext jaxb = JAXBContext.newInstance(XlsReportMetaInfo.class);
		Unmarshaller unmarshaller = jaxb.createUnmarshaller();
		reportMetaInfo=(XlsReportMetaInfo) unmarshaller.unmarshal(getClass().getClassLoader().getResourceAsStream(PropertyResourceBundle.get(reportNm)));
		} catch (JAXBException e) {
			e.printStackTrace();
			throw e;
		}
		return reportMetaInfo;
	}
}