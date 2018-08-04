package application.cdms.component.data.handler;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class TitledBorder extends StackPane 
{
    private Label titleLabel = new Label();
    private StackPane contentPane = new StackPane();
    private Node content;



    public void setContent(Node content)
    {
        //content.getStyleClass().add("bordered-titled-content");
    	content.setStyle("-fx-padding: 30 15 15 15;");
        contentPane.getChildren().add(content);
    }


    public Node getContent()
    {
        return content;
    }


    public void setTitle(String title)
    {
    titleLabel.setText(" " + title + " ");
    }


    public String getTitle()
    {
        return titleLabel.getText();
    }



    public TitledBorder() 
    {
        titleLabel.setText("default title");
       // titleLabel.getStyleClass().add("bordered-titled-title");
        titleLabel.setStyle("  -fx-background-color:#aeb5ba;-fx-translate-y: -15;-fx-font-size: 1.6em;-fx-text-fill:white");
        StackPane.setAlignment(titleLabel, Pos.TOP_CENTER);

        //getStyleClass().add("bordered-titled-border");
        setStyle("-fx-content-display: top;-fx-border-insets: 20 15 15 15;-fx-background-color: transparent;-fx-border-color: #E0E0E0;-fx-border-width: 0.5;");
        getChildren().addAll(titleLabel, contentPane);
      }

}