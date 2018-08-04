package application.cdms.models;


public class Vehicle {
	private String vehicleCd;

	private String vehicleAddedBy;

	private String vehicleAddedDt;

	private String vehicleNo;

	private String vehicleType;

	public String getVehicleCd() {
		return vehicleCd;
	}

	public void setVehicleCd(String vehicleCd) {
		this.vehicleCd = vehicleCd;
	}

	public String getVehicleAddedBy() {
		return vehicleAddedBy;
	}

	public void setVehicleAddedBy(String vehicleAddedBy) {
		this.vehicleAddedBy = vehicleAddedBy;
	}

	public String getVehicleAddedDt() {
		return vehicleAddedDt;
	}

	public void setVehicleAddedDt(String vehicleAddedDt) {
		this.vehicleAddedDt = vehicleAddedDt;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	
	public String toString(){
		return vehicleType+" ("+vehicleNo+")";
	}
}