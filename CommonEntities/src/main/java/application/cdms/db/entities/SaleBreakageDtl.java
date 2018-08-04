package application.cdms.db.entities;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the sale_breakage_dtl database table.
 * 
 */
@Entity
@Table(name="sale_breakage_dtl",schema="cdms")
//@NamedQuery(name="SaleBreakageDtl.findAll", query="SELECT s FROM SaleBreakageDtl s")
public class SaleBreakageDtl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@TableGenerator(name="breakageGnrtr",table="CDMS.SERIAL_NO_GENERATOR",pkColumnName="SEQ_NAME",valueColumnName="SEQ_VALUE",pkColumnValue="SALE_BREAKAGE_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.TABLE,generator="breakageGnrtr")
	@Column(name="breakage_seq", unique=true, nullable=false, precision=10)
	private long breakageSeq;

	@Column(name="breakage_bs")
	private long breakageBs;

	@Column(name="breakage_dt")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar breakageDt;

	@OneToOne
	@JoinColumn(name="product_cd", nullable=false)
	private BeverageProductCategory productCd;

	@ManyToOne
	@JoinColumn(name="sale_invoice_no")
	private SaleDtl saleDtl;

	public SaleBreakageDtl() {
	}

	public long getBreakageSeq() {
		return breakageSeq;
	}

	public void setBreakageSeq(long breakageSeq) {
		this.breakageSeq = breakageSeq;
	}

	public long getBreakageBs() {
		return breakageBs;
	}

	public void setBreakageBs(long breakageBs) {
		this.breakageBs = breakageBs;
	}

	public Calendar getBreakageDt() {
		return breakageDt;
	}

	public void setBreakageDt(Calendar breakageDt) {
		this.breakageDt = breakageDt;
	}
	public BeverageProductCategory getProductCd() {
		return productCd;
	}
	public void setProductCd(BeverageProductCategory productCd) {
		this.productCd = productCd;
	}

	public SaleDtl getSaleDtl() {
		return saleDtl;
	}

	public void setSaleDtl(SaleDtl saleDtl) {
		this.saleDtl = saleDtl;
	}
}