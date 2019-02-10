package application.cdms.controllers;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.jfoenix.controls.JFXDatePicker;

import application.cdms.component.data.handler.CellFactoryGenerator;
import application.cdms.component.data.handler.CustomeStringConverter;
import application.cdms.models.PurchaseDtls;
import application.cdms.models.PurchaseProductDtl;
import application.cdms.models.SearchBean;
import application.cdms.service.CDMSDataProviderService;
import application.cdms.service.ProductService;
import application.cdms.service.impl.ProductServiceImpl;
import application.cdms.transformer.Initialization;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

@SuppressWarnings("rawtypes")
public class PurchaseTaxDetailController implements Initializable, ScreenController {

	//private ScreenTransitionController t;
	
	@FXML
	VBox centerContentVbox;
	
	@FXML
	private VBox inputVbox;

    @FXML
    private JFXDatePicker fromDtTextFld;

    @FXML
    private JFXDatePicker toDtTextFld;
	
	@FXML
    private TableView<String[]> purchaseSummeryLstTable;

    @FXML
    private TableColumn<String[],String> challDt;

    @FXML
    private TableColumn<String[],String> invoiceNum;

    @FXML
    private TableColumn<String[],String> challanNum;

    @FXML
    private TableColumn<String[],String> totalLoad;

    @FXML
    private TableColumn<String[],String> totalGlass;

    @FXML
    private TableColumn<String[],String> returnGlass;

    @FXML
    private TableColumn<String[],String> cgstSum;

    @FXML
    private TableColumn<String[],String> igstSum;

    @FXML
    private TableColumn<String[],String> sgstSum;

    @FXML
    private TableColumn<String[],String> cessSum;
    
    //for tax components details
    @FXML
    private TableView<String[]> challanTaxComponentsTable;

    /*
    @FXML
    private TableColumn<String[],String> hsnCdCol;
    
    @FXML
    private TableColumn<String[],String> taxDescrptnCol;

    @FXML
    private TableColumn<String[],String> sumCgstCol;

    @FXML
    private TableColumn<String[],String> sumSgstCol;

    @FXML
    private TableColumn<String[],String> sumIgstCol;

    @FXML
    private TableColumn<String[],String> sumCessCol;
    */
    
    @FXML
    private TableView<String[]> breakTable;
    
    @FXML
    private HBox taxCompnttableHbox;
    
    @FXML
    private VBox billInfoVbox;
    
    @FXML
    private VBox paymentInfoVbox;
    
    @FXML
    private TableView<PurchaseProductDtl> prdctViewTable;
    
    ProductService productService = ProductServiceImpl.getInstance();
    private CDMSDataProviderService dataProvider=CDMSDataProviderService.createCDMSDataProvider();
	
	private static Logger logger = Logger.getLogger(PurchaseTaxDetailController.class);
	
	@Override
	public void setScreenTransitionController(Object obj) {
		if (obj instanceof ScreenTransitionController<?>) {
			//this.t = (ScreenTransitionController) obj;
		}
	}

	@Override
	public void setParams(Map params) {
		try {
			this.afterLoadingPageAction();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void afterLoadingPageAction() throws Exception {
		ObservableList<String[]> billSummeryLt=dataProvider.viewPurchaseSummeryWithoutCriteria();
		purchaseSummeryLstTable.getItems().addAll(billSummeryLt);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//inputs
		
		fromDtTextFld.setConverter(new CustomeStringConverter());
		fromDtTextFld.setDayCellFactory(CellFactoryGenerator.getFutureDayDisableCellFactory());
		toDtTextFld.setConverter(new CustomeStringConverter());
		toDtTextFld.setDayCellFactory(CellFactoryGenerator.getFutureDayDisableCellFactory());
		//end input
		//taxCompnttableHbox.
		
		int colLen = purchaseSummeryLstTable.getColumns().size();
		int taxComColLen = challanTaxComponentsTable.getColumns().size();
		int breakCol = breakTable.getColumns().size();
		for (int i = 0; i < colLen; i++) {
            //TableColumn tc = new TableColumn(staffArray[0][i]);
			TableColumn tc=purchaseSummeryLstTable.getColumns().get(i);
            int colNo = i;
            tc.setCellValueFactory(new Callback<CellDataFeatures<String[], String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(CellDataFeatures<String[], String> p) {
                    return new SimpleStringProperty((p.getValue()[colNo]));
                }
            });
        }
		invoiceNum.setCellFactory(new Callback<TableColumn<String[],String>, TableCell<String[],String>>() {
			
			@Override
			public TableCell<String[], String> call(TableColumn<String[], String> param) {
				
				return new TableCell<String[],String>(){
					@Override
					public void updateItem(String str, boolean empty) {
						super.updateItem(str, empty);
						Hyperlink link = new Hyperlink(str);
						link.setOnAction((e) -> {
							getChallanTaxComponent(str).start();
							getChallanDetail(str).start();
							viewPurchaseBreakDtl(str).start();
							
						});
						setGraphic(link);
					}
				};
			}
		});
		purchaseSummeryLstTable.setFixedCellSize(34);
		purchaseSummeryLstTable.prefHeightProperty().bind(purchaseSummeryLstTable.fixedCellSizeProperty().multiply(Bindings.size(purchaseSummeryLstTable.getItems()).add(2)));
		//fot tax component table
		for (int i = 0; i < taxComColLen; i++) {
            //TableColumn tc = new TableColumn(staffArray[0][i]);
			TableColumn tc=challanTaxComponentsTable.getColumns().get(i);
            int colNo = i;
            tc.setCellValueFactory(new Callback<CellDataFeatures<String[], String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(CellDataFeatures<String[], String> p) {
                    return new SimpleStringProperty((p.getValue()[colNo]));
                }
            });
        }
		for (int i = 0; i < breakCol; i++) {
            //TableColumn tc = new TableColumn(staffArray[0][i]);
			TableColumn tc=breakTable.getColumns().get(i);
            int colNo = i;
            tc.setCellValueFactory(new Callback<CellDataFeatures<String[], String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(CellDataFeatures<String[], String> p) {
                    return new SimpleStringProperty((p.getValue()[colNo]));
                }
            });
        }
		
		challanTaxComponentsTable.setFixedCellSize(34);
		challanTaxComponentsTable.prefHeightProperty().bind(challanTaxComponentsTable.fixedCellSizeProperty().multiply(Bindings.size(challanTaxComponentsTable.getItems()).add(2)));
		challanTaxComponentsTable.minHeightProperty().bind(challanTaxComponentsTable.prefHeightProperty());
		challanTaxComponentsTable.maxHeightProperty().bind(challanTaxComponentsTable.prefHeightProperty());
		//product detail view 
		/*prdctViewTable.prefHeightProperty().bind(prdctViewTable.fixedCellSizeProperty().multiply(Bindings.size(prdctViewTable.getItems()).add(2)));
		prdctViewTable.minHeightProperty().bind(prdctViewTable.prefHeightProperty());
		prdctViewTable.maxHeightProperty().bind(prdctViewTable.prefHeightProperty());*/
	}
	Thread fecthingPurchsLstTaskThread=null;
	@FXML
	void purchaseSummeryLst(ActionEvent event){
		ProgressIndicator progressIndicator = new ProgressIndicator(0);
		Task<ObservableList<String[]>> fecthingPurchsLstTask = new Task<ObservableList<String[]>>() {
            @Override
            protected ObservableList<String[]> call() throws Exception {
            	logger.info("purchaseSummeryLst :: bigin");
        		String fromDt = fromDtTextFld.getConverter().toString(fromDtTextFld.getValue());
        		String toDt = toDtTextFld.getConverter().toString(toDtTextFld.getValue());
        		logger.info("purchaseSummeryLst :: From Date ### "+fromDt);
        		logger.info("purchaseSummeryLst :: To Date ### "+toDt);
        		purchaseSummeryLstTable.getItems().clear();
        		SearchBean serchBean = new SearchBean();
        		serchBean.setFromDt(fromDt);
        		serchBean.setToDt(toDt);
        		ObservableList<String[]> billSummeryLt =null;
        		billSummeryLt=dataProvider.viewPurchaseSummeryByDt(serchBean.getFromDt(),serchBean.getToDt());
        		//billSummeryLt=productService.viewPurchaseSummeryByDt(serchBean);
                return billSummeryLt;
            }
        };
        progressIndicator.progressProperty().bind(fecthingPurchsLstTask.progressProperty());
        purchaseSummeryLstTable.setPlaceholder(progressIndicator);
        fecthingPurchsLstTask.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent arg0) {
				logger.info("fecthingPurchsLstTask :: completed");
				ObservableList<String[]> lst=fecthingPurchsLstTask.getValue();
				purchaseSummeryLstTable.getItems().addAll(lst);
				progressIndicator.progressProperty().unbind();
			    progressIndicator.setProgress(1);
			}
		});
        fecthingPurchsLstTask.setOnFailed( (e)->{
        	Throwable exception=fecthingPurchsLstTask.getException();
        	progressIndicator.progressProperty().unbind();
        	progressIndicator.setProgress(1);
        	logger.fatal("fecthingPurchsLstTask :: exception ### "+exception.getMessage());
        	exception.printStackTrace();
        });
        if(fecthingPurchsLstTaskThread==null || !fecthingPurchsLstTaskThread.isAlive()){
        	fecthingPurchsLstTaskThread = new Thread(fecthingPurchsLstTask);
        	fecthingPurchsLstTaskThread.start();
        }
        else{
        	logger.info("fecthingPurchsLstTaskThread :: task is already running");
        }
	 }
	
	
	public Thread getChallanDetail(String invoice){
		ProgressIndicator progressIndicator = new ProgressIndicator(0);
		Task<PurchaseDtls> getBillDetailTask=new Task<PurchaseDtls>(){

			@Override
			protected PurchaseDtls call() throws Exception {
				logger.info("getBillDetailTask :: start with invoice ### "+invoice);
				PurchaseDtls purchaseDtls=productService.getChallanDetailByInvoice(invoice,Initialization.EAGER);
				return purchaseDtls;
			}
			
		};
		progressIndicator.progressProperty().bind(getBillDetailTask.progressProperty());
		billInfoVbox.getChildren().add(progressIndicator);
		paymentInfoVbox.getChildren().add(progressIndicator);
		getBillDetailTask.setOnSucceeded( (e) ->{
			logger.info("getBillDetailTask :: completed");
			PurchaseDtls purchaseDtls=getBillDetailTask.getValue();
			try {
				setViewBillPaymentInfo(purchaseDtls);
				setViewPurchaseProductTable(purchaseDtls);
				progressIndicator.progressProperty().unbind();
			    progressIndicator.setProgress(1);
			    billInfoVbox.getChildren().remove(progressIndicator);
			    paymentInfoVbox.getChildren().remove(progressIndicator);
			} catch (Exception e1) {
				logger.fatal("getBillDetailTask :: exception in while setting view ### "+e1.getMessage());
				e1.printStackTrace();
			}
			
		});
		getBillDetailTask.setOnFailed((e) ->{
			Throwable th=getBillDetailTask.getException();
			billInfoVbox.getChildren().remove(progressIndicator);
		    paymentInfoVbox.getChildren().remove(progressIndicator);
			logger.fatal("getBillDetailTask :: task failed :: why ### "+th.getMessage());
			th.printStackTrace();
		});
		return new Thread(getBillDetailTask);
	}
	
	public Thread getChallanTaxComponent(String invoiceNum){
		
		ProgressIndicator progressIndicator = new ProgressIndicator(0);
		Task<ObservableList<String[]>> fectchTaxCompntTask = new Task<ObservableList<String[]>>(){
			 @Override
	            protected ObservableList<String[]> call() throws Exception {
	            	logger.info("fectchTaxCompntTask :: start");
	    			logger.info("fectchTaxCompntTask :: invoice number ### "+invoiceNum);
	    			challanTaxComponentsTable.getItems().clear();
	    			ObservableList<String[]> invoicetaxComponents=dataProvider.viewPurchaseTaxCompntByInvoice(invoiceNum);
	    			//ObservableList<String[]> invoicetaxComponents=productService.viewPurchaseTaxCompntByInvoice(invoiceNum);
	    			return invoicetaxComponents;
	            }
		};
		progressIndicator.progressProperty().bind(fectchTaxCompntTask.progressProperty());
		challanTaxComponentsTable.setPlaceholder(progressIndicator);
		fectchTaxCompntTask.setOnSucceeded( (e) ->{
			logger.info("fectchTaxCompntTask :: completed");
			ObservableList<String[]> lst=fectchTaxCompntTask.getValue();
			challanTaxComponentsTable.getItems().setAll(lst);
			progressIndicator.progressProperty().unbind();
		    progressIndicator.setProgress(1);
		});
		fectchTaxCompntTask.setOnFailed( (e) -> {
			logger.error("fectchTaxCompntTask :: failed");
			Throwable exception=fectchTaxCompntTask.getException();
			progressIndicator.progressProperty().unbind();
        	progressIndicator.setProgress(1);
        	logger.fatal("fectchTaxCompntTask :: exception ### "+exception.getMessage());
        	exception.printStackTrace();
		});
		return new Thread(fectchTaxCompntTask);
	}
	
	public void setViewBillPaymentInfo(PurchaseDtls purchaseDtls) throws IllegalArgumentException, IllegalAccessException{
		Class cls=purchaseDtls.getClass();
	    Field[] flds = cls.getDeclaredFields();
	    	 for(Field fld : flds){
	    		 fld.setAccessible(true);
	    		 Object value=fld.get(purchaseDtls);
	    		 if(value instanceof String){
	    			 Label label=(Label) billInfoVbox.lookup("#"+fld.getName());
	    			 if(label!=null){
	    				 label.setText(value.toString());
	    			 }
	    			 else{
	    				 Label label1=(Label) paymentInfoVbox.lookup("#"+fld.getName());
	    				 if(label1!=null)
	    					 label1.setText(value.toString());
	    			 }
	    		 }
	    		 if(value instanceof Double){
	    			 Label label=(Label) billInfoVbox.lookup("#"+fld.getName());
	    			 if(label!=null){
	    				 label.setText(value.toString());
	    			 }
	    			 else{
	    				 Label label1=(Label) paymentInfoVbox.lookup("#"+fld.getName());
	    				 if(label1!=null)
	    					 label1.setText(value.toString());
	    			 }
	    		 }
	    		 if(value instanceof Long){
	    			 Label label=(Label) billInfoVbox.lookup("#"+fld.getName());
	    			 if(label!=null){
	    				 label.setText(value.toString());
	    			 }
	    			 else{
	    				 Label label1=(Label) paymentInfoVbox.lookup("#"+fld.getName());
	    				 if(label1!=null)
	    					 label1.setText(value.toString());
	    			 }
	    		 }
	     }
	}
	public void setViewPurchaseProductTable(PurchaseDtls purchaseDtls){
		prdctViewTable.setItems(FXCollections.observableArrayList(purchaseDtls.getPurchaseProductDtls()));
	   }
	

	public Thread viewPurchaseBreakDtl(String invoiceNum){
		
		ProgressIndicator progressIndicator = new ProgressIndicator(0);
		Task<ObservableList<String[]>> fectchPurchaseBreakDtl = new Task<ObservableList<String[]>>(){
			 @Override
	            protected ObservableList<String[]> call() throws Exception {
	            	logger.info("fectchPurchaseBreakDtl :: start");
	    			logger.info("fectchPurchaseBreakDtl :: invoice number ### "+invoiceNum);
	    			breakTable.getItems().clear();
	    			//ObservableList<String[]> invoicetaxComponents=productService.viewPurchaseBreakByInvoice(invoiceNum);
	    			ObservableList<String[]> invoicetaxComponents=dataProvider.viewPurchaseBreakByInvoice(invoiceNum);
	    			return invoicetaxComponents;
	            }
		};
		progressIndicator.progressProperty().bind(fectchPurchaseBreakDtl.progressProperty());
		breakTable.setPlaceholder(progressIndicator);
		fectchPurchaseBreakDtl.setOnSucceeded( (e) ->{
			logger.info("fectchPurchaseBreakDtl :: completed");
			ObservableList<String[]> lst=fectchPurchaseBreakDtl.getValue();
			breakTable.getItems().setAll(lst);
			progressIndicator.progressProperty().unbind();
		    progressIndicator.setProgress(1);
		});
		fectchPurchaseBreakDtl.setOnFailed( (e) -> {
			logger.error("fectchPurchaseBreakDtl :: failed");
			Throwable exception=fectchPurchaseBreakDtl.getException();
			progressIndicator.progressProperty().unbind();
			progressIndicator.setProgress(1);
        	logger.fatal("fectchPurchaseBreakDtl :: exception ### "+exception.getMessage());
        	exception.printStackTrace();
		});
		return new Thread(fectchPurchaseBreakDtl);
	}
}
