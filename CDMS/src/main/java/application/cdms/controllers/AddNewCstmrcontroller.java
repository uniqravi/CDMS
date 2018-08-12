package application.cdms.controllers;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import application.SystemMessages;
import application.cdms.component.data.handler.ErrorDialog;
import application.cdms.models.CustomerDtl;
import application.cdms.models.Territory;
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
public class AddNewCstmrcontroller implements Initializable, ScreenController {

	private ScreenTransitionController t;

	private MasterService masterService = MasterServiceImpl.getInstance();
	
	private CustomerService customerService = CustomerServiceImpl.getInstance();

	@FXML
	private JFXTextField cstmrNm;

	@FXML
	private JFXTextField fatherNm;

	@FXML
	private JFXComboBox<Territory> cstmrTerritory;

	@FXML
	private JFXTextField mobileNo;

	@FXML
	private JFXTextField emailId;
	
	@FXML
	private HBox messagePanel;
	
	private boolean isButtonClicked=false;
	
	private boolean isResponseRecieved =false;
	
	private static Logger logger = Logger.getLogger(AddNewCstmrcontroller.class);

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
		logger.info("Initialize");
		cstmrTerritory.setItems(masterService.getAllTerritory());
	}
	
	@FXML
    void addNewCstmr(ActionEvent event) {
		if(!isButtonClicked){
			isButtonClicked=true;
			String cstrmNmStr=cstmrNm.getText();
			String fatherNmStr = fatherNm.getText();
			Territory territory = cstmrTerritory.getValue();
			String mobStr = mobileNo.getText();
			String emailStr = emailId.getText();
			CustomerDtl cstmrDtl = new CustomerDtl();
			boolean isError=false;
			Long mobNo=null;
			StringBuilder StrBuild = new StringBuilder();
			if(cstrmNmStr==null || cstrmNmStr.trim().equals("")){
				StrBuild.append("Please provide customer name.");
				isError=true;
			}
			if(fatherNmStr==null || fatherNmStr.trim().equals("")){
				StrBuild.append("Please provide father name.");
				isError=true;
			}
			if(territory==null){
				StrBuild.append("Please choose territory.");
				isError=true;
			}
			if(isError){
				ErrorDialog.showErrorDilogue(new Text(StrBuild.toString()), ((StackPane) t.getCurrentNode()), SystemMessages.validation_heading);
				isButtonClicked=false;
				return ;
			}
			if(mobStr!=null && !mobStr.trim().equals("")){
				mobNo=Long.parseLong(mobStr);
			}
			cstmrDtl.setCstmrFullname(cstrmNmStr);
			cstmrDtl.setCstmrFathername(fatherNmStr);
			cstmrDtl.setTerritory(territory);
			cstmrDtl.setCstmrMobNo(mobNo);
			cstmrDtl.setCstmrEmail(emailStr);
			cstmrDtl.setCstmrCreatedBy("CDMS");
			CustomerDtl cstomers=customerService.addNewCstmr(cstmrDtl);
			Label text = new Label("We have generated customer id "+cstomers.getCstmrId());
			logger.info("New Customer Has been registered with customer id ### "+cstomers.getCstmrId());
			text.setStyle("-fx-text-fill:green;-fx-font-size: 18px;");		
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
				Label label=new Label("Account has been created already.");
				label.setStyle("-fx-font-size: 18px;");
				t.getFadedNotification(label);
			}
		}
    }
}