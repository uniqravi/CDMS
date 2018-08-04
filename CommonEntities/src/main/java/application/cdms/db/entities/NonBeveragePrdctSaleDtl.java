package application.cdms.db.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name="non_beverage_prdct_sale_dtl",schema="cdms")
public class NonBeveragePrdctSaleDtl {

	@Id
	@TableGenerator(name="seqGnrtr",table="CDMS.SERIAL_NO_GENERATOR",pkColumnName="SEQ_NAME",valueColumnName="SEQ_VALUE",pkColumnValue="NB_PRDCT_SALE_TXN_ID",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.TABLE,generator="seqGnrtr")
	@Column(name="seq_number")
	private int seqNumber;
	
	@OneToOne
	@JoinColumn(name="product_cd")
	private NonBeverageProductCatergory nonBeverageProduct;
	
	@Column(name="selling_qty")
	private long sellingQty;
	
	@ManyToOne
	@JoinColumn(name="nb_sale_invoice_no")
	private NonBeverageSaleDtl nbSaleDtl;
	
	@Column(name="unit_price")
	private double unitPrice;
	
	@Column(name="net_base_amt")
	private double netBaseAmt;

	@Column(name="disocunt_amt")
	private double disocuntAmt;
	
	@Column(name="taxable_amt")
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

	@Column(name="sys_gnrtd_net_amt", precision=10, scale=2)
	private double sysGnrtdNetAmt;

	public int getSeqNumber() {
		return seqNumber;
	}

	public void setSeqNumber(int seqNumber) {
		this.seqNumber = seqNumber;
	}

	public NonBeverageProductCatergory getNonBeverageProduct() {
		return nonBeverageProduct;
	}

	public void setNonBeverageProduct(NonBeverageProductCatergory nonBeverageProduct) {
		this.nonBeverageProduct = nonBeverageProduct;
	}

	public long getSellingQty() {
		return sellingQty;
	}

	public void setSellingQty(long sellingQty) {
		this.sellingQty = sellingQty;
	}

	public NonBeverageSaleDtl getNbSaleDtl() {
		return nbSaleDtl;
	}

	public void setNbSaleDtl(NonBeverageSaleDtl nbSaleDtl) {
		this.nbSaleDtl = nbSaleDtl;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public double getNetBaseAmt() {
		return netBaseAmt;
	}

	public void setNetBaseAmt(double netBaseAmt) {
		this.netBaseAmt = netBaseAmt;
	}

	public double getDisocuntAmt() {
		return disocuntAmt;
	}

	public void setDisocuntAmt(double disocuntAmt) {
		this.disocuntAmt = disocuntAmt;
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

	public double getSysGnrtdNetAmt() {
		return sysGnrtdNetAmt;
	}

	public void setSysGnrtdNetAmt(double sysGnrtdNetAmt) {
		this.sysGnrtdNetAmt = sysGnrtdNetAmt;
	}
}
