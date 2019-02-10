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
import application.cdms.component.data.handler.SuccessDialog;
import application.cdms.constants.ApplicationConstant;
import application.cdms.models.FirmSeller;
import application.cdms.models.HsnTax;
import application.cdms.models.Product;
import application.cdms.models.ProductGroup;
import application.cdms.models.Sale;
import application.cdms.models.SalePrdct;
import application.cdms.models.SalePrdctInvoice;
import application.cdms.service.ProductService;
import application.cdms.service.impl.ProductServiceImpl;
import application.cdms.utilities.Calculation;
import application.cdms.utilities.PropertyResourceBundle;
import application.cdms.utilities.Utility;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.converter.NumberStringConverter;

@SuppressWarnings("rawtypes")
public class BreakageClaimFormGeneratorController implements Initializable, ScreenController  {

	private static Logger logger = Logger.getLogger(BreakageClaimFormGeneratorController.class);
	
	private ScreenTransitionController<?> t;
	
	private ProductService productService = ProductServiceImpl.getInstance();
	
	@FXML
	private ImageView deleteImage;
	
	private ObservableList<Product> productList=productService.productList();
	@FXML
	private HBox headLineHbox;
	@FXML
	private TableView<SalePrdctInvoice> rtnBreakagePrdctTbl;
	@FXML
	private TableColumn sr_col;
	@FXML
	private TableColumn action_col;
	@FXML
	private JFXTextField firmNmTextFld;

	@FXML
	private JFXTextField addressTextFld;

	@FXML
	private JFXTextField firmGstnTextFld;
	
	@FXML
	private TextField ttlTaxableAmtTextFld;
	
	@FXML
	private TextField ttlNetamtTextFld;
	
	@FXML
	private TextField ttlGlassTextFld;
	
	@FXML
	private TextField ttlCellTextFld;
	
	@FXML
    private ComboBox<FirmSeller> cmpnyComboBox;
	
	//private LongProperty totalLoad =new SimpleLongProperty(0);
	
	private DoubleProperty netAmtProp=new SimpleDoubleProperty(0);
	
	private DoubleProperty totalTaxableAmt = new SimpleDoubleProperty(0);
	
	private DoubleProperty totalSGSTAmt = new SimpleDoubleProperty(0);
	
	private DoubleProperty totalCGSTAmt = new SimpleDoubleProperty(0);
	
	private DoubleProperty totalCessAmt = new SimpleDoubleProperty(0);
	
	
	//private DoubleProperty totalSchemeDiscountAmt = new SimpleDoubleProperty(0);
	
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
		logger.info("loaded BreakageClaimSubmitController");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cmpnyComboBox.setItems(productService.getAllSellerFirmDtl());
		cmpnyComboBox.valueProperty().addListener( (e) ->{
			FirmSeller firmSeller=cmpnyComboBox.getSelectionModel().getSelectedItem();
			if(firmSeller!=null){
				firmNmTextFld.setText(firmSeller.getFirmNm());
				firmGstnTextFld.setText(firmSeller.getFirmGstnNumber());
				firmNmTextFld.setDisable(true);
				firmGstnTextFld.setDisable(true);
			}
		});
		Tooltip.install(deleteImage, new Tooltip("Delete"));
		sr_col.setCellFactory(CellFactoryGenerator.getSrNumberCellFactory());
		action_col.setCellFactory(new Callback<TableColumn, TableCell>() {
			@Override
			public TableCell call(TableColumn param) {
				return new TableCell() {
					@Override
					public void updateItem(Object item, boolean empty) {
						ImageView deleteImagelink = new ImageView((Components.DELETE_IMAGE));
						
						//Hyperlink deletelink = new Hyperlink("", deleteImage);
						deleteImagelink.setStyle("-fx-cursor: hand ;");
						AlertDialog alertDiloag = new AlertDialog();
						deleteImagelink.setOnMouseClicked((e) -> {
							JFXButton yesButton = new JFXButton("YES");
							yesButton.setOnAction((f) -> {
								getTableRow().getIndex();
								//yesButtonAction(index);
								//getTableView().getItems().remove(index);
								alertDiloag.closeAlertDialog();
							});
							JFXButton noButton = new JFXButton("NO");
							ObservableList<JFXButton> alertbutnLst = FXCollections.observableArrayList();
							alertbutnLst.add(yesButton);
							alertbutnLst.add(noButton);
							alertDiloag.showAlertDilogue(((StackPane) t.getCurrentNode()),"Do you want to remove the row?", alertbutnLst);
							noButton.setOnAction(new EventHandler<ActionEvent>() {
								@Override
								public void handle(ActionEvent arg0) {
									alertDiloag.closeAlertDialog();
								}
							});
						});
						HBox pane = new HBox(deleteImagelink);
						setGraphic(empty ? null : pane);
					}
				};
			}
		});
		ttlTaxableAmtTextFld.textProperty().bindBidirectional(totalTaxableAmt,new NumberStringConverter());
		ttlNetamtTextFld.textProperty().bindBidirectional(netAmtProp,new NumberStringConverter());
		ttlGlassTextFld.textProperty().bindBidirectional(deliverBsGlass,new NumberStringConverter());
		ttlCellTextFld.textProperty().bindBidirectional(deliverCell,new NumberStringConverter());
	}
	
	@FXML
	public void resetCompanyFldButton(ActionEvent e){
		cmpnyComboBox.setValue(null);
		firmGstnTextFld.setDisable(false);
		firmNmTextFld.setDisable(false);
		firmNmTextFld.setText("");
		firmGstnTextFld.setText("");
	}
	
	
	@FXML
    void addBreakagePrdctIntoTbl(MouseEvent event) {
		JFXDialog productDilogu=null;
		JFXDialogLayout dilogLayout = new JFXDialogLayout();
		Label heading = new Label("Add Breakage Product");
		heading.setStyle("-fx-font-size: 22px;-fx-text-fill:#3F51B5");
		dilogLayout.setHeading(heading);
		//dilogLayout.setStyle("-fx-background-color: #e5eaf5;");//#3F51B5;
		productDilogu = new JFXDialog(((StackPane) t.getCurrentNode()), dilogLayout, DialogTransition.CENTER);
		dilogLayout.setBody(loadDialogBody(productDilogu));
		productDilogu.setOverlayClose(false);
		productDilogu.show();
	}

	private Pane loadDialogBody(JFXDialog productDilogu){
		
		Label choosePrdctLabel = new Label("Product");
		choosePrdctLabel.setStyle("-fx-pref-height:34;-fx-pref-width:160;-fx-text-fill: #aaa; -fx-font-size: 15px;");
		
		Label qtyLabel = new Label("Qty");
		qtyLabel.setStyle("-fx-pref-height:34;-fx-pref-width:160;-fx-text-fill: #aaa; -fx-font-size: 15px;");
		
		
		Label basicValLabel = new Label("Basic Value");
		basicValLabel.setStyle("-fx-pref-height:34;-fx-pref-width:160;-fx-text-fill: #aaa; -fx-font-size: 15px;");
		
		
		Label mrpLabel = new Label("MRP");//-fx-text-fill: #aaa; -fx-font-size: 15px;
		mrpLabel.setStyle("-fx-pref-height:34;-fx-pref-width:160;-fx-text-fill: #aaa; -fx-font-size: 15px;");
		
		
		Label cellQtyLabel = new Label("Cell Qty");
		cellQtyLabel.setStyle("-fx-pref-height:34;-fx-pref-width:160;-fx-text-fill: #aaa; -fx-font-size: 15px;");
		
		Label rsSymbol = new Label("₹");
		rsSymbol.setStyle("-fx-pref-height:34;-fx-pref-width:83.0;-fx-font-size: 15px;-fx-alignment: CENTER_RIGHT;");
		
		Label rsSymbol2 = new Label("₹");
		rsSymbol2.setStyle("-fx-pref-height:34;-fx-pref-width:83.0;-fx-font-size: 15px;-fx-alignment: CENTER_RIGHT;");
		
		ComboBox<Product> prdctcomboBox = new ComboBox<>();
		prdctcomboBox.setStyle("-fx-pref-height:36.0;-fx-pref-width:270.0");
		prdctcomboBox.setItems(productList);
		
		
		TextField qtyFld=new TextField();
		qtyFld.setStyle("-fx-pref-height:34.0;-fx-pref-width:270.0");
		
		
		TextField basicVal = new TextField();
		basicVal.setStyle("-fx-pref-height:34.0;-fx-pref-width:270.0");
		
		
		TextField mrpFld = new TextField();
		mrpFld.setStyle("-fx-pref-height:34.0;-fx-pref-width:270.0");
		
		
		TextField cellQtyFld=new TextField();
		cellQtyFld.setStyle("-fx-pref-height:34.0;-fx-pref-width:270.0");
		cellQtyFld.setDisable(true);
		
		
		ToggleGroup toggleGroup = new ToggleGroup();
		RadioButton withCell = new RadioButton("with cell");
		RadioButton withoutCell = new RadioButton("without cell");
		withCell.setToggleGroup(toggleGroup);
		withCell.setUserData("Y");
		withoutCell.setUserData("N");
		withoutCell.setToggleGroup(toggleGroup);
		withoutCell.selectedProperty().set(true);
		withCell.setOnAction( (e)->{
			if(withCell.isSelected()){
				cellQtyFld.setDisable(false);
				String qtySt = qtyFld.getText();
				if(!qtySt.matches(ValidationRegex.DOUBLENUMBERCHECK)){
					ErrorDialog.showErrorDilogue(new Text("Please enter valid cell qty"), ((StackPane) t.getCurrentNode()),SystemMessages.validation_heading);
					withCell.setSelected(false);
					return ;
				}
				double qty=Double.parseDouble(qtySt);
				int qtyint = (int) qty;
				if(qtyint==qty){
					
				}
				else{
					qtyint++;
				}
				cellQtyFld.setText(qtyint+"");
			}
		});
		withoutCell.setOnAction( (e)->{
			if(withoutCell.isSelected()){
				cellQtyFld.setText("");
				cellQtyFld.setDisable(true);
			}
		});
		
		
		JFXButton addBreakageToTable = new JFXButton("Insert");
		addBreakageToTable.setButtonType(ButtonType.RAISED);
		addBreakageToTable.setOnAction( (e) ->{
			Product prod = prdctcomboBox.getValue();
			String qtyStr = qtyFld.getText();
			String mrpStr = mrpFld.getText();
			String basicValStr = basicVal.getText();
			Object withCellObj=toggleGroup.getSelectedToggle().getUserData();
			String cellStr = cellQtyFld.getText();
			StringBuilder strbuild = new StringBuilder();
			boolean isError=false;
			if(prod==null){
				strbuild.append("please provide ");
				isError=true;
			}
			if(qtyStr==null || qtyStr.trim().equals("") || !qtyStr.matches(ValidationRegex.DOUBLENUMBERCHECK)){
				strbuild.append("please provide valid qty");
				isError=true;
			}
			if(basicValStr==null || basicValStr.trim().equals("") || !basicValStr.matches(ValidationRegex.DOUBLENUMBERCHECK)){
				strbuild.append("please provide valid Basic Amount");
				isError=true;
			}
			if(mrpStr==null || mrpStr.trim().equals("") || !mrpStr.matches(ValidationRegex.DOUBLENUMBERCHECK)){
				strbuild.append("please provide valid MRP Amount");
				isError=true;
			}
			if(isError){
				ErrorDialog.showErrorDilogue(new Text(strbuild.toString()), ((StackPane) t.getCurrentNode()),SystemMessages.validation_heading);
				return;
			}
			addItemTable(prod,qtyStr,basicValStr,mrpStr,withCellObj,cellStr);
			productDilogu.close();
		});
		
		
		JFXButton cancel = new JFXButton("Close");
		cancel.setButtonType(ButtonType.RAISED);
		cancel.setOnAction( (e) ->{
			productDilogu.close();
		});
		addBreakageToTable.setStyle("-fx-pref-height:29;-fx-pref-width:180.0;-fx-background-color: #3F51B5;;-fx-text-fill:white;-fx-font-size: 13px;-fx-font-family:arial;");
		cancel.setStyle("-fx-pref-height:29;-fx-pref-width:201.0;-fx-background-color: #3F51B5;;-fx-text-fill:white;-fx-font-size: 13px;-fx-font-family:arial;");
		Object[][] nodeDtls = new Object[][] {
			{choosePrdctLabel,0,0,0,0},
			{prdctcomboBox,0,2,0,0},
			{qtyLabel,1,0,0,0},
			{qtyFld,1,2,0,0},
			{basicValLabel,2,0,0,0},
			{rsSymbol,2,1,0,0},
			{basicVal,2,2,0,0},
			{mrpLabel,3,0,0,0},
			{rsSymbol2,3,1,0,0},
			{mrpFld,3,2,0,0},
			{withCell,4,0,0,2},
			{withoutCell,4,2,0,2},
			{cellQtyLabel,5,0,0,0},
			{cellQtyFld,5,2,0,0},
			{addBreakageToTable,6,0,0,3},
			{cancel,6,2,0,2}
		};
		return DialogueCreator.createGridDialogue(nodeDtls, 550);
	}
	
	private boolean addItemTable(Product prod,String qtyStr,String basicVal,String mrpVal,Object shellStatus,String cellStr){
		String isWithCell = ((String) shellStatus).toString();
		HsnTax hsn=prod.getHsnTax();
		double cgstRate=hsn.getCgst();
		double sgstRate=hsn.getSgstOrIgst();
		double cessRate = hsn.getCess();
		String hsncd = hsn.getHsnCd();
		
		double basePerCs=Double.parseDouble(basicVal);
		double mrp = Double.parseDouble(mrpVal);
		double qty=Double.parseDouble(qtyStr);
		int cellQty=0;
		if(cellStr!=null && !"".equals(cellStr)){
			cellQty= Integer.parseInt(cellStr);
		}
		int[] sepratedPktQty =Utility.extractIntFraction(qty);
		
		double basic=Double.parseDouble(basicVal);
		double totalBaseAmt=Utility.calculateProdPrice(qty, prod.getPackingQty().getPackingQuantity(), basic);
		double discount=0.0;
		double taxAmt =totalBaseAmt-discount;
		double cgstAmt = taxAmt*cgstRate;
		double sgstAmt = taxAmt*sgstRate;
		double cessAmt = taxAmt*cessRate;
		double netAmt = taxAmt+cgstAmt+sgstAmt+cessAmt;
		
		
		
		SalePrdctInvoice salePrdctInvoice = new SalePrdctInvoice();
		ProductGroup prdctGrp = new ProductGroup();
		
		prdctGrp.setGroupName(prod.getGroupNm());
		salePrdctInvoice.setProductGroup(prdctGrp);
		SalePrdct salePrdct = new SalePrdct();
		
		salePrdct.setProduct(prod);
		
		salePrdct.setSellingQty(sepratedPktQty[0], sepratedPktQty[1]);
		salePrdctInvoice.postActionAftrSalePrdctAdd(salePrdct, -1);
		salePrdctInvoice.setSaleQtyInCsBs(qty);
		//totalLoad.set(totalLoad.get()+qty);
		salePrdctInvoice.setCgstRate(Calculation.decimalRound((cgstRate* 100)));
		salePrdctInvoice.setSgstRate(Calculation.decimalRound((sgstRate * 100)));
		salePrdctInvoice.setCessRate(Calculation.decimalRound((cessRate* 100)));
		salePrdctInvoice.setPrdctGroupDescription(prod.getProductNm()+'_'+mrp);
		salePrdctInvoice.setMrp(mrp);
		salePrdctInvoice.setSysSpecialDiscountPerCs(0.00);
		salePrdctInvoice.setNetBaseAmt(Utility.decimalRound(totalBaseAmt));
		salePrdctInvoice.setBaseRatePerCs(basePerCs);
		salePrdctInvoice.setSchemeDisocuntAmt(discount);
		//totalSchemeDiscountAmt.set(totalSchemeDiscountAmt.get()+purchaseProductDtl.getDiscountAmt());
		salePrdctInvoice.setShemeDiscountPerCs(0);
		salePrdctInvoice.setTaxableAmt(Utility.decimalRound(taxAmt));
		totalTaxableAmt.set(Utility.decimalRound(totalTaxableAmt.get()+taxAmt));
		salePrdctInvoice.setCgstAmt(Utility.decimalRound(cgstAmt));
		totalCGSTAmt.set(Utility.decimalRound(totalCGSTAmt.get()+cgstAmt));
		salePrdctInvoice.setSgstAmt(Utility.decimalRound(sgstAmt));
		totalSGSTAmt.set(Utility.decimalRound(totalSGSTAmt.get()+sgstAmt));
		/*
		if (purchaseProductDtl.getTotalPrdctSGSTAmt() == 0) {
			salePrdctInvoice.setSgstAmt(purchaseProductDtl.getTotalPrdctIGSTAmt());
		}
		*/
		salePrdctInvoice.setCessAmt(Utility.decimalRound(cessAmt));
		totalCessAmt.set(Utility.decimalRound(totalCessAmt.get()+cessAmt));
		salePrdctInvoice.setSysGnrtdNetAmt(Utility.decimalRound(netAmt));
		salePrdctInvoice.setTotalSysGnrtdDiscount(0);
		salePrdctInvoice.setTotalDiscountAdjustment(0);
		salePrdctInvoice.setTotalAmtAdjustment(0);
		salePrdctInvoice.setTotalPrdctNetAmt(Utility.decimalRound(netAmt));
		netAmtProp.set(Utility.decimalRound(netAmtProp.get()+netAmt));
		
		if(ApplicationConstant.RETURNABLE_PACKING_NAME.equalsIgnoreCase(prod.getPacking().getPackingName())){
			deliverBsGlass.set(deliverBsGlass.get()+salePrdctInvoice.getSellingQty());
			if(isWithCell.equals("Y")){
				deliverCell.set(deliverCell.get()+cellQty);
			}
		}
		salePrdctInvoice.setHsn(hsncd);
		rtnBreakagePrdctTbl.getItems().add(salePrdctInvoice);
		return true;
	}
	public void showconfirmDiloag(){
		JFXDialog productDilogu=null;
		JFXDialogLayout dilogLayout = new JFXDialogLayout();
		Label heading = new Label("Confirm Brekage Product");
		heading.setStyle("-fx-font-size: 22px;-fx-text-fill:#3F51B5");
		dilogLayout.setHeading(heading);
		//dilogLayout.setStyle("-fx-background-color: #e5eaf5;");//#3F51B5;
		productDilogu = new JFXDialog(((StackPane) t.getCurrentNode()), dilogLayout, DialogTransition.CENTER);
		try {
			dilogLayout.setBody(loadConfirmDialogBody(productDilogu));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		productDilogu.setOverlayClose(false);
		productDilogu.show();
	}
	@SuppressWarnings("unchecked")
	public Node loadConfirmDialogBody(JFXDialog dilog) throws IOException{
		VBox vbox = FXMLLoader.load(getClass().getResource(Components.BREAKAGE_CONFIRM_FXML));
		System.out.println(headLineHbox.getWidth());
		vbox.setPrefWidth(headLineHbox.getWidth());
		// table view body
		TableView<SalePrdctInvoice> selectedProdctSummery = new TableView<>();
		selectedProdctSummery.setStyle("-fx-pref-height:200.0;-fx-max-width:900.0;");
		TableColumn<SalePrdctInvoice, List<SalePrdct>> productCol = new TableColumn<>("Beverage Group Name");
		productCol.setStyle("-fx-pref-width:253.0;");
		TableColumn<SalePrdctInvoice, Long> productQtyCol = new TableColumn<>("Qty");
		productQtyCol.setStyle("-fx-pref-width:351.0;");
		TableColumn<SalePrdctInvoice, Double> amountCol = new TableColumn<>("Amount");
		amountCol.setStyle("-fx-pref-width:149.0;");
		selectedProdctSummery.getColumns().addAll(productCol, productQtyCol, amountCol);
		productCol.setCellValueFactory(new PropertyValueFactory<>("salePrdctSet"));
		productCol.setCellFactory(new Callback<TableColumn<SalePrdctInvoice,List<SalePrdct>>, TableCell<SalePrdctInvoice,List<SalePrdct>>>() {
			
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
		});
		
		productQtyCol.setCellValueFactory(new PropertyValueFactory<>("saleQtyInCsBs"));
		amountCol.setCellValueFactory(new PropertyValueFactory<>("totalPrdctNetAmt"));
		//load content
		
		selectedProdctSummery.setItems(rtnBreakagePrdctTbl.getItems());
		TextField dilogFirmName=(TextField) vbox.lookup("#dilogFirmName");
		TextField dilogGstnNo=(TextField) vbox.lookup("#dilogGstnNo");//
		TextField dilogTotalGlass=(TextField) vbox.lookup("#dilogTotalGlass");
		TextField dilogTotalCell=(TextField) vbox.lookup("#dilogTotalCell");
		TextField dilogTotalTaxableAmt=(TextField) vbox.lookup("#dilogTotalTaxableAmt");
		TextField dialogTotalNetAmt=(TextField) vbox.lookup("#dialogTotalNetAmt");
		dilogFirmName.setText(firmNmTextFld.getText());
		dilogGstnNo.setText(firmGstnTextFld.getText());
		dilogTotalGlass.setText(ttlGlassTextFld.getText());
		dilogTotalCell.setText(ttlCellTextFld.getText());
		dilogTotalTaxableAmt.setText(ttlTaxableAmtTextFld.getText());
		dialogTotalNetAmt.setText(ttlNetamtTextFld.getText());
		Label label = new Label("Selected products");
		label.getStyleClass().add("headlineLable");
		label.setUnderline(true);
		VBox tableVbox = new  VBox(label,selectedProdctSummery);
		tableVbox.setSpacing(5);
		JFXButton confirmButton = new JFXButton("Confirm");
		confirmButton.setButtonType(ButtonType.RAISED);
		JFXButton cancelButton = new JFXButton("edit");
		cancelButton.setButtonType(ButtonType.RAISED);
		JFXButton printInvoiceButton = new JFXButton("print");
		printInvoiceButton.setButtonType(ButtonType.RAISED);
		JFXButton closeButton = new JFXButton("Close");
		closeButton.setButtonType(ButtonType.RAISED);
		printInvoiceButton.setVisible(false);
		closeButton.setVisible(false);
		confirmButton.setOnAction( (e)->{
			cancelButton.setDisable(true);
			confirmButton.setDisable(true);
			Sale sale=finalBreakageSubmit();
			if(sale!=null){
				SuccessDialog.showSuccessMessage(null, ((StackPane) t.getCurrentNode()),"Breakage Invoice has been generated successfully.Please click print button for printing.");
				printInvoiceButton.setVisible(true);
				closeButton.setVisible(true);
				printInvoiceButton.setOnAction((e1)->{
					printInvoice(sale);
				});
			
			}
			else{
				cancelButton.setDisable(false);
			}
		});
		cancelButton.setOnAction((e)->{
			dilog.close();
		});
		closeButton.setOnAction( (e) -> {
			dilog.close();
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
	@FXML
	void breakageSubmitAction(ActionEvent event){
		
		String firmNm=firmNmTextFld.getText();
		String address=addressTextFld.getText();
		String gstnStr=firmGstnTextFld.getText();
		List<SalePrdctInvoice> choosedprdctLst=rtnBreakagePrdctTbl.getItems();
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
		if(gstnStr==null || gstnStr.trim().equals("")){
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
		showconfirmDiloag();
	}
	
	private Sale finalBreakageSubmit(){
		String firmNm = firmNmTextFld.getText();
		String address = addressTextFld.getText();
		String firmGstn= firmGstnTextFld.getText();
		List<SalePrdctInvoice> choosedprdctLst = rtnBreakagePrdctTbl.getItems();
		double totalAmount = netAmtProp.get();
		long deliverGlass = deliverBsGlass.get();
		long delivrCell=deliverCell.get();
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
		sale.setSaleComments("Company Breakage Invoice");
		sale.setPaymentMode("COMPANY");
		sale.setIsSchemeAlloted("N");
		sale.setIsBreakageReturn("N");
		sale.setInvoicedPrdctDtlsSet(choosedprdctLst);
		sale.setInvoiceType(ApplicationConstant.RETURN_BREAKAGE_SALE);
		Calendar cal = Calendar.getInstance();
		String dt=ApplicationConstant.formatter.format(cal.getTime());
		sale.setSaleDt(dt);
		sale.setTotalSchemeDiscnt(0);
		Sale returnSale=null;
		try {
			returnSale=productService.storeBreakageClaimDetails(sale);
		} catch (Exception e) {
			returnSale=null;
			logger.fatal("BreakageClaimFormGeneratorController :: finalBreakageSubmit :: exception ### "+e.getMessage());
			e.printStackTrace();
		}
		return returnSale;
	}
	
	public void printInvoice(Sale sale){
		List<Sale> saleLst = new ArrayList<>();
		saleLst.add(sale);
		Map<String,Object> jasperParams= new HashMap<>();
		jasperParams.put("totalTaxableAmt", totalTaxableAmt.get());
		jasperParams.put("totalCGSTAmt", totalCGSTAmt.get());
		jasperParams.put("totalSGSTAmt", totalSGSTAmt.get());
		jasperParams.put("totalCessAmt", totalCessAmt.get());
		try {
			String heading = "Breakage Product Invoice";
			PrintJobScreen<Sale> job = new PrintJobScreen<>(t,heading);
			job.printInvoice_modified(saleLst, jasperParams, Components.RETURN_BREAKAGE_INVOICE_TEMPLATE,"/app/temp/file/viewHtml_temp.html");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}