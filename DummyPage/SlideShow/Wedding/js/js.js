/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * Marisa DePasquale
 */

 // Gets Captions from a JSON file.

  var captionSize = 0;
  var actualJSONfile = "title.json";
  $.getJSON(actualJSONfile, function(data) {
        var output="";
        for (var i in data.slides) {
            output=data.slides[0].caption + "<br><br>";
            captionSize++;
        }
        document.getElementById("captions").innerHTML=output;
  });
  
  // Gets image path and img name from JSON file.
  
    $.getJSON(actualJSONfile, function(data) {
        output = '<img src = img/' + data.slides[0].image_file_name + '>';   
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
            document.getElementById("whenClicked").src = "img/icons/Pause.png";
            myTimer = setInterval("handleNext()", 3000);
        }
        else
        {
            document.getElementById("whenClicked").src = "img/icons/Play.png";
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