package application.cdms.models;

public class RouteDtl {
	
	private String routeCd;
	
	private String routeName;

	private String routeAddedBy;

	private String routeAddedDt;

	private String routeLastUpdtBy;

	private String routeLastUpdtDt;

	public String getRouteCd() {
		return routeCd;
	}

	public void setRouteCd(String routeCd) {
		this.routeCd = routeCd;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public String getRouteAddedBy() {
		return routeAddedBy;
	}

	public void setRouteAddedBy(String routeAddedBy) {
		this.routeAddedBy = routeAddedBy;
	}

	public String getRouteAddedDt() {
		return routeAddedDt;
	}

	public void setRouteAddedDt(String routeAddedDt) {
		this.routeAddedDt = routeAddedDt;
	}

	public String getRouteLastUpdtBy() {
		return routeLastUpdtBy;
	}

	public void setRouteLastUpdtBy(String routeLastUpdtBy) {
		this.routeLastUpdtBy = routeLastUpdtBy;
	}

	public String getRouteLastUpdtDt() {
		return routeLastUpdtDt;
	}

	public void setRouteLastUpdtDt(String routeLastUpdtDt) {
		this.routeLastUpdtDt = routeLastUpdtDt;
	}

	@Override
	public String toString() {
		return  routeName ;
	}
	
}
