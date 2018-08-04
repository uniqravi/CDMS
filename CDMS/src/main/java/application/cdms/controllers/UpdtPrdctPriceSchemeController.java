package application.cdms.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXButton.ButtonType;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialog.DialogTransition;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;

import application.Components;
import application.SystemMessages;
import application.ValidationRegex;
import application.cdms.component.data.handler.AlertDialog;
import application.cdms.component.data.handler.ErrorDialog;
import application.cdms.component.data.handler.SuccessDialog;
import application.cdms.models.Product;
import application.cdms.models.ProductGroup;
import application.cdms.models.ProductPriceSchemeDtl;
import application.cdms.models.Scheme;
import application.cdms.models.SchemeProduct;
import application.cdms.service.ProductService;
import application.cdms.service.impl.ProductServiceImpl;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;

@SuppressWarnings("rawtypes")
public class UpdtPrdctPriceSchemeController implements Initializable, ScreenController {

	private static Logger logger = Logger.getLogger(UpdtPrdctPriceSchemeController.class);
	
	private ScreenTransitionController<?> t;

	private Map<String, Object> params;

	@FXML
	private Label groupNameLabel;

	@FXML
	private JFXListView<Product> groupProdctListView;

	@FXML
	private JFXListView<Product> selectedProductListView;
	
	@FXML
	private JFXTextField priceTextFld;
	
	@FXML
	private TreeTableView schemeOptionsTreeView;
	
	@FXML
	private TreeTableColumn<SchemeProduct, String> schemeOptionTreeCol;
	
	@FXML
	private TreeTableColumn actionCol; 
	
	private TreeItem<SchemeProduct> root = new TreeItem<>(new SchemeProduct("Scheme Options"));

	private ProductService productService = ProductServiceImpl.getInstance();
	
	private int schemeOptionCount=0;

	@Override
	public void setScreenTransitionController(Object t) {
		if (t instanceof ScreenTransitionController) {
			this.t = (ScreenTransitionController) t;
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setParams(Map params) {
		this.params = params;
		this.justafterExecuteThis();
	}

	@SuppressWarnings("unchecked")
	private void justafterExecuteThis() {
		String groupNm = (String) params.get("groupNm");
		groupNameLabel.setText(groupNm);
		Product product = new Product();
		product.setGroupNm(groupNm);
		groupProdctListView.setItems(productService.productLstByParams(product));
		groupProdctListView.setCellFactory(new Callback<ListView<Product>, ListCell<Product>>() {
			@Override
			public ListCell<Product> call(ListView<Product> list) {
				return new ActionListCell(1);
			}
		});
		selectedProductListView.setCellFactory(new Callback<ListView<Product>, ListCell<Product>>() {
			@Override
			public ListCell<Product> call(ListView<Product> arg0) {
				return new ActionListCell(2);
			}
		});
		
		schemeOptionTreeCol.setCellValueFactory((
				TreeTableColumn.CellDataFeatures<SchemeProduct, String> param) -> new ReadOnlyStringWrapper(
						param.getValue().getValue().toString()
						)
		);
		
		actionCol.setCellFactory(new Callback<TreeTableColumn, TreeTableCell>() {

			@Override
			public TreeTableCell call(TreeTableColumn param) {
				return new TreeTableCell(){
					@Override
					public void updateItem(Object Item,boolean empty){
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
									//int index = getTreeTableRow().getIndex();
									//TreeTableRow tabblerow= getTreeTableRow();
									 getTreeTableRow().getIndex();//.getTreeItem().getChildren(); //getTreeItem().getChildren().remove(getIndex());
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
						HBox pane = new HBox(deletelink);
						setGraphic(empty ? null:pane);
					}
				};
			}
			
		});
		
		schemeOptionsTreeView.setRoot(root);
		schemeOptionsTreeView.setShowRoot(false);
	}

	@FXML
	void goBack(ActionEvent event) {
		t.loadBackScreen();
	}

	@FXML
	void openSchemeDialog(ActionEvent event) {
		showDialogForSchemeAdd(((StackPane) t.getCurrentNode()));
	}
	

	
	JFXDialog schemDialog=null;
	private void showDialogForSchemeAdd(StackPane stackPane) {
		JFXDialogLayout dilogLayout = new JFXDialogLayout();
		Label heading = new Label("Add Scheme");
		heading.setStyle("-fx-font-size: 22px;-fx-text-fill:#3F51B5");
		dilogLayout.setHeading(heading);
		dilogLayout.setStyle("-fx-background-color: #e5eaf5;");
		dilogLayout.setBody(loadDialogBody());
		schemDialog = new JFXDialog(stackPane, dilogLayout, DialogTransition.CENTER);
		schemDialog.setOverlayClose(false);
		schemDialog.show();
	}

	private TableView<SchemeProduct> addSchemeTable;

	@SuppressWarnings("unchecked")
	private Node loadDialogBody() {
		// ---------------------------------------------Body--------------------------------------------//
		// Input fields to HBOX
		JFXComboBox<ProductGroup> productGroupCombo = new JFXComboBox<>(productService.getAllProductGroup());
		productGroupCombo.setPromptText("Beverage Group");
		productGroupCombo.setStyle("-fx-pref-width:227.0;-fx-pref-height:38.0;");
		JFXComboBox<Product> productCombo = new JFXComboBox<>();
		productCombo.setPromptText("Beverage Product");
		productCombo.setStyle("-fx-pref-width:227.0;-fx-pref-height:38.0;");
		JFXTextField schemqtyTextfld = new JFXTextField();
		schemqtyTextfld.setPromptText("Scheme Qty (B/s)");
		schemqtyTextfld.setStyle("-fx-pref-width:227.0;-fx-pref-height:38.0;");
		JFXButton addTotableBut = new JFXButton("Add to Table");
		addTotableBut.setButtonType(ButtonType.RAISED);
		addTotableBut.setStyle(
				"-fx-background-color: blue; -fx-text-fill: white;-fx-pref-width:227.0;-fx-pref-height:38.0;");
		HBox inputHbox = new HBox(6.0, productGroupCombo, productCombo, schemqtyTextfld, addTotableBut);
		inputHbox.setStyle("-fx-pref-width:754.0;-fx-pref-height:55.0;");

		// table view body
		addSchemeTable = new TableView<>();
		addSchemeTable.setStyle("-fx-pref-height:200.0;");
		TableColumn<SchemeProduct, ProductGroup> productGroupCol = new TableColumn<>("Beverage Group Name");
		productGroupCol.setStyle("-fx-pref-width:253.0;");
		TableColumn<SchemeProduct, Product> productCol = new TableColumn<>("Beverage Product Name");
		productCol.setStyle("-fx-pref-width:351.0;");
		TableColumn<SchemeProduct, Long> schemeQtyCol = new TableColumn<>("Scheme Qty (B/s)");
		schemeQtyCol.setStyle("-fx-pref-width:149.0;");
		addSchemeTable.getColumns().addAll(productGroupCol, productCol, schemeQtyCol);

		// Start AddScheme Button Hbox
		JFXButton addSchemeButton = new JFXButton("Add Scheme");
		addSchemeButton.getStyleClass().add("custom_button");
		addSchemeButton.setButtonType(ButtonType.RAISED);
		addSchemeButton.setStyle("-fx-pref-width:160.0;-fx-pref-height:40.0;");
		JFXButton closeDialog = new JFXButton("Close");
		closeDialog.getStyleClass().add("custom_button");
		closeDialog.setButtonType(ButtonType.RAISED);
		closeDialog.setStyle("-fx-pref-width:160.0;-fx-pref-height:40.0;");
		HBox addShemeButHbox = new HBox(addSchemeButton,closeDialog);
		addShemeButHbox.setStyle("-fx-pref-width:754.0;-fx-pref-height:40.0;");
		addShemeButHbox.setAlignment(Pos.CENTER);

		// ------------------------------------------actions---------------------------------------------------------------//
		// to be implemented product-group combo change listener actions of input
		productGroupCombo.valueProperty().addListener( (e)->{
			ProductGroup productGrp = productGroupCombo.getValue();
			if(productGrp!=null){
				Product product = new Product();
				product.setGroupNm(productGrp.getGroupName());
				productCombo.setItems(productService.productLstByParams(product));
			}
		});
		
		productGroupCol.setCellValueFactory(new PropertyValueFactory<>("groupName"));
		productGroupCol.setCellFactory(
				new Callback<TableColumn<SchemeProduct, ProductGroup>, TableCell<SchemeProduct, ProductGroup>>() {
					@Override
					public TableCell<SchemeProduct, ProductGroup> call(TableColumn<SchemeProduct, ProductGroup> param) {
						TableCell<SchemeProduct, ProductGroup> cell = new TableCell<SchemeProduct, ProductGroup>() {
							@Override
							protected void updateItem(ProductGroup prdctGrp, boolean empty) {
								setText(empty ? null : prdctGrp.getGroupName());
							}
						};
						return cell;
					}
				});
		
		productCol.setCellValueFactory(new PropertyValueFactory<>("product"));
		productCol.setCellFactory(
				new Callback<TableColumn<SchemeProduct, Product>, TableCell<SchemeProduct, Product>>() {
					@Override
					public TableCell<SchemeProduct, Product> call(TableColumn<SchemeProduct, Product> param) {
						TableCell<SchemeProduct, Product> cell = new TableCell<SchemeProduct, Product>() {
							@Override
							protected void updateItem(Product prdct, boolean empty) {
								if(prdct!=null){
									setText(empty ? null : prdct.getProductNm());
								}
								else{
									setText(empty ? null : null);
								}
							}
						};
						return cell;
					}
				});
		
		schemeQtyCol.setCellValueFactory(new PropertyValueFactory<>("prdctOrGroupBSQty"));
		schemeQtyCol.setCellFactory(
				new Callback<TableColumn<SchemeProduct, Long>, TableCell<SchemeProduct, Long>>() {
					@Override
					public TableCell<SchemeProduct, Long> call(TableColumn<SchemeProduct, Long> param) {
						TableCell<SchemeProduct, Long> cell = new TableCell<SchemeProduct, Long>() {
							@Override
							protected void updateItem(Long qty, boolean empty) {
								setText(empty ? null : qty+"");
							}
						};
						return cell;
					}
				});
		
		addTotableBut.setOnAction( (e)->{
			ProductGroup prodGroup=productGroupCombo.getValue();
			String schemQtyText =schemqtyTextfld.getText();
			ArrayList<Node> errorTextlst=new ArrayList<>();
			boolean isError=false;
			try{
				if(prodGroup==null){
					errorTextlst.add(new Text("Please choose beverage group."));
					isError=true;
				}
				if(schemQtyText== null || "".equals(schemQtyText.trim())){
					errorTextlst.add(new Text("Please input scheme Qty (b/s)."));
					isError=true;
				}
				else if(!schemQtyText.matches(ValidationRegex.ONLYDIGIT)){
					errorTextlst.add(new Text("Only digits are allowed in Leakage(B/s)."));
					isError=true;
				}			
				if(isError){
					ErrorDialog.showErrorDilogue(errorTextlst, ((StackPane) t.getCurrentNode()),SystemMessages.validation_heading);
					return ;
				}
				SchemeProduct schemeOptionProd = new SchemeProduct();
				schemeOptionProd.setGroupName(prodGroup);
				schemeOptionProd.setProduct(productCombo.getValue());
				schemeOptionProd.setPrdctOrGroupBSQty(Long.parseLong(schemQtyText));
				addSchemeTable.getItems().add(schemeOptionProd);
			}
			catch(Exception r){
				System.out.println("Error in Scheme Dialog ---->"+r.getMessage());
				r.printStackTrace();
			}
		});
		closeDialog.setOnAction( (e) ->{
			closeDialog();
		});
		addSchemeButton.setOnAction((e) ->{
			//TreeItem<T>
			schemeOptionCount++;
			ObservableList<SchemeProduct> options = addSchemeTable.getItems();
			TreeItem<SchemeProduct> treitems = new TreeItem<>(new SchemeProduct("Option-"+schemeOptionCount));
			options.stream().forEach((schemeProduct) -> {
				TreeItem<SchemeProduct> treeItem2=new TreeItem<>();
				treeItem2.setValue(schemeProduct);
				treitems.getChildren().add(treeItem2);
			});
			root.getChildren().add(treitems);
		});
		
		VBox vbox = new VBox(inputHbox, addSchemeTable, addShemeButHbox);
		vbox.getStylesheets().add(getClass().getResource("/application/cdms/fxcss/application.css").toExternalForm());
		return vbox;
	}

	private void closeDialog(){
		schemDialog.close();
	}
	// --------------------------------------List View Costume
	// Class--------------------------------------------------------//
	private class ActionListCell extends JFXListCell<Product> {
		int whichListView;

		ActionListCell(int parameter) {
			super();
			this.whichListView = parameter;
		}

		@Override
		public void updateItem(Product item, boolean empty) {
			super.updateItem(item, empty);

			if (empty) {
				setOnMouseClicked(null);
			} else {
				setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						if (event.getButton().equals(MouseButton.PRIMARY)) {
							if (event.getClickCount() == 2) {
								if (whichListView == 1) {
									System.out.println("first list view");
									selectedProductListView.getItems().add(item);
									groupProdctListView.getItems().remove(item);
								} else if (whichListView == 2) {
									System.out.println("second list view");
									selectedProductListView.getItems().remove(item);
									groupProdctListView.getItems().add(item);
								}
							}
						}
					}
				});
			}
		}
	}
	
	
	@SuppressWarnings("unchecked")
	@FXML
	void UpdateScheme(ActionEvent event){
		((JFXButton) event.getSource()).setDisable(true);
		ObservableList<Product> selectedproductrits =null;
		String productPriceText=null;
		boolean isError =false;
		ArrayList<Node> errorTextlst = new ArrayList<>();
		try{
			selectedproductrits=selectedProductListView.getItems();
			productPriceText=priceTextFld.getText();
			if(selectedproductrits==null || selectedproductrits.size()==0){
				errorTextlst.add(new Text("Please choose Beverage Products."));
				isError=true;
			}
			if(productPriceText==null || "".equals(productPriceText.trim())){
				errorTextlst.add(new Text("Please provide input Beverage Products Price ."));
				isError=true;
			}
			else if(!Pattern.matches(ValidationRegex.DOUBLENUMBERCHECK, productPriceText)){
				errorTextlst.add(new Text("Please provide valid input of Beverage Products Price ."));
				isError=true;
			}
			if(isError){
				ErrorDialog.showErrorDilogue(errorTextlst, ((StackPane) t.getCurrentNode()),SystemMessages.validation_heading);
				return ;
			}
			double productPrice = Double.parseDouble(productPriceText);
			TreeItem<SchemeProduct> treeItems=schemeOptionsTreeView.getRoot();
			ObservableList<TreeItem<SchemeProduct>> options=treeItems.getChildren();
			List<Scheme> schemes= new ArrayList<>();
			for(TreeItem<SchemeProduct> tr : options){
				Scheme sch =null;
				if(tr.getValue().toString().startsWith("Option-")){
					sch= new Scheme();
					ObservableList<TreeItem<SchemeProduct>> option2=tr.getChildren();
					List<SchemeProduct> schemeProductlist = new ArrayList<>();
					for(TreeItem<SchemeProduct> tr2 : option2){
						SchemeProduct schemeProduct=tr2.getValue();
						schemeProductlist.add(schemeProduct);
					}
					sch.setSchemePrdcts(schemeProductlist);
				}
				schemes.add(sch);
			}
			ProductPriceSchemeDtl productPriceSchemeDtl = null;
			List<ProductPriceSchemeDtl> prodPriceDtls = new ArrayList<>();
			for(Product pr : selectedproductrits){
				productPriceSchemeDtl = new ProductPriceSchemeDtl();
				productPriceSchemeDtl.setProduct(pr);
				productPriceSchemeDtl.setPrdctPrice(productPrice);
				productPriceSchemeDtl.setSchemeChoiceLst(schemes);
				prodPriceDtls.add(productPriceSchemeDtl);
			}
		
			productService.updateSchemeNPrice(prodPriceDtls);
			SuccessDialog.showSuccessMessage(null, ((StackPane) t.getCurrentNode()),"Product Price has been updated succesfully.");
		}
		catch(Exception e){
			logger.fatal("UpdtPrdctPriceNSchemeController :: UpdateScheme :: exception ### "+e.getMessage());
			Text text=new Text(e.getMessage());
			ErrorDialog.showErrorDilogue(text,((StackPane) t.getCurrentNode()),SystemMessages.exception_Heading);
			((JFXButton) event.getSource()).setDisable(false);
			e.printStackTrace();
		}
	}
}
