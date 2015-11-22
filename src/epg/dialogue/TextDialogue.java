/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.dialogue;

import static epg.StartupConstants.CSS_CLASS_OKCANCEL_HBOX;
import static epg.StartupConstants.CSS_CLASS_TEXT_DIALOGUE;
import static epg.StartupConstants.CSS_CLASS_TEXT_DIALOGUE_BUTTON;
import static epg.StartupConstants.CSS_CLASS_TEXT_DIALOGUE_HBOX;
import static epg.StartupConstants.ICON_ADD_TEXT;
import static epg.StartupConstants.ICON_HEADER;
import static epg.StartupConstants.ICON_LIST;
import static epg.StartupConstants.ICON_PARAGRAPH;
import static epg.StartupConstants.PATH_ICONS;
import static epg.StartupConstants.STYLE_SHEET_UI;
import static epg.StartupConstants.TOOLTIP_HEADER;
import static epg.StartupConstants.TOOLTIP_LIST;
import static epg.StartupConstants.TOOLTIP_PARAGRAPH;
import epg.controller.CompController;
import epg.view.ePortfolioGeneratorView;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
public class TextDialogue extends Stage{
    
    
    ePortfolioGeneratorView ui;
    CompController compController;
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
    HBox okCancel;
   
    
    public TextDialogue()
    {
        ui = new ePortfolioGeneratorView();
        compController = new CompController(ui);
        textComp = new Label("Text Component");
        selectType = new Label("Select the type of text component you want to create:");
        okButton = new Button("OK");
        okButton.setMinSize(60, 10);
        chooseImage = new Button("Choose Image");
        cancelButton = new Button("Cancel");
        cancelButton.setMinSize(60, 10);
        
        vBox = new VBox(30);
        vBox.setMinSize(400, 400);
        vBox.getStyleClass().add(CSS_CLASS_TEXT_DIALOGUE);
        vBox.getChildren().add(selectType);
        
        okCancel = new HBox(100);
        okCancel.getStyleClass().add(CSS_CLASS_OKCANCEL_HBOX);
        
        
        hBox = new HBox(10);
        hBox.getStyleClass().add(CSS_CLASS_TEXT_DIALOGUE_HBOX);
        
        // set up button types

        paragraphBtn = initChildButton(hBox, ICON_PARAGRAPH, TOOLTIP_PARAGRAPH, CSS_CLASS_TEXT_DIALOGUE_BUTTON); 
        listBtn = initChildButton(hBox, ICON_LIST, TOOLTIP_LIST, CSS_CLASS_TEXT_DIALOGUE_BUTTON); 
        headerBtn = initChildButton(hBox, ICON_HEADER, TOOLTIP_HEADER, CSS_CLASS_TEXT_DIALOGUE_BUTTON); 
        
        okCancel.getChildren().add(okButton);
        okCancel.getChildren().add(cancelButton);
        okCancel.setAlignment(Pos.CENTER);
        
        
        vBox.getChildren().add(hBox);
        vBox.getChildren().add(okCancel);
        
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
            
            listBtn.setOnAction(e -> {
                try {
                    compController.handleAddListRequest();
                } catch (MalformedURLException ex) {
                    Logger.getLogger(TextDialogue.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            });            
            headerBtn.setOnAction(e -> {
                try {
                    compController.handleAddHeaderRequest();
                } catch (MalformedURLException ex) {
                    Logger.getLogger(TextDialogue.class.getName()).log(Level.SEVERE, null, ex);
                }
            
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
