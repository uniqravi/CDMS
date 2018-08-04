package application.cdms.component.data.handler;

import java.util.Arrays;

import javafx.beans.NamedArg;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.util.Callback;

public class NestedTreePropertyValueFactory<S,T> implements Callback<TreeTableColumn.CellDataFeatures<S, T>, ObservableValue<T>>{
	
	private String[] steps;
	
	private TreeItemPropertyValueFactory<S, T> firstStepSelector;
	
	public NestedTreePropertyValueFactory(){
		
	}
	
	public NestedTreePropertyValueFactory(@NamedArg("property") String property){
		String[] properties = property.split("\\.");
		firstStepSelector=new TreeItemPropertyValueFactory<>(properties[0]);
		this.steps = Arrays.copyOfRange(properties, 1, properties.length);
	}
	
	@Override
	public ObservableValue<T> call(TreeTableColumn.CellDataFeatures<S, T> param) {
		if(param.getValue()!=null){
			return Bindings.select(firstStepSelector.call(param), steps);
		}
		return null;
	}
}
