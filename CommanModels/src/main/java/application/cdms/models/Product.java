package application.cdms.models;

import java.io.Serializable;

public class Product extends ProductCatagory implements Serializable,Comparable<Product>,Cloneable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String productCd;
	private FillingQty fillingQty;
	private Flavour flavour;
	private PackingName packing;
	private PackingQty packingQty;
	private String groupNm;
	private String productAddedBy;
	//private String productAddedDt;
	//private String productNm;
	private boolean hasNewFilling;
	private boolean hasNewFlavour;
	private boolean hasNewPakingType;
	private boolean hasNewPakingQty;
	private boolean hasNewGroup;
	//private HsnTax hsnTax;
	
	public boolean isHasNewGroup() {
		return hasNewGroup;
	}
	public void setHasNewGroup(boolean hasNewGroup) {
		this.hasNewGroup = hasNewGroup;
	}
	
	public String getProductCd() {
		return productCd;
	}
	public void setProductCd(String productCd) {
		this.productCd = productCd;
	}
	public FillingQty getFillingQty() {
		return fillingQty;
	}
	public void setFillingQty(FillingQty fillingQty) {
		this.fillingQty = fillingQty;
	}
	public Flavour getFlavour() {
		return flavour;
	}
	public void setFlavour(Flavour flavour) {
		this.flavour = flavour;
	}
	public String getGroupNm() {
		return groupNm;
	}
	public void setGroupNm(String groupNm) {
		this.groupNm = groupNm;
	}
	public PackingName getPacking() {
		return packing;
	}
	public void setPacking(PackingName packing) {
		this.packing = packing;
	}
	public PackingQty getPackingQty() {
		return packingQty;
	}
	public void setPackingQty(PackingQty packingQty) {
		this.packingQty = packingQty;
	}
	public String getProductAddedBy() {
		return productAddedBy;
	}
	public void setProductAddedBy(String productAddedBy) {
		this.productAddedBy = productAddedBy;
	}
	/*
	public String getProductAddedDt() {
		return productAddedDt;
	}
	public void setProductAddedDt(String productAddedDt) {
		this.productAddedDt = productAddedDt;
	}
	*/
	public boolean isHasNewFilling() {
		return hasNewFilling;
	}
	public void setHasNewFilling(boolean hasNewFilling) {
		this.hasNewFilling = hasNewFilling;
	}
	public boolean isHasNewFlavour() {
		return hasNewFlavour;
	}
	public void setHasNewFlavour(boolean hasNewFlavour) {
		this.hasNewFlavour = hasNewFlavour;
	}
	public boolean isHasNewPakingType() {
		return hasNewPakingType;
	}
	public void setHasNewPakingType(boolean hasNewPakingType) {
		this.hasNewPakingType = hasNewPakingType;
	}
	public boolean isHasNewPakingQty() {
		return hasNewPakingQty;
	}
	public void setHasNewPakingQty(boolean hasNewPakingQty) {
		this.hasNewPakingQty = hasNewPakingQty;
	}
	/*
	public String getProductNm() {
		return productNm;
	}
	public void setProductNm(String productNm) {
		this.productNm = productNm;
	}
	public String toString(){
		return productNm;
	}
	public HsnTax getHsnTax() {
		return hsnTax;
	}
	public void setHsnTax(HsnTax hsnTax) {
		this.hsnTax = hsnTax;
	}
	*/
	@Override
	public int compareTo(Product pr) {
		String thisPrdctNm = this.getProductNm();
		String prdctNm=pr.getProductNm();
		String[] strArray = thisPrdctNm.split("\\_");
		String mlThisString=strArray[0];
		String flavrNmThis= strArray[1];
		strArray = prdctNm.split("\\_");
		String mlString = strArray[0];
		String flvrNm=strArray[1];
		int mlthis=Integer.parseInt(mlThisString);
		int ml=Integer.parseInt(mlString);
		if(mlthis>ml){
			return  1;
		}
		else if(mlthis==ml){
			return flavrNmThis.compareTo(flvrNm);
		}
		else if(mlthis<ml){
			return -1;
		}
		return 0;
	}
}
