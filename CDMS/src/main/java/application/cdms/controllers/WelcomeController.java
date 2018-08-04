package application.cdms.controllers;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import application.Components;
import application.cdms.hibernate.utility.HibernateUtils;
import javafx.animation.Transition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;


@SuppressWarnings("rawtypes")
public class WelcomeController implements Initializable,ScreenController{

    @FXML
    private JFXHamburger menuIcon;

    @FXML
    private JFXDrawer menuDrawer;
    
    @FXML
    private ImageView loginInfoIcon;
    
    private ScreenTransitionController t;
    
    private static Logger logger = Logger.getLogger(WelcomeController.class);
    
    public WelcomeController(){
    	super();
    }
    
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition();
		menuIcon.setAnimation(transition);
		menuDrawer.setOnDrawerOpening(e -> {
	            final Transition animation = menuIcon.getAnimation();
	            animation.setRate(1);
	            animation.play();
	        });
		menuDrawer.setOnDrawerClosing(e -> {
	            final Transition animation = menuIcon.getAnimation();
	            animation.setRate(-1);
	            animation.play();
	        });
		menuIcon.setOnMouseClicked(e -> {
            if (menuDrawer.isHidden() || menuDrawer.isHidding()) {
            	menuDrawer.open();
            } else {
            	menuDrawer.close();
            }
        });
		
		//setting user info icon
		ContextMenu cm = new ContextMenu();
		cm.setPrefWidth(50);
		MenuItem  profileView = new MenuItem("Profile View");
		MenuItem  logoutMenu = new MenuItem("Logout");
		//MenuItem  menu3 = new MenuItem("");
		cm.getItems().addAll(profileView,logoutMenu);
		loginInfoIcon.setOnMouseClicked( (e) ->{
			System.out.println(loginInfoIcon.getLayoutX());
			System.out.println(loginInfoIcon.getTranslateX());
			//System.out.println(loginInfoIcon.getx);
			System.out.println(loginInfoIcon.getFitHeight());
			cm.show(loginInfoIcon,(loginInfoIcon.getLayoutX()+30.0),(loginInfoIcon.getLayoutY()+50.0));
		});
		
		logoutMenu.setOnAction( (e) ->{
			HibernateUtils.getHibernateSessionFactory().close();
			Platform.exit();
		});
	}

	@Override
	public void setScreenTransitionController(Object obj) {
		if(obj instanceof ScreenTransitionController){
			this.t=(ScreenTransitionController) obj;
			logger.info("Welcome User ### "+t.getCurrentLoginUser().getUsername());
			logger.info("Role ### "+t.getCurrentLoginUser().getRole());
		}
		try {
			t.setDrawerContent(Components.SIDEMENU_FXML,menuDrawer);
		} catch (Exception e) {
			logger.fatal("Exception while loading side navigation menu ### "+e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void setParams(Map params) {
		
	}
	
}
