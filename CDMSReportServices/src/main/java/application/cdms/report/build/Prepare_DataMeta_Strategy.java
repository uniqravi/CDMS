package application.cdms.report.build;

import java.util.List;
import java.util.Map;

import application.cdms.report.data.holder.ReportBasicMetaDataHolder;
import application.cdms.report.data.holder.ReportColDataHolder;
import application.cdms.report.data.holder.ReportColHeaderDataHolder;
import application.cdms.report.data.holder.ReportColMetaDataHolder;
import application.cdms.report.data.holder.ReportGroupHeaderDataHolder;
import application.cdms.report.data.holder.ReportHeaderHolder;
import application.cdms.report.data.holder.ReportHeaderMetaDataHolder;
import application.cdms.report.data.holder.ReportMetaDataHolder;
import application.cdms.report.data.holder.ReportTopHeaderDataHolder;
import application.cdms.report.meta.holder.XlsGroupHeader;
import application.cdms.report.meta.holder.XlsHeader;
import application.cdms.report.meta.holder.XlsReportMetaInfo;
import application.cdms.report.meta.holder.XlsTopHeader;

public class Prepare_DataMeta_Strategy {

	private XlsReportMetaInfo metaInfo;
	
	private Map<String,String[]> dataMap;
	
	private ReportMetaDataHolder metaDataHolder;
	
	private ReportElEvaluator elEvaluator;
	
	public Prepare_DataMeta_Strategy(XlsReportMetaInfo metaInfo,Map<String,String[]> dataMap,Map<String,String> elValuesMap){
		this.metaInfo=metaInfo;
		this.dataMap=dataMap;
		this.elEvaluator=new ReportElEvaluator(elValuesMap);
		prepareStrategyData();
	}

	private void prepareStrategyData() {
		metaDataHolder = new ReportMetaDataHolder(elEvaluator.getReportElValue(metaInfo.getXlsWorkSheetNm()), metaInfo.getExportFileNm(), metaInfo.getInputSourcetype());
		ReportHeaderHolder reportHeaderHolder = new ReportHeaderHolder();
		reportHeaderHolder.setTopHeader(createTopHeader());
		reportHeaderHolder.setGroupHeader(createGroupHeaderArr());
		metaDataHolder.setReportHeader(reportHeaderHolder);
	}
	public ReportMetaDataHolder getDataMetaHolder(){
		return metaDataHolder;
	}
	
	private ReportTopHeaderDataHolder createTopHeader(){
		ReportTopHeaderDataHolder topHeaderDataHolder = new ReportTopHeaderDataHolder();
		XlsTopHeader topMetaHeader=metaInfo.getXlsReportHeader().getTopHeader();
		topHeaderDataHolder.setHeaderMetaData(createHeaderMeta(topMetaHeader.getValType(), topMetaHeader.getHeaderText(),topMetaHeader.getHeadercolspan(), topMetaHeader.getHeaderrowspan(), topMetaHeader.getHeaderbgcolor(), topMetaHeader.getHeaderfontcolor()));
		return topHeaderDataHolder;
	}
	
	private ReportGroupHeaderDataHolder[] createGroupHeaderArr(){
		List<XlsGroupHeader> grpLst=metaInfo.getXlsReportHeader().getGroupHeader();
		if(grpLst!=null && grpLst.size()>0){
			ReportGroupHeaderDataHolder[] groupHeaderArry = new ReportGroupHeaderDataHolder[grpLst.size()];
			int i=0;
			for(XlsGroupHeader xlsGrp : grpLst){
				ReportGroupHeaderDataHolder grpHeaderHolder = new ReportGroupHeaderDataHolder();
				grpHeaderHolder.setHeaderMetaData(createHeaderMeta(xlsGrp.getValType(), xlsGrp.getHeaderText(), xlsGrp.getHeadercolspan(), 
						xlsGrp.getHeaderrowspan(), xlsGrp.getHeaderbgcolor(), xlsGrp.getHeaderfontcolor()));
				ReportColHeaderDataHolder[] colHeaders = new ReportColHeaderDataHolder[xlsGrp.getHeaders().size()];
				int j=0;
				for(XlsHeader xlsHeader:xlsGrp.getHeaders()){
					ReportColHeaderDataHolder colHeaderData = new ReportColHeaderDataHolder();
					colHeaderData.setColData(createColData(xlsHeader));
					colHeaderData.setHeaderMetaData(createHeaderMeta(xlsHeader.getValType(), xlsHeader.getHeaderText(), 
							xlsHeader.getHeadercolspan(), xlsHeader.getHeaderrowspan(), xlsHeader.getHeaderbgcolor(), 
							xlsHeader.getHeaderfontcolor()));
					colHeaders[j++]=colHeaderData;
				}
				grpHeaderHolder.setHeader(colHeaders);
				groupHeaderArry[i++]=grpHeaderHolder;
			}
			return groupHeaderArry;
		}
		return null;
	}
	
	private ReportBasicMetaDataHolder createBasicMetadata(int colspan,int rowspan,String bgColor,String fontcolor){
		return new ReportBasicMetaDataHolder(colspan, fontcolor, rowspan, bgColor);
	}
	private ReportHeaderMetaDataHolder createHeaderMeta(String valType,String headerText,int colspan,int rowspan,String bgColor,String fontcolor){
		ReportHeaderMetaDataHolder reportHeaderMetaDataHolder = new ReportHeaderMetaDataHolder();
		reportHeaderMetaDataHolder.setHeaderText(headerText);
		reportHeaderMetaDataHolder.setValType(valType);
		reportHeaderMetaDataHolder.setMetaData(createBasicMetadata(colspan, rowspan, bgColor, fontcolor));
		return reportHeaderMetaDataHolder;
	}
	private ReportColDataHolder createColData(XlsHeader xlsHeader){
		ReportColDataHolder colinfoHolder = new ReportColDataHolder();
		colinfoHolder.setData(dataMap.get(xlsHeader.getDataColName()));
		ReportColMetaDataHolder reportColMetaDataHolder = new ReportColMetaDataHolder();
		reportColMetaDataHolder.setMetaData(createBasicMetadata(xlsHeader.getDatacolspan(), xlsHeader.getDatarowspan(), 
				xlsHeader.getDatabgcolor(), xlsHeader.getDatafontcolor()));
		colinfoHolder.setColMetaData(reportColMetaDataHolder);
		return colinfoHolder;
	}
}