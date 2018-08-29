package application.cdms.enums;

public enum PaymentMethod {
    NEFT("NEFT"),CHEQUE("CHEQUE"),IMPS("IMPS"),RTGS("RTGS"); 
    
    private String payWay;
    PaymentMethod(String payWay){
    	this.payWay=payWay;
    }
    public String getPaymentMethod(){
    	return payWay;
    }
}
