package application.cdms.models;

import java.io.Serializable;

public class PackingName implements Serializable,Comparable<PackingName>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String packingNameCd;
	private String packingName;
	private String pakingAddedBy;
	private String pakingAddedDt;
	public String getPackingNameCd() {
		return packingNameCd;
	}
	public void setPackingNameCd(String packingNameCd) {
		this.packingNameCd = packingNameCd;
	}
	public String getPackingName() {
		return packingName;
	}
	public void setPackingName(String packingName) {
		this.packingName = packingName;
	}
	public String getPakingAddedBy() {
		return pakingAddedBy;
	}
	public void setPakingAddedBy(String pakingAddedBy) {
		this.pakingAddedBy = pakingAddedBy;
	}
	public String getPakingAddedDt() {
		return pakingAddedDt;
	}
	public void setPakingAddedDt(String pakingAddedDt) {
		this.pakingAddedDt = pakingAddedDt;
	}
	@Override
	public int compareTo(PackingName o) {
		return this.packingName.compareTo(o.packingName);
	}
}
