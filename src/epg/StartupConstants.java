package epg;
/**
 * This class provides setup constants for initializing the application
 * that are NOT language dependent.
 * 
 * @author Marisa DePasquale
 */
public class StartupConstants {
    public static String ENGLISH_LANG = "English";

    // WE'LL LOAD ALL THE UI AND LANGUAGE PROPERTIES FROM FILES,
    // BUT WE'LL NEED THESE VALUES TO START THE PROCESS

    public static String PROPERTY_TYPES_LIST = "property_types.txt";
    public static String UI_PROPERTIES_FILE_NAME_English = "properties_EN.xml";
    public static String PROPERTIES_SCHEMA_FILE_NAME = "properties_schema.xsd";
    public static String PATH_DATA = "./data/";
    public static String PATH_SLIDE_SHOWS = PATH_DATA + "slide_shows/";
    public static String PATH_IMAGES = "./images/";
    public static String PATH_ICONS = "./icons/";
    public static String PATH_SLIDE_SHOW_IMAGES = PATH_IMAGES + "slide_show_images/";
    public static String PATH_CSS = "epg/style/";
    public static String STYLE_SHEET_UI = PATH_CSS + "ePortfolioGeneratorStyle.css";

    // HERE ARE THE LANGUAGE INDEPENDENT GUI ICONS/BUTTONS ---------------------
    //file toolbar buttons
    public static String ICON_WINDOW_LOGO = "Logo.png";
    public static String ICON_NEW_EPG = "New.png";
    public static String ICON_LOAD_EPG = "Load.png";
    public static String ICON_SAVE_EPG = "Save.png";
    public static String ICON_SAVEAS_EPG = "SaveAs.png";
    public static String ICON_EXPORT_EPG = "Export.png";
    public static String ICON_EXIT_EPG = "Exit.png";
    
    //comp toolbox buttons
    public static String ICON_ADD_TEXT = "AddText.png";
    public static String ICON_ADD_IMAGE = "AddImage.png";
    public static String ICON_ADD_VIDEO = "AddVideo.png";
    public static String ICON_ADD_SLIDESHOW = "AddSlideShow.png";
    public static String ICON_REMOVE_COMP = "Remove.png";
    
    // site toolbar buttons   
    public static String ICON_ADD_PAGE = "AddPage.png";
    public static String ICON_ENABLE_PAGE = "EnablePage.png";
    public static String ICON_DISABLE_PAGE = "DisablePage.png";
    public static String ICON_DELETE_PAGE = "DeletePage.png";
    
    //workspace mode buttons
    public static String ICON_PAGE_EDITOR = "PageEditor.png";
    public static String ICON_SITE_VIEWER = "SiteViewer.png";
    
    //page editor workspace button
    public static String ICON_EDIT_COMP = "EditComp.png";
    
    //ss related buttons
    public static String ICON_PREVIOUS = "Previous.png";
    public static String ICON_NEXT = "Next.png";
    
    //text dialogue buttons
    
    public static String ICON_PARAGRAPH = "Paragraph.png";
    public static String ICON_HEADER = "Header.png";
    public static String ICON_LIST = "List.png";
    
    // UI SETTINGS
    public static String    DEFAULT_SLIDE_IMAGE = "DefaultStartSlide.png";
    public static int	    DEFAULT_THUMBNAIL_WIDTH = 200;
    public static int	    DEFAULT_SLIDE_SHOW_HEIGHT = 500;
    
    // CSS STYLE SHEET CLASSES ------------------------------------------------
    
    // edit toolbars and buttons
    public static String    CSS_CLASS_FILE_TOOLBAR = "file_toolbar";
    public static String    CSS_CLASS_FILE_TOOLBAR_BUTTON = "file_toolbar_button";
    public static String    CSS_CLASS_FILE_TOOLBAR_EXIT_BUTTON = "file_toolbar_exit_button";
    public static String    CSS_CLASS_WORKSPACE_MODE_TOOLBAR = "workspace_mode_toolbar";
    public static String    CSS_CLASS_WORKSPACE_MODE_TOOLBAR_BUTTON = "workspace_mode_toolbar_button";
    public static String    CSS_CLASS_NAME = "name";
    public static String    CSS_CLASS_FOOTER = "footer";
    public static String    CSS_CLASS_COMP_TOOLBOX = "comp_toolbox";
    public static String    CSS_CLASS_COMP_TOOLBOX_BUTTON = "comp_toolbox_button";
    public static String    CSS_CLASS_COMP = "comp";
    public static String    CSS_CLASS_SELECTED_COMP = "selected_comp";
    public static String    CSS_CLASS_SITE_TOOLBAR = "site_toolbar";
    public static String    CSS_CLASS_SITE_TOOLBAR_BUTTON = "site_toolbar_button";
    public static String    CSS_CLASS_THEMES_TOOLBOX = "themes_toolbox";
    public static String    CSS_CLASS_PAGE_EDITOR = "page_editor";
    public static String    CSS_CLASS_WORKSPACE = "workspace";
    public static String    CSS_CLASS_COMP_TITLE = "comp_title";
    public static String    CSS_CLASS_THEME_TITLE = "theme_title";
    public static String    CSS_CLASS_SITE_TITLE = "site_title";
    public static String    CSS_CLASS_PAGE_TITLE = "page_title";
    public static String    CSS_CLASS_LIST_PAGES = "list_pages";
    public static String    CSS_CLASS_LIST_PAGES_SCROLL = "list_pages_scroll";
    
    // edit component dialogue boxes
    public static String    CSS_CLASS_BANNER_DIALOGUE = "banner_dialogue";
    public static String    CSS_CLASS_IMAGE_DIALOGUE = "image_dialogue";
    public static String    CSS_CLASS_TEXT_DIALOGUE = "text_dialogue";
    public static String    CSS_CLASS_TEXT_DIALOGUE_HBOX = "text_dialogue_hbox";
    public static String    CSS_CLASS_TEXT_DIALOGUE_BUTTON = "text_dialogue_button";
    public static String    CSS_CLASS_VIDEO_DIALOGUE = "video_dialogue";
    public static String    CSS_CLASS_SS_DIALOGUE = "SS_dialogue";
    public static String    CSS_CLASS_DELETE_PAGE_DIALOGUE = "delete_page_dialogue";
    public static String    CSS_CLASS_DELETE_COMP = "delete_comp_dialogue";
    
    
    
    // UI LABELS ---------------------------------------------------------------
    public static String    LABEL_NAME = "name_label";
    public static String    LABEL_FOOTER = "footer_label";
    public static String    OK_BUTTON_TEXT = "OK";
    
    
    // APPLICATION TOOLTIPS FOR BUTTONS ---------------------------------------
    //file toolbar tooltips
    public static String TOOLTIP_NEW_EPG = "Create a new ePortfolio";
    public static String TOOLTIP_LOAD_EPG = "Open an existing ePortfolio";
    public static String TOOLTIP_SAVE_EPG = "Overwrite current ePortfolio";
    public static String TOOLTIP_SAVEAS_EPG = "Save this ePortfolio as a new file";
    public static String TOOLTIP_EXPORT_EPG = "Export this ePortfolio";
    public static String TOOLTIP_EXIT_EPG = "Exit ePortfolio Generator";
    
    //Workspace Mode Toolbar tooltips
    public static String TOOLTIP_PAGE_EDITOR = "Edit pages for your ePortfolio";
    public static String TOOLTIP_SITE_VIEWER = "Loads most recently edited page for viewing";

    //Site Toolbar tooltips
    public static String TOOLTIP_ADD_PAGE = "Add a new page to your ePortfolio";
    public static String TOOLTIP_DELETE_PAGE = "Delete the selected page from your ePortfolio";
    public static String TOOLTIP_ENABLE_PAGE = "Enable as a navigation link";
    public static String TOOLTIP_DISABLE_PAGE = "Disable as a navigation link";
    public static String TOOLTIP_SELECT_PAGE = "Select current page to edit";
    
    //Comp toolbar tooltips
    public static String TOOLTIP_ADD_IMAGE_COMP = "Add an image component to the current page";
    public static String TOOLTIP_ADD_TEXT_COMP = "Add a text component to the current page";
    public static String TOOLTIP_ADD_VIDEO_COMP = "Add a video component to the current page";
    public static String TOOLTIP_ADD_SS_COMP = "Add a slide show component to the current page";
    
    
    //Page Editor Workspace tooltips (comp tooltips)
    public static String TOOLTIP_DELETE_BANNER_COMP = "Delete this banner image component from the current page";
    public static String TOOLTIP_DELETE_IMAGE_COMP = "Delete this image component from the current page";
    public static String TOOLTIP_DELETE_TEXT_COMP = "Delete this text component from the current page";
    public static String TOOLTIP_DELETE_VIDEO_COMP = "Delete this video component from the current page";
    public static String TOOLTIP_DELETE_SS_COMP = "Delete this slide show component from the current page";
   
    //Text dialogue tooltips
    
    public static String TOOLTIP_PARAGRAPH = "Create a paragraph text component";
    public static String TOOLTIP_HEADER = "Create a header text component";
    public static String TOOLTIP_LIST = "Create a list text component";
}
