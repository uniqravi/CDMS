package application.cdms.models;

import java.io.Serializable;
import java.util.List;

public class Sale implements Serializable {

	private static final long serialVersionUID = 1L;

	private String saleInvoiceNo;
	
	private String invoiceType;
	
	private CustomerDtl customer;

	private CstmrLedger cstmrAcnt;

	private String cstmrName;

	private String address;

	private String saleDt;

	private String deliveryMode;

	private long totalDeliverBsGlass;

	private long totalReturnBsGlass;

	private Long totalPrevBsGlass;

	private Long totalNetBsGlass;

	private long totalDeliverCell;

	private long totalReturnCell;

	private Long totalPrevCell;

	private Long totalNetCell;

	private double sysGnrtdTotalAmount;
	
	private double totalSchemeDiscnt;

	private double sysGnrtdTotalDiscount;

	private double totalAdjustmentDiscount;

	private double totalAmountAdjustment;

	private double totalNetActualAmount;

	private Double totalPrevDueAmount;

	private double paidAmount;

	private Double totalNetDueAmount;

	private String paymentMode;

	// private SupplyRecord supplyRecord;

	private String isBreakageReturn;

	private String isSchemeAlloted;

	private long currStockProcessedId;

	private String processedStatus;

	private String processedTime;

	private String saleComments;

	private String saleEntrySystemDt;

	private String soldBy;

	private List<SalePrdctInvoice> invoicedPrdctDtlsSet;

	//private Set<SalePrdct> salePrdctSet;

	private List<SaleScheme> salePrdctSchemeDtlSet;
	
	private List<SaleBrekageDtl> saleBrekageLst;
	
	//temp variable
	private String gstn;
	
	private String cstmrGstn;
	
	public CustomerDtl getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDtl customer) {
		this.customer = customer;
	}

	public String getGstn() {
		return gstn;
	}

	public void setGstn(String gstn) {
		this.gstn = gstn;
	}

	public Sale() {
	}

	public String getSaleInvoiceNo() {
		return saleInvoiceNo;
	}

	public void setSaleInvoiceNo(String saleInvoiceNo) {
		this.saleInvoiceNo = saleInvoiceNo;
	}

	public CstmrLedger getCstmrAcnt() {
		return cstmrAcnt;
	}

	public void setCstmrAcnt(CstmrLedger cstmrAcnt) {
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

	public String getSaleDt() {
		return saleDt;
	}

	public void setSaleDt(String saleDt) {
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

	public String getProcessedTime() {
		return processedTime;
	}

	public void setProcessedTime(String processedTime) {
		this.processedTime = processedTime;
	}

	public String getSaleComments() {
		return saleComments;
	}

	public void setSaleComments(String saleComments) {
		this.saleComments = saleComments;
	}

	public String getSaleEntrySystemDt() {
		return saleEntrySystemDt;
	}

	public void setSaleEntrySystemDt(String saleEntrySystemDt) {
		this.saleEntrySystemDt = saleEntrySystemDt;
	}

	public String getSoldBy() {
		return soldBy;
	}

	public void setSoldBy(String soldBy) {
		this.soldBy = soldBy;
	}

	public List<SalePrdctInvoice> getInvoicedPrdctDtlsSet() {
		return invoicedPrdctDtlsSet;
	}

	public void setInvoicedPrdctDtlsSet(List<SalePrdctInvoice> invoicedPrdctDtlsSet) {
		this.invoicedPrdctDtlsSet = invoicedPrdctDtlsSet;
	}
/*
	public Set<SalePrdct> getSalePrdctSet() {
		return salePrdctSet;
	}

	public void setSalePrdctSet(Set<SalePrdct> salePrdctSet) {
		this.salePrdctSet = salePrdctSet;
	}
*/
	public List<SaleScheme> getSalePrdctSchemeDtlSet() {
		return salePrdctSchemeDtlSet;
	}

	public void setSalePrdctSchemeDtlSet(List<SaleScheme> salePrdctSchemeDtlSet) {
		this.salePrdctSchemeDtlSet = salePrdctSchemeDtlSet;
	}

	public List<SaleBrekageDtl> getSaleBrekageLst() {
		return saleBrekageLst;
	}

	public void setSaleBrekageLst(List<SaleBrekageDtl> saleBrekageLst) {
		this.saleBrekageLst = saleBrekageLst;
	}

	public double getTotalSchemeDiscnt() {
		return totalSchemeDiscnt;
	}

	public void setTotalSchemeDiscnt(double totalSchemeDiscnt) {
		this.totalSchemeDiscnt = totalSchemeDiscnt;
	}
	public String getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	public String getCstmrGstn() {
		return cstmrGstn;
	}

	public void setCstmrGstn(String cstmrGstn) {
		this.cstmrGstn = cstmrGstn;
	}

}
