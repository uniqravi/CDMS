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
 * The persistent class for the route_map_dtl database table.
 * 
 */
@Entity
@Table(name="route_map_dtl",schema="cdms")
//@NamedQuery(name="RouteMapDtl.findAll", query="SELECT r FROM RouteMapDtl r")
public class RouteMapDtl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="route_cd")
	private String routeCd;

	@Column(name="route_added_by")
	private String routeAddedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="route_added_dt", nullable=false)
	private Calendar routeAddedDt;

	@Column(name="route_last_updt_by")
	private String routeLastUpdtBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="route_last_updt_dt")
	private Calendar routeLastUpdtDt;

	@Column(name="route_name")
	private String routeName;

	public RouteMapDtl() {
	}

	public String getRouteCd() {
		return routeCd;
	}

	public void setRouteCd(String routeCd) {
		this.routeCd = routeCd;
	}

	public String getRouteAddedBy() {
		return routeAddedBy;
	}

	public void setRouteAddedBy(String routeAddedBy) {
		this.routeAddedBy = routeAddedBy;
	}

	public Calendar getRouteAddedDt() {
		return routeAddedDt;
	}

	public void setRouteAddedDt(Calendar routeAddedDt) {
		this.routeAddedDt = routeAddedDt;
	}

	public String getRouteLastUpdtBy() {
		return routeLastUpdtBy;
	}

	public void setRouteLastUpdtBy(String routeLastUpdtBy) {
		this.routeLastUpdtBy = routeLastUpdtBy;
	}

	public Calendar getRouteLastUpdtDt() {
		return routeLastUpdtDt;
	}

	public void setRouteLastUpdtDt(Calendar routeLastUpdtDt) {
		this.routeLastUpdtDt = routeLastUpdtDt;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

}