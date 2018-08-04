package application.cdms.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import application.Components;
import application.SystemMessages;
import application.ValidationRegex;
import application.cdms.component.data.handler.AlertDialog;
import application.cdms.component.data.handler.CellFactoryGenerator;
import application.cdms.component.data.handler.CheckerForTextField;
import application.cdms.component.data.handler.CustomeTableEditCallback;
import application.cdms.component.data.handler.EditingTextCell;
import application.cdms.component.data.handler.ErrorDialog;
import application.cdms.constants.ApplicationConstant;
import application.cdms.models.CstmrLedger;
import application.cdms.models.CustomerDtl;
import application.cdms.models.HsnTax;
import application.cdms.models.Product;
import application.cdms.models.ProductGroup;
import application.cdms.models.ProductPriceSchemeDtl;
import application.cdms.models.Sale;
import application.cdms.models.SaleBrekageDtl;
import application.cdms.models.SalePrdct;
import application.cdms.models.SalePrdctInvoice;
import application.cdms.models.SaleScheme;
import application.cdms.service.CustomerService;
import application.cdms.service.ProductService;
import application.cdms.service.impl.CustomerServiceImpl;
import application.cdms.service.impl.ProductServiceImpl;
import application.cdms.utilities.Calculation;
import application.cdms.utilities.PropertyResourceBundle;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Callback;

@SuppressWarnings("rawtypes")
public class SellProductController implements Initializable, ScreenController{

	private static Logger logger = Logger.getLogger(SellProductController.class);
	
	private ScreenTransitionController<?> t;
	
	@FXML
	private Tab selling_prdct_tab;
	
	@FXML
    private TableColumn sr_col;

    @FXML
    private TableColumn<SalePrdctInvoice,ProductGroup> prdctGroup_col;

    @FXML
    private TableColumn<SalePrdctInvoice,StringBuilder> prdct_col;
    
    @FXML
    private TableColumn<SalePrdctInvoice,String> sale_Qty_Calculation_col;

    @FXML
    private TableColumn<SalePrdctInvoice,Long> sellingPrdctGroupQty_col;

    @FXML
    private TableColumn<SalePrdctInvoice,Double> baseRatePrCs_col;

    @FXML
    private TableColumn<SalePrdctInvoice,Double> netBaseAmt_col;
    
    @FXML
    private TableColumn<SalePrdctInvoice,Double> scheme_discnt_PerCs_col;
    
    @FXML
    private TableColumn<SalePrdctInvoice,Double> scheme_discnt_col;
    
    @FXML
    private TableColumn<SalePrdctInvoice,Double> ttlTaxableAmt_col;

    @FXML
    private TableColumn<SalePrdctInvoice,Double> sgstRate_col;

    @FXML
    private TableColumn<SalePrdctInvoice,Double> sgstAmt_col;

    @FXML
    private TableColumn<SalePrdctInvoice,Double> cgstRate_col;

    @FXML
    private TableColumn<SalePrdctInvoice,Double> cgstAmt_col;
    
    @FXML
    private TableColumn<SalePrdctInvoice,Double> igstRate_col;

    @FXML
    private TableColumn<SalePrdctInvoice,Double> igstAmt_col;

    @FXML
    private TableColumn<SalePrdctInvoice,Double> cessRate_col;

    @FXML
    private TableColumn<SalePrdctInvoice,Double> cessAmt_col;

    @FXML
    private TableColumn<SalePrdctInvoice,Double> netRatePerCs_col;

    @FXML
    private TableColumn<SalePrdctInvoice,Double> netAmt_col;
    
    @FXML
    private TableColumn<SalePrdctInvoice,Double> sysSpclDiscnt_col;
    
    @FXML
    private TableColumn<SalePrdctInvoice,Double> discntAdjustment_col;
    
    @FXML
    private TableColumn<SalePrdctInvoice,Double> amtAdjustment_col;
    
    @FXML
    private TableColumn<SalePrdctInvoice,Double> grossPrdctAmt_col;
	
	@FXML
	private TableView<SalePrdctInvoice> sell_table;
	
	@FXML
	private TableView<SellProductController.SellTableSumData> sum_table;
	
	@FXML
	private TableColumn<SellProductController.SellTableSumData,String> sum_totalText_col;
	
	@FXML
	private TableColumn<SellProductController.SellTableSumData,Integer> sum_totalPrdctQty_col;
	
	@FXML
	private TableColumn<SellProductController.SellTableSumData,Double> sum_totalBaseAmt_col;
	
	@FXML
	private TableColumn<SellProductController.SellTableSumData,Double> sum_schemeDiscnt_col;
	
	@FXML
	private TableColumn<SellProductController.SellTableSumData,Double> sum_totalSysNetAmt_col;
	
	@FXML
	private TableColumn<SellProductController.SellTableSumData,Double> sum_sysSpclDisnt_col;
	
	@FXML
	private TableColumn<SellProductController.SellTableSumData,Double> sum_disntAdjustmnt_col;
	
	@FXML
	private TableColumn<SellProductController.SellTableSumData,Double> sum_amtAdjustment_col;
	
	@FXML
	private TableColumn<SellProductController.SellTableSumData,Double> sum_totalNetAmt_col;
	
	@FXML
	private JFXComboBox<ProductGroup> groupCombox; 
	
	@FXML
    private JFXComboBox<Product> prdctComboBox;
	
	@FXML
    private JFXComboBox<CustomerDtl> cstmrcombox;
	
	@FXML
	private JFXTextField buyerNmTextFld;
	
	@FXML
	private JFXTextField addressTxtFld;
	
	@FXML
    private JFXComboBox<CstmrLedger> cstmrAcntsCombox;
	
	@FXML
	private JFXTextField cstmrledrType;
	
	@FXML
	private JFXTextField acntSpclDiscnt;

    @FXML
    private JFXTextField sellQtyTxtFldCs;
    
    @FXML
    private JFXTextField sellQtyTxtFldBs;
    
    //scheme Tab
    
    @FXML
    private Tab schemeTab;
    
    @FXML
    private JFXComboBox<ProductGroup> schemeForPrdctGroupCombox;

    @FXML
    private JFXComboBox<Product> schemeForPrdctCombox;

    @FXML
    private JFXComboBox<Product> schemePrdctCombox;

    @FXML
    private TableView<SaleScheme> schemeTable;

    @FXML
    private TableColumn scheme_sr_col;

    @FXML
    private TableColumn<SaleScheme,ProductGroup> SchemeForGrp_col;

    @FXML
    private TableColumn<SaleScheme,Product> schemeForPrdct_col;

    @FXML
    private TableColumn<SaleScheme,Product> schemePrdct_col;

    @FXML
    private TableColumn<SaleScheme,Long> qty_col;

    @FXML
    private JFXTextField schemeQtyTxtFld;
    
    //Breakage Tab
    
    @FXML
    private JFXComboBox<Product> breakagePrdctCombox;

    @FXML
    private JFXTextField breakageQtyTxtFld;
    
    @FXML
    private TableView<SaleBrekageDtl> brekageTable;
    
    @FXML
    private TableColumn<SaleBrekageDtl,Product> brekage_Prdct_col;

    @FXML
    private TableColumn<SaleBrekageDtl,Long> breakage_qty_col;
    
    //Payment Tab
    
    @FXML
    private Tab payment_tab;
    
    @FXML
    private JFXTextField saleTtlSysGeneratedAmtTextFld;

    @FXML
    private JFXTextField saleTtlSchemeDiscountTextFld;

    @FXML
    private JFXTextField saleTtlSpecialDiscountTextFld;

    @FXML
    private JFXTextField netAmtSchemeSpecialTotalTextFld;

    @FXML
    private JFXTextField adjustmentAmtTotalTextFld;

    @FXML
    private JFXTextField adjustmentDiscntTotalTextFld;

    @FXML
    private JFXTextField totalGrossAmtTextFld;

    @FXML
    private JFXTextField previousAmt;

    @FXML
    private JFXTextField payableAmtTextFld;

    @FXML
    private JFXTextField paidAmtTextFld;

    @FXML
    private JFXTextField balanceTextFld;

    @FXML
    private JFXTextField givenGlassTextFld;
    
    @FXML
    private JFXTextField extraGivenCellTextFld;
    
    private  long giveGlassQty;
    
    @FXML
    private JFXTextField returnGlassTextFld;
    
    @FXML
    private JFXComboBox<String> deliveryToCombox;
	
	private ProductService productService = ProductServiceImpl.getInstance();
	
	//private MasterService masterService = MasterServiceImpl.getInstance();
	
	private CustomerService customerService = CustomerServiceImpl.getInstance();
	
	private Map<String,ProductPriceSchemeDtl> prdctSchemeMap=null;
	
	Map<String,List<Product>> schemeForPrdctMap = null;
	
	ObservableList<Product> prdctsLst=productService.productList();
	
	private String gstn  = PropertyResourceBundle.get("GSTN");
	
	private String deliveryTo="Same State";
	
	//private boolean isDiscountChanged=false;
	
	private final SellProductController.SellTableSumData sellSumData=new SellProductController.SellTableSumData("Total", 0L,0.00,0.00,0.00,0.00,0.00);
	
	@Override
	public void setScreenTransitionController(Object obj) {
		if (obj instanceof ScreenTransitionController<?>) {
			this.t = (ScreenTransitionController) obj;
		}
	}

	@Override
	public void setParams(Map params) {
		acntSpclDiscnt.focusedProperty().addListener(new CheckerForTextField(acntSpclDiscnt, ValidationRegex.DOUBLENUMBERCHECK,t,"Please provide valid double number."));
		cstmrledrType.setDisable(true);
		sellQtyTxtFldCs.focusedProperty().addListener(new CheckerForTextField(sellQtyTxtFldCs,ValidationRegex.ONLYDIGIT,t,"Please provide valid number."));
        sellQtyTxtFldBs.focusedProperty().addListener(new CheckerForTextField(sellQtyTxtFldBs, ValidationRegex.ONLYDIGIT,t,"Please provide valid number."));
        schemeQtyTxtFld.focusedProperty().addListener(new CheckerForTextField(schemeQtyTxtFld, ValidationRegex.ONLYDIGIT,t,"Please provide valid number."));
        breakageQtyTxtFld.focusedProperty().addListener(new CheckerForTextField(breakageQtyTxtFld, ValidationRegex.ONLYDIGIT,t,"Please provide valid number."));
		saleTtlSysGeneratedAmtTextFld.setDisable(true);
        saleTtlSchemeDiscountTextFld.setDisable(true);
        saleTtlSpecialDiscountTextFld.setDisable(true);
        netAmtSchemeSpecialTotalTextFld.setDisable(true);
		givenGlassTextFld.setDisable(true);
        adjustmentDiscntTotalTextFld.focusedProperty().addListener(new CheckerForTextField(adjustmentDiscntTotalTextFld, ValidationRegex.DOUBLENUMBERCHECK,t,"Please provide valid decimal number."));
        totalGrossAmtTextFld.focusedProperty().addListener(new CheckerForTextField(totalGrossAmtTextFld, ValidationRegex.DOUBLENUMBERCHECK,t,"Please provide valid decimal number."));
        paidAmtTextFld.focusedProperty().addListener(new CheckerForTextField(paidAmtTextFld, ValidationRegex.DOUBLENUMBERCHECK,t,"Please provide valid decimal number."));
        givenGlassTextFld.focusedProperty().addListener(new CheckerForTextField(givenGlassTextFld, ValidationRegex.ONLYDIGIT,t,"Please provide valid number."));
        returnGlassTextFld.focusedProperty().addListener(new CheckerForTextField(returnGlassTextFld, ValidationRegex.ONLYDIGIT,t,"Please provide valid number."));
        extraGivenCellTextFld.focusedProperty().addListener(new CheckerForTextField(returnGlassTextFld, ValidationRegex.ONLYDIGIT,t,"Please provide valid number."));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try{
		//------------------------Start of customer tab
        cstmrcombox.setItems(customerService.getAllCustomers());
        cstmrcombox.valueProperty().addListener( (e) -> {
        	CustomerDtl cstmrDtl = cstmrcombox.getValue();
        	if(cstmrDtl!=null){
        		buyerNmTextFld.setText(cstmrDtl.getCstmrFullname()+" s/o "+cstmrDtl.getCstmrFathername()+" "+cstmrDtl.getTerritory().getTerritoryName());
        		addressTxtFld.setText(cstmrDtl.getTerritory().getTerritoryName());
        		buyerNmTextFld.setDisable(true);
        		addressTxtFld.setDisable(true);
        		ObservableList<CstmrLedger> leds=FXCollections.observableArrayList();
        		leds.addAll(cstmrDtl.getLedgers());
        		cstmrAcntsCombox.setItems(leds);
        		acntSpclDiscnt.setDisable(true);
        	}
        });
        
        cstmrAcntsCombox.valueProperty().addListener( (e) -> {
        	CstmrLedger ledr = cstmrAcntsCombox.getValue();
        	if(ledr!=null){
        		cstmrledrType.setText(ledr.getLedgerType().getLedgerTypeNm());
        		acntSpclDiscnt.setText(ledr.getLedgerType().getDiscountQty()+"");
        		//isDiscountChanged=true;
        	}
        });
        
        deliveryToCombox.valueProperty().addListener( (e) ->{
        	if(sell_table!=null &&sell_table.getItems().size()==0){
        		deliveryTo=deliveryToCombox.getSelectionModel().getSelectedItem();
        	}
        });
        //------------------------End of Cutomer Tab
		
        //------------------------Start of Selling Product
        
		prdctSchemeMap=productService.latestAllSchemePrice();
		 // bind/sync tables
        for (int i = 0; i < sell_table.getColumns().size(); i++) {

            TableColumn mainColumn = sell_table.getColumns().get(i);
            TableColumn sumColumn = sum_table.getColumns().get(i);

            // sync column widths
            sumColumn.prefWidthProperty().bind(mainColumn.widthProperty());

           // sync visibility
            sumColumn.visibleProperty().bind(mainColumn.visibleProperty());
        }
        sell_table.setEditable(true);
        sr_col.setCellFactory(CellFactoryGenerator.getSrNumberCellFactory());
        prdctGroup_col.setCellValueFactory(new PropertyValueFactory<>("productGroup"));
        prdct_col.setCellValueFactory(new PropertyValueFactory<>("prductWithQtyStr"));
        prdct_col.setCellFactory(CellFactoryGenerator.getTextCellFactory( (strBuild)->{return ((StringBuilder)strBuild).toString();} ));
        sale_Qty_Calculation_col.setCellValueFactory(new PropertyValueFactory<>("sellingQtyCalculation"));
        sellingPrdctGroupQty_col.setCellValueFactory(new PropertyValueFactory<SalePrdctInvoice,Long>("sellingQtyCs"));
        baseRatePrCs_col.setCellValueFactory(new PropertyValueFactory<SalePrdctInvoice,Double>("baseRatePerCs"));
        netBaseAmt_col.setCellValueFactory(new PropertyValueFactory<>("netBaseAmt"));
        scheme_discnt_PerCs_col.setCellValueFactory(new PropertyValueFactory<>("shemeDiscountPerCs"));
        scheme_discnt_PerCs_col.setCellFactory(new Callback<TableColumn<SalePrdctInvoice,Double>, TableCell<SalePrdctInvoice,Double>>() {
			@Override
			public TableCell<SalePrdctInvoice, Double> call(TableColumn<SalePrdctInvoice, Double> param) {
				
				TableCell<SalePrdctInvoice, Double> cell = new EditingTextCell<>(Double.class);
				((EditingTextCell) cell).setCustmCallback(new CustomeTableEditCallback() {
					
					@Override
					public boolean checkValidity(String newVal) {
						return (newVal.matches(ValidationRegex.DOUBLENUMBERCHECK));
					}
					@Override
					public boolean isValidForEdit() {
						int currentIndex = cell.indexProperty().getValue() < 0 ? 0: cell.indexProperty().getValue();
						SalePrdctInvoice salePrdctInvd = param.getTableView().getItems().get(currentIndex);
						return salePrdctInvd.isValidForEditCol();
					}
					@Override
					public String getValue() {
						return null;
					}
					@Override
					public void postActionAfterEdit(Object obj) {
						int currentIndex = cell.indexProperty().getValue() < 0 ? 0: cell.indexProperty().getValue();
						SalePrdctInvoice salePrdctInvd = param.getTableView().getItems().get(currentIndex);
						long sellingQty = salePrdctInvd.getSellingQtyCs();
						double cessRate = salePrdctInvd.getCessRate()/100;
						double sgstRate = salePrdctInvd.getSgstRate()/100;
						double cgstRate	= salePrdctInvd.getCgstRate()/100;
						
						double igstRate = salePrdctInvd.getIgstRate()/100;
								
						double totalTaxRate = cgstRate+sgstRate+cessRate+igstRate;
						double discntPerCs = (Double) obj;
						double ttlDisntAmt = discntPerCs*sellingQty;
						double ttlGrossPlusDiscnt = salePrdctInvd.getSysGrossPlusDiscountRatePerCs()*sellingQty; 
						double ttlGrossAmt = ttlGrossPlusDiscnt-ttlDisntAmt;
						double grossRatePerCs =	ttlGrossAmt/sellingQty;
						double ttlTaxableAmt = ttlGrossAmt/(1+totalTaxRate);
						double cgstAmt = ttlTaxableAmt*cgstRate;
						double sgstAmt = ttlTaxableAmt*sgstRate;
						double cessAmt = ttlTaxableAmt*cessRate;
						
						double igstAmt = ttlTaxableAmt*igstRate;
						
						double ttlBaseAmt = ttlTaxableAmt+ttlDisntAmt;
						double prevBaseAmt = salePrdctInvd.getNetBaseAmt();
						double prevSchemeDiscnt = salePrdctInvd.getSchemeDisocuntAmt();
						double preSysnetAmt = salePrdctInvd.getSysGnrtdNetAmt();
						double sysSpclDiscnt = sellingQty*salePrdctInvd.getSysSpecialDiscountPerCs();
						double prevNetAmtAfterAdjust=salePrdctInvd.getTotalPrdctNetAmt();
						double prdctNetAmtAfterAdjust = sellingQty*salePrdctInvd.getSysGrossPlusDiscountRatePerCs()-sysSpclDiscnt;
						
						
						double basePerCs = ttlBaseAmt/sellingQty;
						salePrdctInvd.setBaseRatePerCs(Calculation.decimalRound(basePerCs));
						salePrdctInvd.setNetBaseAmt(Calculation.decimalRound(ttlBaseAmt));
						salePrdctInvd.setShemeDiscountPerCs(Calculation.decimalRound(discntPerCs));
						salePrdctInvd.setTaxableAmt(Calculation.decimalRound(ttlTaxableAmt));
						salePrdctInvd.setCgstAmt(Calculation.decimalRound(cgstAmt));
						salePrdctInvd.setSgstAmt(Calculation.decimalRound(sgstAmt));
						salePrdctInvd.setIgstAmt(Calculation.decimalRound(igstAmt));
						salePrdctInvd.setCessAmt(Calculation.decimalRound(cessAmt));
						salePrdctInvd.setSysGrossNetPerCs(Calculation.decimalRound(grossRatePerCs));
						salePrdctInvd.setSysGnrtdNetAmt(Calculation.decimalRound(ttlGrossAmt));
						salePrdctInvd.setSchemeDisocuntAmt(Calculation.decimalRound(ttlDisntAmt));
						salePrdctInvd.setTotalSysGnrtdDiscount(Calculation.decimalRound(sysSpclDiscnt));
						salePrdctInvd.setTotalPrdctNetAmt(Calculation.decimalRound(prdctNetAmtAfterAdjust));
						
						sellSumData.setSummationBaseAmt(Calculation.decimalRound((Calculation.decimalRound(ttlBaseAmt)-prevBaseAmt+sellSumData.getSummationBaseAmt())));
						sellSumData.setSumSchemeDisntAmt(Calculation.decimalRound((Calculation.decimalRound(ttlDisntAmt)-prevSchemeDiscnt+sellSumData.getSumSchemeDisntAmt())));
						sellSumData.setTotalSummationNetAmt(Calculation.decimalRound((Calculation.decimalRound(ttlGrossAmt)-preSysnetAmt+sellSumData.getTotalSummationNetAmt())));
						sellSumData.setSumTtlSysSpclDisntAmt((Calculation.decimalRound((sysSpclDiscnt+sellSumData.getSumTtlSysSpclDisntAmt()))));
						sellSumData.setSumAfterAdjustNetAmt(Calculation.decimalRound((Calculation.decimalRound(prdctNetAmtAfterAdjust)-prevNetAmtAfterAdjust+sellSumData.getSumAfterAdjustNetAmt())));
						
						sum_table.refresh();
						return ;
					}
				});
				return cell;
			}
		});
        scheme_discnt_col.setCellValueFactory(new PropertyValueFactory<>("schemeDisocuntAmt"));
        
        scheme_discnt_col.setCellFactory(new Callback<TableColumn<SalePrdctInvoice,Double>, TableCell<SalePrdctInvoice,Double>>() {
			@Override
			public TableCell<SalePrdctInvoice, Double> call(TableColumn<SalePrdctInvoice, Double> param) {
				
				TableCell<SalePrdctInvoice, Double> cell = new EditingTextCell<>(Double.class);
				((EditingTextCell) cell).setCustmCallback(new CustomeTableEditCallback() {
					
					@Override
					public boolean checkValidity(String newVal) {
						return (newVal.matches(ValidationRegex.DOUBLENUMBERCHECK));
					}
					@Override
					public boolean isValidForEdit() {
						int currentIndex = cell.indexProperty().getValue() < 0 ? 0: cell.indexProperty().getValue();
						SalePrdctInvoice salePrdctInvd = param.getTableView().getItems().get(currentIndex);
						return salePrdctInvd.isValidForEditCol();
					}
					@Override
					public String getValue() {
						return null;
					}
					@Override
					public void postActionAfterEdit(Object obj) {
						int currentIndex = cell.indexProperty().getValue() < 0 ? 0: cell.indexProperty().getValue();
						SalePrdctInvoice salePrdctInvd = param.getTableView().getItems().get(currentIndex);
						long sellingQty = salePrdctInvd.getSellingQtyCs();
						double cessRate = salePrdctInvd.getCessRate()/100;
						double sgstRate = salePrdctInvd.getSgstRate()/100;
						double cgstRate	= salePrdctInvd.getCgstRate()/100;
						
						double igstRate = salePrdctInvd.getIgstRate()/100;
						
						double totalTaxRate = cgstRate+sgstRate+cessRate+igstRate;
						double ttlDisntAmt = (Double) obj;
						double ttlGrossPlusDiscnt = salePrdctInvd.getSysGrossPlusDiscountRatePerCs()*sellingQty; 
						double ttlGrossAmt = ttlGrossPlusDiscnt-ttlDisntAmt;
						double grossRatePerCs =	ttlGrossAmt/sellingQty;
						double ttlTaxableAmt = ttlGrossAmt/(1+totalTaxRate);
						double cgstAmt = ttlTaxableAmt*cgstRate;
						double sgstAmt = ttlTaxableAmt*sgstRate;
						double cessAmt = ttlTaxableAmt*cessRate;
						
						double igstAmt = ttlTaxableAmt*igstRate;
						
						double ttlBaseAmt = ttlTaxableAmt+ttlDisntAmt;
						double discntPerCs = ttlDisntAmt/sellingQty;
						double basePerCs = ttlBaseAmt/sellingQty;
						double prevBaseAmt = salePrdctInvd.getNetBaseAmt();
						double prevSchemeDiscnt = salePrdctInvd.getSchemeDisocuntAmt();
						double preSysnetAmt = salePrdctInvd.getSysGnrtdNetAmt();
						double sysSpclDiscnt = sellingQty*salePrdctInvd.getSysSpecialDiscountPerCs();
						double prevNetAmtAfterAdjust=salePrdctInvd.getTotalPrdctNetAmt();
						double prdctNetAmtAfterAdjust = sellingQty*salePrdctInvd.getSysGrossPlusDiscountRatePerCs()-sysSpclDiscnt;
						
						salePrdctInvd.setBaseRatePerCs(Calculation.decimalRound(basePerCs));
						salePrdctInvd.setNetBaseAmt(Calculation.decimalRound(ttlBaseAmt));
						salePrdctInvd.setShemeDiscountPerCs(Calculation.decimalRound(discntPerCs));
						salePrdctInvd.setTaxableAmt(Calculation.decimalRound(ttlTaxableAmt));
						salePrdctInvd.setCgstAmt(Calculation.decimalRound(cgstAmt));
						salePrdctInvd.setSgstAmt(Calculation.decimalRound(sgstAmt));
						
						salePrdctInvd.setIgstAmt(Calculation.decimalRound(igstAmt));
						
						salePrdctInvd.setCessAmt(Calculation.decimalRound(cessAmt));
						salePrdctInvd.setSysGrossNetPerCs(Calculation.decimalRound(grossRatePerCs));
						salePrdctInvd.setSysGnrtdNetAmt(Calculation.decimalRound(ttlGrossAmt));
						salePrdctInvd.setSchemeDisocuntAmt(ttlDisntAmt);
						salePrdctInvd.setTotalSysGnrtdDiscount(Calculation.decimalRound(sysSpclDiscnt));
						salePrdctInvd.setTotalPrdctNetAmt(Calculation.decimalRound(prdctNetAmtAfterAdjust));
						
						sellSumData.setSummationBaseAmt(Calculation.decimalRound((Calculation.decimalRound(ttlBaseAmt)-prevBaseAmt+sellSumData.getSummationBaseAmt())));
						sellSumData.setSumSchemeDisntAmt(Calculation.decimalRound((Calculation.decimalRound(ttlDisntAmt)-prevSchemeDiscnt+sellSumData.getSumSchemeDisntAmt())));
						sellSumData.setTotalSummationNetAmt(Calculation.decimalRound((Calculation.decimalRound(ttlGrossAmt)-preSysnetAmt+sellSumData.getTotalSummationNetAmt())));
						sellSumData.setSumTtlSysSpclDisntAmt((Calculation.decimalRound((sysSpclDiscnt+sellSumData.getSumTtlSysSpclDisntAmt()))));
						sellSumData.setSumAfterAdjustNetAmt(Calculation.decimalRound((Calculation.decimalRound(prdctNetAmtAfterAdjust)-prevNetAmtAfterAdjust+sellSumData.getSumAfterAdjustNetAmt())));
						sum_table.refresh();
					}
				});
				return cell;
			}
		});
       	ttlTaxableAmt_col.setCellValueFactory(new PropertyValueFactory<>("taxableAmt"));
       	
        sgstRate_col.setCellValueFactory(new PropertyValueFactory<>("sgstRate"));
        sgstAmt_col.setCellValueFactory(new PropertyValueFactory<>("sgstAmt"));
        
        cgstRate_col.setCellValueFactory(new PropertyValueFactory<>("cgstRate"));
        cgstAmt_col.setCellValueFactory(new PropertyValueFactory<>("cgstAmt"));
        
        cessRate_col.setCellValueFactory(new PropertyValueFactory<>("cessRate"));
        cessAmt_col.setCellValueFactory(new PropertyValueFactory<>("cessAmt"));
        
        igstRate_col.setCellValueFactory(new PropertyValueFactory<>("igstRate"));
        igstAmt_col.setCellValueFactory(new PropertyValueFactory<>("igstAmt"));
        
        netRatePerCs_col.setCellValueFactory(new PropertyValueFactory<>("sysGrossNetPerCs"));
        netAmt_col.setCellValueFactory(new PropertyValueFactory<>("sysGnrtdNetAmt"));
        
        sysSpclDiscnt_col.setCellValueFactory(new PropertyValueFactory<>("totalSysGnrtdDiscount"));
        
        grossPrdctAmt_col.setCellValueFactory(new PropertyValueFactory<>("totalPrdctNetAmt"));
        //sum table columns
        sum_totalText_col.setCellValueFactory(new PropertyValueFactory<>("text"));
        sum_totalPrdctQty_col.setCellValueFactory(new PropertyValueFactory<>("totalqty"));
        sum_totalBaseAmt_col.setCellValueFactory(new PropertyValueFactory<>("SummationBaseAmt"));
        sum_schemeDiscnt_col.setCellValueFactory(new PropertyValueFactory<>("sumSchemeDisntAmt"));
        sum_totalSysNetAmt_col.setCellValueFactory(new PropertyValueFactory<>("totalSummationNetAmt"));
        sum_sysSpclDisnt_col.setCellValueFactory(new PropertyValueFactory<>("sumTtlSysSpclDisntAmt"));
        sum_totalNetAmt_col.setCellValueFactory(new PropertyValueFactory<>("sumAfterAdjustNetAmt"));
        
        ObservableList<SellProductController.SellTableSumData> sumDataLst=FXCollections.observableArrayList();
        sumDataLst.add(sellSumData);
        sum_table.setItems(sumDataLst);
        
        groupCombox.setItems(productService.getAllProductGroup());
        
        groupCombox.valueProperty().addListener( (e) -> {
        	ProductGroup productGroup=groupCombox.getValue();
        	if(productGroup!=null){
        		Product prdct = new Product();
        		prdct.setGroupNm(productGroup.getGroupName());
        		prdctComboBox.getItems().clear();
        		prdctComboBox.setItems(productService.productLstByParams(prdct));
        	}
        });
        //-----------------End of Sale Product------------------------------------------
        
        //-----------------Start of Scheme Tab-------------------------------------------
        schemeTab.setOnSelectionChanged( (e) ->{
        	if(schemeTab.isSelected()){
        		ObservableList<ProductGroup> shemeForBeveragelst=FXCollections.observableArrayList();
        		ObservableList<SalePrdctInvoice> prdctInvoiceList=sell_table.getItems();
        		if(prdctInvoiceList!=null && prdctInvoiceList.size()>0){
        			schemeForPrdctMap=new HashMap<>();
        			for(SalePrdctInvoice salePrdctInvoice : prdctInvoiceList){
            			if(salePrdctInvoice.isValidForEditCol()){
            				shemeForBeveragelst.add(salePrdctInvoice.getProductGroup());
            				List<Product> prdcts = null;
            				if(!schemeForPrdctMap.containsKey(salePrdctInvoice.getProductGroup().getGroupName())){
            					prdcts = new ArrayList<>();
            					schemeForPrdctMap.put(salePrdctInvoice.getProductGroup().getGroupName(), prdcts);
            				}
            				for(SalePrdct salePrdt : salePrdctInvoice.getSalePrdctSet()){
            					prdcts =schemeForPrdctMap.get(salePrdctInvoice.getProductGroup().getGroupName());
            					prdcts.add(salePrdt.getProduct());
            				}
            			}
            		}
        			schemeForPrdctGroupCombox.setItems(shemeForBeveragelst);
        		}
        	}
        });
        schemeForPrdctGroupCombox.valueProperty().addListener( (e) -> {
        	ProductGroup prgrp=schemeForPrdctGroupCombox.getValue();
        	if(prgrp!=null){
        		ObservableList<Product> prlSt = FXCollections.observableList(schemeForPrdctMap.get(prgrp.getGroupName()));
        		schemeForPrdctCombox.setItems(prlSt);
        	}
        });
        schemePrdctCombox.setItems(prdctsLst);
        scheme_sr_col.setCellFactory(new Callback<TableColumn, TableCell>() {
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
		});
        
        
        SchemeForGrp_col.setCellValueFactory(new PropertyValueFactory<>("allotedToGroup"));
        schemePrdct_col.setCellValueFactory(new PropertyValueFactory<>("schemePrdct"));
        schemeForPrdct_col.setCellValueFactory(new PropertyValueFactory<>("allotedToProduct"));
        qty_col.setCellValueFactory(new PropertyValueFactory<>("givenSchemeQtyBs"));
        
        //brekage tab
        breakagePrdctCombox.setItems(prdctsLst);
        
        brekage_Prdct_col.setCellValueFactory(new PropertyValueFactory<>("productCd"));
        breakage_qty_col.setCellValueFactory(new PropertyValueFactory<>("breakageBs"));
        
        
        //payment tab
        payment_tab.setOnSelectionChanged( (e) -> {
        	if(payment_tab.isSelected()){
        		saleTtlSysGeneratedAmtTextFld.setText(sellSumData.getTotalSummationNetAmt()+"");
        		saleTtlSchemeDiscountTextFld.setText(sellSumData.getSumSchemeDisntAmt()+"");
        		saleTtlSpecialDiscountTextFld.setText(sellSumData.getSumTtlSysSpclDisntAmt()+"");
        		netAmtSchemeSpecialTotalTextFld.setText((sellSumData.getTotalSummationNetAmt()+sellSumData.getSumSchemeDisntAmt()-sellSumData.getSumTtlSysSpclDisntAmt())+"");
        		adjustmentAmtTotalTextFld.setText("0.0");
        		adjustmentDiscntTotalTextFld.setText("0.0");
        		totalGrossAmtTextFld.setText((netAmtSchemeSpecialTotalTextFld.getText())+"");
        		givenGlassTextFld.setText(giveGlassQty+"");
        		extraGivenCellTextFld.setText("0");
        	}
        });
        adjustmentAmtTotalTextFld.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				String adjusAmt = adjustmentAmtTotalTextFld.getText();
				if(adjusAmt==null || adjusAmt.trim().equals("")){
					adjustmentAmtTotalTextFld.setText("0.0");
					return ;
				}
				if(adjusAmt!=null && !adjusAmt.matches(ValidationRegex.DOUBLENUMBERCHECK)){
					adjustmentAmtTotalTextFld.setText("0.0");
					return ;
				}
					double grossAmt=Double.parseDouble(netAmtSchemeSpecialTotalTextFld.getText())-Double.parseDouble(adjusAmt);
					totalGrossAmtTextFld.setText(grossAmt+"");
			}
		});
		}
		catch(Exception e){
			logger.fatal("SellProductController :: exception at the time of initialization ### "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	 @FXML
	 void custmrIdReset(ActionEvent event) {
		 cstmrcombox.setValue(null);
		 buyerNmTextFld.setText(null);
		 addressTxtFld.setText(null);
		 cstmrAcntsCombox.setItems(null);
		 cstmrledrType.setText(null);
		 acntSpclDiscnt.setText(null);
		 cstmrAcntsCombox.setItems(null);
		 buyerNmTextFld.setDisable(false);
 		 addressTxtFld.setDisable(false);
 		 acntSpclDiscnt.setDisable(false);
	  }
	 
	 	@FXML
	    void addToSellTable(ActionEvent event) {
	 		String prdctSellqtyCsStr=sellQtyTxtFldCs.getText();
	 		String prdctSellqtyBsStr=sellQtyTxtFldBs.getText();
	 		ProductGroup choosedproductGroup = groupCombox.getValue();
	 		Product choosedsalePrdcts = prdctComboBox.getValue();
	 		
	 		if(choosedproductGroup==null){
	 			//ErrorDialog.showErrorDilogue(new Text("Please choose Be erage Group"), ((StackPane) t.getCurrentNode()),SystemMessages.validation_heading);
	 			t.getErrorNotification("Please choose Beverage Group");
	 			return ;
	 		}
	 		if(choosedsalePrdcts==null){
	 			t.getErrorNotification("Please choose Beverage Product");
	 			return ;
	 		}
	 		if((prdctSellqtyCsStr==null || prdctSellqtyCsStr.trim().equals("")) && (prdctSellqtyBsStr==null || prdctSellqtyBsStr.trim().equals(""))){
	 			t.getErrorNotification("Qty should not be blank");
	 			return ;
	 		}
	 		else{
	 			boolean IsvalidQty=true;
	 			StringBuilder stringBuilder = new StringBuilder();
	 			if((prdctSellqtyCsStr==null || prdctSellqtyCsStr.trim().equals(""))){
	 				prdctSellqtyCsStr="0";
	 			}
	 			else if(!prdctSellqtyCsStr.trim().matches(ValidationRegex.ONLYDIGIT)){
	 				IsvalidQty=false;
	 				sellQtyTxtFldCs.setStyle("-fx-border-color: red;-fx-border-width: 0.0px 0px 4px 0px;-fx-border-style: dashed;");
	 				stringBuilder.append("Enter valid Qty(C/s) \n");
	 			}
	 			if((prdctSellqtyBsStr==null || prdctSellqtyBsStr.trim().equals(""))){
	 				prdctSellqtyBsStr="0";
	 			}
	 			else if(!prdctSellqtyBsStr.trim().matches(ValidationRegex.ONLYDIGIT)){
	 				IsvalidQty=false;
	 				sellQtyTxtFldBs.setStyle("-fx-border-color: red;-fx-border-width: 0.0px 0px 4px 0px;-fx-border-style: dashed;");
	 				stringBuilder.append("Enter valid Qty(B/s) \n");
	 			}
	 			if(!IsvalidQty){
	 				//ErrorDialog.showErrorDilogue(new Text(stringBuilder.toString()), ((StackPane) t.getCurrentNode()),SystemMessages.validation_heading);
	 				t.getErrorNotification(stringBuilder.toString());
	 				return ;
	 			}
	 		}
	 		if(prdctSchemeMap==null || !prdctSchemeMap.containsKey(choosedsalePrdcts.getProductCd())){
	 			ErrorDialog.showErrorDilogue(new Text("Undefined price. Please define price by update product scheme & price link."), ((StackPane) t.getCurrentNode()),SystemMessages.validation_heading);
	 			return ;
	 		}
	 		double prdctPriceWithDiscount=prdctSchemeMap.get(choosedsalePrdcts.getProductCd()).getPrdctPrice();
	 		/*if(prdctPriceWithDiscount){
	 			ErrorDialog.showErrorDilogue(new Text("Undefined price for "+choosedsalePrdcts.getProductNm()), ((StackPane) t.getCurrentNode()),SystemMessages.validation_heading);
	 			return ;
	 		}*/
	 		//sellQtyTxtFldCs.setStyle("-fx-background-color: #FFCDD2;");
	 		ObservableList<SalePrdctInvoice> prdctsInvoicedlst=sell_table.getItems();
	 		SalePrdct salePrdct = new SalePrdct();
	 		salePrdct.setProduct(choosedsalePrdcts);
	 		salePrdct.setSellingQty(Long.parseLong(prdctSellqtyCsStr),Long.parseLong(prdctSellqtyBsStr));
	 		if(prdctsInvoicedlst!=null && prdctsInvoicedlst.size()>0){
	 			int size = prdctsInvoicedlst.size();
	 			for(int i=0;i<size;i++){
	 				SalePrdctInvoice salePrdctInvoice = prdctsInvoicedlst.get(i);
	 				if(salePrdctInvoice.getProductGroup().getGroupName().equals(choosedproductGroup.getGroupName()) && (prdctPriceWithDiscount==salePrdctInvoice.getSysGrossPlusDiscountRatePerCs())){
	 					final Integer index = salePrdctInvoice.containItem(salePrdct);
	 					if(index==null){
		 					sellSumData.setTotalqty(sellSumData.getTotalqty()-salePrdctInvoice.getSellingQtyCs());
		 					sellSumData.setSummationBaseAmt(Calculation.decimalRound(sellSumData.getSummationBaseAmt()-salePrdctInvoice.getNetBaseAmt()));
		 					sellSumData.setSumSchemeDisntAmt(Calculation.decimalRound(sellSumData.getSumSchemeDisntAmt()-salePrdctInvoice.getSchemeDisocuntAmt()));
							sellSumData.setTotalSummationNetAmt(Calculation.decimalRound(sellSumData.getTotalSummationNetAmt()-salePrdctInvoice.getSysGnrtdNetAmt()));
							sellSumData.setSumTtlSysSpclDisntAmt(Calculation.decimalRound(sellSumData.getSumTtlSysSpclDisntAmt()-salePrdctInvoice.getTotalSysGnrtdDiscount()));
							sellSumData.setSumAfterAdjustNetAmt(Calculation.decimalRound((sellSumData.getSumAfterAdjustNetAmt()-salePrdctInvoice.getTotalPrdctNetAmt())));
							if(choosedsalePrdcts.getPacking().getPackingName().equals(ApplicationConstant.RETURNABLE_PACKING_NAME)){
								giveGlassQty-=salePrdctInvoice.getSellingQtyCs();
							}
							salePrdctInvoice.postActionAftrSalePrdctAdd(salePrdct,-1);
		 					sellSumData.setTotalqty(sellSumData.getTotalqty()+salePrdctInvoice.getSellingQtyCs());
		 					if(choosedsalePrdcts.getPacking().getPackingName().equals(ApplicationConstant.RETURNABLE_PACKING_NAME)){
								giveGlassQty+=salePrdctInvoice.getSellingQtyCs();
							}
		 					sell_table.refresh();
		 					sum_table.refresh();
	 					}
	 					else{
							AlertDialog alertDiloag = new AlertDialog();
							JFXButton yesButton = new JFXButton("YES");
							JFXButton noButton = new JFXButton("NO");
							ObservableList<JFXButton> alertbutnLst = FXCollections.observableArrayList(); 
							alertbutnLst.add(yesButton);
							alertbutnLst.add(noButton);
							alertDiloag.showAlertDilogue(((StackPane) t.getCurrentNode()), "Do you want to replace "+salePrdct.getProduct().getProductNm()+" with new qty?", alertbutnLst);
							yesButton.setOnAction(new EventHandler<ActionEvent>() {
								@Override
								public void handle(ActionEvent arg0) {
									sellSumData.setTotalqty(sellSumData.getTotalqty()-salePrdctInvoice.getSellingQtyCs());
				 					sellSumData.setSummationBaseAmt(Calculation.decimalRound(sellSumData.getSummationBaseAmt()-salePrdctInvoice.getNetBaseAmt()));
				 					sellSumData.setSumSchemeDisntAmt(Calculation.decimalRound(sellSumData.getSumSchemeDisntAmt()-salePrdctInvoice.getSchemeDisocuntAmt()));
									sellSumData.setTotalSummationNetAmt(Calculation.decimalRound(sellSumData.getTotalSummationNetAmt()-salePrdctInvoice.getSysGnrtdNetAmt()));
									sellSumData.setSumTtlSysSpclDisntAmt(Calculation.decimalRound(sellSumData.getSumTtlSysSpclDisntAmt()-salePrdctInvoice.getTotalSysGnrtdDiscount()));
									sellSumData.setSumAfterAdjustNetAmt(Calculation.decimalRound((sellSumData.getSumAfterAdjustNetAmt()-salePrdctInvoice.getTotalPrdctNetAmt())));
									if(choosedsalePrdcts.getPacking().getPackingName().equals(ApplicationConstant.RETURNABLE_PACKING_NAME)){
										giveGlassQty-=salePrdctInvoice.getSellingQtyCs();
									}
									salePrdctInvoice.postActionAftrSalePrdctAdd(salePrdct,index);
				 					sellSumData.setTotalqty(sellSumData.getTotalqty()+salePrdctInvoice.getSellingQtyCs());
				 					if(choosedsalePrdcts.getPacking().getPackingName().equals(ApplicationConstant.RETURNABLE_PACKING_NAME)){
										giveGlassQty+=salePrdctInvoice.getSellingQtyCs();
									}
				 					sell_table.refresh();
				 					sum_table.refresh();
				 					alertDiloag.closeAlertDialog();
								}
							});
							noButton.setOnAction(new EventHandler<ActionEvent>() {
								@Override
								public void handle(ActionEvent arg0) {
									alertDiloag.closeAlertDialog();
								}
							});
	 					}
	 					return ;
	 				}
	 			}
	 		}
	 		SalePrdctInvoice saleInvoicePrdcts = new SalePrdctInvoice();
	 		saleInvoicePrdcts.setProductGroup(choosedproductGroup);
	 		//saleInvoicePrdcts.setBaseRatePerCs(300.00);
	 		saleInvoicePrdcts.setSysGrossPlusDiscountRatePerCs(prdctPriceWithDiscount);
	 		HsnTax hasTax = choosedsalePrdcts.getHsnTax();
	 		if(ApplicationConstant.DELIVERY_TO_SAME_STATE.equalsIgnoreCase(deliveryTo)){
	 			saleInvoicePrdcts.setCgstRate(Calculation.decimalRound((hasTax.getCgst()*100)));
	 			saleInvoicePrdcts.setSgstRate(Calculation.decimalRound((hasTax.getSgstOrIgst()*100)));
	 		}
	 		else if(ApplicationConstant.DELIVERY_TO_OTHER_STATE.equalsIgnoreCase(deliveryTo)){
	 			saleInvoicePrdcts.setIgstRate(Calculation.decimalRound((hasTax.getIgst()*100)));
	 		}
	 		saleInvoicePrdcts.setCessRate(Calculation.decimalRound((hasTax.getCess()*100)));
	 		
	 		saleInvoicePrdcts.setSysSpecialDiscountPerCs(acntSpclDiscnt.getText()==null || acntSpclDiscnt.getText().equals("")? 0.00:Double.parseDouble(acntSpclDiscnt.getText()));
	 		saleInvoicePrdcts.postActionAftrSalePrdctAdd(salePrdct,-1);
	 		saleInvoicePrdcts.setPrdctGroupDescription(choosedproductGroup.getGroupName()+prdctPriceWithDiscount);
	 		saleInvoicePrdcts.setHsn(hasTax.getHsnCd());
			sellSumData.setTotalqty(sellSumData.getTotalqty()+saleInvoicePrdcts.getSellingQtyCs());
			if(ApplicationConstant.RETURNABLE_PACKING_NAME.equals(choosedsalePrdcts.getPacking().getPackingName())){
				giveGlassQty+=saleInvoicePrdcts.getSellingQtyCs();
			}
			sum_table.refresh();
	 		prdctsInvoicedlst.add(saleInvoicePrdcts);
	    }
	 	
	 	@FXML
	 	void addSchemeToTable(ActionEvent event){
	 		ProductGroup gr = schemeForPrdctGroupCombox.getValue();
	 		Product pr = schemeForPrdctCombox.getValue();
	 		Product scmpr = schemePrdctCombox.getValue();
	 		String schemeQtyStr = schemeQtyTxtFld.getText();
	 		if(gr==null){
	 			t.getErrorNotification("Please choose Beverage group for which scheme will be chosed!");
	 			return ;
	 		}
	 		if(scmpr==null){
	 			t.getErrorNotification("Please choose scheme item.");
	 			return ;
	 		}
	 		if(schemeQtyStr==null || schemeQtyStr.equals("")){
	 			t.getErrorNotification("Scheme Qty(B/s) cannot be blank!");
	 			schemeQtyTxtFld.setStyle("-fx-border-color: red;-fx-border-width: 0.0px 0px 4px 0px;-fx-border-style: dashed;");
	 			return ;
	 		}
	 		ObservableList<SaleScheme> listSaleScheme =schemeTable.getItems(); 
	 		for(SaleScheme slschm: listSaleScheme){
	 			if(slschm.getAllotedToGroup().getGroupName().equals(gr.getGroupName()) && slschm.getSchemePrdct().getProductCd().equals(scmpr.getProductCd())){
	 				if(slschm.getAllotedToProduct()==null && pr==null){
	 					slschm.setGivenSchemeQtyBs(Long.parseLong(schemeQtyStr));
	 					schemeTable.refresh();
	 					return ;
	 				}
	 				else if(pr!=null && slschm.getAllotedToProduct()!=null){
	 					if(slschm.getAllotedToProduct().getProductCd().equals(pr.getProductCd())){
	 						slschm.setGivenSchemeQtyBs(Long.parseLong(schemeQtyStr));
	 						schemeTable.refresh();
		 					return ;
	 					}
	 				}
	 			}
	 		}
	 		SaleScheme slschm = new SaleScheme();
	 		slschm.setAllotedToGroup(gr);
	 		slschm.setAllotedToProduct(pr);
	 		slschm.setSchemePrdct(scmpr);
	 		slschm.setGivenSchemeQtyBs(Long.parseLong(schemeQtyStr));
	 		listSaleScheme.add(slschm);
	 	}
	 	
	 	@FXML
	    void addBreakageToTable(ActionEvent event) {
	 		Product breakPrdct = breakagePrdctCombox.getValue();
	 		String breakQtyStr = breakageQtyTxtFld.getText();
	 		if(breakPrdct==null){
	 			t.getErrorNotification("Please Breakage Item.");
	 			return ;
	 		}
	 		if(breakQtyStr==null || breakQtyStr.equals("")){
	 			t.getErrorNotification("Please Breakage Qty(B/s).");
	 			return ;
	 		}
	 		ObservableList<SaleBrekageDtl> saleBrkLst = brekageTable.getItems();
	 		for(SaleBrekageDtl obj:saleBrkLst){
	 			if(obj.getProductCd().getProductCd().equals(breakPrdct.getProductCd())){
	 				obj.setBreakageBs(Long.parseLong(breakQtyStr));
	 				brekageTable.refresh();
	 				return ;
	 			}
	 		}
	 		SaleBrekageDtl saleBrk = new SaleBrekageDtl();
	 		saleBrk.setProductCd(breakPrdct);
	 		saleBrk.setBreakageBs(Long.parseLong(breakQtyStr));
	 		saleBrkLst.add(saleBrk);
	    }
	 	
	 	@FXML
	    void sellProduct(ActionEvent event) {
	 		//customer detail segment
			logger.info("sellProduct :: start to save selling product on click on sell");
	 		CustomerDtl buyer = cstmrcombox.getValue();
	 		String buyerNmStr = buyerNmTextFld.getText();
	 		String addressStr = addressTxtFld.getText();
	 		CstmrLedger cstmrLedger = cstmrAcntsCombox.getValue();
	 		// String ledgerTypeStr = cstmrledrType.getText();
	 		String acntSpclDiscntStr = acntSpclDiscnt.getText();
	 		double dicntrs = 0.00;
	 		if(acntSpclDiscntStr!=null && !acntSpclDiscntStr.equals("")){
	 			dicntrs=Double.parseDouble(acntSpclDiscntStr);
	 		}
	 		//selling product segment
	 		ObservableList<SalePrdctInvoice> toBeSellPrdctsGroupDtlLst = sell_table.getItems();
	 		//SellTableSumData sellPrdctSumData = sellSumData;
	 		//scheme segment
	 		ObservableList<SaleScheme> saleschemelst= schemeTable.getItems();
	 		//breakage segment
	 		ObservableList<SaleBrekageDtl> breakagedtlst = brekageTable.getItems();
	 		//payment segment
	 		String saleTotalSysGnrtdAmntStr = saleTtlSysGeneratedAmtTextFld.getText();
	 		String saleTtlSchemeDiscntStr = saleTtlSchemeDiscountTextFld.getText();
	 		String saleTtlSpclDiscntStr = saleTtlSpecialDiscountTextFld.getText();
	 		String afterSpclNSchemeNetAmntStr = netAmtSchemeSpecialTotalTextFld.getText();
	 		String ttlAdjustAmtStr = adjustmentAmtTotalTextFld.getText();
	 		String ttlAdjustDiscntStr = adjustmentDiscntTotalTextFld.getText();
	 		String ttlGrossAmtStr = totalGrossAmtTextFld.getText();
	 		String paidAmtStr = paidAmtTextFld.getText();
	 		//other segment
	 		String givenGlassStr = givenGlassTextFld.getText();
	 		String returnGlassStr = returnGlassTextFld.getText();
	 		String extraCellStr=extraGivenCellTextFld.getText();
	 		//action -----
			logger.info("sellProduct ::validation begin");
	 		StringBuilder strBuild = new StringBuilder();
	 		boolean isError=false;
	 		if(buyerNmStr==null || buyerNmStr.trim().equals("")){
	 			isError=true;
	 			strBuild.append("Please provide buyer name \n");
	 			buyerNmTextFld.setStyle("-fx-border-color: red;-fx-border-width: 0.0px 0px 4px 0px;-fx-border-style: dashed;");
	 		}
	 		if(addressStr==null || addressStr.trim().equals("")){
	 			isError=true;
	 			strBuild.append("Please provide buyer address \n");
	 			addressTxtFld.setStyle("-fx-border-color: red;-fx-border-width: 0.0px 0px 4px 0px;-fx-border-style: dashed;");
	 		}
	 		if(buyer!=null){
	 			if(cstmrLedger==null){
	 				isError=true;
	 				strBuild.append("Please select customer ledger \n");
	 				cstmrAcntsCombox.setStyle("-fx-border-color: red;-fx-border-width: 0.0px 0px 4px 0px;-fx-border-style: dashed;");
	 			}
	 		}
	 		for(SalePrdctInvoice toBesalePrdctInvoice : toBeSellPrdctsGroupDtlLst){
	 			if(!toBesalePrdctInvoice.isValidForEditCol()){
	 				strBuild.append("Wrong entry has been found in sale table. Please correct it. \n");
	 				isError=true;
	 				break;
	 			}
	 			if(toBesalePrdctInvoice.getNetBaseAmt()==0 || toBesalePrdctInvoice.getTaxableAmt()==0
	 					|| toBesalePrdctInvoice.getSysGnrtdNetAmt()==0 || toBesalePrdctInvoice.getTotalPrdctNetAmt()==0){
	 				strBuild.append("Wrong entry has been found in sale table. Please correct it. \n");
	 				isError=true;
	 				break ;
	 			}
	 			if(!(toBesalePrdctInvoice.getSysSpecialDiscountPerCs()==dicntrs)){
	 				strBuild.append("Special discont price has been changed.please refresh table.\n");
	 				isError=true;
	 				break ;
	 			}
	 		}
	 		
	 		if(saleTotalSysGnrtdAmntStr.trim().equals("0.0") || saleTotalSysGnrtdAmntStr.trim().equals("0.00")){
	 			isError=true;
 				strBuild.append("Selling table cannnot be blank. \n");
 				saleTtlSysGeneratedAmtTextFld.setStyle("-fx-border-color: red;-fx-border-width: 0.0px 0px 4px 0px;-fx-border-style: dashed;");
	 		}
	 		if(afterSpclNSchemeNetAmntStr.trim().equals("0.0") || afterSpclNSchemeNetAmntStr.trim().equals("0.00")){
	 			isError=true;
 				strBuild.append("Selling table cannnot be blank. \n");
 				netAmtSchemeSpecialTotalTextFld.setStyle("-fx-border-color: red;-fx-border-width: 0.0px 0px 4px 0px;-fx-border-style: dashed;");
	 		}
	 		if(ttlAdjustAmtStr==null || ttlAdjustAmtStr.trim().equals("")){
	 			ttlAdjustAmtStr="0.00";
	 		}
	 		if(ttlAdjustDiscntStr==null || ttlAdjustDiscntStr.trim().equals("")){
	 			ttlAdjustDiscntStr="0.00";
	 		}
	 		double checkGrossAmt= Double.parseDouble(afterSpclNSchemeNetAmntStr)+Double.parseDouble(ttlAdjustAmtStr)+Double.parseDouble(ttlAdjustDiscntStr); 
	 		
	 		if(!(checkGrossAmt+"").equals(ttlGrossAmtStr)){
	 			isError=true;
 				strBuild.append("Please provide correct calculated value of Gross Amount.\n");
 				totalGrossAmtTextFld.setStyle("-fx-border-color: red;-fx-border-width: 0.0px 0px 4px 0px;-fx-border-style: dashed;");
	 		}
	 		if(paidAmtStr==null || paidAmtStr.trim().equals("")){
	 			isError=true;
	 			strBuild.append("Please provide customer paid amount. \n");
	 			paidAmtTextFld.setStyle("-fx-border-color: red;-fx-border-width: 0.0px 0px 4px 0px;-fx-border-style: dashed;");
	 		}
	 		if(returnGlassStr==null || returnGlassStr.trim().equals("")){
	 			isError=true;
	 			strBuild.append("Please provide customer paid amount. \n");
	 			returnGlassTextFld.setStyle("-fx-border-color: red;-fx-border-width: 0.0px 0px 4px 0px;-fx-border-style: dashed;");
	 		}
	 		if(extraCellStr==null || extraCellStr.trim().equals("")){
	 			extraCellStr="0.00";
	 		}
	 		if(isError){
	 			ErrorDialog.showErrorDilogue(new Text(strBuild.toString()), ((StackPane) t.getCurrentNode()),SystemMessages.validation_heading);
	 			return ;
	 		}
			logger.info("sellProduct ::validation end");
	 		Sale saleDtl = new Sale();
	 		saleDtl.setCustomer(buyer);
	 		saleDtl.setCstmrName(buyerNmStr);
	 		saleDtl.setAddress(addressStr);
	 		saleDtl.setCstmrAcnt(cstmrLedger);
	 		Calendar calendar = Calendar.getInstance();
	 		Date currDt = calendar.getTime();
	 		saleDtl.setSaleDt(ApplicationConstant.formatter.format(currDt));
	 		saleDtl.setDeliveryMode(ApplicationConstant.DELIVERY_MODE_SHOP);
	 		saleDtl.setGstn(gstn);
	 		saleDtl.setInvoicedPrdctDtlsSet(toBeSellPrdctsGroupDtlLst);
	 		
	 		
	 		if(breakagedtlst.size()>0)
	 			saleDtl.setIsBreakageReturn("Y");
	 		else
	 			saleDtl.setIsBreakageReturn("N");
	 		
	 		if(saleschemelst.size()>0)
	 			saleDtl.setIsSchemeAlloted("Y");
	 		else
	 			saleDtl.setIsSchemeAlloted("N");
	 		
	 		saleDtl.setSaleBrekageLst(breakagedtlst);
	 		saleDtl.setSaleEntrySystemDt(ApplicationConstant.formatter.format(currDt));
	 		saleDtl.setSalePrdctSchemeDtlSet(saleschemelst);
	 		saleDtl.setSysGnrtdTotalAmount(Double.parseDouble(saleTotalSysGnrtdAmntStr));
	 		saleDtl.setTotalSchemeDiscnt(Double.parseDouble(saleTtlSchemeDiscntStr));
	 		saleDtl.setSysGnrtdTotalDiscount(Double.parseDouble(saleTtlSpclDiscntStr));
	 		saleDtl.setTotalAmountAdjustment(Double.parseDouble(ttlAdjustAmtStr));
	 		saleDtl.setTotalAdjustmentDiscount(Double.parseDouble(ttlAdjustDiscntStr));
	 		saleDtl.setTotalNetActualAmount(Double.parseDouble(ttlGrossAmtStr));
	 		saleDtl.setPaidAmount(Double.parseDouble(paidAmtStr));
	 		saleDtl.setPaymentMode("CASH");
	 		saleDtl.setTotalDeliverBsGlass(24*Long.parseLong(givenGlassStr));
	 		saleDtl.setTotalReturnBsGlass(24*Long.parseLong(returnGlassStr));
	 		saleDtl.setTotalDeliverCell(Long.parseLong(returnGlassStr));
	 		saleDtl.setTotalReturnCell(Long.parseLong(returnGlassStr)+Long.parseLong(extraCellStr));
	 		saleDtl.setSoldBy("CDMS");
	 		saleDtl.setInvoiceType(ApplicationConstant.SALE);
	 		HashMap<String,Object> maps = new HashMap<>();
	 		maps.put("saleDtl", saleDtl);
			logger.info("sellProduct :: confirm screen");
	 		t.loadScreenIntoRoot(Components.CONFIRM_SALESCREEN,Components.CONFIRM_SALESCREEN_FXML, maps);
	    }
	 	
	public class SellTableSumData{
		private SimpleStringProperty text;
		private SimpleLongProperty totalqty;
		private SimpleDoubleProperty summationBaseAmt;
		private SimpleDoubleProperty sumSchemeDisntAmt;
		private SimpleDoubleProperty totalSummationNetAmt;
		private SimpleDoubleProperty sumTtlSysSpclDisntAmt;
		private SimpleDoubleProperty sumAfterAdjustNetAmt;
		public SellTableSumData(String text,Long totalQty,Double summationBaseAmt,Double sumSchemeDisntAmt, Double totalSummationNetAmt,Double sumTtlSysSpclDisntAmt,Double sum_AfterAdjustNetAmt){
			this.text = new SimpleStringProperty(text);
			this.totalqty= new SimpleLongProperty(totalQty);
			this.summationBaseAmt = new SimpleDoubleProperty(summationBaseAmt);
			this.sumSchemeDisntAmt = new SimpleDoubleProperty(sumSchemeDisntAmt);
			this.totalSummationNetAmt = new SimpleDoubleProperty(totalSummationNetAmt);
			this.sumTtlSysSpclDisntAmt = new SimpleDoubleProperty(sumTtlSysSpclDisntAmt);
			this.sumAfterAdjustNetAmt = new SimpleDoubleProperty(sum_AfterAdjustNetAmt);
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
		public Double getSumSchemeDisntAmt() {
			return sumSchemeDisntAmt.get();
		}
		public Double getTotalSummationNetAmt() {
			return totalSummationNetAmt.get();
		}
		public Double getSumTtlSysSpclDisntAmt() {
			return sumTtlSysSpclDisntAmt.get();
		}
		public Double getSumAfterAdjustNetAmt() {
			return sumAfterAdjustNetAmt.get();
		}
		public void setText(String text) {
			this.text.set(text);;
		}
		public void setTotalqty(Long totalqty) {
			this.totalqty.set(totalqty);
		}
		public void setSummationBaseAmt(Double summationBaseAmt) {
			this.summationBaseAmt.set(summationBaseAmt);
		}
		public void setSumSchemeDisntAmt(Double sumSchemeDisntAmt) {
			this.sumSchemeDisntAmt.set(sumSchemeDisntAmt);
		}
		public void setTotalSummationNetAmt(Double totalSummationNetAmt) {
			this.totalSummationNetAmt.set(totalSummationNetAmt);
		}
		public void setSumTtlSysSpclDisntAmt(Double sumTtlSysSpclDisntAmt) {
			this.sumTtlSysSpclDisntAmt.set(sumTtlSysSpclDisntAmt);
		}
		public void setSumAfterAdjustNetAmt(Double sumAfterAdjustNetAmt) {
			this.sumAfterAdjustNetAmt.set(sumAfterAdjustNetAmt);
		}
	}
}
