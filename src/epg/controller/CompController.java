/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.controller;

import epg.dialogue.HeaderDialogue;
import epg.dialogue.ImageDialogue;
import epg.dialogue.ListDialogue;
import epg.dialogue.ParagraphDialogue;
import epg.dialogue.TextDialogue;
import epg.dialogue.VideoDialogue;
import epg.view.ePortfolioGeneratorView;
import java.net.MalformedURLException;
import ssm.file.SlideShowFileManager;
import ssm.view.SlideShowMakerView;

/**
 *
 * @author Marisa
 */
public class CompController {
    
    boolean saved;
    private final ePortfolioGeneratorView ui;
    
     public CompController(ePortfolioGeneratorView initUI) {
         
        saved = true;
	ui = initUI;
       
    }
     
    public void handleAddImageRequest() throws MalformedURLException 
    {
        ImageDialogue addImage = new ImageDialogue();
        addImage.showAndWait();
    }
    
    public void handleAddTextRequest() throws MalformedURLException 
    {
        TextDialogue addText = new TextDialogue();
        addText.showAndWait();
    }   
    
    public void handleAddVideoRequest() throws MalformedURLException 
    {
        VideoDialogue addVideo = new VideoDialogue();
        addVideo.showAndWait();    
    }      
    public void handleAddParagraphRequest() throws MalformedURLException 
    {
        ParagraphDialogue addParagraph = new ParagraphDialogue();
        addParagraph.showAndWait();
    }     
    public void handleAddListRequest() throws MalformedURLException 
    {
        ListDialogue addList = new ListDialogue();
        addList.showAndWait();
    }     
    public void handleAddHeaderRequest() throws MalformedURLException 
    {
        HeaderDialogue addHeader = new HeaderDialogue();
        addHeader.showAndWait();
    }  
    public void handleSlideShowRequest() throws MalformedURLException 
    {
        SlideShowFileManager initFileManager = new SlideShowFileManager();
        SlideShowMakerView addSS = new SlideShowMakerView(initFileManager);
    }  
}
