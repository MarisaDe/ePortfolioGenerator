/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.dialogue;

import static epg.StartupConstants.CSS_CLASS_IMAGE_DIALOGUE;
import static epg.StartupConstants.CSS_CLASS_OKCANCEL_HBOX;
import static epg.StartupConstants.CSS_CLASS_WORKSPACE_MODE_TOOLBAR_BUTTON;
import static epg.StartupConstants.ICON_LINK;
import static epg.StartupConstants.ICON_PARAGRAPH;
import static epg.StartupConstants.PATH_ICONS;
import static epg.StartupConstants.STYLE_SHEET_UI;
import static epg.StartupConstants.TOOLTIP_LINK;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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
public class ParagraphDialogue extends Stage{
   
    TextArea inputParagraph;
    Label enterParagraph;
    Label font;
    ComboBox fontChoice;
    Button link;
    Button okButton;
    Button cancelButton;
    Button remove;
    Button add;
    VBox vBox;
    HBox paragraphBox;
    HBox okCancel;
    HBox fontBox;
    
    public ParagraphDialogue()
    {
      enterParagraph = new Label("Enter Paragraph:");  
      font = new Label("Font:");
      
      
    ObservableList<String> fontOptions = FXCollections.observableArrayList(
         "default",
         "Really awesome font",
         "REALLY awesome font",
         "Way 2 awesome",
         "It's so awesome",
         "Mediocre font"
      );      
     
    
      fontChoice = new ComboBox(fontOptions);
      fontChoice.setValue("default");
      
              
      inputParagraph = new TextArea();
      inputParagraph.setPromptText("Enter Paragraph here");
      inputParagraph.setMaxSize(300, 260);
      inputParagraph.setWrapText(true);
      okButton = new Button("OK");
      okButton.setMinSize(60, 10);
      cancelButton = new Button("Cancel");
      cancelButton.setMinSize(60, 10);
      
     // addBox = new HBox(20);
     // addBox.setAlignment(Pos.CENTER);
     // add = initChildButton(addBox, ICON_ADD,TOOLTIP_ADD_LIST_ITEM,CSS_CLASS_IMAGE_DIALOGUE );
      
      
      vBox = new VBox(20); 
      vBox.setMinSize(400, 400);
      vBox.getStyleClass().add(CSS_CLASS_IMAGE_DIALOGUE);
      
      
      fontBox = new HBox(15);
      fontBox.getStyleClass().add(CSS_CLASS_IMAGE_DIALOGUE);
      fontBox.getChildren().add(font);
      fontBox.getChildren().add(fontChoice);
      
      link = new Button();
      link = initChildButton(fontBox, ICON_LINK, TOOLTIP_LINK,CSS_CLASS_WORKSPACE_MODE_TOOLBAR_BUTTON);
      
      
      paragraphBox = new HBox(5);
      paragraphBox.setAlignment(Pos.CENTER);
     
      
     // remove = initChildButton(listBox, ICON_REMOVE,TOOLTIP_REMOVE_LIST_ITEM,CSS_CLASS_IMAGE_DIALOGUE );
      paragraphBox.getChildren().add(inputParagraph);
     
      
      okCancel = new HBox(100);
      okCancel.setAlignment(Pos.CENTER);
      okCancel.getStyleClass().add(CSS_CLASS_OKCANCEL_HBOX);
          
      okCancel.getChildren().add(okButton);
      okCancel.getChildren().add(cancelButton);
      
      vBox.getChildren().add(enterParagraph);
      vBox.getChildren().add(fontBox);
      vBox.getChildren().add(paragraphBox);
      vBox.getChildren().add(okCancel);
      
      cancelButton.setOnAction(e -> {
      this.hide();
      });
             
      Scene scene = new Scene(vBox);
      scene.getStylesheets().add(STYLE_SHEET_UI);
      this.setTitle("Paragraph Component");
      String windowImagePath = "file:" + PATH_ICONS + ICON_PARAGRAPH;
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
