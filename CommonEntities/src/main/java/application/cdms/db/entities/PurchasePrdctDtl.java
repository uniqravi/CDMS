package application.cdms.db.entities;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Where;


/**
 * The persistent class for the purchase_prdct_dtl database table.
 * 
 */
@Entity
@Table(name="purchase_prdct_dtl",schema="cdms")
//@NamedQuery(name="PurchasePrdctDtl.findAll", query="SELECT p FROM PurchasePrdctDtl p")
public class PurchasePrdctDtl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(
			name="stringSeq",
			strategy="application.cdms.hibernate.utility.StringSequenceIdentifier",
			parameters={
					@Parameter(name="seqColumnNm",value="PURCHASE_PROD_SEQ")
			}
	)
	@GeneratedValue(generator="stringSeq")
	@Column(name="purchased_prdct_seq_no")
	private String purchasedPrdctSeqNo;

	@Column(name="prdct_recieved_dt")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar prdctRecievedDt;

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="product_cd")
	private BeverageProductCategory product;
	
	@Column(name="purchase_prdct_qty")
	private long purchasePrdctQty;

	@ManyToOne
	@JoinColumn(name="purchase_seq_no")
	private PurchaseDtl purchaseMasterdtl;
	
	@OneToOne(fetch=FetchType.EAGER,cascade={CascadeType.ALL})
	@JoinColumns({
        @JoinColumn(name="purchase_breakage_seq",nullable=true)
    })
	@Where(clause="breakageSource='BUY_BREAKAGE'")
	private PrdctBreakageDtl prdctBreakageDtl;
	
	@Column(name="per_cs_base_price")
	private double perCsBasePrice;
	
	@Column(name="purchase_prdct_totl_base_amount")
	private double prdctTotalBaseAmt;
	
	@Column(name="purchase_prdct_totl_discount_amnt")
	private double prdctTotalDiscountAmt;
	
	@Column(name="purchase_prdct_totl_taxable_amount")
	private double prdctTotalTaxableAmt;
	
	@Column(name="purchase_prdct_sgst")
	private double prdctTotalSGST;
	
	@Column(name="purchase_prdct_igst")
	private double prdctTotalIGST;
	
	@Column(name="purchase_prdct_cgst")
	private double prdctTotalCGST;
	
	@Column(name="purchase_prdct_cess_tax")
	private double prdctTotalCess;
	
	@Column(name="per_cs_net_price")
	private double perCsNetPrice;
	
	@Column(name="purchase_prdct_net_amount")
	private double prdctNetAmt;
	
	@Column(name="MRP")
	private Double mrp;

	public PurchasePrdctDtl() {
	}

	public String getPurchasedPrdctSeqNo() {
		return purchasedPrdctSeqNo;
	}

	public void setPurchasedPrdctSeqNo(String purchasedPrdctSeqNo) {
		this.purchasedPrdctSeqNo = purchasedPrdctSeqNo;
	}

	public Calendar getPrdctRecievedDt() {
		return prdctRecievedDt;
	}

	public void setPrdctRecievedDt(Calendar prdctRecievedDt) {
		this.prdctRecievedDt = prdctRecievedDt;
	}

	public BeverageProductCategory getProduct() {
		return product;
	}

	public void setProduct(BeverageProductCategory product) {
		this.product = product;
	}

	public long getPurchasePrdctQty() {
		return purchasePrdctQty;
	}

	public void setPurchasePrdctQty(long purchasePrdctQty) {
		this.purchasePrdctQty = purchasePrdctQty;
	}

	public PurchaseDtl getPurchaseMasterdtl() {
		return purchaseMasterdtl;
	}

	public void setPurchaseMasterdtl(PurchaseDtl purchaseMasterdtl) {
		this.purchaseMasterdtl = purchaseMasterdtl;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public PrdctBreakageDtl getPrdctBreakageDtl() {
		return prdctBreakageDtl;
	}

	public void setPrdctBreakageDtl(PrdctBreakageDtl prdctBreakageDtl) {
		this.prdctBreakageDtl = prdctBreakageDtl;
	}

	public double getPerCsBasePrice() {
		return perCsBasePrice;
	}

	public void setPerCsBasePrice(double perCsBasePrice) {
		this.perCsBasePrice = perCsBasePrice;
	}

	public double getPrdctTotalBaseAmt() {
		return prdctTotalBaseAmt;
	}

	public void setPrdctTotalBaseAmt(double prdctTotalBaseAmt) {
		this.prdctTotalBaseAmt = prdctTotalBaseAmt;
	}

	public double getPrdctTotalDiscountAmt() {
		return prdctTotalDiscountAmt;
	}

	public void setPrdctTotalDiscountAmt(double prdctTotalDiscountAmt) {
		this.prdctTotalDiscountAmt = prdctTotalDiscountAmt;
	}

	public double getPrdctTotalTaxableAmt() {
		return prdctTotalTaxableAmt;
	}

	public void setPrdctTotalTaxableAmt(double prdctTotalTaxableAmt) {
		this.prdctTotalTaxableAmt = prdctTotalTaxableAmt;
	}

	public double getPrdctTotalSGST() {
		return prdctTotalSGST;
	}

	public void setPrdctTotalSGST(double prdctTotalSGST) {
		this.prdctTotalSGST = prdctTotalSGST;
	}

	public double getPrdctTotalIGST() {
		return prdctTotalIGST;
	}

	public void setPrdctTotalIGST(double prdctTotalIGST) {
		this.prdctTotalIGST = prdctTotalIGST;
	}

	public double getPrdctTotalCGST() {
		return prdctTotalCGST;
	}

	public void setPrdctTotalCGST(double prdctTotalCGST) {
		this.prdctTotalCGST = prdctTotalCGST;
	}

	public double getPrdctTotalCess() {
		return prdctTotalCess;
	}

	public void setPrdctTotalCess(double prdctTotalCess) {
		this.prdctTotalCess = prdctTotalCess;
	}

	public double getPerCsNetPrice() {
		return perCsNetPrice;
	}

	public void setPerCsNetPrice(double perCsNetPrice) {
		this.perCsNetPrice = perCsNetPrice;
	}

	public double getPrdctNetAmt() {
		return prdctNetAmt;
	}

	public void setPrdctNetAmt(double prdctNetAmt) {
		this.prdctNetAmt = prdctNetAmt;
	}

	public Double getMrp() {
		return mrp;
	}

	public void setMrp(Double mrp) {
		this.mrp = mrp;
	}
}