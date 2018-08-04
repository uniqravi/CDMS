package application.cdms.models;

import java.util.List;

public class Scheme {
	
	private String shemeLabel;
	
	private List<SchemeProduct> schemePrdcts;

	public List<SchemeProduct> getSchemePrdcts() {
		return schemePrdcts;
	}
	public void setSchemePrdcts(List<SchemeProduct> schemePrdcts) {
		this.schemePrdcts = schemePrdcts;
	}
	public String getShemeLabel() {
		return shemeLabel;
	}
	public void setShemeLabel(String shemeLabel) {
		this.shemeLabel = shemeLabel;
	}
}