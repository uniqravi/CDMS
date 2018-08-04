package application.cdms.models;

import java.util.Set;

public class CustomerDtl {
	private String cstmrId;
	private String cstmrFullname;
	private String cstmrFathername;
	private Long cstmrMobNo;
	private String cstmrEmail;
	private Territory territory;
	private String cstmrCreatedBy;
	private String cstmrCreatedDt;
	private Set<CstmrLedger> ledgers;
	public String getCstmrId() {
		return cstmrId;
	}
	public void setCstmrId(String cstmrId) {
		this.cstmrId = cstmrId;
	}
	public String getCstmrFullname() {
		return cstmrFullname;
	}
	public void setCstmrFullname(String cstmrFullname) {
		this.cstmrFullname = cstmrFullname;
	}
	public String getCstmrFathername() {
		return cstmrFathername;
	}
	public void setCstmrFathername(String cstmrFathername) {
		this.cstmrFathername = cstmrFathername;
	}
	public Long getCstmrMobNo() {
		return cstmrMobNo;
	}
	public void setCstmrMobNo(Long cstmrMobNo) {
		this.cstmrMobNo = cstmrMobNo;
	}
	public String getCstmrEmail() {
		return cstmrEmail;
	}
	public void setCstmrEmail(String cstmrEmail) {
		this.cstmrEmail = cstmrEmail;
	}
	public Territory getTerritory() {
		return territory;
	}
	public void setTerritory(Territory territory) {
		this.territory = territory;
	}
	public String getCstmrCreatedBy() {
		return cstmrCreatedBy;
	}
	public void setCstmrCreatedBy(String cstmrCreatedBy) {
		this.cstmrCreatedBy = cstmrCreatedBy;
	}
	public String getCstmrCreatedDt() {
		return cstmrCreatedDt;
	}
	public void setCstmrCreatedDt(String cstmrCreatedDt) {
		this.cstmrCreatedDt = cstmrCreatedDt;
	}
	public Set<CstmrLedger> getLedgers() {
		return ledgers;
	}
	public void setLedgers(Set<CstmrLedger> ledgers) {
		this.ledgers = ledgers;
	}
	@Override
	public String toString(){
		return cstmrFullname+" "+cstmrFathername+" "+getTerritory().getTerritoryName();
	}
}
