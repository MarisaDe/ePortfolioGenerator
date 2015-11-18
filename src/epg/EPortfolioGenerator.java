/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg;

import epg.view.ePortfolioGeneratorView;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Marisa
 */
public class EPortfolioGenerator extends Application {
    
    ePortfolioGeneratorView ui = new ePortfolioGeneratorView();
    
    @Override
    public void start(Stage primaryStage) {
       
        ui.startUI(primaryStage, "ePortfolio Generator");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
