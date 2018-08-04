package application.cdms.db.entities;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public class ProductTypes {

	@Column(name="product_added_dt")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar productAddedDt;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="hsn_code",referencedColumnName="hsn_cd")
	private HsnTaxStructure hsn;
	
	@Column(name="product_name")
	private String productNm;

	public Calendar getProductAddedDt() {
		return productAddedDt;
	}

	public void setProductAddedDt(Calendar productAddedDt) {
		this.productAddedDt = productAddedDt;
	}

	public HsnTaxStructure getHsn() {
		return hsn;
	}

	public void setHsn(HsnTaxStructure hsn) {
		this.hsn = hsn;
	}

	public String getProductNm() {
		return productNm;
	}

	public void setProductNm(String productNm) {
		this.productNm = productNm;
	}
}
