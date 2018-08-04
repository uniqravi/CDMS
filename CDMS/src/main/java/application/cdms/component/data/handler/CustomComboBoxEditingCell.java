package application.cdms.component.data.handler;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;

public class CustomComboBoxEditingCell<T,S> extends TableCell<T,S> {
	private ComboBox<S> comboBox;
	
	CustomComboBoxCallBack customComboBoxCallBack;

    public CustomComboBoxEditingCell(CustomComboBoxCallBack customComboBoxCallBack) {
    	this.customComboBoxCallBack=customComboBoxCallBack;
    	createComboBox();
    }
    
/*
    @Override
    public void startEdit() {
        if (!isEmpty()) {
            super.startEdit();
            setText(null);
            setGraphic(comboBox);
        }
    }
*/
    /*
    @Override
    public void cancelEdit() {
        super.cancelEdit();

        setText(getTyp()==null?null:getTyp().toString());
        setGraphic(comboBox);
    }
*/
    @Override
    public void updateItem(S item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            if (isEditing()) {
                if (comboBox != null) {
                    comboBox.setValue(getItem());
                }
                setText(null);//getTyp()==null?null:getTyp().toString());
                setGraphic(comboBox);
            } else {
               setText(null);//getTyp()==null?null:getTyp().toString());
               setGraphic(comboBox);
            }
        }
    }

    private void createComboBox() {
        comboBox = new ComboBox<S>();
        customComboBoxCallBack.setItem(comboBox);
        //comboBoxConverter(comboBox);
        comboBox.valueProperty().set(getItem());
        comboBox.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
        comboBox.setOnMouseExited((e) -> {
            System.out.println("Committed: " + comboBox.getSelectionModel().getSelectedItem());
            commitEdit(comboBox.getSelectionModel().getSelectedItem());
        });
       /* comboBox.setmou((e) -> {
            System.out.println("Committed: " + comboBox.getSelectionModel().getSelectedItem());
            commitEdit(comboBox.getSelectionModel().getSelectedItem());
        });*/
    }
/*
    private void comboBoxConverter(ComboBox<S> comboBox) {
        // Define rendering of the list of values in ComboBox drop down. 
        comboBox.setCellFactory((c) -> {
            return new ListCell<S>() {
                @Override
                protected void updateItem(S item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                    	System.out.println("Empty -->"+item);
                        setText(null);
                    } else {
                    	System.out.println("Else -->"+item);
                        setText(item==null?null:item.toString());
                        if(isSelected()){
                        	commitEdit(comboBox.getSelectionModel().getSelectedItem());
                        }
                    }
                }
            };
        });
    }*/
/*
    private S getTyp() {
        return getItem() == null ?null: ;
    }
*/
}
