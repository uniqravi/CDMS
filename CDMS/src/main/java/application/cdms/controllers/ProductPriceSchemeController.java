package application.cdms.controllers;

import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTreeView;

import application.Components;
import application.cdms.models.Choice;
import application.cdms.models.ProductPriceSchemeDtl;
import application.cdms.models.Scheme;
import application.cdms.models.SchemeProduct;
import application.cdms.service.ProductService;
import application.cdms.service.impl.ProductServiceImpl;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

@SuppressWarnings("rawtypes")
public class ProductPriceSchemeController implements Initializable, ScreenController{

	private static Logger logger = Logger.getLogger(ProductPriceSchemeController.class);
	
	private ScreenTransitionController<?> t;
	
	@FXML
	private TilePane prdctTilePane;
	
	 @FXML
	 private JFXComboBox<Choice> beveGroupSelectionBox;
	
	private ProductService productService = ProductServiceImpl.getInstance();
	
	@Override
	public void setScreenTransitionController(Object t) {
		if (t instanceof ScreenTransitionController) {
			this.t = (ScreenTransitionController) t;
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//String name="300_GLASS_24";
		logger.info("ProductPriceSchemeController :: intialization start");
		beveGroupSelectionBox.setItems(productService.getAllGroupNm());
		beveGroupSelectionBox.valueProperty().addListener((e)->{
			Choice choice=beveGroupSelectionBox.getValue();
			if(prdctTilePane.getChildren().size()>0){
				prdctTilePane.getChildren().remove(0);
			}
			Map<String, Map<String, List<ProductPriceSchemeDtl>>> prdctsPriceSchemeMap=null;
			try {
				prdctsPriceSchemeMap = productService.getPrdctSchemePriceDtl(choice.getKey());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			prdctTilePane.getChildren().addAll(getProductPriceNode(prdctsPriceSchemeMap));
		});
		logger.info("ProductPriceSchemeController :: intialization end");
	}

	public Node getProductPriceNode(Map<String, Map<String, List<ProductPriceSchemeDtl>>> prdctsPriceSchemeMap){
		if(prdctsPriceSchemeMap!=null && prdctsPriceSchemeMap.size()>0){
			VBox vbox = new VBox();
			String groupName="";
			vbox.setStyle("-fx-border-width: 1px; -fx-border-color: #D8D8D8;-fx-pref-width:1100.0;-fx-pref-height:500.0;");
			HBox hBox = new HBox();
			hBox.setAlignment(Pos.CENTER);
			hBox.setStyle("-fx-background-color: #607D8B;");
			JFXTabPane jfxtabPane= new JFXTabPane();
			jfxtabPane.setStyle("-fx-pref-height:323.0");
			jfxtabPane.setSide(Side.LEFT);
			Iterator<Map.Entry<String, Map<String, List<ProductPriceSchemeDtl>>>> outerItr=prdctsPriceSchemeMap.entrySet().iterator();
			while(outerItr.hasNext()){
				Map.Entry<String, Map<String, List<ProductPriceSchemeDtl>>> outerEntry=outerItr.next();
				Tab priceTab=new Tab(outerEntry.getKey());
				jfxtabPane.getTabs().add(priceTab);
				JFXTabPane jfxSchemeTabPane= new JFXTabPane();
				jfxSchemeTabPane.setStyle("-fx-pref-height:323.0");
				jfxSchemeTabPane.setSide(Side.LEFT);
				VBox priceTabVbox = new VBox(); 
				priceTabVbox.getChildren().add(jfxSchemeTabPane);
				priceTabVbox.setStyle("-fx-pref-width:1095.0;-fx-pref-height:396.0;");
				Map<String, List<ProductPriceSchemeDtl>> innerMap =outerEntry.getValue();
				Iterator<Map.Entry<String,List<ProductPriceSchemeDtl>>> innerItr =innerMap.entrySet().iterator();
				while(innerItr.hasNext()){
					Map.Entry<String,List<ProductPriceSchemeDtl>> innerEntry = innerItr.next();
					Tab shemeWiseDevideTab=new Tab(innerEntry.getKey().split("_")[0]);
					List<ProductPriceSchemeDtl> products=innerEntry.getValue();
					jfxSchemeTabPane.getTabs().add(shemeWiseDevideTab);
					int j=0;
					VBox prdctListVbox = new VBox();
					HBox hbox2 = null;
					ProductPriceSchemeDtl productPriceSchemeDtl=null;
					int len = products.size();
					for(int i=0;i<len;i++){
						productPriceSchemeDtl = products.get(i);
						if(j==0){
							hbox2=new HBox();
							hbox2.setAlignment(Pos.CENTER_LEFT);
							hbox2.setPrefHeight(41.0);
							hbox2.setPrefWidth(880.0);
							hbox2.setSpacing(30.0);
							prdctListVbox.getChildren().add(hbox2);
						}
						
						Label label = new Label(productPriceSchemeDtl.getProduct().getProductNm());
						groupName=productPriceSchemeDtl.getProduct().getGroupNm();
						label.setStyle("-fx-font-size: 15px; -fx-text-fill: purple;-fx-pref-width:220.0;-fx-pref-height:35.0;");
						hbox2.getChildren().add(label);
						j++;
						if(j==4){
							j=0;
						}
					}
					Label label = new Label("Products");
					label.setAlignment(Pos.CENTER);
					label.setStyle("-fx-background-color: #3F51B5; -fx-font-size: 15px; -fx-text-fill: white;-fx-pref-width:130.0;-fx-pref-height:42.0;");
					HBox prdctsHbox = new HBox(label,prdctListVbox);
					prdctsHbox.setAlignment(Pos.CENTER_LEFT);
					prdctsHbox.setStyle("-fx-pref-width:896.0;-fx-pref-height:98.0");
					ScrollPane prdctsScrollPane = new ScrollPane(prdctsHbox);
					prdctsScrollPane.setFitToWidth(true);
					prdctsScrollPane.setStyle("-fx-border-width: 1px; -fx-border-color: #D8D8D8;-fx-pref-height:200.0");
					
					
					//setting of Price Hbox
					/*Label priceTagLabel = new Label("Price");
					priceTagLabel.setStyle("-fx-font-size: 15px; -fx-background-color: #3F51B5; -fx-text-fill: white;-fx-pref-width:130.0;-fx-pref-height:42.0;");
					priceTagLabel.setAlignment(Pos.CENTER);
					//TODO for price value
					HBox priceHbox = new HBox(priceTagLabel);
					priceHbox.setStyle("-fx-border-width: 0 0 1px 0; -fx-border-color: #D8D8D8;-fx-pref-width:898.0;-fx-pref-height:47.0;");
					priceHbox.setAlignment(Pos.CENTER_LEFT);*/
					
					//setting of Scheme Hbox
					HBox schemeHbox = new HBox();
					
					Label schemeTagLabel = new Label("Scheme");
					schemeTagLabel.setStyle("-fx-font-size: 15px; -fx-background-color: #3F51B5; -fx-text-fill: white;-fx-pref-width:130.0;-fx-pref-height:42.0;");
					schemeTagLabel.setAlignment(Pos.CENTER);
					List<Scheme> schemesChoices=productPriceSchemeDtl.getSchemeChoiceLst();
					//JFXTabPane schemeTabs = new JFXTabPane();
					JFXTreeView<String> treeView = new JFXTreeView<>();
					treeView.setStyle("-fx-pref-height:240.0;-fx-pref-width:800.0");
					schemeHbox.setAlignment(Pos.CENTER_LEFT);
					schemeHbox.prefHeight(300.0);
					schemeHbox.setPrefWidth(898.0);
					schemeHbox.getChildren().addAll(schemeTagLabel,treeView);
					//final consolidation of components
					//VBox groupConsoVbox = new VBox(prdctsScrollPane,priceHbox,schemeHbox);
					VBox groupConsoVbox = new VBox(prdctsScrollPane,schemeHbox);
					groupConsoVbox.setStyle("-fx-pref-width:1095.0;-fx-pref-height:287.0;");
					AnchorPane groupAnchrPne = new AnchorPane(groupConsoVbox);
					AnchorPane.setTopAnchor(groupConsoVbox, 0.0);
					AnchorPane.setBottomAnchor(groupConsoVbox, 0.0);
					AnchorPane.setLeftAnchor(groupConsoVbox, 0.0);
					AnchorPane.setRightAnchor(groupConsoVbox, 0.0);
					shemeWiseDevideTab.setContent(groupAnchrPne);
					groupName=productPriceSchemeDtl.getProduct().getGroupNm();
					if(schemesChoices==null){
						continue;
					}
					len =schemesChoices.size();
					TreeItem<String> roots = new TreeItem<String>();
					//JFXButton xButton=new JFXButton("X");
					//xButton.setStyle("-fx-text-fill:red;-fx-background-color: #e5eaf5;");
					//xButton.setButtonType(ButtonType.RAISED);
					//xButton.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
					//roots.setGraphic(xButton);
					roots.setValue("Scheme Options");
					roots.setExpanded(true);
					for(int k=0;k<len;k++){
						List<SchemeProduct> scheme=schemesChoices.get(k).getSchemePrdcts();
						TreeItem<String> rootitem = new TreeItem<String>("Option "+(k+1));
						rootitem.setExpanded(true);
						for(SchemeProduct schemeProduct : scheme){
							TreeItem<String> item = new TreeItem<String>();
							item.setValue(schemeProduct.toString());
							item.setExpanded(true);
							rootitem.getChildren().add(item);
						}
						//JFXTabPane schemeDtlTabPane=new JFXTabPane(); 
						/*
						Tab schemeSet = new Tab("Scheme-"+(k+1));
						//Text text1 = new Text("No scheme there");
						AnchorPane anchr = new AnchorPane();
						anchr.setPrefWidth(771.0);
						anchr.setPrefHeight(140.0);
						schemeSet.setContent(anchr);
						schemeTabs.getTabs().add(schemeSet);*/
						roots.getChildren().add(rootitem);
					}
					treeView.setRoot(roots);
					treeView.setShowRoot(false);
				}
				AnchorPane priceTabAnchorPane = new AnchorPane(priceTabVbox);
				AnchorPane.setTopAnchor(priceTabVbox, 0.0);
				AnchorPane.setBottomAnchor(priceTabVbox, 0.0);
				AnchorPane.setLeftAnchor(priceTabVbox, 0.0);
				AnchorPane.setRightAnchor(priceTabVbox, 0.0);
				priceTab.setContent(priceTabAnchorPane);
			}
			Label grouplabel = new Label(groupName);
			grouplabel.setStyle("-fx-pref-width:652.0;-fx-pref-height:64.0;-fx-background-color: #607D8B;-fx-font-size: 18px; -fx-text-fill: white;");
			Hyperlink hiperLink = new Hyperlink("Update");
			Map<String,Object> passingParams=new HashMap<>();
			passingParams.put("groupNm",groupName);
			hiperLink.setOnMouseClicked((e) ->{
				try {
					t.loadScreenIntoRoot(Components.UPDATE_PRODUCT_PRICESCHEME, Components.UPDATE_PRODUCT_PRICESCHEME_FXML, passingParams);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			});
			hiperLink.setStyle("-fx-text-fill: white;");
			hBox.getChildren().addAll(grouplabel,hiperLink);
			vbox.getChildren().addAll(hBox,jfxtabPane);
			return vbox;
		}
		return null;
	}

	@Override
	public void setParams(Map params) {
	}
	
}
