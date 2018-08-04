package application.cdms.models;

import java.io.Serializable;

public class ProductGroup implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String groupName;
	private FillingQty fillingQty1;
	private FillingQty fillingQty2;
	private PackingName packing;
	private PackingQty pakingQty;
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public FillingQty getFillingQty1() {
		return fillingQty1;
	}
	public void setFillingQty1(FillingQty fillingQty1) {
		this.fillingQty1 = fillingQty1;
	}
	public FillingQty getFillingQty2() {
		return fillingQty2;
	}
	public void setFillingQty2(FillingQty fillingQty2) {
		this.fillingQty2 = fillingQty2;
	}
	public PackingName getPacking() {
		return packing;
	}
	public void setPacking(PackingName packing) {
		this.packing = packing;
	}
	public PackingQty getPakingQty() {
		return pakingQty;
	}
	public void setPakingQty(PackingQty pakingQty) {
		this.pakingQty = pakingQty;
	}
	@Override
	public String toString() {
		return groupName;
	}
}
