$(document).ready(function() {

       $(".file-dropzone").on('dragover', handleDragEnter);
       $(".file-dropzone").on('dragleave', handleDragLeave);
       $(".file-dropzone").on('drop', handleDragLeave);

       function handleDragEnter(e) {

              this.classList.add('drag-over');
       }

       function handleDragLeave(e) {

              this.classList.remove('drag-over');
       }

       // "dropzoneForm" is the camel-case version of the form id "dropzone-form"
       Dropzone.options.dropzoneForm = {

              url : "upload", // not required if the <form> element has action attribute
              autoProcessQueue : false,
              uploadMultiple : true,
              maxFilesize : 256, // MB
              parallelUploads : 3,
              maxFiles : 3,
              addRemoveLinks : true,
              previewsContainer : ".dropzone-previews",

              // The setting up of the dropzone
              init : function() {

                     var myDropzone = this;

                     // first set autoProcessQueue = false
                     $('#next-button').on("click", function(e) {

                           myDropzone.processQueue();
                     });

                     // customizing the default progress bar
                     this.on("uploadprogress", function(file, progress) {

                           progress = parseFloat(progress).toFixed(0);

                           var progressBar = file.previewElement.getElementsByClassName("dz-upload")[0];
                           progressBar.innerHTML = progress + "%";
                     });

                     // displaying the uploaded files information in a Bootstrap dialog
//                     this.on("successmultiple", function(files, serverResponse) {
//                           showInformationDialog(files, serverResponse);
//                     });
                     
              }
       }

       
       function showInformationDialog(files, objectArray) {

              var responseContent = "";

              for (var i = 0; i < objectArray.length; i++) {

                     var infoObject = objectArray[i];

                     for ( var infoKey in infoObject) {
                           if (infoObject.hasOwnProperty(infoKey)) {
                             responseContent = responseContent + " " + infoKey + " -> " + infoObject[infoKey] + "<br>";
                           }
                     }
                     responseContent = responseContent + "<hr>";
              }

              // from the library bootstrap-dialog.min.js
              BootstrapDialog.show({
                     title : '<b>Server Response</b>',
                     message : responseContent
              });
       }

});