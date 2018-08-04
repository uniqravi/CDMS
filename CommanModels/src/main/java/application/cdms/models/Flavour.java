package application.cdms.models;

import java.io.Serializable;

public class Flavour implements Serializable,Comparable<Flavour>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String flavrCd;
	private String flavrName;
	private String falvrAddedBy;
	private String flavrAddedDt;
	public String getFlavrCd() {
		return flavrCd;
	}
	public void setFlavrCd(String flavrCd) {
		this.flavrCd = flavrCd;
	}
	public String getFlavrName() {
		return flavrName;
	}
	public void setFlavrName(String flavrName) {
		this.flavrName = flavrName;
	}
	public String getFalvrAddedBy() {
		return falvrAddedBy;
	}
	public void setFalvrAddedBy(String falvrAddedBy) {
		this.falvrAddedBy = falvrAddedBy;
	}
	public String getFlavrAddedDt() {
		return flavrAddedDt;
	}
	public void setFlavrAddedDt(String flavrAddedDt) {
		this.flavrAddedDt = flavrAddedDt;
	}
	
	@Override
	public int compareTo(Flavour o){
		return this.flavrName.compareTo(o.flavrName);
	}
}
