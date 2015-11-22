/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.dialogue;

import static epg.StartupConstants.CSS_CLASS_HEADER_VBOX;
import static epg.StartupConstants.CSS_CLASS_OKCANCEL_HBOX;
import static epg.StartupConstants.CSS_CLASS_VIDEO_DIALOGUE;
import static epg.StartupConstants.ICON_ADD_VIDEO;
import static epg.StartupConstants.ICON_SELECT_VIDEO;
import static epg.StartupConstants.PATH_ICONS;
import static epg.StartupConstants.STYLE_SHEET_UI;
import static epg.StartupConstants.TOOLTIP_SELECT_VIDEO;
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
public class VideoDialogue extends Stage{
    
    Label videoComp;
    Button chooseVideoBtn;
    Button okButton;
    Button cancelButton;
    Label file;
    Label height;
    Label width;
    Label addVideo;
    Label caption;
    TextField inputHeight;
    TextField inputWidth;
    TextField inputCaption;
    VBox vBox;
    VBox header;
    HBox chooseVideo;
    HBox heightBox; 
    HBox widthBox; 
    HBox okCancel;
    HBox captionBox;

  public VideoDialogue(){
        
       //set up all objects
        videoComp = new Label("Choose Video File:");
        addVideo = new Label ("Add Video Component");
        caption = new Label("Caption:");

        okButton = new Button("OK");
        okButton.setMinSize(60, 10);
        chooseVideoBtn = new Button();
        
        String imagePath = "file:" + PATH_ICONS + ICON_SELECT_VIDEO;
	Image buttonImage = new Image(imagePath);
	chooseVideoBtn.setGraphic(new ImageView(buttonImage));
	Tooltip buttonTooltip = new Tooltip(TOOLTIP_SELECT_VIDEO);
	chooseVideoBtn.setTooltip(buttonTooltip);
        
        
        cancelButton = new Button("Cancel");
        cancelButton.setMinSize(60, 10);
        height = new Label("Height:");
        width = new Label("Width:");
        file = new Label("C://examplevid//filepathloc..");  //EXAMPLE
        inputHeight = new TextField();
        inputHeight.setPromptText("Default Height");
        inputWidth = new TextField();
        inputWidth.setPromptText("Default Width");
        inputCaption = new TextField();
        inputCaption.setPromptText("Enter Caption Here");
        
        
        
        //Contstruct outer box to contain all hboxes
        header = new VBox(5);
        header.getStyleClass().add(CSS_CLASS_HEADER_VBOX);
        vBox = new VBox(5);
        vBox.setMinSize(400, 400);
        vBox.getStyleClass().add(CSS_CLASS_VIDEO_DIALOGUE);
            
        header.getChildren().add(addVideo);
        
        //Construct the hboxes
        captionBox = new HBox(5);
        chooseVideo = new HBox(5);
        heightBox = new HBox(5);
        widthBox = new HBox(9);
        okCancel = new HBox(100);
        okCancel.getStyleClass().add(CSS_CLASS_OKCANCEL_HBOX);
        
        chooseVideo.getChildren().add(videoComp); 
        chooseVideo.getChildren().add(chooseVideoBtn);
        chooseVideo.getChildren().add(file);   
        
        heightBox.getChildren().add(height);      
        heightBox.getChildren().add(inputHeight); 
        
        widthBox.getChildren().add(width);
        widthBox.getChildren().add(inputWidth);
        
        captionBox.getChildren().add(caption);
        captionBox.getChildren().add(inputCaption);
        
        okCancel.getChildren().add(okButton);
        okCancel.getChildren().add(cancelButton);    
        okCancel.setAlignment(Pos.CENTER);
        
        //add all hboxes to the vbox
        vBox.getChildren().add(header);
        vBox.getChildren().add(chooseVideo);
        vBox.getChildren().add(heightBox);
        vBox.getChildren().add(widthBox);
        vBox.getChildren().add(captionBox);
        vBox.getChildren().add(okCancel);
        
        cancelButton.setOnAction(e -> {
	    this.hide();
	});
        
        Scene scene = new Scene(vBox);
        scene.getStylesheets().add(STYLE_SHEET_UI);
        this.setTitle("Video Component");
        String windowImagePath = "file:" + PATH_ICONS + ICON_ADD_VIDEO;
        Image windowImage = new Image(windowImagePath); 
        this.getIcons().add((windowImage)); 
        setScene(scene);
        
    }
}
