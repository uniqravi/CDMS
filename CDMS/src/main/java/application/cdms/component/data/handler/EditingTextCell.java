package application.cdms.component.data.handler;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;

public class EditingTextCell<T,S> extends TableCell<T,S>{

	private TextField textField;
	
	private CustomeTableEditCallback custmCallback; 
	
	final Class<S> classType;
	
	public EditingTextCell(Class<S> classType){
		this.classType = classType;
	}
	
	public void setCustmCallback(CustomeTableEditCallback custmCallback) {
		this.custmCallback = custmCallback;
	}

	@Override
    public void startEdit() {
		System.out.println(custmCallback.isValidForEdit());
		if(custmCallback.isValidForEdit()){
			if (!isEmpty()) {
				super.startEdit();
				createTextField();
				setText(null);
				setGraphic(textField);
				textField.selectAll();
			}
		}
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setText(getItem()+"");
        setGraphic(null);
    }

    @Override
    public void updateItem(S item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
        	setText(empty ? null :item+"");
            setGraphic(null);
        } else {
            if (isEditing()) {
                if (textField != null) {
                    textField.setText(getString());
                }
                setText(null);
                setGraphic(textField);
            } else {
                setText(getString());
                setGraphic(null);
            }
        }
    }
    
    public void commitVal(String val){
    	Constructor<S> sCon = null;
    	S sIns=null;
		try {
			sCon = classType.getConstructor(String.class);
			sIns = sCon.newInstance(val);
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			System.out.println("Problem ### "+e.getMessage());
			e.printStackTrace();
		}
    	super.commitEdit(sIns);
    	custmCallback.postActionAfterEdit(sIns);
    	getTableView().refresh();
    }

    private void createTextField() {
        textField = new TextField(getString());
        textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
        
        textField.setOnAction((e) -> {
        	if(custmCallback.checkValidity(textField.getText())){
        		getTableRow().setStyle("-fx-background-color:transparent;");
        		commitVal(textField.getText());
        	}
        	else{
        		getTableRow().setStyle("-fx-background-color:#FFEBEE;");
        		setStyle("-fx-background-color:#FFEBEE;");
        	}
        });
        textField.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                if(custmCallback.checkValidity(textField.getText())){
                	getTableRow().setStyle("-fx-background-color:transparent;");
            		commitVal(textField.getText());
            	}
            	else{
            		getTableRow().setStyle("-fx-background-color:#FFEBEE;");
            		textField.setStyle("-fx-background-color:#FFEBEE;");
            	}
            }
        });
    }

  
    
    private String getString() {
    	System.out.println(getItem());
        return getItem() == null ? "" : (getItem()+"");
    }
}
