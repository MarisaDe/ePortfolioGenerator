/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.dialogue;

import static epg.StartupConstants.ICON_ADD_IMAGE;
import static epg.StartupConstants.ICON_ADD_TEXT;
import static epg.StartupConstants.PATH_ICONS;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Marisa
 */
public class TextDialogue extends Stage{
    
    Label textComp;
    Button chooseImage;
    Button okButton;
    Button cancelButton;
    VBox vBox;
    
    public TextDialogue()
    {
        textComp = new Label("Text Component");
        okButton = new Button("OK");
        chooseImage = new Button("Choose Image");
        cancelButton = new Button("Cancel");
        
        vBox = new VBox();
        vBox.setMinSize(300, 300);
        vBox.getChildren().add(okButton);
        vBox.getChildren().add(cancelButton);
        
        cancelButton.setOnAction(e -> {
	    this.hide();
	});
        
        Scene scene = new Scene(vBox);
        this.setTitle("Text Component");
        String windowImagePath = "file:" + PATH_ICONS + ICON_ADD_TEXT;
        Image windowImage = new Image(windowImagePath); 
        this.getIcons().add((windowImage)); 
        setScene(scene);
        
    }
}
