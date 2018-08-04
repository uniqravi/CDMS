package application.cdms.models;


public class SupplyPrdcts {
	
	private long supplyPrdctSeq;

	private String groupName;
	
	private Product product;
	
	private long sentPrdctCsQty;
	
	private long sentPrdctBsQty;
	
	private long totalSentPrdctQty;
	
	private Long totalSellingPrdctQty;
	
	private Long expectedReturnQty;
	
	private Long actualReturnPrdctQty;
	
	private Long prdctLostQty;

	public long getSupplyPrdctSeq() {
		return supplyPrdctSeq;
	}

	public void setSupplyPrdctSeq(long supplyPrdctSeq) {
		this.supplyPrdctSeq = supplyPrdctSeq;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public long getTotalSentPrdctQty() {
		return totalSentPrdctQty;
	}

	public void setTotalSentPrdctQty(long totalSentPrdctQty) {
		this.totalSentPrdctQty = totalSentPrdctQty;
	}

	public Long getTotalSellingPrdctQty() {
		return totalSellingPrdctQty;
	}

	public void setTotalSellingPrdctQty(Long totalSellingPrdctQty) {
		this.totalSellingPrdctQty = totalSellingPrdctQty;
	}

	public Long getExpectedReturnQty() {
		return expectedReturnQty;
	}

	public void setExpectedReturnQty(Long expectedReturnQty) {
		this.expectedReturnQty = expectedReturnQty;
	}

	public Long getActualReturnPrdctQty() {
		return actualReturnPrdctQty;
	}

	public void setActualReturnPrdctQty(Long actualReturnPrdctQty) {
		this.actualReturnPrdctQty = actualReturnPrdctQty;
	}

	public Long getPrdctLostQty() {
		return prdctLostQty;
	}

	public void setPrdctLostQty(Long prdctLostQty) {
		this.prdctLostQty = prdctLostQty;
	}
	public boolean validateThenCalculateSentQty(long cs,long bs){
		if(bs>=0 && bs<product.getPackingQty().getPackingQuantity()){
			this.sentPrdctCsQty=cs;
			this.sentPrdctBsQty=bs;
			long temp = cs*product.getPackingQty().getPackingQuantity();
			this.totalSentPrdctQty=temp+bs;
			return true;
		}
		return false;
	}

	public long getSentPrdctCsQty() {
		return sentPrdctCsQty;
	}

	public void setSentPrdctCsQty(long sentPrdctCsQty) {
		this.sentPrdctCsQty = sentPrdctCsQty;
	}

	public long getSentPrdctBsQty() {
		return sentPrdctBsQty;
	}

	public void setSentPrdctBsQty(long sentPrdctBsQty) {
		this.sentPrdctBsQty = sentPrdctBsQty;
	}
	
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		if(supplyPrdctSeq!=0){
			result = prime * result + (int) (supplyPrdctSeq ^ (supplyPrdctSeq >>> 32));
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
		SupplyPrdcts other = (SupplyPrdcts) obj;
		if(supplyPrdctSeq!=0){
			if (supplyPrdctSeq != other.supplyPrdctSeq)
				return false;
		}
		else{
			if (product == null) {
				if (other.product != null)
					return false;
			} 
			if (other.product == null) {
				if (product != null)
					return false;
			} 
			else if (!product.getProductNm().equals(other.product.getProductNm())){
				return false;
			}
		}
		return true;
	}
}
