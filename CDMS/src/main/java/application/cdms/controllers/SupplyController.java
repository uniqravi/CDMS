package application.cdms.controllers;

import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import application.cdms.constants.ApplicationConstant;
import application.cdms.data.structure.EfficientDataSet;
import application.cdms.models.Product;
import application.cdms.models.ProductGroup;
import application.cdms.models.RouteDtl;
import application.cdms.models.SupplyDtl;
import application.cdms.models.SupplyPrdcts;
import application.cdms.models.Vehicle;
import application.cdms.service.MasterService;
import application.cdms.service.ProductService;
import application.cdms.service.impl.MasterServiceImpl;
import application.cdms.service.impl.ProductServiceImpl;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.HBox;

@SuppressWarnings("rawtypes")
public class SupplyController implements Initializable, ScreenController{

	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(SupplyController.class);
	
	@SuppressWarnings("unused")
	private ScreenTransitionController<?> t;
	
	//@FXML
	//private TableView<SupplyPrdcts> supplyPrdctsTable;
	
	@FXML
	private TreeTableView<SupplyPrdcts> supplyPrdctsTreeTable;
	
	/*private List<SupplyPrdcts> supplyItems;
	
	private Map<SupplyPrdcts,Integer> tempMapSupplyItem;*/
	private EfficientDataSet<SupplyPrdcts> supplyItms;
	
	private Map<String,TreeItem<SupplyPrdcts>> treeGroupLevelItems;
	
	@FXML
	private HBox centerHBox;
	
	@FXML
	private TableView<SupplyController.SupplyPrdctSumData> supplySumTable;
	
	private ProductService productService = ProductServiceImpl.getInstance();
	
	private MasterService masterService = MasterServiceImpl.getInstance();
	
	private final SupplyController.SupplyPrdctSumData sumData=new SupplyController.SupplyPrdctSumData("Total Glass Qty (B/s)", 0L,"Total Cell Qty",0L);
	
	@Override
	public void setScreenTransitionController(Object obj){
		if (obj instanceof ScreenTransitionController<?>) {
			this.t = (ScreenTransitionController) obj;
		}
	}

	@Override
	public void setParams(Map params) {
		
	}

	private ComboBox<RouteDtl> routeComboBox;
	private ComboBox<Vehicle> VehicleCombox;
	private ComboBox<ProductGroup> groupCombox;
	private ComboBox<Product> prdctComboBox;
	private TextField prdctCsQtyTxtFld;
	private TextField prdctBsQtyTxtFld;
	private TextField exitTimeTxtFld;
	
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		groupCombox = (ComboBox<ProductGroup>) centerHBox.lookup("#groupNmComboBox");
		prdctComboBox = (ComboBox<Product>) centerHBox.lookup("#prdctComboBox");
		prdctCsQtyTxtFld=(TextField) centerHBox.lookup("#prdctCsQtyTxtFld");
		prdctBsQtyTxtFld=(TextField) centerHBox.lookup("#prdctBsQtyTxtFld");
		exitTimeTxtFld=(TextField) centerHBox.lookup("#exitTimeTxtFld");
		routeComboBox=(ComboBox<RouteDtl>) centerHBox.lookup("#routeComboBox");
		VehicleCombox = (ComboBox<Vehicle>) centerHBox.lookup("#vehicleComboBox");
		
		supplyPrdctsTreeTable.setRoot(new TreeItem<SupplyPrdcts>());
		supplyPrdctsTreeTable.setShowRoot(false);
		treeGroupLevelItems=new HashMap<>();
		supplyItms = new EfficientDataSet();
		//syn width & visibility of supply prdct table and sum table
		/*for (int i = 0; i < supplyPrdctsTable.getColumns().size(); i++) {

            TableColumn mainColumn = supplyPrdctsTable.getColumns().get(i);
            TableColumn sumColumn = supplySumTable.getColumns().get(i);
            sumColumn.prefWidthProperty().bind(mainColumn.widthProperty());
            sumColumn.visibleProperty().bind(mainColumn.visibleProperty());
        }*/
		routeComboBox.setItems(masterService.getAllRoute());
		VehicleCombox.setItems(masterService.getAllVehicle());
		Calendar cal = Calendar.getInstance();
		Date dt=cal.getTime();
		String time = ApplicationConstant.timeformatter.format(dt);
		exitTimeTxtFld.setText(time);
		supplySumTable.getItems().add(sumData);
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
	}
	@FXML
	void addSupplyPrdctsToTbl(ActionEvent event){
		Product prdct = prdctComboBox.getValue();
		String groupNm = prdct.getGroupNm();
		String prdctCsQtyStr=prdctCsQtyTxtFld.getText();
		String prdctBsQtyStr=prdctBsQtyTxtFld.getText();
		long pkgQty = prdct.getPackingQty().getPackingQuantity();
		long prdctCsQty = Long.parseLong(prdctCsQtyStr);
		long prdctBsQty = Long.parseLong(prdctBsQtyStr);
		SupplyPrdcts supplyPrdct = new SupplyPrdcts();
		supplyPrdct.setProduct(prdct);
		supplyPrdct.setGroupName(groupNm);
		supplyPrdct.validateThenCalculateSentQty(prdctCsQty, prdctBsQty);
		boolean isDuplicate=false;
		if(supplyItms.isElementPresent(supplyPrdct)){
			isDuplicate=true;
		}
		supplyItms.addOrReplace(supplyPrdct);
		/*if(supplyItms.containsKey(supplyPrdct)){
			logger.info("already product present Do you want to replace it?");
			Integer index=tempMapSupplyItem.get(supplyPrdct);
			supplyItems.set(index,supplyPrdct);
			tempMapSupplyItem.put(supplyPrdct, index);
			
		}
		else{
			supplyItems.add(supplyPrdct);
			int index = supplyItems.size()-1;
			tempMapSupplyItem.put(supplyPrdct, index);
		}*/
		//supplyPrdct.setTotalSentPrdctQty(prdctCsQty);
		//supplyPrdctsTable.getItems().add(supplyPrdct);
		TreeItem<SupplyPrdcts> childCategory = new TreeItem<SupplyPrdcts>(supplyPrdct);
		TreeItem<SupplyPrdcts> root=supplyPrdctsTreeTable.getRoot();
		SupplyPrdcts duplicateSupplyEntry=null;
		if(treeGroupLevelItems.containsKey(groupNm)){
			TreeItem<SupplyPrdcts> groupTreeItem = treeGroupLevelItems.get(groupNm);
			SupplyPrdcts levelgroup=groupTreeItem.getValue();
			if(isDuplicate){
				Iterator<TreeItem<SupplyPrdcts>> itr=groupTreeItem.getChildren().iterator();
				while(itr.hasNext()){
					TreeItem<SupplyPrdcts> treeitm = itr.next();
					if(supplyPrdct.getProduct().getProductNm().equals(treeitm.getValue().getProduct().getProductNm())){
						duplicateSupplyEntry=treeitm.getValue();
						if(levelgroup.getSentPrdctBsQty()<duplicateSupplyEntry.getSentPrdctBsQty()){
							levelgroup.setSentPrdctBsQty(levelgroup.getSentPrdctBsQty()+pkgQty-duplicateSupplyEntry.getSentPrdctBsQty());
							levelgroup.setSentPrdctCsQty(levelgroup.getSentPrdctCsQty()-duplicateSupplyEntry.getSentPrdctCsQty()-1);
						}
						else{
							levelgroup.setSentPrdctBsQty(levelgroup.getSentPrdctBsQty()-duplicateSupplyEntry.getSentPrdctBsQty());
							levelgroup.setSentPrdctCsQty(levelgroup.getSentPrdctCsQty()-duplicateSupplyEntry.getSentPrdctCsQty());
						}
						itr.remove();
						break;
					}
				}
			}
			long existingBsQty = levelgroup.getSentPrdctBsQty();
			long nowBsQty=  existingBsQty+supplyPrdct.getSentPrdctBsQty();
			long nowCsQty=levelgroup.getSentPrdctCsQty()+supplyPrdct.getSentPrdctCsQty();
			long remainQty = nowBsQty-pkgQty;
			if(remainQty<0){
				levelgroup.setSentPrdctBsQty(nowBsQty);
				levelgroup.setSentPrdctCsQty(nowCsQty);
			}
			else{
				levelgroup.setSentPrdctBsQty(remainQty);
				levelgroup.setSentPrdctCsQty(nowCsQty+1);
			}
			groupTreeItem.getChildren().add(childCategory);
		}
		else{
			SupplyPrdcts parentCat = new SupplyPrdcts();
			parentCat.setGroupName(groupNm);
			parentCat.setSentPrdctCsQty(prdctCsQty);
			parentCat.setSentPrdctBsQty(prdctBsQty);
			TreeItem<SupplyPrdcts> groupTreeItem = new TreeItem<SupplyPrdcts>(parentCat);
			groupTreeItem.getChildren().add(childCategory);
			treeGroupLevelItems.put(groupNm, groupTreeItem);
			root.getChildren().add(groupTreeItem);
		}
		if(prdct.getPacking().getPackingName().equals(ApplicationConstant.RETURNABLE_PACKING_NAME)){
			long isSubtractBsQty = 0L;
			if(isDuplicate){
				isSubtractBsQty=duplicateSupplyEntry.getTotalSentPrdctQty();
			}
			sumData.setSentglassBsQty(sumData.getSentglassQty()+(prdctCsQty*prdct.getPackingQty().getPackingQuantity()+prdctBsQty)-isSubtractBsQty);
			sumData.setSentCellQty(sumData.getSentglassQty()/prdct.getPackingQty().getPackingQuantity());
		}
		supplyPrdctsTreeTable.refresh();
		supplySumTable.refresh();
	}
	
	@FXML
	void delSuppyPrdctsFromTbl(ActionEvent event){
		if(supplyItms.size()==0){
			return ;
		}
		int index = supplyPrdctsTreeTable.getSelectionModel().getSelectedIndex();
		TreeItem<SupplyPrdcts> delSupplyItemTree=supplyPrdctsTreeTable.getTreeItem(index);
		SupplyPrdcts supplyItem=delSupplyItemTree.getValue();
		
		System.out.println(delSupplyItemTree.isLeaf());
		System.out.println(index);
		if(delSupplyItemTree.isLeaf()){//child element
			TreeItem<SupplyPrdcts> groupTreeItem = treeGroupLevelItems.get(supplyItem.getGroupName());
			SupplyPrdcts levelgroup=groupTreeItem.getValue();
			Iterator<TreeItem<SupplyPrdcts>> itr=groupTreeItem.getChildren().iterator();
			while(itr.hasNext()){
				TreeItem<SupplyPrdcts> treeitm = itr.next();
				if(supplyItem.getProduct().getProductNm().equals(treeitm.getValue().getProduct().getProductNm())){
					SupplyPrdcts itrSupply=treeitm.getValue();
					if(levelgroup.getSentPrdctBsQty()<itrSupply.getSentPrdctBsQty()){
						levelgroup.setSentPrdctBsQty(levelgroup.getSentPrdctBsQty()+itrSupply.getProduct().getPackingQty().getPackingQuantity()-itrSupply.getSentPrdctBsQty());
						levelgroup.setSentPrdctCsQty(levelgroup.getSentPrdctCsQty()-itrSupply.getSentPrdctCsQty()-1);
					}
					else{
						levelgroup.setSentPrdctBsQty(levelgroup.getSentPrdctBsQty()-itrSupply.getSentPrdctBsQty());
						levelgroup.setSentPrdctCsQty(levelgroup.getSentPrdctCsQty()-itrSupply.getSentPrdctCsQty());
					}
					itr.remove();
					break;
				}
			}
			if(supplyItem.getProduct().getPacking().getPackingName().equals(ApplicationConstant.RETURNABLE_PACKING_NAME)){
				long isSubtractBsQty = supplyItem.getTotalSentPrdctQty();
				sumData.setSentglassBsQty(sumData.getSentglassQty()-isSubtractBsQty);
				sumData.setSentCellQty(sumData.getSentglassQty()/supplyItem.getProduct().getPackingQty().getPackingQuantity());
			}
			supplyItms.remove(supplyItem);
		}
		else{
			
		}
		//long prodctQtyForDel=(long) supplyItem.getTotalSentPrdctQty();
		//long totalLoad=sumData.getTotalqty();
		//totalLoad=totalLoad-prodctQtyForDel;
		//sumData.setTotalqty(totalLoad);
		//supplySumTable.refresh();
		//supplyPrdctsTable.getItems().remove(index);
		//supplyPrdctsTreeTable.getTreeItem(index);
	}
	
	
	@FXML
	void saveSupplyRecord(ActionEvent event){
		try {
		RouteDtl route=routeComboBox.getValue();
		Vehicle vehicle=VehicleCombox.getValue();
		SupplyDtl supply = new SupplyDtl();
		supply.setTotalGlassBsSent(sumData.getSentglassQty());
		supply.setTotalCellSent(sumData.getSentCellQty());
		supply.setSupplyVehicleExitTime(ApplicationConstant.formatter.format(Calendar.getInstance().getTime()));
		supply.setRouteCovered(route);
		supply.setVehicle(vehicle);
		supply.setSupplyWorkerName("Haridwari,Raju");
		supply.setSupplyPrdctDtlLst(supplyItms.getDataSet());
		productService.intiateSupply(supply);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public class SupplyPrdctSumData{
		private SimpleStringProperty glassText;
		//private SimpleLongProperty totalqty;
		private SimpleStringProperty cellText;
		private SimpleLongProperty sentglassQty;
		private SimpleLongProperty sentCellQty;
		
		public SupplyPrdctSumData(String glassText,Long sentGlassBsQty,String cellText,Long sentCellQty){
			this.glassText = new SimpleStringProperty(glassText);
			this.sentglassQty=new SimpleLongProperty(sentGlassBsQty);
			this.cellText = new SimpleStringProperty(cellText);
			this.sentCellQty=new SimpleLongProperty(sentCellQty);
			
		}
		public String getGlassText() {
			return glassText.get();
		}
		
		public void setGlassText(String glassText) {
			this.glassText.set(glassText);
		}
		
		public Long getSentglassQty() {
			return sentglassQty.get();
		}
		public void setSentglassBsQty(Long sentglassQty) {
			this.sentglassQty.set(sentglassQty);
		}
		public String getCellText() {
			return cellText.get();
		}
		
		public void setCellText(String cellText) {
			this.cellText.set(cellText);
		}
		public Long getSentCellQty() {
			return this.sentCellQty.get();
		}
		public void setSentCellQty(Long sentCellQty) {
			this.sentCellQty.set(sentCellQty);
		}
	}
}