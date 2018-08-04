package application.cdms.models;

import java.util.Calendar;

public class SalePrdct {
	private long prdctSaleTxnId;
	private Product product;
	private Calendar prdctSaleDt;
	private long prdctSallingQty;
	public long getPrdctSaleTxnId() {
		return prdctSaleTxnId;
	}
	public void setPrdctSaleTxnId(long prdctSaleTxnId) {
		this.prdctSaleTxnId = prdctSaleTxnId;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Calendar getPrdctSaleDt() {
		return prdctSaleDt;
	}
	public void setPrdctSaleDt(Calendar prdctSaleDt) {
		this.prdctSaleDt = prdctSaleDt;
	}
	public long getPrdctSallingQty() {
		return prdctSallingQty;
	}
	public void setPrdctSallingQty(long prdctSallingQty) {
		this.prdctSallingQty = prdctSallingQty;
	}
	public void setSellingQty(long prdctSellingCs,long prdctSellingBs){
		this.prdctSallingQty=((product.getPackingQty().getPackingQuantity())*prdctSellingCs)+prdctSellingBs;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		//result = prime * result + ((prdctSaleDt == null) ? 0 : prdctSaleDt.hashCode());
		if(prdctSaleTxnId!=0){
			result = prime * result + (int) (prdctSaleTxnId ^ (prdctSaleTxnId >>> 32));
		}
		else{
			result = prime * result + ((product == null) ? 0 : product.getProductNm().hashCode());
		}
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SalePrdct other = (SalePrdct) obj;
		if(prdctSaleTxnId!=0){
			if (prdctSaleTxnId != other.prdctSaleTxnId)
				return false;
		}
		else{
			if (product == null) {
				if (other.product != null)
					return false;
			} else if (!product.getProductNm().equals(other.product.getProductNm()))
				return false;
		}
		return true;
	}
}