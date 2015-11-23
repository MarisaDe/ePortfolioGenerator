package ssm.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.COPY_ATTRIBUTES;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.FileChooser;
import ssm.LanguagePropertyType;
import ssm.model.SlideShowModel;
import ssm.error.ErrorHandler;
import ssm.file.SlideShowFileManager;
import ssm.view.SlideShowMakerView;
import static ssm.StartupConstants.PATH_SLIDE_SHOWS;
import static ssm.file.SlideShowFileManager.SLASH;
/**
 * This class serves as the controller for all file toolbar operations,
 * driving the loading and saving of slide shows, among other things.
 * 
 * @author McKilla Gorilla & Marisa DePasquale
 */
public class FileController {

    // WE WANT TO KEEP TRACK OF WHEN SOMETHING HAS NOT BEEN SAVED
    private boolean saved;

    // THE APP UI
    private SlideShowMakerView ui;
    
    // THIS GUY KNOWS HOW TO READ AND WRITE SLIDE SHOW DATA
    private SlideShowFileManager slideShowIO;

    /**
     * This default constructor starts the program without a slide show file being
     * edited.
     *
     * @param initSlideShowIO The object that will be reading and writing slide show
     * data.
     */
    public FileController(SlideShowMakerView initUI, SlideShowFileManager initSlideShowIO) {
        // NOTHING YET
        saved = true;
	ui = initUI;
        slideShowIO = initSlideShowIO;
    }
    

    /**
     * This method starts the process of editing a new slide show. If a pose is
     * already being edited, it will prompt the user to save it first.
     */
    public void handleNewSlideShowRequest() {
        try {
            // WE MAY HAVE TO SAVE CURRENT WORK
            boolean continueToMakeNew = true;
            if (!saved) {
                // THE USER CAN OPT OUT HERE WITH A CANCEL
                continueToMakeNew = promptToSave();
            }

            // IF THE USER REALLY WANTS TO MAKE A NEW COURSE
            if (continueToMakeNew) {
                // RESET THE DATA, WHICH SHOULD TRIGGER A RESET OF THE UI
                SlideShowModel slideShow = ui.getSlideShow();
		slideShow.reset();
                saved = false;


		// MAKE SURE THE TITLE CONTROLS ARE ENABLED
		ui.reloadTitleControls();		
            }
        } catch (IOException ioe) {
            ErrorHandler eH = ui.getErrorHandler();
            eH.processError(LanguagePropertyType.ERROR_UNEXPECTED);
        }
    }

    /**
     * This method lets the user open a slideshow saved to a file. It will also
     * make sure data for the current slideshow is not lost.
     */
    public void handleLoadSlideShowRequest() {
        try {
            // WE MAY HAVE TO SAVE CURRENT WORK
            boolean continueToOpen = true;
            if (!saved) {
                // THE USER CAN OPT OUT HERE WITH A CANCEL
                continueToOpen = promptToSave();
            }

            // IF THE USER REALLY WANTS TO OPEN A POSE
            if (continueToOpen) {
                // GO AHEAD AND PROCEED MAKING A NEW POSE
                promptToOpen();
            }
        } catch (IOException ioe) {
            ErrorHandler eH = ui.getErrorHandler();
            eH.processError(LanguagePropertyType.ERROR_DATA_FILE_LOADING);
        }
    }

    /**
     * This method will save the current slideshow to a file. Note that we already
     * know the name of the file, so we won't need to prompt the user.
     */
    public boolean handleSaveSlideShowRequest() {
        try {
	    // GET THE SLIDE SHOW TO SAVE
	    SlideShowModel slideShowToSave = ui.getSlideShow();
	    
            // SAVE IT TO A FILE
            slideShowIO.saveSlideShow(slideShowToSave);

            // MARK IT AS SAVED
            saved = true;

	    return true;
        } catch (IOException ioe) {
            ErrorHandler eH = ui.getErrorHandler();
            eH.processError(LanguagePropertyType.ERROR_UNEXPECTED);
	    return false;
        }
    }
    
    public void generateFiles()
    {
        SlideShowModel slideShow = ui.getSlideShow();     
        
        //Generates folder for SlideShowTitle ----------------------------------
        Path titleDir = FileSystems.getDefault().getPath("Sites" + SLASH + slideShow.getTitle());      
        try {
            Files.createDirectory(titleDir);
        } catch (IOException e) {
            System.err.println("Slide Show directory already exists!");
        }
        
        //Copy the JSON file over to the Slide Show directory ------------------
        Path jSONToCopy = Paths.get("data/slide_shows", slideShow.getTitle()+".json");
        Path jSONToPut = Paths.get("Sites" + SLASH + slideShow.getTitle(), jSONToCopy.getFileName().toString());
        try {
        Files.copy(jSONToCopy, jSONToPut, REPLACE_EXISTING, COPY_ATTRIBUTES,
          NOFOLLOW_LINKS);
        } catch (IOException e) {
            System.err.println("JSON file couldn't be copied.");
        }  
        
        //Rename the jSON file so all js files can read the "same" document ----
        File jSONfile = new File("Sites/"+slideShow.getTitle()+"/"+slideShow.getTitle()+".json");
        File newName = new File("Sites" + SLASH + slideShow.getTitle() + SLASH + "title.json");
        Path titlePath = Paths.get("Sites" + SLASH + slideShow.getTitle() + SLASH + "title.json");
        boolean exists = newName.exists();
        if(exists)
        {
            try {
                Files.delete(titlePath);
            } catch (IOException ex) {
                Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(jSONfile.renameTo(newName)) 
        {
           System.out.println("renamed");
        } 
        else 
        {
            System.out.println("Error");
        }
        
        
        // Copy the index file over to the Slide Show directory  ---------------  
        Path indexToCopy = Paths.get("index/public_html", "index.html");
        Path indexToPut = Paths.get("Sites" + SLASH + slideShow.getTitle(), indexToCopy.getFileName().toString());
        try {
        Files.copy(indexToCopy, indexToPut, REPLACE_EXISTING, COPY_ATTRIBUTES,
          NOFOLLOW_LINKS);
        } catch (IOException e) {
            System.err.println("index file couldn't be copied.");
        }      
        
        // Set up the img directory --------------------------------------------
        Path imgDir = FileSystems.getDefault().getPath("Sites" + SLASH + slideShow.getTitle() + SLASH + "img");   
        try {
            Files.createDirectory(imgDir);
        } catch (IOException e) {
            System.err.println("img directory already exists!");
        }
            
        // Add images of slideshow to the img directory.
        for (int i = 0; i<slideShow.getSlides().size(); i++)
        {
           Path imgToCopy = Paths.get(slideShow.getSlides().get(i).getImagePath() + SLASH +
                                      slideShow.getSlides().get(i).getImageFileName());
           Path imgToPut = Paths.get("Sites" + SLASH + slideShow.getTitle() + SLASH + "img", imgToCopy.getFileName().toString()); 
           try {
                Files.copy(imgToCopy, imgToPut, REPLACE_EXISTING, COPY_ATTRIBUTES,
          NOFOLLOW_LINKS);
            } catch (IOException e) {
                System.err.println("img file couldn't be copied");
            }
        }
        
        // Set up the js directory ---------------------------------------------
        Path jsDir = FileSystems.getDefault().getPath("Sites" + SLASH + slideShow.getTitle()+ SLASH +"js");
        
        try {
            Files.createDirectory(jsDir);
        } catch (IOException e) {
            System.err.println("js directory already exists!");
        }
        
        // Copy the js file over to the Slide Show js directory  ---------------  
        Path jsToCopy = Paths.get("index/public_html", "js.js");
        Path jsToPut = Paths.get("Sites" + SLASH + slideShow.getTitle() + SLASH + "js", jsToCopy.getFileName().toString());
        try {
        Files.copy(jsToCopy, jsToPut, REPLACE_EXISTING, COPY_ATTRIBUTES,
          NOFOLLOW_LINKS);
        } catch (IOException e) {
            System.err.println("js file couldn't be copied.");
        }
          
        //Set up the CSS directory ---------------------------------------------
        Path cssDir = FileSystems.getDefault().getPath("Sites" + SLASH + slideShow.getTitle()+ SLASH + "css");
        try {
            Files.createDirectory(cssDir);
        } catch (IOException e) {
            System.err.println("CSS directory already exists!");
        }
        
        // Copy the css file over to the Slide Show directory  ---------------  
        Path cssToCopy = Paths.get("index/public_html/css", "style.css");
        Path cssToPut = Paths.get("Sites" + SLASH + slideShow.getTitle() + SLASH + "css", cssToCopy.getFileName().toString());
        try {
        Files.copy(cssToCopy, cssToPut, REPLACE_EXISTING, COPY_ATTRIBUTES,
          NOFOLLOW_LINKS);
        } catch (IOException e) {
            System.err.println("CSS file couldn't be copied.");
        } 
        
        // Copy the font file1 over to the Slide Show directory  ---------------  
        Path font1ToCopy = Paths.get("index/public_html/css", "fonts.otf");
        Path font1ToPut = Paths.get("Sites" + SLASH + slideShow.getTitle() + SLASH + "css", font1ToCopy.getFileName().toString());
        try {
        Files.copy(font1ToCopy, font1ToPut, REPLACE_EXISTING, COPY_ATTRIBUTES,
          NOFOLLOW_LINKS);
        } catch (IOException e) {
            System.err.println("Font file 1 couldn't be copied.");
        } 
        
        // Copy the font file2 over to the Slide Show directory  ---------------  
        Path font2ToCopy = Paths.get("index/public_html/css", "markers.ttf");
        Path font2ToPut = Paths.get("Sites" + SLASH + slideShow.getTitle() + SLASH + "css", font2ToCopy.getFileName().toString());
        try {
        Files.copy(font2ToCopy, font2ToPut, REPLACE_EXISTING, COPY_ATTRIBUTES,
          NOFOLLOW_LINKS);
        } catch (IOException e) {
            System.err.println("Font file 2 couldn't be copied.");
        } 
    }
    

 
     /**
     * This method will exit the application, making sure the user doesn't lose
     * any data first.
     */
    public void handleExitRequest() {
        try {
            // WE MAY HAVE TO SAVE CURRENT WORK
            boolean continueToExit = true;
            if (!saved) {
                // THE USER CAN OPT OUT HERE
                continueToExit = promptToSave();
            }

            // IF THE USER REALLY WANTS TO EXIT THE APP
            if (continueToExit) {
                // EXIT THE APPLICATION
                System.exit(0);
            }
        } catch (IOException ioe) {
            ErrorHandler eH = ui.getErrorHandler();
            eH.processError(LanguagePropertyType.ERROR_UNEXPECTED);
        }
    }

    /**
     * This helper method verifies that the user really wants to save their
     * unsaved work, which they might not want to do. Note that it could be used
     * in multiple contexts before doing other actions, like creating a new
     * pose, or opening another pose, or exiting. Note that the user will be
     * presented with 3 options: YES, NO, and CANCEL. YES means the user wants
     * to save their work and continue the other action (we return true to
     * denote this), NO means don't save the work but continue with the other
     * action (true is returned), CANCEL means don't save the work and don't
     * continue with the other action (false is retuned).
     *
     * @return true if the user presses the YES option to save, true if the user
     * presses the NO option to not save, false if the user presses the CANCEL
     * option to not continue.
     */
    private boolean promptToSave() throws IOException {
        // PROMPT THE USER TO SAVE UNSAVED WORK
        boolean saveWork = true; 

        // IF THE USER SAID YES, THEN SAVE BEFORE MOVING ON
        if (saveWork) {
            SlideShowModel slideShow = ui.getSlideShow();
            slideShowIO.saveSlideShow(slideShow);
            saved = true;
        } // IF THE USER SAID CANCEL, THEN WE'LL TELL WHOEVER
        // CALLED THIS THAT THE USER IS NOT INTERESTED ANYMORE
        else if (!true) {
            return false;
        }

        // IF THE USER SAID NO, WE JUST GO ON WITHOUT SAVING
        // BUT FOR BOTH YES AND NO WE DO WHATEVER THE USER
        // HAD IN MIND IN THE FIRST PLACE
        return true;
    }

    /**
     * This helper method asks the user for a file to open. The user-selected
     * file is then loaded and the GUI updated. Note that if the user cancels
     * the open process, nothing is done. If an error occurs loading the file, a
     * message is displayed, but nothing changes.
     */
    private void promptToOpen() {
        // AND NOW ASK THE USER FOR THE COURSE TO OPEN
        FileChooser slideShowFileChooser = new FileChooser();
        slideShowFileChooser.setInitialDirectory(new File(PATH_SLIDE_SHOWS));
        File selectedFile = slideShowFileChooser.showOpenDialog(ui.getWindow());

        // ONLY OPEN A NEW FILE IF THE USER SAYS OK
        if (selectedFile != null) {
            try {
		SlideShowModel slideShowToLoad = ui.getSlideShow();
                slideShowIO.loadSlideShow(slideShowToLoad, selectedFile.getAbsolutePath());
                ui.reloadSlideShowPane();
                saved = true;
            } catch (Exception e) {
                ErrorHandler eH = ui.getErrorHandler();
		eH.processError(LanguagePropertyType.ERROR_UNEXPECTED);
            }
        }
    }

    /**
     * This mutator method marks the file as not saved, which means that when
     * the user wants to do a file-type operation, we should prompt the user to
     * save current work first. Note that this method should be called any time
     * the pose is changed in some way.
     */
    public void markFileAsNotSaved() {
        saved = false;
    }

    /**
     * Accessor method for checking to see if the current pose has been saved
     * since it was last editing. If the current file matches the pose data,
     * we'll return true, otherwise false.
     *
     * @return true if the current pose is saved to the file, false otherwise.
     */
    public boolean isSaved() {
        return saved;
    }
}

