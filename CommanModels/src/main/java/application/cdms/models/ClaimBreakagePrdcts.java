package application.cdms.models;


public class ClaimBreakagePrdcts {

	private long seqNum;

	private double basicAmt;

	private Double cellQtyWithoutBs;

	private double discountAmt;

	private String headerClaimId;

	private double mrp;

	private double netAmt;

	private String productCd;

	private double productQtyBs;

	private double taxAmt;

	private double taxableAmt;

	public long getSeqNum() {
		return this.seqNum;
	}

	public void setSeqNum(long seqNum) {
		this.seqNum = seqNum;
	}

	public double getBasicAmt() {
		return this.basicAmt;
	}

	public void setBasicAmt(double basicAmt) {
		this.basicAmt = basicAmt;
	}

	public Double getCellQtyWithoutBs() {
		return this.cellQtyWithoutBs;
	}

	public void setCellQtyWithoutBs(Double cellQtyWithoutBs) {
		this.cellQtyWithoutBs = cellQtyWithoutBs;
	}

	public double getDiscountAmt() {
		return this.discountAmt;
	}

	public void setDiscountAmt(double discountAmt) {
		this.discountAmt = discountAmt;
	}

	public String getHeaderClaimId() {
		return this.headerClaimId;
	}

	public void setHeaderClaimId(String headerClaimId) {
		this.headerClaimId = headerClaimId;
	}

	public double getMrp() {
		return this.mrp;
	}

	public void setMrp(double mrp) {
		this.mrp = mrp;
	}

	public double getNetAmt() {
		return this.netAmt;
	}

	public void setNetAmt(double netAmt) {
		this.netAmt = netAmt;
	}

	public String getProductCd() {
		return this.productCd;
	}

	public void setProductCd(String productCd) {
		this.productCd = productCd;
	}

	public double getProductQtyBs() {
		return this.productQtyBs;
	}

	public void setProductQtyBs(double productQtyBs) {
		this.productQtyBs = productQtyBs;
	}

	public double getTaxAmt() {
		return this.taxAmt;
	}

	public void setTaxAmt(double taxAmt) {
		this.taxAmt = taxAmt;
	}

	public double getTaxableAmt() {
		return this.taxableAmt;
	}

	public void setTaxableAmt(double taxableAmt) {
		this.taxableAmt = taxableAmt;
	}
}
