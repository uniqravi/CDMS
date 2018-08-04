package application.cdms.models;

public class SaleBrekageDtl {

	private long breakageSeq;

	private long breakageBs;
	
	private Product productCd;

	public long getBreakageSeq() {
		return breakageSeq;
	}

	public void setBreakageSeq(long breakageSeq) {
		this.breakageSeq = breakageSeq;
	}

	public long getBreakageBs() {
		return breakageBs;
	}

	public void setBreakageBs(long breakageBs) {
		this.breakageBs = breakageBs;
	}

	public Product getProductCd() {
		return productCd;
	}

	public void setProductCd(Product productCd) {
		this.productCd = productCd;
	}
}
