package application.cdms.db.entities;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import application.cdms.enums.InvoiceType;

@Entity
@Table(name="non_beverage_sale_dtl",schema="cdms")
public class NonBeverageSaleDtl {

	@Id
	@GenericGenerator(
			name="stringSeq",
			strategy="application.cdms.hibernate.utility.StringSequenceIdentifier",
			parameters={
					@Parameter(name="seqColumnNm",value="NB_SALE_INVOICE")
			}
	)
	@GeneratedValue(generator="stringSeq")
	@Column(name="nb_sale_invoice_no", unique=true, nullable=false)
	private String nbSaleInvoiceNo;
	
	@Column(name="cstmr_acnt_no")
	private String cstmrAcntNo;
	
	@Column(name="cstmr_or_firm_name")
	private String cstmrFrmNm;
	
	@Column(name="address")
	private String address;
	
	@Column(name="nb_sale_dt")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar nbSaleDt;
	
	@Column(name="invoice_type")
	private String invoiceType;
	
	@Column(name="cstmr_firm_gstn")
	private String cstmrFirmGstn;
	
	@OneToMany(mappedBy="nbSaleDtl",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<NonBeveragePrdctSaleDtl> nbSaleProductlst;

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

	public Calendar getNbSaleDt() {
		return nbSaleDt;
	}

	public void setNbSaleDt(Calendar nbSaleDt) {
		this.nbSaleDt = nbSaleDt;
	}

	public String getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	public List<NonBeveragePrdctSaleDtl> getNbSaleProductlst() {
		return nbSaleProductlst;
	}

	public void setNbSaleProductlst(List<NonBeveragePrdctSaleDtl> nbSaleProductlst) {
		this.nbSaleProductlst = nbSaleProductlst;
	}

	public String getCstmrFirmGstn() {
		return cstmrFirmGstn;
	}

	public void setCstmrFirmGstn(String cstmrFirmGstn) {
		this.cstmrFirmGstn = cstmrFirmGstn;
	}
	
}
