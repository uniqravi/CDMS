package application.cdms.db.entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


/**
 * The persistent class for the cstmr_dtl database table.
 * 
 */
@Entity
@Table(name="cstmr_dtl",schema="cdms")
//@NamedQuery(name="CstmrDtl.findAll", query="SELECT c FROM CstmrDtl c")
public class CstmrDtl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(
			name="stringSeq",
			strategy="application.cdms.hibernate.utility.StringSequenceIdentifier",
			parameters={
					@Parameter(name="seqColumnNm",value="CUSTOMER_SEQ")
			}
	)
	@GeneratedValue(generator="stringSeq")
	@Column(name="cstmr_id")
	private String cstmrId;

	@Column(name="cstmr_fullname")
	private String cstmrFullname;
	
	@Column(name="cstmr_fathername")
	private String cstmrFathername;
	
	@Column(name="cstmr_email")
	private String cstmrEmail;

	@Column(name="cstmr_mob_no")
	private Long cstmrMobNo;
	
	@Column(name="cstmr_created_by")
	private String cstmrCreatedBy;

	@Column(name="cstmr_created_dt", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar cstmrCreatedDt;

	@OneToOne
	@JoinColumn(name="cstmr_territory")
	private TerritoryAreaDtl cstmrTerritory;

	@Column(name="cstmr_updt_by", length=50)
	private String cstmrUpdtBy;

	@Column(name="cstmr_updt_dt")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar cstmrUpdtDt;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="cstmr")
	private Set<CstmrAcntsDtl> cstmrAcnts;

	public CstmrDtl() {
	}

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

	public String getCstmrEmail() {
		return cstmrEmail;
	}

	public void setCstmrEmail(String cstmrEmail) {
		this.cstmrEmail = cstmrEmail;
	}

	public Long getCstmrMobNo() {
		return cstmrMobNo;
	}

	public void setCstmrMobNo(Long cstmrMobNo) {
		this.cstmrMobNo = cstmrMobNo;
	}

	public String getCstmrCreatedBy() {
		return cstmrCreatedBy;
	}

	public void setCstmrCreatedBy(String cstmrCreatedBy) {
		this.cstmrCreatedBy = cstmrCreatedBy;
	}

	public Calendar getCstmrCreatedDt() {
		return cstmrCreatedDt;
	}

	public void setCstmrCreatedDt(Calendar cstmrCreatedDt) {
		this.cstmrCreatedDt = cstmrCreatedDt;
	}

	public TerritoryAreaDtl getCstmrTerritory() {
		return cstmrTerritory;
	}

	public void setCstmrTerritory(TerritoryAreaDtl cstmrTerritory) {
		this.cstmrTerritory = cstmrTerritory;
	}

	public String getCstmrUpdtBy() {
		return cstmrUpdtBy;
	}

	public void setCstmrUpdtBy(String cstmrUpdtBy) {
		this.cstmrUpdtBy = cstmrUpdtBy;
	}

	public Calendar getCstmrUpdtDt() {
		return cstmrUpdtDt;
	}

	public void setCstmrUpdtDt(Calendar cstmrUpdtDt) {
		this.cstmrUpdtDt = cstmrUpdtDt;
	}

	public Set<CstmrAcntsDtl> getCstmrAcnts() {
		return cstmrAcnts;
	}

	public void setCstmrAcnts(Set<CstmrAcntsDtl> cstmrAcnts) {
		this.cstmrAcnts = cstmrAcnts;
	}
}