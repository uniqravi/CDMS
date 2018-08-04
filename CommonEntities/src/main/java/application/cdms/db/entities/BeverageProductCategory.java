package application.cdms.db.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


/**
 * The persistent class for the beverage_product_category database table.
 * 
 */
@Entity(name="BeverageProductCategory")
@Table(name="beverage_product_category",schema="cdms")
//@NamedQuery(name="BeverageProductCategory.findAll", query="SELECT b FROM BeverageProductCategory b")
public class BeverageProductCategory extends ProductTypes implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(
			name="stringSeq",
			strategy="application.cdms.hibernate.utility.StringSequenceIdentifier",
			parameters={
					@Parameter(name="seqColumnNm",value="PROD_SEQ")
			}
	)
	@GeneratedValue(generator="stringSeq")
	@Column(name="product_cd")
	private String productCd;

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="filling_qty_cd")
	private FillingQtyCategory fillingQty;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="flavr_cd")
	private FlavourCategory flavr;

	@Column(name="group_name")
	private String groupName;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="packing_name_cd")
	private PackingNameCategory packing;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="packing_qty_cd")
	private PackingQtyCategory packingQty;

	@Column(name="product_added_by")
	private String productAddedBy;
	/*
	@Column(name="product_name")
	private String productNm;
	
	@Column(name="product_added_dt")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar productAddedDt;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="hsn_code",referencedColumnName="hsn_cd")
	private HsnTaxStructure hsn;
*/
	public BeverageProductCategory() {
	}

	public String getProductCd() {
		return this.productCd;
	}

	public void setProductCd(String productCd) {
		this.productCd = productCd;
	}

	public String getProductAddedBy() {
		return this.productAddedBy;
	}

	public void setProductAddedBy(String productAddedBy) {
		this.productAddedBy = productAddedBy;
	}
/*
	public Calendar getProductAddedDt() {
		return this.productAddedDt;
	}

	public void setProductAddedDt(Calendar productAddedDt) {
		this.productAddedDt = productAddedDt;
	}
*/
	public FillingQtyCategory getFillingQty() {
		return fillingQty;
	}

	public void setFillingQty(FillingQtyCategory fillingQty) {
		this.fillingQty = fillingQty;
	}

	public FlavourCategory getFlavr() {
		return flavr;
	}

	public void setFlavr(FlavourCategory flavr) {
		this.flavr = flavr;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public PackingNameCategory getPacking() {
		return packing;
	}

	public void setPacking(PackingNameCategory packing) {
		this.packing = packing;
	}

	public PackingQtyCategory getPackingQty() {
		return packingQty;
	}

	public void setPackingQty(PackingQtyCategory packingQty) {
		this.packingQty = packingQty;
	}
/*
	public String getProductNm() {
		return productNm;
	}

	public void setProductNm(String productNm) {
		this.productNm = productNm;
	}
	public HsnTaxStructure getHsn() {
		return hsn;
	}

	public void setHsn(HsnTaxStructure hsn) {
		this.hsn = hsn;
	}*/
}