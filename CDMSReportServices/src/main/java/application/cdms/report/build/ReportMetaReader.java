package application.cdms.report.build;

import javax.xml.bind.JAXBException;

import application.cdms.report.meta.holder.XlsReportMetaInfo;

public interface ReportMetaReader {
	XlsReportMetaInfo readReportMetaInfo(String reportNm) throws JAXBException;
}