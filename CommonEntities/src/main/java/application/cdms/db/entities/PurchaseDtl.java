package application.cdms.db.entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


/**
 * The persistent class for the purchase_dtl database table.
 * 
 */
@Entity(name="PurchaseDtl")
@Table(name="purchase_dtl",schema="cdms")
//@NamedQuery(name="PurchaseDtl.findAll", query="SELECT p FROM PurchaseDtl p")
public class PurchaseDtl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(
			name="stringSeq",
			strategy="application.cdms.hibernate.utility.StringSequenceIdentifier",
			parameters={
					@Parameter(name="seqColumnNm",value="PURCHASE_SEQ")
			}
	)
	@GeneratedValue(generator="stringSeq")
	@Column(name="purchase_seq_no")
	private String purchaseSeqNo;

	@Column(name="challan_dt")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar challanDt;

	@Column(name="challan_invoice_no")
	private String challanInvoiceNo;

	@Column(name="challan_no")
	private String challanNo;

	@Column(name="paid_amount")
	private double paidAmount;

	@Column(name="payment_id")
	private String paymentId;

	@Column(name="payment_method")
	private String paymentMethod;

	@Column(name="purchase_comment")
	private String purchaseComment;

	@Column(name="total_challan_amount")
	private double totalChallanAmount;

	@Column(name="total_discount_amount")
	private double totalDiscountAmount;

	@Column(name="total_prdct_qty")
	private long totalPrdctQty;

	@Column(name="total_purchased_glass_qty")
	private long totalPurchasedGlassQty;

	@Column(name="total_return_empty_glass_qty")
	private long totalReturnEmptyGlassQty;
	
	@Column(name="total_purchase_bottle_qty")
	private long buyingBottleQty;
	
	@Column(name="total_purchase_cell_qty")
	private long buyingCellQty;
	
	@Column(name="total_return_bottle_qty")
	private long returningBottleQty;
	
	@Column(name="total_return_cell_qty")
	private long returningCellQty;
	
	@Column(name="payment_date")
	@Temporal(TemporalType.DATE)
	private Calendar payment_dt;
	
	@Column(name="firm_name")
	private String firmName;
	
	@Column(name="firm_gstn_number")
	private String firmGstnNumber;
	
	@OneToMany(mappedBy="purchaseMasterdtl",cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	private List<PurchasePrdctDtl> puchasedPrdctList;
	
	@OneToMany(mappedBy="purchaseMasterdtl",cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	private List<PurchaseNonBeveragePrdctDtl> nonBevPrdctList;
	
	@Column(name="return_invoice_no")
	private String returnInvoiceNo;
	
	@Column(name="return_empty_invoice_no")
	private String rtnEmptyInvoiceNo;

	public PurchaseDtl() {
	}

	public String getPurchaseSeqNo() {
		return purchaseSeqNo;
	}

	public void setPurchaseSeqNo(String purchaseSeqNo) {
		this.purchaseSeqNo = purchaseSeqNo;
	}

	public Calendar getChallanDt() {
		return challanDt;
	}

	public void setChallanDt(Calendar challanDt) {
		this.challanDt = challanDt;
	}

	public String getChallanInvoiceNo() {
		return challanInvoiceNo;
	}

	public void setChallanInvoiceNo(String challanInvoiceNo) {
		this.challanInvoiceNo = challanInvoiceNo;
	}

	public String getChallanNo() {
		return challanNo;
	}

	public void setChallanNo(String challanNo) {
		this.challanNo = challanNo;
	}

	public double getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getPurchaseComment() {
		return purchaseComment;
	}

	public void setPurchaseComment(String purchaseComment) {
		this.purchaseComment = purchaseComment;
	}

	public double getTotalChallanAmount() {
		return totalChallanAmount;
	}

	public void setTotalChallanAmount(double totalChallanAmount) {
		this.totalChallanAmount = totalChallanAmount;
	}

	public double getTotalDiscountAmount() {
		return totalDiscountAmount;
	}

	public void setTotalDiscountAmount(double totalDiscountAmount) {
		this.totalDiscountAmount = totalDiscountAmount;
	}

	public long getTotalPrdctQty() {
		return totalPrdctQty;
	}

	public void setTotalPrdctQty(long totalPrdctQty) {
		this.totalPrdctQty = totalPrdctQty;
	}

	public long getTotalPurchasedGlassQty() {
		return totalPurchasedGlassQty;
	}

	public void setTotalPurchasedGlassQty(long totalPurchasedGlassQty) {
		this.totalPurchasedGlassQty = totalPurchasedGlassQty;
	}

	public long getTotalReturnEmptyGlassQty() {
		return totalReturnEmptyGlassQty;
	}

	public void setTotalReturnEmptyGlassQty(long totalReturnEmptyGlassQty) {
		this.totalReturnEmptyGlassQty = totalReturnEmptyGlassQty;
	}
	
	public Calendar getPayment_dt() {
		return payment_dt;
	}

	public void setPayment_dt(Calendar payment_dt) {
		this.payment_dt = payment_dt;
	}

	public List<PurchasePrdctDtl> getPuchasedPrdctList() {
		return puchasedPrdctList;
	}

	public void setPuchasedPrdctList(List<PurchasePrdctDtl> puchasedPrdctList) {
		this.puchasedPrdctList = puchasedPrdctList;
	}
	
	public String getFirmName() {
		return firmName;
	}

	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}

	public String getFirmGstnNumber() {
		return firmGstnNumber;
	}

	public void setFirmGstnNumber(String firmGstnNumber) {
		this.firmGstnNumber = firmGstnNumber;
	}

	public String getReturnInvoiceNo() {
		return returnInvoiceNo;
	}

	public void setReturnInvoiceNo(String returnInvoiceNo) {
		this.returnInvoiceNo = returnInvoiceNo;
	}

	public List<PurchaseNonBeveragePrdctDtl> getNonBevPrdctList() {
		return nonBevPrdctList;
	}

	public void setNonBevPrdctList(List<PurchaseNonBeveragePrdctDtl> nonBevPrdctList) {
		this.nonBevPrdctList = nonBevPrdctList;
	}

	public long getBuyingBottleQty() {
		return buyingBottleQty;
	}

	public void setBuyingBottleQty(long buyingBottleQty) {
		this.buyingBottleQty = buyingBottleQty;
	}

	public long getBuyingCellQty() {
		return buyingCellQty;
	}

	public void setBuyingCellQty(long buyingCellQty) {
		this.buyingCellQty = buyingCellQty;
	}

	public long getReturningBottleQty() {
		return returningBottleQty;
	}

	public void setReturningBottleQty(long returningBottleQty) {
		this.returningBottleQty = returningBottleQty;
	}

	public long getReturningCellQty() {
		return returningCellQty;
	}

	public void setReturningCellQty(long returningCellQty) {
		this.returningCellQty = returningCellQty;
	}

	public String getRtnEmptyInvoiceNo() {
		return rtnEmptyInvoiceNo;
	}

	public void setRtnEmptyInvoiceNo(String rtnEmptyInvoiceNo) {
		this.rtnEmptyInvoiceNo = rtnEmptyInvoiceNo;
	}
}