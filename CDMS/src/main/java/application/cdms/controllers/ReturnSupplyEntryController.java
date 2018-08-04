package application.cdms.controllers;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

@SuppressWarnings("rawtypes")
public class ReturnSupplyEntryController implements Initializable, ScreenController{

	//private ScreenTransitionController t;
	
	@Override
	public void setScreenTransitionController(Object obj) {
		if (obj instanceof ScreenTransitionController<?>) {
			//this.t = (ScreenTransitionController) obj;
		}
	}

	@Override
	public void setParams(Map params) {
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

}
