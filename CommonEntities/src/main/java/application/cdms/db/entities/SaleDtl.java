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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


/**
 * The persistent class for the sale_dtl database table.
 * 
 */
@Entity
@Table(name="sale_dtl",schema="cdms")
//@NamedQuery(name="SaleDtl.findAll", query="SELECT s FROM SaleDtl s")
public class SaleDtl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(
			name="stringSeq",
			strategy="application.cdms.hibernate.utility.StringSequenceIdentifier",
			parameters={
					@Parameter(name="seqColumnNm",value="SALE_INVOICE")
			}
	)
	@GeneratedValue(generator="stringSeq")
	@Column(name="sale_invoice_no", unique=true, nullable=false)
	private String saleInvoiceNo;
	
	@Column(name="invoice_type")
	private String invoiceType;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cstmr_acnt_no")
	private CstmrAcntsDtl cstmrAcnt;
	
	/*
	@Column(name="cstmr_id")
	private String cstmrId;
	*/
	
	@Column(name="cstmr_name")
	private String cstmrName;
	
	@Column(name="address")
	private String address;
	
	@Column(name="sale_dt", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar saleDt;

	@Column(name="delivery_mode", nullable=false)
	private String deliveryMode;

	@Column(name="total_deliver_bs_glass", nullable=false, precision=10)
	private long totalDeliverBsGlass;

	@Column(name="total_return_bs_glass", nullable=false, precision=10)
	private long totalReturnBsGlass;

	@Column(name="total_prev_bs_glass", precision=10)
	private Long totalPrevBsGlass;
	
	@Column(name="total_net_bs_glass", precision=10)
	private Long totalNetBsGlass;

	@Column(name="total_deliver_cell", nullable=false, precision=10)
	private long totalDeliverCell;
	
	@Column(name="total_return_cell", nullable=false, precision=10)
	private long totalReturnCell;
	
	@Column(name="total_prev_cell", precision=10)
	private Long totalPrevCell;
	
	@Column(name="total_net_cell", precision=10)
	private Long totalNetCell;
	
	@Column(name="sys_gnrtd_total_amount", nullable=false, precision=10, scale=2)
	private double sysGnrtdTotalAmount;

	@Column(name="total_scheme_discount",nullable=false)
	private double totalSchemeDiscnt;
	
	@Column(name="sys_gnrtd_total_discount", nullable=false, precision=10, scale=2)
	private double sysGnrtdTotalDiscount;

	@Column(name="total_adjustment_discount", nullable=false, precision=10, scale=2)
	private double totalAdjustmentDiscount;

	@Column(name="total_amount_adjustment", nullable=false, precision=10, scale=2)
	private double totalAmountAdjustment;

	@Column(name="total_net_actual_amount", nullable=false, precision=10, scale=2)
	private double totalNetActualAmount;

	@Column(name="total_prev_due_amount", precision=10, scale=2)
	private Double totalPrevDueAmount;
	
	@Column(name="paid_amount", nullable=false, precision=10, scale=2)
	private double paidAmount;
	
	@Column(name="total_net_due_amount", precision=10, scale=2)
	private Double totalNetDueAmount;
	
	@Column(name="payment_mode", nullable=false, length=20)
	private String paymentMode;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="supply_seq_no")
	private SupplyRecord supplyRecord;
	
	@Column(name="is_breakage_return", length=3)
	private String isBreakageReturn;

	@Column(name="is_scheme_alloted", length=3)
	private String isSchemeAlloted;
	
	@Column(name="curr_stock_processed_id")
	private long currStockProcessedId;

	@Column(name="processed_status", length=10)
	private String processedStatus;

	@Column(name="processed_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar processedTime;

	@Column(name="sale_comments", length=500)
	private String saleComments;

	@Column(name="sale_entry_system_dt", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar saleEntrySystemDt;

	@Column(name="sold_by")
	private String soldBy;
	
	@OneToMany(mappedBy="saledtl",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<SaleInvoicesPrdctDtl> invoicedPrdctDtlsSet;
	
	@OneToMany(mappedBy="saleDtl",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List<SalePrdctSchemeDtl> salePrdctSchemeDtlSet;
	
	@OneToMany(mappedBy="saleDtl",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List<SaleBreakageDtl> saleBrekageLst;

	public SaleDtl() {
	}

	public String getSaleInvoiceNo() {
		return saleInvoiceNo;
	}

	public void setSaleInvoiceNo(String saleInvoiceNo) {
		this.saleInvoiceNo = saleInvoiceNo;
	}

	public String getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	public CstmrAcntsDtl getCstmrAcnt() {
		return cstmrAcnt;
	}

	public void setCstmrAcnt(CstmrAcntsDtl cstmrAcnt) {
		this.cstmrAcnt = cstmrAcnt;
	}

	public String getCstmrName() {
		return cstmrName;
	}

	public void setCstmrName(String cstmrName) {
		this.cstmrName = cstmrName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Calendar getSaleDt() {
		return saleDt;
	}

	public void setSaleDt(Calendar saleDt) {
		this.saleDt = saleDt;
	}

	public String getDeliveryMode() {
		return deliveryMode;
	}

	public void setDeliveryMode(String deliveryMode) {
		this.deliveryMode = deliveryMode;
	}

	public long getTotalDeliverBsGlass() {
		return totalDeliverBsGlass;
	}

	public void setTotalDeliverBsGlass(long totalDeliverBsGlass) {
		this.totalDeliverBsGlass = totalDeliverBsGlass;
	}

	public long getTotalReturnBsGlass() {
		return totalReturnBsGlass;
	}

	public void setTotalReturnBsGlass(long totalReturnBsGlass) {
		this.totalReturnBsGlass = totalReturnBsGlass;
	}

	public Long getTotalPrevBsGlass() {
		return totalPrevBsGlass;
	}

	public void setTotalPrevBsGlass(Long totalPrevBsGlass) {
		this.totalPrevBsGlass = totalPrevBsGlass;
	}

	public Long getTotalNetBsGlass() {
		return totalNetBsGlass;
	}

	public void setTotalNetBsGlass(Long totalNetBsGlass) {
		this.totalNetBsGlass = totalNetBsGlass;
	}

	public long getTotalDeliverCell() {
		return totalDeliverCell;
	}

	public void setTotalDeliverCell(long totalDeliverCell) {
		this.totalDeliverCell = totalDeliverCell;
	}

	public long getTotalReturnCell() {
		return totalReturnCell;
	}

	public void setTotalReturnCell(long totalReturnCell) {
		this.totalReturnCell = totalReturnCell;
	}

	public Long getTotalPrevCell() {
		return totalPrevCell;
	}

	public void setTotalPrevCell(Long totalPrevCell) {
		this.totalPrevCell = totalPrevCell;
	}

	public Long getTotalNetCell() {
		return totalNetCell;
	}

	public void setTotalNetCell(Long totalNetCell) {
		this.totalNetCell = totalNetCell;
	}

	public double getSysGnrtdTotalAmount() {
		return sysGnrtdTotalAmount;
	}

	public void setSysGnrtdTotalAmount(double sysGnrtdTotalAmount) {
		this.sysGnrtdTotalAmount = sysGnrtdTotalAmount;
	}

	public double getSysGnrtdTotalDiscount() {
		return sysGnrtdTotalDiscount;
	}

	public void setSysGnrtdTotalDiscount(double sysGnrtdTotalDiscount) {
		this.sysGnrtdTotalDiscount = sysGnrtdTotalDiscount;
	}

	public double getTotalAdjustmentDiscount() {
		return totalAdjustmentDiscount;
	}

	public void setTotalAdjustmentDiscount(double totalAdjustmentDiscount) {
		this.totalAdjustmentDiscount = totalAdjustmentDiscount;
	}

	public double getTotalAmountAdjustment() {
		return totalAmountAdjustment;
	}

	public void setTotalAmountAdjustment(double totalAmountAdjustment) {
		this.totalAmountAdjustment = totalAmountAdjustment;
	}

	public double getTotalNetActualAmount() {
		return totalNetActualAmount;
	}

	public void setTotalNetActualAmount(double totalNetActualAmount) {
		this.totalNetActualAmount = totalNetActualAmount;
	}

	public Double getTotalPrevDueAmount() {
		return totalPrevDueAmount;
	}

	public void setTotalPrevDueAmount(Double totalPrevDueAmount) {
		this.totalPrevDueAmount = totalPrevDueAmount;
	}

	public double getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}

	public Double getTotalNetDueAmount() {
		return totalNetDueAmount;
	}

	public void setTotalNetDueAmount(Double totalNetDueAmount) {
		this.totalNetDueAmount = totalNetDueAmount;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public SupplyRecord getSupplyRecord() {
		return supplyRecord;
	}

	public void setSupplyRecord(SupplyRecord supplyRecord) {
		this.supplyRecord = supplyRecord;
	}

	public String getIsBreakageReturn() {
		return isBreakageReturn;
	}

	public void setIsBreakageReturn(String isBreakageReturn) {
		this.isBreakageReturn = isBreakageReturn;
	}

	public String getIsSchemeAlloted() {
		return isSchemeAlloted;
	}

	public void setIsSchemeAlloted(String isSchemeAlloted) {
		this.isSchemeAlloted = isSchemeAlloted;
	}

	public long getCurrStockProcessedId() {
		return currStockProcessedId;
	}

	public void setCurrStockProcessedId(long currStockProcessedId) {
		this.currStockProcessedId = currStockProcessedId;
	}

	public String getProcessedStatus() {
		return processedStatus;
	}

	public void setProcessedStatus(String processedStatus) {
		this.processedStatus = processedStatus;
	}

	public Calendar getProcessedTime() {
		return processedTime;
	}

	public void setProcessedTime(Calendar processedTime) {
		this.processedTime = processedTime;
	}

	public String getSaleComments() {
		return saleComments;
	}

	public void setSaleComments(String saleComments) {
		this.saleComments = saleComments;
	}

	public Calendar getSaleEntrySystemDt() {
		return saleEntrySystemDt;
	}

	public void setSaleEntrySystemDt(Calendar saleEntrySystemDt) {
		this.saleEntrySystemDt = saleEntrySystemDt;
	}

	public String getSoldBy() {
		return soldBy;
	}

	public void setSoldBy(String soldBy) {
		this.soldBy = soldBy;
	}

	public List<SaleInvoicesPrdctDtl> getInvoicedPrdctDtlsSet() {
		return invoicedPrdctDtlsSet;
	}

	public void setInvoicedPrdctDtlsSet(List<SaleInvoicesPrdctDtl> invoicedPrdctDtlsSet) {
		this.invoicedPrdctDtlsSet = invoicedPrdctDtlsSet;
	}

	public List<SalePrdctSchemeDtl> getSalePrdctSchemeDtlSet() {
		return salePrdctSchemeDtlSet;
	}

	public void setSalePrdctSchemeDtlSet(List<SalePrdctSchemeDtl> salePrdctSchemeDtlSet) {
		this.salePrdctSchemeDtlSet = salePrdctSchemeDtlSet;
	}

	public List<SaleBreakageDtl> getSaleBrekageLst() {
		return saleBrekageLst;
	}

	public void setSaleBrekageLst(List<SaleBreakageDtl> saleBrekageLst) {
		this.saleBrekageLst = saleBrekageLst;
	}

	public double getTotalSchemeDiscnt() {
		return totalSchemeDiscnt;
	}

	public void setTotalSchemeDiscnt(double totalSchemeDiscnt) {
		this.totalSchemeDiscnt = totalSchemeDiscnt;
	}
}