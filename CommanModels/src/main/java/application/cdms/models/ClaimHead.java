package application.cdms.models;

import java.util.Calendar;


public class ClaimHead {
	//private static final long serialVersionUID = 1L;

	private String claimId;

	private Double approvedAmount;

	private double claimAmount;

	private Calendar claimApproveDt;

	private String claimStatus;

	private String claimType;

	private String initiatedBy;

	private Calendar initiationDate;

	private Long linkedClaimDetailId;

	private Long linkedCompanyClaimId;

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

	public Long getLinkedClaimDetailId() {
		return this.linkedClaimDetailId;
	}

	public void setLinkedClaimDetailId(Long linkedClaimDetailId) {
		this.linkedClaimDetailId = linkedClaimDetailId;
	}

	public Long getLinkedCompanyClaimId() {
		return this.linkedCompanyClaimId;
	}

	public void setLinkedCompanyClaimId(Long linkedCompanyClaimId) {
		this.linkedCompanyClaimId = linkedCompanyClaimId;
	}
}