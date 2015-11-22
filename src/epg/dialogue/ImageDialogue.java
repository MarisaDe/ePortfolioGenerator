/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.dialogue;

import static epg.StartupConstants.CSS_CLASS_HEADER_VBOX;
import static epg.StartupConstants.CSS_CLASS_IMAGE_DIALOGUE;
import static epg.StartupConstants.CSS_CLASS_OKCANCEL_HBOX;
import static epg.StartupConstants.ICON_ADD_IMAGE;
import static epg.StartupConstants.ICON_SELECT_IMAGE;
import static epg.StartupConstants.PATH_ICONS;
import static epg.StartupConstants.STYLE_SHEET_UI;
import static epg.StartupConstants.TOOLTIP_SELECT_IMAGE;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Marisa
 */
public class ImageDialogue extends Stage{
    
    Button chooseImageBtn;
    Button okButton;
    Button cancelButton;
    Label imageComp;
    Label file;
    Label addImage;
    Label height;
    Label width;
    Label pos;
    Label caption;
    ComboBox choosePos;
    TextField inputHeight;
    TextField inputWidth;
    TextField inputCaption;
    VBox vBox;
    VBox header;
    HBox chooseImage;
    HBox heightBox; 
    HBox widthBox; 
    HBox floatBox;
    HBox okCancel;
    HBox captionBox;
    
    public ImageDialogue(){
       
       //set up all objects
        caption = new Label("Caption:");
        imageComp = new Label("Choose Image File:");
        addImage = new Label ("Add Image Component");
        addImage.getStyleClass().add("CSS_CLASS_HEADER_VBOX");
        
        okButton = new Button("OK");
        okButton.setMinSize(60, 10);
        chooseImageBtn = new Button();
        
        String imagePath = "file:" + PATH_ICONS + ICON_SELECT_IMAGE;
	Image buttonImage = new Image(imagePath);
	chooseImageBtn.setGraphic(new ImageView(buttonImage));
	Tooltip buttonTooltip = new Tooltip(TOOLTIP_SELECT_IMAGE);
	chooseImageBtn.setTooltip(buttonTooltip);
        
        
        cancelButton = new Button("Cancel");
        cancelButton.setMinSize(60, 10);
        height = new Label("Height:");
        width = new Label("Width:");
        pos = new Label("Float:");
        file = new Label("C://examplepic//filepathloc..");  //EXAMPLE
        inputHeight = new TextField();
        inputHeight.setPromptText("Default Height");
        inputWidth = new TextField();
        inputWidth.setPromptText("Default Width");
        inputCaption = new TextField();
        inputCaption.setPromptText("Enter Caption Here");
        
        ObservableList<String> floatChoices = FXCollections.observableArrayList();
	floatChoices.add("Left");
	floatChoices.add("Right");
        floatChoices.add("Neither");
        
        choosePos = new ComboBox(floatChoices);
	choosePos.getSelectionModel().select("Neither");
         
        
        //Contstruct outer box to contain all hboxes
        header = new VBox(10);
        header.getStyleClass().add(CSS_CLASS_HEADER_VBOX);
        vBox = new VBox(5);
        vBox.setMinSize(400, 400);
        vBox.getStyleClass().add(CSS_CLASS_IMAGE_DIALOGUE);
        
        header.getChildren().add(addImage);
        
        //Construct the hboxes
        captionBox = new HBox(5);
        chooseImage = new HBox(5);
        floatBox = new HBox(12);
        heightBox = new HBox(5);
        widthBox = new HBox(9);
        okCancel = new HBox(100);
        okCancel.getStyleClass().add(CSS_CLASS_OKCANCEL_HBOX);
               
        chooseImage.getChildren().add(imageComp); 
        chooseImage.getChildren().add(chooseImageBtn);
        chooseImage.getChildren().add(file);   
        
        heightBox.getChildren().add(height);      
        heightBox.getChildren().add(inputHeight); 
        
        widthBox.getChildren().add(width);
        widthBox.getChildren().add(inputWidth);
        
        floatBox.getChildren().add(pos);
        floatBox.getChildren().add(choosePos);
        
        captionBox.getChildren().add(caption);
        captionBox.getChildren().add(inputCaption);
        
        okCancel.getChildren().add(okButton);
        okCancel.getChildren().add(cancelButton);     
        okCancel.setAlignment(Pos.CENTER);
        
        //add all hboxes to the vbox
        vBox.getChildren().add(header);
        vBox.getChildren().add(chooseImage);
        vBox.getChildren().add(heightBox);
        vBox.getChildren().add(widthBox);
        vBox.getChildren().add(captionBox);
        vBox.getChildren().add(floatBox);   
        vBox.getChildren().add(okCancel);
        
        cancelButton.setOnAction(e -> {
	    this.hide();
	});
        
        Scene scene = new Scene(vBox);
        scene.getStylesheets().add(STYLE_SHEET_UI);
        this.setTitle("Image Component");
        String windowImagePath = "file:" + PATH_ICONS + ICON_ADD_IMAGE;
        Image windowImage = new Image(windowImagePath); 
        this.getIcons().add((windowImage)); 
        setScene(scene);
        
    }
}

