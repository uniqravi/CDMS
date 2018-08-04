package application.cdms.models;

import java.io.Serializable;

public class PackingQty implements Serializable,Comparable<PackingQty>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String packingQtyCd;
	private int packingQuantity;
	private String packingQtyAddedBy;
	private String packingQtyAddedDt;
	public String getPackingQtyCd() {
		return packingQtyCd;
	}
	public void setPackingQtyCd(String packingQtyCd) {
		this.packingQtyCd = packingQtyCd;
	}
	public int getPackingQuantity() {
		return packingQuantity;
	}
	public void setPackingQuantity(int packingQuantity) {
		this.packingQuantity = packingQuantity;
	}
	public String getPackingQtyAddedBy() {
		return packingQtyAddedBy;
	}
	public void setPackingQtyAddedBy(String packingQtyAddedBy) {
		this.packingQtyAddedBy = packingQtyAddedBy;
	}
	public String getPackingQtyAddedDt() {
		return packingQtyAddedDt;
	}
	public void setPackingQtyAddedDt(String packingQtyAddedDt) {
		this.packingQtyAddedDt = packingQtyAddedDt;
	}
	
	@Override
	public int compareTo(PackingQty o){
		int returnVal=-1;
		if(this.packingQuantity>o.packingQuantity){
			returnVal=1;
		}
		else if(this.packingQuantity==o.packingQuantity){
			returnVal=0;
		}
		else{
			returnVal=-1;
		}
		return returnVal;
	}
}