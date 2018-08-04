package application.cdms.db.entities;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the product_instant_stock_dtl database table.
 * 
 */
@Entity
@Table(name="product_instant_stock_dtl",schema="cdms")
//@NamedQuery(name="ProductInstantStockDtl.findAll", query="SELECT p FROM ProductInstantStockDtl p")
public class ProductInstantStockDtl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@TableGenerator(name="seqGnrtr",table="CDMS.SERIAL_NO_GENERATOR",pkColumnName="SEQ_NAME",valueColumnName="SEQ_VALUE",pkColumnValue="INSTANT_STOCK_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.TABLE,generator="seqGnrtr")
	@Column(name="prdct_stock_curr_seq")
	private long prdctStockCurrSeq;

	@Column(name="curr_prdct_qty_adustment")
	private Long currPrdctQtyAdustment;

	@Column(name="curr_stock_processed_id")
	private Long currStockProcessedId;

	@Column(name="offered_scheme_dtl")
	private String offeredSchemeDtl;

	@Column(name="packate_price")
	private Double packatePrice;

	@Column(name="prdct_breakage_qty_bs")
	private long prdctBreakageQtyBs;

	@Column(name="prdct_curr_available_qty")
	private Long prdctCurrAvailableQty;

	@Column(name="prdct_distributed_scheme_qty_bs")
	private long prdctDistributedSchemeQtyBs;

	@OneToOne
	@JoinColumn(name="prdct_group_name", nullable=false)
	private BeverageProductGroupDtl prdctGroup;
	
	@Column(name="prdct_mrp")
	private Double prdctMrp;

	@Column(name="prdct_opening_qty")
	private Long prdctOpeningQty;

	@Column(name="prdct_outscheme_sale_qty")
	private long prdctOutschemeSaleQty;

	@Column(name="prdct_purchase_qty")
	private long prdctPurchaseQty;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="prdct_stock_curr_time", nullable=false)
	private Calendar prdctStockCurrTime;

	@OneToOne
	@JoinColumn(name="product_cd", nullable=false)
	private BeverageProductCategory product;

	@Column(name="purchase_invoice_number")
	private String purchaseInvoiceNumber;

	@Column(name="sale_invoice_number")
	private String saleInvoiceNumber;

	public ProductInstantStockDtl() {
	}

	public long getPrdctStockCurrSeq() {
		return prdctStockCurrSeq;
	}

	public void setPrdctStockCurrSeq(long prdctStockCurrSeq) {
		this.prdctStockCurrSeq = prdctStockCurrSeq;
	}

	public Long getCurrPrdctQtyAdustment() {
		return currPrdctQtyAdustment;
	}

	public void setCurrPrdctQtyAdustment(Long currPrdctQtyAdustment) {
		this.currPrdctQtyAdustment = currPrdctQtyAdustment;
	}

	public Long getCurrStockProcessedId() {
		return currStockProcessedId;
	}

	public void setCurrStockProcessedId(Long currStockProcessedId) {
		this.currStockProcessedId = currStockProcessedId;
	}

	public String getOfferedSchemeDtl() {
		return offeredSchemeDtl;
	}

	public void setOfferedSchemeDtl(String offeredSchemeDtl) {
		this.offeredSchemeDtl = offeredSchemeDtl;
	}

	public Double getPackatePrice() {
		return packatePrice;
	}

	public void setPackatePrice(Double packatePrice) {
		this.packatePrice = packatePrice;
	}

	public long getPrdctBreakageQtyBs() {
		return prdctBreakageQtyBs;
	}

	public void setPrdctBreakageQtyBs(long prdctBreakageQtyBs) {
		this.prdctBreakageQtyBs = prdctBreakageQtyBs;
	}

	public Long getPrdctCurrAvailableQty() {
		return prdctCurrAvailableQty;
	}

	public void setPrdctCurrAvailableQty(Long prdctCurrAvailableQty) {
		this.prdctCurrAvailableQty = prdctCurrAvailableQty;
	}

	public long getPrdctDistributedSchemeQtyBs() {
		return prdctDistributedSchemeQtyBs;
	}

	public void setPrdctDistributedSchemeQtyBs(long prdctDistributedSchemeQtyBs) {
		this.prdctDistributedSchemeQtyBs = prdctDistributedSchemeQtyBs;
	}

	public BeverageProductGroupDtl getPrdctGroup() {
		return prdctGroup;
	}

	public void setPrdctGroup(BeverageProductGroupDtl prdctGroup) {
		this.prdctGroup = prdctGroup;
	}

	public Double getPrdctMrp() {
		return prdctMrp;
	}

	public void setPrdctMrp(Double prdctMrp) {
		this.prdctMrp = prdctMrp;
	}

	public Long getPrdctOpeningQty() {
		return prdctOpeningQty;
	}

	public void setPrdctOpeningQty(Long prdctOpeningQty) {
		this.prdctOpeningQty = prdctOpeningQty;
	}

	public long getPrdctOutschemeSaleQty() {
		return prdctOutschemeSaleQty;
	}

	public void setPrdctOutschemeSaleQty(long prdctOutschemeSaleQty) {
		this.prdctOutschemeSaleQty = prdctOutschemeSaleQty;
	}

	public long getPrdctPurchaseQty() {
		return prdctPurchaseQty;
	}

	public void setPrdctPurchaseQty(long prdctPurchaseQty) {
		this.prdctPurchaseQty = prdctPurchaseQty;
	}

	public Calendar getPrdctStockCurrTime() {
		return prdctStockCurrTime;
	}

	public void setPrdctStockCurrTime(Calendar prdctStockCurrTime) {
		this.prdctStockCurrTime = prdctStockCurrTime;
	}

	public BeverageProductCategory getProduct() {
		return product;
	}

	public void setProduct(BeverageProductCategory product) {
		this.product = product;
	}

	public String getPurchaseInvoiceNumber() {
		return purchaseInvoiceNumber;
	}

	public void setPurchaseInvoiceNumber(String purchaseInvoiceNumber) {
		this.purchaseInvoiceNumber = purchaseInvoiceNumber;
	}

	public String getSaleInvoiceNumber() {
		return saleInvoiceNumber;
	}

	public void setSaleInvoiceNumber(String saleInvoiceNumber) {
		this.saleInvoiceNumber = saleInvoiceNumber;
	}

}