/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


//Get HTML File name to indicate the page number - works!
$(document).ready(function() {

   var actualJSONfile = "test.json";
   var getHtmlFilePath;
   var htmlFile;
   getHTMLFilePath = document.location.pathname;
   var indexOfSlash;
   indexOfSlash = getHTMLFilePath.lastIndexOf('/');
   htmlFile = getHTMLFilePath.substr(indexOfSlash+1, getHTMLFilePath.length);
   htmlFile = htmlFile.substr(0,htmlFile.length-5);



 //Get the number of pages there are in an ePortfolio - works!

    var numOfPages = 0;
    $.getJSON(actualJSONfile, function(data) {    
            numOfPages = data.pages;       
    });
    
    
    
 //Set up the navigation - works!
    
    var link;
    var pageNum = parseInt(htmlFile) - 1;
    
    
    $.getJSON(actualJSONfile, function(data) {
        for (var i in data.site) 
        {
            if(data.site[i].page[0].enable)
            {
                page = parseInt(i)+1;
                link = '<a href ="'+page+'.html">' + data.site[i].page[0].pageTitle;
                document.getElementById("navbar").innerHTML += link;
            }
                
            
        }
            
         
    });
    
    //Set up the banner image - works!
    

    $.getJSON(actualJSONfile, function(data) {
        for (var i in data.site[pageNum].page) 
        {
            if(data.site[pageNum].page[i].type === "banner_image")
            {
                output = '<img src = "Images/'+ data.site[pageNum].page[i].image_file_name+
                        '">';
                document.getElementById("bannerimg").innerHTML += output;
            }
        }

    });
    
    
    //Display name in banner section - works!
    $.getJSON(actualJSONfile, function(data) {
          
        name = data.name;  
        document.getElementById("banner").innerHTML += "<h1>"+name+"</h1>";

    });
    
    //Display the footer
    $.getJSON(actualJSONfile, function(data) {
          
        footer = data.footer;  
        document.getElementById("footer").innerHTML += footer;

    });
    
    
    
 // Add all of the components
 
    var height = 0;
    var width = 0;
    var float;
    var margin = 0;
    var outputImage;
    var outputText;
    var outputVideo;
    $.getJSON(actualJSONfile, function(data) {
        for (var i in data.site[pageNum].page) 
        {
            //image component
            if(data.site[pageNum].page[i].type === "image")
            {
                height = data.site[pageNum].page[i].height;
                width = data.site[pageNum].page[i].width;
                float = data.site[pageNum].page[i].float;
                margin = data.site[pageNum].page[i].margin;

                outputImage = '<img style =  float:' + float + ';margin:' +margin+'px; src = "Images/'+ data.site[pageNum].page[i].image_file_name+ 
                        '" height ='+height+ 'width ='+width+'>';
                document.getElementById("content").innerHTML += outputImage;
                    
            }
            
            //p component
             if(data.site[pageNum].page[i].type === "p")
            {
                outputText = '<p>'+ data.site[pageNum].page[i].text+ '</p>';
                document.getElementById("content").innerHTML += outputText;
            }
            
            //h component
             if(data.site[pageNum].page[i].type === "h")
            {
                outputText = '<h2>'+ data.site[pageNum].page[i].text+ '</h2>';
                document.getElementById("content").innerHTML += outputText;
            }
            
            //li component
             if(data.site[pageNum].page[i].type === "li")
            {
                outputText = '<li>'+ data.site[pageNum].page[i].text+ '</li>';
                document.getElementById("content").innerHTML += outputText;
            }
            
             //inlined paragraph hyperlink component
             if(data.site[pageNum].page[i].type === "pa")
            {
                var text = data.site[pageNum].page[i].text;
                var link = data.site[pageNum].page[i].link;
                var startLink = data.site[pageNum].page[i].linkStart;
                var endLink = data.site[pageNum].page[i].linkEnd;
                var linkText = text.substring(startLink,endLink);
                var beforeText = text.substring(0,startLink);
                var afterText = text.substring(endLink, text.length);
                outputText = beforeText + '<a href ="'+link+ '">'+ linkText + '</a>' +afterText;
                document.getElementById("content").innerHTML += outputText;
            }
              //inlined header hyperlink component
             if(data.site[pageNum].page[i].type === "ha")
            {
                var text = data.site[pageNum].page[i].text;
                var link = data.site[pageNum].page[i].link;
                var startLink = data.site[pageNum].page[i].linkStart;
                var endLink = data.site[pageNum].page[i].linkEnd;
                var linkText = text.substring(startLink,endLink);
                var beforeText = text.substring(0,startLink);
                var afterText = text.substring(endLink, text.length);
                outputText = '<h2>' + beforeText + '<a href ="'+link+ '">'+ linkText + '</a>' +afterText + '</h2>';
                document.getElementById("content").innerHTML += outputText;
            } 
            
              //inlined list hyperlink component
             if(data.site[pageNum].page[i].type === "lia")
            {
                var text = data.site[pageNum].page[i].text;
                var link = data.site[pageNum].page[i].link;
                var startLink = data.site[pageNum].page[i].linkStart;
                var endLink = data.site[pageNum].page[i].linkEnd;
                var linkText = text.substring(startLink,endLink);
                var beforeText = text.substring(0,startLink);
                var afterText = text.substring(endLink, text.length);
                outputText = '<li>' + beforeText + '<a href ="'+link+ '">'+ linkText + '</a>' +afterText + '</li>';
                document.getElementById("content").innerHTML += outputText;
            }            
                
            
            //video component
            if(data.site[pageNum].page[i].type === "video")
            {
                height = data.site[pageNum].page[i].height;
                width = data.site[pageNum].page[i].width;
                outputVideo = '<video width="' + width + '"height ="' + height +'"controls> <source src = "Videos/'+ data.site[pageNum].page[i].video_file_name+'"type="video/mp4">';
                document.getElementById("content").innerHTML += outputVideo;
            }
            
            // DO SLIDESHOW COMP
             if(data.site[pageNum].page[i].type === "slideshow")
            {
                height = data.site[pageNum].page[i].height;
                width = data.site[pageNum].page[i].width;
                outputVideo = '<iframe src ="'+ data.site[pageNum].page[i].path + '" width="' + width + '" height ="' + height +'">';
                document.getElementById("content").innerHTML += outputVideo;
            }
        }
         
    });
    
   
  
  // Gets image path and img name from JSON file.
 /* 
    $.getJSON(actualJSONfile, function(data) {
        output = '<img src = img/' + data.page1[0].image_file_name + '>';   
        document.getElementById("imgs").innerHTML=output;
  });
  
    // Gets image path and img name from JSON file.
    $.getJSON(actualJSONfile, function(data) {
        var output="";
        for (var i in data.title) {
            output+= data.title[i];
        }
        document.getElementById("title").innerHTML=output;
  });
  
  
  // Set up the functions for the arrow buttons
    var nextbtn = document.images["nextbtn"];
    var prevbtn = document.images["previousbtn"];
    
    var count = 1;
    function handlePrev()
    {
        //Goes to previous image and caption
        $.getJSON(actualJSONfile, function(data) {
        output = data.slides[count].caption + "<br><br>";
        outputImg = '<img src = img/' + data.slides[count].image_file_name + '>';
        document.getElementById("captions").innerHTML=output;
        document.getElementById("imgs").innerHTML=outputImg;
         if(count <= 0)
        {
           count = captionSize-1;  
        }
        else
            count--;
        });
    }
    
    var playSlideShow = false;
    function handlePlay()
    {
        playSlideShow = !playSlideShow;
        if(playSlideShow)
        {
            document.getElementById("whenClicked").src = "../../images/icons/Pause.png";
            myTimer = setInterval("handleNext()", 3000);
        }
        else
        {
            document.getElementById("whenClicked").src = "../../images/icons/Play.png";
            clearInterval(myTimer);
        }
   
    }
    
    function handleNext()
    {
        // Goes to next image and caption
        $.getJSON(actualJSONfile, function(data) {
        output = data.slides[count].caption + "<br><br>";
        outputImg = '<img src = img/' + data.slides[count].image_file_name + '>';
        document.getElementById("captions").innerHTML=output;
        document.getElementById("imgs").innerHTML=outputImg;
        
        if(count === captionSize-1)
        {
           count = 0; 
        }
        else
           count++;
        });
    }
*/

});