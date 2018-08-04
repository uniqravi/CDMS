package application.cdms.component.data.handler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.sun.javafx.scene.control.skin.TableViewSkin;

import javafx.collections.ListChangeListener;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class CustomTableSkin {
	private static Method columnToFitMethod;

    static {
        try {
            columnToFitMethod = TableViewSkin.class.getDeclaredMethod("resizeColumnToFitContent", TableColumn.class, int.class);
            columnToFitMethod.setAccessible(true);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    
    @SuppressWarnings("unchecked")
	public static void autoFitTable(@SuppressWarnings("rawtypes") TableView tableView) {
        tableView.getItems().addListener(new ListChangeListener<Object>() {
            @Override
            public void onChanged(Change<?> c) {
                for (Object column : tableView.getColumns()) {
                    try {
                        columnToFitMethod.invoke(tableView.getSkin(), column, -1);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
