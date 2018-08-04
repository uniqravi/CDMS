package application.cdms.db.entities;

import java.io.Serializable;
import java.util.Calendar;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the sale_prdct_dtl database table.
 * 
 */
@Entity
@Table(name="sale_prdct_dtl",schema="cdms")
//@NamedQuery(name="SalePrdctDtl.findAll", query="SELECT s FROM SalePrdctDtl s")
public class SalePrdctDtl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@TableGenerator(name="seqGnrtr",table="CDMS.SERIAL_NO_GENERATOR",pkColumnName="SEQ_NAME",valueColumnName="SEQ_VALUE",pkColumnValue="SALE_PRDCT_TXN_ID",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.TABLE,generator="seqGnrtr")
	@Column(name="prdct_sale_txn_id", unique=true, nullable=false)
	private long prdctSaleTxnId;

	@OneToOne
	@JoinColumn(name="prdct_cd")
	private BeverageProductCategory prdct;
	
	@Column(name="prdct_sale_dt", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar prdctSaleDt;

	@Column(name="prdct_salling_qty", nullable=false)
	private long prdctSallingQty;

	@ManyToOne
	@JoinColumn(name="invoice_prdct_seq_no")
	private SaleInvoicesPrdctDtl saleInvoicedtl;

	public SalePrdctDtl() {
	}

	public long getPrdctSaleTxnId() {
		return this.prdctSaleTxnId;
	}

	public void setPrdctSaleTxnId(long prdctSaleTxnId) {
		this.prdctSaleTxnId = prdctSaleTxnId;
	}

	public BeverageProductCategory getPrdct() {
		return prdct;
	}

	public void setPrdct(BeverageProductCategory prdct) {
		this.prdct = prdct;
	}

	public Calendar getPrdctSaleDt() {
		return prdctSaleDt;
	}

	public void setPrdctSaleDt(Calendar prdctSaleDt) {
		this.prdctSaleDt = prdctSaleDt;
	}

	public long getPrdctSallingQty() {
		return prdctSallingQty;
	}

	public void setPrdctSallingQty(long prdctSallingQty) {
		this.prdctSallingQty = prdctSallingQty;
	}

	public SaleInvoicesPrdctDtl getSaleInvoicedtl() {
		return saleInvoicedtl;
	}

	public void setSaleInvoicedtl(SaleInvoicesPrdctDtl saleInvoicedtl) {
		this.saleInvoicedtl = saleInvoicedtl;
	}
}