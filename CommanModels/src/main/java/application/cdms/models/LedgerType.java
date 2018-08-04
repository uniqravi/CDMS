package application.cdms.models;

public class LedgerType {

	private String ledgerTypeCd;
	private String ledgerTypeNm;
	private double discountQty;
	public String getLedgerTypeCd() {
		return ledgerTypeCd;
	}
	public void setLedgerTypeCd(String ledgerTypeCd) {
		this.ledgerTypeCd = ledgerTypeCd;
	}
	public String getLedgerTypeNm() {
		return ledgerTypeNm;
	}
	public void setLedgerTypeNm(String ledgerTypeNm) {
		this.ledgerTypeNm = ledgerTypeNm;
	}
	public double getDiscountQty() {
		return discountQty;
	}
	public void setDiscountQty(double discountQty) {
		this.discountQty = discountQty;
	}
	@Override
	public String toString() {
		return "[ledgerTypeNm=" + ledgerTypeNm + ", discountQty=" + discountQty + "]";
	}
}
