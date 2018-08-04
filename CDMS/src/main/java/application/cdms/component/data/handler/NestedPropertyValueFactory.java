package application.cdms.component.data.handler;

import java.util.Arrays;

import javafx.beans.NamedArg;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class NestedPropertyValueFactory<S,T> implements Callback<TableColumn.CellDataFeatures<S, T>, ObservableValue<T>>{

	private final String[] steps;
	
	private final PropertyValueFactory<S,T> firstStepSelector;
	
	public NestedPropertyValueFactory(@NamedArg("property") String property) {
        String[] properties = property.split("\\.");
        firstStepSelector = new PropertyValueFactory<>(properties[0]);
        this.steps = Arrays.copyOfRange(properties, 1, properties.length);
    }
	
	@Override
    public ObservableValue<T> call(TableColumn.CellDataFeatures<S, T> param) {
		
		if(param.getValue()!=null){
			return Bindings.select(firstStepSelector.call(param), steps);
		}
		return null;
	}
}

