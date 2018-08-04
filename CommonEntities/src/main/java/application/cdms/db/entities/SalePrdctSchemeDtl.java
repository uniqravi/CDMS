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
 * The persistent class for the sale_prdct_scheme_dtl database table.
 * 
 */
@Entity
@Table(name="sale_prdct_scheme_dtl",schema="cdms")
//@NamedQuery(name="SalePrdctSchemeDtl.findAll", query="SELECT s FROM SalePrdctSchemeDtl s")
public class SalePrdctSchemeDtl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@TableGenerator(name="seqGnrtr",table="CDMS.SERIAL_NO_GENERATOR",pkColumnName="SEQ_NAME",valueColumnName="SEQ_VALUE",pkColumnValue="SALE_SCHEME_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.TABLE,generator="seqGnrtr")
	@Column(name="alloted_scheme_id")
	private long allotedSchemeId;

	@OneToOne
	@JoinColumn(name="alloted_to_group_name")
	private BeverageProductGroupDtl allotedToGroup;

	@OneToOne
	@JoinColumn(name="alloted_to_product_cd")
	private BeverageProductCategory allotedToProduct;

	@Column(name="calculated_scheme_qty_bs", nullable=false, precision=10)
	private long calculatedSchemeQtyBs;

	@Column(name="given_scheme_qty_bs", nullable=false, precision=10)
	private long givenSchemeQtyBs;

	@Column(name="old_given_scheme_bs", nullable=false, precision=10)
	private long oldGivenSchemeBs;

	@Column(name="pending_scheme_bs", precision=10)
	private Long pendingSchemeBs;

	@ManyToOne
	@JoinColumn(name="sale_invoice_no")
	private SaleDtl saleDtl;

	@Column(name="scheme_alloted_dt", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar schemeAllotedDt;

	@OneToOne
	@JoinColumn(name="scheme_prdct_cd")
	private BeverageProductCategory schemePrdct;

	public SalePrdctSchemeDtl() {
	}

	public long getAllotedSchemeId() {
		return allotedSchemeId;
	}

	public void setAllotedSchemeId(long allotedSchemeId) {
		this.allotedSchemeId = allotedSchemeId;
	}

	public BeverageProductGroupDtl getAllotedToGroup() {
		return allotedToGroup;
	}

	public void setAllotedToGroup(BeverageProductGroupDtl allotedToGroup) {
		this.allotedToGroup = allotedToGroup;
	}

	public BeverageProductCategory getAllotedToProduct() {
		return allotedToProduct;
	}

	public void setAllotedToProduct(BeverageProductCategory allotedToProduct) {
		this.allotedToProduct = allotedToProduct;
	}

	public long getCalculatedSchemeQtyBs() {
		return calculatedSchemeQtyBs;
	}

	public void setCalculatedSchemeQtyBs(long calculatedSchemeQtyBs) {
		this.calculatedSchemeQtyBs = calculatedSchemeQtyBs;
	}

	public long getGivenSchemeQtyBs() {
		return givenSchemeQtyBs;
	}

	public void setGivenSchemeQtyBs(long givenSchemeQtyBs) {
		this.givenSchemeQtyBs = givenSchemeQtyBs;
	}

	public long getOldGivenSchemeBs() {
		return oldGivenSchemeBs;
	}

	public void setOldGivenSchemeBs(long oldGivenSchemeBs) {
		this.oldGivenSchemeBs = oldGivenSchemeBs;
	}

	public Long getPendingSchemeBs() {
		return pendingSchemeBs;
	}

	public void setPendingSchemeBs(Long pendingSchemeBs) {
		this.pendingSchemeBs = pendingSchemeBs;
	}

	public SaleDtl getSaleDtl() {
		return saleDtl;
	}

	public void setSaleDtl(SaleDtl saleDtl) {
		this.saleDtl = saleDtl;
	}

	public Calendar getSchemeAllotedDt() {
		return schemeAllotedDt;
	}

	public void setSchemeAllotedDt(Calendar schemeAllotedDt) {
		this.schemeAllotedDt = schemeAllotedDt;
	}

	public BeverageProductCategory getSchemePrdct() {
		return schemePrdct;
	}

	public void setSchemePrdct(BeverageProductCategory schemePrdct) {
		this.schemePrdct = schemePrdct;
	}

}