package application.cdms.models;

public class ProductBreakageDtl {

	private long burst;
	private long leakage;
	private long shortage;
	private long sealPackShortage;
	private long openMouth;
	private String breakageDt;
	private long breakageId;
	private String breakageSource;
	private Product product;
	public long getBurst() {
		return burst;
	}
	public void setBurst(long burst) {
		this.burst = burst;
	}
	public long getLeakage() {
		return leakage;
	}
	public void setLeakage(long leakage) {
		this.leakage = leakage;
	}
	public long getShortage() {
		return shortage;
	}
	public void setShortage(long shortage) {
		this.shortage = shortage;
	}
	public long getSealPackShortage() {
		return sealPackShortage;
	}
	public void setSealPackShortage(long sealPackShortage) {
		this.sealPackShortage = sealPackShortage;
	}
	public long getOpenMouth() {
		return openMouth;
	}
	public void setOpenMouth(long openMouth) {
		this.openMouth = openMouth;
	}
	public String getBreakageDt() {
		return breakageDt;
	}
	public void setBreakageDt(String breakageDt) {
		this.breakageDt = breakageDt;
	}
	public long getBreakageId() {
		return breakageId;
	}
	public void setBreakageId(long breakageId) {
		this.breakageId = breakageId;
	}
	public String getBreakageSource() {
		return breakageSource;
	}
	public void setBreakageSource(String breakageSource) {
		this.breakageSource = breakageSource;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
}
