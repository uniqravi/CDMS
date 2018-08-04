package application.cdms.db.entities;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the territory_area_dtl database table.
 * 
 */
@Entity
@Table(name="territory_area_dtl",schema="cdms")
//@NamedQuery(name="TerritoryAreaDtl.findAll", query="SELECT t FROM TerritoryAreaDtl t")
public class TerritoryAreaDtl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="territory_cd", unique=true, nullable=false, length=10)
	private String territoryCd;

	@Column(name="territory_added_by")
	private String territoryAddedBy;

	@Column(name="territory_added_dt", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar territoryAddedDt;

	@Column(name="territory_distance", nullable=false, precision=10, scale=3)
	private double territoryDistance;

	@Column(name="territory_name")
	private String territoryName;

	public TerritoryAreaDtl() {
	}

	public String getTerritoryCd() {
		return territoryCd;
	}

	public void setTerritoryCd(String territoryCd) {
		this.territoryCd = territoryCd;
	}

	public String getTerritoryAddedBy() {
		return territoryAddedBy;
	}

	public void setTerritoryAddedBy(String territoryAddedBy) {
		this.territoryAddedBy = territoryAddedBy;
	}

	public Calendar getTerritoryAddedDt() {
		return territoryAddedDt;
	}

	public void setTerritoryAddedDt(Calendar territoryAddedDt) {
		this.territoryAddedDt = territoryAddedDt;
	}

	public double getTerritoryDistance() {
		return territoryDistance;
	}

	public void setTerritoryDistance(double territoryDistance) {
		this.territoryDistance = territoryDistance;
	}

	public String getTerritoryName() {
		return territoryName;
	}

	public void setTerritoryName(String territoryName) {
		this.territoryName = territoryName;
	}

}