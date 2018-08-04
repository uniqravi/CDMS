package application.cdms.utilities;

import java.math.BigDecimal;

public final class MoneyCalculation {

	  private static int ROUNDING_MODE = BigDecimal.ROUND_HALF_EVEN;

	  /**
	  * Number of decimals to retain. Also referred to as "scale".
	  */
	  private static int DECIMALS = 2;
	  //An alternate style for this value :
	  //private static int DECIMAL_PLACES =
	  //  Currency.getInstance("USD").getDefaultFractionDigits()
	  //;

	  private static int EXTRA_DECIMALS = 4;
	 // private static final BigDecimal TWO = new BigDecimal("2");
	  private static BigDecimal HUNDRED = new BigDecimal("100");
	  

	  public static BigDecimal getSum(BigDecimal result,BigDecimal toBeAdded){
	    return result.add(toBeAdded);
	  }

	  private static BigDecimal getDifference(BigDecimal sub,BigDecimal toBeSub){
	    return sub.subtract(toBeSub);
	  }

	  public static BigDecimal getAverage(BigDecimal... numbers){
		  int size=numbers.length;
		  BigDecimal result=new BigDecimal("0");
		  for(int i=0;i<size;i++){
			  result.add(numbers[i]);
		  }
	    return result.divide(new BigDecimal(size), ROUNDING_MODE);
	  }

	  public static BigDecimal getPercentage(BigDecimal number,BigDecimal percentage){
	    BigDecimal result = number.multiply(percentage);
	    result = result.divide(HUNDRED, ROUNDING_MODE);
	    return rounded(result);
	  }

	  public static BigDecimal getPercentageChange(BigDecimal number1,BigDecimal number2){
	    BigDecimal fractionalChange = getDifference(number2,number1).divide(number1, EXTRA_DECIMALS, ROUNDING_MODE);
	    return rounded(fractionalChange.multiply(HUNDRED));
	  }

	  
	  public static BigDecimal rounded(BigDecimal aNumber){
	    return aNumber.setScale(DECIMALS, ROUNDING_MODE);
	  }
	  
	  public static  BigDecimal divide(BigDecimal divider,BigDecimal dividor){
		  	BigDecimal result1 = divider.divide(dividor,2,BigDecimal.ROUND_CEILING);
		    return result1;
	  }
	  
	  
	  
	  public static void main(String[] args){
		//  BigDecimal dce  = divide(new BigDecimal(218), new BigDecimal(24));
		 // System.out.println(dce);
		 // System.out.println(dce.multiply(new BigDecimal(24)));
		  
		  //double d = 218.00/24;
		  double basePrice = 300.00/1.40;
		  System.out.println(basePrice);
		  double totalAmnt = basePrice*5;
		  System.out.println(totalAmnt);
		  double p14_d= totalAmnt*0.14;
		  System.out.println(p14_d);
		  double p_12 = totalAmnt*0.12;
		  System.out.println(p_12);
		  double ttlAmnt = totalAmnt+p14_d+p14_d+p_12;
		  System.out.println(ttlAmnt);
	  }
	} 
