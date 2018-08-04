package application.cdms.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SalePrdctInvoice {
	private String seqNumber;
	private String prdctGroupDescription;
	private ProductGroup productGroup;
	private List<SalePrdct> salePrdctSet;
	private long sellingQty;
	private String sellingQtyCalculation="";
	private long sellingQtyCs;
	private double baseRatePerCs;
	private double netBaseAmt;
	private double schemeDisocuntAmt;
	private double shemeDiscountPerCs;
	private double taxableAmt;
	private double sgstRate;
	private double sgstAmt;
	private double cgstRate;
	private double cgstAmt;
	private double cessRate;
	private double cessAmt;
	private double igstRate;
	private double igstAmt;
	private double sysGrossNetPerCs;
	private double sysGrossPlusDiscountRatePerCs;
	private double sysGnrtdNetAmt;
	private double sysSpecialDiscountPerCs;
	private double totalSysGnrtdDiscount;
	private double totalDiscountAdjustment;
	private double totalAmtAdjustment;
	private double totalPrdctNetAmt;
	private long tempTtlGroupBottleQty;
	private boolean isValidForDBEntry=false;
	private boolean isValidForEditCol=false;
	private StringBuilder prductWithQtyStr=null;
	private String hsn;
	private Map<SalePrdct,Integer> tempSalePrdctsMap;
	private	double saleQtyInCsBs;
	private double mrp;
	private long leftOverBs;
	
	public String getHsn() {
		return hsn;
	}

	public void setHsn(String hsn) {
		this.hsn = hsn;
	}

	public StringBuilder getPrductWithQtyStr() {
		if(prductWithQtyStr==null){
			this.prductWithQtyStr = new StringBuilder();
		}
		return prductWithQtyStr;
	}
	
	public boolean isValidForDbEntry(){
		return isValidForDBEntry;
	}
	public boolean isValidForEditCol(){
		return isValidForEditCol;
	}
	public String getSellingQtyCalculation() {
		return sellingQtyCalculation;
	}
	public void postActionAftrSalePrdctAdd(SalePrdct salePrdct,int index){
		if(salePrdctSet==null){
			this.salePrdctSet = new ArrayList<>();
			this.tempSalePrdctsMap=new HashMap<>();
		}
		if(index==-1){
			this.salePrdctSet.add(salePrdct);
			int r = salePrdctSet.size()-1;
			this.tempSalePrdctsMap.put(salePrdct,r);
			getPrductWithQtyStr().append(salePrdct.getPrdctSallingQty()+ " | "+salePrdct.getProduct().getProductNm()+"\n");
		}
		else{
			SalePrdct prevSalePrdct = this.salePrdctSet.get(index);
			this.salePrdctSet.set(index,salePrdct);
			this.tempSalePrdctsMap.remove(salePrdct);
			this.tempSalePrdctsMap.put(salePrdct,index);
			int position=tempSalePrdctsMap.get(salePrdct);
			String[] strArry = getPrductWithQtyStr().toString().split("\\r\\n|\\n|\\r");
			int len = strArry.length;
			int charLen=0;
			if(position==0){
				charLen=0;
			}else{
				for(int k=0;k<len;k++){
					if(position==k){
						  break;
						}
					charLen+=strArry[k].length();
					charLen++;
				}
			}
			int totalLen = strArry[position].length();
			setTempTtlGroupBottleQty(getTempTtlGroupBottleQty()-prevSalePrdct.getPrdctSallingQty());
			getPrductWithQtyStr().replace(charLen,charLen+totalLen,salePrdct.getPrdctSallingQty()+ " | "+salePrdct.getProduct().getProductNm());
		}
		//getPrductWithQtyStr().re
		setTempTtlGroupBottleQty(getTempTtlGroupBottleQty()+salePrdct.getPrdctSallingQty());//sellingQtyCs
		this.netBaseAmt=0.00;
		this.schemeDisocuntAmt=0.00;
		this.taxableAmt=0.00;
		this.sgstAmt=0.00;
		this.cgstAmt=0.00;
		this.cessAmt=0.00;
		this.igstAmt=0.00;
		this.sysGnrtdNetAmt=0.00;
		this.totalPrdctNetAmt=0.00;
		if(getTempTtlGroupBottleQty()!=0){
			long packQty=salePrdct.getProduct().getPackingQty().getPackingQuantity();
			long rem = getTempTtlGroupBottleQty()%packQty;
			this.leftOverBs=rem;
			this.sellingQtyCs = getTempTtlGroupBottleQty()/packQty;
			if(rem==0){
				this.sellingQtyCs = getTempTtlGroupBottleQty()/packQty;
				this.sellingQty=getTempTtlGroupBottleQty();
				this.isValidForEditCol=true;
				this.sellingQtyCalculation=(sellingQtyCs)+" * "+packQty+" - "+getTempTtlGroupBottleQty()+" = 0";
				//totalSysGnrtdDiscount=sysSpecialDiscountPerCs*sellingQtyCs;
			}
			else{
				this.sellingQty=0;
				long tempSellingQty = getTempTtlGroupBottleQty()/packQty;
				long remainBottle = packQty-rem;
				this.sellingQtyCalculation=(tempSellingQty+1)+" * "+packQty+" - "+getTempTtlGroupBottleQty()+" = "+remainBottle;
				this.isValidForEditCol=false;
				this.totalSysGnrtdDiscount=0.00;
			}
		}
	}
	public long getSellingQtyCs() {
		return sellingQtyCs;
	}
	/*public long setSellingQtyCs() {
		return sellingQtyCs;
	}*/
	public String getSeqNumber() {
		return seqNumber;
	}
	public void setSeqNumber(String seqNumber) {
		this.seqNumber = seqNumber;
	}
	public String getPrdctGroupDescription() {
		return prdctGroupDescription;
	}
	public void setPrdctGroupDescription(String prdctGroupDescription) {
		this.prdctGroupDescription = prdctGroupDescription;
	}
	public ProductGroup getProductGroup() {
		return productGroup;
	}
	public void setProductGroup(ProductGroup productGroup) {
		this.productGroup = productGroup;
	}
	public List<SalePrdct> getSalePrdctSet() {
		return salePrdctSet;
	}
	public void setSalePrdctSet(List<SalePrdct> salePrdctSet) {
		this.salePrdctSet = salePrdctSet;
	}
	public long getSellingQty() {
		return sellingQty;
	}
	public void setSellingQty(long sellingQty) {
		this.sellingQty = sellingQty;
	}
	public double getBaseRatePerCs() {
		return baseRatePerCs;
	}
	public void setBaseRatePerCs(double baseRatePerCs) {
		this.baseRatePerCs = baseRatePerCs;
	}
	public double getNetBaseAmt() {
		return netBaseAmt;
	}
	public void setNetBaseAmt(double netBaseAmt) {
		this.netBaseAmt = netBaseAmt;
	}
	public double getSchemeDisocuntAmt() {
		return schemeDisocuntAmt;
	}
	public void setSchemeDisocuntAmt(double schemeDisocuntAmt) {
		this.schemeDisocuntAmt = schemeDisocuntAmt;
	}
	public double getTaxableAmt() {
		return taxableAmt;
	}
	public void setTaxableAmt(double taxableAmt) {
		this.taxableAmt = taxableAmt;
	}
	public double getSgstRate() {
		return sgstRate;
	}
	public void setSgstRate(double sgstRate) {
		this.sgstRate = sgstRate;
	}
	public double getSgstAmt() {
		return sgstAmt;
	}
	public void setSgstAmt(double sgstAmt) {
		this.sgstAmt = sgstAmt;
	}
	public double getCgstRate() {
		return cgstRate;
	}
	public void setCgstRate(double cgstRate) {
		this.cgstRate = cgstRate;
	}
	public double getCgstAmt() {
		return cgstAmt;
	}
	public void setCgstAmt(double cgstAmt) {
		this.cgstAmt = cgstAmt;
	}
	public double getIgstRate() {
		return igstRate;
	}
	public void setIgstRate(double igstRate) {
		this.igstRate = igstRate;
	}
	public double getIgstAmt() {
		return igstAmt;
	}
	public void setIgstAmt(double igstAmt) {
		this.igstAmt = igstAmt;
	}
	public double getCessRate() {
		return cessRate;
	}
	public void setCessRate(double cessRate) {
		this.cessRate = cessRate;
	}
	public double getCessAmt() {
		return cessAmt;
	}
	public void setCessAmt(double cessAmt) {
		this.cessAmt = cessAmt;
	}
	public double getSysGrossNetPerCs() {
		return sysGrossNetPerCs;
	}
	public void setSysGrossNetPerCs(double sysGrossNetPerCs) {
		this.sysGrossNetPerCs = sysGrossNetPerCs;
	}
	public double getSysGnrtdNetAmt() {
		return sysGnrtdNetAmt;
	}
	public void setSysGnrtdNetAmt(double sysGnrtdNetAmt) {
		this.sysGnrtdNetAmt = sysGnrtdNetAmt;
	}
	public double getSysSpecialDiscountPerCs() {
		return sysSpecialDiscountPerCs;
	}
	public void setSysSpecialDiscountPerCs(double sysSpecialDiscountPerCs) {
		this.sysSpecialDiscountPerCs = sysSpecialDiscountPerCs;
	}
	public double getTotalSysGnrtdDiscount() {
		return totalSysGnrtdDiscount;
	}
	public void setTotalSysGnrtdDiscount(double totalSysGnrtdDiscount) {
		this.totalSysGnrtdDiscount = totalSysGnrtdDiscount;
	}
	public double getTotalDiscountAdjustment() {
		return totalDiscountAdjustment;
	}
	public void setTotalDiscountAdjustment(double totalDiscountAdjustment) {
		this.totalDiscountAdjustment = totalDiscountAdjustment;
	}
	public double getTotalAmtAdjustment() {
		return totalAmtAdjustment;
	}
	public void setTotalAmtAdjustment(double totalAmtAdjustment) {
		this.totalAmtAdjustment = totalAmtAdjustment;
	}
	public double getTotalPrdctNetAmt() {
		return totalPrdctNetAmt;
	}
	public void setTotalPrdctNetAmt(double totalPrdctNetAmt) {
		this.totalPrdctNetAmt = totalPrdctNetAmt;
	}
	public long getTempTtlGroupBottleQty() {
		return tempTtlGroupBottleQty;
	}
	public void setTempTtlGroupBottleQty(long tempTtlGroupBottleQty) {
		this.tempTtlGroupBottleQty = tempTtlGroupBottleQty;
	}
	public double getShemeDiscountPerCs() {
		return shemeDiscountPerCs;
	}
	public void setShemeDiscountPerCs(double shemeDiscountPerCs) {
		this.shemeDiscountPerCs = shemeDiscountPerCs;
	}

	public double getSysGrossPlusDiscountRatePerCs() {
		return sysGrossPlusDiscountRatePerCs;
	}

	public void setSysGrossPlusDiscountRatePerCs(double sysGrossPlusDiscountRatePerCs) {
		this.sysGrossPlusDiscountRatePerCs = sysGrossPlusDiscountRatePerCs;
	}
	public Integer containItem(SalePrdct salePrdct){
		return tempSalePrdctsMap.get(salePrdct);
	}

	public double getSaleQtyInCsBs() {
		return saleQtyInCsBs;
	}

	public void setSaleQtyInCsBs(double saleQtyInCsBs) {
		this.sellingQty=tempTtlGroupBottleQty;
		this.saleQtyInCsBs = saleQtyInCsBs;
	}

	public double getMrp() {
		return mrp;
	}

	public void setMrp(double mrp) {
		this.mrp = mrp;
	}

	public long getLeftOverBs() {
		return leftOverBs;
	}

	public void setLeftOverBs(long leftOverBs) {
		this.leftOverBs = leftOverBs;
	}
}