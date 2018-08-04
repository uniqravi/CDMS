package application.cdms.db.entities;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the hsn_tax_structure database table.
 * 
 */
@Entity
@Table(name="hsn_tax_structure",schema="cdms")
//@NamedQuery(name="HsnTaxStructure.findAll", query="SELECT h FROM HsnTaxStructure h")
public class HsnTaxStructure implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="hsn_cd", nullable=false, length=20)
	private String hsnCd;
	
	@Column(nullable=false, precision=5)
	private double cess;

	@Column(nullable=false, precision=5)
	private double cgst;

	@Column(name="hsn_discription", length=50)
	private String hsnDiscription;

	@Column(name="last_added_dt", nullable=false)
	private Calendar lastAddedDt;

	@Column(name="sgst_or_igst", nullable=false, precision=5)
	private double sgstOrIgst;
	
	@Column(name="igst")
	private double igst;

	public HsnTaxStructure() {
	}

	public String getHsnCd() {
		return hsnCd;
	}

	public void setHsnCd(String hsnCd) {
		this.hsnCd = hsnCd;
	}

	public double getCess() {
		return cess;
	}

	public void setCess(double cess) {
		this.cess = cess;
	}

	public double getCgst() {
		return cgst;
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
		return sgstOrIgst;
	}

	public void setSgstOrIgst(double sgstOrIgst) {
		this.sgstOrIgst = sgstOrIgst;
	}

	public double getIgst() {
		return igst;
	}

	public void setIgst(double igst) {
		this.igst = igst;
	}

}