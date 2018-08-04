package application.cdms.models;

public class SchemeProduct {

	private ProductGroup groupName;

	private Product product;
	
	private long prdctOrGroupBSQty;
	
	private String colHeading;
	
	public SchemeProduct(){
		
	}
	public SchemeProduct(String colHeading){
		this.colHeading=colHeading;
	}
	
	public ProductGroup getGroupName() {
		return groupName;
	}

	public void setGroupName(ProductGroup groupName) {
		this.groupName = groupName;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public long getPrdctOrGroupBSQty() {
		return prdctOrGroupBSQty;
	}

	public void setPrdctOrGroupBSQty(long prdctOrGroupBSQty) {
		this.prdctOrGroupBSQty = prdctOrGroupBSQty;
	}

	@Override
	public String toString() {
		String str=null;
		if(colHeading!=null){
			str=colHeading;
		}
		else{
		if(product!=null){
			str=product.getProductNm()+" ";
		}
		else if(groupName!=null){
			str=groupName.getGroupName()+" ";
		}
		str+="( "+prdctOrGroupBSQty+" )";
		}
		return str;
	}
}
