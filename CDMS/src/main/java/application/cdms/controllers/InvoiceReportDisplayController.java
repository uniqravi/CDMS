package application.cdms.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.jfoenix.controls.JFXButton;

import application.Components;
import application.cdms.component.data.handler.CellFactoryGenerator;
import application.cdms.component.data.handler.PrintJobScreen;
import application.cdms.models.Product;
import application.cdms.models.ProductGroup;
import application.cdms.models.Sale;
import application.cdms.models.SaleBrekageDtl;
import application.cdms.models.SalePrdctInvoice;
import application.cdms.models.SaleScheme;
import application.cdms.service.ProductService;
import application.cdms.service.impl.ProductServiceImpl;
import application.cdms.transformer.AppConstant;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

@SuppressWarnings("rawtypes")
public class InvoiceReportDisplayController implements Initializable, ScreenController{

	private static Logger logger = Logger.getLogger(InvoiceReportDisplayController.class);
	
	private ScreenTransitionController<?> t;
	
	@FXML
	private ScrollPane sellSScrollPane;
	
	@FXML
	private VBox schemeVbox;
	
	@FXML
    private Label cstmrId_label;

    @FXML
    private Label cstmrName_label;

    @FXML
    private Label address_label;

    @FXML
    private Label cstmrAcntNo_label;

    @FXML
    private Label ledgerTypeNm_label;

    @FXML
    private Label discountQty_label;

    @FXML
    private TableView<SalePrdctInvoice> sell_table;

    @FXML
    private TableColumn sr_col;

    @FXML
    private TableColumn<SalePrdctInvoice,ProductGroup> prdctGroup_col;

    @FXML
    private TableColumn<SalePrdctInvoice,StringBuilder> prdct_col;
    

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
    private TableColumn<SalePrdctInvoice,Double> igstRate_col;

    @FXML
    private TableColumn<SalePrdctInvoice,Double> igstAmt_col;

    @FXML
    private TableColumn<SalePrdctInvoice,Double> cgstRate_col;

    @FXML
    private TableColumn<SalePrdctInvoice,Double> cgstAmt_col;

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
    private TableView<InvoiceReportDisplayController.SellTableSumData> sum_table;

    @FXML
    private TableColumn<InvoiceReportDisplayController.SellTableSumData,String> sum_totalText_col;

    @FXML
    private TableColumn<InvoiceReportDisplayController.SellTableSumData,Integer> sum_totalPrdctQty_col;

    @FXML
    private TableColumn<InvoiceReportDisplayController.SellTableSumData,Double> sum_totalBaseAmt_col;

    @FXML
    private TableColumn<InvoiceReportDisplayController.SellTableSumData,Double> sum_schemeDiscnt_col;

    @FXML
    private TableColumn<InvoiceReportDisplayController.SellTableSumData,Double> sum_totalSysNetAmt_col;

    @FXML
    private TableColumn<InvoiceReportDisplayController.SellTableSumData,Double> sum_sysSpclDisnt_col;

    @FXML
    private TableColumn<InvoiceReportDisplayController.SellTableSumData,Double> sum_disntAdjustmnt_col;

    @FXML
    private TableColumn<InvoiceReportDisplayController.SellTableSumData,Double> sum_amtAdjustment_col;

    @FXML
    private TableColumn<InvoiceReportDisplayController.SellTableSumData,Double> sum_totalNetAmt_col;

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
    private TableView<SaleBrekageDtl> brekageTable;

    @FXML
    private TableColumn<SaleBrekageDtl,Product> brekage_Prdct_col;

    @FXML
    private TableColumn<SaleBrekageDtl,Long> breakage_qty_col;

    @FXML
    private Label sysGnrtdTotalAmount_label;

    @FXML
    private Label totalSchemeDiscnt_label;

    @FXML
    private Label sysGnrtdTotalDiscount_label;

    @FXML
    private Label afterSysSchmeNSpclDicntTotalNet_label;

    @FXML
    private Label totalAdjustmentDiscount;

    @FXML
    private Label totalAmountAdjustment;

    @FXML
    private Label totalNetActualAmount_label;

    @FXML
    private Label totalPrevDueAmount_label;

    @FXML
    private Label paidAmount_label;

    @FXML
    private Label totalNetDueAmount_label;

    @FXML
    private Label totalDeliverBsGlass_label;

    @FXML
    private Label totalReturnBsGlass_label;

    @FXML
    private Label extraGivenCell_label;
    
    private boolean isSaleButtonClicked=false;
    
    private boolean isResponseRecieved=false;;
    
    @FXML
    private JFXButton editButton;

    @FXML
    private JFXButton printInvoiceButton;
    
    @FXML
    private JFXButton confirmButton;
    
    private ProductService productService = ProductServiceImpl.getInstance();

    @FXML
    void goBack(ActionEvent event) {
    	t.loadBackScreen();
    }

    @FXML
    void sellProduct(ActionEvent event) {
    	try{
    	if(!isSaleButtonClicked){
    		isSaleButtonClicked=true;
    		Sale sale = (Sale) params.get("saleDtl");
    		logger.info("sellProduct :: invoice generating ");
        	Sale sucessSale=productService.sellProduct(sale);
        	logger.info("sellProduct :: invoice generated successfully ### "+sucessSale.getSaleInvoiceNo());
        	isResponseRecieved=true;
        	if(sucessSale.getSaleInvoiceNo()==null){
        		t.getErrorNotification("Sorry ! something bad happened.");
        	}
        	else{
        		logger.info("Sale recorded succesffully and corresponding sale invoice number ### "+sucessSale.getSaleInvoiceNo());
        		Label label = new Label("Sale invoice number is "+sucessSale.getSaleInvoiceNo()+".Please click on print for printing.");
        		label.setStyle("-fx-background-color:green;-fx-font-size: 18px;-fx-font-weight:bold;");
        		t.getNotification(label);
        		editButton.setVisible(false);
        		confirmButton.setVisible(false);
        		printInvoiceButton.setVisible(true);
        	}
    	}
    	else{
    		if(!isResponseRecieved){
    			t.getNotification(new Label("Please wait! We will get back soon with invoice number."));
    		}
    	}
    	}
    	catch(Exception e){
    		isSaleButtonClicked=false;
    		logger.fatal("sellProduct :: exception ### "+e.getMessage());
    		e.printStackTrace();
    	}
    }
	
	Map<String,Object> params=null;
	
	@FXML
	private VBox reportVbox;
	
	@Override
	public void setScreenTransitionController(Object obj) {
		this.t=(ScreenTransitionController) obj;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setParams(Map params) {
		this.params=params;
		justAftrAction();
	}
		
	private void justAftrAction(){
		Sale sale = (Sale) params.get("saleDtl");
		if(sale.getCustomer()!=null){
			cstmrId_label.setText(sale.getCustomer().getCstmrId());
			cstmrAcntNo_label.setText(sale.getCstmrAcnt().getCstmrAcntNo());
			ledgerTypeNm_label.setText(sale.getCstmrAcnt().getLedgerType().getLedgerTypeNm());
			discountQty_label.setText(sale.getCstmrAcnt().getLedgerType().getDiscountQty()+"");
		}
		cstmrName_label.setText(sale.getCstmrName());
		address_label.setText(sale.getAddress());
		//selling product name
		List<SalePrdctInvoice> prdctsLst = sale.getInvoicedPrdctDtlsSet();
		ObservableList<SalePrdctInvoice> prdcs = FXCollections.observableArrayList();
		InvoiceReportDisplayController.SellTableSumData sumData= new InvoiceReportDisplayController.SellTableSumData("Total",0L,0.00,0.00,0.00,0.00,0.00);
		for(SalePrdctInvoice invoicprd : prdctsLst){
			sumData.setTotalqty(sumData.getTotalqty()+invoicprd.getSellingQtyCs());
			sumData.setSummationBaseAmt(sumData.getSummationBaseAmt()+invoicprd.getNetBaseAmt());
			sumData.setSumSchemeDisntAmt(sumData.getSumSchemeDisntAmt()+invoicprd.getSchemeDisocuntAmt());
			sumData.setTotalSummationNetAmt(sumData.getTotalSummationNetAmt()+invoicprd.getSysGnrtdNetAmt());
			sumData.setSumTtlSysSpclDisntAmt(sumData.getSumTtlSysSpclDisntAmt()+invoicprd.getTotalSysGnrtdDiscount());
			sumData.setSumAfterAdjustNetAmt(sumData.getSumAfterAdjustNetAmt()+invoicprd.getTotalPrdctNetAmt());
			prdcs.add(invoicprd);
		}
		sell_table.getItems().clear();
		sell_table.getItems().addAll(prdcs);
		sum_table.getItems().add(sumData);
		sell_table.prefHeightProperty().bind(sell_table.fixedCellSizeProperty().multiply(Bindings.size(sell_table.getItems()).add(3)));
		
		sellSScrollPane.setPrefViewportHeight(Math.min(400, sell_table.getPrefHeight()+70));
		
		//scheme table
		if(AppConstant.POSITIVE.equals(sale.getIsSchemeAlloted())){
			schemeTable.getItems().clear();
			schemeTable.getItems().addAll(FXCollections.observableList(sale.getSalePrdctSchemeDtlSet()));
		}
		schemeTable.prefHeightProperty().bind(schemeTable.fixedCellSizeProperty().multiply(Bindings.size(schemeTable.getItems()).add(3)));
		//schemeTable.setPrefHeight(schemeTable.getFixedCellSize()*(schemeTable.getItems().size()+1));
		schemeVbox.prefHeightProperty().bind(schemeTable.prefHeightProperty());
		//breakage table
		if(AppConstant.POSITIVE.equals(sale.getIsBreakageReturn())){
			brekageTable.getItems().clear();
			brekageTable.getItems().addAll(FXCollections.observableList(sale.getSaleBrekageLst()));
		}
		brekageTable.prefHeightProperty().bind(brekageTable.fixedCellSizeProperty().multiply(Bindings.size(brekageTable.getItems()).add(1.01)));
		//payment detail
		sysGnrtdTotalAmount_label.setText(sale.getSysGnrtdTotalAmount()+"");
		totalSchemeDiscnt_label.setText(sale.getTotalSchemeDiscnt()+"");
		sysGnrtdTotalDiscount_label.setText(sale.getSysGnrtdTotalDiscount()+"");
		afterSysSchmeNSpclDicntTotalNet_label.setText((sale.getSysGnrtdTotalAmount()+sale.getTotalSchemeDiscnt()-sale.getSysGnrtdTotalDiscount())+"");
		totalAmountAdjustment.setText(sale.getTotalAmountAdjustment()+"");
		totalAdjustmentDiscount.setText(sale.getTotalAdjustmentDiscount()+"");
		totalNetActualAmount_label.setText(sale.getTotalNetActualAmount()+"");
		paidAmount_label.setText(sale.getPaidAmount()+"");
		
		totalDeliverBsGlass_label.setText((sale.getTotalDeliverBsGlass()/24)+"");
		totalReturnBsGlass_label.setText((sale.getTotalReturnBsGlass()/24)+"");
		extraGivenCell_label.setText((sale.getTotalDeliverCell()-sale.getTotalReturnCell())+"");
	}
	
	@FXML
	public void printInvoice(ActionEvent event){
		List<Sale> saleLst = new ArrayList<>();
		Sale sale = (Sale) params.get("saleDtl");
		saleLst.add(sale);
		Map<String,Object> jasperParams= new HashMap<>();
		try {
			String heading = "Breakage Product Invoice";
			PrintJobScreen<Sale> job = new PrintJobScreen<>(t,heading);
			job.printInvoice(saleLst, jasperParams, Components.CSTMR_INVOICE_TEMPLATE,"/app/temp/file/viewsellHtml_temp.html");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		 // bind/sync tables
        for (int i = 0; i < sell_table.getColumns().size(); i++) {

            TableColumn mainColumn = sell_table.getColumns().get(i);
            TableColumn sumColumn = sum_table.getColumns().get(i);

            // sync column widths
            sumColumn.prefWidthProperty().bind(mainColumn.widthProperty());

           // sync visibility
            sumColumn.visibleProperty().bind(mainColumn.visibleProperty());
        }
        
        
        //sell_table.minHeightProperty().bind(sell_table.prefHeightProperty());
       // sell_table.maxHeightProperty().bind(sell_table.prefHeightProperty());
        sr_col.setCellFactory(CellFactoryGenerator.getSrNumberCellFactory());
        
        prdctGroup_col.setCellValueFactory(new PropertyValueFactory<>("productGroup"));
        
        prdctGroup_col.setCellFactory(CellFactoryGenerator.getTextCellFactory((productGrp) -> {return ((ProductGroup)productGrp).getGroupName();}));
        prdct_col.setCellValueFactory(new PropertyValueFactory<>("prductWithQtyStr"));
        prdct_col.setCellFactory(CellFactoryGenerator.getTextCellFactory((strBuild) -> {return ((StringBuilder)strBuild).toString();}));
        sellingPrdctGroupQty_col.setCellValueFactory(new PropertyValueFactory<SalePrdctInvoice,Long>("sellingQtyCs"));
        baseRatePrCs_col.setCellValueFactory(new PropertyValueFactory<SalePrdctInvoice,Double>("baseRatePerCs"));
        netBaseAmt_col.setCellValueFactory(new PropertyValueFactory<>("netBaseAmt"));
        scheme_discnt_PerCs_col.setCellValueFactory(new PropertyValueFactory<>("shemeDiscountPerCs"));
        scheme_discnt_col.setCellValueFactory(new PropertyValueFactory<>("schemeDisocuntAmt"));
       
       	ttlTaxableAmt_col.setCellValueFactory(new PropertyValueFactory<>("taxableAmt"));
       	
        sgstRate_col.setCellValueFactory(new PropertyValueFactory<>("sgstRate"));
        sgstAmt_col.setCellValueFactory(new PropertyValueFactory<>("sgstAmt"));
        
        cgstRate_col.setCellValueFactory(new PropertyValueFactory<>("cgstRate"));
        cgstAmt_col.setCellValueFactory(new PropertyValueFactory<>("cgstAmt"));
        
        igstRate_col.setCellValueFactory(new PropertyValueFactory<>("igstRate"));
        igstAmt_col.setCellValueFactory(new PropertyValueFactory<>("igstAmt"));
        
        cessRate_col.setCellValueFactory(new PropertyValueFactory<>("cessRate"));
        cessAmt_col.setCellValueFactory(new PropertyValueFactory<>("cessAmt"));
        
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
        
        scheme_sr_col.setCellFactory(CellFactoryGenerator.getSrNumberCellFactory());
        
        SchemeForGrp_col.setCellValueFactory(new PropertyValueFactory<>("allotedToGroup"));
        schemeForPrdct_col.setCellValueFactory(new PropertyValueFactory<>("allotedToProduct"));
        qty_col.setCellValueFactory(new PropertyValueFactory<>("givenSchemeQtyBs"));
        
        brekage_Prdct_col.setCellValueFactory(new PropertyValueFactory<>("productCd"));
        breakage_qty_col.setCellValueFactory(new PropertyValueFactory<>("breakageBs"));
        
        editButton.managedProperty().bind(editButton.visibleProperty());
        printInvoiceButton.managedProperty().bind(printInvoiceButton.visibleProperty());
        printInvoiceButton.setVisible(false);
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
