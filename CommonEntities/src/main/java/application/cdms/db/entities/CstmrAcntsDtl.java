package application.cdms.db.entities;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


/**
 * The persistent class for the cstmr_acnts_dtl database table.
 * 
 */
@Entity
@Table(name="cstmr_acnts_dtl",schema="cdms")
//@NamedQuery(name="CstmrAcntsDtl.findAll", query="SELECT c FROM CstmrAcntsDtl c")
public class CstmrAcntsDtl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(
			name="stringSeq",
			strategy="application.cdms.hibernate.utility.StringSequenceIdentifier",
			parameters={
					@Parameter(name="seqColumnNm",value="LEDGER_SEQ")
			}
	)
	@GeneratedValue(generator="stringSeq")
	@Column(name="cstmr_acnt_no")
	private String cstmrAcntNo;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cstmr_id")
	private CstmrDtl cstmr;

	@OneToOne
	@JoinColumn(name="cstmr_ledger_type_cd")
	private CstmrLedgerCategory cstmrLedgerTypeCd;

	@Column(name="acnt_closed_by", length=50)
	private String acntClosedBy;

	@Column(name="acnt_closing_comments", length=200)
	private String acntClosingComments;

	@Column(name="acnt_closing_dt")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar acntClosingDt;

	@Column(name="acnt_closing_due_balance", precision=10, scale=2)
	private Double acntClosingDueBalance;

	@Column(name="acnt_closing_due_empty_cs", precision=10, scale=2)
	private Double acntClosingDueEmptyCs;

	@Column(name="acnt_open_by", nullable=false, length=50)
	private String acntOpenBy;

	@Column(name="acnt_opening_dt", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar acntOpeningDt;

	public CstmrAcntsDtl() {
	}
	
	public CstmrDtl getCstmr() {
		return cstmr;
	}

	public void setCstmr(CstmrDtl cstmr) {
		this.cstmr = cstmr;
	}

	public String getCstmrAcntNo() {
		return cstmrAcntNo;
	}

	public void setCstmrAcntNo(String cstmrAcntNo) {
		this.cstmrAcntNo = cstmrAcntNo;
	}
	
	public CstmrLedgerCategory getCstmrLedgerTypeCd() {
		return cstmrLedgerTypeCd;
	}

	public void setCstmrLedgerTypeCd(CstmrLedgerCategory cstmrLedgerTypeCd) {
		this.cstmrLedgerTypeCd = cstmrLedgerTypeCd;
	}

	public String getAcntClosedBy() {
		return acntClosedBy;
	}

	public void setAcntClosedBy(String acntClosedBy) {
		this.acntClosedBy = acntClosedBy;
	}

	public String getAcntClosingComments() {
		return acntClosingComments;
	}

	public void setAcntClosingComments(String acntClosingComments) {
		this.acntClosingComments = acntClosingComments;
	}

	public Calendar getAcntClosingDt() {
		return acntClosingDt;
	}

	public void setAcntClosingDt(Calendar acntClosingDt) {
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

	public Calendar getAcntOpeningDt() {
		return acntOpeningDt;
	}

	public void setAcntOpeningDt(Calendar acntOpeningDt) {
		this.acntOpeningDt = acntOpeningDt;
	}

}