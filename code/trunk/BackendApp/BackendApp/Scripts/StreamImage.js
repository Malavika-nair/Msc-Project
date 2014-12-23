$(document).ready(function () {
    var count = 0;
    $('#start').attr("disabled", false);
    $('#stop').attr("disabled", true);

  
   
    function calculateCount(callback) {
        var uri1 = 'api/images';

        //send ajax request
        $.getJSON(uri1)
         .done(function (data) {
             callback(data[0].Number);
             
         });
        
    }
   
    $('#start').on('click', function () {
        $('#start').attr("disabled", true);
        $('#stop').attr("disabled", false);
        var intervalId = 1000;
        var index = 1;
        intervalId = setInterval(function () {

            calculateCount(function (value) {
           count = value;
        console.log("count=" + count); //debug
       });

          

        //   var uri = 'api/images/' + index;
          
           $.ajax({
               type: 'GET',
               data: JSON,
               url: 'api/images/' + index,
               cache: false,
               success: function (images) {

                   $.each(images, function (i, image) {
                       console.log(image.Path);

                       $("img").attr("src", image.Path);
                       //$("img").fadeIn(1000);



                   });
                   console.log("index=" + index); //debug
                   if (index < count) {
                       index++;
                   }
                   else {
                       index = count;

                   }


               }


           });
    

          

            $('#stop').on('click', function () {
                $('#start').attr("disabled", false);
                $('#stop').attr("disabled", true);
                clearInterval(intervalId);
                index = 1;
            });


        }, 200);


        $('#start').attr("disabled", false);



    });


});
