package application.cdms.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.jfoenix.controls.JFXDrawer;

import application.Components;
import application.cdms.hibernate.utility.HibernateUtils;
import application.cdms.models.LoginUser;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ScreenTransitionController<T extends Pane> {
	
	private static Logger logger = Logger.getLogger(ScreenTransitionController.class);
	
	private enum ScreenReq{
		CURRENT_SCREEN("1"),PREV_SCREEN("-1"),WELCOME_SCREEN("0");
		
		private String screenReq;
		
		ScreenReq(String screenReq){
			this.screenReq=screenReq;
		}

		@SuppressWarnings("unused")
		public String getScreenReq() {
			return screenReq;
		}
	}
	
	private HashMap<ScreenReq, Node> screens; 
 
	private T t;
	
	private StackPane stackPane;
	
	private ScreenTransitionController<T> mainTransition;
	
	private Stage rootStage;
	
	private Scene  rootscene;
	
	private Node drawerSideContent;
	
	private JFXDrawer drawer;
	
	private HBox notificationArea;
	
	private LoginUser loginUser;
	
	//private Group rootGroup;

	public ScreenTransitionController(String name, String resource,LoginUser user){
		screens=new HashMap<ScreenReq, Node>();
		mainTransition=this;
		loginUser=user;
		this.setRootPaneNLaunch(name,resource);
	}
	
	public LoginUser getCurrentLoginUser(){
		return loginUser;
	}
	
	@SuppressWarnings("unchecked")
	private boolean setRootPaneNLaunch(String name, String resource){
		try{
		rootStage = new Stage();
		rootStage.setOnCloseRequest((e) ->{
			e.consume();
			HibernateUtils.getHibernateSessionFactory().close();
			rootStage.close();
		});
		FXMLLoader fxloader = new FXMLLoader(getClass().getResource(resource));
		t  = (T) fxloader.load();
		ScreenController<ScreenTransitionController<?>> controller = fxloader.getController();
		controller.setScreenTransitionController(mainTransition);
		//System.out.println(mainTransition);
		if(t instanceof StackPane){
			System.out.println("Its rootpane.");
			Node node=t.getChildren().get(0);
			if(node instanceof BorderPane){
				System.out.println("Its Borderpane.");
				stackPane=(StackPane)((BorderPane) node).getCenter();
				notificationArea=(HBox) ((BorderPane) node).getBottom();
			}
		}
		//rootGroup=new Group();
		//rootGroup.getChildren().addAll(t);
		rootscene = new Scene(t);
		rootStage.setScene(rootscene);
		Image icon = new Image(getClass().getResourceAsStream(Components.CDMS_ICON));
		rootStage.getIcons().add(icon);
		//rootStage.initStyle(StageStyle.UNDECORATED);
		rootStage.setMaximized(true);
		//rootStage.setFullScreen(true);
		rootStage.show();
		
		addScreen(ScreenReq.WELCOME_SCREEN, t);
		
		}
		catch(IOException io){
			logger.fatal("Error While Loading Welcome Screen ### "+io.getMessage());
			io.printStackTrace();
		}
		return false;
	}
	public void addScreen(ScreenReq name, Node node) {
		screens.put(name, node);
	}
	public boolean loadScreenIntoRoot(String name, String resource,Map<String,Object> passingParams){
		try{
			if(notificationArea.getChildren().size()>0){
			notificationArea.getChildren().remove(0);
			}
		FXMLLoader fxloader = new FXMLLoader(getClass().getResource(resource));
		Parent loadScreen = (Parent) fxloader.load();
		ScreenController<ScreenTransitionController<?>> controller = fxloader.getController();
		controller.setScreenTransitionController((mainTransition));
		controller.setParams(passingParams);
		Node prev = screens.get(ScreenReq.CURRENT_SCREEN);
		if(prev!=null){
			addScreen(ScreenReq.PREV_SCREEN, prev);
		}
		addScreen(ScreenReq.CURRENT_SCREEN, loadScreen);
		setScreen(loadScreen);
		}
		catch(IOException io){
			logger.fatal("File not Found ### "+resource);
			io.printStackTrace();
		}
		return true;
	}
	
	public boolean loadBackScreen(){
		Node prev = screens.get(ScreenReq.PREV_SCREEN);
		if(prev!=null){
			addScreen(ScreenReq.CURRENT_SCREEN, prev);
			screens.remove(ScreenReq.PREV_SCREEN);
			setScreen(prev);
		}
		else{
			System.out.println("No Back Screen");
		}
		return false;
	}
		public boolean setScreen(Node node) {
			if (node != null) { // check screen already loaded
				final DoubleProperty opacity = node.opacityProperty();
				// Is there is more than one screen
				if (!stackPane.getChildren().isEmpty()) { //&& stackPane.getChildren().size()>=2
					// @SuppressWarnings("unchecked")
					/*Timeline fade = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)),
							new KeyFrame(new Duration(1000), (e) -> {
								
								// remove displayed screen.
								//stackPane.getChildren().remove(1);
								// add new screen
								//stackPane.getChildren().add(1, node);
								drawer.setContent(node);
								Timeline fadeIn = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
										new KeyFrame(new Duration(800), new KeyValue(opacity, 1.0)));
								fadeIn.play();
							}, new KeyValue(opacity, 0.0)));
					fade.play();*/
					drawer.setContent(node);
					TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1));
					translateTransition.setNode(node);
					translateTransition.setFromX(400);
					translateTransition.setByX(-400);
					translateTransition.play();
				} else {
					// no one else been displayed, then just show
					node.setOpacity(0.0);
					//stackPane.setOpacity(0.0);
					//ScreenTransitionController.Node drawer =stackPane.getChildren().get(0);
					//stackPane.getChildren().remove(0);
					stackPane.getChildren().addAll(node);
					Timeline fadeIn = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
							new KeyFrame(new Duration(2500), new KeyValue(opacity, 1.0)));
					fadeIn.play();
				}
			} else {
				System.out.println("screen hasn't been loaded!\n");
				return false;
			}
			return true;
		}
		
		public boolean setDrawerContent(String resource,JFXDrawer drawer) throws IOException{
			FXMLLoader fxloader = new FXMLLoader(getClass().getResource(resource));
			drawerSideContent = fxloader.load();
			ScreenController<ScreenTransitionController<?>> screenController=fxloader.getController();
			screenController.setScreenTransitionController(mainTransition);
			this.drawer=drawer;
			this.drawer.setSidePane(drawerSideContent);
			//this.drawer.setAlignment(Pos.TOP_CENTER);
			return true;
		}
		public Node getCurrentNode(){
			return screens.get(ScreenReq.CURRENT_SCREEN);
		}
		public Stage getRootStage(){
			return rootStage;
		}
		public boolean getFadedNotification(Node node){
			if(notificationArea.getChildren().size()>0){
				notificationArea.getChildren().remove(0);
			}
			notificationArea.getChildren().add(node);
			TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(2));
			translateTransition.setNode(node);
			translateTransition.setFromX(200);
			translateTransition.setByX(-200);
			translateTransition.play();
			node.setOpacity(1.0);
			final DoubleProperty opacity = node.opacityProperty();
			Timeline fadeIn = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)),
					new KeyFrame(new Duration(8000), new KeyValue(opacity, 0.0)));
			fadeIn.play();
			return true;
		}
		public boolean getNotification(Node node){
			if(notificationArea.getChildren().size()>0){
				notificationArea.getChildren().remove(0);
			}
			notificationArea.getChildren().add(node);
			//TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(2));
			//translateTransition.setNode(node);
			//translateTransition.setFromX(200);
			//translateTransition.setByX(-200);
			//translateTransition.play();
			return true;
		}
		public boolean getErrorNotification(String errMsg){
			Label label = new Label(errMsg);
			label.setStyle("-fx-background-color: #FFCDD2;-fx-font-size: 18px;-fx-font-weight:bold;");
			if(notificationArea.getChildren().size()>0){
				notificationArea.getChildren().remove(0);
			}
			notificationArea.getChildren().add(label);
			//TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(2));
			//translateTransition.setNode(label);
			//translateTransition.setFromX(200);
			//translateTransition.setByX(-200);
			//translateTransition.play();
			return true;
		}
		
		/*public boolean getNoti(){
			
				@SuppressWarnings("deprecation")
				BorderPane content =BorderPaneBuilder.create()
					 .minWidth(230).minHeight(130)
					 .bottom(getBottomBox(controller))
					 .center(getCenterBox())
					 .style(              "-fx-background-color:linear-gradient(darkslategrey, wheat, white);"
						  + "-fx-background-radius:7;"
						  + "-fx-border-radius:7")
					 .build();
				pp = new Popup();
				pp.setAutoHide(true);
				pp.getContent().add(content);
				pp.show(controller.DOWN.getScene().getWindow());
			}
			return t
		}*/
}
