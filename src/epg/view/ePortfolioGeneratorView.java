package epg.view;

import static epg.StartupConstants.CSS_CLASS_COMP_TITLE;
import static epg.StartupConstants.CSS_CLASS_COMP_TOOLBOX;
import static epg.StartupConstants.CSS_CLASS_COMP_TOOLBOX_BUTTON;
import static epg.StartupConstants.CSS_CLASS_COMP_TOOLS;
import static epg.StartupConstants.CSS_CLASS_FILE_TOOLBAR;
import static epg.StartupConstants.CSS_CLASS_FILE_TOOLBAR_BUTTON;
import static epg.StartupConstants.CSS_CLASS_FILE_TOOLBAR_EXIT_BUTTON;
import static epg.StartupConstants.CSS_CLASS_LIST_PAGES;
import static epg.StartupConstants.CSS_CLASS_LIST_PAGES_SCROLL;
import static epg.StartupConstants.CSS_CLASS_NAME;
import static epg.StartupConstants.CSS_CLASS_PAGE_EDITOR;
import static epg.StartupConstants.CSS_CLASS_PAGE_TITLE;
import static epg.StartupConstants.CSS_CLASS_SITE_TITLE;
import static epg.StartupConstants.CSS_CLASS_SITE_TOOLBAR;
import static epg.StartupConstants.CSS_CLASS_SITE_TOOLBAR_BUTTON;
import static epg.StartupConstants.CSS_CLASS_THEMES_TOOLBOX;
import static epg.StartupConstants.CSS_CLASS_THEME_TITLE;
import static epg.StartupConstants.CSS_CLASS_WORKSPACE;
import static epg.StartupConstants.CSS_CLASS_WORKSPACE_MODE_TOOLBAR;
import static epg.StartupConstants.CSS_CLASS_WORKSPACE_MODE_TOOLBAR_BUTTON;
import static epg.StartupConstants.ICON_ADD_IMAGE;
import static epg.StartupConstants.ICON_ADD_PAGE;
import static epg.StartupConstants.ICON_ADD_SLIDESHOW;
import javafx.geometry.Rectangle2D;
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
import static epg.StartupConstants.ICON_DELETE_PAGE;
import static epg.StartupConstants.ICON_EDIT_COMP;
import static epg.StartupConstants.ICON_ENABLE_PAGE;
import static epg.StartupConstants.PATH_ICONS;
import static epg.StartupConstants.ICON_WINDOW_LOGO;
import static epg.StartupConstants.ICON_NEW_EPG;
import static epg.StartupConstants.ICON_SAVE_EPG;
import static epg.StartupConstants.ICON_LOAD_EPG;
import static epg.StartupConstants.ICON_EXPORT_EPG;
import static epg.StartupConstants.ICON_EXIT_EPG;
import static epg.StartupConstants.ICON_PAGE_EDITOR;
import static epg.StartupConstants.ICON_REMOVE_COMP2;
import static epg.StartupConstants.ICON_SAVEAS_EPG;
import static epg.StartupConstants.ICON_SELECT_IMAGE;
import static epg.StartupConstants.ICON_SITE_VIEWER;
import static epg.StartupConstants.STYLE_SHEET_UI;
import static epg.StartupConstants.TOOLTIP_ADD_BANNER_IMAGE;
import static epg.StartupConstants.TOOLTIP_ADD_IMAGE_COMP;
import static epg.StartupConstants.TOOLTIP_ADD_PAGE;
import static epg.StartupConstants.TOOLTIP_ADD_SS_COMP;
import static epg.StartupConstants.TOOLTIP_ADD_TEXT_COMP;
import static epg.StartupConstants.TOOLTIP_ADD_VIDEO_COMP;
import static epg.StartupConstants.TOOLTIP_DELETE_PAGE;
import static epg.StartupConstants.TOOLTIP_EDIT_COMP;
import static epg.StartupConstants.TOOLTIP_ENABLE_PAGE;
import static epg.StartupConstants.TOOLTIP_EXIT_EPG;
import static epg.StartupConstants.TOOLTIP_EXPORT_EPG;
import static epg.StartupConstants.TOOLTIP_LOAD_EPG;
import static epg.StartupConstants.TOOLTIP_NEW_EPG;
import static epg.StartupConstants.TOOLTIP_PAGE_EDITOR;
import static epg.StartupConstants.TOOLTIP_REMOVE_COMP;
import static epg.StartupConstants.TOOLTIP_SAVEAS_EPG;
import static epg.StartupConstants.TOOLTIP_SAVE_EPG;
import static epg.StartupConstants.TOOLTIP_SITE_VIEWER;
import epg.controller.CompController;
import epg.controller.FileController;
import epg.controller.SiteController;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.text.TextAlignment;


import properties_manager.PropertiesManager;


/**
 * This class provides the User Interface for this application,
 * providing controls and the entry points for creating, loading, 
 * saving, editing, and viewing slide shows.
 * 
 * @author McKilla Gorilla & Marisa DePasquale
 */
public class ePortfolioGeneratorView {

    //The main application UI 
    Stage primaryStage;
    Scene primaryScene;

    //Organizes the big picture
    BorderPane epgPane;
    FlowPane topBars;
    FlowPane leftBars;
    FlowPane rightBars;

    //File Toolbar and its controls
    FlowPane fileToolbarPane;
    Button newEPGButton;
    Button loadEPGButton;
    Button saveEPGButton;
    Button saveAsEPGButton;
    Button exportEPGButton;
    Button exitEPGButton;
    
    //Workspace
    TabPane workspace;
    
    //Workspace Mode Toolbar   
    FlowPane workspaceModeToolbar;
    Button siteViewerButton;
    Button pageEditButton;

    //Comp Toolbox section
    FlowPane compTitle = new FlowPane();
    FlowPane compToolsTitle = new FlowPane();
    FlowPane compToolbox;
    FlowPane compTools;
    Button removeCompButton;
    Button editCompButton;
    Button addImageButton;
    Button addTextButton;
    Button addVideoButton;
    Button addSSButton;
    
    //Name stuff
    FlowPane nameFlowPane;
    Label name;
    TextField inputName;
    
    //Themes toolbox
    FlowPane themeToolbox;
    FlowPane themeTitle;
    Label layouts;
    Label fonts;
    Label colors;
    ComboBox layoutChoice;
    ComboBox fontChoice;
    ComboBox colorChoice;
    
    //Basic title
    FlowPane basicsTitle;
    Label basic;
    
    //Footer stuff
    FlowPane footerFlowPane;
    Label footer;
    TextField inputFooter;
    
    
    //Banner image stuff
    FlowPane bannerFlowPane;
    Label bannerImage;
    Button openBanner;
    
    
    //Site Toolbar
    ScrollPane listPageScroll;
    FlowPane siteToolbar;
    FlowPane siteTitle;
    FlowPane pageTitleFlowPane;
    VBox listPages;
    Label pageTitle;
    TextField inputPageTitle;
    Button addPageButton;
    Button deletePageButton;
    Button enablePageButton;
    
    //Controllers
    
    FileController fileController = new FileController(this);
    CompController compController = new CompController(this);
    SiteController siteController = new SiteController(this);
    
    public TabPane getWorkSpace(){
        return workspace;
    }
    
    // FOR THE SLIDE SHOW TITLE
    // FlowPane titlePane;
    // Label titleLabel;
    // TextField titleTextField;
    

    // THIS IS THE SLIDE SHOW WE'RE WORKING WITH
    // SlideShowModel slideShow;

    // THIS IS FOR SAVING AND LOADING SLIDE SHOWS
    // SlideShowFileManager fileManager;

    // THIS CLASS WILL HANDLE ALL ERRORS FOR THIS PROGRAM
    // private ErrorHandler errorHandler;

    // THIS CONTROLLER WILL ROUTE THE PROPER RESPONSES
    // ASSOCIATED WITH THE FILE TOOLBAR
    // private FileController fileController;
    
    // THIS CONTROLLER RESPONDS TO SLIDE SHOW EDIT BUTTONS
    // private SlideShowEditController editController;

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
    
   
    * /**
     * Initializes the UI controls and gets it rolling.
     * 
     * @param initPrimaryStage The window for this application.
     * 
     * @param windowTitle The title for this window.
     */
    public void startUI(Stage initPrimaryStage, String windowTitle) {
	// THE TOOLBAR ALONG THE NORTH
	initFileToolbar();

        //THE TOOLBAR UNDER THE FILE TOOLBAR
        initWorkSpaceToolbar();
        
        // Add the tabs for the pages
        initWorkSpace();
        
        // INIT THE CENTER WORKSPACE CONTROLS BUT DON'T ADD THEM
	// TO THE WINDOW YET
	initCompToolbox();

        //init name stuff
        initName();
        
        //init footer stuff
        initFooter();
       
        //init banner stuff
        initBannerImage();
        
        //init the selections for the different templates
        initThemeToolbox();
              
        
        //init Site Toolbox
        initSiteToolbar();
        
	// Setup Event Handlers
	initEventHandlers();

	// Start up the window 
	primaryStage = initPrimaryStage;
	initWindow(windowTitle);
        
        primaryStage = initPrimaryStage;  
        String windowImagePath = "file:" + PATH_ICONS + ICON_WINDOW_LOGO;
        Image windowImage = new Image(windowImagePath); 
        primaryStage.getIcons().add((windowImage));  
	initWindow(windowTitle);
    }

    // UI SETUP HELPER METHODS
  

    private void initEventHandlers() {
	/*// FIRST THE FILE CONTROLS
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
    */
    siteViewerButton.setOnAction(e -> {
        try {
            fileController.handleSiteViewRequest();
            siteViewerButton.setDisable(true);
            pageEditButton.setDisable(false);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ePortfolioGeneratorView.class.getName()).log(Level.SEVERE, null, ex);
        }
        });
    
     pageEditButton.setOnAction(e -> {
           // fileController.handleSiteViewRequest();
            siteViewerButton.setDisable(false);
            pageEditButton.setDisable(true);
       
        });   
     
     addPageButton.setOnAction(e -> {
        try {    
            listPages.getChildren().add(siteController.handleAddPageRequest());
        } catch (MalformedURLException ex) {
            Logger.getLogger(ePortfolioGeneratorView.class.getName()).log(Level.SEVERE, null, ex);
        }
        });      
     
     addImageButton.setOnAction(e -> {
        try {
            compController.handleAddImageRequest();
        } catch (MalformedURLException ex) {
            Logger.getLogger(ePortfolioGeneratorView.class.getName()).log(Level.SEVERE, null, ex);
        }
        });  
     
     addTextButton.setOnAction(e -> {
        try {
            compController.handleAddTextRequest();
        } catch (MalformedURLException ex) {
            Logger.getLogger(ePortfolioGeneratorView.class.getName()).log(Level.SEVERE, null, ex);
        }
        });
      
     addVideoButton.setOnAction(e -> {
        try {
            compController.handleAddVideoRequest();
        } catch (MalformedURLException ex) {
            Logger.getLogger(ePortfolioGeneratorView.class.getName()).log(Level.SEVERE, null, ex);
        }
        });
     
    addSSButton.setOnAction(e -> {
        try {
            compController.handleSlideShowRequest();
        } catch (MalformedURLException ex) {
            Logger.getLogger(ePortfolioGeneratorView.class.getName()).log(Level.SEVERE, null, ex);
        }
        }); 
     
    }

    /*
     * This function initializes all the buttons in the toolbar at the top of
     * the application window. These are related to file management.
     */
    
    
    private void initFileToolbar() {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
	fileToolbarPane = new FlowPane();
        fileToolbarPane.setPrefWrapLength(1280);
        fileToolbarPane.getStyleClass().add(CSS_CLASS_FILE_TOOLBAR);
        
	PropertiesManager props = PropertiesManager.getPropertiesManager();
	newEPGButton = initChildButton(fileToolbarPane, ICON_NEW_EPG,	TOOLTIP_NEW_EPG,	    CSS_CLASS_FILE_TOOLBAR_BUTTON, false);
	loadEPGButton = initChildButton(fileToolbarPane, ICON_LOAD_EPG,	TOOLTIP_LOAD_EPG,    CSS_CLASS_FILE_TOOLBAR_BUTTON, false);
	saveEPGButton = initChildButton(fileToolbarPane, ICON_SAVE_EPG,	TOOLTIP_SAVE_EPG,    CSS_CLASS_FILE_TOOLBAR_BUTTON, true);
        saveAsEPGButton = initChildButton(fileToolbarPane, ICON_SAVEAS_EPG,	TOOLTIP_SAVEAS_EPG,    CSS_CLASS_FILE_TOOLBAR_BUTTON, true);
	exportEPGButton = initChildButton(fileToolbarPane, ICON_EXPORT_EPG,	TOOLTIP_EXPORT_EPG,   CSS_CLASS_FILE_TOOLBAR_BUTTON, true);
	exitEPGButton = initChildButton(fileToolbarPane, ICON_EXIT_EPG, TOOLTIP_EXIT_EPG, CSS_CLASS_FILE_TOOLBAR_EXIT_BUTTON, false);
    }
    
    
    
    
    private void initWorkSpaceToolbar() {   
	workspaceModeToolbar = new FlowPane();  
        workspaceModeToolbar.getStyleClass().add(CSS_CLASS_WORKSPACE_MODE_TOOLBAR);
        workspaceModeToolbar.setPrefWidth(1280);
        // HERE ARE OUR FILE TOOLBAR BUTTONS, NOTE THAT SOME WILL
	// START AS ENABLED (false), WHILE OTHERS DISABLED (true)
	PropertiesManager props = PropertiesManager.getPropertiesManager();
	pageEditButton = initChildButton(workspaceModeToolbar, ICON_PAGE_EDITOR,	TOOLTIP_PAGE_EDITOR,	    CSS_CLASS_WORKSPACE_MODE_TOOLBAR_BUTTON, true);
	siteViewerButton = initChildButton(workspaceModeToolbar, ICON_SITE_VIEWER,	TOOLTIP_SITE_VIEWER,    CSS_CLASS_WORKSPACE_MODE_TOOLBAR_BUTTON, false);
    }
    
    private void initSiteToolbar() {
        
        //add page title
        pageTitleFlowPane = new FlowPane();
        pageTitleFlowPane.setPrefWrapLength(180);
        pageTitleFlowPane.getStyleClass().add(CSS_CLASS_PAGE_TITLE);
        pageTitle = new Label("Title:");
        pageTitleFlowPane.getChildren().add(pageTitle);
        inputPageTitle = new TextField();
        inputPageTitle.setMaxWidth(120);
        inputPageTitle.setPromptText("Page 1");
        pageTitleFlowPane.getChildren().add(inputPageTitle);
        
        
        //addd Site title and buttons
	siteToolbar = new FlowPane();  
        siteToolbar.setPrefWrapLength(180);
        siteToolbar.getStyleClass().add(CSS_CLASS_SITE_TOOLBAR);
        
        Label site = new Label("Site Toolbar");
        siteTitle = new FlowPane();
        siteTitle.setPrefWrapLength(180);
        siteTitle.getStyleClass().add(CSS_CLASS_SITE_TITLE);
        siteTitle.getChildren().add(site);
        
	PropertiesManager props = PropertiesManager.getPropertiesManager();
	addPageButton = initChildButton(siteToolbar, ICON_ADD_PAGE,	TOOLTIP_ADD_PAGE,	    CSS_CLASS_SITE_TOOLBAR_BUTTON, false);
        deletePageButton = initChildButton(siteToolbar, ICON_DELETE_PAGE,	TOOLTIP_DELETE_PAGE,	    CSS_CLASS_SITE_TOOLBAR_BUTTON, false);
        enablePageButton = initChildButton(siteToolbar, ICON_ENABLE_PAGE,	TOOLTIP_ENABLE_PAGE,	    CSS_CLASS_SITE_TOOLBAR_BUTTON, false);
        
        
        listPageScroll = new ScrollPane();
        listPageScroll.setMaxWidth(180);
        listPageScroll.setMaxHeight(482);
        listPageScroll.getStyleClass().add(CSS_CLASS_LIST_PAGES_SCROLL);
        
        //add list of pages
        listPages = new VBox(1);
        listPages.getStyleClass().add(CSS_CLASS_LIST_PAGES);
        listPages.getChildren().add(new Label("Page 1"));
        
        listPageScroll.setContent(listPages);
    
    } 
        
    
    private void initWorkSpace() {
           
	workspace = new TabPane();  
        workspace.getStyleClass().add(CSS_CLASS_WORKSPACE);

      //  workspace.setMaxHeight(500);
        workspace.setPrefWidth(900);

	Tab tab = new Tab();
            tab.setText("Page 1");
            HBox hbox = new HBox();
            hbox.getChildren().add(new Label("Page 1"));
            hbox.setAlignment(Pos.CENTER); 
            tab.setContent(hbox);
            workspace.getTabs().add(tab);
    } 
    

    
    private void initName() {   
        
        basicsTitle = new FlowPane();
        basicsTitle.setPrefWrapLength(200);
	basicsTitle.getStyleClass().add(CSS_CLASS_COMP_TITLE);
        
        basic = new Label("Basics ");
        basicsTitle.getChildren().add(basic);
              
	nameFlowPane = new FlowPane();
        nameFlowPane.setPrefWrapLength(200);
	nameFlowPane.getStyleClass().add(CSS_CLASS_NAME);
        
        name = new Label("Name: ");
        nameFlowPane.getChildren().add(name);
        
        inputName = new TextField();
        inputName.setPromptText("Pam Beesly");
        inputName.setMaxWidth(130);
        nameFlowPane.getChildren().add(inputName);
    }
          
    private void initThemeToolbox() {
        
	themeToolbox = new FlowPane();
        themeToolbox.setPrefWrapLength(200);
        themeToolbox.setVgap(5);
        themeToolbox.setPrefHeight(150);
        themeToolbox.getStyleClass().add(CSS_CLASS_THEMES_TOOLBOX);
        
        themeTitle = new FlowPane();
        themeTitle.setPrefWrapLength(200);
        themeTitle.getStyleClass().add(CSS_CLASS_THEME_TITLE);
        Label theme = new Label("Templates");
        themeTitle.getChildren().add(theme);
        
        PropertiesManager props = PropertiesManager.getPropertiesManager();
  
        ObservableList<String> layoutOptions = FXCollections.observableArrayList(
        "Right Navigation",
        "Left Navigation",
        "Above Banner Nav",
        "Below Banner Nav",
        "Below Name Nav"
      );
        
        ObservableList<String> colorOptions = FXCollections.observableArrayList(
        "I'm a Seawolf!",
        "l33t hax0r",
        "Pretty Purple",
        "Forest",
        "Facebook"
      );
        
        ObservableList<String> fontOptions = FXCollections.observableArrayList(
        "Really awesome font",
        "REALLY awesome font",
        "Way 2 awesome",
        "It's so awesome",
        "Mediocre font"
      );
        
        layoutChoice = new ComboBox(layoutOptions);
        layoutChoice.setValue("Right Navigation");
        
        colorChoice = new ComboBox(colorOptions);
        colorChoice.setValue("I'm a Seawolf!");
        
        fontChoice = new ComboBox(fontOptions);
        fontChoice.setValue("Really awesome font");
        
        layouts = new Label("Layout:\n");
        colors = new Label("Color:  \n");
        fonts = new Label("Font:   \n");
        
        themeToolbox.getChildren().add(layouts);
        themeToolbox.getChildren().add(layoutChoice);
        themeToolbox.getChildren().add(colors);
        themeToolbox.getChildren().add(colorChoice);
        themeToolbox.getChildren().add(fonts);
        themeToolbox.getChildren().add(fontChoice);	
    }
    
      private void initCompToolbox() {     
	compToolbox = new FlowPane();
        compToolbox.setPrefWrapLength(200);
        compToolbox.setPrefHeight(100);
	compToolbox.getStyleClass().add(CSS_CLASS_COMP_TOOLBOX);
        
        compTitle.setPrefWrapLength(200);
	compTitle.getStyleClass().add(CSS_CLASS_COMP_TITLE);
        
        Label addComp = new Label("Components");
        addComp.setAlignment(Pos.CENTER);
        compTitle.getChildren().add(addComp);
        
	addImageButton = this.initChildButton(compToolbox,ICON_ADD_IMAGE, TOOLTIP_ADD_IMAGE_COMP,  CSS_CLASS_COMP_TOOLBOX_BUTTON,  false);
	addTextButton = this.initChildButton(compToolbox,ICON_ADD_TEXT,	TOOLTIP_ADD_TEXT_COMP, CSS_CLASS_COMP_TOOLBOX_BUTTON,  false);
        addVideoButton = this.initChildButton(compToolbox,ICON_ADD_VIDEO,TOOLTIP_ADD_VIDEO_COMP, CSS_CLASS_COMP_TOOLBOX_BUTTON,  false);
        addSSButton = this.initChildButton(compToolbox,	ICON_ADD_SLIDESHOW, TOOLTIP_ADD_SS_COMP,  CSS_CLASS_COMP_TOOLBOX_BUTTON,  false);	
    
        compTools = new FlowPane();
        compTools.setPrefWrapLength(200);
        compTools.setPrefHeight(75);
        compTools.setHgap(10);
	compTools.getStyleClass().add(CSS_CLASS_COMP_TOOLS);
       
        compToolsTitle.setPrefWrapLength(200);
	compToolsTitle.getStyleClass().add(CSS_CLASS_COMP_TITLE);
        
        Label compToolsLabel = new Label("Component Tools");
        compToolsLabel.setAlignment(Pos.CENTER);
        compToolsTitle.getChildren().add(compToolsLabel);
        
        removeCompButton = this.initChildButton(compTools,ICON_REMOVE_COMP2, TOOLTIP_REMOVE_COMP,  CSS_CLASS_WORKSPACE_MODE_TOOLBAR_BUTTON,  false);
	editCompButton = this.initChildButton(compTools,ICON_EDIT_COMP,	TOOLTIP_EDIT_COMP, CSS_CLASS_WORKSPACE_MODE_TOOLBAR_BUTTON,  false);
      
      
      }

  private void initFooter() {     
	footerFlowPane = new FlowPane();
        footerFlowPane.setPrefWrapLength(200);
	footerFlowPane.getStyleClass().add(CSS_CLASS_NAME);
        
        footer = new Label("Footer:");
        footerFlowPane.getChildren().add(footer);
        
        inputFooter = new TextField();
        inputFooter.setPromptText("© The Office. Info from Wikipedia.");
        inputFooter.setMaxWidth(130);
        footerFlowPane.getChildren().add(inputFooter);
    }    

    private void initBannerImage() {     
	bannerFlowPane = new FlowPane();
        bannerFlowPane.setPrefWrapLength(200);
        bannerFlowPane.setPrefHeight(50);
	bannerFlowPane.getStyleClass().add(CSS_CLASS_NAME);
        
        bannerImage = new Label("Banner Image:");
        bannerFlowPane.getChildren().add(bannerImage);
        
        openBanner = this.initChildButton(bannerFlowPane,ICON_SELECT_IMAGE, TOOLTIP_ADD_BANNER_IMAGE,  CSS_CLASS_WORKSPACE_MODE_TOOLBAR_BUTTON,  false);	
         

    }
    
    
    private void initWindow(String windowTitle) {
	// set window title and resolutions
	primaryStage.setTitle(windowTitle);
	primaryStage.setWidth(1295);
	primaryStage.setHeight(720);

        // add flowpanes to sections of the boarderpane
	epgPane = new BorderPane();
        topBars = new FlowPane();
        
        leftBars = new FlowPane(Orientation.VERTICAL);
        leftBars.setPrefWrapLength(700);
        leftBars.setMaxWidth(200);
        
        rightBars = new FlowPane(Orientation.VERTICAL);
        rightBars.setPrefWrapLength(600);
        rightBars.setMaxWidth(180);
        
        
        topBars.getChildren().add(fileToolbarPane);
        topBars.getChildren().add(workspaceModeToolbar);
        
        leftBars.getChildren().add(basicsTitle);
        leftBars.getChildren().add(nameFlowPane);
        leftBars.getChildren().add(footerFlowPane);
        leftBars.getChildren().add(bannerFlowPane);
        leftBars.getChildren().add(themeTitle);
        leftBars.getChildren().add(themeToolbox);
        leftBars.getChildren().add(compTitle);
        leftBars.getChildren().add(compToolbox);
        leftBars.getChildren().add(compToolsTitle);
        leftBars.getChildren().add(compTools);
        
        rightBars.getChildren().add(pageTitleFlowPane);
        rightBars.getChildren().add(siteTitle);
        rightBars.getChildren().add(siteToolbar);  
        rightBars.getChildren().add(listPageScroll);
              
	epgPane.setTop(topBars);
        epgPane.setLeft(leftBars);    
        epgPane.setRight(rightBars);
        
        Group root = new Group();
        epgPane.setCenter(workspace);
        root.getChildren().add(epgPane); 
        
	primaryScene = new Scene(root);
	
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
	Tooltip buttonTooltip = new Tooltip(tooltip);
	button.setTooltip(buttonTooltip);
	toolbar.getChildren().add(button);
	return button;
    }
    
}
