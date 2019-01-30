// Get the modal
var modal = document.getElementById('myModal');

// Get the link that opens the modal
var link = document.getElementById("upload");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks on the button, open the modal 
link.onclick = function() {
    modal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
    modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}

var uploadForm = document.querySelector('#upload-form');
var uploadInput = document.querySelector('#file');
uploadForm.addEventListener('submit', function(event){
    var files = uploadInput.files;
    //if no files selected
    if(files.length === 0) {
        alert("Please select a file.");
    }
    //upload the file
    uploadMovies(files[0]);
    event.preventDefault();
}, true);

//upload the movie data file
function uploadMovies(file) {
    // Bind the FormData object and the form element
    var formData = new FormData();
    formData.append("file", file);
    var http = new XMLHttpRequest();
    http.open("POST", "/uploadMovies");

    // The data sent is what the user provided in the form
    http.send(formData);

    http.onreadystatechange = function() {
        if(this.readyState == 4 && this.status == 200) {
            //successful response
            modal.style.display = "none";
            //display response text
            alert(http.responseText);
        }
        else if(this.readyState == 4 && this.status != 200) {
            alert("Upload failed. Please try again.");
        }
    }
}

//for submitted slider values as they are changed
function submitValues(ac, hs, twa, sf) {
    var http = new XMLHttpRequest();
    //the table of movies
    var table = document.getElementById("tbl-movies");
    http.onreadystatechange = function() {
        //if successful response and a list of movies have been retrieved, update  the movie table to show the movies
        if(this.readyState == 4 && this.status == 200) {
            var movieArr = JSON.parse(this.responseText);
            //loop through the rows in the movie table
            for (var i = 0, row; row = table.rows[i]; i++) {
               if (i == 0) {
                    //loop through the cells of the images row (first row) and add the images
                   for (var j = 0; j < row.cells.length; j++) {
                        row.cells[j].innerHTML = '<img src="' + movieArr[j].path + '">';
                   }
               }
               else if (i == 1) {
                    //loop through the cells of the movie names row (second row) and add the movie names + certification
                   for (var j = 0; j < row.cells.length; j++) {
                        row.cells[j].innerHTML = movieArr[j].name + "<br>Cert: " + movieArr[j].rating;
                   }
               }
            }
        }
        // if unsuccessful response
        else if(this.readyState == 4 && this.status != 200) {
            for (i = 0; i < sliders.length; i++) {
            //rest the sliders
                sliders[i].value = 50;
            }
            alert("Please upload movies first.");
        }
    }
    http.open("GET", "/getMovies?acValue=" + ac + "&hsValue=" + hs + "&twaValue=" + twa + "&sfValue=" + sf, true);
    http.send(null);
}

var sliders = document.getElementsByClassName("slider");
var acSlider = document.getElementById("acS");
var hsSlider = document.getElementById("hsS");
var twaSlider = document.getElementById("twaS");
var sfSlider = document.getElementById("sfS");
var ac = acSlider.value;
var hs = hsSlider.value;
var twa = twaSlider.value;
var sf = sfSlider.value;

//update and submit slider values everytime they are changed by the user.
for (i = 0; i < sliders.length; i++) {
    sliders[i].onmouseup = function () {
      ac = acSlider.value;
      hs = hsSlider.value;
      twa = twaSlider.value;
      sf = sfSlider.value;
      submitValues(ac, hs, twa, sf);
    }
}