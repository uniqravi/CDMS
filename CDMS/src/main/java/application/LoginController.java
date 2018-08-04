package application;
	
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import application.cdms.controllers.ScreenTransitionController;
import application.cdms.models.LoginUser;
import application.cdms.service.LoginService;
import application.cdms.service.impl.LoginServiceImpl;
import application.cdms.utilities.PropertyResourceBundle;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
//import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class LoginController extends Application implements Initializable{
	
	@FXML
	private Label errorLabel;
	
	@FXML
	private JFXTextField txtUsername;
	
	@FXML
	private JFXPasswordField txtPassword;
	
	@FXML
	private BorderPane borderPn;
	
	private LoginService loginService = LoginServiceImpl.getInstance();
	
	private Scene scene;
	
	@FXML
	public void login(ActionEvent event) throws IOException{
		String username = txtUsername.getText();
		String password = txtPassword.getText();
		if(username==null || password==null || "".equals(username) || "".equals(password)){
			errorLabel.setText("Login failed, Please try again");
			return ;
		}
		ProgressBar progressBar = new ProgressBar(0);
		Task<LoginUser> loginTask = new Task<LoginUser>(){
			@Override
			protected LoginUser call() throws Exception {
				LoginUser login = new LoginUser();
				login.setUsername(txtUsername.getText());
				login.setPassword(txtPassword.getText());
				login=loginService.validateCredential(login);
				return login;
			}
		};
		
		HBox hbox =(HBox) borderPn.getBottom();
		progressBar.setPrefWidth(hbox.getWidth());
		hbox.getChildren().add(progressBar);
		
		loginTask.setOnSucceeded( new EventHandler<WorkerStateEvent>(){
			@Override
			public void handle(WorkerStateEvent arg0) {
			LoginUser login = loginTask.getValue();
			//progressBar.setProgress(1);
			progressBar.progressProperty().unbind();
			if(login!=null){
				((Node)(event.getSource())).getScene().getWindow().hide();
				new ScreenTransitionController<BorderPane>(Components.WELCOMESCREEN, Components.WELCOMESCREEN_FXML,login);
			}
			else{
				hbox.getChildren().remove(0);
				errorLabel.setText("Login failed, Please try again");
			}
		}
		});
		loginTask.setOnFailed( (e) -> {
			Throwable exception=loginTask.getException();
			progressBar.progressProperty().unbind();
			//progressBar.setProgress(1);
			hbox.getChildren().remove(0);
			errorLabel.setText("Login failed, Please try again "+exception.getMessage());
			exception.printStackTrace();
		});
		progressBar.progressProperty().bind(loginTask.progressProperty());
		new Thread(loginTask).start();
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource(Components.LOGIN_FXML));
			BorderPane root = (BorderPane) parent;
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.initStyle(StageStyle.UNDECORATED);
			Image icon = new Image(getClass().getResourceAsStream(Components.CDMS_ICON));
			primaryStage.getIcons().add(icon);
			primaryStage.setResizable(false);
			primaryStage.setTitle("CDMS System");
			ToolBar toolBar = new ToolBar();

	        int height = 25;
	        toolBar.setPrefHeight(height);
	        toolBar.setMinHeight(height);
	        toolBar.setMaxHeight(height);
	        toolBar.setStyle("-fx-background-color: #e5eaf5;");
	        toolBar.getItems().add(new WindowButtons());
	        //toolBar.
	        root.setTop(toolBar);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	class WindowButtons extends HBox {

        public WindowButtons() {
            Button closeBtn = new Button("X");

            closeBtn.setOnAction((e) -> {
                    Platform.exit();
            });
            closeBtn.setStyle("-fx-background-color: #9F0000;-fx-text-fill:White;");
            this.getChildren().add(closeBtn);
        }
    }
	
	public static void main(String[] args) throws InterruptedException {
		Runnable th1 = new Runnable() {
			@Override
			public void run() {
				if(checkPostgresDbRunnig()){
					return;
				}
				 // Command to create an external process
				String dbAlreadyRunnigCommand = "D:/binary/PostgreSQL/9.6/bin/pg_ctl.exe status -N postgresql-x64-9.6 -D D:/binary/PostgreSQL/9.6/data -w";
	            String dbStartcommand = "D:/binary/PostgreSQL/9.6/bin/pg_ctl.exe start -N postgresql-x64-9.6 -D D:/binary/PostgreSQL/9.6/data -w";
	            String exit="exit";
	            try {
	            	Runtime run  = Runtime.getRuntime();
	            	Process p = run.exec(dbAlreadyRunnigCommand);
	            	
	            	BufferedReader input =new BufferedReader((new InputStreamReader(p.getInputStream())));
	            	String line="";
	            	String status="";
	            	while ((line=input.readLine()) != null) {
	            		status=line;
	            		System.out.println(line);
	            	}
	            	
	            	input.close();
	            	if(status.contains("no server running")){
	            		System.out.println("stating postgres process");
	            		Process p1 = run.exec(dbStartcommand);
		            	
		            	BufferedReader input1 =new BufferedReader((new InputStreamReader(p1.getInputStream())));
		            	String line1=""; 
		            	while ((line1 = input1.readLine()) != null) {
		            		System.out.println(line1);
		            	}
		            	input1.close();
	            	}
	            	else{
	            		System.out.println("postgres already running");
	            	}
	            	run.exec(exit);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}; 
		Thread th = new Thread(th1);
		th.start();
		th.join();
		launch(args);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	// create a launcher method for this. Here I am going to take like below--
    public void launchHome(Stage stage) {
        stage.setScene(scene);
        //stage.setResizable(true);
        //stage.setFullScreen(true);
        stage.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                setCurrentWidthToStage(number2); 
            }
        });

        stage.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                setCurrentHeightToStage(number2);
            }
        });
        Image icon = new Image(getClass().getResourceAsStream("/com/application/cdms/images/pepsiIcon.png"));
		stage.getIcons().add(icon);
        //Don't forget to add below code in every controller
        stage.hide();
        stage.show();

    }
    
    private void setCurrentWidthToStage(Number number2) {
    	Stage primarystage=new Stage();
        primarystage.setWidth((double) number2);
    }

    private void setCurrentHeightToStage(Number number2) {
    	Stage primarystage=new Stage();
        primarystage.setHeight((double) number2);
    }
    
    private static boolean checkPostgresDbRunnig(){
    	File f = new File(PropertyResourceBundle.get("PG_DATA_PATH"));
    	if(f.exists() && !f.isDirectory()){
    		return true;
    	}
    	return false;
    }
}