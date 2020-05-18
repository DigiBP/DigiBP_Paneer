const videoPlayer = document.querySelector("#player");
const canvasElement = document.querySelector("#canvas");
const captureButton = document.querySelector("#capture-btn");
const imagePicker = document.querySelector("#image-picker");
const imagePickerArea = document.querySelector("#pick-image");
const newImages = document.querySelector("#newImages");
var image2test = "";
var faceReturn = "";

// Image dimensions
const width = 320;
const height = 240;
let zIndex = 1;

const createImage = (src, alt, title, width, height, className) => {
  let newImg = document.createElement("img");

  if (src !== null) newImg.setAttribute("src", src);
  if (alt !== null) newImg.setAttribute("alt", alt);
  if (title !== null) newImg.setAttribute("title", title);
  if (width !== null) newImg.setAttribute("width", width);
  if (height !== null) newImg.setAttribute("height", height);
  if (className !== null) newImg.setAttribute("class", className);

  return newImg;
};

const startMedia = () => {
  if (!("mediaDevices" in navigator)) {
    navigator.mediaDevices = {};
  }

  if (!("getUserMedia" in navigator.mediaDevices)) {
    navigator.mediaDevices.getUserMedia = constraints => {
      const getUserMedia =
        navigator.webkitGetUserMedia || navigator.mozGetUserMedia;

      if (!getUserMedia) {
        return Promise.reject(new Error("getUserMedia is not supported"));
      } else {
        return new Promise((resolve, reject) =>
          getUserMedia.call(navigator, constraints, resolve, reject)
        );
      }
    };
  }

  navigator.mediaDevices
    .getUserMedia({ video: true })
    .then(stream => {
      videoPlayer.srcObject = stream;
      videoPlayer.style.display = "block";
    })
    .catch(err => {
      imagePickerArea.style.display = "block";
    });
};

// Capture the image, save it and then paste it to the DOM
captureButton.addEventListener("click", event => {
  // Draw the image from the video player on the canvas
  canvasElement.style.display = "block";
  const context = canvasElement.getContext("2d");
  context.drawImage(videoPlayer, 0, 0, canvas.width, canvas.height);

  videoPlayer.srcObject.getVideoTracks().forEach(track => {
    // track.stop();
  });

  // Convert the data so it can be saved as a file
  let picture = canvasElement.toDataURL();

  // Save the file by posting it to the server
  fetch("./api/save_image.php", {
    method: "post",
    body: JSON.stringify({ data: picture })
  })
    .then(res => res.json())
    .then(data => {
      if (data.success) {
        let newImage = createImage(
          data.path,
          "new image",
          "new image",
          width,
          height,
          "masked"
        );
        console.log(newImage);
        console.log(newImage.src);
        image2test = newImage;

      }
    })
    .catch(error => console.log(error));
    setTimeout(() => {  processImage(); }, 1000);
    get_customer();
});

window.addEventListener("load", event => startMedia());

function processImage() {
  url = './api/analyze.php?s=' + image2test.src
  console.log(url);
  
  fetch(url)
  .then(
    function(response) {
      if (response.status !== 200) {
        console.log('Looks like there was a problem. Status Code: ' +
          response.status);
          
          document.getElementById("personName").innerHTML = "Authorization failed, please try again.";
          document.getElementById("startbtn").remove();
        return;
      }

      // Examine the text in the response
      response.json().then(function(data) {
        console.log(data);
        //document.getElementById("personName").innerHTML = data;
        document.getElementById("personName").innerHTML = "";
        var btn = document.createElement("BUTTON");
        btn.innerHTML = "Start process as " + data; 
        btn.className = "btn btn-primary";
        btn.id = "startbtn";
        document.getElementById("personName").appendChild(btn); 

        var select = document.createElement('select');
        select.appendChild(new Option("my label 1", "my value 1"));
        select.appendChild(new Option("my label 2", "my value 2"));
        document.getElementById("personName").appendChild(select);



        startButton = document.querySelector("#startbtn");
        startButton.addEventListener("click", event => { 
        start_process();
        });
        //innerHTML += '<button class="btn btn-primary" id="start-btn">Start process</button>';
      });
    }
  )
  .catch(function(err) {
    console.log('Fetch Error :-S', err);
  });

};


function start_process() {
  url = './api/start_process.php?n=' + "Test2";
  console.log(url);
  
  fetch(url)
  .then(
    function(response) {
      if (response.status !== 200) {
        console.log('Looks like there was a problem. Status Code: ' +
          response.status);
          
        return;
      }

      // Examine the text in the response
      response.json().then(function(data) {
        console.log(data);
        window.setTimeout(function(){ window.location = "https://digibp-paneer.herokuapp.com/app/cockpit/default/#/process-definition/" + data["definitionId"]; },100);

      });
    }
  )
  .catch(function(err) {
    console.log('Fetch Error :-S', err);
  });

};


function get_customer() {
  url = 'https://hook.integromat.com/r6yggd8uf1lopbx67nafvamhjwnrsx4l';
  console.log(url);
  
  fetch(url)
  .then(
    function(response) {
      if (response.status !== 200) {
        console.log('Looks like there was a problem. Status Code: ' +
          response.status);
          
        return;
      }

      // Examine the text in the response
      response.json().then(function(data) {
        console.log(data);
        

      });
    }
  )
  .catch(function(err) {
    console.log('Fetch Error :-S', err);
  });

};


