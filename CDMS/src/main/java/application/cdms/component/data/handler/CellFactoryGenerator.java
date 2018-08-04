package application.cdms.component.data.handler;

import java.time.LocalDate;

import javafx.scene.Node;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

@SuppressWarnings("rawtypes")
public class CellFactoryGenerator {

	public static  Callback<DatePicker, DateCell> getFutureDayDisableCellFactory() {
        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        // Disable Monday, Tueday, Wednesday.
                        /*if (item.getDayOfWeek() == DayOfWeek.MONDAY //
                                || item.getDayOfWeek() == DayOfWeek.TUESDAY //
                                || item.getDayOfWeek() == DayOfWeek.WEDNESDAY) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }*/
                        if (item.isAfter(LocalDate.now())) {
                        	setDisable(true);
                        	//setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                };
            }
        };
        return dayCellFactory;
    }
	
	@SuppressWarnings("unchecked")
	public static Callback<TableColumn, TableCell> getHiperLinkCellFactory(CellFactoryGenerator.AnonymousTaskInterface task){
		final Callback<TableColumn, TableCell> hiperLinkFactory=new Callback<TableColumn, TableCell>() {
			@Override
			public TableCell call(TableColumn param) {
				return new TableCell() {
					
					@Override
					public void updateItem(Object item, boolean empty) {
						super.updateItem(item, empty);
						int currentIndex = indexProperty().getValue() < 0 ? 0 : indexProperty().getValue();//getUpdtPaymentHyper(currentIndex)
						setGraphic(empty ? null:task.performTask(currentIndex));
					}
				};
			}
		};
		return hiperLinkFactory;
	}
	
	@SuppressWarnings("unchecked")
	public static Callback<TableColumn, TableCell> getSrNumberCellFactory(){
		final Callback<TableColumn, TableCell> srNoCellCallBack=new Callback<TableColumn, TableCell>() {
			@Override
			public TableCell call(TableColumn param) {
				return new TableCell() {
					@Override
					public void updateItem(Object item, boolean empty) {
						super.updateItem(item, empty);
						setText(empty ? null : getIndex() + 1 + "");
					}
				};
			}
		};
		return srNoCellCallBack;
	}
	
	@SuppressWarnings("unchecked")
	public static Callback<TableColumn, TableCell> getHiperLink2CellFactory(CellFactoryGenerator.AnonymousTaskInterface task){
		final Callback<TableColumn, TableCell> hiperLinkFactory=new Callback<TableColumn, TableCell>() {
			@Override
			public TableCell call(TableColumn param) {
				return new TableCell() {
					
					@Override
					public void updateItem(Object item, boolean empty) {
						super.updateItem(item, empty);
						int currentIndex = indexProperty().getValue() < 0 ? 0 : indexProperty().getValue();//getUpdtPaymentHyper(currentIndex)
						setGraphic(empty ? null:task.performTask(currentIndex));
					}
				};
			}
		};
		return hiperLinkFactory;
	}
	
	//@SuppressWarnings("unchecked")
	public static <T,V> Callback<TableColumn<T,V>, TableCell<T,V>> getTextCellFactory(CellFactoryGenerator.SetTextInterface st){
		final Callback<TableColumn<T,V>, TableCell<T,V>> textCellFactory=new Callback<TableColumn<T,V>, TableCell<T,V>>() {
			@Override
			public TableCell<T,V> call(TableColumn<T,V> param) {
				return new TableCell<T,V>() {
					
					@Override
					public void updateItem(V item, boolean empty) {
						super.updateItem(item, empty);
						setText(empty ? null : st.setCellText(item));
					}
				};
			}
		};
		return textCellFactory;
	}
	
	public static interface AnonymousTaskInterface {
		public Node performTask(int index );
	}
	
	public static interface SetTextInterface{
		public  String setCellText(Object t);
	}
}
