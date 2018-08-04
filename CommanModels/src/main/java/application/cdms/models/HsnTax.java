package application.cdms.models;

import java.util.Calendar;

public class HsnTax {
	private String hsnCd;
	
	private double cess;

	private double cgst;

	private String hsnDiscription;

	private Calendar lastAddedDt;

	private double sgstOrIgst;
	
	private double igst;

	public String getHsnCd() {
		return hsnCd;
	}

	public void setHsnCd(String hsnCd) {
		this.hsnCd = hsnCd;
	}

	public double getCess() {
		return cess/100;
	}

	public void setCess(double cess) {
		this.cess = cess;
	}

	public double getCgst() {
		return cgst/100;
	}

	public void setCgst(double cgst) {
		this.cgst = cgst;
	}

	public String getHsnDiscription() {
		return hsnDiscription;
	}

	public void setHsnDiscription(String hsnDiscription) {
		this.hsnDiscription = hsnDiscription;
	}

	public Calendar getLastAddedDt() {
		return lastAddedDt;
	}

	public void setLastAddedDt(Calendar lastAddedDt) {
		this.lastAddedDt = lastAddedDt;
	}

	public double getSgstOrIgst() {
		return sgstOrIgst/100;
	}

	public void setIgst(double igst) {
		this.igst = igst;
	}
	
	public double getIgst() {
		return igst/100;
	}

	public void setSgstOrIgst(double sgstOrIgst) {
		this.sgstOrIgst = sgstOrIgst;
	}

	@Override
	public String toString() {
		return "HsnTax [hsnCd=" + hsnCd + ", cess=" + cess + ", cgst=" + cgst + ", hsnDiscription=" + hsnDiscription
				+ ", sgstOrIgst=" + sgstOrIgst + "]";
	}
	
	
}
