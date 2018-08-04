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
 * The persistent class for the purchase_prdct_dtl database table.
 * 
 */
@Entity(name="PurchaseNonBeveragePrdctDtl")
@Table(name="purchase_non_beverage_prdct_dtl",schema="cdms")
public class PurchaseNonBeveragePrdctDtl implements Serializable {
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
	@JoinColumn(name="non_bev_product_cd")
	private NonBeverageProductCatergory novBevProduct;
	
	@Column(name="purchase_prdct_qty")
	private long purchasePrdctQty;

	@ManyToOne
	@JoinColumn(name="purchase_seq_no")
	private PurchaseDtl purchaseMasterdtl;
	
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
	
	@Column(name="unit_price")
	private double unitPrice;
	
	@Column(name="purchase_prdct_net_amount")
	private double prdctNetAmt;
	

	public PurchaseNonBeveragePrdctDtl() {
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

	public NonBeverageProductCatergory getProduct() {
		return novBevProduct;
	}

	public void setProduct(NonBeverageProductCatergory novBevProduct) {
		this.novBevProduct = novBevProduct;
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

	public double getPrdctNetAmt() {
		return prdctNetAmt;
	}

	public void setPrdctNetAmt(double prdctNetAmt) {
		this.prdctNetAmt=prdctNetAmt;
	}

	public NonBeverageProductCatergory getNovBevProduct() {
		return novBevProduct;
	}

	public void setNovBevProduct(NonBeverageProductCatergory novBevProduct) {
		this.novBevProduct = novBevProduct;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
}