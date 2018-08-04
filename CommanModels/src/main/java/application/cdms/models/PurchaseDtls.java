package application.cdms.models;

import java.util.List;

public class PurchaseDtls {
	private String purchaseId;
	private List<PurchaseProductDtl> purchaseProductDtls;
	private List<PurchaseProductDtl> nonBeverageProdctsDtls;
	private String challanNumber;
	private String challanDt;
	private String recieveDt;
	private String challanInvoiceNumber;
	private double challanAmount;
	private double totalDiscount;
	private long totalpurchasedProduct;
	private long totalGlassQty;
	private long totalReturnGlassQty;
	private long buyingBottleQty;
	private long buyingCellQty;
	private long returingBottleQty;
	private long returningCellQty;
	private String paymentMethod;
	private String paymentId;
	private double paidAmount;
    private String purchaseComment;
    private String paymentDt;
    private String firmNm;
    private String firmGstn;
	
	public List<PurchaseProductDtl> getPurchaseProductDtls() {
		return purchaseProductDtls;
	}

	public void setPurchaseProductDtls(List<PurchaseProductDtl> purchaseProductDtls) {
		this.purchaseProductDtls = purchaseProductDtls;
	}

	public String getChallanNumber() {
		return challanNumber;
	}

	public void setChallanNumber(String challanNumber) {
		this.challanNumber = challanNumber;
	}

	public String getChallanDt() {
		return challanDt;
	}

	public void setChallanDt(String challanDt) {
		this.challanDt = challanDt;
	}

	public String getRecieveDt() {
		return recieveDt;
	}

	public void setRecieveDt(String recieveDt) {
		this.recieveDt = recieveDt;
	}

	public String getChallanInvoiceNumber() {
		return challanInvoiceNumber;
	}

	public void setChallanInvoiceNumber(String challanInvoiceNumber) {
		this.challanInvoiceNumber = challanInvoiceNumber;
	}

	public double getChallanAmount() {
		return challanAmount;
	}

	public void setChallanAmount(double challanAmount) {
		this.challanAmount = challanAmount;
	}

	public double getTotalDiscount() {
		return totalDiscount;
	}

	public void setTotalDiscount(double totalDiscount) {
		this.totalDiscount = totalDiscount;
	}

	public long getTotalpurchasedProduct() {
		return totalpurchasedProduct;
	}

	public void setTotalpurchasedProduct(long totalpurchasedProduct) {
		this.totalpurchasedProduct = totalpurchasedProduct;
	}

	public long getTotalGlassQty() {
		return totalGlassQty;
	}

	public void setTotalGlassQty(long totalGlassQty) {
		this.totalGlassQty = totalGlassQty;
	}

	public long getTotalReturnGlassQty() {
		return totalReturnGlassQty;
	}

	public void setTotalReturnGlassQty(long totalReturnGlassQty) {
		this.totalReturnGlassQty = totalReturnGlassQty;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public double getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}

	public String getPurchaseComment() {
		return purchaseComment;
	}

	public void setPurchaseComment(String purchaseComment) {
		this.purchaseComment = purchaseComment;
	}

	public String getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(String purchaseId) {
		this.purchaseId = purchaseId;
	}

	public String getPaymentDt() {
		return paymentDt;
	}

	public void setPaymentDt(String paymentDt) {
		this.paymentDt = paymentDt;
	}

	public String getFirmNm() {
		return firmNm;
	}

	public void setFirmNm(String firmNm) {
		this.firmNm = firmNm;
	}

	public String getFirmGstn() {
		return firmGstn;
	}

	public void setFirmGstn(String firmGstn) {
		this.firmGstn = firmGstn;
	}

	public List<PurchaseProductDtl> getNonBeverageProdctsDtls() {
		return nonBeverageProdctsDtls;
	}

	public void setNonBeverageProdctsDtls(List<PurchaseProductDtl> nonBeverageProdctsDtls) {
		this.nonBeverageProdctsDtls = nonBeverageProdctsDtls;
	}

	public long getBuyingBottleQty() {
		return buyingBottleQty;
	}

	public void setBuyingBottleQty(long buyingBottleQty) {
		this.buyingBottleQty = buyingBottleQty;
	}

	public long getBuyingCellQty() {
		return buyingCellQty;
	}

	public void setBuyingCellQty(long buyingCellQty) {
		this.buyingCellQty = buyingCellQty;
	}

	public long getReturingBottleQty() {
		return returingBottleQty;
	}

	public void setReturingBottleQty(long returingBottleQty) {
		this.returingBottleQty = returingBottleQty;
	}

	public long getReturningCellQty() {
		return returningCellQty;
	}

	public void setReturningCellQty(long returningCellQty) {
		this.returningCellQty = returningCellQty;
	}
}