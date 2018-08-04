package application.cdms.models;

import java.util.List;

public class ProductPriceSchemeDtl {

	private Product product;
	
	private double prdctPrice;
	
	private List<Scheme> schemeChoiceLst;
	
	private String dbSchemeScript;     

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public double getPrdctPrice() {
		return prdctPrice;
	}

	public void setPrdctPrice(double prdctPrice) {
		this.prdctPrice = prdctPrice;
	}

	public List<Scheme> getSchemeChoiceLst() {
		return schemeChoiceLst;
	}

	public void setSchemeChoiceLst(List<Scheme> schemeChoiceLst) {
		this.schemeChoiceLst = schemeChoiceLst;
	}

	public String getDbSchemeScript() {
		return dbSchemeScript;
	}

	public void setDbSchemeScript(String dbSchemeScript) {
		this.dbSchemeScript = dbSchemeScript;
	}
}