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
 * The persistent class for the packing_qty_category database table.
 * 
 */
@Entity
@Table(name="packing_qty_category",schema="cdms")
//@NamedQuery(name="PackingQtyCategory.findAll", query="SELECT p FROM PackingQtyCategory p")
public class PackingQtyCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(
			name="stringSeq",
			strategy="application.cdms.hibernate.utility.StringSequenceIdentifier",
			parameters={
					@Parameter(name="seqColumnNm",value="PACKINGQTY_SEQ")
			}
	)
	@GeneratedValue(generator="stringSeq")
	@Column(name="packing_qty_cd")
	private String packingQtyCd;

	@Column(name="packing_qty_added_by")
	private String packingQtyAddedBy;

	@Column(name="packing_qty_added_dt")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar packingQtyAddedDt;

	@Column(name="packing_quantity")
	private int packingQuantity;

	public PackingQtyCategory() {
	}

	public String getPackingQtyCd() {
		return this.packingQtyCd;
	}

	public void setPackingQtyCd(String packingQtyCd) {
		this.packingQtyCd = packingQtyCd;
	}

	public String getPackingQtyAddedBy() {
		return this.packingQtyAddedBy;
	}

	public void setPackingQtyAddedBy(String packingQtyAddedBy) {
		this.packingQtyAddedBy = packingQtyAddedBy;
	}

	public Calendar getPackingQtyAddedDt() {
		return this.packingQtyAddedDt;
	}

	public void setPackingQtyAddedDt(Calendar packingQtyAddedDt) {
		this.packingQtyAddedDt = packingQtyAddedDt;
	}

	public int getPackingQuantity() {
		return this.packingQuantity;
	}

	public void setPackingQuantity(int packingQuantity) {
		this.packingQuantity = packingQuantity;
	}

}