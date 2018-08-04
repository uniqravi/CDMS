package application.cdms.models;

public class SaleScheme {

	private long allotedSchemeId;

	private ProductGroup allotedToGroup;

	private Product allotedToProduct;

	private long calculatedSchemeQtyBs;

	private long givenSchemeQtyBs;

	private long oldGivenSchemeBs;

	private Long pendingSchemeBs;

	private String schemeAllotedDt;

	private Product schemePrdct;

	public SaleScheme() {
	}

	public long getAllotedSchemeId() {
		return allotedSchemeId;
	}

	public void setAllotedSchemeId(long allotedSchemeId) {
		this.allotedSchemeId = allotedSchemeId;
	}

	public ProductGroup getAllotedToGroup() {
		return allotedToGroup;
	}

	public void setAllotedToGroup(ProductGroup allotedToGroup) {
		this.allotedToGroup = allotedToGroup;
	}

	public Product getAllotedToProduct() {
		return allotedToProduct;
	}

	public void setAllotedToProduct(Product allotedToProduct) {
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

	public String getSchemeAllotedDt() {
		return schemeAllotedDt;
	}

	public void setSchemeAllotedDt(String schemeAllotedDt) {
		this.schemeAllotedDt = schemeAllotedDt;
	}

	public Product getSchemePrdct() {
		return schemePrdct;
	}

	public void setSchemePrdct(Product schemePrdct) {
		this.schemePrdct = schemePrdct;
	}
	
}
