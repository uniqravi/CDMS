package application.cdms.models;

import java.util.List;

import application.cdms.enums.InvoiceType;

public class NonBeverageSale {
	
	private String nbSaleInvoiceNo;
	
	private String cstmrAcntNo;
	
	private String cstmrFrmNm;
	
	private String address;
	
	private String nbSaleDt;
	
	private String buyerGstn;
	
	private InvoiceType invoiceType;
	
	private String gstn;
	
	private List<NonBeveragePrdctSale> nbSaleProductlst;

	public String getNbSaleInvoiceNo() {
		return nbSaleInvoiceNo;
	}

	public void setNbSaleInvoiceNo(String nbSaleInvoiceNo) {
		this.nbSaleInvoiceNo = nbSaleInvoiceNo;
	}

	public String getCstmrAcntNo() {
		return cstmrAcntNo;
	}

	public void setCstmrAcntNo(String cstmrAcntNo) {
		this.cstmrAcntNo = cstmrAcntNo;
	}

	public String getCstmrFrmNm() {
		return cstmrFrmNm;
	}

	public void setCstmrFrmNm(String cstmrFrmNm) {
		this.cstmrFrmNm = cstmrFrmNm;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNbSaleDt() {
		return nbSaleDt;
	}

	public void setNbSaleDt(String nbSaleDt) {
		this.nbSaleDt = nbSaleDt;
	}

	public InvoiceType getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(InvoiceType invoiceType) {
		this.invoiceType = invoiceType;
	}

	public List<NonBeveragePrdctSale> getNbSaleProductlst() {
		return nbSaleProductlst;
	}

	public void setNbSaleProductlst(List<NonBeveragePrdctSale> nbSaleProductlst) {
		this.nbSaleProductlst = nbSaleProductlst;
	}

	public String getBuyerGstn() {
		return buyerGstn;
	}

	public void setBuyerGstn(String buyerGstn) {
		this.buyerGstn = buyerGstn;
	}

	public String getGstn() {
		return gstn;
	}

	public void setGstn(String gstn) {
		this.gstn = gstn;
	}
}
