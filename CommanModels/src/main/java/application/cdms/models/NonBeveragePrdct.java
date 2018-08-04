package application.cdms.models;

public class NonBeveragePrdct extends ProductCatagory {
	private String productCd;
	
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
