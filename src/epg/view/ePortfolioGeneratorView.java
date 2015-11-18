package epg.view;

import static epg.StartupConstants.CSS_CLASS_COMP_TOOLBOX;
import static epg.StartupConstants.CSS_CLASS_COMP_TOOLBOX_BUTTON;
import static epg.StartupConstants.CSS_CLASS_FILE_TOOLBAR;
import static epg.StartupConstants.CSS_CLASS_FILE_TOOLBAR_BUTTON;
import static epg.StartupConstants.CSS_CLASS_PAGE_EDITOR;
import static epg.StartupConstants.ICON_ADD_IMAGE;
import static epg.StartupConstants.ICON_ADD_SLIDESHOW;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import static epg.StartupConstants.ICON_ADD_TEXT;
import static epg.StartupConstants.ICON_ADD_VIDEO;
import static epg.StartupConstants.PATH_ICONS;
import static epg.StartupConstants.ICON_WINDOW_LOGO;
import static epg.StartupConstants.ICON_NEW_EPG;
import static epg.StartupConstants.ICON_SAVE_EPG;
import static epg.StartupConstants.ICON_LOAD_EPG;
import static epg.StartupConstants.ICON_EXPORT_EPG;
import static epg.StartupConstants.ICON_EXIT_EPG;
import static epg.StartupConstants.ICON_SAVEAS_EPG;
import static epg.StartupConstants.STYLE_SHEET_UI;
import static epg.StartupConstants.TOOLTIP_ADD_IMAGE_COMP;
import static epg.StartupConstants.TOOLTIP_ADD_SS_COMP;
import static epg.StartupConstants.TOOLTIP_ADD_TEXT_COMP;
import static epg.StartupConstants.TOOLTIP_ADD_VIDEO_COMP;
import static epg.StartupConstants.TOOLTIP_EXIT_EPG;
import static epg.StartupConstants.TOOLTIP_EXPORT_EPG;
import static epg.StartupConstants.TOOLTIP_LOAD_EPG;
import static epg.StartupConstants.TOOLTIP_NEW_EPG;
import static epg.StartupConstants.TOOLTIP_SAVEAS_EPG;
import static epg.StartupConstants.TOOLTIP_SAVE_EPG;


import properties_manager.PropertiesManager;


/**
 * This class provides the User Interface for this application,
 * providing controls and the entry points for creating, loading, 
 * saving, editing, and viewing slide shows.
 * 
 * @author McKilla Gorilla & Marisa DePasquale
 */
public class ePortfolioGeneratorView {

    // THIS IS THE MAIN APPLICATION UI WINDOW AND ITS SCENE GRAPH
    Stage primaryStage;
    Scene primaryScene;

    // THIS PANE ORGANIZES THE BIG PICTURE CONTAINERS FOR THE
    // APPLICATION GUI
    BorderPane epgPane;

    // THIS IS THE TOP TOOLBAR AND ITS CONTROLS
    FlowPane fileToolbarPane;
    Button newEPGButton;
    Button loadEPGButton;
    Button saveEPGButton;
    Button saveAsEPGButton;
    Button exportEPGButton;
    Button exitEPGButton;
    
    // WORKSPACE
    HBox workspace;

    // This will go in the Comp Toolbox section
    VBox compToolbox;
    Button addImageButton;
    Button addTextButton;
    Button addVideoButton;
    Button addSSButton;
    
    // FOR THE SLIDE SHOW TITLE
    FlowPane titlePane;
    Label titleLabel;
    TextField titleTextField;
    
    // AND THIS WILL GO IN THE CENTER
    ScrollPane slidesEditorScrollPane;
    VBox slidesEditorPane;

    // THIS IS THE SLIDE SHOW WE'RE WORKING WITH
 //   SlideShowModel slideShow;

    // THIS IS FOR SAVING AND LOADING SLIDE SHOWS
//    SlideShowFileManager fileManager;

    // THIS CLASS WILL HANDLE ALL ERRORS FOR THIS PROGRAM
//    private ErrorHandler errorHandler;

    // THIS CONTROLLER WILL ROUTE THE PROPER RESPONSES
    // ASSOCIATED WITH THE FILE TOOLBAR
//    private FileController fileController;
    
    // THIS CONTROLLER RESPONDS TO SLIDE SHOW EDIT BUTTONS
 //   private SlideShowEditController editController;

    /**
     * Default constructor, it initializes the GUI for use, but does not yet
     * load all the language-dependent controls, that needs to be done via the
     * startUI method after the user has selected a language.
     */

    // ACCESSOR METHODS
   /**public SlideShowModel getSlideShow() {
	return slideShow;
    }

    public Stage getWindow() {
	return primaryStage;
    }

    public ErrorHandler getErrorHandler() {
	return errorHandler;
    }
/**
    /**
     * Initializes the UI controls and gets it rolling.
     * 
     * @param initPrimaryStage The window for this application.
     * 
     * @param windowTitle The title for this window.
     */
    public void startUI(Stage initPrimaryStage, String windowTitle) {
	// THE TOOLBAR ALONG THE NORTH
	initFileToolbar();

        // INIT THE CENTER WORKSPACE CONTROLS BUT DON'T ADD THEM
	// TO THE WINDOW YET
	initCompToolbox();

	// NOW SETUP THE EVENT HANDLERS
	//initEventHandlers();

	// AND FINALLY START UP THE WINDOW (WITHOUT THE WORKSPACE)
	// KEEP THE WINDOW FOR LATER
	primaryStage = initPrimaryStage;
	initWindow(windowTitle);
        
        primaryStage = initPrimaryStage;  
        String windowImagePath = "file:" + PATH_ICONS + ICON_WINDOW_LOGO;
        Image windowImage = new Image(windowImagePath); 
        primaryStage.getIcons().add((windowImage));  
	initWindow(windowTitle);
    }

    // UI SETUP HELPER METHODS
    private void initCompToolbox() {
	// FIRST THE WORKSPACE ITSELF, WHICH WILL CONTAIN TWO REGIONS
	workspace = new HBox();
        workspace.getStyleClass().add(CSS_CLASS_PAGE_EDITOR);
	
	// THIS WILL GO IN THE LEFT SIDE OF THE SCREEN
	compToolbox = new VBox();
	compToolbox.getStyleClass().add(CSS_CLASS_COMP_TOOLBOX);
	addImageButton = this.initChildButton(compToolbox,		ICON_ADD_IMAGE,	    TOOLTIP_ADD_IMAGE_COMP,	    CSS_CLASS_COMP_TOOLBOX_BUTTON,  true);
	addTextButton = this.initChildButton(compToolbox,		ICON_ADD_TEXT,	    TOOLTIP_ADD_TEXT_COMP,	    CSS_CLASS_COMP_TOOLBOX_BUTTON,  true);
        addVideoButton = this.initChildButton(compToolbox,		ICON_ADD_VIDEO,	    TOOLTIP_ADD_VIDEO_COMP,	    CSS_CLASS_COMP_TOOLBOX_BUTTON,  true);
        addSSButton = this.initChildButton(compToolbox,		ICON_ADD_SLIDESHOW,	    TOOLTIP_ADD_SS_COMP,	    CSS_CLASS_COMP_TOOLBOX_BUTTON,  true);
	
	// AND THIS WILL GO IN THE CENTER
	slidesEditorPane = new VBox();
	slidesEditorScrollPane = new ScrollPane(slidesEditorPane);
        //slidesEditorPane.getStyleClass().add(CSS_CLASS_WORKSPACE_BG);
	//initTitleControls();
	
	// NOW PUT THESE TWO IN THE WORKSPACE
	workspace.getChildren().add(compToolbox);
	workspace.getChildren().add(slidesEditorScrollPane);
    }

  /*  private void initEventHandlers() {
	// FIRST THE FILE CONTROLS
	fileController = new FileController(this, fileManager);
	newSlideShowButton.setOnAction(e -> {
	    fileController.handleNewSlideShowRequest();
	});
	loadSlideShowButton.setOnAction(e -> {
	    fileController.handleLoadSlideShowRequest();
	});
	saveSlideShowButton.setOnAction(e -> {
	    fileController.handleSaveSlideShowRequest();
	});
	viewSlideShowButton.setOnAction(e -> {
            try {
                fileController.handleViewSlideShowRequest();
            } catch (MalformedURLException ex) {
                Logger.getLogger(SlideShowMakerView.class.getName()).log(Level.SEVERE, null, ex);
            }
	});
	exitButton.setOnAction(e -> {
	    fileController.handleExitRequest();
	});
	
	// THEN THE SLIDE SHOW EDIT CONTROLS
	editController = new SlideShowEditController(this);
	addSlideButton.setOnAction(e -> {
	    editController.processAddSlideRequest();
	});
	removeSlideButton.setOnAction(e -> {
	    editController.processRemoveSlideRequest();
	});
	moveSlideUpButton.setOnAction(e -> {
	    editController.processMoveSlideUpRequest();
	});
	moveSlideDownButton.setOnAction(e -> {
	    editController.processMoveSlideDownRequest();
	});
    }
/*
    /**
     * This function initializes all the buttons in the toolbar at the top of
     * the application window. These are related to file management.
     */
    private void initFileToolbar() {
	fileToolbarPane = new FlowPane();
        
        fileToolbarPane.getStyleClass().add(CSS_CLASS_FILE_TOOLBAR);
        // HERE ARE OUR FILE TOOLBAR BUTTONS, NOTE THAT SOME WILL
	// START AS ENABLED (false), WHILE OTHERS DISABLED (true)
	PropertiesManager props = PropertiesManager.getPropertiesManager();
	newEPGButton = initChildButton(fileToolbarPane, ICON_NEW_EPG,	TOOLTIP_NEW_EPG,	    CSS_CLASS_FILE_TOOLBAR_BUTTON, false);
	loadEPGButton = initChildButton(fileToolbarPane, ICON_LOAD_EPG,	TOOLTIP_LOAD_EPG,    CSS_CLASS_FILE_TOOLBAR_BUTTON, false);
	saveEPGButton = initChildButton(fileToolbarPane, ICON_SAVE_EPG,	TOOLTIP_SAVE_EPG,    CSS_CLASS_FILE_TOOLBAR_BUTTON, true);
        saveAsEPGButton = initChildButton(fileToolbarPane, ICON_SAVEAS_EPG,	TOOLTIP_SAVEAS_EPG,    CSS_CLASS_FILE_TOOLBAR_BUTTON, true);
	exportEPGButton = initChildButton(fileToolbarPane, ICON_EXPORT_EPG,	TOOLTIP_EXPORT_EPG,   CSS_CLASS_FILE_TOOLBAR_BUTTON, true);
	exitEPGButton = initChildButton(fileToolbarPane, ICON_EXIT_EPG, TOOLTIP_EXIT_EPG, CSS_CLASS_FILE_TOOLBAR_BUTTON, false);
    }

    private void initWindow(String windowTitle) {
	// SET THE WINDOW TITLE
	primaryStage.setTitle(windowTitle);
        


	// GET THE SIZE OF THE SCREEN
	Screen screen = Screen.getPrimary();
	Rectangle2D bounds = screen.getVisualBounds();

	// AND USE IT TO SIZE THE WINDOW
	primaryStage.setX(bounds.getMinX());
	primaryStage.setY(bounds.getMinY());
	primaryStage.setWidth(bounds.getWidth());
	primaryStage.setHeight(bounds.getHeight());

        // SETUP THE UI, NOTE WE'LL ADD THE WORKSPACE LATER
	epgPane = new BorderPane();
	epgPane.setTop(fileToolbarPane);	
	primaryScene = new Scene(epgPane);
	
        // NOW TIE THE SCENE TO THE WINDOW, SELECT THE STYLESHEET
	// WE'LL USE TO STYLIZE OUR GUI CONTROLS, AND OPEN THE WINDOW
	primaryScene.getStylesheets().add(STYLE_SHEET_UI);
	primaryStage.setScene(primaryScene);
	primaryStage.show();
    }
    
    /**
     * This helps initialize buttons in a toolbar, constructing a custom button
     * with a customly provided icon and tooltip, adding it to the provided
     * toolbar pane, and then returning it.
     * @param tooltip
     * @param iconFileName
     * @param cssClass
     * @param disabled
     */
    public Button initChildButton(
	    Pane toolbar, 
	    String iconFileName, 
	    String tooltip, 
	    String cssClass,
	    boolean disabled) {
	PropertiesManager props = PropertiesManager.getPropertiesManager();
	String imagePath = "file:" + PATH_ICONS + iconFileName;
	Image buttonImage = new Image(imagePath);
	Button button = new Button();
	button.getStyleClass().add(cssClass);
	button.setDisable(disabled);
	button.setGraphic(new ImageView(buttonImage));
	Tooltip buttonTooltip = new Tooltip(props.getProperty(tooltip));
	button.setTooltip(buttonTooltip);
	toolbar.getChildren().add(button);
	return button;
    }
    
}
