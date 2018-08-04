package application.cdms.db.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity(name="NonBeverageProductCatergory")
@Table(name="non_beverage_product_category",schema="cdms")
public class NonBeverageProductCatergory extends ProductTypes implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GenericGenerator(
			name="stringSeq",
			strategy="application.cdms.hibernate.utility.StringSequenceIdentifier",
			parameters={
					@Parameter(name="seqColumnNm",value="NON_BEV_PROD_SEQ")
			}
	)
	@GeneratedValue(generator="stringSeq")
	@Column(name="non_bev_product_cd")
	private String productCd;
	
	@Column(name="group_name")
	private String groupName;

	public String getProductCd() {
		return productCd;
	}

	public void setProductCd(String productCd) {
		this.productCd = productCd;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
}
