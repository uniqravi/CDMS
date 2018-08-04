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
import org.controlsfx.control.CheckComboBox;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialog.DialogTransition;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;

import application.Components;
import application.SystemMessages;
import application.ValidationRegex;
import application.cdms.component.data.handler.AlertDialog;
import application.cdms.component.data.handler.CellFactoryGenerator;
import application.cdms.component.data.handler.CustomeTableEditCallback;
import application.cdms.component.data.handler.EditingTextCell;
import application.cdms.component.data.handler.ErrorDialog;
import application.cdms.component.data.handler.PrintJobScreen;
import application.cdms.constants.ApplicationConstant;
import application.cdms.models.HsnTax;
import application.cdms.models.Product;
import application.cdms.models.ProductGroup;
import application.cdms.models.PurchaseDtls;
import application.cdms.models.PurchaseProductDtl;
import application.cdms.models.Sale;
import application.cdms.models.SalePrdct;
import application.cdms.models.SalePrdctInvoice;
import application.cdms.models.SearchBean;
import application.cdms.service.ProductService;
import application.cdms.service.impl.ProductServiceImpl;
import application.cdms.transformer.Initialization;
import application.cdms.utilities.Calculation;
import application.cdms.utilities.PropertyResourceBundle;
import application.cdms.utilities.Utility;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.converter.NumberStringConverter;

@SuppressWarnings("rawtypes")
public class ReturnPurchaseController implements Initializable, ScreenController {

	private static Logger logger = Logger.getLogger(ReturnPurchaseController.class);

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
	private CheckComboBox<Product> challanPrdctsComboBox;

	
	@FXML
	private TableView<SalePrdctInvoice> returnPrdctLst;

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
	private TableColumn<SalePrdctInvoice,Long> productQty_col;

	// private List<PurchaseProductDtl> purhsdPrdctLst;
	private PurchaseDtls purchaseDtls;
	
	private LongProperty totalLoad =new SimpleLongProperty(0);
	
	private DoubleProperty netAmt=new SimpleDoubleProperty(0);
	
	private DoubleProperty totalBaseAmt = new SimpleDoubleProperty(0);
	
	private DoubleProperty totalSchemeDiscountAmt = new SimpleDoubleProperty(0);
	
	private DoubleProperty totalTaxableAmt = new SimpleDoubleProperty(0);
	
	private DoubleProperty totalCGSTAmt = new SimpleDoubleProperty(0);
	
	private DoubleProperty totalSGSTAmt = new SimpleDoubleProperty(0);
	
	private DoubleProperty totalCessAmt = new SimpleDoubleProperty(0);
	
	private LongProperty deliverBsGlass = new SimpleLongProperty(0);
	
	private LongProperty deliverCell = new SimpleLongProperty(0);

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
		challanSummeryLstTable.getItems().addAll(productService.getAllChallanDetail(Initialization.FULL_EAGER));
	}
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// totalLoadTextFld.textProperty().bindBidirectional(totalLoad,new NumberStringConverter());
		Bindings.bindBidirectional(totalLoadTextFld.textProperty(),totalLoad, new NumberStringConverter());
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
		returnPrdctLst.setEditable(true);
      	productQty_col.setCellValueFactory(new PropertyValueFactory<>("sellingQtyCs"));
		productQty_col.setCellFactory(new Callback<TableColumn<SalePrdctInvoice,Long>, TableCell<SalePrdctInvoice,Long>>() {
			@Override
			public TableCell<SalePrdctInvoice, Long> call(TableColumn<SalePrdctInvoice, Long> param) {
				
				TableCell<SalePrdctInvoice, Long> cell = new EditingTextCell<>(Long.class);
				((EditingTextCell) cell).setCustmCallback(new CustomeTableEditCallback() {
					
					@Override
					public boolean checkValidity(String newVal) {
						return (newVal.matches(ValidationRegex.ONLYDIGIT));
						//return true;
					}
					@Override
					public boolean isValidForEdit() {
						/*int currentIndex = cell.indexProperty().getValue() < 0 ? 0: cell.indexProperty().getValue();
						SalePrdctInvoice salePrdctInvd = param.getTableView().getItems().get(currentIndex);
						return salePrdctInvd.isValidForEditCol();*/
						return true;
					}
					@Override
					public String getValue() {
						return null;
					}
					@Override
					public void postActionAfterEdit(Object obj) {
						int currentIndex = cell.indexProperty().getValue() < 0 ? 0: cell.indexProperty().getValue();
						
						Long newQty = (Long) obj;
						SalePrdctInvoice salePrdctInvd = param.getTableView().getItems().get(currentIndex);
						double basePerCs=salePrdctInvd.getBaseRatePerCs();
						double discntPerCs = salePrdctInvd.getShemeDiscountPerCs();
						long prevSellingQty = salePrdctInvd.getSellingQtyCs();
						
						double cessRate = salePrdctInvd.getCessRate()/100;
						double sgstRate = salePrdctInvd.getSgstRate()/100;
						double cgstRate	= salePrdctInvd.getCgstRate()/100;
						
						double igstRate = salePrdctInvd.getIgstRate()/100;
								
						//double totalTaxRate = cgstRate+sgstRate+cessRate+igstRate;
						//double discntPerCs = (Double) obj;
						//double discntPerCs=salePrdctInvd.getShemeDiscountPerCs();
						double ttlDisntAmt = discntPerCs*newQty;
						double ttlBaseAmt = basePerCs*newQty;
						double ttlTaxableAmt = ttlBaseAmt-ttlDisntAmt;
						double cgstAmt = ttlTaxableAmt*cgstRate;
						double sgstAmt = ttlTaxableAmt*sgstRate;
						double cessAmt = ttlTaxableAmt*cessRate;
						
						double igstAmt = ttlTaxableAmt*igstRate;
						double ttlGrossAmt=ttlTaxableAmt+cgstAmt+sgstAmt+cessAmt+igstAmt;
						double ttlGrossPlusDiscnt=ttlGrossAmt+ttlDisntAmt;
						//double ttlGrossPlusDiscnt = salePrdctInvd.getSysGrossPlusDiscountRatePerCs()*newQty;
						
						//double ttlGrossAmt = ttlGrossPlusDiscnt-ttlDisntAmt;
						//double grossRatePerCs =	ttlGrossAmt/newQty;
						//double ttlTaxableAmt = ttlGrossAmt/(1+totalTaxRate);
						
						
						//double ttlBaseAmt = ttlTaxableAmt+ttlDisntAmt;
						double prevBaseAmt = salePrdctInvd.getNetBaseAmt();
						double prevSchemeDiscnt = salePrdctInvd.getSchemeDisocuntAmt();
						double prevTaxableAmt=salePrdctInvd.getTaxableAmt();
						double preSysnetAmt = salePrdctInvd.getSysGnrtdNetAmt();
						double prevCgstAmt=salePrdctInvd.getCgstAmt();
						double prevSgstAmt=salePrdctInvd.getSgstAmt();
						double prevCessAmt=salePrdctInvd.getCessAmt();
						//double prevIgstAmt=salePrdctInvd.getIgstAmt();
						//double sysSpclDiscnt = newQty*salePrdctInvd.getSysSpecialDiscountPerCs();
						//double prevNetAmtAfterAdjust=salePrdctInvd.getTotalPrdctNetAmt();
						double prdctNetAmtAfterAdjust=ttlGrossPlusDiscnt;
						//double prdctNetAmtAfterAdjust = newQty*salePrdctInvd.getSysGrossPlusDiscountRatePerCs()-sysSpclDiscnt;
						
						
						//double basePerCs = ttlBaseAmt/newQty;
						SalePrdct saleprdct = new SalePrdct();
						saleprdct.setProduct(salePrdctInvd.getSalePrdctSet().get(0).getProduct());
						saleprdct.setSellingQty(newQty,0);
				 		final Integer index = salePrdctInvd.containItem(saleprdct);
				 		salePrdctInvd.postActionAftrSalePrdctAdd(saleprdct, index);
						salePrdctInvd.setBaseRatePerCs(Calculation.decimalRound(basePerCs));
						salePrdctInvd.setNetBaseAmt(Calculation.decimalRound(ttlBaseAmt));
						salePrdctInvd.setShemeDiscountPerCs(Calculation.decimalRound(discntPerCs));
						salePrdctInvd.setTaxableAmt(Calculation.decimalRound(ttlTaxableAmt));
						salePrdctInvd.setCgstAmt(Calculation.decimalRound(cgstAmt));
						salePrdctInvd.setSgstAmt(Calculation.decimalRound(sgstAmt));
						salePrdctInvd.setIgstAmt(Calculation.decimalRound(igstAmt));
						salePrdctInvd.setCessAmt(Calculation.decimalRound(cessAmt));
						//salePrdctInvd.setSysGrossNetPerCs(Calculation.decimalRound(grossRatePerCs));
						salePrdctInvd.setSysGnrtdNetAmt(Calculation.decimalRound(ttlGrossAmt));
						salePrdctInvd.setSchemeDisocuntAmt(Calculation.decimalRound(ttlDisntAmt));
						//salePrdctInvd.setTotalSysGnrtdDiscount(Calculation.decimalRound(sysSpclDiscnt));
						salePrdctInvd.setTotalPrdctNetAmt(Calculation.decimalRound(prdctNetAmtAfterAdjust));
						returnPrdctLst.refresh();
						totalLoad.set(totalLoad.get()-prevSellingQty+newQty);
						totalBaseAmt.set(Utility.decimalRound(totalBaseAmt.get()-prevBaseAmt+salePrdctInvd.getNetBaseAmt()));
						totalSchemeDiscountAmt.set(Utility.decimalRound(totalSchemeDiscountAmt.get()-prevSchemeDiscnt+salePrdctInvd.getSchemeDisocuntAmt()));
						totalTaxableAmt.set(Utility.decimalRound(totalTaxableAmt.get()-prevTaxableAmt+salePrdctInvd.getTaxableAmt()));
						totalCGSTAmt.set(Utility.decimalRound(totalCGSTAmt.get()-prevCgstAmt+salePrdctInvd.getCgstAmt()));
						totalSGSTAmt.set(Utility.decimalRound(totalSGSTAmt.get()-prevSgstAmt+salePrdctInvd.getSgstAmt()));
						totalCessAmt.set(Utility.decimalRound(totalCessAmt.get()-prevCessAmt+salePrdctInvd.getCessAmt()));
						netAmt.set(Utility.decimalRound(netAmt.get()-preSysnetAmt+salePrdctInvd.getSysGnrtdNetAmt()));
						if(ApplicationConstant.RETURNABLE_PACKING_NAME.equalsIgnoreCase(saleprdct.getProduct().getPacking().getPackingName())){
							deliverBsGlass.set(deliverBsGlass.get()-prevSellingQty+salePrdctInvd.getSellingQty());
							deliverCell.set(deliverCell.get()-prevSellingQty+salePrdctInvd.getSellingQtyCs());
						}
						/*
						sellSumData.setSummationBaseAmt(Calculation.decimalRound((Calculation.decimalRound(ttlBaseAmt)-prevBaseAmt+sellSumData.getSummationBaseAmt())));
						sellSumData.setSumSchemeDisntAmt(Calculation.decimalRound((Calculation.decimalRound(ttlDisntAmt)-prevSchemeDiscnt+sellSumData.getSumSchemeDisntAmt())));
						sellSumData.setTotalSummationNetAmt(Calculation.decimalRound((Calculation.decimalRound(ttlGrossAmt)-preSysnetAmt+sellSumData.getTotalSummationNetAmt())));
						sellSumData.setSumTtlSysSpclDisntAmt((Calculation.decimalRound((sysSpclDiscnt+sellSumData.getSumTtlSysSpclDisntAmt()))));
						sellSumData.setSumAfterAdjustNetAmt(Calculation.decimalRound((Calculation.decimalRound(prdctNetAmtAfterAdjust)-prevNetAmtAfterAdjust+sellSumData.getSumAfterAdjustNetAmt())));
						
						sum_table.refresh();*/
						return ;
					}
				});
				return cell;
			}
		});

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
				PurchaseDtls purchaseDtls = productService.getChallanDetailByInvoice(invoice,Initialization.FULL_EAGER);
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
			challanPrdctsComboBox.getItems().clear();
			returnPrdctLst.getItems().clear();
			totalLoad.set(0);
			netAmt.set(0);
			totalBaseAmt.set(0);
			totalSchemeDiscountAmt.set(0);
			totalTaxableAmt.set(0);
			totalCGSTAmt.set(0);
			totalSGSTAmt.set(0);
			totalCessAmt.set(0);
			deliverBsGlass.set(0);
			deliverCell.set(0);
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
			List<PurchaseProductDtl> purhsdPrdctLst = purchaseDtls.getPurchaseProductDtls();
			ObservableList<Product> comboPrdcts = FXCollections.observableArrayList();
			for (PurchaseProductDtl purchases : purhsdPrdctLst) {
				comboPrdcts.add((Product)purchases.getProduct());
			}
			challanPrdctsComboBox.getItems().addAll(comboPrdcts);
			challanPrdctsComboBox.getCheckModel().clearChecks();
		});
		return updtHyperPayLink;
	}

	@FXML
	void addChallanPrdctForReturn(ActionEvent event) {
		// challanPrdctsComboBox.getItemBooleanProperty(index);
		ObservableList<Product> checkedPrdctsLst = challanPrdctsComboBox.getCheckModel().getCheckedItems();
		for (Product prd : checkedPrdctsLst) {
			for (PurchaseProductDtl purchaseProductDtl : purchaseDtls.getPurchaseProductDtls()) {
				boolean duplicateEntry=false;
				if (prd != null && prd.getProductCd().equalsIgnoreCase(((Product) purchaseProductDtl.getProduct()).getProductCd())) {
					if(returnPrdctLst.getItems()!=null && returnPrdctLst.getItems().size()>0){
						for(SalePrdctInvoice tableInvoice : returnPrdctLst.getItems()){
							if(tableInvoice.getPrdctGroupDescription().equalsIgnoreCase(prd.getProductNm())){
								duplicateEntry=true;
								break;
							}
						}
					}
					if(duplicateEntry){
						break;
					}
					SalePrdctInvoice salePrdctInvoice = new SalePrdctInvoice();
					ProductGroup prdctGrp = new ProductGroup();
					prdctGrp.setGroupName(prd.getGroupNm());
					salePrdctInvoice.setProductGroup(prdctGrp);
					SalePrdct salePrdct = new SalePrdct();
					
					salePrdct.setProduct((Product) purchaseProductDtl.getProduct());
					
					salePrdct.setSellingQty(purchaseProductDtl.getProduct_qty(), 0);
					
					totalLoad.set(totalLoad.get()+purchaseProductDtl.getProduct_qty());
					HsnTax hasTax = prd.getHsnTax();
					salePrdctInvoice.setCgstRate(Calculation.decimalRound((hasTax.getCgst() * 100)));
					salePrdctInvoice.setSgstRate(Calculation.decimalRound((hasTax.getSgstOrIgst() * 100)));
					salePrdctInvoice.setCessRate(Calculation.decimalRound((hasTax.getCess() * 100)));
					salePrdctInvoice.setPrdctGroupDescription(purchaseProductDtl.getProduct().getProductNm());
					salePrdctInvoice.postActionAftrSalePrdctAdd(salePrdct, -1);
					salePrdctInvoice.setSysSpecialDiscountPerCs(0.00);
					salePrdctInvoice.setNetBaseAmt(purchaseProductDtl.getTotalBaseAmt());
					totalBaseAmt.set(Utility.decimalRound(totalBaseAmt.get()+purchaseProductDtl.getTotalBaseAmt()));
					salePrdctInvoice.setBaseRatePerCs(purchaseProductDtl.getPerPacketBasePrice());
					salePrdctInvoice.setSchemeDisocuntAmt(purchaseProductDtl.getDiscountAmt());
					totalSchemeDiscountAmt.set(Utility.decimalRound(totalSchemeDiscountAmt.get()+purchaseProductDtl.getDiscountAmt()));
					salePrdctInvoice.setShemeDiscountPerCs(Calculation.decimalRound(purchaseProductDtl.getDiscountAmt() / purchaseProductDtl.getProduct_qty()));
					salePrdctInvoice.setTaxableAmt(purchaseProductDtl.getNetTaxableAmt());
					totalTaxableAmt.set(Utility.decimalRound(totalTaxableAmt.get()+purchaseProductDtl.getNetTaxableAmt()));
					salePrdctInvoice.setCgstAmt(purchaseProductDtl.getTotalPrdctCGSTAmt());
					totalCGSTAmt.set(Utility.decimalRound(purchaseProductDtl.getTotalPrdctCGSTAmt()+totalCGSTAmt.get()));
					salePrdctInvoice.setSgstAmt(purchaseProductDtl.getTotalPrdctSGSTAmt());
					
					if (purchaseProductDtl.getTotalPrdctSGSTAmt() == 0) {
						salePrdctInvoice.setSgstAmt(purchaseProductDtl.getTotalPrdctIGSTAmt());
					}
					totalSGSTAmt.set(Utility.decimalRound(purchaseProductDtl.getTotalPrdctSGSTAmt()+totalSGSTAmt.get()));
					salePrdctInvoice.setCessAmt(purchaseProductDtl.getTotalPrdctCessAmt());
					totalCessAmt.set(Utility.decimalRound(purchaseProductDtl.getTotalPrdctCessAmt()+totalCessAmt.get()));
					salePrdctInvoice.setSysGnrtdNetAmt(purchaseProductDtl.getNetPrdctAmnt());
					salePrdctInvoice.setTotalSysGnrtdDiscount(0);
					salePrdctInvoice.setTotalDiscountAdjustment(0);
					salePrdctInvoice.setTotalAmtAdjustment(0);
					salePrdctInvoice.setTotalPrdctNetAmt(purchaseProductDtl.getNetPrdctAmnt());
					salePrdctInvoice.setHsn(hasTax.getHsnCd());
					netAmt.set(Utility.decimalRound(netAmt.get()+purchaseProductDtl.getNetPrdctAmnt()));
					if(ApplicationConstant.RETURNABLE_PACKING_NAME.equalsIgnoreCase(((Product) purchaseProductDtl.getProduct()).getPacking().getPackingName())){
						deliverBsGlass.set(deliverBsGlass.get()+salePrdctInvoice.getSellingQty());
						deliverCell.set(deliverCell.get()+salePrdctInvoice.getSellingQtyCs());
					}
					returnPrdctLst.getItems().add(salePrdctInvoice);
					break;
				}
			}
		}
	}
	
	@FXML
	void gnrtRtrnPrchseInvoice(ActionEvent event){
		//String challanNumber = challanNumberTextFld.getText();
		//String purchaseInvoiceNo = challanInvoiceNumberTextFld.getText();
		
		String firmNm = firmNmTextFld.getText();
		String address = addressTextFld.getText();
		String firmGstn = firmGstnTextFld.getText();
		//String totalAmount = totalLoadTextFld.getText();
		//Double totalAmount = netAmt.get();
		//long deliverGlass = deliverBsGlass.get();
		//long delivrCell = 	deliverCell.get();
		List<SalePrdctInvoice> choosedprdctLst = returnPrdctLst.getItems();
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
			str.append("Please provide brekage in breakage table. \n");
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
		SalePrdctInvoice delTblInvoice=returnPrdctLst.getItems().get(index);
		totalLoad.set(totalLoad.get()-delTblInvoice.getSellingQtyCs());
		netAmt.set(netAmt.get()-delTblInvoice.getTotalPrdctNetAmt());
		totalSchemeDiscountAmt.set(totalSchemeDiscountAmt.get()-delTblInvoice.getSchemeDisocuntAmt());
		totalTaxableAmt.set(totalTaxableAmt.get()-delTblInvoice.getTaxableAmt());
		if(ApplicationConstant.RETURNABLE_PACKING_NAME.equalsIgnoreCase(delTblInvoice.getSalePrdctSet().get(0).getProduct().getPacking().getPackingName())){
			deliverBsGlass.set(deliverBsGlass.get()+delTblInvoice.getSellingQty());
			deliverCell.set(deliverCell.get()+delTblInvoice.getSellingQtyCs());
		}
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
		productDilogu.setOverlayClose(true);
		productDilogu.show();
		System.out.println(productDilogu.computeAreaInScreen());
	}
	@SuppressWarnings("unchecked")
	public Node loadDialogBody(JFXDialog productDilogu) throws IOException{
		VBox vbox = FXMLLoader.load(getClass().getResource(Components.RETURN_PURCHASE_CONFIRM_FXML));
		System.out.println(headLineHbox.getWidth());
		vbox.setPrefWidth(headLineHbox.getWidth());
		// table view body
		TableView<SalePrdctInvoice> selectedProdctSummery = new TableView<>();
		selectedProdctSummery.setStyle("-fx-pref-height:200.0;-fx-max-width:+Infinity;");
		TableColumn<SalePrdctInvoice, List<SalePrdct>> productCol = new TableColumn<>("Beverage Group Name");
		productCol.setStyle("-fx-pref-width:253.0;");
		TableColumn<SalePrdctInvoice, Long> productQtyCol = new TableColumn<>("Qty");
		productQtyCol.setStyle("-fx-pref-width:351.0;");
		TableColumn<SalePrdctInvoice, Double> amountCol = new TableColumn<>("Amount");
		amountCol.setStyle("-fx-pref-width:149.0;");
		selectedProdctSummery.getColumns().addAll(productCol, productQtyCol, amountCol);
		productCol.setCellValueFactory(new PropertyValueFactory<>("salePrdctSet"));
		/*productCol.setCellFactory(new Callback<TableColumn<SalePrdctInvoice,List<SalePrdct>>, TableCell<SalePrdctInvoice,List<SalePrdct>>>() {
			
			@Override
			public TableCell<SalePrdctInvoice, List<SalePrdct>> call(TableColumn<SalePrdctInvoice, List<SalePrdct>> param) {
				// TODO Auto-generated method stub
				TableCell<SalePrdctInvoice, List<SalePrdct>> cell = new TableCell<SalePrdctInvoice, List<SalePrdct>>() {
					@Override
					protected void updateItem(List<SalePrdct> prdctLst, boolean empty) {
						if(prdctLst!=null){
							setText(empty ? null : prdctLst.get(0).getProduct().getProductNm());
						}
						else{
							setText(empty ? null : null);
						}
					}
				};
				return cell;
			}
		});*/
		productCol.setCellFactory(CellFactoryGenerator.getTextCellFactory((e) -> {
			if(e!=null){
				List<SalePrdct> ls = (List<SalePrdct>) e;
				return ls.get(0).getProduct().getProductNm();
			}
			else{
				return null;
			} 
		}));
		productQtyCol.setCellValueFactory(new PropertyValueFactory<>("sellingQtyCs"));
		amountCol.setCellValueFactory(new PropertyValueFactory<>("totalPrdctNetAmt"));
		//load content
		
		selectedProdctSummery.setItems(returnPrdctLst.getItems());
		TextField dilogFirmName=(TextField) vbox.lookup("#dilogFirmName");
		TextField dilogInvoiceNum=(TextField) vbox.lookup("#dilogInvoiceNum");
		TextField dilogTotalLoad=(TextField) vbox.lookup("#dilogTotalLoad");
		TextField dilogTotalAmt=(TextField) vbox.lookup("#dilogTotalAmt");
		dilogFirmName.setText(firmNmTextFld.getText());
		dilogInvoiceNum.setText(challanInvoiceNumberTextFld.getText());
		dilogTotalLoad.setText(totalLoad.get()+"");
		dilogTotalAmt.setText(netAmt.get()+"");
		Label label = new Label("Selected products");
		label.getStyleClass().add("headlineLable");
		label.setUnderline(true);
		VBox tableVbox = new  VBox(label,selectedProdctSummery);
		tableVbox.setSpacing(5);
		
		JFXButton confirmButton = new JFXButton("Confirm");
		JFXButton cancelButton = new JFXButton("edit");
		JFXButton printInvoiceButton = new JFXButton("print");
		printInvoiceButton.setVisible(false);
		confirmButton.setOnAction( (e)->{
			cancelButton.setDisable(true);
			confirmButton.setDisable(true);
			Sale sale=submitReturnPurchaseCnfrmAction();
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
		
		
		confirmButton.setStyle("-fx-background-color: #3F51B5; -fx-text-fill: white; -fx-font-size: 13px;-fx-pref-width:111.0;");
		cancelButton.setStyle("-fx-background-color: #3F51B5; -fx-text-fill: white; -fx-font-size: 13px;fx-pref-width:111.0;");
		HBox hbox = new HBox(cancelButton,confirmButton,printInvoiceButton);
		hbox.setAlignment(Pos.CENTER);
		hbox.prefHeight(30);
		hbox.setSpacing(10);
		vbox.getChildren().addAll(tableVbox,hbox);
		return vbox;
	}
	
	private Sale  submitReturnPurchaseCnfrmAction(){
		
		String challanNumber = challanNumberTextFld.getText();
		String purchaseInvoiceNo = challanInvoiceNumberTextFld.getText();
		
		String firmNm = firmNmTextFld.getText();
		String address = addressTextFld.getText();
		String firmGstn = firmGstnTextFld.getText();
		//String totalAmount = totalLoadTextFld.getText();
		Double totalAmount = netAmt.get();
		long deliverGlass = deliverBsGlass.get();
		long delivrCell = 	deliverCell.get();
		List<SalePrdctInvoice> choosedprdctLst = returnPrdctLst.getItems();
		SearchBean searchBean = new SearchBean();
		searchBean.setChallanNo(challanNumber);
		searchBean.setChallanInvoiceNo(purchaseInvoiceNo);
		Sale sale = new Sale();
		sale.setCstmrName(firmNm.trim().toUpperCase());
		sale.setAddress(address.trim().toUpperCase());
		sale.setSysGnrtdTotalAmount(totalAmount);
		sale.setSysGnrtdTotalDiscount(0);
		sale.setTotalAmountAdjustment(0);
		sale.setTotalAdjustmentDiscount(0);
		sale.setTotalNetActualAmount(totalAmount);
		sale.setDeliveryMode(ApplicationConstant.DELIVERY_MODE_SHOP);
		sale.setTotalDeliverBsGlass(deliverGlass);
		sale.setTotalDeliverCell(delivrCell);
		sale.setCstmrGstn(firmGstn.trim().toUpperCase());
		sale.setGstn(PropertyResourceBundle.get("GSTN"));
		sale.setSoldBy("CDMS");
		sale.setSaleComments("Company Return Purchase");
		sale.setIsSchemeAlloted("N");
		sale.setIsBreakageReturn("N");
		sale.setInvoicedPrdctDtlsSet(choosedprdctLst);
		sale.setInvoiceType(ApplicationConstant.RETURN_PURCHASE_SALE);
		sale.setPaymentMode("COMPANY");
		sale.setTotalSchemeDiscnt(totalSchemeDiscountAmt.get());
		Calendar cal = Calendar.getInstance();
		String dt=ApplicationConstant.formatter.format(cal.getTime());
		sale.setSaleDt(dt);
		Sale returnSale=null;
		try{
			sale=productService.sellProduct(sale);
			productService.updateRtnPurchaseInvoiceNumber(challanNumber,purchaseInvoiceNo,sale.getSaleInvoiceNo());
			returnSale=sale;
		}
		catch(Exception e){
			returnSale=null;
			logger.fatal("ReturnPurchaseController :: submitReturnPurchaseCnfrmAction :: exception ### "+e.getMessage());
			e.printStackTrace();
		}
		return returnSale;
	}
	
	public void printInvoice(Sale sale){
		List<Sale> saleLst = new ArrayList<>();
		Map<String,Object> jasperParams=new HashMap<>();
		jasperParams.put("totalBaseAmt", totalBaseAmt.get());
		jasperParams.put("totalDiscount", totalSchemeDiscountAmt.get());
		jasperParams.put("totalTaxableAmt", totalTaxableAmt.get());
		jasperParams.put("totalCGSTAmt", totalCGSTAmt.get());
		jasperParams.put("totalSGSTAmt", totalCGSTAmt.get());
		jasperParams.put("totalCessAmt", totalCessAmt.get());
		saleLst.add(sale);
		String heading = "Return Product Invoice";
		try {
			PrintJobScreen<Sale> job = new PrintJobScreen<>(t,heading);
			job.printInvoice_modified(saleLst, jasperParams, Components.RETURN_PRODUCT_INVOICE_TEMPLATE,"/app/temp/file/viewReturnHtml_temp.html");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}