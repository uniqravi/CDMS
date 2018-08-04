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
 * The persistent class for the packing_name_category database table.
 * 
 */
@Entity
@Table(name="packing_name_category",schema="cdms")
//@NamedQuery(name="PackingNameCategory.findAll", query="SELECT p FROM PackingNameCategory p")
public class PackingNameCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(
			name="stringSeq",
			strategy="application.cdms.hibernate.utility.StringSequenceIdentifier",
			parameters={
					@Parameter(name="seqColumnNm",value="PACKINGTYPE_SEQ")
			}
	)
	@GeneratedValue(generator="stringSeq")
	@Column(name="packing_name_cd")
	private String packingNameCd;

	@Column(name="packing_name")
	private String packingName;

	@Column(name="paking_added__dt")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar pakingAddedDt;

	@Column(name="paking_added_by")
	private String pakingAddedBy;

	public PackingNameCategory() {
	}

	public String getPackingNameCd() {
		return this.packingNameCd;
	}

	public void setPackingNameCd(String packingNameCd) {
		this.packingNameCd = packingNameCd;
	}

	public String getPackingName() {
		return this.packingName;
	}

	public void setPackingName(String packingName) {
		this.packingName = packingName;
	}

	public Calendar getPakingAddedDt() {
		return this.pakingAddedDt;
	}

	public void setPakingAddedDt(Calendar pakingAddedDt) {
		this.pakingAddedDt = pakingAddedDt;
	}

	public String getPakingAddedBy() {
		return this.pakingAddedBy;
	}

	public void setPakingAddedBy(String pakingAddedBy) {
		this.pakingAddedBy = pakingAddedBy;
	}

}