package application.cdms.component.data.handler;

import application.cdms.controllers.ScreenTransitionController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

public class CheckerForTextField implements ChangeListener<Boolean>{

	private final TextField textField;
	
	private final String regex; 
	
	private ScreenTransitionController<?> t;
	
	private final String customMsg;
	
	final ContextMenu validatorTooltip;
	
	public CheckerForTextField(TextField textField,String regex,ScreenTransitionController<?> t,String customMsg){
		this.textField=textField;
		this.regex=regex;
		this.t=t;
		this.customMsg=customMsg;
		validatorTooltip=null;
	}
	
	public CheckerForTextField(TextField textField,String regex,String customMsg){
		this.textField=textField;
		this.regex=regex;
		this.customMsg=customMsg;
		t=null;
		validatorTooltip=new ContextMenu();
		validatorTooltip.setAutoHide(true);
		validatorTooltip.setStyle("-fx-background-color:red;");
	}
	
	@Override
	public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		if (!newValue) { //when focus lost
				String str =textField.getText();
				if(str!=null && !str.trim().equals("") && !str.matches(regex)){
					textField.setText("");
					textField.setStyle("-fx-border-color: red;-fx-border-width: 0.0px 0px 4px 0px;-fx-border-style: dashed;");
					if(t!=null){
						t.getErrorNotification(customMsg);
					}
					else{
						validatorTooltip.getItems().clear();
						validatorTooltip.getItems().add(new MenuItem(customMsg));
						validatorTooltip.show(textField, Side.RIGHT, 10, 0);
					}
				}
				else{
//					validatorTooltip.getItems().clear();
					textField.setStyle("-fx-border-width: 0.0px 0px 0px 0px");
				}
			}
        }
}