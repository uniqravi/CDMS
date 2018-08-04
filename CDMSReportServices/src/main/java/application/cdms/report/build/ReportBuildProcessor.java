package application.cdms.report.build;

import java.util.Map;

import application.cdms.exception.handler.CDMSGeneralException;
import application.cdms.report.data.holder.ReportMetaDataHolder;
import application.cdms.report.meta.holder.XlsReportMetaInfo;
import javafx.scene.Node;

public class ReportBuildProcessor {
	
	private static final String fxTableViewType = "fxTableViewType";
	
	private static final String queryType="queryType";
	
	public void startBuildProcess(String reportNm,Node node){
		try{
			//reading xml meta files
			ReportMetaReader metaReader = new ReportXMLMetaReader();
			XlsReportMetaInfo reportMetaInfo=metaReader.readReportMetaInfo(reportNm);
			
			//data collection process start
			ReportDataCollector dataCollector=this.getDataCollector(reportMetaInfo,node);
			Map<String,String[]> dataMap=dataCollector.collectReportData();
			
			//prepare data n meta  Strategy
			Prepare_DataMeta_Strategy dataNMetaPrepare = new Prepare_DataMeta_Strategy(reportMetaInfo, dataMap);
			ReportMetaDataHolder dataMetaHolder=dataNMetaPrepare.getDataMetaHolder();
			
			//generate Excel report
			System.out.println(dataMetaHolder);
			ExportReportManager reportBuilder = new XlsExportedReport(dataMetaHolder);
			reportBuilder.exportReport();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public ReportDataCollector getDataCollector(XlsReportMetaInfo metaInfo,Node node) throws CDMSGeneralException{
		if(metaInfo==null){
			throw new CDMSGeneralException("meta info can not be null");
		}
		ReportDataCollector collector_Instance = null;
				
		String sourceType = metaInfo.getInputSourcetype();
		
		if(fxTableViewType.equals(sourceType)){
			collector_Instance = new NodeDataCollector(node);
		}
		else if(queryType.equals(sourceType)){
			//collector_Instance = 
			collector_Instance = new QueryDataCollector(metaInfo);
		}
		return collector_Instance;
	}
}