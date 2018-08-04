package application.cdms.models;

public class CstmrLedger {
	
	private String cstmrAcntNo;
	private CustomerDtl cstmr;
	private LedgerType ledgerType;
	private String acntClosingDt;
	private Double acntClosingDueBalance;
	private Double acntClosingDueEmptyCs;
	private String acntOpenBy;
	private String acntOpeningDt;
	private String acntClosedBy;
	
	public String getCstmrAcntNo() {
		return cstmrAcntNo;
	}
	public void setCstmrAcntNo(String cstmrAcntNo) {
		this.cstmrAcntNo = cstmrAcntNo;
	}
	public CustomerDtl getCstmr() {
		return cstmr;
	}
	public void setCstmr(CustomerDtl cstmr) {
		this.cstmr = cstmr;
	}
	public LedgerType getLedgerType() {
		return ledgerType;
	}
	public void setLedgerType(LedgerType ledgerType) {
		this.ledgerType = ledgerType;
	}
	public String getAcntClosingDt() {
		return acntClosingDt;
	}
	public void setAcntClosingDt(String acntClosingDt) {
		this.acntClosingDt = acntClosingDt;
	}
	public Double getAcntClosingDueBalance() {
		return acntClosingDueBalance;
	}
	public void setAcntClosingDueBalance(Double acntClosingDueBalance) {
		this.acntClosingDueBalance = acntClosingDueBalance;
	}
	public Double getAcntClosingDueEmptyCs() {
		return acntClosingDueEmptyCs;
	}
	public void setAcntClosingDueEmptyCs(Double acntClosingDueEmptyCs) {
		this.acntClosingDueEmptyCs = acntClosingDueEmptyCs;
	}
	public String getAcntOpenBy() {
		return acntOpenBy;
	}
	public void setAcntOpenBy(String acntOpenBy) {
		this.acntOpenBy = acntOpenBy;
	}
	public String getAcntOpeningDt() {
		return acntOpeningDt;
	}
	public void setAcntOpeningDt(String acntOpeningDt) {
		this.acntOpeningDt = acntOpeningDt;
	}
	public String getAcntClosedBy() {
		return acntClosedBy;
	}
	public void setAcntClosedBy(String acntClosedBy) {
		this.acntClosedBy = acntClosedBy;
	}
	@Override
	public String toString() {
		return cstmrAcntNo ;
	}
	
}