package ssm.view;

import static epg.StartupConstants.ICON_ADD_SLIDESHOW;
import static epg.StartupConstants.PATH_ICONS;
import static epg.StartupConstants.STYLE_SHEET_UI;
import static epg.StartupConstants.ICON_MOVE_UP;
import static epg.StartupConstants.ICON_MOVE_DOWN;
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
import javafx.stage.Stage;
import properties_manager.PropertiesManager;
import ssm.LanguagePropertyType;
import static ssm.LanguagePropertyType.LABEL_SLIDESHOW_TITLE;
import static ssm.LanguagePropertyType.TOOLTIP_ADD_SLIDE;
import static ssm.LanguagePropertyType.TOOLTIP_MOVE_DOWN;
import static ssm.LanguagePropertyType.TOOLTIP_MOVE_UP;
import static ssm.LanguagePropertyType.TOOLTIP_REMOVE_SLIDE;
import static ssm.StartupConstants.CSS_CLASS_SELECTED_SLIDE_EDIT_VIEW;
import static ssm.StartupConstants.CSS_CLASS_SLIDE_EDIT_VIEW;
import static ssm.StartupConstants.CSS_CLASS_SLIDE_SHOW_EDIT_VBOX;
import static ssm.StartupConstants.CSS_CLASS_VERTICAL_TOOLBAR_BUTTON;
import static ssm.StartupConstants.CSS_CLASS_SLIDE_SHOW_FILE_PANE;
import static ssm.StartupConstants.CSS_CLASS_TITLE;
import static ssm.StartupConstants.CSS_CLASS_WORKSPACE_BG;
import static ssm.StartupConstants.ICON_ADD_SLIDE;
import static ssm.StartupConstants.ICON_REMOVE_SLIDE;
import ssm.controller.FileController;
import ssm.controller.SlideShowEditController;
import ssm.model.Slide;
import ssm.model.SlideShowModel;
import ssm.error.ErrorHandler;
import ssm.file.SlideShowFileManager;

/**
 * This class provides the User Interface for this application,
 * providing controls and the entry points for creating, loading, 
 * saving, editing, and viewing slide shows.
 * 
 * @author McKilla Gorilla & Marisa DePasquale
 */
public class SlideShowMakerView extends Stage{

    // THIS IS THE MAIN APPLICATION UI WINDOW AND ITS SCENE GRAPH
    Stage primaryStage = new Stage();
    Scene primaryScene;

    // THIS PANE ORGANIZES THE BIG PICTURE CONTAINERS FOR THE
    // APPLICATION GUI
    BorderPane ssmPane;

    // THIS IS THE TOP TOOLBAR AND ITS CONTROLS
    FlowPane okCancelPane;
    Button okButton;
    Button cancelButton;
    
    // WORKSPACE
    HBox workspace;

    // THIS WILL GO IN THE LEFT SIDE OF THE SCREEN
    VBox slideEditToolbar;
    Button addSlideButton;
    Button removeSlideButton;
    Button moveSlideUpButton;
    Button moveSlideDownButton;
    
    // FOR THE SLIDE SHOW TITLE
    FlowPane titlePane;
    Label titleLabel;
    TextField titleTextField;
    
    // AND THIS WILL GO IN THE CENTER
    ScrollPane slidesEditorScrollPane;
    VBox slidesEditorPane;

    // THIS IS THE SLIDE SHOW WE'RE WORKING WITH
    SlideShowModel slideShow;

    // THIS IS FOR SAVING AND LOADING SLIDE SHOWS
    SlideShowFileManager fileManager;

    // THIS CLASS WILL HANDLE ALL ERRORS FOR THIS PROGRAM
    private ErrorHandler errorHandler;

    // THIS CONTROLLER WILL ROUTE THE PROPER RESPONSES
    // ASSOCIATED WITH THE FILE TOOLBAR
    private FileController fileController;
    
    // THIS CONTROLLER RESPONDS TO SLIDE SHOW EDIT BUTTONS
    private SlideShowEditController editController;

    /**
     * Default constructor, it initializes the GUI for use, but does not yet
     * load all the language-dependent controls, that needs to be done via the
     * startUI method after the user has selected a language.
     */
    public SlideShowMakerView(SlideShowFileManager initFileManager) {
      
      fileManager = initFileManager;
	
      slideShow = new SlideShowModel(this);

      errorHandler = new ErrorHandler(this);
      
      startUI("Slide Show Component");
              
    }

    // ACCESSOR METHODS
    public SlideShowModel getSlideShow() {
	return slideShow;
    }

    public Stage getWindow() {
	return primaryStage;
    }

    public ErrorHandler getErrorHandler() {
	return errorHandler;
    }

    /**
     * Initializes the UI controls and gets it rolling.
     * 
     * @param initPrimaryStage The window for this application.
     * 
     * @param windowTitle The title for this window.
     */
    public void startUI(String windowTitle) {
	// THE TOOLBAR ALONG THE NORTH
	initFileToolbar();

        // INIT THE CENTER WORKSPACE CONTROLS BUT DON'T ADD THEM
	// TO THE WINDOW YET
	initWorkspace();

	// NOW SETUP THE EVENT HANDLERS
	initEventHandlers();

	// AND FINALLY START UP THE WINDOW (WITHOUT THE WORKSPACE)
	// KEEP THE WINDOW FOR LATER
	primaryStage = new Stage();
	initWindow("Slide Show Component");
        
        String windowImagePath = "file:" + PATH_ICONS + ICON_ADD_SLIDESHOW;
        Image windowImage = new Image(windowImagePath); 
        primaryStage.getIcons().add((windowImage));  
        
        primaryScene.getStylesheets().add(STYLE_SHEET_UI);
	primaryStage.setScene(primaryScene);
	primaryStage.show();
    }

    // UI SETUP HELPER METHODS
    private void initWorkspace() {
	// FIRST THE WORKSPACE ITSELF, WHICH WILL CONTAIN TWO REGIONS
	workspace = new HBox();
        workspace.getStyleClass().add(CSS_CLASS_WORKSPACE_BG);
	
	// THIS WILL GO IN THE LEFT SIDE OF THE SCREEN
	slideEditToolbar = new VBox();
	slideEditToolbar.getStyleClass().add(CSS_CLASS_SLIDE_SHOW_EDIT_VBOX);
	addSlideButton = this.initChildButton(slideEditToolbar,		ICON_ADD_SLIDE,	    TOOLTIP_ADD_SLIDE,	    CSS_CLASS_VERTICAL_TOOLBAR_BUTTON,  true);
	removeSlideButton = this.initChildButton(slideEditToolbar,	ICON_REMOVE_SLIDE,  TOOLTIP_REMOVE_SLIDE,   CSS_CLASS_VERTICAL_TOOLBAR_BUTTON,  true);
	moveSlideUpButton = this.initChildButton(slideEditToolbar,	ICON_MOVE_UP,	    TOOLTIP_MOVE_UP,	    CSS_CLASS_VERTICAL_TOOLBAR_BUTTON,  true);
	moveSlideDownButton = this.initChildButton(slideEditToolbar,	ICON_MOVE_DOWN,	    TOOLTIP_MOVE_DOWN,	    CSS_CLASS_VERTICAL_TOOLBAR_BUTTON,  true);
	
	// AND THIS WILL GO IN THE CENTER
	slidesEditorPane = new VBox();
	slidesEditorScrollPane = new ScrollPane(slidesEditorPane);
        //slidesEditorPane.getStyleClass().add(CSS_CLASS_WORKSPACE_BG);
	initTitleControls();
	
	// NOW PUT THESE TWO IN THE WORKSPACE
	workspace.getChildren().add(slideEditToolbar);
	workspace.getChildren().add(slidesEditorScrollPane);
    }

    private void initEventHandlers() {
	// FIRST THE FILE CONTROLS
	fileController = new FileController(this, fileManager);
	okButton.setOnAction(e -> {
	    fileController.handleNewSlideShowRequest();
	});
	cancelButton.setOnAction(e -> {
	    fileController.handleLoadSlideShowRequest();
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

    /**
     * This function initializes all the buttons in the toolbar at the top of
     * the application window. These are related to file management.
     */
    private void initFileToolbar() {
	okCancelPane = new FlowPane();       
        okCancelPane.getStyleClass().add(CSS_CLASS_SLIDE_SHOW_FILE_PANE);

	PropertiesManager props = PropertiesManager.getPropertiesManager();
	
        okButton = new Button("OK");
        okButton.setMinSize(60, 10);
        
        cancelButton = new Button("CANCEL");
        cancelButton.setMinSize(60, 10);
        
        okCancelPane.getChildren().add(okButton);
        okCancelPane.getChildren().add(cancelButton);
        
    }

    private void initWindow(String windowTitle) {
	// SET THE WINDOW TITLE
	primaryStage.setTitle(windowTitle);
     
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(400);
        // SETUP THE UI, NOTE WE'LL ADD THE WORKSPACE LATER
	ssmPane = new BorderPane();
	ssmPane.setTop(okCancelPane);	
        ssmPane.setLeft(slideEditToolbar);
	primaryScene = new Scene(ssmPane);


    }
    
    /**
     * This helps initialize buttons in a toolbar, constructing a custom button
     * with a customly provided icon and tooltip, adding it to the provided
     * toolbar pane, and then returning it.
     */
    public Button initChildButton(
	    Pane toolbar, 
	    String iconFileName, 
	    LanguagePropertyType tooltip, 
	    String cssClass,
	    boolean disabled) {
	PropertiesManager props = PropertiesManager.getPropertiesManager();
	String imagePath = "file:" + PATH_ICONS + iconFileName;
	Image buttonImage = new Image(imagePath);
	Button button = new Button();
	button.getStyleClass().add(cssClass);
	button.setDisable(disabled);
	button.setGraphic(new ImageView(buttonImage));
	Tooltip buttonTooltip = new Tooltip(props.getProperty(tooltip.toString()));
	button.setTooltip(buttonTooltip);
	toolbar.getChildren().add(button);
	return button;
    }
    
    public void updateSlideshowEditToolbarControls() {
	// AND THE SLIDESHOW EDIT TOOLBAR
	addSlideButton.setDisable(false);
	boolean slideSelected = slideShow.isSlideSelected();
	removeSlideButton.setDisable(!slideSelected);
	moveSlideUpButton.setDisable(!slideSelected);
	moveSlideDownButton.setDisable(!slideSelected);	
    }

    /**
     * Uses the slide show data to reload all the components for
     * slide editing.
     * 
     * @param slideShowToLoad SLide show being reloaded.
     */
    public void reloadSlideShowPane() {
	slidesEditorPane.getChildren().clear();
	reloadTitleControls();
	for (Slide slide : slideShow.getSlides()) {
	    SlideEditView slideEditor = new SlideEditView(slide);
	    if (slideShow.isSelectedSlide(slide))
            { 
                slideEditor.getStyleClass().add(CSS_CLASS_SELECTED_SLIDE_EDIT_VIEW);
            }
	    else
		slideEditor.getStyleClass().add(CSS_CLASS_SLIDE_EDIT_VIEW);
                slidesEditorPane.getChildren().add(slideEditor);
                slideEditor.setOnMousePressed(e -> {
		slideShow.setSelectedSlide(slide);
		this.reloadSlideShowPane();
	    });
	}
	updateSlideshowEditToolbarControls();
    }
    
    private void initTitleControls() {
	PropertiesManager props = PropertiesManager.getPropertiesManager();
	String labelPrompt = props.getProperty(LABEL_SLIDESHOW_TITLE);
	titlePane = new FlowPane();
        titlePane.getStyleClass().add(CSS_CLASS_TITLE);
	titleLabel = new Label(labelPrompt);
	titleTextField = new TextField();
	
	titlePane.getChildren().add(titleLabel);
	titlePane.getChildren().add(titleTextField);
	
	String titlePrompt = props.getProperty(LanguagePropertyType.LABEL_SLIDESHOW_TITLE);
	titleTextField.setText(titlePrompt);
	
	titleTextField.textProperty().addListener(e -> {
	    slideShow.setTitle(titleTextField.getText());
	});
    }
    
    public void reloadTitleControls() {
	slidesEditorPane.getChildren().add(titlePane);
	titleTextField.setText(slideShow.getTitle());
    }
}
