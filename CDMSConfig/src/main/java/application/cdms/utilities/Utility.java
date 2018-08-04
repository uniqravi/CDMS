package application.cdms.utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.transform.Scale;

public class Utility{
	
	public static int[] extractIntFraction(double d){
		//Object 
		String dStr = d+"";
		int[] numbers = new int[2];
		String patternStr = "([0-9]*)(.)?([0-9]*)";
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(dStr);
		if(matcher.matches()){
			numbers[0]=Integer.parseInt(matcher.group(1));
			numbers[1]=Integer.parseInt(matcher.group(3));
		}
		else{
			System.out.println("No matching found");
		}	
		return numbers;
	}
	
	public static double calculateProdPrice(double prodQty,int pktSize,double pktPrice){
		int[] pktQtyArray=extractIntFraction(prodQty);
		double qtyPrice = pktPrice*pktQtyArray[0]+(pktPrice*pktQtyArray[1])/pktSize;
		return decimalRound(qtyPrice);
	}
	public static double decimalRound(double num){
		 double roundOff = (double) Math.round(num * 100) / 100;
		 return roundOff;
	}
	
	public static boolean print(final Node node) {
        Printer printer = Printer.getDefaultPrinter();	
        PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.DEFAULT);
        double scaleX = pageLayout.getPrintableWidth() / node.getBoundsInParent().getWidth();
        double scaleY = pageLayout.getPrintableHeight() / node.getBoundsInParent().getHeight();
        node.getTransforms().add(new Scale(scaleX, scaleY));
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            boolean success = job.printPage(node);
            if (success) {
                job.endJob();
                return true;
            }
        }
        return false;
    }
	
}
