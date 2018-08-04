package application.cdms.utilities;

import java.awt.Rectangle;
//import java.awt.print.PageFormat;
//import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.swing.JComponent;

import javafx.concurrent.Task;
import javafx.scene.Node;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;

public class GenerateReport{ 
	/**
	 * 
	 */
	
	public <T> JComponent  showReport(String sourceReportFile, List<T> list,Map<String,Object> params) throws JRException, FileNotFoundException{
	    JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list,false);
	    JasperPrint print = JasperFillManager.fillReport(sourceReportFile, params, beanColDataSource);
	    JRViewer viewer = new JRViewer(print);
	    viewer.setOpaque(true);
	    viewer.setVisible(true);
	    viewer.scrollRectToVisible(new Rectangle(1200, 800));
	    System.out.print("Report Completed!");
	    return viewer;
	}
	public <T> Node showPrintReport(String sourceReportFile,List<T> list,Map<String,Object> params,String destLoc) throws MalformedURLException, JRException{
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list,false);
	    JasperPrint print = JasperFillManager.fillReport(sourceReportFile, params, beanColDataSource);
	    printJasperReport(print);
	    JasperExportManager.exportReportToHtmlFile(print, destLoc);
	    final WebView browser = new WebView();
        final WebEngine webEngine = browser.getEngine();
        File file = new File(destLoc);
        URL url = file.toURI().toURL();
        webEngine.load(url.toString());
	    System.out.print("Report Completed!");
	    return browser;
	}
	
	public <T> Node compileShowPrintReport(String sourceReportFile,List<T> list,Map<String,Object> params,String destLoc) throws MalformedURLException, JRException{
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list,false);
		JasperReport masterReport = JasperCompileManager.compileReport(sourceReportFile+".jrxml");
		JasperReport subReport = JasperCompileManager.compileReport(sourceReportFile+"_subreport.jrxml");
		params.put("subreportParameter", subReport);
	    JasperPrint print = JasperFillManager.fillReport(masterReport, params, beanColDataSource);
	    printJasperReport(print);
	    JasperExportManager.exportReportToHtmlFile(print, destLoc);
	    final WebView browser = new WebView();
        final WebEngine webEngine = browser.getEngine();
        File file = new File(destLoc);
        URL url = file.toURI().toURL();
        webEngine.load(url.toString());
	    System.out.print("Report Completed!");
	    return browser;
	}
	
	public void printJasperReport(JasperPrint print){
		Task<Boolean> printTask = new Task<Boolean>(){
	    	@Override
            protected Boolean call() throws JRException {
	    		return JasperPrintManager.printReport(print, true); 
            }
	    };
	    printTask.setOnSucceeded( (e) ->{
				try {
					printTask.get();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			
	    });
	    new Thread(printTask).start();
	}
}
