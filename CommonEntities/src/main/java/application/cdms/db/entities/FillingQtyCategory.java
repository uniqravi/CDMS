package application.cdms.db.entities;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


/**
 * The persistent class for the filling_qty_category database table.
 * 
 */
@Entity
@Table(name="filling_qty_category",schema="cdms")
//@NamedQuery(name="FillingQtyCategory.findAll", query="SELECT f FROM FillingQtyCategory f")
public class FillingQtyCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(
			name="stringSeq",
			strategy="application.cdms.hibernate.utility.StringSequenceIdentifier",
			parameters={
					@Parameter(name="seqColumnNm",value="FILLINGQTY_SEQ")
			}
	)
	@GeneratedValue(generator="stringSeq")
	@Column(name="filling_qty_cd")
	private String fillingQtyCd;

	@Column(name="filling_qty_added_by")
	private String fillingQtyAddedBy;

	@Column(name="filling_qty_added_dt")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fillingQtyAddedDt;

	@Column(name="filling_qty_ml")
	private String fillingQtyMl;

	public FillingQtyCategory() {
	}

	public String getFillingQtyCd() {
		return this.fillingQtyCd;
	}

	public void setFillingQtyCd(String fillingQtyCd) {
		this.fillingQtyCd = fillingQtyCd;
	}

	public String getFillingQtyAddedBy() {
		return this.fillingQtyAddedBy;
	}

	public void setFillingQtyAddedBy(String fillingQtyAddedBy) {
		this.fillingQtyAddedBy = fillingQtyAddedBy;
	}

	public Calendar getFillingQtyAddedDt() {
		return this.fillingQtyAddedDt;
	}

	public void setFillingQtyAddedDt(Calendar fillingQtyAddedDt) {
		this.fillingQtyAddedDt = fillingQtyAddedDt;
	}

	public String getFillingQtyMl() {
		return this.fillingQtyMl;
	}

	public void setFillingQtyMl(String fillingQtyMl) {
		this.fillingQtyMl = fillingQtyMl;
	}

}