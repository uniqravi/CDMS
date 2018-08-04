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
 * The persistent class for the flavour_category database table.
 * 
 */
@Entity
@Table(name="flavour_category",schema="cdms")
//@NamedQuery(name="FlavourCategory.findAll", query="SELECT f FROM FlavourCategory f")
public class FlavourCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(
			name="stringSeq",
			strategy="application.cdms.hibernate.utility.StringSequenceIdentifier",
			parameters={
					@Parameter(name="seqColumnNm",value="FLAVOUR_SEQ")
			}
	)
	@GeneratedValue(generator="stringSeq")
	@Column(name="flavr_cd")
	private String flavrCd;

	@Column(name="falvr_added_by")
	private String falvrAddedBy;

	@Column(name="flavr_added_dt")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar flavrAddedDt;

	@Column(name="flavr_name")
	private String flavrName;

	public FlavourCategory() {
	}

	public String getFlavrCd() {
		return this.flavrCd;
	}

	public void setFlavrCd(String flavrCd) {
		this.flavrCd = flavrCd;
	}

	public String getFalvrAddedBy() {
		return this.falvrAddedBy;
	}

	public void setFalvrAddedBy(String falvrAddedBy) {
		this.falvrAddedBy = falvrAddedBy;
	}

	public Calendar getFlavrAddedDt() {
		return this.flavrAddedDt;
	}

	public void setFlavrAddedDt(Calendar flavrAddedDt) {
		this.flavrAddedDt = flavrAddedDt;
	}

	public String getFlavrName() {
		return this.flavrName;
	}

	public void setFlavrName(String flavrName) {
		this.flavrName = flavrName;
	}

}