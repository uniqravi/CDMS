package application.cdms.models;

public class PurchaseProductDtl {
	//private Product product;
	private ProductCatagory product;
	private long product_qty;
	private ProductBreakageDtl braekageDtl;
	private String recieveDt;
	private String purchaseProductId;
	private double perPacketBasePrice;
	private double totalBaseAmt;
	private double discountAmt;
	private double netTaxableAmt;
	private double totalPrdctCGSTAmt;
	private double totalPrdctSGSTAmt;
	private double totalPrdctIGSTAmt;
	private double totalPrdctCessAmt;
	private double netPerCsPrice;
	private double netPrdctAmnt;
	private Double mrp;
	private double unitPrice;
	
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public ProductCatagory getProduct() {
		return product;
	}
	public void setProduct(ProductCatagory product) {
		this.product = product;
	}
	
	public long getProduct_qty() {
		return product_qty;
	}
	public void setProduct_qty(long product_qty) {
		this.product_qty = product_qty;
	}
	public ProductBreakageDtl getBraekageDtl() {
		return braekageDtl;
	}
	public void setBraekageDtl(ProductBreakageDtl braekageDtl) {
		this.braekageDtl = braekageDtl;
	}
	public String getRecieveDt() {
		return recieveDt;
	}
	public void setRecieveDt(String recieveDt) {
		this.recieveDt = recieveDt;
	}
	public String getPurchaseProductId() {
		return purchaseProductId;
	}
	public void setPurchaseProductId(String purchaseProductId) {
		this.purchaseProductId = purchaseProductId;
	}
	public double getPerPacketBasePrice() {
		return perPacketBasePrice;
	}
	public void setPerPacketBasePrice(double perPacketBasePrice) {
		this.perPacketBasePrice = perPacketBasePrice;
	}
	public double getTotalBaseAmt() {
		return totalBaseAmt;
	}
	public void setTotalBaseAmt(double totalBaseAmt) {
		this.totalBaseAmt = totalBaseAmt;
	}
	public double getDiscountAmt() {
		return discountAmt;
	}
	public void setDiscountAmt(double discountAmt) {
		this.discountAmt = discountAmt;
	}
	public double getNetTaxableAmt() {
		return netTaxableAmt;
	}
	public void setNetTaxableAmt(double netTaxableAmt) {
		this.netTaxableAmt = netTaxableAmt;
	}
	public double getTotalPrdctCGSTAmt() {
		return totalPrdctCGSTAmt;
	}
	public void setTotalPrdctCGSTAmt(double totalPrdctCGSTAmt) {
		this.totalPrdctCGSTAmt = totalPrdctCGSTAmt;
	}
	public double getTotalPrdctSGSTAmt() {
		return totalPrdctSGSTAmt;
	}
	public void setTotalPrdctSGSTAmt(double totalPrdctSGSTAmt) {
		this.totalPrdctSGSTAmt = totalPrdctSGSTAmt;
	}
	public double getTotalPrdctCessAmt() {
		return totalPrdctCessAmt;
	}
	public void setTotalPrdctCessAmt(double totalPrdctCessAmt) {
		this.totalPrdctCessAmt = totalPrdctCessAmt;
	}
	public double getNetPerCsPrice() {
		return netPerCsPrice;
	}
	public void setNetPerCsPrice(double netPerCsPrice) {
		this.netPerCsPrice = netPerCsPrice;
	}
	public double getNetPrdctAmnt() {
		return netPrdctAmnt;
	}
	public void setNetPrdctAmnt(double netPrdctAmnt) {
		this.netPrdctAmnt = netPrdctAmnt;
	}
	public double getTotalPrdctIGSTAmt() {
		return totalPrdctIGSTAmt;
	}
	public void setTotalPrdctIGSTAmt(double totalPrdctIGSTAmt) {
		this.totalPrdctIGSTAmt = totalPrdctIGSTAmt;
	}
	public Double getMrp() {
		return mrp;
	}
	public void setMrp(Double mrp) {
		this.mrp = mrp;
	}
}