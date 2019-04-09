package application.cdms.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXButton.ButtonType;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialog.DialogTransition;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;

import application.Components;
import application.SystemMessages;
import application.ValidationRegex;
import application.cdms.component.data.handler.AlertDialog;
import application.cdms.component.data.handler.CellFactoryGenerator;
import application.cdms.component.data.handler.DialogueCreator;
import application.cdms.component.data.handler.ErrorDialog;
import application.cdms.component.data.handler.PrintJobScreen;
import application.cdms.constants.ApplicationConstant;
import application.cdms.enums.InvoiceType;
import application.cdms.models.HsnTax;
import application.cdms.models.NonBeveragePrdct;
import application.cdms.models.NonBeveragePrdctSale;
import application.cdms.models.NonBeverageSale;
import application.cdms.models.PurchaseDtls;
import application.cdms.models.SearchBean;
import application.cdms.service.CDMSDataProviderService;
import application.cdms.service.ProductService;
import application.cdms.service.impl.ProductServiceImpl;
import application.cdms.transformer.Initialization;
import application.cdms.utilities.PropertyResourceBundle;
import application.cdms.utilities.Utility;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.converter.NumberStringConverter;

@SuppressWarnings("rawtypes")
public class ReturnEmptyInvoiceController implements ScreenController,Initializable {
	private static Logger logger = Logger.getLogger(ReturnEmptyInvoiceController.class);

	private ScreenTransitionController<?> t;

	@FXML
	private HBox headLineHbox;
	@FXML
	private TableView<PurchaseDtls> challanSummeryLstTable;
	@FXML
	private TextField challanInvoiceNum;
	@FXML
	private TableColumn purchaseReturnLink;
	@FXML
	private HBox billInfoHbox;
	@FXML
	private JFXButton searchButton;

	private ProductService productService = ProductServiceImpl.getInstance();
	
	@FXML
	private TableView<NonBeveragePrdctSale> returnEmptyTable;

	@FXML
	private JFXTextField totalLoadTextFld;
	@FXML
	private JFXTextField netAmtTextFld;

	@FXML
	private TableColumn return_sr_col;

	@FXML
	private TableColumn action_col;

	@FXML
	private JFXTextField challanNumberTextFld;

	@FXML
	private JFXTextField challanInvoiceNumberTextFld;

	@FXML
	private JFXTextField firmNmTextFld;

	@FXML
	private JFXTextField addressTextFld;

	@FXML
	private JFXTextField firmGstnTextFld;
	
	@FXML
	private TableColumn<NonBeveragePrdctSale,Long> productQty_col;
	
	@FXML
	private ToggleGroup state;

	// private List<PurchaseProductDtl> purhsdPrdctLst;
	private PurchaseDtls purchaseDtls;
	
	//private LongProperty totalLoad =new SimpleLongProperty(0);
	
	private DoubleProperty netAmt=new SimpleDoubleProperty(0);
	
	private DoubleProperty totalBaseAmt = new SimpleDoubleProperty(0);
	
	//private DoubleProperty totalSchemeDiscountAmt = new SimpleDoubleProperty(0);
	
	private DoubleProperty totalTaxableAmt = new SimpleDoubleProperty(0);
	
	private DoubleProperty totalCGSTAmt = new SimpleDoubleProperty(0);
	
	private DoubleProperty totalSGSTAmt = new SimpleDoubleProperty(0);
	
	private DoubleProperty totalIGSTAmt = new SimpleDoubleProperty(0);
	
	private DoubleProperty totalCessAmt = new SimpleDoubleProperty(0);
	
	private CDMSDataProviderService cdmsDataProvideService = CDMSDataProviderService.createCDMSDataProvider();

	@Override
	public void setScreenTransitionController(Object obj) {
		if (obj instanceof ScreenTransitionController<?>) {
			this.t = (ScreenTransitionController) obj;
		}
	}

	@Override
	public void setParams(Map params) {
		afterPageLoadingAction();
	}

	private void afterPageLoadingAction() {
		List<PurchaseDtls> purchaseForReturn = productService.getAllChallanDtlsForForRtnEmpty(Initialization.LAZY);
		if(purchaseForReturn!=null && !purchaseForReturn.isEmpty()) {
			challanSummeryLstTable.getItems().addAll(purchaseForReturn);
		}
		//challanSummeryLstTable.getItems().addAll();
	}
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// totalLoadTextFld.textProperty().bindBidirectional(totalLoad,new NumberStringConverter());
		netAmtTextFld.textProperty().bindBidirectional(netAmt,new NumberStringConverter());
		purchaseReturnLink.setCellFactory(CellFactoryGenerator.getHiperLinkCellFactory((index) -> {return getChallanDtlForReturn(index);}));
		return_sr_col.setCellFactory(CellFactoryGenerator.getSrNumberCellFactory());
		action_col.setCellFactory(new Callback<TableColumn, TableCell>() {
			@Override
			public TableCell call(TableColumn param) {
				return new TableCell() {
					@Override
					public void updateItem(Object item, boolean empty) {
						ImageView deleteImage = new ImageView((Components.DELETE_IMAGE));
						Hyperlink deletelink = new Hyperlink("", deleteImage);
						AlertDialog alertDiloag = new AlertDialog();
						deletelink.setOnMouseClicked((e) -> {
							JFXButton yesButton = new JFXButton("YES");
							yesButton.setOnAction((f) -> {
								int index = getTableRow().getIndex();
								yesButtonAction(index);
								getTableView().getItems().remove(index);
								alertDiloag.closeAlertDialog();
							});
							JFXButton noButton = new JFXButton("NO");
							ObservableList<JFXButton> alertbutnLst = FXCollections.observableArrayList();
							alertbutnLst.add(yesButton);
							alertbutnLst.add(noButton);
							alertDiloag.showAlertDilogue(((StackPane) t.getCurrentNode()),
									"Do you want to remove the row?", alertbutnLst);
							noButton.setOnAction(new EventHandler<ActionEvent>() {
								@Override
								public void handle(ActionEvent arg0) {
									alertDiloag.closeAlertDialog();
								}
							});
						});
						HBox pane = new HBox(deletelink);
						setGraphic(empty ? null : pane);
					}
				};
			}
		});
      	//productQty_col.setCellValueFactory(new PropertyValueFactory<>("sellingQtyCs"));
	}

	@FXML
	void getChallanLst(ActionEvent event) {
		if (challanInvoiceNum.getText() == null || challanInvoiceNum.getText().trim().equals("")) {
			return;
		}
		ProgressIndicator progressIndicator = new ProgressIndicator(0);
		Task<PurchaseDtls> getChallanLstTask = new Task<PurchaseDtls>() {
			@Override
			protected PurchaseDtls call() throws Exception {
				String invoice = challanInvoiceNum.getText().trim();
				logger.info("getChallanInfoTask :: start with invoice ### " + invoice);
				challanSummeryLstTable.getItems().clear();
				PurchaseDtls purchaseDtls = productService.getChallanDetailByInvoice(invoice,Initialization.LAZY);
				return purchaseDtls;
			}
		};
		progressIndicator.progressProperty().bind(getChallanLstTask.progressProperty());
		challanSummeryLstTable.setPlaceholder(progressIndicator);
		getChallanLstTask.setOnSucceeded((e) -> {
			logger.info("getChallanInfoTask :: completed");
			PurchaseDtls purchaseDtls = getChallanLstTask.getValue();
			challanSummeryLstTable.getItems().add(purchaseDtls);
			progressIndicator.progressProperty().unbind();
			progressIndicator.setProgress(1);

		});
		getChallanLstTask.setOnFailed((e) -> {
			Throwable th = getChallanLstTask.getException();
			progressIndicator.progressProperty().unbind();
			progressIndicator.setProgress(1);
			logger.fatal("getChallanInfoTask :: task failed :: why ### " + th.getMessage());
			th.printStackTrace();
		});
		new Thread(getChallanLstTask).start();
	}

	private Hyperlink getChallanDtlForReturn(int index) {
		Hyperlink updtHyperPayLink = new Hyperlink("Fetch Detail");
		updtHyperPayLink.setOnAction((e) -> {
			purchaseDtls = null;
			//challanPrdctsComboBox.getItems().clear();
			returnEmptyTable.getItems().clear();
			netAmt.set(0);
			totalBaseAmt.set(0);
			totalTaxableAmt.set(0);
			totalCGSTAmt.set(0);
			totalSGSTAmt.set(0);
			totalIGSTAmt.set(0);
			totalCessAmt.set(0);
			challanNumberTextFld.setText("");
			challanInvoiceNumberTextFld.setText("");
			firmNmTextFld.setText("");
			firmGstnTextFld.setText("");
			purchaseDtls = challanSummeryLstTable.getItems().get(index);
			if (purchaseDtls == null) {
				return;
			}
			challanNumberTextFld.setText(purchaseDtls.getChallanNumber());
			challanInvoiceNumberTextFld.setText(purchaseDtls.getChallanInvoiceNumber());
			firmNmTextFld.setText(purchaseDtls.getFirmNm());
			firmGstnTextFld.setText(purchaseDtls.getFirmGstn());
			addChallanPrdctForReturn(purchaseDtls);
		});
		return updtHyperPayLink;
	}

	void addChallanPrdctForReturn(PurchaseDtls purchaseDtls) {
		ObservableList<String[]> lst=cdmsDataProvideService.viewNonBeveragePurchaseDtlByInvoice(purchaseDtls.getChallanInvoiceNumber());
		if(!lst.isEmpty()){
			for(String[] strArr : lst){
				if(strArr[1].equalsIgnoreCase(ApplicationConstant.BOTTLE) || strArr[1].equalsIgnoreCase(ApplicationConstant.SHELL)){
					NonBeveragePrdctSale salePrdctInvoice = new NonBeveragePrdctSale();
					NonBeveragePrdct nonBeveragePrdct = new NonBeveragePrdct();
					nonBeveragePrdct.setProductCd(strArr[0]);
					HsnTax hsn = new HsnTax();
					hsn.setHsnCd(strArr[3]);
					nonBeveragePrdct.setHsnTax(hsn);
					nonBeveragePrdct.setProductNm(strArr[1]);
					salePrdctInvoice.setNonBeveragePrdct(nonBeveragePrdct);
					if(strArr[1].equalsIgnoreCase(ApplicationConstant.BOTTLE)){
						salePrdctInvoice.setSellingQty(purchaseDtls.getReturingBottleQty());
					}	
					else if(strArr[1].equalsIgnoreCase(ApplicationConstant.SHELL)){
						salePrdctInvoice.setSellingQty(purchaseDtls.getReturningCellQty());
					}
					salePrdctInvoice.setUnitPrice(Double.parseDouble(strArr[2]));
					salePrdctInvoice.setCgstRate(Double.parseDouble(strArr[5]));
					salePrdctInvoice.setSgstRate(Double.parseDouble(strArr[6]));
					salePrdctInvoice.setIgstRate(Double.parseDouble(strArr[9]));
					salePrdctInvoice.setCessRate(Double.parseDouble(strArr[7]));
					double netBaseAmt = salePrdctInvoice.getSellingQty()*salePrdctInvoice.getUnitPrice();
					
					double taxableAmt = netBaseAmt;
					double cgstAmt =0;
					double sgstAmt =0;
					double igstAmt =0; 
					
					String deliveryTo=(String) state.getSelectedToggle().getUserData();
					if(ApplicationConstant.DELIVERY_TO_SAME_STATE.equalsIgnoreCase(deliveryTo)){
						sgstAmt = taxableAmt*salePrdctInvoice.getSgstRate()/100;
						cgstAmt = taxableAmt*salePrdctInvoice.getCgstRate()/100;
					}
					else{
						igstAmt=taxableAmt*salePrdctInvoice.getIgstRate()/100;
					}
					double cessAmt = taxableAmt*salePrdctInvoice.getCessRate()/100;
					double netAmount = taxableAmt+cgstAmt+sgstAmt+igstAmt+cessAmt;
					
					salePrdctInvoice.setNetBaseAmt(Utility.decimalRound(netBaseAmt));
					totalBaseAmt.set(Utility.decimalRound(totalBaseAmt.get()+netBaseAmt));
					salePrdctInvoice.setDisocuntAmt(0);
					salePrdctInvoice.setTaxableAmt(Utility.decimalRound(taxableAmt));
					totalTaxableAmt.set(Utility.decimalRound(totalTaxableAmt.get()+salePrdctInvoice.getTaxableAmt()));
					salePrdctInvoice.setCgstAmt(Utility.decimalRound(cgstAmt));
					totalCGSTAmt.set(Utility.decimalRound(salePrdctInvoice.getCgstAmt()+totalCGSTAmt.get()));
					salePrdctInvoice.setSgstAmt(Utility.decimalRound(sgstAmt));
					totalSGSTAmt.set(Utility.decimalRound(salePrdctInvoice.getSgstAmt()+totalSGSTAmt.get()));
					salePrdctInvoice.setIgstAmt(Utility.decimalRound(igstAmt));
					totalIGSTAmt.set(Utility.decimalRound(salePrdctInvoice.getIgstAmt()+totalIGSTAmt.get()));
					salePrdctInvoice.setCessAmt(Utility.decimalRound(cessAmt));
					totalCessAmt.set(Utility.decimalRound(salePrdctInvoice.getCessAmt()+totalCessAmt.get()));
					salePrdctInvoice.setSysGnrtdNetAmt(Utility.decimalRound(netAmount));
					netAmt.set(Utility.decimalRound(netAmt.get()+salePrdctInvoice.getSysGnrtdNetAmt()));
					returnEmptyTable.getItems().add(salePrdctInvoice);
				}	
			}
		}
		else {
			showNonBevPriceDialog(purchaseDtls);
		}
	}
	
	
	
	@FXML
	void gnrtRtrnPrchseInvoice(ActionEvent event){
		//String challanNumber = challanNumberTextFld.getText();
		//String purchaseInvoiceNo = challanInvoiceNumberTextFld.getText();
		
		String firmNm = firmNmTextFld.getText();
		String address = addressTextFld.getText();
		String firmGstn = firmGstnTextFld.getText();
		List<NonBeveragePrdctSale> choosedprdctLst = returnEmptyTable.getItems();
		StringBuilder str=new StringBuilder();
		boolean isError=false;
		if(firmNm==null || firmNm.trim().equals("")){
			str.append("please provide firm name. \n");
			isError=true;
		}
		if(address==null || address.trim().equals("")){
			str.append("please provide firm address. \n");
			isError=true;
		}
		if(firmGstn==null || firmGstn.trim().equals("")){
			str.append("please provide firm GSTN. \n");
			isError=true;
		}
		if(!(choosedprdctLst.size()>0)){
			str.append("Please provide Empty. \n");
			isError=true;
		}
		if(isError){
			ErrorDialog.showErrorDilogue(new Text(str.toString()), ((StackPane) t.getCurrentNode()),SystemMessages.validation_heading);
			return;
		}
		try{
			showDialogAddNewProductRow();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void yesButtonAction(int index){
		NonBeveragePrdctSale delTblInvoice=returnEmptyTable.getItems().get(index);
		netAmt.set(netAmt.get()-delTblInvoice.getSysGnrtdNetAmt());
		totalTaxableAmt.set(totalTaxableAmt.get()-delTblInvoice.getTaxableAmt());
	}
	//JFXDialog productDilogu=null;
	public void showDialogAddNewProductRow(){
		JFXDialog productDilogu=null;
		JFXDialogLayout dilogLayout = new JFXDialogLayout();
		Label heading = new Label("Confirm Return Product");
		heading.setStyle("-fx-font-size: 22px;-fx-text-fill:#3F51B5");
		dilogLayout.setHeading(heading);
		
		//dilogLayout.setStyle("-fx-background-color: #e5eaf5;");//#3F51B5;
		try {
			productDilogu = new JFXDialog(((StackPane) t.getCurrentNode()), dilogLayout, DialogTransition.CENTER);
			dilogLayout.setBody(loadDialogBody(productDilogu));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		productDilogu.setOverlayClose(false);
		productDilogu.show();
		System.out.println(productDilogu.computeAreaInScreen());
	}
	@SuppressWarnings("unchecked")
	public Node loadDialogBody(JFXDialog productDilogu) throws IOException{
		VBox vbox = FXMLLoader.load(getClass().getResource(Components.RETURN_PURCHASE_CONFIRM_FXML));
		// table view body
		TableView<NonBeveragePrdctSale> selectedProdctSummery = new TableView<>();
		selectedProdctSummery.setStyle("-fx-pref-height:200.0;-fx-max-width:+Infinity;");
		TableColumn<NonBeveragePrdctSale, NonBeveragePrdct> productCol = new TableColumn<>("Returnable Material Name");
		productCol.setStyle("-fx-pref-width:253.0;");
		TableColumn<NonBeveragePrdctSale, Long> productQtyCol = new TableColumn<>("Qty");
		productQtyCol.setStyle("-fx-pref-width:351.0;");
		TableColumn<NonBeveragePrdctSale, Double> amountCol = new TableColumn<>("Amount");
		amountCol.setStyle("-fx-pref-width:149.0;");
		selectedProdctSummery.getColumns().addAll(productCol, productQtyCol, amountCol);
		productCol.setCellValueFactory(new PropertyValueFactory<>("nonBeveragePrdct"));
		productCol.setCellFactory(CellFactoryGenerator.getTextCellFactory((e) -> {
			if(e!=null){
				NonBeveragePrdct nbPrdct = (NonBeveragePrdct) e;
				return nbPrdct.getProductNm();
			}
			else{
				return null;
			} 
		}));
		productQtyCol.setCellValueFactory(new PropertyValueFactory<>("sellingQty"));
		amountCol.setCellValueFactory(new PropertyValueFactory<>("sysGnrtdNetAmt"));
		//load content
		
		selectedProdctSummery.setItems(returnEmptyTable.getItems());
		TextField dilogFirmName=(TextField) vbox.lookup("#dilogFirmName");
		TextField dilogInvoiceNum=(TextField) vbox.lookup("#dilogInvoiceNum");
		TextField dilogTotalAmt=(TextField) vbox.lookup("#dilogTotalAmt");
		dilogFirmName.setText(firmNmTextFld.getText());
		dilogInvoiceNum.setText(challanInvoiceNumberTextFld.getText());
		dilogTotalAmt.setText(netAmt.get()+"");
		Label label = new Label("Selected products");
		label.getStyleClass().add("headlineLable");
		label.setUnderline(true);
		VBox tableVbox = new  VBox(label,selectedProdctSummery);
		tableVbox.setSpacing(5);
		
		JFXButton confirmButton = new JFXButton("Confirm");
		JFXButton cancelButton = new JFXButton("edit");
		JFXButton printInvoiceButton = new JFXButton("print");
		JFXButton closeButton = new JFXButton("close");
		printInvoiceButton.setVisible(false);
		confirmButton.setOnAction( (e)->{
			cancelButton.setDisable(true);
			confirmButton.setDisable(true);
			NonBeverageSale sale=submitReturnPurchaseCnfrmAction();
			if(sale!=null){
				printInvoiceButton.setVisible(true);
				printInvoiceButton.setOnAction((e1)->{
					printInvoice(sale);
				});
			}
			else{
				cancelButton.setDisable(false);
			}
		});
		
		cancelButton.setOnAction((e)->{
			productDilogu.close();
		});
		closeButton.setOnAction((e)->{
			productDilogu.close();
			t.loadScreenIntoRoot(Components.HOMESCREEN, Components.HOMESCREEN_FXML, null);
		});
		
		
		confirmButton.setStyle("-fx-background-color: #3F51B5; -fx-text-fill: white; -fx-font-size: 13px;-fx-pref-width:111.0;");
		cancelButton.setStyle("-fx-background-color: #3F51B5; -fx-text-fill: white; -fx-font-size: 13px;fx-pref-width:111.0;");
		printInvoiceButton.setStyle("-fx-background-color: #3F51B5; -fx-text-fill: white; -fx-font-size: 13px;fx-pref-width:111.0;");
		closeButton.setStyle("-fx-background-color: #3F51B5; -fx-text-fill: white; -fx-font-size: 13px;fx-pref-width:111.0;");
		HBox hbox = new HBox(cancelButton,confirmButton,printInvoiceButton,closeButton);
		hbox.setAlignment(Pos.CENTER);
		hbox.prefHeight(30);
		hbox.setSpacing(10);
		vbox.getChildren().addAll(tableVbox,hbox);
		return vbox;
	}
	
	private NonBeverageSale submitReturnPurchaseCnfrmAction(){
		
		String challanNumber = challanNumberTextFld.getText();
		String purchaseInvoiceNo = challanInvoiceNumberTextFld.getText();
		String firmNm = firmNmTextFld.getText();
		String address = addressTextFld.getText();
		String firmGstn = firmGstnTextFld.getText();
		//String totalAmount = totalLoadTextFld.getText();
		//Double totalAmount = netAmt.get();
		List<NonBeveragePrdctSale> choosedprdctLst = returnEmptyTable.getItems();
		SearchBean searchBean = new SearchBean();
		searchBean.setChallanNo(challanNumber);
		searchBean.setChallanInvoiceNo(purchaseInvoiceNo);
		NonBeverageSale sale = new NonBeverageSale();
		sale.setCstmrFrmNm(firmNm.trim().toUpperCase());
		sale.setAddress(address.trim().toUpperCase());
		sale.setInvoiceType(InvoiceType.RETURNABLE_MATERIAL);
		sale.setBuyerGstn(firmGstn);
		Calendar cal = Calendar.getInstance();
		String dt=ApplicationConstant.formatter.format(cal.getTime());
		sale.setNbSaleDt(dt);
		sale.setNbSaleProductlst(choosedprdctLst);
		sale.setGstn(PropertyResourceBundle.get("GSTN"));
		NonBeverageSale returnSale=null;
		try{
			sale=productService.returnEmptyInvoice(sale);
			productService.updateRtnEmtpyInvoiceNumber(challanNumber,purchaseInvoiceNo,sale.getNbSaleInvoiceNo());
			returnSale=sale;
		}
		catch(Exception e){
			returnSale=null;
			logger.fatal("ReturnPurchaseController :: submitReturnPurchaseCnfrmAction :: exception ### "+e.getMessage());
			e.printStackTrace();
		}
		return returnSale;
	}
	
	public void printInvoice(NonBeverageSale sale){
		List<NonBeverageSale> saleLst = new ArrayList<>();
		Map<String,Object> jasperParams=new HashMap<>();
		jasperParams.put("totalBaseAmt", totalBaseAmt.get());
		jasperParams.put("totalDiscount", 0);
		jasperParams.put("totalTaxableAmt", totalTaxableAmt.get());
		jasperParams.put("totalCGSTAmt", totalCGSTAmt.get());
		jasperParams.put("totalSGSTAmt", totalSGSTAmt.get());
		jasperParams.put("totalIGSTAmt", totalIGSTAmt.get());
		jasperParams.put("totalCessAmt", totalCessAmt.get());
		jasperParams.put("netAmt", netAmt.get());
		saleLst.add(sale);
		String heading = "Return Product Invoice";
		try {
			PrintJobScreen<NonBeverageSale> job = new PrintJobScreen<>(t,heading);
			job.printInvoice_modified(saleLst, jasperParams, Components.RETURN_EMPTY_INVOICE_TEMPLATE,"/app/temp/file/viewReturnEmptyHtml_temp.html");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void showNonBevPriceDialog(PurchaseDtls purchaseDtls) {
		JFXDialog productDilogu=null;
		JFXDialogLayout dilogLayout = new JFXDialogLayout();
		Label heading = new Label("Set Non Beverage Products Price :");
		heading.setStyle("-fx-font-size: 22px;-fx-text-fill:#3F51B5");
		dilogLayout.setHeading(heading);
		productDilogu = new JFXDialog(((StackPane) t.getCurrentNode()), dilogLayout, DialogTransition.CENTER);
		dilogLayout.setBody(bodyforNonBevDefaultPriceSet(productDilogu,purchaseDtls));
		productDilogu.setOverlayClose(false);
		productDilogu.show();
	}


	private Pane bodyforNonBevDefaultPriceSet(JFXDialog productDilogu,PurchaseDtls purchaseDtls) {
		ObservableList<NonBeveragePrdct> nonBProduct = productService.nonBproductList();
		
		//row : 0;
		Label warningHeader = new Label("There are no purchased glassess.So Please input unit Price"); 
		warningHeader.setStyle("-fx-text-fill: purple; -fx-font-size: 15px;-fx-pref-height:24.0;-fx-pref-width:408.0;-fx-alignment: CENTER;");
		
		//row : 1
		Label bottleUnitPriceLabel = new Label("Bottle Unit Price"); 
		bottleUnitPriceLabel.setStyle("-fx-text-fill: #aaa; -fx-font-size: 15px;-fx-pref-height:34.0;-fx-pref-width:160.0;-fx-alignment: CENTER_RIGHT;");
		
		Label bottleRsSymbol = new Label("₹"); 
		bottleRsSymbol.setStyle("-fx-text-fill: #aaa; -fx-font-size: 15px;-fx-pref-height:34.0;-fx-pref-width:83.0;-fx-alignment: CENTER_RIGHT;");
		
		TextField bottleUnitPriceFld = new JFXTextField();
		bottleUnitPriceFld.setStyle("-fx-pref-height:34.0;-fx-font-size: 14px;");
		
		//row	: 2
		Label shellUnitPriceLabel = new Label("Shell Unit Price"); 
		shellUnitPriceLabel.setStyle("-fx-text-fill: #aaa; -fx-font-size: 15px;-fx-pref-height:34.0;-fx-pref-width:160.0;-fx-alignment: CENTER_RIGHT;");
		
		Label shellRsSymbol = new Label("₹"); 
		shellRsSymbol.setStyle("-fx-text-fill: #aaa; -fx-font-size: 15px;-fx-pref-height:34.0;-fx-pref-width:83.0;-fx-alignment: CENTER_RIGHT;");
		
		TextField shellUnitPriceFld = new JFXTextField();
		shellUnitPriceFld.setStyle("-fx-pref-height:34.0;-fx-font-size: 14px;");
		
		//row : 3
		JFXButton setPriceBtn = new JFXButton("Set Price");
		setPriceBtn.setButtonType(ButtonType.RAISED);
		setPriceBtn.setStyle("-fx-background-color: #3F51B5; -fx-text-fill: white; -fx-font-size: 13px;-fx-pref-height:29.0;-fx-pref-width:181.0;");
		
		JFXButton cancelBtn = new JFXButton("Cancel");
		cancelBtn.setButtonType(ButtonType.RAISED);
		cancelBtn.setStyle("-fx-background-color: #3F51B5; -fx-text-fill: white; -fx-font-size: 13px;-fx-pref-height:29.0;-fx-pref-width:201.0;");
		
		
		setPriceBtn.setOnAction( (e) ->{
			StringBuilder strbuild = new StringBuilder();
			boolean isError = false;
			String bottleUnitPriceStr = bottleUnitPriceFld.getText();
			String shellUnitPriceStr  = shellUnitPriceFld.getText();
			if(bottleUnitPriceStr==null || bottleUnitPriceStr.trim().equals("") || !bottleUnitPriceStr.matches(ValidationRegex.DOUBLENUMBERCHECK)){
				strbuild.append("Please provide valid bottle Unit Price");
				isError=true;
			}
			if(shellUnitPriceStr==null || shellUnitPriceStr.trim().equals("") 
					|| !shellUnitPriceStr.matches(ValidationRegex.DOUBLENUMBERCHECK)){
				strbuild.append("Please provide valid shell Unit Price");
				isError=true;
			}
			else if(Double.parseDouble(shellUnitPriceStr)==0.0) {
				strbuild.append("Please provide valid shell Unit Price");
				isError=true;
			}
			if(isError){
				ErrorDialog.showErrorDilogue(new Text(strbuild.toString()), ((StackPane) t.getCurrentNode()),SystemMessages.validation_heading);
				return;
			}
			for(NonBeveragePrdct nb:nonBProduct) {
				if(ApplicationConstant.BOTTLE.equals(nb.getProductNm())) {
					createNonBSale(nb,purchaseDtls, bottleUnitPriceStr);
				}
				else if(ApplicationConstant.SHELL.equals(nb.getProductNm())) {
					createNonBSale(nb,purchaseDtls, shellUnitPriceStr);
				}
			}
			returnEmptyTable.refresh();
			productDilogu.close();
		});
		
		cancelBtn.setOnAction( (e) ->{
			productDilogu.close();
		});
		
		Object[][] nodDtls = new Object[][]{
				{warningHeader,0,0,0,3},
				{bottleUnitPriceLabel,1,0,0,0},
				{bottleRsSymbol,1,1,0,0},
				{bottleUnitPriceFld,1,2,0,0},
				{shellUnitPriceLabel,2,0,0,0},
				{shellRsSymbol,2,1,0,0},
				{shellUnitPriceFld,2,2,0,0},
				{setPriceBtn,3,0,0,3},
				{cancelBtn,3,2,0,2}
		};
		return DialogueCreator.createGridDialogue(nodDtls, 440.0);
	}

	private void createNonBSale(NonBeveragePrdct nonBProduct, PurchaseDtls purchaseDtls,String UnitPriceStr) {
		NonBeveragePrdctSale salePrdctInvoice = new NonBeveragePrdctSale();
		salePrdctInvoice.setNonBeveragePrdct(nonBProduct);
		if(nonBProduct.getProductNm().equalsIgnoreCase(ApplicationConstant.BOTTLE)){
			salePrdctInvoice.setSellingQty(purchaseDtls.getReturingBottleQty());
		}	
		else if(nonBProduct.getProductNm().equalsIgnoreCase(ApplicationConstant.SHELL)){
			salePrdctInvoice.setSellingQty(purchaseDtls.getReturningCellQty());
		}
		salePrdctInvoice.setUnitPrice(Double.parseDouble(UnitPriceStr));
		salePrdctInvoice.setCgstRate(nonBProduct.getHsnTax().getCgst());
		salePrdctInvoice.setSgstRate(nonBProduct.getHsnTax().getSgstOrIgst());
		salePrdctInvoice.setIgstRate(nonBProduct.getHsnTax().getIgst());
		salePrdctInvoice.setCessRate(nonBProduct.getHsnTax().getCess());
		double netBaseAmt = salePrdctInvoice.getSellingQty()*salePrdctInvoice.getUnitPrice();
		
		double taxableAmt = netBaseAmt;
		double cgstAmt =0;
		double sgstAmt =0;
		double igstAmt =0; 
		
		String deliveryTo=(String) state.getSelectedToggle().getUserData();
		if(ApplicationConstant.DELIVERY_TO_SAME_STATE.equalsIgnoreCase(deliveryTo)){
			sgstAmt = taxableAmt*salePrdctInvoice.getSgstRate();
			cgstAmt = taxableAmt*salePrdctInvoice.getCgstRate();
		}
		else{
			igstAmt=taxableAmt*salePrdctInvoice.getIgstRate();
		}
		double cessAmt = taxableAmt*salePrdctInvoice.getCessRate();
		double netAmount = taxableAmt+cgstAmt+sgstAmt+igstAmt+cessAmt;
		
		salePrdctInvoice.setNetBaseAmt(Utility.decimalRound(netBaseAmt));
		totalBaseAmt.set(Utility.decimalRound(totalBaseAmt.get()+netBaseAmt));
		salePrdctInvoice.setDisocuntAmt(0);
		salePrdctInvoice.setTaxableAmt(Utility.decimalRound(taxableAmt));
		totalTaxableAmt.set(Utility.decimalRound(totalTaxableAmt.get()+salePrdctInvoice.getTaxableAmt()));
		salePrdctInvoice.setCgstAmt(Utility.decimalRound(cgstAmt));
		totalCGSTAmt.set(Utility.decimalRound(salePrdctInvoice.getCgstAmt()+totalCGSTAmt.get()));
		salePrdctInvoice.setSgstAmt(Utility.decimalRound(sgstAmt));
		totalSGSTAmt.set(Utility.decimalRound(salePrdctInvoice.getSgstAmt()+totalSGSTAmt.get()));
		salePrdctInvoice.setIgstAmt(Utility.decimalRound(igstAmt));
		totalIGSTAmt.set(Utility.decimalRound(salePrdctInvoice.getIgstAmt()+totalIGSTAmt.get()));
		salePrdctInvoice.setCessAmt(Utility.decimalRound(cessAmt));
		totalCessAmt.set(Utility.decimalRound(salePrdctInvoice.getCessAmt()+totalCessAmt.get()));
		salePrdctInvoice.setSysGnrtdNetAmt(Utility.decimalRound(netAmount));
		netAmt.set(Utility.decimalRound(netAmt.get()+salePrdctInvoice.getSysGnrtdNetAmt()));
		returnEmptyTable.getItems().add(salePrdctInvoice);
	}
	
}
