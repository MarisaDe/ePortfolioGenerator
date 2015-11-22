/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.dialogue;

import static epg.StartupConstants.CSS_CLASS_IMAGE_DIALOGUE;
import static epg.StartupConstants.CSS_CLASS_TEXT_DIALOGUE;
import static epg.StartupConstants.ICON_ADD_IMAGE;
import static epg.StartupConstants.ICON_HEADER;
import static epg.StartupConstants.PATH_ICONS;
import static epg.StartupConstants.STYLE_SHEET_UI;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Marisa
 */
public class HeaderDialogue extends Stage{
    
    TextField inputHeader;
    Label enterHeader;
    Button okButton;
    Button cancelButton;
    VBox vBox;
    HBox headerBox;
    HBox okCancel;
    
    public HeaderDialogue()
    {
      enterHeader = new Label("Enter Header:");  
      inputHeader = new TextField();
      inputHeader.setPromptText("Enter header here");
      okButton = new Button("OK");
      okButton.setMinSize(60, 10);
      cancelButton = new Button("Cancel");
      cancelButton.setMinSize(60, 10);
      
      vBox = new VBox(20); 
      vBox.setMinSize(400, 200);
      vBox.getStyleClass().add(CSS_CLASS_IMAGE_DIALOGUE);
      
      headerBox = new HBox(5);
      headerBox.setAlignment(Pos.CENTER);
      headerBox.getChildren().add(enterHeader);
      headerBox.getChildren().add(inputHeader);
      
      okCancel = new HBox(100);
      okCancel.setAlignment(Pos.CENTER);
      okCancel.getChildren().add(okButton);
      okCancel.getChildren().add(cancelButton);
      
      vBox.getChildren().add(headerBox);
      vBox.getChildren().add(okCancel);
      
      cancelButton.setOnAction(e -> {
      this.hide();
      });
             
      Scene scene = new Scene(vBox);
      scene.getStylesheets().add(STYLE_SHEET_UI);
      this.setTitle("Header Component");
      String windowImagePath = "file:" + PATH_ICONS + ICON_HEADER;
      Image windowImage = new Image(windowImagePath); 
      this.getIcons().add((windowImage)); 
      setScene(scene);      
    }
}
