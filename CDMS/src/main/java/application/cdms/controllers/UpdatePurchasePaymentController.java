package application.cdms.controllers;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;

import application.SystemMessages;
import application.ValidationRegex;
import application.cdms.component.data.handler.CellFactoryGenerator;
import application.cdms.component.data.handler.CheckerForTextField;
import application.cdms.component.data.handler.CustomeStringConverter;
import application.cdms.component.data.handler.ErrorDialog;
import application.cdms.models.PaymentMethod;
import application.cdms.models.PurchaseDtls;
import application.cdms.service.ProductService;
import application.cdms.service.impl.ProductServiceImpl;
import application.cdms.transformer.Initialization;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

@SuppressWarnings("rawtypes")
public class UpdatePurchasePaymentController implements Initializable, ScreenController {
	
	private static Logger logger = Logger.getLogger(SideNavigationController.class);
	
	private ScreenTransitionController<?> t;
	
	@FXML
	private TableView<PurchaseDtls> challanSummeryLstTable;
	
	@FXML
	private TextField challanInvoiceNum;
	
	@FXML
	private TableColumn updtPaymentCol;
	
	@FXML
	private HBox billInfoHbox;
	
	@FXML
	private JFXButton searchButton;
	
	@FXML
	private JFXButton updatePayButton;
	
	private ProductService productService = ProductServiceImpl.getInstance();
	
	private Task<Void> updatePurchasepayDtlTask;
	

	@SuppressWarnings("unchecked")
	@Override
	public void setScreenTransitionController(Object obj) {
		if (obj instanceof ScreenTransitionController<?>) {
			this.t = (ScreenTransitionController) obj;
		}
		TextField paidAmtTextfld=(TextField) billInfoHbox.lookup("#paidAmount");
		paidAmtTextfld.focusedProperty().addListener(new CheckerForTextField(paidAmtTextfld, ValidationRegex.DOUBLENUMBERCHECK,"Please provide valid decimal number."));
		
		JFXDatePicker payDt = (JFXDatePicker) billInfoHbox.lookup("#paymentDt");
		payDt.setDayCellFactory(CellFactoryGenerator.getFutureDayDisableCellFactory());
		ObservableList<String> paymentMethods=FXCollections.observableArrayList();
		for(PaymentMethod paymentWay: PaymentMethod.values()){
			paymentMethods.add(paymentWay.name());
		}
		JFXComboBox<String> payCombo = (JFXComboBox) billInfoHbox.lookup("#paymentMethod");
		payCombo.setItems(paymentMethods);
		payDt.setConverter(new CustomeStringConverter());
		
	}

	@Override
	public void setParams(Map params) {
		afterPageLoadingAction();
	}

	private void afterPageLoadingAction() {
		challanSummeryLstTable.getItems().addAll(productService.getAllChallanDetail(Initialization.LAZY));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		challanSummeryLstTable.prefHeightProperty().bind(challanSummeryLstTable.fixedCellSizeProperty().multiply(Bindings.size(challanSummeryLstTable.getItems()).add(2)));
		updtPaymentCol.setCellFactory(CellFactoryGenerator.getHiperLinkCellFactory((int index) ->{return getUpdtPaymentHyper(index);}));
	}
		
	@FXML
	void getChallanInfo(ActionEvent event){
		if(challanInvoiceNum.getText()==null || challanInvoiceNum.getText().trim().equals("")){
			return ;
		}
		ProgressIndicator progressIndicator = new ProgressIndicator(0);
		Task<PurchaseDtls> getChallanInfoTask=new Task<PurchaseDtls>(){
			@Override
			protected PurchaseDtls call() throws Exception {
				String invoice = challanInvoiceNum.getText().trim();
				logger.info("getChallanInfoTask :: start with invoice ### "+invoice);
				challanSummeryLstTable.getItems().clear();
				PurchaseDtls purchaseDtls=productService.getChallanDetailByInvoice(invoice,Initialization.LAZY);
				return purchaseDtls;
			}
		};
		progressIndicator.progressProperty().bind(getChallanInfoTask.progressProperty());
		challanSummeryLstTable.setPlaceholder(progressIndicator);
		getChallanInfoTask.setOnSucceeded( (e) ->{
			logger.info("getChallanInfoTask :: completed");
			PurchaseDtls purchaseDtls=getChallanInfoTask.getValue();
			challanSummeryLstTable.getItems().add(purchaseDtls);
			//updatePayButton.setDisable(false);
			progressIndicator.progressProperty().unbind();
			progressIndicator.setProgress(1);
			
		});
		getChallanInfoTask.setOnFailed((e) ->{
			Throwable th=getChallanInfoTask.getException();
			progressIndicator.progressProperty().unbind();
			progressIndicator.setProgress(1);
			logger.fatal("getChallanInfoTask :: task failed :: why ### "+th.getMessage());
			th.printStackTrace();
		});
		 new Thread(getChallanInfoTask).start();
	}
	
	@SuppressWarnings("unchecked")
	private Hyperlink getUpdtPaymentHyper(int index){
		Hyperlink updtHyperPayLink = new Hyperlink("Update Payment");
		updtHyperPayLink.setOnAction( (e) ->{
			PurchaseDtls purchaseDtls = challanSummeryLstTable.getItems().get(index);
			try{
				updatePayButton.setDisable(false);
				Class cls=purchaseDtls.getClass();
			    Field[] flds = cls.getDeclaredFields();
			    for(Field fld : flds){
		    		 fld.setAccessible(true);
		    		 Object value=fld.get(purchaseDtls);
		    		 if(value instanceof String){
		    			 Node node  = billInfoHbox.lookup("#"+fld.getName());
		    			 if(node instanceof TextField){
		    				 ((TextField) node).setText(value.toString());
		    			 }
		    			 if(node instanceof ComboBox){
		    				 ((ComboBox) node).setValue(value.toString());
		    			 }
		    		 }
		    		 else if(value instanceof Double){
		    			 TextField textField = (TextField) billInfoHbox.lookup("#"+fld.getName());
		    			 if(textField!=null){
		    				 textField.setText(value.toString());
		    			 }
		    		 }
		    		 else if(value instanceof Long){
		    			 TextField textField = (TextField) billInfoHbox.lookup("#"+fld.getName());
		    			 if(textField!=null){
		    				 textField.setText(value.toString());
		    			 }
		    		 }
			    }
			}
			catch(Exception exp){
				logger.fatal("Exception ### "+exp.getMessage());
				exp.printStackTrace();
			}
		});
		return updtHyperPayLink;
	}
	
	@SuppressWarnings("unchecked")
	@FXML
	void updatePaymentDetail(ActionEvent event){
		logger.info("updatePaymentDetail :: Inside Update Action");
		if(updatePurchasepayDtlTask!=null && updatePurchasepayDtlTask.isRunning()){
			logger.info("updatePaymentDetail :: updatePurchasepayDtlTask Task already running");
			return ;
		}
		TextField paidAmtTextfld=(TextField) billInfoHbox.lookup("#paidAmount");
		JFXDatePicker payDt = (JFXDatePicker) billInfoHbox.lookup("#paymentDt");
		JFXComboBox<String> payCombo = (JFXComboBox) billInfoHbox.lookup("#paymentMethod");
		TextField payIdText=(TextField) billInfoHbox.lookup("#paymentId");
		String paidAmtStr = paidAmtTextfld.getText();
		String payDateStr = payDt.getConverter().toString(payDt.getValue());
		String payMethod = payCombo.getValue();
		String payId = payIdText.getText();
		StringBuilder strbuld = new StringBuilder();
		boolean isError = false;
		if(paidAmtStr==null || paidAmtStr.trim().equals("")){
			isError=true;
			strbuld.append("Paid amount field cannot be blank \n");
			paidAmtTextfld.setStyle("-fx-border-color: red;-fx-border-width: 0.0px 0px 4px 0px;-fx-border-style: dashed;");
		}
		if(payDateStr==null || payDateStr.trim().equals("")){
			isError=true;
			strbuld.append("Please provide payment date on which payment has been made. \n");
			payDt.setStyle("-fx-border-color: red;-fx-border-width: 0.0px 0px 4px 0px;-fx-border-style: dashed;");
		}
		if(payMethod==null || payMethod.trim().equals("")){
			isError=true;
			strbuld.append("Please choose payment method. \n");
			payCombo.setStyle("-fx-border-color: red;-fx-border-width: 0.0px 0px 4px 0px;-fx-border-style: dashed;");
		}
		if(payId==null || payId.trim().equals("")){
			isError=true;
			strbuld.append("Payment Id cannot be blank. \n");
			payIdText.setStyle("-fx-border-color: red;-fx-border-width: 0.0px 0px 4px 0px;-fx-border-style: dashed;");
		}
		if(isError){
			ErrorDialog.showErrorDilogue(new Text(strbuld.toString()), ((StackPane) t.getCurrentNode()),SystemMessages.validation_heading);
			return ;
		}
		String purchaseSeq = challanSummeryLstTable.getItems().get(0).getPurchaseId();
		updatePurchasepayDtlTask=new Task<Void>(){
			@Override
			protected Void call() throws Exception {
				logger.info("updatePurchasepayDtlTask :: start");
				updatePayButton.setDisable(true);
				searchButton.setDisable(true);
				Double paidAmt = Double.parseDouble(paidAmtStr);
				productService.updatePurchasePaymentDtl(purchaseSeq,paidAmt,payMethod,payDateStr,payId);
				return null;
			}
		};
		ProgressIndicator progressIndicator = new ProgressIndicator(0);
		progressIndicator.progressProperty().bind(updatePurchasepayDtlTask.progressProperty());
		Text text = new Text("Updating ");
		HBox hbBox = new HBox(text,progressIndicator);
		t.getNotification(hbBox);
		updatePurchasepayDtlTask.setOnSucceeded( (e) ->{
			logger.info("updatePurchasepayDtlTask :: completed");
			updatePayButton.setDisable(false);
			searchButton.setDisable(false);
			progressIndicator.progressProperty().unbind();
			progressIndicator.setProgress(1);
			
		});
		updatePurchasepayDtlTask.setOnFailed((e) ->{
			Throwable th=updatePurchasepayDtlTask.getException();
			progressIndicator.progressProperty().unbind();
			progressIndicator.setProgress(1);
			updatePayButton.setDisable(false);
			searchButton.setDisable(false);
			logger.fatal("updatePurchasepayDtlTask :: task failed :: why ### "+th.getMessage());
			th.printStackTrace();
		});
		 new Thread(updatePurchasepayDtlTask).start();
	}
}