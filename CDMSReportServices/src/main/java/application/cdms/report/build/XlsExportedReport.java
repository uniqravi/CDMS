package application.cdms.report.build;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import application.cdms.report.data.holder.ReportBasicMetaDataHolder;
import application.cdms.report.data.holder.ReportColDataHolder;
import application.cdms.report.data.holder.ReportColHeaderDataHolder;
import application.cdms.report.data.holder.ReportColMetaDataHolder;
import application.cdms.report.data.holder.ReportGroupHeaderDataHolder;
import application.cdms.report.data.holder.ReportHeaderMetaDataHolder;
import application.cdms.report.data.holder.ReportMetaDataHolder;
import application.cdms.report.data.holder.ReportTopHeaderDataHolder;

public class XlsExportedReport extends AbstractExportReportManager{

	ReportMetaDataHolder metaDataHolder;
	
	private XSSFWorkbook workbook=null;
	
	private XSSFSheet sheet = null;
	
	private int currRow=0;
	
	private static final Map<String, Short> colorIndexedMap = new HashMap<>();
	
	static {
		colorIndexedMap.put("WHITE", IndexedColors.WHITE1.getIndex());
		colorIndexedMap.put("BLACK", IndexedColors.BLACK1.getIndex());
		colorIndexedMap.put("BLUE", IndexedColors.BLUE1.getIndex());
		colorIndexedMap.put("SKY_BLUE", IndexedColors.SKY_BLUE.getIndex());
		colorIndexedMap.put("PALE_BLUE", IndexedColors.PALE_BLUE.getIndex());
		colorIndexedMap.put("CORNFLOWER_BLUE", IndexedColors.CORNFLOWER_BLUE.getIndex());
		colorIndexedMap.put("INDIGO", IndexedColors.INDIGO.getIndex());
	}
	
	public XlsExportedReport(ReportMetaDataHolder metaDataHolder){
		this.metaDataHolder=metaDataHolder;
		buildWorkBook();
	}
	
	@Override
	public void exportReport() {
		try(FileOutputStream outputStream = new FileOutputStream(metaDataHolder.getExportfileNm()+".xlsx")){
			workbook.write(outputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void buildWorkBook(){
		workbook=new XSSFWorkbook();
		sheet = workbook.createSheet(metaDataHolder.getXlsWorkSheetNm());
		createRowTitle();
		createGroupHeaders();
	}

	private void createRowTitle(){
		ReportTopHeaderDataHolder topHeaderDtl=metaDataHolder.getReportHeader().getTopHeader();
		ReportHeaderMetaDataHolder headerMetaData=topHeaderDtl.getHeaderMetaData();
		String title = headerMetaData.getHeaderText();
		ReportBasicMetaDataHolder basicMeta = headerMetaData.getMetaData();
		XSSFRow row=sheet.createRow(currRow);
		Cell cell=row.createCell(0);
		cell.setCellValue(title);
		setStyle(row,cell,basicMeta,true);
		setRowColSpan(basicMeta,true);
		cell.getCellStyle().setAlignment(HorizontalAlignment.CENTER);
		cell.getCellStyle().setVerticalAlignment(VerticalAlignment.CENTER);
	}
	
	private void createGroupHeaders() {
		ReportGroupHeaderDataHolder[] groupHeaderDtls = metaDataHolder.getReportHeader().getGroupHeader();
			for(ReportGroupHeaderDataHolder groupHeaderDtl : groupHeaderDtls){
				createGroupHeader(groupHeaderDtl);
				//pending row update
			}
	}
	
	private void createGroupHeader(ReportGroupHeaderDataHolder groupHeaderDtl){
		ReportHeaderMetaDataHolder groupMetaHolder=groupHeaderDtl.getHeaderMetaData();
		String valueType=groupMetaHolder.getValType();
		if(!NO_GROUP.equalsIgnoreCase(valueType)){
			String groupHeading = groupMetaHolder.getHeaderText();
			ReportBasicMetaDataHolder basicMetaData=groupMetaHolder.getMetaData();
			XSSFRow row = sheet.createRow(currRow);
			Cell cell=row.createCell(0);
			cell.setCellValue(groupHeading);
			setStyle(row,cell,basicMetaData,true);
			setRowColSpan(basicMetaData,true);
		}
		//set Headers
		createHeaders(groupHeaderDtl);
	}
	
	private void createHeaders(ReportGroupHeaderDataHolder groupHeaderDtl) {
		// TODO Auto-generated method stub
		ReportColHeaderDataHolder[] colHeaders=groupHeaderDtl.getHeader();
		for(int i=0;i<colHeaders.length;i++){
			ReportColHeaderDataHolder colHeader = colHeaders[i];
			ReportHeaderMetaDataHolder headerNodeMeta=colHeader.getHeaderMetaData();
			createHeaderNode(headerNodeMeta,currRow,i);
			createDataNode(colHeader.getColData(),currRow+1,i);
		}
	}

	private void createHeaderNode(ReportHeaderMetaDataHolder headerNodeMeta,int startRowIndex,int colIndex){
		String headerTxt = headerNodeMeta.getHeaderText();
		ReportBasicMetaDataHolder basicMetaData=headerNodeMeta.getMetaData(); 
		int rowIndex = startRowIndex;
		XSSFRow row = sheet.getRow(rowIndex);
		if(row==null){
			row = sheet.createRow(rowIndex);
		}
		Cell cell=row.createCell(colIndex);
		cell.setCellValue(headerTxt);
		setStyle(row,cell,basicMetaData,false);
		//setRowColSpan(basicMetaData,true);
	}
	
	private void createDataNode(ReportColDataHolder colData, int startRowIndex, int colIndex) {
		ReportColMetaDataHolder dataNodeMetaData=colData.getColMetaData();
		ReportBasicMetaDataHolder basicMetaData=dataNodeMetaData.getMetaData();
		int rowIndex = startRowIndex;
		String[] strArr = colData.getData();
		for(String str : strArr){
			XSSFRow row = sheet.getRow(rowIndex);
			if(row==null){
				row = sheet.createRow(rowIndex);
			}
			Cell cell=row.createCell(colIndex);
			cell.setCellValue(str);
			setStyle(row,cell,basicMetaData,false);
			rowIndex++;
			sheet.autoSizeColumn(colIndex);
		}
	}

	private void setStyle(Row row,Cell cell,ReportBasicMetaDataHolder basicMeta,boolean isBold){
		String bgColor=basicMeta.getBgcolor();
		String fontColor=basicMeta.getFontcolor();
		XSSFFont font = workbook.createFont();
		font.setBold(isBold);
		short fontColrIndex = getColorIndex(fontColor);
		if(fontColrIndex==-2){
			font.setColor(new XSSFColor(hex2Rgb(fontColor)));
		}
		else{
			font.setColor(fontColrIndex==-1?colorIndexedMap.get(DEFAULT_HEADERFONTCOLOR):fontColrIndex);
		}
		XSSFCellStyle cellStyle = workbook.createCellStyle();
		short bgIndex = getColorIndex(bgColor);
		if(bgIndex==-2){
			cellStyle.setFillForegroundColor(new XSSFColor(hex2Rgb(bgColor)));
		}
		else{
			cellStyle.setFillForegroundColor(bgIndex==-1?colorIndexedMap.get(DEFAULT_HEADERBGCOLOR):bgIndex);
		}
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cellStyle.setFont(font);
		cellStyle.setBorderBottom(BorderStyle.THIN);
		cellStyle.setBorderLeft(BorderStyle.THIN);
		cellStyle.setBorderTop(BorderStyle.THIN);
		cellStyle.setBorderRight(BorderStyle.THIN);
		cell.setCellStyle(cellStyle);
	}
	
	private void setRowColSpan(ReportBasicMetaDataHolder basicMeta,boolean isToporGroupHeader){
		int rowspan=basicMeta.getRowspan();
		int colspan=basicMeta.getColspan();
		if(rowspan!=0 && colspan!=0){
			if(isToporGroupHeader){
				sheet.addMergedRegion(new CellRangeAddress(currRow,currRow+rowspan,0,colspan));
				currRow+=(rowspan+1);
			}
			else{
				
			}
		}
	}
	
	private Short getColorIndex(String color){
		if(color==null || color.equals("")){
			return -1;
		}
		else if(color.startsWith("#")){
			return -2;
		}
		else{
			Short colorIndex=colorIndexedMap.get(color.toUpperCase());
			return colorIndex==null?-1:colorIndex;
		}
	}
	
	private Color hex2Rgb(String colorStr) {
			return new Color(
	            Integer.valueOf( colorStr.substring( 1, 3 ), 16 ),
	            Integer.valueOf( colorStr.substring( 3, 5 ), 16 ),
	            Integer.valueOf( colorStr.substring( 5, 7 ), 16 ) );
	}
}
