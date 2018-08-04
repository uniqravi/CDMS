package application.cdms.models;

public class ReturnPurchases{

	private String challanInvoiceNo;

	private String returnPurchaseInvoiceNo;

	public ReturnPurchases() {
	}

	public String getChallanInvoiceNo() {
		return this.challanInvoiceNo;
	}

	public void setChallanInvoiceNo(String challanInvoiceNo) {
		this.challanInvoiceNo = challanInvoiceNo;
	}

	public String getReturnPurchaseInvoiceNo() {
		return this.returnPurchaseInvoiceNo;
	}

	public void setReturnPurchaseInvoiceNo(String returnPurchaseInvoiceNo) {
		this.returnPurchaseInvoiceNo = returnPurchaseInvoiceNo;
	}

}
