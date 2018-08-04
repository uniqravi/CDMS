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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the prdct_curr_price_scheme database table.
 * 
 */
@Entity
@Table(name="prdct_curr_price_scheme",schema="cdms")
//@NamedQuery(name="PrdctCurrPriceScheme.findAll", query="SELECT p FROM PrdctCurrPriceScheme p")
public class PrdctCurrPriceScheme implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="prdct_curr_price_scheme_seq")
	@SequenceGenerator(name = "prdct_curr_price_scheme_seq", sequenceName = "cdms.prdct_curr_price_scheme_seq", allocationSize = 1)
	@Column(name="seq_no", unique=true, nullable=false, length=20)
	private long seqNo;

	@OneToOne
	@JoinColumn(name="product_cd",unique=true,nullable=false)
	private BeverageProductCategory product;
	
	@Column(name="is_group_same_price")
	private String isGroupSamePrice;

	@Column(name="prdct_curr_mrp", precision=10, scale=2)
	private double prdctCurrMrp;

	@Column(name="prdct_curr_price", precision=10, scale=2)
	private double prdctCurrPrice;

	@Column(name="prdct_curr_scheme", length=200)
	private String prdctCurrScheme;

	@Column(name="price_lastchange_dt", nullable=false)
	private Calendar priceLastchangeDt;

	public PrdctCurrPriceScheme() {
	}

	public long getSeqNo() {
		return this.seqNo;
	}

	public void setSeqNo(long seqNo) {
		this.seqNo = seqNo;
	}

	public String getIsGroupSamePrice() {
		return this.isGroupSamePrice;
	}

	public void setIsGroupSamePrice(String isGroupSamePrice) {
		this.isGroupSamePrice = isGroupSamePrice;
	}

	public double getPrdctCurrMrp() {
		return prdctCurrMrp;
	}

	public void setPrdctCurrMrp(double prdctCurrMrp) {
		this.prdctCurrMrp = prdctCurrMrp;
	}

	public double getPrdctCurrPrice() {
		return prdctCurrPrice;
	}

	public void setPrdctCurrPrice(double prdctCurrPrice) {
		this.prdctCurrPrice = prdctCurrPrice;
	}

	public String getPrdctCurrScheme() {
		return prdctCurrScheme;
	}

	public void setPrdctCurrScheme(String prdctCurrScheme) {
		this.prdctCurrScheme = prdctCurrScheme;
	}

	public Calendar getPriceLastchangeDt() {
		return priceLastchangeDt;
	}

	public void setPriceLastchangeDt(Calendar priceLastchangeDt) {
		this.priceLastchangeDt = priceLastchangeDt;
	}

	public BeverageProductCategory getProduct() {
		return product;
	}

	public void setProduct(BeverageProductCategory product) {
		this.product = product;
	}
}