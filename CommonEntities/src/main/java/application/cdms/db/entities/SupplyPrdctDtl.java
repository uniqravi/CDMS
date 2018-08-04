package application.cdms.db.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;


/**
 * The persistent class for the supply_prdct_dtl database table.
 * 
 */
@Entity
@Table(name="supply_prdct_dtl",schema="cdms")
//@NamedQuery(name="SupplyPrdctDtl.findAll", query="SELECT s FROM SupplyPrdctDtl s")
public class SupplyPrdctDtl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@TableGenerator(name="seqGnrtr",table="CDMS.SERIAL_NO_GENERATOR",pkColumnName="SEQ_NAME",valueColumnName="SEQ_VALUE",pkColumnValue="SUPPLY_PRDCT_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.TABLE,generator="seqGnrtr")
	@Column(name="supply_prdct_seq")
	private long supplyPrdctSeq;

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="product_cd")
	private BeverageProductCategory product;
	
	@Column(name="total_sent_prdct_qty")
	private long totalSentPrdctQty;
	
	@Column(name="total_selling_prdct_qty")
	private Long totalSellingPrdctQty;
	
	@Column(name="expected_return_qty")
	private Long expectedReturnQty;
	
	@Column(name="actual_return_prdct_qty")
	private Long actualReturnPrdctQty;
	
	@Column(name="prdct_lost_qty")
	private Long prdctLostQty;
	
	@ManyToOne
	@JoinColumn(name="supply_seq_no")
	private SupplyRecord supplyRecordMaster;

	public SupplyPrdctDtl() {
	}

	public long getSupplyPrdctSeq() {
		return supplyPrdctSeq;
	}

	public void setSupplyPrdctSeq(long supplyPrdctSeq) {
		this.supplyPrdctSeq = supplyPrdctSeq;
	}

	public BeverageProductCategory getProduct() {
		return product;
	}

	public void setProduct(BeverageProductCategory product) {
		this.product = product;
	}

	public long getTotalSentPrdctQty() {
		return totalSentPrdctQty;
	}

	public void setTotalSentPrdctQty(long totalSentPrdctQty) {
		this.totalSentPrdctQty = totalSentPrdctQty;
	}

	public Long getTotalSellingPrdctQty() {
		return totalSellingPrdctQty;
	}

	public void setTotalSellingPrdctQty(Long totalSellingPrdctQty) {
		this.totalSellingPrdctQty = totalSellingPrdctQty;
	}

	public Long getExpectedReturnQty() {
		return expectedReturnQty;
	}

	public void setExpectedReturnQty(Long expectedReturnQty) {
		this.expectedReturnQty = expectedReturnQty;
	}

	public Long getActualReturnPrdctQty() {
		return actualReturnPrdctQty;
	}

	public void setActualReturnPrdctQty(Long actualReturnPrdctQty) {
		this.actualReturnPrdctQty = actualReturnPrdctQty;
	}

	public Long getPrdctLostQty() {
		return prdctLostQty;
	}

	public void setPrdctLostQty(Long prdctLostQty) {
		this.prdctLostQty = prdctLostQty;
	}

	public SupplyRecord getSupplyRecordMaster() {
		return supplyRecordMaster;
	}

	public void setSupplyRecordMaster(SupplyRecord supplyRecordMaster) {
		this.supplyRecordMaster = supplyRecordMaster;
	}

}