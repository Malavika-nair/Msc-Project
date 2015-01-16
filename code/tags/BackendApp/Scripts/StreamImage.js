$(document).ready(function () {
    var count = 0;
    $('#start').attr("disabled", false);
    $('#stop').attr("disabled", true);

  
   
    function calculateCount(callback) {
        var uri1 = 'api/images';

        //sending ajax request
        $.getJSON(uri1)
         .done(function (data) {
             callback(data[0].Number);
             
         });
        
    }
   
    $('#start').on('click', function () {
        $('#start').attr("disabled", true);
        $('#stop').attr("disabled", false);
        var intervalId = 200;
        var index = 1;
        intervalId = setInterval(function () {

            calculateCount(function (value) {
           count = value;
        
       });

         
          
           $.ajax({
               type: 'GET',
               data: JSON,
               url: 'api/images/' + index,
               cache: false,
               success: function (images) {

                   $.each(images, function (i, image) {
                       console.log(image.Path);

                       $("img").attr("src", image.Path);
                      

                   });
                
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
                count = 0;
            });


        }, 200);


        $('#start').attr("disabled", false);



    });


});
