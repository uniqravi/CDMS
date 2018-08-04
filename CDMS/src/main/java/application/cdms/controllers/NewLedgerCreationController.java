package application.cdms.controllers;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;

import application.SystemMessages;
import application.cdms.component.data.handler.CustomeStringConverter;
import application.cdms.component.data.handler.ErrorDialog;
import application.cdms.models.CstmrLedger;
import application.cdms.models.CustomerDtl;
import application.cdms.models.LedgerType;
import application.cdms.service.CustomerService;
import application.cdms.service.MasterService;
import application.cdms.service.impl.CustomerServiceImpl;
import application.cdms.service.impl.MasterServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

@SuppressWarnings("rawtypes")
public class NewLedgerCreationController implements Initializable,ScreenController {

	private static Logger logger = Logger.getLogger(NewLedgerCreationController.class);
	
	private ScreenTransitionController t;
	
	private MasterService masterService = MasterServiceImpl.getInstance();
	
	private CustomerService customerService = CustomerServiceImpl.getInstance();

    @FXML
    private JFXComboBox<CustomerDtl> cstmrCombo;

    @FXML
    private JFXComboBox<LedgerType> ledgerTypeCombo;

    @FXML
    private JFXDatePicker opendtText;
    
    private boolean isButtonClicked=false;
	
	private boolean isResponseRecieved =false;
	
	@FXML
	private HBox messagePanel;

	@Override
	public void setScreenTransitionController(Object obj) {
		if (obj instanceof ScreenTransitionController<?>) {
			this.t = (ScreenTransitionController) obj;
		}
	}

	@Override
	public void setParams(Map params) {
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ledgerTypeCombo.setItems(masterService.getAllLedgerTypes());
		cstmrCombo.setItems(customerService.getAllCustomers());
		opendtText.setConverter(new CustomeStringConverter());
	}
	 @FXML
	 void createNewLedger(ActionEvent event) {
		 if(!isButtonClicked){
		 	isButtonClicked=true;
		 	CustomerDtl cstmr = cstmrCombo.getValue();
		 	LedgerType ledgerType=ledgerTypeCombo.getValue();
		 	String dt = opendtText.getConverter().toString(opendtText.getValue());
		 	boolean isError=false;
		 	StringBuilder StrBuild = new StringBuilder();
		 	if(cstmr==null){
		 		StrBuild.append("Please provide customer name.");
				isError=true;
		 	}
		 	if(ledgerType==null){
		 		StrBuild.append("Please provide Ledger Type.");
				isError=true;
		 	}
		 	if(opendtText.getValue()==null || opendtText.getValue().equals("")){
		 		StrBuild.append("Please provide Opening Date.");
				isError=true;
		 	}
		 	if(isError){
				ErrorDialog.showErrorDilogue(new Text(StrBuild.toString()), ((StackPane) t.getCurrentNode()), SystemMessages.validation_heading);
				isButtonClicked=false;
				return ;
			}
		 	CstmrLedger cstmrLedger = new CstmrLedger();
		 	cstmrLedger.setAcntOpeningDt(dt);
		 	cstmrLedger.setCstmr(cstmr);
		 	cstmrLedger.setLedgerType(ledgerType);
		 	cstmrLedger.setAcntOpenBy("CDMS");
		 	CstmrLedger returnledger=customerService.createNewLedger(cstmrLedger);
		 	Label text = new Label("We have generated customer id "+returnledger.getCstmrAcntNo());
		 	logger.info("new Ledger created ### "+returnledger.getCstmrAcntNo());
			text.setStyle("-fx-text-fill:green");		
			messagePanel.getChildren().add(text);
			isResponseRecieved=true;
		 }
		 else{
				if(!isResponseRecieved){
					Label label=new Label("Please wait...we will get back soon with customer id.");
					label.setStyle("-fx-font-size: 18px;");
					t.getFadedNotification(label);
				}
				else{
					Label label=new Label("Please create new customer id.");
					label.setStyle("-fx-font-size: 18px;");
					t.getFadedNotification(label);
				}
			}
	 }
}