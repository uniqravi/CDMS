package application;

public interface Components {

	//Stage primarystage=new Stage();
	//Images Path
	String DELETE_IMAGE="/application/cdms/images/cancel24.png";
	String EDIT_IMAGE="/application/cdms/images/edit.png";
	String CDMS_ICON="/application/cdms/images/pepsiIcon.png";
	
	//public static final Parent root;
	String LOGIN_FXML="/application/cdms/fxmls/Login.fxml";
	String HOMESCREEN = "HOMESCREEN";
	String HOMESCREEN_FXML = "/application/cdms/fxmls/Home.fxml";
	String WELCOMESCREEN = "WELCOMESCREEN";
	String WELCOMESCREEN_FXML = "/application/cdms/fxmls/Welcome.fxml";
	String SIDEMENU_FXML="/application/cdms/fxmls/AdminSideMenu.fxml";
	String ADDNEWPRODUCTSCREEN = "ADDNEWPRODUCTSCREEN";
	String ADDNEWPRODUCTSCREEN_FXML = "/application/cdms/fxmls/AddNewPrdctScreen.fxml";
	String ADD_PURCHASEDQTY_SCREEN="ADDPURCHASEDQTYSCREEN";
	String ADD_PURCHASEDQTY_SCREEN_FXML="/application/cdms/fxmls/AddPurchasedProductQty.fxml";
	String PURCHASED_PRODUCT_DIALOG="/application/cdms/fxmls/PurchasedProductDialog.fxml";
	String VIEW_PRODUCT_PRICESCHEME="VIEWPRODUCTPRICESCHEME";
	String VIEW_PRODUCT_PRICESCHEME_FXML="/application/cdms/fxmls/PrdctPriceSchemeViewScreen.fxml";
	String UPDATE_PRODUCT_PRICESCHEME="UPDATEPRODUCTPRICESCHEME";
	String UPDATE_PRODUCT_PRICESCHEME_FXML="/application/cdms/fxmls/UpdtPrdctPriceScheme.fxml";
	String ADD_SCHEME_DIALOG="/application/cdms/fxmls/AddSchemeDialog.fxml";
	String SELL_PRODUCT="SELLPRODUCT";
	String SELL_PRODUCT_FXML="/application/cdms/fxmls/SellProduct.fxml";
	String CONFIRM_SALESCREEN="CONFIRMSALESCREEN";
	String CONFIRM_SALESCREEN_FXML="/application/cdms/fxmls/ConfirmSellScreen.fxml";
	String ADD_NEW_CSTMR="ADDNEWCSTMR";
	String ADD_NEW_CSTMR_FXML = "/application/cdms/fxmls/AddNewCstmr.fxml";
	String CREATE_NEW_LEDGER="NEWLEDGER";
	String CREATE_NEW_LEDGER_FXML = "/application/cdms/fxmls/CreateNewLedger.fxml";
	String SUPPLY_ENTRY="SUPPLY_ENTRY";
	String SUPPLY_ENTRY_FXML = "/application/cdms/fxmls/SupplyEntry.fxml";
	String NEW_SUPPLY_ENTRY="NEW_SUPPLY_ENTRY";
	String NEW_SUPPLY_ENTRY_FXML = "/application/cdms/fxmls/NewSupplyEntry.fxml";
	String PURCHASE_TAX_DETAIL="PURCHASETAXDETAIL";
	String PURCHASE_TAX_DETAIL_FXML="/application/cdms/fxmls/PurchaseTaxDeltail.fxml";
	String UPDATE_PAYMENT_DETAIL="UPDATEPAYMENTDETAIL";
	String UPDATE_PAYMENT_DETAIL_FXML="/application/cdms/fxmls/UpdtPurchasePaymentDetail.fxml";
	String RETURN_SUPPLY_ENTRY = "RETURNSUPPLYENTRY";
	String RETURN_SUPPLY_ENTRY_FXML="/application/cdms/fxmls/UpdateSupply.fxml";
	String RETURN_PURCHASE="RETURNPURCHASE";
	String RETURN_PURCHASE_FXML ="/application/cdms/fxmls/ReturnPurchaseInvoice.fxml";
	String RETURN_PURCHASE_CONFIRM_FXML="/application/cdms/fxmls/ConfirmReturnPurchaseDialog.fxml";
	String BREAKAGE_FORM="BREAKAGE_FORM";
	String BREAKAGE_FORM_FXML="/application/cdms/fxmls/BreakageClaimForm.fxml";
	String BREAKAGE_CONFIRM_FXML="/application/cdms/fxmls/ConfirmBreakageProductDialog.fxml";
	String PURCHASE_TAX_REPORT="PURCHASE_TAX_REPORT";
	String PURCHASE_TAX_REPORT_FXML="/application/cdms/fxmls/PurchasedTaxReport.fxml";
	String RETURN_BOTTLE_SHELL_FXML="/application/cdms/fxmls/ReturnEmptyInvoice.fxml";
	String RETURN_BOTTLE_SHELL="RETURNBOTTLESHELLINVOICE";
	//JASPER Invoice Template 
	String CSTMR_INVOICE_TEMPLATE="/app/report/template/sellReport";
	String RETURN_PRODUCT_INVOICE_TEMPLATE="/app/report/template/returnPurchaseInvoice";
	String RETURN_BREAKAGE_INVOICE_TEMPLATE="/app/report/template/breakageInvoice";
	String RETURN_EMPTY_INVOICE_TEMPLATE="/app/report/template/returnEmptyInvoice";
}
