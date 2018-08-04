package application.cdms.db.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Where;


/**
 * The persistent class for the claim_breakage_prdct_dtl database table.
 * 
 */
@Entity
@Table(name="claim_breakage_prdct_dtl",schema="cdms")
//@NamedQuery(name="ClaimBreakagePrdctDtl.findAll", query="SELECT c FROM ClaimBreakagePrdctDtl c")
public class ClaimBreakagePrdctDtl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@TableGenerator(name="breakClaimGnrtr",table="CDMS.SERIAL_NO_GENERATOR",pkColumnName="SEQ_NAME",valueColumnName="SEQ_VALUE",pkColumnValue="BREKAGE_CLAIM_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.TABLE,generator="breakClaimGnrtr")
	@Column(name="seq_num")
	private long seqNum;

	@Column(name="basic_amt")
	private double basicAmt;

	@Column(name="cell_qty_without_bs")
	private Double cellQtyWithoutBs;

	@Column(name="discount_amt")
	private double discountAmt;

	@Column(name="mrp")
	private double mrp;

	@Column(name="net_amt")
	private double netAmt;

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="product_cd")
	private BeverageProductCategory product;

	@Column(name="product_qty_bs")
	private double productQtyBs;

	@Column(name="tax_amt")
	private double taxAmt;

	@Column(name="taxable_amt")
	private double taxableAmt;
	
	@OneToOne
	@JoinColumn(name="header_claim_id")
	@Where(clause="claimType='BREAKAGE_CLAIM'")
	private ClaimHeader header;

	public ClaimBreakagePrdctDtl() {
	}

	public long getSeqNum() {
		return this.seqNum;
	}

	public void setSeqNum(long seqNum) {
		this.seqNum = seqNum;
	}

	public double getBasicAmt() {
		return this.basicAmt;
	}

	public void setBasicAmt(double basicAmt) {
		this.basicAmt = basicAmt;
	}

	public Double getCellQtyWithoutBs() {
		return this.cellQtyWithoutBs;
	}

	public void setCellQtyWithoutBs(Double cellQtyWithoutBs) {
		this.cellQtyWithoutBs = cellQtyWithoutBs;
	}

	public double getDiscountAmt() {
		return this.discountAmt;
	}

	public void setDiscountAmt(double discountAmt) {
		this.discountAmt = discountAmt;
	}

	public double getMrp() {
		return this.mrp;
	}

	public void setMrp(double mrp) {
		this.mrp = mrp;
	}

	public double getNetAmt() {
		return this.netAmt;
	}

	public void setNetAmt(double netAmt) {
		this.netAmt = netAmt;
	}

	public double getProductQtyBs() {
		return this.productQtyBs;
	}

	public void setProductQtyBs(double productQtyBs) {
		this.productQtyBs = productQtyBs;
	}

	public double getTaxAmt() {
		return this.taxAmt;
	}

	public void setTaxAmt(double taxAmt) {
		this.taxAmt = taxAmt;
	}

	public double getTaxableAmt() {
		return this.taxableAmt;
	}

	public void setTaxableAmt(double taxableAmt) {
		this.taxableAmt = taxableAmt;
	}

	public BeverageProductCategory getProduct() {
		return product;
	}

	public void setProduct(BeverageProductCategory product) {
		this.product = product;
	}

	public ClaimHeader getHeader() {
		return header;
	}

	public void setHeader(ClaimHeader header) {
		this.header = header;
	}
}