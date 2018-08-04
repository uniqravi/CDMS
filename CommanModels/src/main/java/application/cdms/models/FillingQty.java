package application.cdms.models;

import java.io.Serializable;

public class FillingQty implements Serializable,Comparable<FillingQty>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fillingQtyCd;
	private String fillingQtyMl;
	private String fillingAddedBy;
	private String fillingQtyAddedDt;
	
	public String getFillingQtyCd() {
		return fillingQtyCd;
	}
	public void setFillingQtyCd(String fillingQtyCd) {
		this.fillingQtyCd = fillingQtyCd;
	}
	public String getFillingQtyMl() {
		return fillingQtyMl;
	}
	public void setFillingQtyMl(String fillingQtyMl) {
		this.fillingQtyMl = fillingQtyMl;
	}
	public String getFillingAddedBy() {
		return fillingAddedBy;
	}
	public void setFillingAddedBy(String fillingAddedBy) {
		this.fillingAddedBy = fillingAddedBy;
	}
	public String getFillingQtyAddedDt() {
		return fillingQtyAddedDt;
	}
	public void setFillingQtyAddedDt(String fillingQtyAddedDt) {
		this.fillingQtyAddedDt = fillingQtyAddedDt;
	}
	
	@Override
	public int compareTo(FillingQty o) {
		Integer thisMl = Integer.parseInt(this.fillingQtyMl);
		Integer M2 = Integer.parseInt(o.fillingQtyMl);
		if(thisMl>M2){
			return 1;
		}
		else if(thisMl==M2){
			return 0;
		}
		else{
			return -1;
		}
		//return this.fillingQtyMl.compareTo(o.fillingQtyMl);
	}
}
