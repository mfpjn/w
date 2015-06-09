$(document).ready(function() {

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