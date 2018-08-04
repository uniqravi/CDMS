package application.cdms.db.entities;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the cstmr_ledger_category database table.
 * 
 */
@Entity
@Table(name="cstmr_ledger_category",schema="cdms")
//@NamedQuery(name="CstmrLedgerCategory.findAll", query="SELECT c FROM CstmrLedgerCategory c")
public class CstmrLedgerCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cstmr_ledger_type_cd", unique=true, nullable=false, length=10)
	private String cstmrLedgerTypeCd;
	
	@Column(name="cstmr_ledger_type_name", nullable=false, length=20)
	private String cstmrLedgerTypeName;
	
	@Column(name="ledger_curr_special_discount", nullable=false, precision=10, scale=2)
	private double ledgerCurrSpecialDiscount;

	@Column(name="cstmr_ledger_created_by", nullable=false, length=50)
	private String cstmrLedgerCreatedBy;

	@Column(name="cstmr_ledger_created_dt", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar cstmrLedgerCreatedDt;

	@Column(name="cstmr_ledger_updt_by", length=50)
	private String cstmrLedgerUpdtBy;

	@Column(name="cstmr_ledger_updt_dt")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar cstmrLedgerUpdtDt;

	public CstmrLedgerCategory() {
	}

	public String getCstmrLedgerTypeCd() {
		return cstmrLedgerTypeCd;
	}

	public void setCstmrLedgerTypeCd(String cstmrLedgerTypeCd) {
		this.cstmrLedgerTypeCd = cstmrLedgerTypeCd;
	}

	public String getCstmrLedgerTypeName() {
		return cstmrLedgerTypeName;
	}

	public void setCstmrLedgerTypeName(String cstmrLedgerTypeName) {
		this.cstmrLedgerTypeName = cstmrLedgerTypeName;
	}

	public double getLedgerCurrSpecialDiscount() {
		return ledgerCurrSpecialDiscount;
	}

	public void setLedgerCurrSpecialDiscount(double ledgerCurrSpecialDiscount) {
		this.ledgerCurrSpecialDiscount = ledgerCurrSpecialDiscount;
	}

	public String getCstmrLedgerCreatedBy() {
		return cstmrLedgerCreatedBy;
	}

	public void setCstmrLedgerCreatedBy(String cstmrLedgerCreatedBy) {
		this.cstmrLedgerCreatedBy = cstmrLedgerCreatedBy;
	}

	public Calendar getCstmrLedgerCreatedDt() {
		return cstmrLedgerCreatedDt;
	}

	public void setCstmrLedgerCreatedDt(Calendar cstmrLedgerCreatedDt) {
		this.cstmrLedgerCreatedDt = cstmrLedgerCreatedDt;
	}

	public String getCstmrLedgerUpdtBy() {
		return cstmrLedgerUpdtBy;
	}

	public void setCstmrLedgerUpdtBy(String cstmrLedgerUpdtBy) {
		this.cstmrLedgerUpdtBy = cstmrLedgerUpdtBy;
	}

	public Calendar getCstmrLedgerUpdtDt() {
		return cstmrLedgerUpdtDt;
	}

	public void setCstmrLedgerUpdtDt(Calendar cstmrLedgerUpdtDt) {
		this.cstmrLedgerUpdtDt = cstmrLedgerUpdtDt;
	}
	
}