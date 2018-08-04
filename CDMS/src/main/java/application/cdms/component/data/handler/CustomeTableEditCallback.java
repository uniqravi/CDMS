package application.cdms.component.data.handler;

public interface CustomeTableEditCallback {

	boolean checkValidity(String newVal);
	
	String getValue();
	
	boolean isValidForEdit();
	
	void postActionAfterEdit(Object obj);
}
