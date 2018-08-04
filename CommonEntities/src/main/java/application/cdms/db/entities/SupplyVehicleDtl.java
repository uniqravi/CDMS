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
 * The persistent class for the supply_vehicle_dtl database table.
 * 
 */
@Entity
@Table(name="supply_vehicle_dtl",schema="cdms")
//@NamedQuery(name="SupplyVehicleDtl.findAll", query="SELECT s FROM SupplyVehicleDtl s")
public class SupplyVehicleDtl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="vehicle_cd", unique=true, nullable=false, length=10)
	private String vehicleCd;

	@Column(name="vehicle_added_by")
	private String vehicleAddedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="vehicle_added_dt")
	private Calendar vehicleAddedDt;

	@Column(name="vehicle_no")
	private String vehicleNo;

	@Column(name="vehicle_type")
	private String vehicleType;

	public SupplyVehicleDtl() {
	}

	public String getVehicleCd() {
		return this.vehicleCd;
	}

	public void setVehicleCd(String vehicleCd) {
		this.vehicleCd = vehicleCd;
	}

	public String getVehicleAddedBy() {
		return this.vehicleAddedBy;
	}

	public void setVehicleAddedBy(String vehicleAddedBy) {
		this.vehicleAddedBy = vehicleAddedBy;
	}

	public Calendar getVehicleAddedDt() {
		return this.vehicleAddedDt;
	}

	public void setVehicleAddedDt(Calendar vehicleAddedDt) {
		this.vehicleAddedDt = vehicleAddedDt;
	}

	public String getVehicleNo() {
		return this.vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public String getVehicleType() {
		return this.vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

}