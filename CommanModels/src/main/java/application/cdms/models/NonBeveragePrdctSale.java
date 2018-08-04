package application.cdms.models;

public class NonBeveragePrdctSale {

	
	private int seqNumber;
	
	private long sellingQty;
	
	private double unitPrice;
	
	private NonBeveragePrdct nonBeveragePrdct;
	
	private double netBaseAmt;

	private double disocuntAmt;
	
	private double taxableAmt;
	
	private double sgstRate;
	
	private double sgstAmt;

	private double cgstRate;

	private double cgstAmt;

	private double cessRate;

	private double cessAmt;
	
	private double igstRate;

	private double igstAmt;

	private double sysGnrtdNetAmt;

	public int getSeqNumber() {
		return seqNumber;
	}

	public void setSeqNumber(int seqNumber) {
		this.seqNumber = seqNumber;
	}

	public long getSellingQty() {
		return sellingQty;
	}

	public void setSellingQty(long sellingQty) {
		this.sellingQty = sellingQty;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public double getNetBaseAmt() {
		return netBaseAmt;
	}

	public void setNetBaseAmt(double netBaseAmt) {
		this.netBaseAmt = netBaseAmt;
	}

	public double getDisocuntAmt() {
		return disocuntAmt;
	}

	public void setDisocuntAmt(double disocuntAmt) {
		this.disocuntAmt = disocuntAmt;
	}

	public double getTaxableAmt() {
		return taxableAmt;
	}

	public void setTaxableAmt(double taxableAmt) {
		this.taxableAmt = taxableAmt;
	}

	public double getSgstRate() {
		return sgstRate;
	}

	public void setSgstRate(double sgstRate) {
		this.sgstRate = sgstRate;
	}

	public double getSgstAmt() {
		return sgstAmt;
	}

	public void setSgstAmt(double sgstAmt) {
		this.sgstAmt = sgstAmt;
	}

	public double getCgstRate() {
		return cgstRate;
	}

	public void setCgstRate(double cgstRate) {
		this.cgstRate = cgstRate;
	}

	public double getCgstAmt() {
		return cgstAmt;
	}

	public void setCgstAmt(double cgstAmt) {
		this.cgstAmt = cgstAmt;
	}

	public double getCessRate() {
		return cessRate;
	}

	public void setCessRate(double cessRate) {
		this.cessRate = cessRate;
	}

	public double getCessAmt() {
		return cessAmt;
	}

	public void setCessAmt(double cessAmt) {
		this.cessAmt = cessAmt;
	}

	public double getIgstRate() {
		return igstRate;
	}

	public void setIgstRate(double igstRate) {
		this.igstRate = igstRate;
	}

	public double getIgstAmt() {
		return igstAmt;
	}

	public void setIgstAmt(double igstAmt) {
		this.igstAmt = igstAmt;
	}

	public double getSysGnrtdNetAmt() {
		return sysGnrtdNetAmt;
	}

	public void setSysGnrtdNetAmt(double sysGnrtdNetAmt) {
		this.sysGnrtdNetAmt = sysGnrtdNetAmt;
	}

	public NonBeveragePrdct getNonBeveragePrdct() {
		return nonBeveragePrdct;
	}

	public void setNonBeveragePrdct(NonBeveragePrdct nonBeveragePrdct) {
		this.nonBeveragePrdct = nonBeveragePrdct;
	}
}
