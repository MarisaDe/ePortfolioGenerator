/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.dialogue;

import static epg.StartupConstants.CSS_CLASS_IMAGE_DIALOGUE;
import static epg.StartupConstants.CSS_CLASS_OKCANCEL_HBOX;
import static epg.StartupConstants.ICON_ADD;
import static epg.StartupConstants.ICON_HEADER;
import static epg.StartupConstants.ICON_LIST;
import static epg.StartupConstants.ICON_REMOVE;
import static epg.StartupConstants.PATH_ICONS;
import static epg.StartupConstants.STYLE_SHEET_UI;
import static epg.StartupConstants.TOOLTIP_ADD_LIST_ITEM;
import static epg.StartupConstants.TOOLTIP_REMOVE_LIST_ITEM;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;

/**
 *
 * @author Marisa
 */
public class ListDialogue extends Stage {
   
    TextField inputList;
    Label enterList;
    Button okButton;
    Button cancelButton;
    Button remove;
    Button add;
    VBox vBox;
    HBox listBox;
    HBox okCancel;
    HBox addBox;
    
    public ListDialogue()
    {
      enterList = new Label("Enter List Items:");  
      inputList = new TextField();
      inputList.setPromptText("Enter list item here");
      okButton = new Button("OK");
      okButton.setMinSize(60, 10);
      cancelButton = new Button("Cancel");
      cancelButton.setMinSize(60, 10);
      
      addBox = new HBox(20);
      addBox.setAlignment(Pos.CENTER);
      add = initChildButton(addBox, ICON_ADD,TOOLTIP_ADD_LIST_ITEM,CSS_CLASS_IMAGE_DIALOGUE );
      
      
      vBox = new VBox(20); 
      vBox.setMinSize(400, 400);
      vBox.getStyleClass().add(CSS_CLASS_IMAGE_DIALOGUE);
      
      listBox = new HBox(5);
      listBox.setAlignment(Pos.CENTER);
     
      
      remove = initChildButton(listBox, ICON_REMOVE,TOOLTIP_REMOVE_LIST_ITEM,CSS_CLASS_IMAGE_DIALOGUE );
      listBox.getChildren().add(inputList);
      

      
      okCancel = new HBox(100);
      okCancel.setAlignment(Pos.CENTER);
      okCancel.getStyleClass().add(CSS_CLASS_OKCANCEL_HBOX);
          
      okCancel.getChildren().add(okButton);
      okCancel.getChildren().add(cancelButton);
      
      vBox.getChildren().add(enterList);
      vBox.getChildren().add(listBox);
      vBox.getChildren().add(addBox);
      vBox.getChildren().add(okCancel);
      
      cancelButton.setOnAction(e -> {
      this.hide();
      });
             
      Scene scene = new Scene(vBox);
      scene.getStylesheets().add(STYLE_SHEET_UI);
      this.setTitle("List Component");
      String windowImagePath = "file:" + PATH_ICONS + ICON_LIST;
      Image windowImage = new Image(windowImagePath); 
      this.getIcons().add((windowImage)); 
      setScene(scene);      
    }
    
    public final Button initChildButton(
	    HBox hBox, 
	    String iconFileName, 
	    String tooltip, 
	    String cssClass) {
	PropertiesManager props = PropertiesManager.getPropertiesManager();
	String imagePath = "file:" + PATH_ICONS + iconFileName;
	Image buttonImage = new Image(imagePath);
	Button button = new Button();
	button.getStyleClass().add(cssClass);
	button.setGraphic(new ImageView(buttonImage));
	Tooltip buttonTooltip = new Tooltip(tooltip);
	button.setTooltip(buttonTooltip);
	hBox.getChildren().add(button);
	return button;
    }      
}

