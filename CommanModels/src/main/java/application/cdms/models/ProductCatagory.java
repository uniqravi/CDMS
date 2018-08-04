package application.cdms.models;

public abstract class ProductCatagory {

	private String productNm;
	
	private String productAddedDt;
	
	private HsnTax hsnTax;

	public String getProductNm() {
		return productNm;
	}

	public void setProductNm(String productNm) {
		this.productNm = productNm;
	}

	public String getProductAddedDt() {
		return productAddedDt;
	}

	public void setProductAddedDt(String productAddedDt) {
		this.productAddedDt = productAddedDt;
	}

	public HsnTax getHsnTax() {
		return hsnTax;
	}

	public void setHsnTax(HsnTax hsnTax) {
		this.hsnTax = hsnTax;
	}
	public String toString(){
		return productNm;
	}
}
