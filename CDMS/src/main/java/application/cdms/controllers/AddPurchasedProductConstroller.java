package application.cdms.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXButton.ButtonType;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialog.DialogTransition;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;

import application.Components;
import application.SystemMessages;
import application.ValidationRegex;
import application.cdms.component.data.handler.AlertDialog;
import application.cdms.component.data.handler.CellFactoryGenerator;
import application.cdms.component.data.handler.CustomeStringConverter;
import application.cdms.component.data.handler.ErrorDialog;
import application.cdms.component.data.handler.SuccessDialog;
import application.cdms.constants.ApplicationConstant;
import application.cdms.models.FirmSeller;
import application.cdms.models.HsnTax;
import application.cdms.models.NonBeveragePrdct;
import application.cdms.models.PaymentMethod;
import application.cdms.models.Product;
import application.cdms.models.ProductBreakageDtl;
import application.cdms.models.PurchaseDtls;
import application.cdms.models.PurchaseProductDtl;
import application.cdms.service.ProductService;
import application.cdms.service.impl.ProductServiceImpl;
import application.cdms.utilities.Calculation;
import application.cdms.utilities.Utility;
import javafx.beans.binding.Bindings;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.converter.NumberStringConverter;

@SuppressWarnings("rawtypes")
public class AddPurchasedProductConstroller implements Initializable, ScreenController {

	private ScreenTransitionController t;
	
	@FXML
	private HBox headLineHbox;
	
	@FXML
	private JFXTextField firmGstn;
	
	@FXML
	private JFXTextField firmName;
	
	@FXML
    private JFXDatePicker challan_date_text;

    @FXML
    private JFXTextField challan_no_textFld;

    @FXML
    private JFXTextField invoiceNoTextFld;

    @FXML
    private JFXTextField challanAmountTextField;

    @FXML
    private JFXTextField totalDiscountTextFld;

    @FXML
    private JFXComboBox<String> paymentMethodComboBox;

    @FXML
    private JFXTextField paymentIdTextFld;

    @FXML
    private JFXTextField paymentAmountTextFld;
	

	@FXML
	TableView<PurchaseProductDtl> purchasedProductsTable;
	

	@FXML
	private TableColumn sr_col;
	
	@FXML
	private TableColumn nB_sr_col;
	
	@FXML
	private TableColumn<PurchaseProductDtl, Product> product_cd_col;

	@FXML
	private TableColumn<PurchaseProductDtl, Product> product_name_col;

	@FXML
	private TableColumn<PurchaseProductDtl, Double> productQty_col;

	@FXML
	private TableColumn<PurchaseProductDtl, ProductBreakageDtl> burst_col;

	@FXML
	private TableColumn<PurchaseProductDtl, ProductBreakageDtl> leakage_col;

	@FXML
	private TableColumn<PurchaseProductDtl, ProductBreakageDtl> shortage_col;

	@FXML
	private TableColumn<PurchaseProductDtl, ProductBreakageDtl> sealpackshortage_col;
	
	@FXML
	private TableColumn<PurchaseProductDtl, ProductBreakageDtl> openmouth_col;

	@FXML
	private TableColumn<PurchaseProductDtl, Double> perCsBasePrice_col;
	
	@FXML
	private TableColumn<PurchaseProductDtl, Double> ttlBasePrice_col;
	
	@FXML
	private TableColumn<PurchaseProductDtl, Double> ttlDiscount_col;
	
	@FXML
	private TableColumn<PurchaseProductDtl, Double> netTaxableAmt_col;
	
	@FXML
	private TableColumn<PurchaseProductDtl, Double> ttlCGSTAmt_col;
	
	@FXML
	private TableColumn<PurchaseProductDtl, Double> ttlSGSTAmt_col;
	
	@FXML
	private TableColumn<PurchaseProductDtl, Double> ttlIGSTAmt_col;
	
	@FXML
	private TableColumn<PurchaseProductDtl, Double> ttlCessAmt_col;
	
	/*@FXML
	private TableColumn<PurchaseProductDtl, Double> netPerCsPrice_col;*/
	
	@FXML
	private TableColumn<PurchaseProductDtl, Double> ttlGrossAmt_col;

	@FXML
	private TableColumn action_col;
	
	@FXML
	private TableColumn nB_action_col;
	
	//@FXML
   // private JFXTextField totalPurchasedPrdctQtyTextFld;

    @FXML
    private JFXTextField TotalGlassQtyTextFld;

    @FXML
    private JFXTextField totalReturnGlassQtyTextFld;
    
    @FXML
    private JFXTextField totalShellQtyTextFld;
    
    @FXML
    private JFXTextField totalBottleQtyTextFld;

    @FXML
    private JFXTextField purchasecomment;
    
    @FXML
    private TableView<AddPurchasedProductConstroller.PurchaseTableSumData> sum_table;
    
    
    @FXML
    private TableColumn<AddPurchasedProductConstroller.PurchaseTableSumData,String> sum_text_nm_col;

    @FXML
    private TableColumn<AddPurchasedProductConstroller.PurchaseTableSumData,Long> sum_productQty_col;

    @FXML
    private TableColumn<AddPurchasedProductConstroller.PurchaseTableSumData,Double> sum_ttl_base_price_col;

    @FXML
    private TableColumn<AddPurchasedProductConstroller.PurchaseTableSumData,Double> sum_ttl_discount_col;

    @FXML
    private TableColumn<AddPurchasedProductConstroller.PurchaseTableSumData,Double> sum_ttl_taxable_amt_col;

    @FXML
    private TableColumn<AddPurchasedProductConstroller.PurchaseTableSumData,Double> sum_ttl_gross_amt_col;
    
    @FXML
    private TableView<PurchaseProductDtl> nB_purchasedProductsTable;
    
    @FXML
    private ComboBox<FirmSeller> cmpnyComboBox;
	
	private ProductService productService = ProductServiceImpl.getInstance();
	
	private ObservableList<Product> productList=productService.productList();
	
	private ObservableList<NonBeveragePrdct> nonBProduct = productService.nonBproductList();
	
	//PubottlePurchasedtl 

	int i = 1;
	
	private final AddPurchasedProductConstroller.PurchaseTableSumData purschaseSum=new PurchaseTableSumData("Total",0L,0.0,0.0,0.0,0.0);
	
	private static Logger logger = Logger.getLogger(AddPurchasedProductConstroller.class);

	@Override
	public void setScreenTransitionController(Object obj) {
		if (obj instanceof ScreenTransitionController) {
			// System.out.println("inside"+obj);
			this.t = (ScreenTransitionController) obj;
		}
		// showAddPersonDialog(t.getRootStage(),purchasedProductsTable,200);
	}
     
	
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cmpnyComboBox.setItems(productService.getAllSellerFirmDtl());
		cmpnyComboBox.valueProperty().addListener( (e) ->{
			FirmSeller firmSeller=cmpnyComboBox.getSelectionModel().getSelectedItem();
			if(firmSeller!=null){
				firmName.setText(firmSeller.getFirmNm());
				firmGstn.setText(firmSeller.getFirmGstnNumber());
				firmName.setDisable(true);
				firmGstn.setDisable(true);
			}
		});
		//cal=Calendar.getInstance();
		challan_date_text.setConverter(new CustomeStringConverter());
		challan_date_text.setDayCellFactory(CellFactoryGenerator.getFutureDayDisableCellFactory());
		ObservableList<String> paymentMethods=FXCollections.observableArrayList();
		for(PaymentMethod paymentWay: PaymentMethod.values()){
			paymentMethods.add(paymentWay.name());
		}
		paymentMethodComboBox.setItems(paymentMethods);
		paymentMethodComboBox.getItems().add(0, "");
		
		//implement on change mechanism 
		
		paymentMethodComboBox.valueProperty().addListener( (e) ->{
			 String paymentMethodEnum =paymentMethodComboBox.getValue();
			 if(paymentMethodEnum==null || paymentMethodEnum.trim().equals("")){
				 paymentAmountTextFld.setDisable(true);
				 paymentIdTextFld.setDisable(true);
			 }
			 else{
				 paymentAmountTextFld.setDisable(false);
				 paymentIdTextFld.setDisable(false);
			 }
		});
		paymentAmountTextFld.setDisable(true);
		paymentIdTextFld.setDisable(true);
		//totalPurchasedPrdctQtyTextFld.setDisable(true);
		//TotalGlassQtyTextFld.setDisable(true);
		
		int purchaseTableColumns = purchasedProductsTable.getColumns().size();
		
		for (int i = 0; i < purchaseTableColumns; i++) {

            TableColumn mainColumn = purchasedProductsTable.getColumns().get(i);
            TableColumn sumColumn = sum_table.getColumns().get(i);

            // sync column widths
            sumColumn.prefWidthProperty().bind(mainColumn.widthProperty());

            // sync visibility
            sumColumn.visibleProperty().bind(mainColumn.visibleProperty());
            }
		
		sr_col.setCellFactory(CellFactoryGenerator.getSrNumberCellFactory());
		
		setSumTableColumns();

		product_name_col.setCellValueFactory(new PropertyValueFactory<>("product"));
		product_name_col.setCellFactory(CellFactoryGenerator.getTextCellFactory( (e) -> { return ((Product) e).getProductNm();}));
		product_cd_col.setCellValueFactory(new PropertyValueFactory<>("product"));
		product_cd_col.setCellFactory(CellFactoryGenerator.getTextCellFactory( (e) -> { return ((Product) e).getProductCd();}));
		productQty_col.setCellValueFactory(new PropertyValueFactory<PurchaseProductDtl,Double>("product_qty"));
		burst_col.setCellValueFactory(new PropertyValueFactory<>("braekageDtl"));
		burst_col.setCellFactory(CellFactoryGenerator.getTextCellFactory( (e) -> { return ((ProductBreakageDtl) e).getBurst()+"";}));
		leakage_col.setCellValueFactory(new PropertyValueFactory<>("braekageDtl"));
		leakage_col.setCellFactory(CellFactoryGenerator.getTextCellFactory( (e) -> { return ((ProductBreakageDtl) e).getLeakage()+"";}));
		shortage_col.setCellValueFactory(new PropertyValueFactory<>("braekageDtl"));
		shortage_col.setCellFactory(CellFactoryGenerator.getTextCellFactory( (e) -> { return ((ProductBreakageDtl) e).getShortage()+"";}));
		sealpackshortage_col.setCellValueFactory(new PropertyValueFactory<>("braekageDtl"));
		sealpackshortage_col.setCellFactory(CellFactoryGenerator.getTextCellFactory( (e) -> { return ((ProductBreakageDtl) e).getSealPackShortage()+"";}));
		openmouth_col.setCellValueFactory(new PropertyValueFactory<>("braekageDtl"));
		openmouth_col.setCellFactory(CellFactoryGenerator.getTextCellFactory( (e) -> { return ((ProductBreakageDtl) e).getOpenMouth()+"";}));
		perCsBasePrice_col.setCellValueFactory(new PropertyValueFactory<PurchaseProductDtl,Double>("perPacketBasePrice"));
		ttlBasePrice_col.setCellValueFactory(new PropertyValueFactory<PurchaseProductDtl,Double>("totalBaseAmt"));
		ttlDiscount_col.setCellValueFactory(new PropertyValueFactory<PurchaseProductDtl,Double>("discountAmt"));
		netTaxableAmt_col.setCellValueFactory(new PropertyValueFactory<PurchaseProductDtl,Double>("netTaxableAmt"));
		ttlCGSTAmt_col.setCellValueFactory(new PropertyValueFactory<PurchaseProductDtl,Double>("totalPrdctCGSTAmt"));
		ttlSGSTAmt_col.setCellValueFactory(new PropertyValueFactory<PurchaseProductDtl,Double>("totalPrdctSGSTAmt"));
		ttlIGSTAmt_col.setCellValueFactory(new PropertyValueFactory<PurchaseProductDtl,Double>("totalPrdctIGSTAmt"));
		ttlCessAmt_col.setCellValueFactory(new PropertyValueFactory<PurchaseProductDtl,Double>("totalPrdctCessAmt"));
		//netPerCsPrice_col.setCellValueFactory(new PropertyValueFactory<PurchaseProductDtl,Double>("netPerCsPrice"));
		ttlGrossAmt_col.setCellValueFactory(new PropertyValueFactory<PurchaseProductDtl,Double>("netPrdctAmnt"));
		
		action_col.setCellFactory(new Callback<TableColumn, TableCell>() {
			@Override
			public TableCell call(TableColumn param) {
				return new TableCell() {
					@Override
					public void updateItem(Object item, boolean empty) {
						ImageView deleteImage = new ImageView((Components.DELETE_IMAGE));
						deleteImage.setFitHeight(24.0);
						Hyperlink deletelink = new Hyperlink("",deleteImage);
						AlertDialog alertDiloag = new AlertDialog();
						deletelink.setOnMouseClicked( (e) ->{
							//calculation of totalLoad and totalPurchased Glass
							JFXButton yesButton = new JFXButton("YES");
							yesButton.setOnAction(new EventHandler<ActionEvent>() {
								@Override
								public void handle(ActionEvent arg0) {
									int index = getTableRow().getIndex();
									PurchaseProductDtl purchaseProductDtl=(PurchaseProductDtl) getTableView().getItems().get(index);
									long prodctQtyForDel=(long) purchaseProductDtl.getProduct_qty();
									long totalLoad=purschaseSum.getTotalqty();
									//long totalLoad=totalLoadStr!=null && !totalLoadStr.equals("")?Long.parseLong(totalLoadStr):0;
									totalLoad=totalLoad-prodctQtyForDel;
									purschaseSum.setTotalqty(totalLoad);
									
									
									double prevSumTtlBaseAmt = purschaseSum.getSummationBaseAmt();
									double newSumTtlBaseAmt = prevSumTtlBaseAmt-purchaseProductDtl.getTotalBaseAmt();
									purschaseSum.setSummationBaseAmt(Calculation.decimalRound(newSumTtlBaseAmt));
									double prevSumDiscntAmt = purschaseSum.getSummationDiscountAmt();
									double newSumDiscntAmt = prevSumDiscntAmt-purchaseProductDtl.getDiscountAmt();
									purschaseSum.setSummationDiscountAmt(Calculation.decimalRound(newSumDiscntAmt));
									double prevSumTtlTaxableAmt = purschaseSum.getSummationTaxableAmt();
									double newSumTaxableAmt = prevSumTtlTaxableAmt-purchaseProductDtl.getNetTaxableAmt();
									purschaseSum.setSummationTaxableAmt(Calculation.decimalRound(newSumTaxableAmt));
									double prevSumGrossAmt = purschaseSum.getTotalSummationGrossAmt();
									double newSumGrossAmt = prevSumGrossAmt-purchaseProductDtl.getNetPrdctAmnt();
									purschaseSum.setTotalSummationGrossAmt(Calculation.decimalRound(newSumGrossAmt));
									sum_table.getItems().set(0, purschaseSum);
									
									
									//totalPurchasedPrdctQtyTextFld.setText(totalLoad+"");
									if(ApplicationConstant.RETURNABLE_PACKING_NAME.equals(((Product)purchaseProductDtl.getProduct()).getPacking().getPackingName())){
										String totalPurchasedGlassStr=TotalGlassQtyTextFld.getText();
										long totalPurchaseGlass=totalPurchasedGlassStr!=null && !totalPurchasedGlassStr.equals("")?Long.parseLong(totalPurchasedGlassStr):0;
										totalPurchaseGlass=totalPurchaseGlass-prodctQtyForDel;
										//TotalGlassQtyTextFld.setText(totalPurchaseGlass+"");
										purschaseSum.setTotalShellQty(purschaseSum.getTotalShellQty()-prodctQtyForDel);
										purschaseSum.setTotalBottleQty(purschaseSum.getTotalBottleQty()-prodctQtyForDel*((Product)purchaseProductDtl.getProduct()).getPackingQty().getPackingQuantity());
									}
									getTableView().getItems().remove(index);
									alertDiloag.closeAlertDialog();
								}
							});
							
							JFXButton noButton = new JFXButton("NO");
							ObservableList<JFXButton> alertbutnLst = FXCollections.observableArrayList(); 
							alertbutnLst.add(yesButton);
							alertbutnLst.add(noButton);
							alertDiloag.showAlertDilogue(((StackPane) t.getCurrentNode()), "Do you want to remove the row?", alertbutnLst);
							noButton.setOnAction(new EventHandler<ActionEvent>() {
								@Override
								public void handle(ActionEvent arg0) {
									alertDiloag.closeAlertDialog();
								}
							});
						});
						ImageView edit = new ImageView(Components.EDIT_IMAGE);
						edit.setFitHeight(24.0);
						Hyperlink editlink = new Hyperlink("",edit);
						HBox pane = new HBox(deletelink,editlink);
						setGraphic(empty ? null:pane);
					}
				};
			}
			
		});
		purschaseSum.doBindings();
		nB_sr_col.setCellFactory(CellFactoryGenerator.getSrNumberCellFactory());
		nB_action_col.setCellFactory(CellFactoryGenerator.getHiperLink2CellFactory(index->{
			Hyperlink hyper = new Hyperlink("Update Price");
			hyper.setOnMouseClicked( (e) ->{
				JFXDialog productDilogu=null;
				JFXDialogLayout dilogLayout = new JFXDialogLayout();
				Label heading = new Label("Update purchase price");
				heading.setStyle("-fx-font-size: 22px;-fx-text-fill:#3F51B5");
				dilogLayout.setHeading(heading);
				//dilogLayout.setStyle("-fx-background-color: #e5eaf5;");//#3F51B5;
				productDilogu = new JFXDialog(((StackPane) t.getCurrentNode()), dilogLayout, DialogTransition.CENTER);
				dilogLayout.setBody(loadDialogNonBeveBody(productDilogu,index));
				//dilogLayout.setPrefWidth(700);
				productDilogu.setOverlayClose(false);
				productDilogu.show();
			});
			return hyper;
		}));
	}
	
	/****  Adding New Row of Product  */
	
	ObjectProperty<Product> prodObjectProp=null;
	StringProperty productCd=null;
	StringProperty productQtyProp=null;
	StringProperty burstProp=null;
	StringProperty leakageProp=null;
	StringProperty shortageProp=null;
	StringProperty sealProp=null;
	StringProperty openMouthProp=null;
	StringProperty ttlBaseAmountProp=null;
	StringProperty discountProp=null;
	StringProperty taxableAmntProp=null;
	StringProperty csgtProp=null;
	StringProperty sgstProp=null;
	StringProperty cessProp=null;
	StringProperty igstProp=null;
	StringProperty grossAmntProp=null;
	StringProperty dilogMrpProp=null;
	JFXDialog productDilogu=null;
	@FXML
    void addNewRowToTable(ActionEvent event) {
		//showAddPersonDialog(t.getRootStage(),purchasedProductsTable,100);((StackPane) t.getCurrentNode())
		showDialogAddNewProductRow(((StackPane) t.getCurrentNode()));
    }
	
	public void showDialogAddNewProductRow(StackPane stackPane){
		JFXDialogLayout dilogLayout = new JFXDialogLayout();
		Label heading = new Label("Purchased Product");
		heading.setStyle("-fx-font-size: 22px;-fx-text-fill:#3F51B5");
		dilogLayout.setHeading(heading);
		productDilogu = new JFXDialog(stackPane, dilogLayout, DialogTransition.CENTER);
		//dilogLayout.setStyle("-fx-background-color: #e5eaf5;");//#3F51B5;
		try {
			dilogLayout.setBody(loadDialogBody());
			//dilogLayout.setPrefWidth(700);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		productDilogu.setOverlayClose(false);
		productDilogu.show();
		productDilogu.boundsInParentProperty();
	}
	
	public Node loadDialogBody() throws IOException{
		VBox vbox = FXMLLoader.load(getClass().getResource(Components.PURCHASED_PRODUCT_DIALOG));
		System.out.println("Dialog width and height ### "+vbox.computeAreaInScreen());
		prodObjectProp=new SimpleObjectProperty<>(); 
		productCd=new SimpleStringProperty();
		productQtyProp=new SimpleStringProperty();
		burstProp=new SimpleStringProperty();
		leakageProp=new SimpleStringProperty();
		shortageProp=new SimpleStringProperty();
		sealProp=new SimpleStringProperty();
		openMouthProp=new SimpleStringProperty();
		ttlBaseAmountProp=new SimpleStringProperty();
		discountProp=new SimpleStringProperty();
		taxableAmntProp=new SimpleStringProperty();
		csgtProp=new SimpleStringProperty();
		sgstProp=new SimpleStringProperty();
		cessProp=new SimpleStringProperty();
		igstProp=new SimpleStringProperty();
		grossAmntProp=new SimpleStringProperty();
		dilogMrpProp = new SimpleStringProperty();
		Map<String,Property> bindPropMap=new HashMap<String,Property>();
		bindPropMap.put("dilogproductCombo", prodObjectProp);
		bindPropMap.put("dilogProductCd", productCd);
		bindPropMap.put("dilogproductQty", productQtyProp);
		bindPropMap.put("dilogburst", burstProp);
		bindPropMap.put("dilogLeakage", leakageProp);
		bindPropMap.put("dilogShortage", shortageProp);
		bindPropMap.put("dilogSealPackShortageQty", sealProp);
		bindPropMap.put("dilogOpenMouth", openMouthProp);
		bindPropMap.put("dilogTtlBaseAmount", ttlBaseAmountProp);
		bindPropMap.put("dilogDiscount", discountProp);
		bindPropMap.put("dilogTaxableAmnt", taxableAmntProp);
		bindPropMap.put("dilogcsgt", csgtProp);
		bindPropMap.put("dilogSgst", sgstProp);
		bindPropMap.put("dilogCess", cessProp);
		bindPropMap.put("dilogIgst", igstProp);
		bindPropMap.put("dilogGrossAmnt", grossAmntProp);
		bindPropMap.put("dilogMrp", dilogMrpProp);
		Map<String,TextField> tobeAddedListnerNode=new HashMap<>();
		bindAllProp(vbox,bindPropMap,tobeAddedListnerNode);
	
		tobeAddedListnerNode.get("dilogDiscount").textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				if(ttlBaseAmountProp.get()!=null && !ttlBaseAmountProp.get().equals("") && discountProp.get()!=null && !discountProp.get().equals("")){
					//System.out.println(ttlBaseAmountProp.get());
					HsnTax hsnTax=prodObjectProp.get().getHsnTax();
					System.out.println(hsnTax.getCgst());
					System.out.println(hsnTax.getSgstOrIgst());
					System.out.println(hsnTax.getCess());
					double bseAmnt = Double.parseDouble(ttlBaseAmountProp.get());
					double disAmnt = Double.parseDouble(discountProp.get());
					double taxableAmt=bseAmnt-disAmnt;
					double cgst = taxableAmt*hsnTax.getCgst();
					double sgst = taxableAmt*hsnTax.getSgstOrIgst();
					double cess = taxableAmt*hsnTax.getCess();
					double grossAmt = taxableAmt+cgst+sgst+cess;
					tobeAddedListnerNode.get("dilogTaxableAmnt").setText(Calculation.decimalRound(taxableAmt)+"");
					tobeAddedListnerNode.get("dilogcsgt").setText(Calculation.decimalRound(cgst)+"");
					tobeAddedListnerNode.get("dilogSgst").setText(Calculation.decimalRound(sgst)+"");
					tobeAddedListnerNode.get("dilogCess").setText(Calculation.decimalRound(cess)+"");
					tobeAddedListnerNode.get("dilogGrossAmnt").setText(Calculation.decimalRound(grossAmt)+"");
				}
			}
		});
		
		
		return vbox;
	}
	
	//calling recursive function
	public void bindAllProp(Node parentNode,Map<String,?> bindingProp,Map<String,TextField> tobeAddedListnerNode){
		if(parentNode instanceof Pane){
			Pane pane=(Pane) parentNode;
			ObservableList<Node> nodeList=pane.getChildren();
			if(nodeList!=null && nodeList.size()>0){
				for(Node node:nodeList){
					bindAllProp(node, bindingProp,tobeAddedListnerNode);
				}
			}
		}
		else{
			if(parentNode instanceof TextField){
				TextField txtFld = (TextField) parentNode;
				String str=txtFld.getAccessibleText();
			//System.out.println(str);
				StringProperty strProp=(StringProperty) bindingProp.get(str);
				strProp.bind(txtFld.textProperty());
				tobeAddedListnerNode.put(str, txtFld);
				//strProp.bindBidirectional(txtFld.textProperty());
			}
			else if(parentNode instanceof ComboBox<?>){
				@SuppressWarnings("unchecked")
				ComboBox<Product> comboBox = (ComboBox<Product>) parentNode;
				comboBox.setItems(productList);
				String str=comboBox.getAccessibleText();
				@SuppressWarnings("unchecked")
				ObjectProperty<Product> objectProperty=(ObjectProperty<Product>) bindingProp.get(str);
				objectProperty.bind(comboBox.valueProperty());
			}
			else if(parentNode instanceof Button){
				Button button = (Button) parentNode;
				String buttonType=button.getText();
				if("Add".equals(buttonType)){
					button.setOnAction((e) ->{
						addProductIntoTable();
					});
				}
				else if("Cancel".equals(buttonType)){
					button.setOnAction((e) ->{
						closeDialog();
					});
				}
			}
		}
	}
	
	private void addProductIntoTable(){
		boolean isError=false;
		Product product=prodObjectProp.get();
		String productQtyStr=productQtyProp.get();
		String burstStr=burstProp.get();
		String leakageStr=leakageProp.get();
		String shortageStr=shortageProp.get();
		String sealPackStr=sealProp.get();
		String openMouthStr=openMouthProp.get();
		String ttlBaseAmountStr=ttlBaseAmountProp.get();
		String discountStr=discountProp.get();
		String taxableAmntStr=taxableAmntProp.get();
		String csgtStr=csgtProp.get();
		String sgstStr=sgstProp.get();
		String cessStr=cessProp.get();
		String igstStr=igstProp.get();
		String grossAmntStr=grossAmntProp.get();
		String mrp = dilogMrpProp.get();
		StringBuilder strBuild = new StringBuilder();
		//List<Node> errortext = new ArrayList<Node>(); 
		if(product==null){
			strBuild.append("Please select product name. \n");
			//errortext.add(new Text("Please select product name"));
			isError=true;
		}
		if(productQtyStr==null || productQtyStr.trim().equals("")){
			strBuild.append("Please fill product quantity. \n");
			//errortext.add(new Text("Please fill product quantity."));
			isError=true;
		}
		else if(!productQtyStr.matches(ValidationRegex.ONLYDIGIT)){
			strBuild.append("Only digits are allowed in Product (Qty). \n");
			//errortext.add(new Text("Only digits are allowed in Product (Qty)."));
			isError=true;
		}
		if(burstStr==null || burstStr.trim().equals("")){
			burstStr="0";
		}
		else if(!burstStr.matches(ValidationRegex.ONLYDIGIT)){
			strBuild.append("Only digits are allowed in Burst(B/s). \n");
			//errortext.add(new Text("Only digits are allowed in Burst(B/s)."));
			isError=true;
		}
		if(leakageStr==null || leakageStr.trim().equals("")){
			leakageStr="0";
		}
		else if(!leakageStr.matches(ValidationRegex.ONLYDIGIT)){
			strBuild.append("Only digits are allowed in Leakage(B/s). \n");
			//errortext.add(new Text("Only digits are allowed in Leakage(B/s)."));
			isError=true;
		}
		if(shortageStr==null || shortageStr.trim().equals("")){
			shortageStr="0";
		}
		else if(!shortageStr.matches(ValidationRegex.ONLYDIGIT)){
			strBuild.append("Only digits are allowed in Leakage(B/s). \n");
			//errortext.add(new Text("Only digits are allowed in Leakage(B/s)."));
			isError=true;
		}
		if(sealPackStr==null || sealPackStr.trim().equals("")){
			sealPackStr="0";
		}
		else if(!sealPackStr.matches(ValidationRegex.ONLYDIGIT)){
			strBuild.append("Only digits are allowed in SealPackShortage(B/s). \n");
			//errortext.add(new Text("Only digits are allowed in SealPackShortage(B/s)."));
			isError=true;
		}
		if(openMouthStr==null || openMouthStr.trim().equals("")){
			openMouthStr="0";
		}
		else if(!openMouthStr.matches(ValidationRegex.ONLYDIGIT)){
			strBuild.append("Only digits are allowed in Open Mouth(B/s). \n");
			//errortext.add(new Text("Only digits are allowed in Open Mouth(B/s)."));
			isError=true;
		}
		if(ttlBaseAmountStr==null || ttlBaseAmountStr.trim().equals("")){
			strBuild.append("Please fill product Total Base Amount. \n");
			//errortext.add(new Text("Please fill product Total Base Amount."));
			isError=true;
		}
		else if(!ttlBaseAmountStr.matches(ValidationRegex.DOUBLENUMBERCHECK)){
			strBuild.append("Please input valid Total Base Amount. \n");
			//errortext.add(new Text("Please input valid Total Base Amount."));
			isError=true;
		}
		if(discountStr==null || discountStr.trim().equals("")){
			discountStr="0.00";
		}
		else if(!discountStr.matches(ValidationRegex.DOUBLENUMBERCHECK)){
			strBuild.append("Please enter valid discount amount. \n");
			//errortext.add(new Text("Please enter valid discount amount."));
			isError=true;
		}
		if(taxableAmntStr==null || taxableAmntStr.trim().equals("")){
			strBuild.append("Please fill product total taxable amount. \n");
			//errortext.add(new Text("Please fill product total taxable amount."));
			isError=true;
		}
		else if(!taxableAmntStr.matches(ValidationRegex.DOUBLENUMBERCHECK)){
			strBuild.append("Please enter valid total taxable amount. \n");
			//errortext.add(new Text("Please enter valid total taxable amount."));
			isError=true;
		}
		if(csgtStr==null || csgtStr.trim().equals("")){
			strBuild.append("Please fill CGST amount. \n");
			//errortext.add(new Text("Please fill CGST amount"));
			isError=true;
		}
		else if(!csgtStr.matches(ValidationRegex.DOUBLENUMBERCHECK)){
			strBuild.append("Please enter valid CGST amount. \n");
			//errortext.add(new Text("Please enter valid CGST amount."));
			isError=true;
		}
		if((sgstStr==null || sgstStr.trim().equals("")) && (igstStr==null || igstStr.trim().equals(""))){
			strBuild.append("Please fill SGST or IGST amount. \n");
			//errortext.add(new Text("Please fill SGST or IGST amount"));
			isError=true;
		}
		else if(sgstStr==null || sgstStr.trim().equals("")){
			sgstStr="0.00";
			if(!igstStr.matches(ValidationRegex.DOUBLENUMBERCHECK)){
				strBuild.append("Please enter valid IGST amount. \n");
				//errortext.add(new Text("Please enter valid IGST amount"));
				isError=true;
			}
		}
		else if(igstStr==null || igstStr.trim().equals("")){
			igstStr="0.00";
			if(!sgstStr.matches(ValidationRegex.DOUBLENUMBERCHECK)){
				strBuild.append("Please enter valid SGST amount. \n");
				//errortext.add(new Text("Please enter valid SGST amount"));
				isError=true;
			}
		}
		if(cessStr==null || cessStr.trim().equals("")){
			cessStr="0.00";
		}
		else if(!cessStr.matches(ValidationRegex.DOUBLENUMBERCHECK)){
			strBuild.append("Please enter valid CGST amount. \n");
			//errortext.add(new Text("Please enter valid CGST amount."));
			isError=true;
		}
		if(grossAmntStr==null || grossAmntStr.trim().equals("")){
			strBuild.append("Please fill gross amount. \n");
			//errortext.add(new Text("Please fill gross amount."));
			isError=true;
		}
		else if(!grossAmntStr.matches(ValidationRegex.DOUBLENUMBERCHECK)){
			strBuild.append("Please enter valid gross amount. \n");
			//errortext.add(new Text("Please enter valid gross amount."));
			isError=true;
		}
		if(mrp==null || mrp.trim().equals("")){
			strBuild.append("Please fill Product MRP. \n");
			//errortext.add(new Text("Please fill gross amount."));
			isError=true;
		}
		else if(!mrp.matches(ValidationRegex.DOUBLENUMBERCHECK)){
			strBuild.append("Please enter valid MRP. \n");
			//errortext.add(new Text("Please enter valid gross amount."));
			isError=true;
		}
		if(isError){
			ErrorDialog.showErrorDilogue(new Text(strBuild.toString()), ((StackPane) t.getCurrentNode()),SystemMessages.validation_heading);
			return;
		}
		double baseAmt=Double.parseDouble(ttlBaseAmountStr);
		long purchsQty=Long.parseLong(productQtyStr);
		double basePerPacket = baseAmt/purchsQty;
		double discntAmt=Double.parseDouble(discountStr);
		double taxableAmt= Double.parseDouble(taxableAmntStr);
		double cgstAmt=Double.parseDouble(csgtStr);
		double sgstAmt=Double.parseDouble(sgstStr);
		double igstAmt=Double.parseDouble(igstStr);
		double cessAmt=Double.parseDouble(cessStr);
		double grossAmt=Double.parseDouble(grossAmntStr);
		double netPerPacketAmt = grossAmt/purchsQty;
		double checkGrossAmt = baseAmt-discntAmt+cgstAmt+sgstAmt+igstAmt+cessAmt;
		double mrpAmt = Double.parseDouble(mrp);
		if(Calculation.decimalRound(checkGrossAmt)!=Calculation.decimalRound(grossAmt)){
			ErrorDialog.showErrorDilogue(new Text("Gross amount calculation is wrong.Please do correct calculation."), ((StackPane) t.getCurrentNode()),SystemMessages.validation_heading);
			return ;
		}
		
		if(product!=null){
			PurchaseProductDtl purchaseProductDtl = new PurchaseProductDtl();
			purchaseProductDtl.setProduct(product);
			purchaseProductDtl.setProduct_qty(purchsQty);
			ProductBreakageDtl productBreakageDtl = new ProductBreakageDtl();
			productBreakageDtl.setBurst(Integer.parseInt(burstStr));
			productBreakageDtl.setLeakage(Integer.parseInt(leakageStr));
			productBreakageDtl.setShortage(Integer.parseInt(shortageStr));
			productBreakageDtl.setSealPackShortage(Integer.parseInt(sealPackStr));
			productBreakageDtl.setOpenMouth(Integer.parseInt(openMouthStr));
			productBreakageDtl.setBreakageSource("BUY_BREAKAGE");
			productBreakageDtl.setProduct(product);
			//productBreakageDtl
			purchaseProductDtl.setBraekageDtl(productBreakageDtl);
			//amount
			purchaseProductDtl.setPerPacketBasePrice(Calculation.decimalRound(basePerPacket));
			purchaseProductDtl.setNetPerCsPrice(Calculation.decimalRound(netPerPacketAmt));
			
			purchaseProductDtl.setTotalBaseAmt(baseAmt);
			purchaseProductDtl.setDiscountAmt(discntAmt);
			purchaseProductDtl.setNetTaxableAmt(taxableAmt);
			purchaseProductDtl.setTotalPrdctCGSTAmt(cgstAmt);
			purchaseProductDtl.setTotalPrdctSGSTAmt(sgstAmt);
			purchaseProductDtl.setTotalPrdctIGSTAmt(igstAmt);
			purchaseProductDtl.setTotalPrdctCessAmt(cessAmt);
			purchaseProductDtl.setNetPrdctAmnt(grossAmt);
			purchaseProductDtl.setMrp(mrpAmt);
			//calculation of totalLoad and totalPurchased Glass
//			/String totalLoadStr=totalPurchasedPrdctQtyTextFld.getText();
			long totalLoad=purschaseSum.getTotalqty();
			totalLoad=totalLoad+Long.parseLong(productQtyStr);
			//totalPurchasedPrdctQtyTextFld.setText(totalLoad+"");
			purschaseSum.setTotalqty(totalLoad);
			double prevSumTtlBaseAmt = purschaseSum.getSummationBaseAmt();
			double newSumTtlBaseAmt = prevSumTtlBaseAmt+baseAmt;
			purschaseSum.setSummationBaseAmt(Calculation.decimalRound(newSumTtlBaseAmt));
			double prevSumDiscntAmt = purschaseSum.getSummationDiscountAmt();
			double newSumDiscntAmt = prevSumDiscntAmt+discntAmt;
			purschaseSum.setSummationDiscountAmt(Calculation.decimalRound(newSumDiscntAmt));
			double prevSumTtlTaxableAmt = purschaseSum.getSummationTaxableAmt();
			double newSumTaxableAmt = prevSumTtlTaxableAmt+taxableAmt;
			purschaseSum.setSummationTaxableAmt(Calculation.decimalRound(newSumTaxableAmt));
			double prevSumGrossAmt = purschaseSum.getTotalSummationGrossAmt();
			double newSumGrossAmt = prevSumGrossAmt+grossAmt;
			purschaseSum.setTotalSummationGrossAmt(Calculation.decimalRound(newSumGrossAmt));
			sum_table.getItems().set(0, purschaseSum);
			if(ApplicationConstant.RETURNABLE_PACKING_NAME.equals(product.getPacking().getPackingName())){
				purschaseSum.setTotalBottleQty(purschaseSum.getTotalBottleQty()+product.getPackingQty().getPackingQuantity()*purchsQty);
				purschaseSum.setTotalShellQty(purschaseSum.getTotalShellQty()+purchsQty);
				String totalPurchasedGlassStr=TotalGlassQtyTextFld.getText();
				long totalPurchaseGlass=totalPurchasedGlassStr!=null && !totalPurchasedGlassStr.equals("")?Long.parseLong(totalPurchasedGlassStr):0;
				totalPurchaseGlass=totalPurchaseGlass+Integer.parseInt(productQtyStr);
				TotalGlassQtyTextFld.setText(totalPurchaseGlass+"");
			}
			ObservableList<PurchaseProductDtl> purchasedPrdctLst=purchasedProductsTable.getItems();//;.add(purchaseProductDtl);
			purchasedPrdctLst.add(purchaseProductDtl);
			productDilogu.close();
			System.out.println(product.getProductNm());
		}
		else{
			System.out.println("Not choosen");
		}
	}
	
	private void closeDialog(){
		productDilogu.close();
	}
	
	
	@FXML
	public void resetCompanyFldButton(ActionEvent e){
		cmpnyComboBox.setValue(null);
		firmGstn.setDisable(false);
		firmName.setDisable(false);
		firmName.setText("");
		firmGstn.setText("");
	}
	
	/****  End New Row of Product  */
	
	@FXML
	public void deleteRowFrmTable(ActionEvent event){
		if(purchasedProductsTable.getItems().size()==0){
			return ;
		}
		AlertDialog alertDiloag = new AlertDialog();
		JFXButton yesButton = new JFXButton("YES");
		yesButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				int index = purchasedProductsTable.getSelectionModel().getSelectedIndex();
				PurchaseProductDtl purchaseProductDtl=(PurchaseProductDtl) purchasedProductsTable.getItems().get(index);
				long prodctQtyForDel=(long) purchaseProductDtl.getProduct_qty();
				long totalLoad=purschaseSum.getTotalqty();
				//long totalLoad=totalLoadStr!=null && !totalLoadStr.equals("")?Long.parseLong(totalLoadStr):0;
				totalLoad=totalLoad-prodctQtyForDel;
				purschaseSum.setTotalqty(totalLoad);
				
				
				double prevSumTtlBaseAmt = purschaseSum.getSummationBaseAmt();
				double newSumTtlBaseAmt = prevSumTtlBaseAmt-purchaseProductDtl.getTotalBaseAmt();
				purschaseSum.setSummationBaseAmt(Calculation.decimalRound(newSumTtlBaseAmt));
				double prevSumDiscntAmt = purschaseSum.getSummationDiscountAmt();
				double newSumDiscntAmt = prevSumDiscntAmt-purchaseProductDtl.getDiscountAmt();
				purschaseSum.setSummationDiscountAmt(Calculation.decimalRound(newSumDiscntAmt));
				double prevSumTtlTaxableAmt = purschaseSum.getSummationTaxableAmt();
				double newSumTaxableAmt = prevSumTtlTaxableAmt-purchaseProductDtl.getNetTaxableAmt();
				purschaseSum.setSummationTaxableAmt(Calculation.decimalRound(newSumTaxableAmt));
				double prevSumGrossAmt = purschaseSum.getTotalSummationGrossAmt();
				double newSumGrossAmt = prevSumGrossAmt-purchaseProductDtl.getNetPrdctAmnt();
				purschaseSum.setTotalSummationGrossAmt(Calculation.decimalRound(newSumGrossAmt));
				sum_table.getItems().set(0, purschaseSum);
				
				
				//totalPurchasedPrdctQtyTextFld.setText(totalLoad+"");
				if(ApplicationConstant.RETURNABLE_PACKING_NAME.equals(((Product)purchaseProductDtl.getProduct()).getPacking().getPackingName())){
					String totalPurchasedGlassStr=TotalGlassQtyTextFld.getText();
					long totalPurchaseGlass=totalPurchasedGlassStr!=null && !totalPurchasedGlassStr.equals("")?Long.parseLong(totalPurchasedGlassStr):0;
					totalPurchaseGlass=totalPurchaseGlass-prodctQtyForDel;
					TotalGlassQtyTextFld.setText(totalPurchaseGlass+"");
					purschaseSum.setTotalShellQty(purschaseSum.getTotalShellQty()-prodctQtyForDel);
					purschaseSum.setTotalBottleQty(purschaseSum.getTotalBottleQty()-prodctQtyForDel*((Product)purchaseProductDtl.getProduct()).getPackingQty().getPackingQuantity());
				}
				purchasedProductsTable.getItems().remove(index);
				alertDiloag.closeAlertDialog();
			}
		});
		
		JFXButton noButton = new JFXButton("NO");
		ObservableList<JFXButton> alertbutnLst = FXCollections.observableArrayList(); 
		alertbutnLst.add(yesButton);
		alertbutnLst.add(noButton);
		alertDiloag.showAlertDilogue(((StackPane) t.getCurrentNode()), "Do you want to remove the row?", alertbutnLst);
		noButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				alertDiloag.closeAlertDialog();
			}
		});
	}
	
	@FXML
    public void addPurchaseddtls(ActionEvent event) {
		((JFXButton) event.getSource()).setDisable(true);
		String firmNameStr=null;
		String firmGstnStr=null;
		String challanDt=null;
		String challanNo=null;
		String invoiceNo=null;
		//String challanAmount=null;
		//String discountAmount=null;
		String paymentMethod=null;
		String paymentId=null;
		String paidAmount=null;
		String totalGlassQty=null;
		String totalReturnGlassQty=null;
		String purchasedComment=null;
		//String totalPurchasedProduct=null;
		ArrayList<Node> errorTextlst=new ArrayList<>();
		boolean isError=false;
		try{
			firmNameStr=firmName.getText();
			firmGstnStr=firmGstn.getText();
			challanDt=challan_date_text.getConverter().toString(challan_date_text.getValue());
			challanNo=challan_no_textFld.getText();
			invoiceNo=invoiceNoTextFld.getText();
			//challanAmount=challanAmountTextField.getText();
			//discountAmount=totalDiscountTextFld.getText();
			paymentMethod=paymentMethodComboBox.getValue();
			paymentId=paymentIdTextFld.getText();
			paidAmount=paymentAmountTextFld.getText();
			totalGlassQty=TotalGlassQtyTextFld.getText();
			totalReturnGlassQty=totalReturnGlassQtyTextFld.getText();
			purchasedComment=purchasecomment.getText();
			ObservableList<PurchaseProductDtl> buyPrdctLst=purchasedProductsTable.getItems();
			ObservableList<PurchaseProductDtl> nonBBuyPrdctLst=nB_purchasedProductsTable.getItems();
			//totalPurchasedProduct=totalPurchasedPrdctQtyTextFld.getText();
			if(firmNameStr==null || "".equals(firmNameStr.trim())){
				errorTextlst.add(new Text("Please provide Firm Name."));
				isError=true;
			}
			if(firmGstnStr==null || "".equals(firmGstnStr.trim())){
				errorTextlst.add(new Text("Please provide Firm GSTN Number."));
				isError=true;
			}
			if(challanDt==null || "".equals(challanDt.trim())){
				errorTextlst.add(new Text("Please provide challan date."));
				isError=true;
			}
			if(challanNo==null || "".equals(challanNo.trim())){
				errorTextlst.add(new Text("Please provide challan number in input field."));
				isError=true;
			}
			if(invoiceNo==null || "".equals(invoiceNo.trim())){
				errorTextlst.add(new Text("Please provide invoice number in input field."));
				isError=true;
			}
			if(buyPrdctLst.size()==0){
				errorTextlst.add(new Text("purchase book cannot be empty."));
				isError=true;
			}
			if(paymentMethod!=null && !paymentMethod.equals("")){
				if(paidAmount==null || paidAmount.trim().equals("") || paidAmount.trim().equals("0") || paidAmount.trim().equals("0.0")){
					errorTextlst.add(new Text("Payment amount is mandatory after choosing payment method."));
					isError=true;
				}
				else if(!Pattern.matches(ValidationRegex.DOUBLENUMBERCHECK,paidAmount)){
					errorTextlst.add(new Text("value in Payment amount field is invalid."));
				}
				if(paymentId==null || paymentId.trim().equals("")){
					errorTextlst.add(new Text("Paymend id is mandatory field after choosing payment method."));
					isError=true;
				}
			}
			
			if(paidAmount==null || paidAmount.trim().equals("")){
				paidAmount="0.0";
			}
			if(totalGlassQty==null || totalGlassQty.trim().equals("")){
				totalGlassQty="0";
			}
			else if(!Pattern.matches(ValidationRegex.DOUBLENUMBERCHECK, totalGlassQty)){
				errorTextlst.add(new Text("value in total Glass Qty field is invalid."));
				isError=true;
			}
			if(totalReturnGlassQty==null || totalReturnGlassQty.trim().equals("")){
				errorTextlst.add(new Text("Please enter return glass qty."));
				isError=true;
				//totalReturnGlassQty="0";
			}
			else if(!Pattern.matches(ValidationRegex.DOUBLENUMBERCHECK, totalReturnGlassQty)){
				errorTextlst.add(new Text("value in total Return Glass Qty field is invalid."));
				isError=true;
			}
			if(isError){
				ErrorDialog.showErrorDilogue(errorTextlst, ((StackPane) t.getCurrentNode()),SystemMessages.validation_heading);
				((JFXButton) event.getSource()).setDisable(false);
				return ;
			}
			
			//set these all with object 
			logger.info("AddPurchasedProductConstroller :: addPurchaseddtls :: No input error found and all input are correct.");
			PurchaseDtls purchaseDtls = new PurchaseDtls();
			purchaseDtls.setFirmNm(firmNameStr.trim().toUpperCase());
			purchaseDtls.setFirmGstn(firmGstnStr.trim().toUpperCase());
			purchaseDtls.setChallanDt(challanDt);
			purchaseDtls.setChallanNumber(challanNo);
			purchaseDtls.setChallanInvoiceNumber(invoiceNo.trim().toUpperCase());
			purchaseDtls.setChallanAmount(Calculation.decimalRound(purschaseSum.getTotalSummationGrossAmt()));
			purchaseDtls.setTotalDiscount(Calculation.decimalRound(purschaseSum.getSummationDiscountAmt()));
			purchaseDtls.setPaymentMethod(paymentMethod);
			purchaseDtls.setPaymentId(paymentId.trim().toUpperCase());
			purchaseDtls.setPaidAmount(Double.parseDouble(paidAmount));
			purchaseDtls.setPurchaseProductDtls(buyPrdctLst);
			purchaseDtls.setNonBeverageProdctsDtls(nonBBuyPrdctLst);
			purchaseDtls.setPurchaseComment(purchasedComment);
			purchaseDtls.setTotalpurchasedProduct(purschaseSum.getTotalqty());
			purchaseDtls.setTotalGlassQty(Long.parseLong(totalGlassQty));
			purchaseDtls.setBuyingBottleQty(purschaseSum.getTotalBottleQty());
			purchaseDtls.setBuyingCellQty(purschaseSum.getTotalShellQty());
			purchaseDtls.setReturingBottleQty(Long.parseLong(totalReturnGlassQty)*24);
			purchaseDtls.setReturningCellQty(Long.parseLong(totalReturnGlassQty));
			purchaseDtls.setTotalReturnGlassQty(Long.parseLong(totalReturnGlassQty));
			logger.info("AddPurchasedProductConstroller :: addPurchaseddtls :: Payment Method ### "+paymentMethod);
			logger.info("AddPurchasedProductConstroller :: addPurchaseddtls :: saving purchase details start");
			productService.addPurchaseDtls(purchaseDtls);
			logger.info("AddPurchasedProductConstroller :: addPurchaseddtls :: saving purchase details succesfully");
			SuccessDialog.showSuccessMessage(null, ((StackPane) t.getCurrentNode()),"Purchase Detail has been saved sucessfully.");
		}
		catch(Exception e){
			logger.fatal("AddPurchasedProductConstroller :: addPurchaseddtls :: exception ### "+e.getMessage());
			Text text=new Text(e.getMessage());
			ErrorDialog.showErrorDilogue(text,((StackPane) t.getCurrentNode()),SystemMessages.exception_Heading);
			((JFXButton) event.getSource()).setDisable(false);
			e.printStackTrace();
			return;
		}

    }
	
	private Pane loadDialogNonBeveBody(JFXDialog dialog,int index){
		GridPane gridpane = new GridPane();
		Label basicValLabel = new Label("Unit Price");
		TextField basicVal=new TextField();
		basicVal.setStyle("-fx-pref-height:39.0;-fx-pref-width:270.0");
		ToggleGroup toggleGroup = new ToggleGroup();
		RadioButton withCell = new RadioButton(ApplicationConstant.DELIVERY_TO_SAME_STATE);
		RadioButton withoutCell = new RadioButton(ApplicationConstant.DELIVERY_TO_OTHER_STATE);
		withCell.setToggleGroup(toggleGroup);
		withCell.setUserData(ApplicationConstant.DELIVERY_TO_SAME_STATE);
		withCell.setSelected(true);
		withoutCell.setUserData(ApplicationConstant.DELIVERY_TO_OTHER_STATE);
		withoutCell.setToggleGroup(toggleGroup);
		JFXButton setPrice = new JFXButton("Set Price");
		setPrice.setButtonType(ButtonType.RAISED);
		setPrice.setStyle("-fx-background-color: #3F51B5;;-fx-text-fill:white;-fx-font-size: 13px;-fx-font-family:arial;");
		setPrice.setOnAction( (e) ->{
			String basicValStr = basicVal.getText();
			StringBuilder strbuild = new StringBuilder();
			boolean isError=false;
			
			if(basicValStr==null || basicValStr.trim().equals("") || !basicValStr.matches(ValidationRegex.DOUBLENUMBERCHECK)){
				strbuild.append("please provide valid Basic Amount");
				isError=true;
			}
			if(isError){
				ErrorDialog.showErrorDilogue(new Text(strbuild.toString()), ((StackPane) t.getCurrentNode()),SystemMessages.validation_heading);
				return;
			}
			//addItemTable(prod,qtyStr,basicValStr,mrpStr,withCellObj,cellStr);
			PurchaseProductDtl nonBPurchPrdct=nB_purchasedProductsTable.getItems().get(index);
			long qty=nonBPurchPrdct.getProduct_qty();
			HsnTax hsn=nonBPurchPrdct.getProduct().getHsnTax();
			double totalBasic = Double.parseDouble(basicValStr)*qty;
			double discount = 0;
			double totalTaxable = totalBasic-discount;
			double cgst = hsn.getCgst()*totalTaxable;
			double sgst =0;
			double igst =0;
			if(ApplicationConstant.DELIVERY_TO_SAME_STATE.equalsIgnoreCase(toggleGroup.getSelectedToggle().getUserData().toString())){
				sgst = hsn.getSgstOrIgst()*totalTaxable;
			}
			else{
				igst = hsn.getIgst()*totalTaxable;
			}
			double cess = hsn.getCess()*totalTaxable;
			double grossAmt = totalTaxable+cgst+sgst+igst+cess;
			nonBPurchPrdct.setTotalBaseAmt(Utility.decimalRound(totalBasic));
			nonBPurchPrdct.setDiscountAmt(Utility.decimalRound(discount));
			nonBPurchPrdct.setNetTaxableAmt(Utility.decimalRound(totalTaxable));
			nonBPurchPrdct.setTotalPrdctCGSTAmt(Utility.decimalRound(cgst));
			nonBPurchPrdct.setTotalPrdctSGSTAmt(Utility.decimalRound(sgst));
			nonBPurchPrdct.setTotalPrdctIGSTAmt(Utility.decimalRound(igst));
			nonBPurchPrdct.setTotalPrdctCessAmt(Utility.decimalRound(cess));
			nonBPurchPrdct.setUnitPrice(Double.parseDouble(basicValStr));
			nonBPurchPrdct.setNetPrdctAmnt(Utility.decimalRound(grossAmt));
			nB_purchasedProductsTable.refresh();
			dialog.close();
		});
		JFXButton cancel = new JFXButton("Close");
		cancel.setButtonType(ButtonType.RAISED);
		cancel.setOnAction( (e) ->{
			dialog.close();
		});
		cancel.setStyle("-fx-background-color: #3F51B5;;-fx-text-fill:white;-fx-font-size: 13px;-fx-font-family:arial;");
		gridpane.add(basicValLabel, 0,0);
		gridpane.add(basicVal, 1,0);
		gridpane.add(withCell, 0,1);
		gridpane.add(withoutCell, 1,1);
		gridpane.add(setPrice,0,2);
		gridpane.add(cancel,1,2);
		//GridPane.setConstraints(setPrice, 0, 1, 2, 1, HPos.LEFT, VPos.CENTER);
		//GridPane.setConstraints(cancel, 1, 1, 2, 1, HPos.RIGHT, VPos.CENTER);
		gridpane.setHgap(3);
		gridpane.setVgap(5);
		return gridpane;
	}
	
	@Override
	public void setParams(Map params) {
	
	}
	
	private void setSumTableColumns(){
		sum_text_nm_col.setCellValueFactory(new PropertyValueFactory<>("text"));
		sum_productQty_col.setCellValueFactory(new PropertyValueFactory<>("totalqty"));
		sum_ttl_base_price_col.setCellValueFactory(new PropertyValueFactory<>("summationBaseAmt"));
		sum_ttl_discount_col.setCellValueFactory(new PropertyValueFactory<>("summationDiscountAmt"));
		sum_ttl_taxable_amt_col.setCellValueFactory(new PropertyValueFactory<>("summationTaxableAmt"));
		sum_ttl_gross_amt_col.setCellValueFactory(new PropertyValueFactory<>("totalSummationGrossAmt"));
		ObservableList<AddPurchasedProductConstroller.PurchaseTableSumData> sumData = FXCollections.observableArrayList();
		sumData.add(purschaseSum);
		sum_table.setItems(sumData);
	}
	protected class PurchaseTableSumData{
		private SimpleStringProperty text;
		private SimpleLongProperty totalqty;
		private SimpleDoubleProperty summationBaseAmt;
		private SimpleDoubleProperty summationDiscountAmt;
		private SimpleDoubleProperty summationTaxableAmt;
		private SimpleDoubleProperty totalSummationGrossAmt;
		private LongProperty totalShellQty;
		private LongProperty totalBottleQty;
		public PurchaseTableSumData(String text,Long totalQty,Double summationBaseAmt,Double summationDiscountAmt,Double taxableamt,Double totalSummationNetAmt){
			this.text = new SimpleStringProperty(text);
			this.totalqty= new SimpleLongProperty(totalQty);
			this.summationBaseAmt = new SimpleDoubleProperty(summationBaseAmt);
			this.summationDiscountAmt = new SimpleDoubleProperty(summationDiscountAmt);
			this.summationTaxableAmt = new SimpleDoubleProperty(taxableamt);
			this.totalSummationGrossAmt = new SimpleDoubleProperty(totalSummationNetAmt);
			this.totalShellQty=new SimpleLongProperty(0);
			this.totalBottleQty=new SimpleLongProperty(0);
			totalShellQty.addListener(
				( observable, oldValue,  newValue)-> {
					if(oldValue.longValue()==0){
						PurchaseProductDtl bottlePurchasedtl = new PurchaseProductDtl();
						PurchaseProductDtl shellPurchasedtl = new PurchaseProductDtl();
						bottlePurchasedtl.setProduct(nonBProduct.get(0));
						shellPurchasedtl.setProduct(nonBProduct.get(1));
						bottlePurchasedtl.setProduct_qty(totalBottleQty.get());
						shellPurchasedtl.setProduct_qty(totalShellQty.get());
						if(nB_purchasedProductsTable.getItems().size()==0){
							nB_purchasedProductsTable.getItems().addAll(bottlePurchasedtl,shellPurchasedtl);
						}
						/*else{
							ObservableList<PurchaseProductDtl> nonBPurLst = nB_purchasedProductsTable.getItems();
							int count=0;
							for(PurchaseProductDtl nonBPurchase : nonBPurLst){
								if(count==2){
									break;
								}
								else if(nonBPurchase.getProduct().getProductNm().equals(anObject))
							}
						}*/
					}
					else if(newValue.longValue()>0){
						PurchaseProductDtl bottlePurchasedtl=nB_purchasedProductsTable.getItems().get(0);
						bottlePurchasedtl.setProduct_qty(totalBottleQty.get());
						PurchaseProductDtl shellPurchasedtl=nB_purchasedProductsTable.getItems().get(1);
						shellPurchasedtl.setProduct_qty(totalShellQty.get());
						nB_purchasedProductsTable.refresh();
					}
			});
		}
		public String getText() {
			return text.get();
		}
		public Long getTotalqty() {
			return totalqty.get();
		}
		public Double getSummationBaseAmt() {
			return summationBaseAmt.get();
		}
		public Double getSummationDiscountAmt() {
			return summationDiscountAmt.get();
		}
		public Double getSummationTaxableAmt() {
			return summationTaxableAmt.get();
		}
		public Double getTotalSummationGrossAmt() {
			return totalSummationGrossAmt.get();
		}
		public void setText(String text) {
			this.text.set(text);
		}
		public void setTotalqty(Long totalqty) {
			this.totalqty.set(totalqty);
		}
		public void setSummationBaseAmt(Double summationBaseAmt) {
			this.summationBaseAmt.set(summationBaseAmt);;
		}
		public void setSummationDiscountAmt(Double summationDiscountAmt) {
			this.summationDiscountAmt.set(summationDiscountAmt);
		}
		public void setSummationTaxableAmt(Double summationTaxableAmt) {
			this.summationTaxableAmt.set(summationTaxableAmt);;
		}
		public void setTotalSummationGrossAmt(Double totalSummationGrossAmt) {
			this.totalSummationGrossAmt.set(totalSummationGrossAmt);;
		}
		public Long getTotalShellQty() {
			return this.totalShellQty.get();
		}
		public void setTotalShellQty(Long totalShellQty) {
			this.totalShellQty.set(totalShellQty);
		}
		public Long getTotalBottleQty() {
			return this.totalBottleQty.get();
		}
		public void setTotalBottleQty(Long totalBottleQty) {
			this.totalBottleQty.set(totalBottleQty);
		}
		public void doBindings(){
			Bindings.bindBidirectional(totalShellQtyTextFld.textProperty(), totalShellQty,new NumberStringConverter());
			Bindings.bindBidirectional(totalBottleQtyTextFld.textProperty(), totalBottleQty,new NumberStringConverter());
		}
	}
}