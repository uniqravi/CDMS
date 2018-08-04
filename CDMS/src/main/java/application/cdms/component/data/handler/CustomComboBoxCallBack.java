package application.cdms.component.data.handler;

import javafx.scene.control.ComboBox;

public interface CustomComboBoxCallBack {

	<T> void  setItem(ComboBox<T> comboBox);
}
