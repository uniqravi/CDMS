package application.cdms.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import application.Components;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

@SuppressWarnings("rawtypes")
public class SideNavigationController implements Initializable, ScreenController {

	private static Logger logger = Logger.getLogger(SideNavigationController.class);
	
	private ScreenTransitionController<?> t;

	@FXML
	private VBox homeVbox;
	
	@FXML
	private VBox productPaneVbox;

	@FXML
	private VBox stockPanelVbox;
	
	@FXML
	private VBox salePanelVbox;
	
	@FXML
	private VBox cstmrPanelVbox;
	
	@FXML
	private VBox claimVbox;
	
	@FXML
	private VBox purchasePanelVbox;
	
	@FXML
	private VBox reportPanelVbox;

	@Override
	public void setScreenTransitionController(Object obj) {
		if (obj instanceof ScreenTransitionController<?>) {
			this.t = (ScreenTransitionController) obj;
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//SET HOME PANEL LINKS
		for (Node node : homeVbox.getChildren()){
			if (node.getAccessibleText() != null) {
				node.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
					try {
						switch (node.getAccessibleText()) {
						case "home":
							displayHomeScreen();
							break;
						}
					} catch (Exception e1) {
						logger.fatal("Error While Loading Home Panel FXML ### "+e1.getMessage());
						e1.printStackTrace();
					}
				});
			}
		}
		
		// SET PRODUCT PANEL LINK		
		
		for (Node node : productPaneVbox.getChildren()) {
			if (node.getAccessibleText() != null) {
				node.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
					try {
						switch (node.getAccessibleText()) {
						case "addNewPrdct":
							displayAddPrdctScreen();
							break;
						case "addNewNonBevPrdct":
						//displayAddPrdctScreen();
						break;
						case "updatePriceScheme":
							displayProdctPriceScheme();
						break;
						case "":
							
							break;
					}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				});
			}
		}
		//purchase details
		for (Node node : purchasePanelVbox.getChildren()) {
			if (node.getAccessibleText() != null) {
				node.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
					try {
						System.out.println("Third Pa");
						switch (node.getAccessibleText()) {
						case "addPurchasedQty":
							displayAddPurchasedQty();
							break;
						case "purchaseTaxDtl":
							displayPurchaseTaxDtl();
							break;
						case "updatePaymentDetails":
							displayUpdatePurchasePaymentDetial();
							break;
						case "returnPurchaseInvoice":
							displayReturnPurchaseInvoice();
							break;
						case "returnBottleShellInvoice":
							displayReturnBottleShellInvoice();
							break;
						}
					} catch (Exception e1) {
						logger.fatal("Error While Loading Home Panel FXML ### "+e1.getMessage());
						e1.printStackTrace();
					}
				});
			}
		}
		// SET SALE PANEL LINKS
				for (Node node : salePanelVbox.getChildren()) {
					if (node.getAccessibleText() != null) {
						node.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
							try {
								switch (node.getAccessibleText()) {
								case "sellProduct":
									displaySellProduct();
									break;
								case "supplyRecord":
									displaySupplyRecords();
									break;
								case "SupplyEntry" :
									displayReturnSupply(node);
								break;
								}
							} catch (Exception e1) {
								logger.fatal("Error While Loading Home Panel FXML ### "+e1.getMessage());
								e1.printStackTrace();
							}
						});
					}
				}
				
				// SET CSTMR PANEL LINKS
				for (Node node : cstmrPanelVbox.getChildren()) {
					if (node.getAccessibleText() != null) {
						node.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
							try {
								switch (node.getAccessibleText()) {
								case "addNewCstmr":
									displayAddNewCstmr();
									break;
								case "createNewLedger":
									displayCreateNewLedger();
									break;
								}
							} catch (Exception e1) {
								logger.fatal("Error While Loading Home Panel FXML ### "+e1.getMessage());
								e1.printStackTrace();
							}
						});
					}
				}
				
				//set Claim Panel
				for (Node node : claimVbox.getChildren()) {
					if (node.getAccessibleText() != null) {
						node.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
							try {
								switch (node.getAccessibleText()) {
								case "breakgeClaimForm":
									displayBreakageClaimForm();
									break;
								}
							} catch (Exception e1) {
								logger.fatal("Error While Loading Home Panel FXML ### "+e1.getMessage());
								e1.printStackTrace();
							}
						});
					}
				}
				
				for(Node node : reportPanelVbox.getChildren()){
					if (node.getAccessibleText() != null) {
						node.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
							try {
								switch (node.getAccessibleText()) {
								case "purchaseTaxReport":
									displayPurchaseReport();
									break;
								}
							} catch (Exception e1) {
								logger.fatal("Error While Loading Home Panel FXML ### "+e1.getMessage());
								e1.printStackTrace();
							}
						});
					}
				}
	}

	private void displayHomeScreen() throws IOException{
		t.loadScreenIntoRoot(Components.HOMESCREEN, Components.HOMESCREEN_FXML,null);
	}
	
	private void displayReturnPurchaseInvoice() {
		t.loadScreenIntoRoot(Components.RETURN_PURCHASE, Components.RETURN_PURCHASE_FXML,null);
	}

	private void displayProdctPriceScheme(){
		t.loadScreenIntoRoot(Components.VIEW_PRODUCT_PRICESCHEME, Components.VIEW_PRODUCT_PRICESCHEME_FXML,null);
	}

	private void displayAddPrdctScreen(){
		t.loadScreenIntoRoot(Components.ADDNEWPRODUCTSCREEN, Components.ADDNEWPRODUCTSCREEN_FXML,null);
	}
	private void displayAddPurchasedQty(){
		t.loadScreenIntoRoot(Components.ADD_PURCHASEDQTY_SCREEN, Components.ADD_PURCHASEDQTY_SCREEN_FXML,null);
	}
	
	private void displaySellProduct(){
		t.loadScreenIntoRoot(Components.SELL_PRODUCT, Components.SELL_PRODUCT_FXML,null);
	}
	private void displayAddNewCstmr(){
		t.loadScreenIntoRoot(Components.ADD_NEW_CSTMR, Components.ADD_NEW_CSTMR_FXML,null);
	}
	private void displayCreateNewLedger(){
		t.loadScreenIntoRoot(Components.CREATE_NEW_LEDGER, Components.CREATE_NEW_LEDGER_FXML,null);
	}
	private void displayBreakageClaimForm(){
		t.loadScreenIntoRoot(Components.BREAKAGE_FORM, Components.BREAKAGE_FORM_FXML, null);
	}
	private void displaySupplyRecords(){
		t.loadScreenIntoRoot(Components.SUPPLY_ENTRY, Components.SUPPLY_ENTRY_FXML,null);
	}
	private void displayPurchaseTaxDtl() {
		t.loadScreenIntoRoot(Components.PURCHASE_TAX_DETAIL, Components.PURCHASE_TAX_DETAIL_FXML,null);
	}
	private void displayUpdatePurchasePaymentDetial() {
		t.loadScreenIntoRoot(Components.UPDATE_PAYMENT_DETAIL, Components.UPDATE_PAYMENT_DETAIL_FXML,null);
	}
	private void displayPurchaseReport() {
		t.loadScreenIntoRoot(Components.PURCHASE_TAX_REPORT, Components.PURCHASE_TAX_REPORT_FXML,null);
	}
	private void displayReturnBottleShellInvoice() {
		t.loadScreenIntoRoot(Components.RETURN_BOTTLE_SHELL, Components.RETURN_BOTTLE_SHELL_FXML,null);
	}
	private void displayNewSupplyEntry(){
		
	}
	private void displayReturnSupply(Node node) throws IOException {
		//t.loadScreenIntoRoot(Components.RETURN_SUPPLY_ENTRY, Components.RETURN_SUPPLY_ENTRY_FXML,null);
		ContextMenu contextMenu = new ContextMenu();
	    MenuItem newSupplyItem = new MenuItem("New Supply Entry");
	    MenuItem returnSupplyItem = new MenuItem("Return Supply Entry");
	    MenuItem assessSupply = new MenuItem("Assess Supply");
	    newSupplyItem.setOnAction((e) -> {
	    	t.loadScreenIntoRoot(Components.NEW_SUPPLY_ENTRY, Components.NEW_SUPPLY_ENTRY_FXML,null);
	    });
	    returnSupplyItem.setOnAction((e) -> {
	    	displayNewSupplyEntry();
	    	t.loadScreenIntoRoot(Components.RETURN_SUPPLY_ENTRY, Components.RETURN_SUPPLY_ENTRY_FXML,null);
	    });
	    contextMenu.getItems().addAll(newSupplyItem,returnSupplyItem,assessSupply);
	    contextMenu.show(node, Side.RIGHT,0,0);
	}
	
	@Override
	public void setParams(Map params) {
		
	}
	

}
