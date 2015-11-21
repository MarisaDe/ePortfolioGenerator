/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.dialogue;

import static epg.StartupConstants.CSS_CLASS_SITE_TOOLBAR;
import static epg.StartupConstants.CSS_CLASS_TEXT_DIALOGUE;
import static epg.StartupConstants.CSS_CLASS_TEXT_DIALOGUE_BUTTON;
import static epg.StartupConstants.CSS_CLASS_TEXT_DIALOGUE_HBOX;
import static epg.StartupConstants.ICON_ADD_IMAGE;
import static epg.StartupConstants.ICON_ADD_TEXT;
import static epg.StartupConstants.ICON_HEADER;
import static epg.StartupConstants.ICON_LIST;
import static epg.StartupConstants.ICON_PARAGRAPH;
import static epg.StartupConstants.PATH_ICONS;
import static epg.StartupConstants.STYLE_SHEET_UI;
import static epg.StartupConstants.TOOLTIP_HEADER;
import static epg.StartupConstants.TOOLTIP_LIST;
import static epg.StartupConstants.TOOLTIP_PARAGRAPH;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;

/**
 *
 * @author Marisa
 */
public class TextDialogue extends Stage{
    
    Label textComp;
    Label selectType;
    Button chooseImage;
    Button headerBtn;
    Button paragraphBtn;
    Button listBtn;
    Button okButton;
    Button cancelButton;
    VBox vBox;
    HBox hBox;
   
    
    public TextDialogue()
    {
        textComp = new Label("Text Component");
        selectType = new Label("Select the type of text component you want to create:");
        okButton = new Button("OK");
        chooseImage = new Button("Choose Image");
        cancelButton = new Button("Cancel");
        
        vBox = new VBox(30);
        vBox.setMinSize(400, 400);
        vBox.getStyleClass().add(CSS_CLASS_TEXT_DIALOGUE);
        vBox.getChildren().add(selectType);
        
        hBox = new HBox(10);
        hBox.getStyleClass().add(CSS_CLASS_TEXT_DIALOGUE_HBOX);
        
        // set up button types

        paragraphBtn = initChildButton(hBox, ICON_PARAGRAPH, TOOLTIP_PARAGRAPH, CSS_CLASS_TEXT_DIALOGUE_BUTTON); 
        listBtn = initChildButton(hBox, ICON_LIST, TOOLTIP_LIST, CSS_CLASS_TEXT_DIALOGUE_BUTTON); 
        headerBtn = initChildButton(hBox, ICON_HEADER, TOOLTIP_HEADER, CSS_CLASS_TEXT_DIALOGUE_BUTTON); 
        vBox.getChildren().add(hBox);
        vBox.getChildren().add(cancelButton);
        
        //init event handlers
        initEventHandlers();
        
        Scene scene = new Scene(vBox);
        scene.getStylesheets().add(STYLE_SHEET_UI);
        this.setTitle("Text Component");
        String windowImagePath = "file:" + PATH_ICONS + ICON_ADD_TEXT;
        Image windowImage = new Image(windowImagePath); 
        this.getIcons().add((windowImage)); 
        setScene(scene);
        
    }
    
       public void initEventHandlers()
       {
            cancelButton.setOnAction(e -> {
	    this.hide();
            });  
            
            paragraphBtn.setOnAction(e -> {
	    this.hide();
            }); 
            
            listBtn.setOnAction(e -> {
	    this.hide();
            }); 
            
            headerBtn.setOnAction(e -> {
	    this.hide();
            });
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
