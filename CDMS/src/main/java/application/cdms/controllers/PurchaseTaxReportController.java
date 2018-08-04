package application.cdms.controllers;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.jfoenix.controls.JFXDatePicker;

import application.cdms.component.data.handler.CellFactoryGenerator;
import application.cdms.component.data.handler.CustomeStringConverter;
import application.cdms.constants.ApplicationConstant;
import application.cdms.models.SearchBean;
import application.cdms.service.CDMSDataProviderService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class PurchaseTaxReportController implements Initializable, ScreenController<ScreenTransitionController<?>> {

	@SuppressWarnings("unused")
	private ScreenTransitionController<?> t;
	
	@FXML
	VBox centerContentVbox;
	
	@FXML
	private VBox inputVbox;

    @FXML
    private JFXDatePicker fromDtTextFld;

    @FXML
    private JFXDatePicker toDtTextFld;
    
    @FXML
	private ToggleGroup firmTg; 
	
	@FXML
    private TableView<String[]> purchaseTaxDtlsTbl;
    
    //private ProductService productService = ProductServiceImpl.getInstance();
    private CDMSDataProviderService dataProvider= CDMSDataProviderService.createCDMSDataProvider();
	
	private static Logger logger = Logger.getLogger(PurchaseTaxDetailController.class);
	

	@SuppressWarnings("rawtypes")
	@Override
	public void setScreenTransitionController(ScreenTransitionController t) {
		if (t instanceof ScreenTransitionController<?>) {
			this.t = (ScreenTransitionController) t;
		}
	}

	@Override
	public void setParams(Map<String,Object>  params) {
		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		fromDtTextFld.setConverter(new CustomeStringConverter());
		fromDtTextFld.setDayCellFactory(CellFactoryGenerator.getFutureDayDisableCellFactory());
		toDtTextFld.setConverter(new CustomeStringConverter());
		toDtTextFld.setDayCellFactory(CellFactoryGenerator.getFutureDayDisableCellFactory());
		int colLen=purchaseTaxDtlsTbl.getColumns().size();
		for (int i = 1; i < colLen; i++) {
			TableColumn tc=purchaseTaxDtlsTbl.getColumns().get(i);
            int colNo = i;
            tc.setCellValueFactory(new Callback<CellDataFeatures<String[], String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(CellDataFeatures<String[], String> p) {
                    return new SimpleStringProperty((p.getValue()[colNo-1]));
                }
            });
        }
	}
	Thread fetchPurchaseTaxDtlsTaskThread=null;
	@FXML
	void purchaseTaxDetail(ActionEvent event){
		ProgressIndicator progressIndicator = new ProgressIndicator(0);
		Task<ObservableList<String[]>> fetchPurchaseTaxDtlsTask = new Task<ObservableList<String[]>>() {
			@Override
			protected ObservableList<String[]> call() throws Exception{
				logger.info("purchaseSummeryLst :: bigin");
        		String fromDt = fromDtTextFld.getConverter().toString(fromDtTextFld.getValue());
        		String toDt = toDtTextFld.getConverter().toString(toDtTextFld.getValue());
        		logger.info("purchaseSummeryLst :: From Date ### "+fromDt);
        		logger.info("purchaseSummeryLst :: To Date ### "+toDt);
        		purchaseTaxDtlsTbl.getItems().clear();
        		SearchBean serchBean = new SearchBean();
        		serchBean.setFromDt(fromDt);
        		serchBean.setToDt(toDt);
        		ObservableList<String[]> billSummeryLt =null;
        		//billSummeryLt=productService.viewPurchaseTaxByDt(serchBean);
        		String groupByRadio = (String) firmTg.getSelectedToggle().getUserData();
        		if(ApplicationConstant.GROUPBY_WITHFIRM.equals(groupByRadio)){
        			billSummeryLt=dataProvider.viewPurchaseTaxByDtWithGroupByFirm(fromDt,toDt);
        		}
        		else{
        			billSummeryLt=dataProvider.viewPurchaseTaxByDt(fromDt,toDt);
        		}
                return billSummeryLt;
			}
		};
		progressIndicator.progressProperty().bind(fetchPurchaseTaxDtlsTask.progressProperty());
		purchaseTaxDtlsTbl.setPlaceholder(progressIndicator);
        fetchPurchaseTaxDtlsTask.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent arg0) {
				logger.info("fecthingPurchsLstTask :: completed");
				ObservableList<String[]> lst=fetchPurchaseTaxDtlsTask.getValue();
				purchaseTaxDtlsTbl.getItems().addAll(lst);
				progressIndicator.progressProperty().unbind();
			    progressIndicator.setProgress(1);
			}
		});
        fetchPurchaseTaxDtlsTask.setOnFailed( (e)->{
        	Throwable exception=fetchPurchaseTaxDtlsTask.getException();
        	progressIndicator.progressProperty().unbind();
        	progressIndicator.setProgress(1);
        	logger.fatal("fecthingPurchsLstTask :: exception ### "+exception.getMessage());
        	exception.printStackTrace();
        });
        if(fetchPurchaseTaxDtlsTaskThread==null || !fetchPurchaseTaxDtlsTaskThread.isAlive()){
        	fetchPurchaseTaxDtlsTaskThread = new Thread(fetchPurchaseTaxDtlsTask);
        	fetchPurchaseTaxDtlsTaskThread.start();
        }
        else{
        	logger.info("fecthingPurchsLstTaskThread :: task is already running");
        }
	}
}