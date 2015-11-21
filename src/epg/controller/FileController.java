/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.controller;

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
public class FileController {
    
    boolean saved; 
    boolean isSiteViewerOpen;
    private ePortfolioGeneratorView ui;
    
     public FileController(ePortfolioGeneratorView initUI) {
         
        isSiteViewerOpen = false;
        saved = true;
	ui = initUI;
       
    }
     
    public void handleSiteViewRequest() throws MalformedURLException 
    {

        Tab tab = new Tab();
        tab.setText("Site Viewer");
        HBox hbox = new HBox();
        
        WebView browser = new WebView();
        WebEngine webEngine = browser.getEngine();
        browser.setMaxHeight(570);
        
        //Opens webview of that specific index file.
        File f = new File("./DummyPage/2.html");
        webEngine.load(f.toURI().toURL().toString());
        
        hbox.getChildren().add(browser);
        hbox.setAlignment(Pos.CENTER); 
        tab.setContent(hbox);
        ui.getWorkSpace().getTabs().add(tab);
        
        isSiteViewerOpen = true;
        

    }
}
