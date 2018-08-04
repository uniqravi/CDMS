package application.cdms.db.entities;

import java.io.Serializable;
import java.util.Set;

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

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


/**
 * The persistent class for the sale_invoices_prdct_dtl database table.
 * 
 */
@Entity
@Table(name="sale_invoices_prdct_dtl",schema="cdms")
//@NamedQuery(name="SaleInvoicesPrdctDtl.findAll", query="SELECT s FROM SaleInvoicesPrdctDtl s")
public class SaleInvoicesPrdctDtl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(
			name="stringSeq",
			strategy="application.cdms.hibernate.utility.StringSequenceIdentifier",
			parameters={
					@Parameter(name="seqColumnNm",value="INVOICED_PRDCT_SEQ")
			}
	)
	@GeneratedValue(generator="stringSeq")
	@Column(name="seq_number")
	private String seqNumber;
	
	@ManyToOne
	@JoinColumn(name="sale_invoice_no")
	private SaleDtl saledtl;
	
	@Column(name="prdct_group_description", nullable=false, length=100)
	private String prdctGroupDescription;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="group_name")
	private BeverageProductGroupDtl beverageGroup;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="saleInvoicedtl",cascade=CascadeType.ALL)
	private Set<SalePrdctDtl> salePrdctSet;
	
	@Column(name="selling_qty", nullable=false, precision=10)
	private long sellingQty;

	@Column(name="base_rate_per_cs")
	private double baseRatePerCs;

	@Column(name="net_base_amt", nullable=false, precision=10, scale=2)
	private double netBaseAmt;

	@Column(name="scheme_discnt_per_cs")
	private double schemeDiscntPerCs;
	
	@Column(name="scheme_disocunt_amt", nullable=false, precision=10, scale=2)
	private double schemeDisocuntAmt;
	
	@Column(name="taxable_amt", nullable=false, precision=10, scale=2)
	private double taxableAmt;
	
	@Column(name="sgst_rate", nullable=false, precision=5, scale=2)
	private double sgstRate;
	
	@Column(name="sgst_amt", nullable=false, precision=10, scale=2)
	private double sgstAmt;

	@Column(name="cgst_rate", nullable=false, precision=5, scale=2)
	private double cgstRate;

	@Column(name="cgst_amt", nullable=false, precision=10, scale=2)
	private double cgstAmt;

	@Column(name="cess_rate", nullable=false, precision=5, scale=2)
	private double cessRate;

	@Column(name="cess_amt", nullable=false, precision=10, scale=2)
	private double cessAmt;
	
	@Column(name="igst_rate", nullable=false, precision=5, scale=2)
	private double igstRate;

	@Column(name="igst_amt", nullable=false, precision=10, scale=2)
	private double igstAmt;

	@Column(name="sys_gross_net_per_cs", precision=10, scale=2)
	private double sysGrossNetPerCs;

	@Column(name="sys_gnrtd_net_amt", precision=10, scale=2)
	private double sysGnrtdNetAmt;
	
	@Column(name="sys_gnrtd_special_discount_per_cs")
	private double sysSpecialDiscountPerCs;
	
	@Column(name="total_sys_gnrtd_discount")
	private double totalSysGnrtdDiscount;

	@Column(name="total_discnt_adjustment")
	private double totalDiscountAdjustment;
	
	@Column(name="total_amnt_adjustment")
	private double totalAmtAdjustment;
	
	@Column(name="total_prdct_net_amt")
	private double totalPrdctNetAmt;

	public SaleInvoicesPrdctDtl() {
	}

	public String getSeqNumber() {
		return seqNumber;
	}

	public void setSeqNumber(String seqNumber) {
		this.seqNumber = seqNumber;
	}

	public SaleDtl getSaledtl() {
		return saledtl;
	}

	public void setSaledtl(SaleDtl saledtl) {
		this.saledtl = saledtl;
	}

	public String getPrdctGroupDescription() {
		return prdctGroupDescription;
	}

	public void setPrdctGroupDescription(String prdctGroupDescription) {
		this.prdctGroupDescription = prdctGroupDescription;
	}

	public BeverageProductGroupDtl getBeverageGroup() {
		return beverageGroup;
	}

	public void setBeverageGroup(BeverageProductGroupDtl beverageGroup) {
		this.beverageGroup = beverageGroup;
	}

	public Set<SalePrdctDtl> getSalePrdctSet() {
		return salePrdctSet;
	}

	public void setSalePrdctSet(Set<SalePrdctDtl> salePrdctSet) {
		this.salePrdctSet = salePrdctSet;
	}

	public long getSellingQty() {
		return sellingQty;
	}

	public void setSellingQty(long sellingQty) {
		this.sellingQty = sellingQty;
	}

	public double getBaseRatePerCs() {
		return baseRatePerCs;
	}

	public void setBaseRatePerCs(double baseRatePerCs) {
		this.baseRatePerCs = baseRatePerCs;
	}

	public double getNetBaseAmt() {
		return netBaseAmt;
	}

	public void setNetBaseAmt(double netBaseAmt) {
		this.netBaseAmt = netBaseAmt;
	}
	
	public double getSchemeDiscntPerCs() {
		return schemeDiscntPerCs;
	}

	public void setSchemeDiscntPerCs(double schemeDiscntPerCs) {
		this.schemeDiscntPerCs = schemeDiscntPerCs;
	}

	public double getSchemeDisocuntAmt() {
		return schemeDisocuntAmt;
	}

	public void setSchemeDisocuntAmt(double schemeDisocuntAmt) {
		this.schemeDisocuntAmt = schemeDisocuntAmt;
	}

	public double getTaxableAmt() {
		return taxableAmt;
	}

	public void setTaxableAmt(double taxableAmt) {
		this.taxableAmt = taxableAmt;
	}

	public double getSgstRate() {
		return sgstRate;
	}

	public void setSgstRate(double sgstRate) {
		this.sgstRate = sgstRate;
	}

	public double getSgstAmt() {
		return sgstAmt;
	}

	public void setSgstAmt(double sgstAmt) {
		this.sgstAmt = sgstAmt;
	}

	public double getCgstRate() {
		return cgstRate;
	}

	public void setCgstRate(double cgstRate) {
		this.cgstRate = cgstRate;
	}

	public double getCgstAmt() {
		return cgstAmt;
	}

	public void setCgstAmt(double cgstAmt) {
		this.cgstAmt = cgstAmt;
	}

	public double getCessRate() {
		return cessRate;
	}

	public void setCessRate(double cessRate) {
		this.cessRate = cessRate;
	}

	public double getCessAmt() {
		return cessAmt;
	}

	public void setCessAmt(double cessAmt) {
		this.cessAmt = cessAmt;
	}
	
	public double getIgstRate() {
		return igstRate;
	}

	public void setIgstRate(double igstRate) {
		this.igstRate = igstRate;
	}

	public double getIgstAmt() {
		return igstAmt;
	}

	public void setIgstAmt(double igstAmt) {
		this.igstAmt = igstAmt;
	}

	public double getSysGrossNetPerCs() {
		return sysGrossNetPerCs;
	}

	public void setSysGrossNetPerCs(double sysGrossNetPerCs) {
		this.sysGrossNetPerCs = sysGrossNetPerCs;
	}

	public double getSysGnrtdNetAmt() {
		return sysGnrtdNetAmt;
	}

	public void setSysGnrtdNetAmt(double sysGnrtdNetAmt) {
		this.sysGnrtdNetAmt = sysGnrtdNetAmt;
	}

	public double getSysSpecialDiscountPerCs() {
		return sysSpecialDiscountPerCs;
	}

	public void setSysSpecialDiscountPerCs(double sysSpecialDiscountPerCs) {
		this.sysSpecialDiscountPerCs = sysSpecialDiscountPerCs;
	}

	public double getTotalSysGnrtdDiscount() {
		return totalSysGnrtdDiscount;
	}

	public void setTotalSysGnrtdDiscount(double totalSysGnrtdDiscount) {
		this.totalSysGnrtdDiscount = totalSysGnrtdDiscount;
	}

	public double getTotalDiscountAdjustment() {
		return totalDiscountAdjustment;
	}

	public void setTotalDiscountAdjustment(double totalDiscountAdjustment) {
		this.totalDiscountAdjustment = totalDiscountAdjustment;
	}

	public double getTotalAmtAdjustment() {
		return totalAmtAdjustment;
	}

	public void setTotalAmtAdjustment(double totalAmtAdjustment) {
		this.totalAmtAdjustment = totalAmtAdjustment;
	}

	public double getTotalPrdctNetAmt() {
		return totalPrdctNetAmt;
	}

	public void setTotalPrdctNetAmt(double totalPrdctNetAmt) {
		this.totalPrdctNetAmt = totalPrdctNetAmt;
	}

}