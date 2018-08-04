package application.cdms.models;

public class Territory {

	private String territoryCode;
	private String territoryName;
	private double territoryDistance;
	public String getTerritoryCode() {
		return territoryCode;
	}
	public void setTerritoryCode(String territoryCode) {
		this.territoryCode = territoryCode;
	}
	public String getTerritoryName() {
		return territoryName;
	}
	public void setTerritoryName(String territoryName) {
		this.territoryName = territoryName;
	}
	public double getTerritoryDistance() {
		return territoryDistance;
	}
	public void setTerritoryDistance(double territoryDistance) {
		this.territoryDistance = territoryDistance;
	}
	@Override
	public String toString() {
		return territoryName+" [ " +territoryCode +" ]";
	}
	
}