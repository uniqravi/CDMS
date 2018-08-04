package application.cdms.controllers;

import java.util.Map;

public interface ScreenController<T>{
	
	void setScreenTransitionController(T t);
	
	void setParams(Map<String,Object> params);
	
}
