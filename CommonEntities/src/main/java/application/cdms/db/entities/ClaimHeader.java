package application.cdms.db.entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


/**
 * The persistent class for the claim_header database table.
 * 
 */
@Entity
@Table(name="claim_header",schema="cdms")
//@NamedQuery(name="ClaimHeader.findAll", query="SELECT c FROM ClaimHeader c")
public class ClaimHeader implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(
			name="stringSeq",
			strategy="application.cdms.hibernate.utility.StringSequenceIdentifier",
			parameters={
					@Parameter(name="seqColumnNm",value="CLAIM_SEQ")
			}
	)
	@GeneratedValue(generator="stringSeq")
	@Column(name="claim_id")
	private String claimId;

	@Column(name="approved_amount")
	private Double approvedAmount;

	@Column(name="claim_amount")
	private double claimAmount;

	@Column(name="claim_approve_dt")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar claimApproveDt;

	@Column(name="claim_status")
	private String claimStatus;

	@Column(name="claim_type")
	private String claimType;

	@Column(name="initiated_by")
	private String initiatedBy;

	@Column(name="initiation_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar initiationDate;

	@Column(name="linked_claim_detail_id")
	private String linkedClaimDetailId;

	@Column(name="linked_company_claim_id")
	private Long linkedCompanyClaimId;
	
	@Column(name="give_cell_qty")
	private Long giveCellQty;
	
	@Column(name="given_glass_qty")
	private Long givenGlassQty;
	
	/*@JoinColumns({
    @JoinColumn(name="purchase_breakage_seq",nullable=true)
	})*/
	@OneToMany(mappedBy="header",cascade={CascadeType.ALL})
	private List<ClaimBreakagePrdctDtl> claimBreakPrdctsLst;

	public ClaimHeader() {
	}

	public String getClaimId() {
		return this.claimId;
	}

	public void setClaimId(String claimId) {
		this.claimId = claimId;
	}

	public Double getApprovedAmount() {
		return this.approvedAmount;
	}

	public void setApprovedAmount(Double approvedAmount) {
		this.approvedAmount = approvedAmount;
	}

	public double getClaimAmount() {
		return this.claimAmount;
	}

	public void setClaimAmount(double claimAmount) {
		this.claimAmount = claimAmount;
	}

	public Calendar getClaimApproveDt() {
		return this.claimApproveDt;
	}

	public void setClaimApproveDt(Calendar claimApproveDt) {
		this.claimApproveDt = claimApproveDt;
	}

	public String getClaimStatus() {
		return this.claimStatus;
	}

	public void setClaimStatus(String claimStatus) {
		this.claimStatus = claimStatus;
	}

	public String getClaimType() {
		return this.claimType;
	}

	public void setClaimType(String claimType) {
		this.claimType = claimType;
	}

	public String getInitiatedBy() {
		return this.initiatedBy;
	}

	public void setInitiatedBy(String initiatedBy) {
		this.initiatedBy = initiatedBy;
	}

	public Calendar getInitiationDate() {
		return this.initiationDate;
	}

	public void setInitiationDate(Calendar initiationDate) {
		this.initiationDate = initiationDate;
	}

	public String getLinkedClaimDetailId() {
		return this.linkedClaimDetailId;
	}

	public void setLinkedClaimDetailId(String linkedClaimDetailId) {
		this.linkedClaimDetailId = linkedClaimDetailId;
	}

	public Long getLinkedCompanyClaimId() {
		return this.linkedCompanyClaimId;
	}

	public void setLinkedCompanyClaimId(Long linkedCompanyClaimId) {
		this.linkedCompanyClaimId = linkedCompanyClaimId;
	}

	public List<ClaimBreakagePrdctDtl> getClaimBreakPrdctsLst() {
		return claimBreakPrdctsLst;
	}

	public void setClaimBreakPrdctsLst(List<ClaimBreakagePrdctDtl> claimBreakPrdctsLst) {
		this.claimBreakPrdctsLst = claimBreakPrdctsLst;
	}

	public Long getGiveCellQty() {
		return giveCellQty;
	}

	public void setGiveCellQty(Long giveCellQty) {
		this.giveCellQty = giveCellQty;
	}

	public Long getGivenGlassQty() {
		return givenGlassQty;
	}

	public void setGivenGlassQty(Long givenGlassQty) {
		this.givenGlassQty = givenGlassQty;
	}
}