package application.cdms.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;

import application.SystemMessages;
import application.ValidationRegex;
import application.cdms.component.data.handler.CellFactoryGenerator;
import application.cdms.component.data.handler.CustomeStringConverter;
import application.cdms.component.data.handler.ErrorDialog;
import application.cdms.component.data.handler.SuccessDialog;
import application.cdms.exception.handler.CDMSGeneralException;
import application.cdms.models.Choice;
import application.cdms.models.FillingQty;
import application.cdms.models.Flavour;
import application.cdms.models.HsnTax;
import application.cdms.models.PackingName;
import application.cdms.models.PackingQty;
import application.cdms.models.Product;
import application.cdms.service.MasterService;
import application.cdms.service.ProductService;
import application.cdms.service.impl.MasterServiceImpl;
import application.cdms.service.impl.ProductServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

@SuppressWarnings("rawtypes")
public class ProductController implements Initializable, ScreenController {

	@FXML
	private JFXDatePicker newPrdctAddingDt;

	@FXML
	private JFXToggleButton fillingQtyNew;

	@FXML
	private JFXComboBox<Choice> mlComboBox;

	@FXML
	private JFXTextField mlFillingText;

	@FXML
	private JFXToggleButton newFlavour;

	@FXML
	private JFXComboBox<Choice> flavrComboBox;

	@FXML
	private JFXTextField flavrText;

	@FXML
	private JFXToggleButton newPakingType;

	@FXML
	private JFXComboBox<Choice> pakingTypeComboBox;

	@FXML
	private JFXTextField packingTypeText;

	@FXML
	private JFXToggleButton newPakingQty;

	@FXML
	private JFXComboBox<Choice> pakingQtyComboBox;

	@FXML
	private JFXTextField pakingQtyText;

	@FXML
	private JFXComboBox<Choice> groupNmText;
	
	@FXML
	private JFXComboBox<HsnTax> hsnCombox;

	private ProductService productService = ProductServiceImpl.getInstance();
	
	private MasterService masterService = MasterServiceImpl.getInstance();

	@FXML
	private ScrollPane scrollPane;
	
	private static Logger logger = Logger.getLogger(ProductController.class);

	private ScreenTransitionController t;

	@Override
	public void setScreenTransitionController(Object obj) {
		if (obj instanceof ScreenTransitionController<?>) {
			this.t = (ScreenTransitionController) obj;
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// -------------------------Start Setting Data into-----------------------------------------------
		//------------------------------------------------ ComboBox----------------------------------------
		logger.info("ProductController :: Initialization Start ###");
		List<FillingQty> listFIllingQty = productService.fillingQtyList();
		List<Flavour> lstFlvr = productService.flavrList();
		List<PackingName> lstPackingType = productService.packingtypeList();
		List<PackingQty> lstPackingQty = productService.packingQtyList();
		ObservableList<Choice> fillingCombolist = FXCollections.observableArrayList();
		ObservableList<Choice> flavrComboBoxList = FXCollections.observableArrayList();
		ObservableList<Choice> pakingTypeComboBoxList = FXCollections.observableArrayList();
		ObservableList<Choice> pakingQtyComboBoxList = FXCollections.observableArrayList();
		for (FillingQty fillingQty : listFIllingQty) {
			fillingCombolist.add(new Choice(fillingQty.getFillingQtyCd(), fillingQty.getFillingQtyMl()));
		}
		for (Flavour flavour : lstFlvr) {
			flavrComboBoxList.add(new Choice(flavour.getFlavrCd(), flavour.getFlavrName()));
		}
		for (PackingName packingName : lstPackingType) {
			pakingTypeComboBoxList.add(new Choice(packingName.getPackingNameCd(), packingName.getPackingName()));
		}
		for (PackingQty packingQty : lstPackingQty) {
			pakingQtyComboBoxList.add(new Choice(packingQty.getPackingQtyCd(),
					String.valueOf(packingQty.getPackingQuantity())));
		}
		//newPrdctAddingDt.getmon
		newPrdctAddingDt.setConverter(new CustomeStringConverter());
		newPrdctAddingDt.setDayCellFactory(CellFactoryGenerator.getFutureDayDisableCellFactory());
		mlComboBox.setItems(fillingCombolist);
		flavrComboBox.setItems(flavrComboBoxList);
		pakingTypeComboBox.setItems(pakingTypeComboBoxList);
		pakingQtyComboBox.setItems(pakingQtyComboBoxList);
		hsnCombox.setItems(masterService.getAllHSn());
		// -------------------------END Setting Data into
		// ComboBox------------------------------------------------

		// -------------------------Start providing event to toggle
		// button---------------------------------------
		// for new filling toggle
		fillingQtyNew.setOnMouseClicked((e) -> {
			if (fillingQtyNew.isSelected()) {
				mlFillingText.setDisable(false);
				mlComboBox.setDisable(true);
			} else {
				mlFillingText.clear();
				mlFillingText.setDisable(true);
				mlComboBox.setDisable(false);
			}
		});
		// for new flavour toggle
		newFlavour.setOnMouseClicked((e) -> {
			if (newFlavour.isSelected()) {
				flavrText.setDisable(false);
				flavrComboBox.setDisable(true);
			} else {
				flavrText.clear();
				flavrText.setDisable(true);
				flavrComboBox.setDisable(false);
			}
		});

		// for new packing Type toggle
		newPakingType.setOnMouseClicked((e) -> {
			if (newPakingType.isSelected()) {
				packingTypeText.setDisable(false);
				pakingTypeComboBox.setDisable(true);
			} else {
				packingTypeText.clear();
				packingTypeText.setDisable(true);
				pakingTypeComboBox.setDisable(false);
			}
		});
		// for new paking Qty toggle
		newPakingQty.setOnMouseClicked((e) -> {
			if (newPakingQty.isSelected()) {
				pakingQtyText.setDisable(false);
				pakingQtyComboBox.setDisable(true);
			} else {
				pakingQtyText.clear();
				pakingQtyText.setDisable(true);
				pakingQtyComboBox.setDisable(false);
			}
		});
		// -------------------------End providing event to toggle
		// button---------------------------------------
		// -------------------------Start adding
		// listener------------------------------------------------------
		pakingQtyComboBox.valueProperty().addListener((e) -> {
			Choice mlchoice = mlComboBox.getValue();
			Choice typeChoice = pakingTypeComboBox.getValue();
			Choice qtyChoice = pakingQtyComboBox.getValue();
			if (mlchoice != null && typeChoice != null && qtyChoice != null) {
				List<String> grpNmLst = null;
				try {
					grpNmLst = productService.groupNmLstByParameters(mlchoice.getKey(), typeChoice.getKey(),qtyChoice.getKey());
					ObservableList<Choice> grmComboLst = FXCollections.observableArrayList();
					for (String str : grpNmLst) {
					grmComboLst.add(new Choice(str, str));
					}
					groupNmText.setItems(grmComboLst);
				} catch (Exception e1) {
					List<Node> errorText= new ArrayList<Node>();
					errorText.add(new Text(e1.getMessage()));
					ErrorDialog.showErrorDilogue(errorText,((StackPane) t.getCurrentNode()),SystemMessages.exception_Heading);
					e1.printStackTrace();
				}
			}
		});
		logger.info("ProductController :: Initialization end");
	}

	@FXML
	public void addNewProduct(ActionEvent event) {
		logger.info("addNewProduct :: product addition start");
		((JFXButton) event.getSource()).setDisable(true);
		String prodctAdddt = null;
		String mlfillingCd = null;
		String mlFilling = null;
		boolean isNewFilling = false;
		boolean isNewFlavr = false;
		String flavrCd = null;
		String flavrNm = null;
		boolean isNewPakingType = false;
		String pakingTypeCd = null;
		String pakingTypeNm = null;
		boolean isNewPakingQty = false;
		String pakingQtyCd = null;
		String pakingQtyNm = null;
		String groupNm = null;
		boolean isNewGroup = false;
		boolean isError = false;
		HsnTax hsn = hsnCombox.getValue();
		List<Node> errortext = new ArrayList<Node>();
		logger.info("addNewProduct :: validation start");
		try {
			prodctAdddt = newPrdctAddingDt.getConverter().toString(newPrdctAddingDt.getValue());
			// prodctAdddt=Components.locadateConverter.toString(dt);
			if (fillingQtyNew.isSelected()) {
				isNewFilling = true;
				mlFilling = mlFillingText.getText();
			} else {
				Choice choice = mlComboBox.getValue();
				if (choice != null) {
					mlfillingCd = choice.getKey();
					mlFilling = choice.getValue();
				}
			}
			if (newFlavour.isSelected()) {
				isNewFlavr = true;
				flavrNm = flavrText.getText();
			} else {
				Choice choice = flavrComboBox.getValue();
				if (choice != null) {
					flavrCd = choice.getKey();
					flavrNm = choice.getValue();
				}
			}
			if (newPakingType.isSelected()) {
				isNewPakingType = true;
				pakingTypeNm = packingTypeText.getText();
			} else {
				Choice choice = pakingTypeComboBox.getValue();
				if (choice != null) {
					pakingTypeCd = choice.getKey();
					pakingTypeNm = choice.getValue();
				}
			}
			if (newPakingQty.isSelected()) {
				isNewPakingQty = true;
				pakingQtyNm = pakingQtyText.getText();
			} else {
				Choice choice = pakingQtyComboBox.getValue();
				if (choice != null) {
					pakingQtyCd = choice.getKey();
					pakingQtyNm = choice.getValue();
				}
			}

			int i = 0;
			Choice choicegroup = groupNmText.getValue();
			if (choicegroup != null) {
				groupNm = choicegroup.getKey();
			}
			if (groupNm == null) {
				if (isNewFilling || isNewPakingQty || isNewPakingType) {
					isNewGroup = true;					
				} /*else {
					errortext.add(new Text(++i + " Please choose Beverage Group.")); // group
																						// validation
					isError = true;
				}*/
			}
			// Start of Validation
			if (prodctAdddt == null || "".equals(prodctAdddt.trim())) {
				errortext.add(new Text(++i + " Please choose date from calendar"));
				isError = true;
			}
			if (mlFilling == null || "".equals(mlFilling.trim())) {
				errortext.add(new Text(++i + " Please enter or Choose ML(Qty) value."));
				isError = true;
			} else {
				if (!mlFilling.matches(ValidationRegex.ONLYDIGIT)) {
					errortext.add(new Text(++i + " Only digits are allowed in ML(Qty)"));
					isError = true;
				}
			}
			if (flavrNm == null || "".equals(flavrNm.trim())) {
				errortext.add(new Text(++i + " Please enter or Choose Flavaour Name."));
				isError = true;
			}
			if (pakingTypeNm == null || "".equals(pakingTypeNm.trim())) {
				errortext.add(new Text(++i + " Please enter or Choose Packing Type."));
				isError = true;
			}
			if (pakingQtyNm == null || "".equals(pakingQtyNm.trim())) {
				errortext.add(new Text(++i + " Please enter or Choose Packing Qty."));
				isError = true;
			} else {
				if (!pakingQtyNm.matches(ValidationRegex.ONLYDIGIT)) {
					errortext.add(new Text(++i + " Only digits are allowed in Packing(Qty)"));
					isError = true;
				}
			}
			if(hsn==null){
				errortext.add(new Text(++i + " Please Choose Hsn."));
				isError = true;
			}
			if (isError) {
				ErrorDialog.showErrorDilogue(errortext, ((StackPane) t.getCurrentNode()),SystemMessages.validation_heading);
				((JFXButton) event.getSource()).setDisable(false);
				return;
			}
			logger.info("addNewProduct :: validation end");
			// End of Validation
			Product productModel = new Product();
			productModel.setProductAddedDt(prodctAdddt);
			// Set Filling Qty
			FillingQty fillingQtyModel = new FillingQty();
			fillingQtyModel.setFillingQtyCd(mlfillingCd);
			fillingQtyModel.setFillingQtyMl(mlFilling);
			productModel.setFillingQty(fillingQtyModel);
			productModel.setHasNewFilling(isNewFilling);
			// set Packing Type
			PackingName packingNameModel = new PackingName();
			packingNameModel.setPackingNameCd(pakingTypeCd);
			pakingTypeNm=pakingTypeNm.trim().toUpperCase();
			packingNameModel.setPackingName(pakingTypeNm);
			productModel.setPacking(packingNameModel);
			productModel.setHasNewPakingType(isNewPakingType);
			// Set Flavr
			Flavour flavourModel = new Flavour();
			flavourModel.setFlavrCd(flavrCd);
			flavrNm=flavrNm.trim().toUpperCase();
			flavourModel.setFlavrName(flavrNm);
			productModel.setFlavour(flavourModel);
			productModel.setHasNewFlavour(isNewFlavr);
			// set Packing Qty
			PackingQty packingQtyModel = new PackingQty();
			packingQtyModel.setPackingQtyCd(pakingQtyCd);
			packingQtyModel.setPackingQuantity(Integer.valueOf(pakingQtyNm));
			productModel.setPackingQty(packingQtyModel);
			productModel.setHasNewPakingQty(isNewPakingQty);
			productModel.setHasNewGroup(isNewGroup);
			if(isNewGroup){
				groupNm = mlFilling + "_" + pakingTypeNm + "_" + pakingQtyNm;
			}
			productModel.setGroupNm(groupNm);
			productModel.setProductAddedBy("CDMS");
			//set txn 
			productModel.setHsnTax(hsn);
			String productNm =mlFilling + "_"+flavrNm+"_" + pakingTypeNm + "_" + pakingQtyNm;
			productModel.setProductNm(productNm);
			logger.info("addNewProduct :: begin to new product saving ### "+productNm);
			boolean addingStatus = productService.addNewBeverageProduct(productModel);
			logger.info("addNewProduct :: new product has been saved succesfully ### "+productNm);
			if (addingStatus) {
				newFlavour.setDisable(true);
				fillingQtyNew.setDisable(true);
				newPakingType.setDisable(true);
				newPakingQty.setDisable(true);
				newPrdctAddingDt.setDisable(true);
				mlFillingText.setDisable(true);
				mlComboBox.setDisable(true);
				flavrText.setDisable(true);
				flavrComboBox.setDisable(true);
				packingTypeText.setDisable(true);
				pakingTypeComboBox.setDisable(true);
				pakingQtyText.setDisable(true);
				pakingQtyComboBox.setDisable(true);
				groupNmText.setDisable(true);
				((JFXButton) event.getSource()).setDisable(true);
				SuccessDialog.showSuccessMessage(null, ((StackPane) t.getCurrentNode()),"Product has been added sucessfully.");
			}
		} 
		catch(CDMSGeneralException cdmsEx){
			logger.fatal("addNewProduct :: exception ### "+cdmsEx.getMessage());
			errortext.add(new Text(cdmsEx.getMessage()));
			ErrorDialog.showErrorDilogue(errortext,((StackPane) t.getCurrentNode()),SystemMessages.exception_Heading);
			((JFXButton) event.getSource()).setDisable(false);
		}
		catch (Exception e) {
			logger.fatal("addNewProduct :: exception ### "+e.getMessage());
			errortext.add(new Text(e.getCause().getMessage()));
			ErrorDialog.showErrorDilogue(errortext,((StackPane) t.getCurrentNode()),SystemMessages.exception_Heading);
			((JFXButton) event.getSource()).setDisable(false);
			//return;
		}
	}

	@Override
	public void setParams(Map params) {
		// TODO Auto-generated method stub
		
	}
}