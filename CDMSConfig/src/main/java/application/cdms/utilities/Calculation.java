package application.cdms.utilities;

public class Calculation {

	public static double decimalRound(double num){
		 double roundOff = (double) Math.round(num * 100) / 100;
		 return roundOff;
	}
}
