/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.controller;

import epg.dialogue.ImageDialogue;
import epg.dialogue.TextDialogue;
import epg.dialogue.VideoDialogue;
import epg.view.ePortfolioGeneratorView;
import java.io.File;
import java.net.MalformedURLException;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import static javafx.scene.input.KeyCode.SLASH;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 *
 * @author Marisa
 */
public class SiteController {
    
    int numOfPages;
    boolean saved;
    private ePortfolioGeneratorView ui;
    
     public SiteController(ePortfolioGeneratorView initUI) {
         
        numOfPages = 1; 
        saved = true;
	ui = initUI;
       
    }
     
    public void handleAddPageRequest() throws MalformedURLException 
    {
        numOfPages++;
        Tab tab = new Tab();
        tab.setText("Page" + numOfPages);
        HBox hbox = new HBox();      
        hbox.getChildren().add(new Label("Page" + numOfPages));
        hbox.setAlignment(Pos.CENTER); 
        tab.setContent(hbox);
        ui.getWorkSpace().getTabs().add(tab);
        
    }       
}
