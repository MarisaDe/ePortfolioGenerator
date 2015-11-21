/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.dialogue;

import static epg.StartupConstants.CSS_CLASS_VIDEO_DIALOGUE;
import static epg.StartupConstants.ICON_ADD_IMAGE;
import static epg.StartupConstants.ICON_ADD_VIDEO;
import static epg.StartupConstants.PATH_ICONS;
import static epg.StartupConstants.STYLE_SHEET_UI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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
    Label height;
    Label width;
    Label pos;
    ComboBox choosePos;
    TextField inputHeight;
    TextField inputWidth;
    VBox vBox;

  public VideoDialogue(){
        
        videoComp = new Label("Video Component");
        okButton = new Button("OK");
        chooseVideoBtn = new Button("Choose Video");
        cancelButton = new Button("Cancel");
        height = new Label("Height:");
        width = new Label("Width:");
        pos = new Label("Float:");
        
        ObservableList<String> floatChoices = FXCollections.observableArrayList();
	floatChoices.add("left");
	floatChoices.add("right");
        floatChoices.add("center");
        
        choosePos = new ComboBox(floatChoices);
	choosePos.getSelectionModel().select("left");
        
        vBox = new VBox();
        vBox.setMinSize(300, 300);
        vBox.getStyleClass().add(CSS_CLASS_VIDEO_DIALOGUE);
        
        vBox.getChildren().add(height);
        vBox.getChildren().add(width);
        vBox.getChildren().add(pos);
        vBox.getChildren().add(choosePos);
        vBox.getChildren().add(okButton);
        vBox.getChildren().add(cancelButton);
        
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
